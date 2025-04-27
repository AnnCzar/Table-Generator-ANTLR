// Generated from C:/Users/martu/Desktop/studia/magisterka/1_sem/MiASI_projekt/src/main/resources/org/grammar/TableGrammarParser.g4 by ANTLR 4.13.2
package org.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TableGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, NOT=3, EQ=4, COMMA=5, SEMI=6, COLON=7, QUOTE=8, DIVIDER=9, 
		LPAREN=10, RPAREN=11, LCURLY=12, RCURLY=13, TABLE=14, COLUMNS=15, HEADER=16, 
		LAYOUT=17, ROWS=18, CAPTION=19, BORDER=20, ALIGN=21, LEFT=22, RIGHT=23, 
		CENTER=24, ITALIC=25, BOLD=26, FRAME=27, GRID=28, NONE=29, INT=30, WS=31, 
		TEXT=32, ID=33;
	public static final int
		RULE_program = 0, RULE_table = 1, RULE_inside = 2, RULE_column = 3, RULE_head = 4, 
		RULE_headRow = 5, RULE_align = 6, RULE_borderStyle = 7, RULE_rows = 8, 
		RULE_row = 9, RULE_content = 10, RULE_formattedText = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "table", "inside", "column", "head", "headRow", "align", "borderStyle", 
			"rows", "row", "content", "formattedText"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'or'", "'not'", "'='", "','", "';'", "':'", "'\"'", "'|'", 
			"'('", "')'", "'{'", "'}'", "'table'", "'columns'", "'header'", "'layout'", 
			"'rows'", "'caption'", "'border'", "'align'", "'left'", "'right'", "'center'", 
			"'italic'", "'bold'", "'frame'", "'grid'", "'none'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "NOT", "EQ", "COMMA", "SEMI", "COLON", "QUOTE", "DIVIDER", 
			"LPAREN", "RPAREN", "LCURLY", "RCURLY", "TABLE", "COLUMNS", "HEADER", 
			"LAYOUT", "ROWS", "CAPTION", "BORDER", "ALIGN", "LEFT", "RIGHT", "CENTER", 
			"ITALIC", "BOLD", "FRAME", "GRID", "NONE", "INT", "WS", "TEXT", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TableGrammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TableGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TableGrammarParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			table();
			setState(25);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(TableGrammarParser.TABLE, 0); }
		public TerminalNode ID() { return getToken(TableGrammarParser.ID, 0); }
		public TerminalNode LCURLY() { return getToken(TableGrammarParser.LCURLY, 0); }
		public InsideContext inside() {
			return getRuleContext(InsideContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(TableGrammarParser.RCURLY, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(TABLE);
			setState(28);
			match(ID);
			setState(29);
			match(LCURLY);
			setState(30);
			inside();
			setState(31);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsideContext extends ParserRuleContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public AlignContext align() {
			return getRuleContext(AlignContext.class,0);
		}
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public RowsContext rows() {
			return getRuleContext(RowsContext.class,0);
		}
		public BorderStyleContext borderStyle() {
			return getRuleContext(BorderStyleContext.class,0);
		}
		public InsideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inside; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterInside(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitInside(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitInside(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsideContext inside() throws RecognitionException {
		InsideContext _localctx = new InsideContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_inside);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			column();
			setState(34);
			align();
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BORDER) {
				{
				setState(35);
				borderStyle();
				}
			}

			setState(38);
			head();
			setState(39);
			rows();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnContext extends ParserRuleContext {
		public TerminalNode COLUMNS() { return getToken(TableGrammarParser.COLUMNS, 0); }
		public TerminalNode COLON() { return getToken(TableGrammarParser.COLON, 0); }
		public TerminalNode INT() { return getToken(TableGrammarParser.INT, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(COLUMNS);
			setState(42);
			match(COLON);
			setState(43);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeadContext extends ParserRuleContext {
		public TerminalNode HEADER() { return getToken(TableGrammarParser.HEADER, 0); }
		public TerminalNode COLON() { return getToken(TableGrammarParser.COLON, 0); }
		public TerminalNode LCURLY() { return getToken(TableGrammarParser.LCURLY, 0); }
		public HeadRowContext headRow() {
			return getRuleContext(HeadRowContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(TableGrammarParser.RCURLY, 0); }
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(HEADER);
			setState(46);
			match(COLON);
			setState(47);
			match(LCURLY);
			setState(48);
			headRow();
			setState(49);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeadRowContext extends ParserRuleContext {
		public FormattedTextContext formattedText() {
			return getRuleContext(FormattedTextContext.class,0);
		}
		public TerminalNode DIVIDER() { return getToken(TableGrammarParser.DIVIDER, 0); }
		public HeadRowContext headRow() {
			return getRuleContext(HeadRowContext.class,0);
		}
		public HeadRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headRow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterHeadRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitHeadRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitHeadRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadRowContext headRow() throws RecognitionException {
		HeadRowContext _localctx = new HeadRowContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_headRow);
		try {
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				formattedText();
				setState(52);
				match(DIVIDER);
				setState(53);
				headRow();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				formattedText();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlignContext extends ParserRuleContext {
		public TerminalNode ALIGN() { return getToken(TableGrammarParser.ALIGN, 0); }
		public TerminalNode COLON() { return getToken(TableGrammarParser.COLON, 0); }
		public TerminalNode LEFT() { return getToken(TableGrammarParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(TableGrammarParser.RIGHT, 0); }
		public TerminalNode CENTER() { return getToken(TableGrammarParser.CENTER, 0); }
		public AlignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_align; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterAlign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitAlign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitAlign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignContext align() throws RecognitionException {
		AlignContext _localctx = new AlignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_align);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ALIGN);
			setState(59);
			match(COLON);
			setState(60);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BorderStyleContext extends ParserRuleContext {
		public TerminalNode BORDER() { return getToken(TableGrammarParser.BORDER, 0); }
		public TerminalNode COLON() { return getToken(TableGrammarParser.COLON, 0); }
		public TerminalNode FRAME() { return getToken(TableGrammarParser.FRAME, 0); }
		public TerminalNode GRID() { return getToken(TableGrammarParser.GRID, 0); }
		public TerminalNode NONE() { return getToken(TableGrammarParser.NONE, 0); }
		public BorderStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_borderStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterBorderStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitBorderStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitBorderStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorderStyleContext borderStyle() throws RecognitionException {
		BorderStyleContext _localctx = new BorderStyleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_borderStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(BORDER);
			setState(63);
			match(COLON);
			setState(64);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 939524096L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RowsContext extends ParserRuleContext {
		public TerminalNode ROWS() { return getToken(TableGrammarParser.ROWS, 0); }
		public TerminalNode COLON() { return getToken(TableGrammarParser.COLON, 0); }
		public TerminalNode LCURLY() { return getToken(TableGrammarParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(TableGrammarParser.RCURLY, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(TableGrammarParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(TableGrammarParser.LPAREN, i);
		}
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(TableGrammarParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(TableGrammarParser.RPAREN, i);
		}
		public RowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rows; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterRows(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitRows(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitRows(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowsContext rows() throws RecognitionException {
		RowsContext _localctx = new RowsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rows);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(ROWS);
			setState(67);
			match(COLON);
			setState(68);
			match(LCURLY);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN) {
				{
				{
				setState(69);
				match(LPAREN);
				setState(70);
				row();
				setState(71);
				match(RPAREN);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RowContext extends ParserRuleContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode DIVIDER() { return getToken(TableGrammarParser.DIVIDER, 0); }
		public RowContext row() {
			return getRuleContext(RowContext.class,0);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_row);
		try {
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				content();
				setState(81);
				match(DIVIDER);
				setState(82);
				row();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				content();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContentContext extends ParserRuleContext {
		public List<FormattedTextContext> formattedText() {
			return getRuleContexts(FormattedTextContext.class);
		}
		public FormattedTextContext formattedText(int i) {
			return getRuleContext(FormattedTextContext.class,i);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_content);
		int _la;
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ITALIC:
			case BOLD:
			case TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(87);
					formattedText();
					}
					}
					setState(90); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4395630592L) != 0) );
				}
				break;
			case TABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				table();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormattedTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(TableGrammarParser.TEXT, 0); }
		public TerminalNode ITALIC() { return getToken(TableGrammarParser.ITALIC, 0); }
		public TerminalNode BOLD() { return getToken(TableGrammarParser.BOLD, 0); }
		public FormattedTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formattedText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).enterFormattedText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TableGrammarParserListener ) ((TableGrammarParserListener)listener).exitFormattedText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TableGrammarParserVisitor ) return ((TableGrammarParserVisitor<? extends T>)visitor).visitFormattedText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormattedTextContext formattedText() throws RecognitionException {
		FormattedTextContext _localctx = new FormattedTextContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formattedText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ITALIC || _la==BOLD) {
				{
				setState(95);
				_la = _input.LA(1);
				if ( !(_la==ITALIC || _la==BOLD) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(98);
			match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001!e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"%\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u00059\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\bJ\b\b\n\b\f\bM\t\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tV\b\t\u0001"+
		"\n\u0004\nY\b\n\u000b\n\f\nZ\u0001\n\u0003\n^\b\n\u0001\u000b\u0003\u000b"+
		"a\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0003\u0001\u0000"+
		"\u0016\u0018\u0001\u0000\u001b\u001d\u0001\u0000\u0019\u001a_\u0000\u0018"+
		"\u0001\u0000\u0000\u0000\u0002\u001b\u0001\u0000\u0000\u0000\u0004!\u0001"+
		"\u0000\u0000\u0000\u0006)\u0001\u0000\u0000\u0000\b-\u0001\u0000\u0000"+
		"\u0000\n8\u0001\u0000\u0000\u0000\f:\u0001\u0000\u0000\u0000\u000e>\u0001"+
		"\u0000\u0000\u0000\u0010B\u0001\u0000\u0000\u0000\u0012U\u0001\u0000\u0000"+
		"\u0000\u0014]\u0001\u0000\u0000\u0000\u0016`\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0003\u0002\u0001\u0000\u0019\u001a\u0005\u0000\u0000\u0001\u001a"+
		"\u0001\u0001\u0000\u0000\u0000\u001b\u001c\u0005\u000e\u0000\u0000\u001c"+
		"\u001d\u0005!\u0000\u0000\u001d\u001e\u0005\f\u0000\u0000\u001e\u001f"+
		"\u0003\u0004\u0002\u0000\u001f \u0005\r\u0000\u0000 \u0003\u0001\u0000"+
		"\u0000\u0000!\"\u0003\u0006\u0003\u0000\"$\u0003\f\u0006\u0000#%\u0003"+
		"\u000e\u0007\u0000$#\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000"+
		"%&\u0001\u0000\u0000\u0000&\'\u0003\b\u0004\u0000\'(\u0003\u0010\b\u0000"+
		"(\u0005\u0001\u0000\u0000\u0000)*\u0005\u000f\u0000\u0000*+\u0005\u0007"+
		"\u0000\u0000+,\u0005\u001e\u0000\u0000,\u0007\u0001\u0000\u0000\u0000"+
		"-.\u0005\u0010\u0000\u0000./\u0005\u0007\u0000\u0000/0\u0005\f\u0000\u0000"+
		"01\u0003\n\u0005\u000012\u0005\r\u0000\u00002\t\u0001\u0000\u0000\u0000"+
		"34\u0003\u0016\u000b\u000045\u0005\t\u0000\u000056\u0003\n\u0005\u0000"+
		"69\u0001\u0000\u0000\u000079\u0003\u0016\u000b\u000083\u0001\u0000\u0000"+
		"\u000087\u0001\u0000\u0000\u00009\u000b\u0001\u0000\u0000\u0000:;\u0005"+
		"\u0015\u0000\u0000;<\u0005\u0007\u0000\u0000<=\u0007\u0000\u0000\u0000"+
		"=\r\u0001\u0000\u0000\u0000>?\u0005\u0014\u0000\u0000?@\u0005\u0007\u0000"+
		"\u0000@A\u0007\u0001\u0000\u0000A\u000f\u0001\u0000\u0000\u0000BC\u0005"+
		"\u0012\u0000\u0000CD\u0005\u0007\u0000\u0000DK\u0005\f\u0000\u0000EF\u0005"+
		"\n\u0000\u0000FG\u0003\u0012\t\u0000GH\u0005\u000b\u0000\u0000HJ\u0001"+
		"\u0000\u0000\u0000IE\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000"+
		"KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LN\u0001\u0000\u0000"+
		"\u0000MK\u0001\u0000\u0000\u0000NO\u0005\r\u0000\u0000O\u0011\u0001\u0000"+
		"\u0000\u0000PQ\u0003\u0014\n\u0000QR\u0005\t\u0000\u0000RS\u0003\u0012"+
		"\t\u0000SV\u0001\u0000\u0000\u0000TV\u0003\u0014\n\u0000UP\u0001\u0000"+
		"\u0000\u0000UT\u0001\u0000\u0000\u0000V\u0013\u0001\u0000\u0000\u0000"+
		"WY\u0003\u0016\u000b\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[^\u0001\u0000"+
		"\u0000\u0000\\^\u0003\u0002\u0001\u0000]X\u0001\u0000\u0000\u0000]\\\u0001"+
		"\u0000\u0000\u0000^\u0015\u0001\u0000\u0000\u0000_a\u0007\u0002\u0000"+
		"\u0000`_\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000"+
		"\u0000\u0000bc\u0005 \u0000\u0000c\u0017\u0001\u0000\u0000\u0000\u0007"+
		"$8KUZ]`";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}