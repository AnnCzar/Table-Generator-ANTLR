package org.example;
import org.grammar.TableGrammarParser;
import org.grammar.TableGrammarParserBaseVisitor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableHtmlFlexVisitor extends TableGrammarParserBaseVisitor<String> {
    private final STGroup stGroup;
    private String currentBorderStyle = "grid"; // domyślny styl obramowania

    public TableHtmlFlexVisitor(STGroup stGroup) {
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
// do zagniezdzonych tabel
        ST tableHtml = isNested
                ? stGroup.getInstanceOf("tableIN")
                : stGroup.getInstanceOf("table");

        tableHtml.add("tableName", ctx.ID().getText());
        tableHtml.add("columns", visit(ctx.inside().column()));

        //wyrównanie-domyślnie center
        String align = (ctx.inside().align() != null)
                ? visit(ctx.inside().align())
                : "center";
        tableHtml.add("align", getAlignStyle(align));

        //styl obramowania-domyślnie grid)
        String borderStyleStr = ctx.inside().borderStyle() != null
                ? visit(ctx.inside().borderStyle())
                : "grid";
        this.currentBorderStyle = borderStyleStr;

        if (isNested) {
            // Dla zagnieżdżonych tabel używamy bardziej specyficznych selektorów
            switch (borderStyleStr.toLowerCase()) {
                case "frame":
                    tableHtml.add("tableBorderStyle", "border: 1px solid #ddd; border-collapse: collapse");
                    tableHtml.add("cellBorderStyle", "border: none");
                    break;
                case "none":
                    tableHtml.add("tableBorderStyle", "border: none");
                    tableHtml.add("cellBorderStyle", "border: none");
                    break;
                default:
                    tableHtml.add("tableBorderStyle", "border-collapse: collapse");
                    tableHtml.add("cellBorderStyle", "border: 1px solid #ddd");
            }
        } else {
            // Dla głównych tabel
            switch (borderStyleStr.toLowerCase()) {
                case "frame":
                    tableHtml.add("tableBorderStyle", stGroup.getInstanceOf("borderFrameTable").render());
                    tableHtml.add("cellBorderStyle", stGroup.getInstanceOf("borderFrameCell").render());
                    break;
                case "none":
                    tableHtml.add("tableBorderStyle", stGroup.getInstanceOf("borderNoneTable").render());
                    tableHtml.add("cellBorderStyle", stGroup.getInstanceOf("borderNoneCell").render());
                    break;
                default:
                    tableHtml.add("tableBorderStyle", stGroup.getInstanceOf("borderGridTable").render());
                    tableHtml.add("cellBorderStyle", stGroup.getInstanceOf("borderGridCell").render());
            }
        }

        tableHtml.add("header", visitHeadRow(ctx.inside().head().headRow()));
        tableHtml.add("rows", visitRows(ctx.inside().rows()));

        return tableHtml.render();
    }

    private String getBorderStyleCSS(String borderStyle) {
        return switch (borderStyle.toLowerCase()) {
            case "frame" -> stGroup.getInstanceOf("borderFrame").render();
            case "none" -> stGroup.getInstanceOf("borderNone").render();
            default -> stGroup.getInstanceOf("borderGrid").render();
        };
    }

    private String getAlignStyle(String align) {
        return switch (align.toLowerCase()) {
            case "left" -> stGroup.getInstanceOf("alignLeft").render();
            case "right" -> stGroup.getInstanceOf("alignRight").render();
            default -> stGroup.getInstanceOf("alignCenter").render();
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
        if (ctx == null || ctx.getChildCount() < 3) {
            return "center"; // Domyślne wyrównanie
        }

        return ctx.getChild(2).getText().toLowerCase();
    }

    @Override
    public String visitHead(TableGrammarParser.HeadContext ctx) {
        return visit(ctx.headRow());
    }

    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx) {
        List<String> cells = new ArrayList<>();

        String cellContent = visitFormattedText(ctx.formattedText());
        ST cell = stGroup.getInstanceOf("cell");
        cell.add("content", cellContent);
        cells.add(cell.render());

        if (ctx.headRow() != null) { // zbieram wszystkie komórki
            TableGrammarParser.HeadRowContext currentCtx = ctx.headRow();
            while (currentCtx != null) {
                String nextCellContent = visitFormattedText(currentCtx.formattedText());
                ST nextCell = stGroup.getInstanceOf("cell");
                nextCell.add("content", nextCellContent);
                cells.add(nextCell.render());

                currentCtx = currentCtx.headRow();
            }
        }

        ST row = stGroup.getInstanceOf("row");
        row.add("cells", String.join("", cells));
        return row.render();
    }


    public String visitRows(TableGrammarParser.RowsContext ctx) {
        List<String> htmlRows = new ArrayList<>();

        for (TableGrammarParser.RowContext rowCtx : ctx.row()) {
            htmlRows.add(visitRow(rowCtx));
        }


        return String.join("\n", htmlRows);
    }


    @Override
    public String visitRow(TableGrammarParser.RowContext ctx) {
        List<String> cells = new ArrayList<>();

        String cellContent = visitContent(ctx.content()); // dodawanie komórki
        ST cell = stGroup.getInstanceOf("cell");
        cell.add("content", cellContent);
        cells.add(cell.render());

        if (ctx.row() != null) {
            TableGrammarParser.RowContext currentCtx = ctx.row();
            while (currentCtx != null) {
                String nextCellContent = visitContent(currentCtx.content());
                ST nextCell = stGroup.getInstanceOf("cell");
                nextCell.add("content", nextCellContent);
                cells.add(nextCell.render());

                currentCtx = currentCtx.row();
            }
        }

        ST row = stGroup.getInstanceOf("row");
        row.add("cells", String.join("\n", cells));
        return row.render();
    }


    @Override
    public String visitContent(TableGrammarParser.ContentContext ctx) {
        if (ctx.formattedText() != null && !ctx.formattedText().isEmpty()) {
            return ctx.formattedText().stream()
                    .map(this::visitFormattedText)
                    .collect(Collectors.joining(" "));
        } else if (ctx.table() != null) {
            return visitTable(ctx.table(), true); // true BO to zagniezdzona tabela
        } else {
            return "";
        }
    }

    @Override
    public String visitFormattedText(TableGrammarParser.FormattedTextContext ctx) {
        if (ctx.TEXT() == null) {
            throw new NullPointerException("Missing TEXT in formattedText.");
        }

        // usuniecie cudzyslowow
        String text = ctx.TEXT().getText().replaceAll("^\"|\"$", "");

        if (ctx.ITALIC() != null) {
            ST italic = stGroup.getInstanceOf("italic");
            italic.add("text", text);
            text = italic.render();
        }

        if (ctx.BOLD() != null) {
            ST bold = stGroup.getInstanceOf("bold");
            bold.add("text", text);
            text = bold.render();
        }

        return text;
    }
}
