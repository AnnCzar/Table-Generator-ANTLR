// Generated from C:/Users/aczar/Desktop/polibuda/MiASI_projekt/TableGenerator/src/main/resources/org/grammar/TableGrammarParser.g4 by ANTLR 4.13.2
package org.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TableGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TableGrammarParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TableGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(TableGrammarParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInside(TableGrammarParser.InsideContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn(TableGrammarParser.ColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(TableGrammarParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#headRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadRow(TableGrammarParser.HeadRowContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#align}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlign(TableGrammarParser.AlignContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#borderStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorderStyle(TableGrammarParser.BorderStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#rows}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRows(TableGrammarParser.RowsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(TableGrammarParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(TableGrammarParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TableGrammarParser#formattedText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormattedText(TableGrammarParser.FormattedTextContext ctx);
}