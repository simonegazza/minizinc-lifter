// Generated from FlatZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.flatzinc;

import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape" })
public class FlatZincParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int PREDICATE = 1, ARRAY = 2, OF = 3, VAR = 4, BOOL = 5, INT = 6, FLOAT = 7, SET = 8,
		CONSTRAINT = 9,
		SOLVE = 10, SATISFY = 11, MINIMIZE = 12, MAXIMIZE = 13, TRUE = 14, FALSE = 15, LPAREN = 16,
		RPAREN = 17, LBRACK = 18, RBRACK = 19, LBRACE = 20, RBRACE = 21, COLON = 22, SEMI = 23,
		COMMA = 24, ASSIGN = 25, RANGE = 26, DCOLON = 27, INT_LITERAL = 28, FLOAT_LITERAL = 29,
		STRING_LITERAL = 30, IDENTIFIER = 31, VAR_PAR_IDENTIFIER = 32, WS = 33, LINE_COMMENT = 34,
		BLOCK_COMMENT = 35;
	public static final int RULE_model = 0, RULE_predicateItem = 1, RULE_predParamDeclList = 2, RULE_predParamDecl = 3,
		RULE_basicParType = 4, RULE_basicVarType = 5, RULE_arrayVarType = 6, RULE_indexSet = 7,
		RULE_basicPredParamType = 8, RULE_predParamType = 9, RULE_predIndexSet = 10,
		RULE_basicLiteralExpr = 11, RULE_basicExpr = 12, RULE_expr = 13, RULE_varParIdentifier = 14,
		RULE_boolLiteral = 15, RULE_intLiteral = 16, RULE_floatLiteral = 17, RULE_setLiteral = 18,
		RULE_arrayLiteral = 19, RULE_parArrayLiteral = 20, RULE_intLiteralList = 21,
		RULE_floatLiteralList = 22, RULE_basicExprList = 23, RULE_basicLiteralExprList = 24,
		RULE_exprList = 25, RULE_annExprList = 26, RULE_parDeclItem = 27, RULE_varDeclItem = 28,
		RULE_constraintItem = 29, RULE_solveItem = 30, RULE_annotations = 31,
		RULE_annotation = 32, RULE_annExpr = 33, RULE_basicAnnExpr = 34, RULE_basicAnnExprList = 35,
		RULE_identifier = 36, RULE_stringLiteral = 37;

	private static String[] makeRuleNames() {
		return new String[] {
				"model", "predicateItem", "predParamDeclList", "predParamDecl", "basicParType",
				"basicVarType", "arrayVarType", "indexSet", "basicPredParamType", "predParamType",
				"predIndexSet", "basicLiteralExpr", "basicExpr", "expr", "varParIdentifier",
				"boolLiteral", "intLiteral", "floatLiteral", "setLiteral", "arrayLiteral",
				"parArrayLiteral", "intLiteralList", "floatLiteralList", "basicExprList",
				"basicLiteralExprList", "exprList", "annExprList", "parDeclItem", "varDeclItem",
				"constraintItem", "solveItem", "annotations", "annotation", "annExpr",
				"basicAnnExpr", "basicAnnExprList", "identifier", "stringLiteral"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
				null, "'predicate'", "'array'", "'of'", "'var'", "'bool'", "'int'", "'float'",
				"'set'", "'constraint'", "'solve'", "'satisfy'", "'minimize'", "'maximize'",
				"'true'", "'false'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'",
				"';'", "','", "'='", "'..'", "'::'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
				null, "PREDICATE", "ARRAY", "OF", "VAR", "BOOL", "INT", "FLOAT", "SET",
				"CONSTRAINT", "SOLVE", "SATISFY", "MINIMIZE", "MAXIMIZE", "TRUE", "FALSE",
				"LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "COLON",
				"SEMI", "COMMA", "ASSIGN", "RANGE", "DCOLON", "INT_LITERAL", "FLOAT_LITERAL",
				"STRING_LITERAL", "IDENTIFIER", "VAR_PAR_IDENTIFIER", "WS", "LINE_COMMENT",
				"BLOCK_COMMENT"
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
	public String getGrammarFileName() {
		return "FlatZinc.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public FlatZincParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModelContext extends ParserRuleContext {
		public SolveItemContext solveItem() {
			return getRuleContext(SolveItemContext.class, 0);
		}

		public TerminalNode EOF() {
			return getToken(FlatZincParser.EOF, 0);
		}

		public List<PredicateItemContext> predicateItem() {
			return getRuleContexts(PredicateItemContext.class);
		}

		public PredicateItemContext predicateItem(int i) {
			return getRuleContext(PredicateItemContext.class, i);
		}

		public List<ParDeclItemContext> parDeclItem() {
			return getRuleContexts(ParDeclItemContext.class);
		}

		public ParDeclItemContext parDeclItem(int i) {
			return getRuleContext(ParDeclItemContext.class, i);
		}

		public List<VarDeclItemContext> varDeclItem() {
			return getRuleContexts(VarDeclItemContext.class);
		}

		public VarDeclItemContext varDeclItem(int i) {
			return getRuleContext(VarDeclItemContext.class, i);
		}

		public List<ConstraintItemContext> constraintItem() {
			return getRuleContexts(ConstraintItemContext.class);
		}

		public ConstraintItemContext constraintItem(int i) {
			return getRuleContext(ConstraintItemContext.class, i);
		}

		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_model;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterModel(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitModel(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitModel(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == PREDICATE) {
					{
						{
							setState(76);
							predicateItem();
						}
					}
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(82);
								parDeclItem();
							}
						}
					}
					setState(87);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == ARRAY || _la == VAR) {
					{
						{
							setState(88);
							varDeclItem();
						}
					}
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == CONSTRAINT) {
					{
						{
							setState(94);
							constraintItem();
						}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(100);
				solveItem();
				setState(101);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateItemContext extends ParserRuleContext {
		public TerminalNode PREDICATE() {
			return getToken(FlatZincParser.PREDICATE, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FlatZincParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FlatZincParser.RPAREN, 0);
		}

		public TerminalNode SEMI() {
			return getToken(FlatZincParser.SEMI, 0);
		}

		public PredParamDeclListContext predParamDeclList() {
			return getRuleContext(PredParamDeclListContext.class, 0);
		}

		public PredicateItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predicateItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterPredicateItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitPredicateItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitPredicateItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredicateItemContext predicateItem() throws RecognitionException {
		PredicateItemContext _localctx = new PredicateItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_predicateItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(103);
				match(PREDICATE);
				setState(104);
				identifier();
				setState(105);
				match(LPAREN);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 806355444L) != 0)) {
					{
						setState(106);
						predParamDeclList();
					}
				}

				setState(109);
				match(RPAREN);
				setState(110);
				match(SEMI);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredParamDeclListContext extends ParserRuleContext {
		public List<PredParamDeclContext> predParamDecl() {
			return getRuleContexts(PredParamDeclContext.class);
		}

		public PredParamDeclContext predParamDecl(int i) {
			return getRuleContext(PredParamDeclContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public PredParamDeclListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predParamDeclList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterPredParamDeclList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitPredParamDeclList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitPredParamDeclList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredParamDeclListContext predParamDeclList() throws RecognitionException {
		PredParamDeclListContext _localctx = new PredParamDeclListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_predParamDeclList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(112);
				predParamDecl();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(113);
							match(COMMA);
							setState(114);
							predParamDecl();
						}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredParamDeclContext extends ParserRuleContext {
		public PredParamTypeContext predParamType() {
			return getRuleContext(PredParamTypeContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(FlatZincParser.COLON, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public PredParamDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predParamDecl;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterPredParamDecl(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitPredParamDecl(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitPredParamDecl(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredParamDeclContext predParamDecl() throws RecognitionException {
		PredParamDeclContext _localctx = new PredParamDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_predParamDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(120);
				predParamType();
				setState(121);
				match(COLON);
				setState(122);
				identifier();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicParTypeContext extends ParserRuleContext {
		public TerminalNode BOOL() {
			return getToken(FlatZincParser.BOOL, 0);
		}

		public TerminalNode INT() {
			return getToken(FlatZincParser.INT, 0);
		}

		public TerminalNode FLOAT() {
			return getToken(FlatZincParser.FLOAT, 0);
		}

		public TerminalNode SET() {
			return getToken(FlatZincParser.SET, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public BasicParTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicParType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicParType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicParType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicParType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicParTypeContext basicParType() throws RecognitionException {
		BasicParTypeContext _localctx = new BasicParTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_basicParType);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				enterOuterAlt(_localctx, 1); {
				setState(124);
				match(BOOL);
			}
				break;
			case INT:
				enterOuterAlt(_localctx, 2); {
				setState(125);
				match(INT);
			}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3); {
				setState(126);
				match(FLOAT);
			}
				break;
			case SET:
				enterOuterAlt(_localctx, 4); {
				setState(127);
				match(SET);
				setState(128);
				match(OF);
				setState(129);
				match(INT);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicVarTypeContext extends ParserRuleContext {
		public TerminalNode VAR() {
			return getToken(FlatZincParser.VAR, 0);
		}

		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public List<IntLiteralContext> intLiteral() {
			return getRuleContexts(IntLiteralContext.class);
		}

		public IntLiteralContext intLiteral(int i) {
			return getRuleContext(IntLiteralContext.class, i);
		}

		public TerminalNode RANGE() {
			return getToken(FlatZincParser.RANGE, 0);
		}

		public TerminalNode LBRACE() {
			return getToken(FlatZincParser.LBRACE, 0);
		}

		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
		}

		public TerminalNode RBRACE() {
			return getToken(FlatZincParser.RBRACE, 0);
		}

		public List<FloatLiteralContext> floatLiteral() {
			return getRuleContexts(FloatLiteralContext.class);
		}

		public FloatLiteralContext floatLiteral(int i) {
			return getRuleContext(FloatLiteralContext.class, i);
		}

		public TerminalNode SET() {
			return getToken(FlatZincParser.SET, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public BasicVarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicVarType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicVarType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicVarType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicVarType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicVarTypeContext basicVarType() throws RecognitionException {
		BasicVarTypeContext _localctx = new BasicVarTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_basicVarType);
		int _la;
		try {
			setState(164);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(132);
				match(VAR);
				setState(133);
				basicParType();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(134);
				match(VAR);
				setState(135);
				intLiteral();
				setState(136);
				match(RANGE);
				setState(137);
				intLiteral();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(139);
				match(VAR);
				setState(140);
				match(LBRACE);
				setState(141);
				intLiteralList();
				setState(142);
				match(RBRACE);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(144);
				match(VAR);
				setState(145);
				floatLiteral();
				setState(146);
				match(RANGE);
				setState(147);
				floatLiteral();
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(149);
				match(VAR);
				setState(150);
				match(SET);
				setState(151);
				match(OF);
				setState(152);
				intLiteral();
				setState(153);
				match(RANGE);
				setState(154);
				intLiteral();
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(156);
				match(VAR);
				setState(157);
				match(SET);
				setState(158);
				match(OF);
				setState(159);
				match(LBRACE);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(160);
						intLiteralList();
					}
				}

				setState(163);
				match(RBRACE);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayVarTypeContext extends ParserRuleContext {
		public TerminalNode ARRAY() {
			return getToken(FlatZincParser.ARRAY, 0);
		}

		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public BasicVarTypeContext basicVarType() {
			return getRuleContext(BasicVarTypeContext.class, 0);
		}

		public ArrayVarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayVarType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterArrayVarType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitArrayVarType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitArrayVarType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayVarTypeContext arrayVarType() throws RecognitionException {
		ArrayVarTypeContext _localctx = new ArrayVarTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arrayVarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(166);
				match(ARRAY);
				setState(167);
				match(LBRACK);
				setState(168);
				indexSet();
				setState(169);
				match(RBRACK);
				setState(170);
				match(OF);
				setState(171);
				basicVarType();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexSetContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() {
			return getToken(FlatZincParser.INT_LITERAL, 0);
		}

		public TerminalNode RANGE() {
			return getToken(FlatZincParser.RANGE, 0);
		}

		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class, 0);
		}

		public IndexSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexSet;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterIndexSet(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitIndexSet(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitIndexSet(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexSetContext indexSet() throws RecognitionException {
		IndexSetContext _localctx = new IndexSetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_indexSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(173);
				match(INT_LITERAL);
				setState(174);
				match(RANGE);
				setState(175);
				intLiteral();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicPredParamTypeContext extends ParserRuleContext {
		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public BasicVarTypeContext basicVarType() {
			return getRuleContext(BasicVarTypeContext.class, 0);
		}

		public List<IntLiteralContext> intLiteral() {
			return getRuleContexts(IntLiteralContext.class);
		}

		public IntLiteralContext intLiteral(int i) {
			return getRuleContext(IntLiteralContext.class, i);
		}

		public TerminalNode RANGE() {
			return getToken(FlatZincParser.RANGE, 0);
		}

		public List<FloatLiteralContext> floatLiteral() {
			return getRuleContexts(FloatLiteralContext.class);
		}

		public FloatLiteralContext floatLiteral(int i) {
			return getRuleContext(FloatLiteralContext.class, i);
		}

		public TerminalNode LBRACE() {
			return getToken(FlatZincParser.LBRACE, 0);
		}

		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
		}

		public TerminalNode RBRACE() {
			return getToken(FlatZincParser.RBRACE, 0);
		}

		public TerminalNode SET() {
			return getToken(FlatZincParser.SET, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public BasicPredParamTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicPredParamType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicPredParamType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicPredParamType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicPredParamType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicPredParamTypeContext basicPredParamType() throws RecognitionException {
		BasicPredParamTypeContext _localctx = new BasicPredParamTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_basicPredParamType);
		int _la;
		try {
			setState(204);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(177);
				basicParType();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(178);
				basicVarType();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(179);
				intLiteral();
				setState(180);
				match(RANGE);
				setState(181);
				intLiteral();
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(183);
				floatLiteral();
				setState(184);
				match(RANGE);
				setState(185);
				floatLiteral();
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(187);
				match(LBRACE);
				setState(188);
				intLiteralList();
				setState(189);
				match(RBRACE);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(191);
				match(SET);
				setState(192);
				match(OF);
				setState(193);
				intLiteral();
				setState(194);
				match(RANGE);
				setState(195);
				intLiteral();
			}
				break;
			case 7:
				enterOuterAlt(_localctx, 7); {
				setState(197);
				match(SET);
				setState(198);
				match(OF);
				setState(199);
				match(LBRACE);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(200);
						intLiteralList();
					}
				}

				setState(203);
				match(RBRACE);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredParamTypeContext extends ParserRuleContext {
		public BasicPredParamTypeContext basicPredParamType() {
			return getRuleContext(BasicPredParamTypeContext.class, 0);
		}

		public TerminalNode ARRAY() {
			return getToken(FlatZincParser.ARRAY, 0);
		}

		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public PredIndexSetContext predIndexSet() {
			return getRuleContext(PredIndexSetContext.class, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public PredParamTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predParamType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterPredParamType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitPredParamType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitPredParamType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredParamTypeContext predParamType() throws RecognitionException {
		PredParamTypeContext _localctx = new PredParamTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_predParamType);
		try {
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
			case BOOL:
			case INT:
			case FLOAT:
			case SET:
			case LBRACE:
			case INT_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(206);
				basicPredParamType();
			}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 2); {
				setState(207);
				match(ARRAY);
				setState(208);
				match(LBRACK);
				setState(209);
				predIndexSet();
				setState(210);
				match(RBRACK);
				setState(211);
				match(OF);
				setState(212);
				basicPredParamType();
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredIndexSetContext extends ParserRuleContext {
		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
		}

		public TerminalNode INT() {
			return getToken(FlatZincParser.INT, 0);
		}

		public PredIndexSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predIndexSet;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterPredIndexSet(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitPredIndexSet(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitPredIndexSet(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredIndexSetContext predIndexSet() throws RecognitionException {
		PredIndexSetContext _localctx = new PredIndexSetContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_predIndexSet);
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(216);
				indexSet();
			}
				break;
			case INT:
				enterOuterAlt(_localctx, 2); {
				setState(217);
				match(INT);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicLiteralExprContext extends ParserRuleContext {
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class, 0);
		}

		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class, 0);
		}

		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class, 0);
		}

		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class, 0);
		}

		public BasicLiteralExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicLiteralExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicLiteralExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicLiteralExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicLiteralExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicLiteralExprContext basicLiteralExpr() throws RecognitionException {
		BasicLiteralExprContext _localctx = new BasicLiteralExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_basicLiteralExpr);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(220);
				boolLiteral();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(221);
				intLiteral();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(222);
				floatLiteral();
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(223);
				setLiteral();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicExprContext extends ParserRuleContext {
		public BasicLiteralExprContext basicLiteralExpr() {
			return getRuleContext(BasicLiteralExprContext.class, 0);
		}

		public VarParIdentifierContext varParIdentifier() {
			return getRuleContext(VarParIdentifierContext.class, 0);
		}

		public BasicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicExprContext basicExpr() throws RecognitionException {
		BasicExprContext _localctx = new BasicExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_basicExpr);
		try {
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case LBRACE:
			case INT_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(226);
				basicLiteralExpr();
			}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2); {
				setState(227);
				varParIdentifier();
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class, 0);
		}

		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class, 0);
		}

		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case LBRACE:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1); {
				setState(230);
				basicExpr();
			}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 2); {
				setState(231);
				arrayLiteral();
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarParIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public VarParIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_varParIdentifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterVarParIdentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitVarParIdentifier(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitVarParIdentifier(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final VarParIdentifierContext varParIdentifier() throws RecognitionException {
		VarParIdentifierContext _localctx = new VarParIdentifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_varParIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(234);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolLiteralContext extends ParserRuleContext {
		public TerminalNode FALSE() {
			return getToken(FlatZincParser.FALSE, 0);
		}

		public TerminalNode TRUE() {
			return getToken(FlatZincParser.TRUE, 0);
		}

		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_boolLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBoolLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBoolLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBoolLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_boolLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(236);
				_la = _input.LA(1);
				if (!(_la == TRUE || _la == FALSE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF)
						matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntLiteralContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() {
			return getToken(FlatZincParser.INT_LITERAL, 0);
		}

		public IntLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_intLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterIntLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitIntLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitIntLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IntLiteralContext intLiteral() throws RecognitionException {
		IntLiteralContext _localctx = new IntLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(238);
				match(INT_LITERAL);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() {
			return getToken(FlatZincParser.FLOAT_LITERAL, 0);
		}

		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_floatLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterFloatLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitFloatLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitFloatLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_floatLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(240);
				match(FLOAT_LITERAL);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACE() {
			return getToken(FlatZincParser.LBRACE, 0);
		}

		public TerminalNode RBRACE() {
			return getToken(FlatZincParser.RBRACE, 0);
		}

		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
		}

		public List<IntLiteralContext> intLiteral() {
			return getRuleContexts(IntLiteralContext.class);
		}

		public IntLiteralContext intLiteral(int i) {
			return getRuleContext(IntLiteralContext.class, i);
		}

		public TerminalNode RANGE() {
			return getToken(FlatZincParser.RANGE, 0);
		}

		public FloatLiteralListContext floatLiteralList() {
			return getRuleContext(FloatLiteralListContext.class, 0);
		}

		public List<FloatLiteralContext> floatLiteral() {
			return getRuleContexts(FloatLiteralContext.class);
		}

		public FloatLiteralContext floatLiteral(int i) {
			return getRuleContext(FloatLiteralContext.class, i);
		}

		public SetLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_setLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterSetLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitSetLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitSetLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final SetLiteralContext setLiteral() throws RecognitionException {
		SetLiteralContext _localctx = new SetLiteralContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_setLiteral);
		int _la;
		try {
			setState(260);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(242);
				match(LBRACE);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(243);
						intLiteralList();
					}
				}

				setState(246);
				match(RBRACE);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(247);
				intLiteral();
				setState(248);
				match(RANGE);
				setState(249);
				intLiteral();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(251);
				match(LBRACE);
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == FLOAT_LITERAL) {
					{
						setState(252);
						floatLiteralList();
					}
				}

				setState(255);
				match(RBRACE);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(256);
				floatLiteral();
				setState(257);
				match(RANGE);
				setState(258);
				floatLiteral();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public BasicExprListContext basicExprList() {
			return getRuleContext(BasicExprListContext.class, 0);
		}

		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterArrayLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitArrayLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitArrayLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(262);
				match(LBRACK);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2953887744L) != 0)) {
					{
						setState(263);
						basicExprList();
					}
				}

				setState(266);
				match(RBRACK);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParArrayLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public BasicLiteralExprListContext basicLiteralExprList() {
			return getRuleContext(BasicLiteralExprListContext.class, 0);
		}

		public ParArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parArrayLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterParArrayLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitParArrayLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitParArrayLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ParArrayLiteralContext parArrayLiteral() throws RecognitionException {
		ParArrayLiteralContext _localctx = new ParArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parArrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(268);
				match(LBRACK);
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 806404096L) != 0)) {
					{
						setState(269);
						basicLiteralExprList();
					}
				}

				setState(272);
				match(RBRACK);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntLiteralListContext extends ParserRuleContext {
		public List<IntLiteralContext> intLiteral() {
			return getRuleContexts(IntLiteralContext.class);
		}

		public IntLiteralContext intLiteral(int i) {
			return getRuleContext(IntLiteralContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public IntLiteralListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_intLiteralList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterIntLiteralList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitIntLiteralList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitIntLiteralList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IntLiteralListContext intLiteralList() throws RecognitionException {
		IntLiteralListContext _localctx = new IntLiteralListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_intLiteralList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(274);
				intLiteral();
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(275);
							match(COMMA);
							setState(276);
							intLiteral();
						}
					}
					setState(281);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralListContext extends ParserRuleContext {
		public List<FloatLiteralContext> floatLiteral() {
			return getRuleContexts(FloatLiteralContext.class);
		}

		public FloatLiteralContext floatLiteral(int i) {
			return getRuleContext(FloatLiteralContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public FloatLiteralListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_floatLiteralList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterFloatLiteralList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitFloatLiteralList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitFloatLiteralList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FloatLiteralListContext floatLiteralList() throws RecognitionException {
		FloatLiteralListContext _localctx = new FloatLiteralListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_floatLiteralList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(282);
				floatLiteral();
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(283);
							match(COMMA);
							setState(284);
							floatLiteral();
						}
					}
					setState(289);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicExprListContext extends ParserRuleContext {
		public List<BasicExprContext> basicExpr() {
			return getRuleContexts(BasicExprContext.class);
		}

		public BasicExprContext basicExpr(int i) {
			return getRuleContext(BasicExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public BasicExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicExprList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicExprList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicExprList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicExprList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicExprListContext basicExprList() throws RecognitionException {
		BasicExprListContext _localctx = new BasicExprListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_basicExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(290);
				basicExpr();
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(291);
							match(COMMA);
							setState(292);
							basicExpr();
						}
					}
					setState(297);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicLiteralExprListContext extends ParserRuleContext {
		public List<BasicLiteralExprContext> basicLiteralExpr() {
			return getRuleContexts(BasicLiteralExprContext.class);
		}

		public BasicLiteralExprContext basicLiteralExpr(int i) {
			return getRuleContext(BasicLiteralExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public BasicLiteralExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicLiteralExprList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicLiteralExprList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicLiteralExprList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicLiteralExprList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicLiteralExprListContext basicLiteralExprList() throws RecognitionException {
		BasicLiteralExprListContext _localctx = new BasicLiteralExprListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_basicLiteralExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(298);
				basicLiteralExpr();
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(299);
							match(COMMA);
							setState(300);
							basicLiteralExpr();
						}
					}
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_exprList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterExprList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitExprList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitExprList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(306);
				expr();
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(307);
							match(COMMA);
							setState(308);
							expr();
						}
					}
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnExprListContext extends ParserRuleContext {
		public List<AnnExprContext> annExpr() {
			return getRuleContexts(AnnExprContext.class);
		}

		public AnnExprContext annExpr(int i) {
			return getRuleContext(AnnExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public AnnExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annExprList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterAnnExprList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitAnnExprList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitAnnExprList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnExprListContext annExprList() throws RecognitionException {
		AnnExprListContext _localctx = new AnnExprListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_annExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(314);
				annExpr();
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(315);
							match(COMMA);
							setState(316);
							annExpr();
						}
					}
					setState(321);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParDeclItemContext extends ParserRuleContext {
		public TerminalNode ARRAY() {
			return getToken(FlatZincParser.ARRAY, 0);
		}

		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public TerminalNode OF() {
			return getToken(FlatZincParser.OF, 0);
		}

		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(FlatZincParser.COLON, 0);
		}

		public VarParIdentifierContext varParIdentifier() {
			return getRuleContext(VarParIdentifierContext.class, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(FlatZincParser.ASSIGN, 0);
		}

		public ParArrayLiteralContext parArrayLiteral() {
			return getRuleContext(ParArrayLiteralContext.class, 0);
		}

		public TerminalNode SEMI() {
			return getToken(FlatZincParser.SEMI, 0);
		}

		public ParDeclItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parDeclItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterParDeclItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitParDeclItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitParDeclItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ParDeclItemContext parDeclItem() throws RecognitionException {
		ParDeclItemContext _localctx = new ParDeclItemContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_parDeclItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(322);
				match(ARRAY);
				setState(323);
				match(LBRACK);
				setState(324);
				indexSet();
				setState(325);
				match(RBRACK);
				setState(326);
				match(OF);
				setState(327);
				basicParType();
				setState(328);
				match(COLON);
				setState(329);
				varParIdentifier();
				setState(330);
				match(ASSIGN);
				setState(331);
				parArrayLiteral();
				setState(332);
				match(SEMI);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclItemContext extends ParserRuleContext {
		public BasicVarTypeContext basicVarType() {
			return getRuleContext(BasicVarTypeContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(FlatZincParser.COLON, 0);
		}

		public VarParIdentifierContext varParIdentifier() {
			return getRuleContext(VarParIdentifierContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode SEMI() {
			return getToken(FlatZincParser.SEMI, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(FlatZincParser.ASSIGN, 0);
		}

		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class, 0);
		}

		public ArrayVarTypeContext arrayVarType() {
			return getRuleContext(ArrayVarTypeContext.class, 0);
		}

		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class, 0);
		}

		public VarDeclItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_varDeclItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterVarDeclItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitVarDeclItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitVarDeclItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final VarDeclItemContext varDeclItem() throws RecognitionException {
		VarDeclItemContext _localctx = new VarDeclItemContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_varDeclItem);
		int _la;
		try {
			setState(352);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1); {
				setState(334);
				basicVarType();
				setState(335);
				match(COLON);
				setState(336);
				varParIdentifier();
				setState(337);
				annotations();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ASSIGN) {
					{
						setState(338);
						match(ASSIGN);
						setState(339);
						basicExpr();
					}
				}

				setState(342);
				match(SEMI);
			}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 2); {
				setState(344);
				arrayVarType();
				setState(345);
				match(COLON);
				setState(346);
				varParIdentifier();
				setState(347);
				annotations();
				setState(348);
				match(ASSIGN);
				setState(349);
				arrayLiteral();
				setState(350);
				match(SEMI);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstraintItemContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() {
			return getToken(FlatZincParser.CONSTRAINT, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FlatZincParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FlatZincParser.RPAREN, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode SEMI() {
			return getToken(FlatZincParser.SEMI, 0);
		}

		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class, 0);
		}

		public ConstraintItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_constraintItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterConstraintItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitConstraintItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitConstraintItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ConstraintItemContext constraintItem() throws RecognitionException {
		ConstraintItemContext _localctx = new ConstraintItemContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constraintItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(354);
				match(CONSTRAINT);
				setState(355);
				identifier();
				setState(356);
				match(LPAREN);
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2954149888L) != 0)) {
					{
						setState(357);
						exprList();
					}
				}

				setState(360);
				match(RPAREN);
				setState(361);
				annotations();
				setState(362);
				match(SEMI);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SolveItemContext extends ParserRuleContext {
		public TerminalNode SOLVE() {
			return getToken(FlatZincParser.SOLVE, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode SATISFY() {
			return getToken(FlatZincParser.SATISFY, 0);
		}

		public TerminalNode SEMI() {
			return getToken(FlatZincParser.SEMI, 0);
		}

		public TerminalNode MINIMIZE() {
			return getToken(FlatZincParser.MINIMIZE, 0);
		}

		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class, 0);
		}

		public TerminalNode MAXIMIZE() {
			return getToken(FlatZincParser.MAXIMIZE, 0);
		}

		public SolveItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_solveItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterSolveItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitSolveItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitSolveItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final SolveItemContext solveItem() throws RecognitionException {
		SolveItemContext _localctx = new SolveItemContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_solveItem);
		try {
			setState(381);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 30, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(364);
				match(SOLVE);
				setState(365);
				annotations();
				setState(366);
				match(SATISFY);
				setState(367);
				match(SEMI);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(369);
				match(SOLVE);
				setState(370);
				annotations();
				setState(371);
				match(MINIMIZE);
				setState(372);
				basicExpr();
				setState(373);
				match(SEMI);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(375);
				match(SOLVE);
				setState(376);
				annotations();
				setState(377);
				match(MAXIMIZE);
				setState(378);
				basicExpr();
				setState(379);
				match(SEMI);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationsContext extends ParserRuleContext {
		public List<TerminalNode> DCOLON() {
			return getTokens(FlatZincParser.DCOLON);
		}

		public TerminalNode DCOLON(int i) {
			return getToken(FlatZincParser.DCOLON, i);
		}

		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
		}

		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitAnnotations(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitAnnotations(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == DCOLON) {
					{
						{
							setState(383);
							match(DCOLON);
							setState(384);
							annotation();
						}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FlatZincParser.LPAREN, 0);
		}

		public AnnExprListContext annExprList() {
			return getRuleContext(AnnExprListContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FlatZincParser.RPAREN, 0);
		}

		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annotation;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterAnnotation(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitAnnotation(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitAnnotation(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_annotation);
		try {
			setState(396);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(390);
				identifier();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(391);
				identifier();
				setState(392);
				match(LPAREN);
				setState(393);
				annExprList();
				setState(394);
				match(RPAREN);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnExprContext extends ParserRuleContext {
		public BasicAnnExprContext basicAnnExpr() {
			return getRuleContext(BasicAnnExprContext.class, 0);
		}

		public TerminalNode LBRACK() {
			return getToken(FlatZincParser.LBRACK, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(FlatZincParser.RBRACK, 0);
		}

		public BasicAnnExprListContext basicAnnExprList() {
			return getRuleContext(BasicAnnExprListContext.class, 0);
		}

		public AnnExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterAnnExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitAnnExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitAnnExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnExprContext annExpr() throws RecognitionException {
		AnnExprContext _localctx = new AnnExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_annExpr);
		int _la;
		try {
			setState(404);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case LBRACE:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1); {
				setState(398);
				basicAnnExpr();
			}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 2); {
				setState(399);
				match(LBRACK);
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4027629568L) != 0)) {
					{
						setState(400);
						basicAnnExprList();
					}
				}

				setState(403);
				match(RBRACK);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicAnnExprContext extends ParserRuleContext {
		public BasicLiteralExprContext basicLiteralExpr() {
			return getRuleContext(BasicLiteralExprContext.class, 0);
		}

		public VarParIdentifierContext varParIdentifier() {
			return getRuleContext(VarParIdentifierContext.class, 0);
		}

		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class, 0);
		}

		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class, 0);
		}

		public BasicAnnExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicAnnExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicAnnExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicAnnExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicAnnExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicAnnExprContext basicAnnExpr() throws RecognitionException {
		BasicAnnExprContext _localctx = new BasicAnnExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_basicAnnExpr);
		try {
			setState(410);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 35, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(406);
				basicLiteralExpr();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(407);
				varParIdentifier();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(408);
				stringLiteral();
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(409);
				annotation();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicAnnExprListContext extends ParserRuleContext {
		public List<BasicAnnExprContext> basicAnnExpr() {
			return getRuleContexts(BasicAnnExprContext.class);
		}

		public BasicAnnExprContext basicAnnExpr(int i) {
			return getRuleContext(BasicAnnExprContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FlatZincParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FlatZincParser.COMMA, i);
		}

		public BasicAnnExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_basicAnnExprList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterBasicAnnExprList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitBasicAnnExprList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitBasicAnnExprList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BasicAnnExprListContext basicAnnExprList() throws RecognitionException {
		BasicAnnExprListContext _localctx = new BasicAnnExprListContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_basicAnnExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(412);
				basicAnnExpr();
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(413);
							match(COMMA);
							setState(414);
							basicAnnExpr();
						}
					}
					setState(419);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterIdentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitIdentifier(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitIdentifier(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(420);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() {
			return getToken(FlatZincParser.STRING_LITERAL, 0);
		}

		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stringLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).enterStringLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FlatZincListener)
				((FlatZincListener) listener).exitStringLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FlatZincVisitor)
				return ((FlatZincVisitor<? extends T>) visitor).visitStringLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(422);
				match(STRING_LITERAL);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN = "\u0004\u0001#\u01a9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
		+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015" +
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018" +
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b" +
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e" +
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002" +
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000\u0005\u0000N\b\u0000" +
		"\n\u0000\f\u0000Q\t\u0000\u0001\u0000\u0005\u0000T\b\u0000\n\u0000\f\u0000" +
		"W\t\u0000\u0001\u0000\u0005\u0000Z\b\u0000\n\u0000\f\u0000]\t\u0000\u0001" +
		"\u0000\u0005\u0000`\b\u0000\n\u0000\f\u0000c\t\u0000\u0001\u0000\u0001" +
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003" +
		"\u0001l\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001" +
		"\u0002\u0001\u0002\u0005\u0002t\b\u0002\n\u0002\f\u0002w\t\u0002\u0001" +
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001" +
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0083\b\u0004\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a2" +
		"\b\u0005\u0001\u0005\u0003\u0005\u00a5\b\u0005\u0001\u0006\u0001\u0006" +
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007" +
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
		"\b\u0001\b\u0003\b\u00ca\b\b\u0001\b\u0003\b\u00cd\b\b\u0001\t\u0001\t" +
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00d7\b\t\u0001" +
		"\n\u0001\n\u0003\n\u00db\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001" +
		"\u000b\u0003\u000b\u00e1\b\u000b\u0001\f\u0001\f\u0003\f\u00e5\b\f\u0001" +
		"\r\u0001\r\u0003\r\u00e9\b\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001" +
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001" +
		"\u0012\u0003\u0012\u00f5\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001" +
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00fe\b\u0012\u0001" +
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0105" +
		"\b\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0109\b\u0013\u0001\u0013" +
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u010f\b\u0014\u0001\u0014" +
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0116\b\u0015" +
		"\n\u0015\f\u0015\u0119\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005" +
		"\u0016\u011e\b\u0016\n\u0016\f\u0016\u0121\t\u0016\u0001\u0017\u0001\u0017" +
		"\u0001\u0017\u0005\u0017\u0126\b\u0017\n\u0017\f\u0017\u0129\t\u0017\u0001" +
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u012e\b\u0018\n\u0018\f\u0018" +
		"\u0131\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0136\b" +
		"\u0019\n\u0019\f\u0019\u0139\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a" +
		"\u0005\u001a\u013e\b\u001a\n\u001a\f\u001a\u0141\t\u001a\u0001\u001b\u0001" +
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001" +
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0155" +
		"\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0161" +
		"\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u0167" +
		"\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001" +
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001" +
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001" +
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u017e\b\u001e\u0001" +
		"\u001f\u0001\u001f\u0005\u001f\u0182\b\u001f\n\u001f\f\u001f\u0185\t\u001f" +
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u018d\b \u0001!\u0001" +
		"!\u0001!\u0003!\u0192\b!\u0001!\u0003!\u0195\b!\u0001\"\u0001\"\u0001" +
		"\"\u0001\"\u0003\"\u019b\b\"\u0001#\u0001#\u0001#\u0005#\u01a0\b#\n#\f" +
		"#\u01a3\t#\u0001$\u0001$\u0001%\u0001%\u0001%\u0000\u0000&\u0000\u0002" +
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e" +
		" \"$&(*,.02468:<>@BDFHJ\u0000\u0001\u0001\u0000\u000e\u000f\u01b9\u0000" +
		"O\u0001\u0000\u0000\u0000\u0002g\u0001\u0000\u0000\u0000\u0004p\u0001" +
		"\u0000\u0000\u0000\u0006x\u0001\u0000\u0000\u0000\b\u0082\u0001\u0000" +
		"\u0000\u0000\n\u00a4\u0001\u0000\u0000\u0000\f\u00a6\u0001\u0000\u0000" +
		"\u0000\u000e\u00ad\u0001\u0000\u0000\u0000\u0010\u00cc\u0001\u0000\u0000" +
		"\u0000\u0012\u00d6\u0001\u0000\u0000\u0000\u0014\u00da\u0001\u0000\u0000" +
		"\u0000\u0016\u00e0\u0001\u0000\u0000\u0000\u0018\u00e4\u0001\u0000\u0000" +
		"\u0000\u001a\u00e8\u0001\u0000\u0000\u0000\u001c\u00ea\u0001\u0000\u0000" +
		"\u0000\u001e\u00ec\u0001\u0000\u0000\u0000 \u00ee\u0001\u0000\u0000\u0000" +
		"\"\u00f0\u0001\u0000\u0000\u0000$\u0104\u0001\u0000\u0000\u0000&\u0106" +
		"\u0001\u0000\u0000\u0000(\u010c\u0001\u0000\u0000\u0000*\u0112\u0001\u0000" +
		"\u0000\u0000,\u011a\u0001\u0000\u0000\u0000.\u0122\u0001\u0000\u0000\u0000" +
		"0\u012a\u0001\u0000\u0000\u00002\u0132\u0001\u0000\u0000\u00004\u013a" +
		"\u0001\u0000\u0000\u00006\u0142\u0001\u0000\u0000\u00008\u0160\u0001\u0000" +
		"\u0000\u0000:\u0162\u0001\u0000\u0000\u0000<\u017d\u0001\u0000\u0000\u0000" +
		">\u0183\u0001\u0000\u0000\u0000@\u018c\u0001\u0000\u0000\u0000B\u0194" +
		"\u0001\u0000\u0000\u0000D\u019a\u0001\u0000\u0000\u0000F\u019c\u0001\u0000" +
		"\u0000\u0000H\u01a4\u0001\u0000\u0000\u0000J\u01a6\u0001\u0000\u0000\u0000" +
		"LN\u0003\u0002\u0001\u0000ML\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000" +
		"\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PU\u0001\u0000" +
		"\u0000\u0000QO\u0001\u0000\u0000\u0000RT\u00036\u001b\u0000SR\u0001\u0000" +
		"\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001" +
		"\u0000\u0000\u0000V[\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000" +
		"XZ\u00038\u001c\u0000YX\u0001\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000" +
		"[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\a\u0001\u0000\u0000" +
		"\u0000][\u0001\u0000\u0000\u0000^`\u0003:\u001d\u0000_^\u0001\u0000\u0000" +
		"\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000" +
		"\u0000\u0000bd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000de\u0003" +
		"<\u001e\u0000ef\u0005\u0000\u0000\u0001f\u0001\u0001\u0000\u0000\u0000" +
		"gh\u0005\u0001\u0000\u0000hi\u0003H$\u0000ik\u0005\u0010\u0000\u0000j" +
		"l\u0003\u0004\u0002\u0000kj\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000" +
		"\u0000lm\u0001\u0000\u0000\u0000mn\u0005\u0011\u0000\u0000no\u0005\u0017" +
		"\u0000\u0000o\u0003\u0001\u0000\u0000\u0000pu\u0003\u0006\u0003\u0000" +
		"qr\u0005\u0018\u0000\u0000rt\u0003\u0006\u0003\u0000sq\u0001\u0000\u0000" +
		"\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000" +
		"\u0000\u0000v\u0005\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000" +
		"xy\u0003\u0012\t\u0000yz\u0005\u0016\u0000\u0000z{\u0003H$\u0000{\u0007" +
		"\u0001\u0000\u0000\u0000|\u0083\u0005\u0005\u0000\u0000}\u0083\u0005\u0006" +
		"\u0000\u0000~\u0083\u0005\u0007\u0000\u0000\u007f\u0080\u0005\b\u0000" +
		"\u0000\u0080\u0081\u0005\u0003\u0000\u0000\u0081\u0083\u0005\u0006\u0000" +
		"\u0000\u0082|\u0001\u0000\u0000\u0000\u0082}\u0001\u0000\u0000\u0000\u0082" +
		"~\u0001\u0000\u0000\u0000\u0082\u007f\u0001\u0000\u0000\u0000\u0083\t" +
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0004\u0000\u0000\u0085\u00a5" +
		"\u0003\b\u0004\u0000\u0086\u0087\u0005\u0004\u0000\u0000\u0087\u0088\u0003" +
		" \u0010\u0000\u0088\u0089\u0005\u001a\u0000\u0000\u0089\u008a\u0003 \u0010" +
		"\u0000\u008a\u00a5\u0001\u0000\u0000\u0000\u008b\u008c\u0005\u0004\u0000" +
		"\u0000\u008c\u008d\u0005\u0014\u0000\u0000\u008d\u008e\u0003*\u0015\u0000" +
		"\u008e\u008f\u0005\u0015\u0000\u0000\u008f\u00a5\u0001\u0000\u0000\u0000" +
		"\u0090\u0091\u0005\u0004\u0000\u0000\u0091\u0092\u0003\"\u0011\u0000\u0092" +
		"\u0093\u0005\u001a\u0000\u0000\u0093\u0094\u0003\"\u0011\u0000\u0094\u00a5" +
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0004\u0000\u0000\u0096\u0097" +
		"\u0005\b\u0000\u0000\u0097\u0098\u0005\u0003\u0000\u0000\u0098\u0099\u0003" +
		" \u0010\u0000\u0099\u009a\u0005\u001a\u0000\u0000\u009a\u009b\u0003 \u0010" +
		"\u0000\u009b\u00a5\u0001\u0000\u0000\u0000\u009c\u009d\u0005\u0004\u0000" +
		"\u0000\u009d\u009e\u0005\b\u0000\u0000\u009e\u009f\u0005\u0003\u0000\u0000" +
		"\u009f\u00a1\u0005\u0014\u0000\u0000\u00a0\u00a2\u0003*\u0015\u0000\u00a1" +
		"\u00a0\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2" +
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0005\u0015\u0000\u0000\u00a4" +
		"\u0084\u0001\u0000\u0000\u0000\u00a4\u0086\u0001\u0000\u0000\u0000\u00a4" +
		"\u008b\u0001\u0000\u0000\u0000\u00a4\u0090\u0001\u0000\u0000\u0000\u00a4" +
		"\u0095\u0001\u0000\u0000\u0000\u00a4\u009c\u0001\u0000\u0000\u0000\u00a5" +
		"\u000b\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\u0002\u0000\u0000\u00a7" +
		"\u00a8\u0005\u0012\u0000\u0000\u00a8\u00a9\u0003\u000e\u0007\u0000\u00a9" +
		"\u00aa\u0005\u0013\u0000\u0000\u00aa\u00ab\u0005\u0003\u0000\u0000\u00ab" +
		"\u00ac\u0003\n\u0005\u0000\u00ac\r\u0001\u0000\u0000\u0000\u00ad\u00ae" +
		"\u0005\u001c\u0000\u0000\u00ae\u00af\u0005\u001a\u0000\u0000\u00af\u00b0" +
		"\u0003 \u0010\u0000\u00b0\u000f\u0001\u0000\u0000\u0000\u00b1\u00cd\u0003" +
		"\b\u0004\u0000\u00b2\u00cd\u0003\n\u0005\u0000\u00b3\u00b4\u0003 \u0010" +
		"\u0000\u00b4\u00b5\u0005\u001a\u0000\u0000\u00b5\u00b6\u0003 \u0010\u0000" +
		"\u00b6\u00cd\u0001\u0000\u0000\u0000\u00b7\u00b8\u0003\"\u0011\u0000\u00b8" +
		"\u00b9\u0005\u001a\u0000\u0000\u00b9\u00ba\u0003\"\u0011\u0000\u00ba\u00cd" +
		"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0014\u0000\u0000\u00bc\u00bd" +
		"\u0003*\u0015\u0000\u00bd\u00be\u0005\u0015\u0000\u0000\u00be\u00cd\u0001" +
		"\u0000\u0000\u0000\u00bf\u00c0\u0005\b\u0000\u0000\u00c0\u00c1\u0005\u0003" +
		"\u0000\u0000\u00c1\u00c2\u0003 \u0010\u0000\u00c2\u00c3\u0005\u001a\u0000" +
		"\u0000\u00c3\u00c4\u0003 \u0010\u0000\u00c4\u00cd\u0001\u0000\u0000\u0000" +
		"\u00c5\u00c6\u0005\b\u0000\u0000\u00c6\u00c7\u0005\u0003\u0000\u0000\u00c7" +
		"\u00c9\u0005\u0014\u0000\u0000\u00c8\u00ca\u0003*\u0015\u0000\u00c9\u00c8" +
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cb" +
		"\u0001\u0000\u0000\u0000\u00cb\u00cd\u0005\u0015\u0000\u0000\u00cc\u00b1" +
		"\u0001\u0000\u0000\u0000\u00cc\u00b2\u0001\u0000\u0000\u0000\u00cc\u00b3" +
		"\u0001\u0000\u0000\u0000\u00cc\u00b7\u0001\u0000\u0000\u0000\u00cc\u00bb" +
		"\u0001\u0000\u0000\u0000\u00cc\u00bf\u0001\u0000\u0000\u0000\u00cc\u00c5" +
		"\u0001\u0000\u0000\u0000\u00cd\u0011\u0001\u0000\u0000\u0000\u00ce\u00d7" +
		"\u0003\u0010\b\u0000\u00cf\u00d0\u0005\u0002\u0000\u0000\u00d0\u00d1\u0005" +
		"\u0012\u0000\u0000\u00d1\u00d2\u0003\u0014\n\u0000\u00d2\u00d3\u0005\u0013" +
		"\u0000\u0000\u00d3\u00d4\u0005\u0003\u0000\u0000\u00d4\u00d5\u0003\u0010" +
		"\b\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6\u00ce\u0001\u0000\u0000" +
		"\u0000\u00d6\u00cf\u0001\u0000\u0000\u0000\u00d7\u0013\u0001\u0000\u0000" +
		"\u0000\u00d8\u00db\u0003\u000e\u0007\u0000\u00d9\u00db\u0005\u0006\u0000" +
		"\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00da\u00d9\u0001\u0000\u0000" +
		"\u0000\u00db\u0015\u0001\u0000\u0000\u0000\u00dc\u00e1\u0003\u001e\u000f" +
		"\u0000\u00dd\u00e1\u0003 \u0010\u0000\u00de\u00e1\u0003\"\u0011\u0000" +
		"\u00df\u00e1\u0003$\u0012\u0000\u00e0\u00dc\u0001\u0000\u0000\u0000\u00e0" +
		"\u00dd\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0" +
		"\u00df\u0001\u0000\u0000\u0000\u00e1\u0017\u0001\u0000\u0000\u0000\u00e2" +
		"\u00e5\u0003\u0016\u000b\u0000\u00e3\u00e5\u0003\u001c\u000e\u0000\u00e4" +
		"\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000\u0000\u00e5" +
		"\u0019\u0001\u0000\u0000\u0000\u00e6\u00e9\u0003\u0018\f\u0000\u00e7\u00e9" +
		"\u0003&\u0013\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e7\u0001" +
		"\u0000\u0000\u0000\u00e9\u001b\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005" +
		"\u001f\u0000\u0000\u00eb\u001d\u0001\u0000\u0000\u0000\u00ec\u00ed\u0007" +
		"\u0000\u0000\u0000\u00ed\u001f\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005" +
		"\u001c\u0000\u0000\u00ef!\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u001d" +
		"\u0000\u0000\u00f1#\u0001\u0000\u0000\u0000\u00f2\u00f4\u0005\u0014\u0000" +
		"\u0000\u00f3\u00f5\u0003*\u0015\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000" +
		"\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000" +
		"\u00f6\u0105\u0005\u0015\u0000\u0000\u00f7\u00f8\u0003 \u0010\u0000\u00f8" +
		"\u00f9\u0005\u001a\u0000\u0000\u00f9\u00fa\u0003 \u0010\u0000\u00fa\u0105" +
		"\u0001\u0000\u0000\u0000\u00fb\u00fd\u0005\u0014\u0000\u0000\u00fc\u00fe" +
		"\u0003,\u0016\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001" +
		"\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff\u0105\u0005" +
		"\u0015\u0000\u0000\u0100\u0101\u0003\"\u0011\u0000\u0101\u0102\u0005\u001a" +
		"\u0000\u0000\u0102\u0103\u0003\"\u0011\u0000\u0103\u0105\u0001\u0000\u0000" +
		"\u0000\u0104\u00f2\u0001\u0000\u0000\u0000\u0104\u00f7\u0001\u0000\u0000" +
		"\u0000\u0104\u00fb\u0001\u0000\u0000\u0000\u0104\u0100\u0001\u0000\u0000" +
		"\u0000\u0105%\u0001\u0000\u0000\u0000\u0106\u0108\u0005\u0012\u0000\u0000" +
		"\u0107\u0109\u0003.\u0017\u0000\u0108\u0107\u0001\u0000\u0000\u0000\u0108" +
		"\u0109\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000\u010a" +
		"\u010b\u0005\u0013\u0000\u0000\u010b\'\u0001\u0000\u0000\u0000\u010c\u010e" +
		"\u0005\u0012\u0000\u0000\u010d\u010f\u00030\u0018\u0000\u010e\u010d\u0001" +
		"\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110\u0001" +
		"\u0000\u0000\u0000\u0110\u0111\u0005\u0013\u0000\u0000\u0111)\u0001\u0000" +
		"\u0000\u0000\u0112\u0117\u0003 \u0010\u0000\u0113\u0114\u0005\u0018\u0000" +
		"\u0000\u0114\u0116\u0003 \u0010\u0000\u0115\u0113\u0001\u0000\u0000\u0000" +
		"\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000" +
		"\u0117\u0118\u0001\u0000\u0000\u0000\u0118+\u0001\u0000\u0000\u0000\u0119" +
		"\u0117\u0001\u0000\u0000\u0000\u011a\u011f\u0003\"\u0011\u0000\u011b\u011c" +
		"\u0005\u0018\u0000\u0000\u011c\u011e\u0003\"\u0011\u0000\u011d\u011b\u0001" +
		"\u0000\u0000\u0000\u011e\u0121\u0001\u0000\u0000\u0000\u011f\u011d\u0001" +
		"\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120-\u0001\u0000" +
		"\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0127\u0003\u0018" +
		"\f\u0000\u0123\u0124\u0005\u0018\u0000\u0000\u0124\u0126\u0003\u0018\f" +
		"\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0126\u0129\u0001\u0000\u0000" +
		"\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000" +
		"\u0000\u0128/\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000" +
		"\u012a\u012f\u0003\u0016\u000b\u0000\u012b\u012c\u0005\u0018\u0000\u0000" +
		"\u012c\u012e\u0003\u0016\u000b\u0000\u012d\u012b\u0001\u0000\u0000\u0000" +
		"\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000" +
		"\u012f\u0130\u0001\u0000\u0000\u0000\u01301\u0001\u0000\u0000\u0000\u0131" +
		"\u012f\u0001\u0000\u0000\u0000\u0132\u0137\u0003\u001a\r\u0000\u0133\u0134" +
		"\u0005\u0018\u0000\u0000\u0134\u0136\u0003\u001a\r\u0000\u0135\u0133\u0001" +
		"\u0000\u0000\u0000\u0136\u0139\u0001\u0000\u0000\u0000\u0137\u0135\u0001" +
		"\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u01383\u0001\u0000" +
		"\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u013a\u013f\u0003B!\u0000" +
		"\u013b\u013c\u0005\u0018\u0000\u0000\u013c\u013e\u0003B!\u0000\u013d\u013b" +
		"\u0001\u0000\u0000\u0000\u013e\u0141\u0001\u0000\u0000\u0000\u013f\u013d" +
		"\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u01405\u0001" +
		"\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0142\u0143\u0005" +
		"\u0002\u0000\u0000\u0143\u0144\u0005\u0012\u0000\u0000\u0144\u0145\u0003" +
		"\u000e\u0007\u0000\u0145\u0146\u0005\u0013\u0000\u0000\u0146\u0147\u0005" +
		"\u0003\u0000\u0000\u0147\u0148\u0003\b\u0004\u0000\u0148\u0149\u0005\u0016" +
		"\u0000\u0000\u0149\u014a\u0003\u001c\u000e\u0000\u014a\u014b\u0005\u0019" +
		"\u0000\u0000\u014b\u014c\u0003(\u0014\u0000\u014c\u014d\u0005\u0017\u0000" +
		"\u0000\u014d7\u0001\u0000\u0000\u0000\u014e\u014f\u0003\n\u0005\u0000" +
		"\u014f\u0150\u0005\u0016\u0000\u0000\u0150\u0151\u0003\u001c\u000e\u0000" +
		"\u0151\u0154\u0003>\u001f\u0000\u0152\u0153\u0005\u0019\u0000\u0000\u0153" +
		"\u0155\u0003\u0018\f\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0154\u0155" +
		"\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0157" +
		"\u0005\u0017\u0000\u0000\u0157\u0161\u0001\u0000\u0000\u0000\u0158\u0159" +
		"\u0003\f\u0006\u0000\u0159\u015a\u0005\u0016\u0000\u0000\u015a\u015b\u0003" +
		"\u001c\u000e\u0000\u015b\u015c\u0003>\u001f\u0000\u015c\u015d\u0005\u0019" +
		"\u0000\u0000\u015d\u015e\u0003&\u0013\u0000\u015e\u015f\u0005\u0017\u0000" +
		"\u0000\u015f\u0161\u0001\u0000\u0000\u0000\u0160\u014e\u0001\u0000\u0000" +
		"\u0000\u0160\u0158\u0001\u0000\u0000\u0000\u01619\u0001\u0000\u0000\u0000" +
		"\u0162\u0163\u0005\t\u0000\u0000\u0163\u0164\u0003H$\u0000\u0164\u0166" +
		"\u0005\u0010\u0000\u0000\u0165\u0167\u00032\u0019\u0000\u0166\u0165\u0001" +
		"\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0168\u0001" +
		"\u0000\u0000\u0000\u0168\u0169\u0005\u0011\u0000\u0000\u0169\u016a\u0003" +
		">\u001f\u0000\u016a\u016b\u0005\u0017\u0000\u0000\u016b;\u0001\u0000\u0000" +
		"\u0000\u016c\u016d\u0005\n\u0000\u0000\u016d\u016e\u0003>\u001f\u0000" +
		"\u016e\u016f\u0005\u000b\u0000\u0000\u016f\u0170\u0005\u0017\u0000\u0000" +
		"\u0170\u017e\u0001\u0000\u0000\u0000\u0171\u0172\u0005\n\u0000\u0000\u0172" +
		"\u0173\u0003>\u001f\u0000\u0173\u0174\u0005\f\u0000\u0000\u0174\u0175" +
		"\u0003\u0018\f\u0000\u0175\u0176\u0005\u0017\u0000\u0000\u0176\u017e\u0001" +
		"\u0000\u0000\u0000\u0177\u0178\u0005\n\u0000\u0000\u0178\u0179\u0003>" +
		"\u001f\u0000\u0179\u017a\u0005\r\u0000\u0000\u017a\u017b\u0003\u0018\f" +
		"\u0000\u017b\u017c\u0005\u0017\u0000\u0000\u017c\u017e\u0001\u0000\u0000" +
		"\u0000\u017d\u016c\u0001\u0000\u0000\u0000\u017d\u0171\u0001\u0000\u0000" +
		"\u0000\u017d\u0177\u0001\u0000\u0000\u0000\u017e=\u0001\u0000\u0000\u0000" +
		"\u017f\u0180\u0005\u001b\u0000\u0000\u0180\u0182\u0003@ \u0000\u0181\u017f" +
		"\u0001\u0000\u0000\u0000\u0182\u0185\u0001\u0000\u0000\u0000\u0183\u0181" +
		"\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184?\u0001" +
		"\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0186\u018d\u0003" +
		"H$\u0000\u0187\u0188\u0003H$\u0000\u0188\u0189\u0005\u0010\u0000\u0000" +
		"\u0189\u018a\u00034\u001a\u0000\u018a\u018b\u0005\u0011\u0000\u0000\u018b" +
		"\u018d\u0001\u0000\u0000\u0000\u018c\u0186\u0001\u0000\u0000\u0000\u018c" +
		"\u0187\u0001\u0000\u0000\u0000\u018dA\u0001\u0000\u0000\u0000\u018e\u0195" +
		"\u0003D\"\u0000\u018f\u0191\u0005\u0012\u0000\u0000\u0190\u0192\u0003" +
		"F#\u0000\u0191\u0190\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000" +
		"\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0195\u0005\u0013\u0000" +
		"\u0000\u0194\u018e\u0001\u0000\u0000\u0000\u0194\u018f\u0001\u0000\u0000" +
		"\u0000\u0195C\u0001\u0000\u0000\u0000\u0196\u019b\u0003\u0016\u000b\u0000" +
		"\u0197\u019b\u0003\u001c\u000e\u0000\u0198\u019b\u0003J%\u0000\u0199\u019b" +
		"\u0003@ \u0000\u019a\u0196\u0001\u0000\u0000\u0000\u019a\u0197\u0001\u0000" +
		"\u0000\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u0199\u0001\u0000" +
		"\u0000\u0000\u019bE\u0001\u0000\u0000\u0000\u019c\u01a1\u0003D\"\u0000" +
		"\u019d\u019e\u0005\u0018\u0000\u0000\u019e\u01a0\u0003D\"\u0000\u019f" +
		"\u019d\u0001\u0000\u0000\u0000\u01a0\u01a3\u0001\u0000\u0000\u0000\u01a1" +
		"\u019f\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2" +
		"G\u0001\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000\u0000\u01a4\u01a5" +
		"\u0005\u001f\u0000\u0000\u01a5I\u0001\u0000\u0000\u0000\u01a6\u01a7\u0005" +
		"\u001e\u0000\u0000\u01a7K\u0001\u0000\u0000\u0000%OU[aku\u0082\u00a1\u00a4" +
		"\u00c9\u00cc\u00d6\u00da\u00e0\u00e4\u00e8\u00f4\u00fd\u0104\u0108\u010e" +
		"\u0117\u011f\u0127\u012f\u0137\u013f\u0154\u0160\u0166\u017d\u0183\u018c" +
		"\u0191\u0194\u019a\u01a1";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
