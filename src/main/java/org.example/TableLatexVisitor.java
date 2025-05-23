package org.example;

import org.grammar.TableGrammarParser;
import org.grammar.TableGrammarParserBaseVisitor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TableLatexVisitor extends TableGrammarParserBaseVisitor<String> {
    private final STGroup stGroup;
    private String borderStyle = "";

    public TableLatexVisitor(STGroup stGroup) {
        this.stGroup = stGroup;
    }

    @Override
    public String visitProgram(TableGrammarParser.ProgramContext ctx) {
        return visit(ctx.table());
    }
    @Override
    public String visitTable(TableGrammarParser.TableContext ctx) {
        return visitTable(ctx, false);
    }

    public String visitTable(TableGrammarParser.TableContext ctx, boolean isNested) {
//        ST tableLatex = stGroup.getInstanceOf("table");
        ST tableLatex = isNested
        ? stGroup.getInstanceOf("tableIN")
        : stGroup.getInstanceOf("table");
        tableLatex.add("tableName", ctx.ID().getText());
        tableLatex.add("columns", visit(ctx.inside().column()));

        // Domyślny align, jeśli nie podano
        String align = (ctx.inside().align() != null)
                ? visit(ctx.inside().align())
                : "c"; // domyślnie wyrówananie center
        tableLatex.add("align", align);

        String borderStyleStr = ctx.inside().borderStyle() != null
                ? visitBorderStyle(ctx.inside().borderStyle())
                : "grid";  // Jeśli null, domyślna wartość "grid"
        tableLatex.add("addHlineAfterHeader", "grid".equalsIgnoreCase(borderStyleStr));

        tableLatex.add("border", border(ctx.inside().borderStyle() != null ? visit(ctx.inside().borderStyle()) : "grid", align , visit(ctx.inside().column())));
        tableLatex.add("header", visitHeadRow(ctx.inside().head().headRow(), borderStyleStr));
        tableLatex.add("rows", visitRows(ctx.inside().rows(), borderStyleStr));

        tableLatex.add("borderD", chcekBorder(borderStyleStr));

        return tableLatex.render();
    }

    private Boolean chcekBorder(String s) {
        return !s.equalsIgnoreCase("none");
    }


    private String border(String border, String align, String columns) {
        int cols = Integer.parseInt(columns);
        return switch (border) {
            case "grid" -> "|" + String.join("|", Collections.nCopies(cols, align)) + "|";
            case "frame" -> "|" + String.join("", Collections.nCopies(cols, align)) + "|";
            default -> String.join("", Collections.nCopies(cols, align));
        };
    }

    @Override
    public String visitColumn(TableGrammarParser.ColumnContext ctx) {

        if (ctx.INT() == null) {
            throw new NullPointerException("Missing column count (INT) in column definition.");
        }
        return ctx.INT().getText();
    }

    @Override
    public String visitBorderStyle(TableGrammarParser.BorderStyleContext ctx) {
        if (ctx.getChildCount() < 3) {
            throw new IllegalArgumentException("Invalid border style syntax. Expected format: border : <style>");
        }

        String borderStyle = ctx.getChild(2).getText().toLowerCase();
        if (!borderStyle.equals("grid") && !borderStyle.equals("frame") && !borderStyle.equals("none")) {
            throw new IllegalArgumentException("Unknown border style: " + borderStyle);
        }
        return borderStyle;
    }

    @Override
    public String visitAlign(TableGrammarParser.AlignContext ctx) {

        if (ctx == null || ctx.getChildCount() < 3) { // sprawdzenie czy jest podane align i ew. ustawinie domyslnego
            return "c";
        }

        String alignText = ctx.getChild(2).getText().toLowerCase(); // Pobieramy wartość align i konwertujemy na małe litery
        return switch (alignText) {
            case "center" -> "c";
            case "left" -> "l";
            case "right" -> "r";
            default -> throw new IllegalArgumentException("Unknown align type: " + alignText);
        };
    }

    @Override
    public String visitHead(TableGrammarParser.HeadContext ctx) {
        return visit(ctx.headRow());
    }

    @Override
    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx) {  //deprecated
        String left = visitFormattedText(ctx.formattedText());

        if (ctx.headRow() != null) {
            String right = visitHeadRow(ctx.headRow());
            ST two_cells = stGroup.getInstanceOf("two-cells");
            two_cells.add("left", left).add("right", right);
            return two_cells.render();
        }
        switch (borderStyle){
            case "frame":
            case "none":
                ST no_endline = stGroup.getInstanceOf("no-endline");
                no_endline.add("left", left);
                return no_endline.render();
            default:
                ST endline = stGroup.getInstanceOf("endline");
                endline.add("left", left);
                return endline.render();
        }
    }


    @Override
    public String visitRow(TableGrammarParser.RowContext ctx) {  //deprecated
        String left = visitContent(ctx.content());

        if(ctx.row() != null) {
            String right = visitRow(ctx.row());
            ST two_cells = stGroup.getInstanceOf("two-cells");
            two_cells.add("left", left).add("right", right);
            return two_cells.render();
        }
        switch (borderStyle){
            case "frame":
            case "none":
                ST no_endline = stGroup.getInstanceOf("no-endline");
                no_endline.add("left", left);
                return no_endline.render();
            default:
                ST endline = stGroup.getInstanceOf("endline");
                endline.add("left", left);
                return endline.render();
        }
    }

    @Override
    public String visitRows(TableGrammarParser.RowsContext ctx) {  //deprecated
        List<String> rows = new ArrayList<>();
        for (TableGrammarParser.RowContext rowCTX : ctx.row()) {
            rows.add(visitRow(rowCTX));
        }
        String joinedRows = String.join("\n", rows);
        if ("frame".equals(borderStyle)) {
            joinedRows += "\n\\hline"; // aby dolna linia sie pojawiła
        }

        if ("none".equals(borderStyle)) {
            int lastIndex = joinedRows.lastIndexOf("\n");  // znajduje ostatni znak nowej linii
            if (lastIndex != -1 && joinedRows.length() > lastIndex + 5) {
                joinedRows = joinedRows.substring(0, lastIndex + 1) + joinedRows.substring(lastIndex + 1, joinedRows.length() - 3); // usuwam ostanie //hline
            }
        }
        return joinedRows;

    }
    public String visitRows(TableGrammarParser.RowsContext ctx, String borderStyle) {
        List<String> rows = new ArrayList<>();
        for (TableGrammarParser.RowContext rowCTX : ctx.row()) {
            rows.add(visitRow(rowCTX, borderStyle));
        }
        String joinedRows = String.join("\n", rows);

        if ("frame".equals(borderStyle)) {
            joinedRows += "\n\\hline";
        }

        if ("none".equals(borderStyle)) {
            int lastIndex = joinedRows.lastIndexOf("\n");
            if (lastIndex != -1 && joinedRows.length() > lastIndex + 5) {
                joinedRows = joinedRows.substring(0, lastIndex + 1) + joinedRows.substring(lastIndex + 1, joinedRows.length() - 3);
            }
        }
        return joinedRows;
    }

    public String visitRow(TableGrammarParser.RowContext ctx, String borderStyle) {
        String left = visitContent(ctx.content());

        if (ctx.row() != null) {
            String right = visitRow(ctx.row(), borderStyle);
            ST two_cells = stGroup.getInstanceOf("two-cells");
            two_cells.add("left", left).add("right", right);
            return two_cells.render();
        }

        switch (borderStyle){
            case "frame":
            case "none":
                ST no_endline = stGroup.getInstanceOf("no-endline");
                no_endline.add("left", left);
                return no_endline.render();
            default:
                ST endline = stGroup.getInstanceOf("endline");
                endline.add("left", left);
                return endline.render();
        }
    }

    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx, String borderStyle) {
        String left = visitFormattedText(ctx.formattedText());

        if (ctx.headRow() != null) {
            String right = visitHeadRow(ctx.headRow(), borderStyle);
            return left + " & " + right;
        }
        ST no_endline = stGroup.getInstanceOf("no-endline");
        no_endline.add("left", left);
        return no_endline.render();

    }

@Override
public String visitContent(TableGrammarParser.ContentContext ctx) {
    if (ctx.formattedText() != null && !ctx.formattedText().isEmpty()) {
        return ctx.formattedText().stream()
                .map(this::visitFormattedText)  // iterujemy po liscie i przetwarzamy kazdy element
                .collect(Collectors.joining(" "));  // laczymy przetworzone fragmenty tekstu
    }
    else if (ctx.table() != null) {

        return visitTable(ctx.table(), true); // true bo zagniezdzona tabela
    }else {
        return "";
    }
}

    @Override
    public String visitFormattedText(TableGrammarParser.FormattedTextContext ctx) {
        if (ctx.TEXT() == null) {
            throw new NullPointerException("Missing TEXT in formattedText.");
        }

        String text = ctx.TEXT().getText().replaceAll("^\"|\"$", "");
        if ( ctx.ITALIC() != null ) {
            ST italic = stGroup.getInstanceOf("italic");
            italic.add("text", text);
            text = italic.render();
        }
        if ( ctx.BOLD() != null ) {
            ST bold = stGroup.getInstanceOf("bold");
            bold.add("text", text);
            text = bold.render();
        }
        return text;
    }
}
