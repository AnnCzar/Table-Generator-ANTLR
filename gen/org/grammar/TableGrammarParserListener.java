// Generated from C:/Users/aczar/Desktop/polibuda/MiASI_projekt/TableGenerator/src/main/resources/org/grammar/TableGrammarParser.g4 by ANTLR 4.13.2
package org.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TableGrammarParser}.
 */
public interface TableGrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TableGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TableGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(TableGrammarParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(TableGrammarParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterInside(TableGrammarParser.InsideContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitInside(TableGrammarParser.InsideContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(TableGrammarParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(TableGrammarParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(TableGrammarParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(TableGrammarParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#headRow}.
	 * @param ctx the parse tree
	 */
	void enterHeadRow(TableGrammarParser.HeadRowContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#headRow}.
	 * @param ctx the parse tree
	 */
	void exitHeadRow(TableGrammarParser.HeadRowContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#align}.
	 * @param ctx the parse tree
	 */
	void enterAlign(TableGrammarParser.AlignContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#align}.
	 * @param ctx the parse tree
	 */
	void exitAlign(TableGrammarParser.AlignContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#borderStyle}.
	 * @param ctx the parse tree
	 */
	void enterBorderStyle(TableGrammarParser.BorderStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#borderStyle}.
	 * @param ctx the parse tree
	 */
	void exitBorderStyle(TableGrammarParser.BorderStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#rows}.
	 * @param ctx the parse tree
	 */
	void enterRows(TableGrammarParser.RowsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#rows}.
	 * @param ctx the parse tree
	 */
	void exitRows(TableGrammarParser.RowsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(TableGrammarParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(TableGrammarParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(TableGrammarParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(TableGrammarParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TableGrammarParser#formattedText}.
	 * @param ctx the parse tree
	 */
	void enterFormattedText(TableGrammarParser.FormattedTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TableGrammarParser#formattedText}.
	 * @param ctx the parse tree
	 */
	void exitFormattedText(TableGrammarParser.FormattedTextContext ctx);
}