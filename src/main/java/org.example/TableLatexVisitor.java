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
    private ST latexTable;
    private int columnsCount;
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
        this.columnsCount = Integer.parseInt(visit(ctx.inside().column()));

        ST tableLatex = stGroup.getInstanceOf("table");
        tableLatex.add("tableName", ctx.ID().getText());
        tableLatex.add("columns", visit(ctx.inside().column()));
        tableLatex.add("align", visit(ctx.inside().align()));
        tableLatex.add("border", border(ctx.inside().borderStyle() != null ? visit(ctx.inside().borderStyle()) : " grid", visit(ctx.inside().align()) , visit(ctx.inside().column())));
        tableLatex.add("header", visit(ctx.inside().head()));
        tableLatex.add("rows", visit(ctx.inside().rows()));
        tableLatex.add("borderD", chcekBorder(visitBorderStyle(ctx.inside().borderStyle())));
        return tableLatex.render();
    }

    private Boolean chcekBorder(String s) {
        if (s.equalsIgnoreCase("none")) {

            return false;
        }
        else{
            return true;
        }
    }

//    private String border(String border, String align, String columns) {
//<[1..repeatCount]:{x | <expression>}; separator=separator>

//        switch (border) {
//            case "frame":
//                return new ST("<[repeat]:{x | <expression>}; separator=separator>")
//                        .add("repeat", columns)
//                        .add("expresion", align)
//                        .add("separator", "|").render();
//
//            case "grid":
//                return "";
//            case "none":
//                return "";
//            default:
//                return "";
//        }

        private String border(String border, String align, String columns) {
            int cols = Integer.parseInt(columns);
            switch (border) {
                case "grid":
                    return "|" + String.join("|", Collections.nCopies(cols, align)) + "|";
                case "frame":
                    return "|" + String.join("", Collections.nCopies(cols, align)) + "|";
                case "none":
                    return String.join("", Collections.nCopies(cols, align));
                default:
                    return String.join("", Collections.nCopies(cols, align)) ;
            }
        }


    @Override
    public String visitColumn(TableGrammarParser.ColumnContext ctx) {
        return ctx.INT().getText();
    }

    @Override
    public String visitBorderStyle(TableGrammarParser.BorderStyleContext ctx) {
        borderStyle = ctx.getChild(2).getText();
        System.out.println("styl obramowania" + ctx.getChild(2).getText());
        return ctx.getChild(2).getText();
    }

    @Override
    public String visitAlign(TableGrammarParser.AlignContext ctx) {
        String alignText = ctx.getChild(2).getText().toLowerCase(); // Pobieramy wartość align i konwertujemy na małe litery
        switch (alignText) {
            case "center": return "c";
            case "left": return "l";
            case "right": return "r";
            default: throw new IllegalArgumentException("Nieznane wyrównanie: " + alignText);
        }
    }


    @Override
    public String visitHead(TableGrammarParser.HeadContext ctx) {
        return visit(ctx.headRow());
    }

//    @Override
//    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx) {
//        List<String> header = ctx.formattedText().
//    }


    @Override
    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx) {
        String left = visitFormattedText(ctx.formattedText()); // Przetwarzamy pierwszą część

        if (ctx.headRow() != null) {  // Jeśli jest więcej kolumn
            String right = visitHeadRow(ctx.headRow());  // Rekurencyjne wywołanie
            return left + " & " + right;
        }
        switch (borderStyle){
            case "grid":
                return left + " \\\\ \\hline";
            case "frame":
                return left + " \\\\";
            case "none":
                return left + " \\\\";
            default:
                return left + "\\\\ \\hline";
        }
    }


    @Override
    public String visitRow(TableGrammarParser.RowContext ctx) {
        String left = visitContent(ctx.content());

        if(ctx.row() != null) {
            String right = visitRow(ctx.row());
            return left + " & " + right;
        }
        switch (borderStyle){
            case "grid":
                return left + " \\\\ \\hline";
            case "frame":
                return left + " \\\\";
            case "none":
                return left + "\\\\";
            default:
                return left + "\\\\ \\hline";


        }
    }

    @Override
    public String visitRows(TableGrammarParser.RowsContext ctx) {
        List<String> rows = new ArrayList<>();

        for (TableGrammarParser.RowContext rowCTX : ctx.row()) {
            rows.add(visitRow(rowCTX));
        }
        String joinedRows = String.join("\n", rows);
        if ("frame".equals(borderStyle)) {
            joinedRows += "\n\\hline"; // aby dolna linia sie pojawiła
        }

        if ("none".equals(borderStyle)) {
            int lastIndex = joinedRows.lastIndexOf("\n");
            if (lastIndex != -1 && joinedRows.length() > lastIndex + 5) {
                joinedRows = joinedRows.substring(0, lastIndex + 1) + joinedRows.substring(lastIndex + 1, joinedRows.length() - 5);
            }
        }
        return joinedRows;

    }
//
//    @Override
//    public String visitContent(TableGrammarParser.ContentContext ctx) {
//        if (  ctx.formattedText() != null) {
//            return visitFormattedText(ctx.formattedText());
//        }
//        else{
//            return visitTable(ctx.table());
//        }
//    }
@Override
public String visitContent(TableGrammarParser.ContentContext ctx) {
    if (ctx.formattedText() != null && !ctx.formattedText().isEmpty()) {
        return ctx.formattedText().stream()
                .map(this::visitFormattedText)  // Iterujemy po liście i przetwarzamy każdy element
                .collect(Collectors.joining(" "));  // Łączymy przetworzone fragmenty tekstu
    } else {
        return visitTable(ctx.table());
    }
}


    @Override
    public String visitFormattedText(TableGrammarParser.FormattedTextContext ctx) {
        String text = ctx.TEXT().getText().replaceAll("^\"|\"$", "");
        if ( ctx.ITALIC() != null ) {
            text = "\\textit{" + text + "}";
        }
        if ( ctx.BOLD() != null ) {
            text = "\\textbf{" + text + "}";
        }
        return text;
    }
}