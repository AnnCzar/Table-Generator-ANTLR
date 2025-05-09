//package org.example;
//import org.grammar.TableGrammarParser;
//import org.grammar.TableGrammarParserBaseVisitor;
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class TableHtmlFlexVisitor extends TableGrammarParserBaseVisitor<String> {
//    private final STGroup stGroup;
////    private String currentBorderStyle = "grid"; // domyślny styl obramowania
//
//    public TableHtmlFlexVisitor(STGroup stGroup) {
//        this.stGroup = stGroup;
//    }
//
//    @Override
//    public String visitProgram(TableGrammarParser.ProgramContext ctx) {
//        return visit(ctx.table());
//    }
//
//    @Override
//    public String visitTable(TableGrammarParser.TableContext ctx) {
//        return visitTable(ctx, false);
//    }
//
//    public String visitTable(TableGrammarParser.TableContext ctx, boolean isNested) {
//// do zagniezdzonych tabel
//        ST tableHtml = isNested
//                ? stGroup.getInstanceOf("tableIN")
//                : stGroup.getInstanceOf("table");
//
//        tableHtml.add("tableName", ctx.ID().getText());
//        tableHtml.add("columns", visit(ctx.inside().column()));
//
//        //wyrównanie-domyślnie center
//        String align = (ctx.inside().align() != null)
//                ? visit(ctx.inside().align())
//                : "center";
//        tableHtml.add("align", getAlignStyle(align));
//
//        //styl obramowania-domyślnie grid)
//        String borderStyleStr = ctx.inside().borderStyle() != null
//                ? visit(ctx.inside().borderStyle())
//                : "grid";
////        this.currentBorderStyle = borderStyleStr;
////        String tableBorderStyle = getBorderStyleCSS(borderStyleStr);
////        tableHtml.add("borderStyle", tableBorderStyle);
//
//        tableHtml.add("borderStyle", getBorderStyleCSS(borderStyleStr));
//
//        tableHtml.add("header", visitHeadRow(ctx.inside().head().headRow()));
//        tableHtml.add("rows", visitRows(ctx.inside().rows()));
//
//        return tableHtml.render();
//    }
//
//    private String getBorderStyleCSS(String borderStyle) {
//        switch (borderStyle.toLowerCase()) {
//            case "grid":
//                return stGroup.getInstanceOf("borderGrid").render();
//            case "frame":
//                return stGroup.getInstanceOf("borderFrame").render();
//            case "none":
//                return stGroup.getInstanceOf("borderNone").render();
//            default:
//                return stGroup.getInstanceOf("borderGrid").render();
//        }
//    }
//
//    private String getAlignStyle(String align) {
//        switch (align.toLowerCase()) {
//            case "left":
//                return stGroup.getInstanceOf("alignLeft").render();
//            case "right":
//                return stGroup.getInstanceOf("alignRight").render();
//            case "center":
//                return stGroup.getInstanceOf("alignCenter").render();
//            default:
//                return stGroup.getInstanceOf("alignCenter").render();
//        }
//    }
//
//    @Override
//    public String visitColumn(TableGrammarParser.ColumnContext ctx) {
//        if (ctx.INT() == null) {
//            throw new NullPointerException("Missing column count (INT) in column definition.");
//        }
//        return ctx.INT().getText();
//    }
//
//    @Override
//    public String visitBorderStyle(TableGrammarParser.BorderStyleContext ctx) {
//        if (ctx.getChildCount() < 3) {
//            throw new IllegalArgumentException("Invalid border style syntax. Expected format: border : <style>");
//        }
//
//        String borderStyle = ctx.getChild(2).getText().toLowerCase();
//        if (!borderStyle.equals("grid") && !borderStyle.equals("frame") && !borderStyle.equals("none")) {
//            throw new IllegalArgumentException("Unknown border style: " + borderStyle);
//        }
//        return borderStyle;
//    }
//
//    @Override
//    public String visitAlign(TableGrammarParser.AlignContext ctx) {
//        if (ctx == null || ctx.getChildCount() < 3) {
//            return "center"; // Domyślne wyrównanie
//        }
//
//        String alignText = ctx.getChild(2).getText().toLowerCase();
//        return alignText;
//    }
//
//    @Override
//    public String visitHead(TableGrammarParser.HeadContext ctx) {
//        return visit(ctx.headRow());
//    }
//
//    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx) {
//        List<String> cells = new ArrayList<>();
//
//        String cellContent = visitFormattedText(ctx.formattedText());
//        ST cell = stGroup.getInstanceOf("cell");
//        cell.add("content", cellContent);
//        cells.add(cell.render());
//
//        if (ctx.headRow() != null) { // zbieram wszystkie komórki
//            TableGrammarParser.HeadRowContext currentCtx = ctx.headRow();
//            while (currentCtx != null) {
//                String nextCellContent = visitFormattedText(currentCtx.formattedText());
//                ST nextCell = stGroup.getInstanceOf("cell");
//                nextCell.add("content", nextCellContent);
//                cells.add(nextCell.render());
//
//                currentCtx = currentCtx.headRow();
//            }
//        }
//
//        ST row = stGroup.getInstanceOf("row");
//        row.add("cells", String.join("", cells));
//        return row.render();
//    }
//
//
//    public String visitRows(TableGrammarParser.RowsContext ctx) {
//        List<String> htmlRows = new ArrayList<>();
//
//        for (TableGrammarParser.RowContext rowCtx : ctx.row()) {
//            htmlRows.add(visitRow(rowCtx));
//        }
//
//
//        return String.join("\n", htmlRows);
//    }
//
//
//    @Override
//    public String visitRow(TableGrammarParser.RowContext ctx) {
//        List<String> cells = new ArrayList<>();
//
//        String cellContent = visitContent(ctx.content()); // dodawanie komórki
//        ST cell = stGroup.getInstanceOf("cell");
//        cell.add("content", cellContent);
//        cells.add(cell.render());
//
//        if (ctx.row() != null) {
//            TableGrammarParser.RowContext currentCtx = ctx.row();
//            while (currentCtx != null) {
//                String nextCellContent = visitContent(currentCtx.content());
//                ST nextCell = stGroup.getInstanceOf("cell");
//                nextCell.add("content", nextCellContent);
//                cells.add(nextCell.render());
//
//                currentCtx = currentCtx.row();
//            }
//        }
//
//        ST row = stGroup.getInstanceOf("row");
//        row.add("cells", String.join("\n", cells));
//        return row.render();
//    }
//
//
//    @Override
//    public String visitContent(TableGrammarParser.ContentContext ctx) {
//        if (ctx.formattedText() != null && !ctx.formattedText().isEmpty()) {
//            return ctx.formattedText().stream()
//                    .map(this::visitFormattedText)
//                    .collect(Collectors.joining(" "));
//        } else if (ctx.table() != null) {
//            return visitTable(ctx.table(), true); // true BO to zagniezdzona tabela
//        } else {
//            return "";
//        }
//    }
//
//    @Override
//    public String visitFormattedText(TableGrammarParser.FormattedTextContext ctx) {
//        if (ctx.TEXT() == null) {
//            throw new NullPointerException("Missing TEXT in formattedText.");
//        }
//
//        // usuniecie cudzyslowow
//        String text = ctx.TEXT().getText().replaceAll("^\"|\"$", "");
//
//        if (ctx.ITALIC() != null) {
//            text = "<em>" + text + "</em>";
//        }
//        if (ctx.BOLD() != null) {
//            text = "<strong>" + text + "</strong>";
//        }
//
//        return text;
//    }
//}
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
        // Wybierz odpowiedni szablon w zależności od tego, czy tabela jest zagnieżdżona
        ST tableHtml = isNested
                ? stGroup.getInstanceOf("tableIN")
                : stGroup.getInstanceOf("table");

        tableHtml.add("tableName", ctx.ID().getText());
        tableHtml.add("columns", visit(ctx.inside().column()));

        // Wyrównanie - domyślnie center
        String align = (ctx.inside().align() != null)
                ? visit(ctx.inside().align())
                : "center";
        tableHtml.add("align", getAlignStyle(align));

        // Styl obramowania - pobierz z definicji tej tabeli
        String borderStyleStr = ctx.inside().borderStyle() != null
                ? visit(ctx.inside().borderStyle())
                : "grid";

        // Zastosuj styl obramowania bezpośrednio do tej tabeli
        tableHtml.add("borderStyle", getBorderStyleCSS(borderStyleStr, isNested));

        tableHtml.add("header", visitHeadRow(ctx.inside().head().headRow(), isNested));
        tableHtml.add("rows", visitRows(ctx.inside().rows(), isNested));

        return tableHtml.render();
    }

    private String getBorderStyleCSS(String borderStyle, boolean isNested) {
        switch (borderStyle.toLowerCase()) {
            case "grid":
                ST gridTemplate = stGroup.getInstanceOf("borderGrid");
                gridTemplate.add("isNested", isNested);
                return gridTemplate.render();
            case "frame":
                ST frameTemplate = stGroup.getInstanceOf("borderFrame");
                frameTemplate.add("isNested", isNested);
                return frameTemplate.render();
            case "none":
                ST noneTemplate = stGroup.getInstanceOf("borderNone");
                noneTemplate.add("isNested", isNested);
                return noneTemplate.render();
            default:
                ST defaultTemplate = stGroup.getInstanceOf("borderGrid");
                defaultTemplate.add("isNested", isNested);
                return defaultTemplate.render();
        }
    }

    private String getAlignStyle(String align) {
        switch (align.toLowerCase()) {
            case "left":
                return stGroup.getInstanceOf("alignLeft").render();
            case "right":
                return stGroup.getInstanceOf("alignRight").render();
            case "center":
                return stGroup.getInstanceOf("alignCenter").render();
            default:
                return stGroup.getInstanceOf("alignCenter").render();
        }
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

        String alignText = ctx.getChild(2).getText().toLowerCase();
        return alignText;
    }

    @Override
    public String visitHead(TableGrammarParser.HeadContext ctx) {
        return visitHeadRow(ctx.headRow(), false);
    }

    public String visitHeadRow(TableGrammarParser.HeadRowContext ctx, boolean isNested) {
        List<String> cells = new ArrayList<>();

        String cellContent = visitFormattedText(ctx.formattedText());
        ST cell = stGroup.getInstanceOf("cell");
        cell.add("content", cellContent);
        cell.add("isNested", isNested);
        cells.add(cell.render());

        if (ctx.headRow() != null) {
            TableGrammarParser.HeadRowContext currentCtx = ctx.headRow();
            while (currentCtx != null) {
                String nextCellContent = visitFormattedText(currentCtx.formattedText());
                ST nextCell = stGroup.getInstanceOf("cell");
                nextCell.add("content", nextCellContent);
                nextCell.add("isNested", isNested);
                cells.add(nextCell.render());

                currentCtx = currentCtx.headRow();
            }
        }

        ST row = stGroup.getInstanceOf("row");
        row.add("cells", String.join("", cells));
        row.add("isNested", isNested);
        return row.render();
    }

    public String visitRows(TableGrammarParser.RowsContext ctx, boolean isNested) {
        List<String> htmlRows = new ArrayList<>();

        for (TableGrammarParser.RowContext rowCtx : ctx.row()) {
            htmlRows.add(visitRow(rowCtx, isNested));
        }

        return String.join("\n", htmlRows);
    }

    public String visitRow(TableGrammarParser.RowContext ctx, boolean isNested) {
        List<String> cells = new ArrayList<>();

        String cellContent = visitContent(ctx.content(), isNested);
        ST cell = stGroup.getInstanceOf("cell");
        cell.add("content", cellContent);
        cell.add("isNested", isNested);
        cells.add(cell.render());

        if (ctx.row() != null) {
            TableGrammarParser.RowContext currentCtx = ctx.row();
            while (currentCtx != null) {
                String nextCellContent = visitContent(currentCtx.content(), isNested);
                ST nextCell = stGroup.getInstanceOf("cell");
                nextCell.add("content", nextCellContent);
                nextCell.add("isNested", isNested);
                cells.add(nextCell.render());

                currentCtx = currentCtx.row();
            }
        }

        ST row = stGroup.getInstanceOf("row");
        row.add("cells", String.join("\n", cells));
        row.add("isNested", isNested);
        return row.render();
    }

    public String visitContent(TableGrammarParser.ContentContext ctx, boolean isNested) {
        if (ctx.formattedText() != null && !ctx.formattedText().isEmpty()) {
            return ctx.formattedText().stream()
                    .map(this::visitFormattedText)
                    .collect(Collectors.joining(" "));
        } else if (ctx.table() != null) {
            return visitTable(ctx.table(), true); // true, bo to zagnieżdżona tabela
        } else {
            return "";
        }
    }

    @Override
    public String visitFormattedText(TableGrammarParser.FormattedTextContext ctx) {
        if (ctx.TEXT() == null) {
            throw new NullPointerException("Missing TEXT in formattedText.");
        }

        // usunięcie cudzysłowów
        String text = ctx.TEXT().getText().replaceAll("^\"|\"$", "");

        if (ctx.ITALIC() != null) {
            text = "<em>" + text + "</em>";
        }
        if (ctx.BOLD() != null) {
            text = "<strong>" + text + "</strong>";
        }

        return text;
    }
}
