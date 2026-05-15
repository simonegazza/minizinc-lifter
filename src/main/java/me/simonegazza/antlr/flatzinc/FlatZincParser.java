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
	public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
		T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
		T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
		T__24 = 25, T__25 = 26, T__26 = 27, INT_LITERAL = 28, FLOAT_LITERAL = 29, STRING_LITERAL = 30,
		IDENTIFIER = 31, VAR_PAR_IDENTIFIER = 32, WS = 33, LINE_COMMENT = 34, BLOCK_COMMENT = 35;
	public static final int RULE_model = 0, RULE_predicateItem = 1, RULE_predParamDeclList = 2, RULE_predParamDecl = 3,
		RULE_basicParType = 4, RULE_basicVarType = 5, RULE_arrayVarType = 6, RULE_predIndexSet = 7,
		RULE_indexSet = 8, RULE_basicPredParamType = 9, RULE_predParamType = 10,
		RULE_expr = 11, RULE_basicExpr = 12, RULE_setLiteral = 13, RULE_basicExprList = 14,
		RULE_basicLiteralExprList = 15, RULE_arrayLiteral = 16, RULE_intLiteralList = 17,
		RULE_floatLiteralList = 18, RULE_parDeclItem = 19, RULE_varDeclItem = 20,
		RULE_constraintItem = 21, RULE_solveItem = 22, RULE_annotation = 23, RULE_annExpr = 24,
		RULE_basicAnnExpr = 25;

	private static String[] makeRuleNames() {
		return new String[] {
				"model", "predicateItem", "predParamDeclList", "predParamDecl", "basicParType",
				"basicVarType", "arrayVarType", "predIndexSet", "indexSet", "basicPredParamType",
				"predParamType", "expr", "basicExpr", "setLiteral", "basicExprList",
				"basicLiteralExprList", "arrayLiteral", "intLiteralList", "floatLiteralList",
				"parDeclItem", "varDeclItem", "constraintItem", "solveItem", "annotation",
				"annExpr", "basicAnnExpr"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
				null, "'predicate'", "'('", "')'", "';'", "','", "':'", "'bool'", "'int'",
				"'float'", "'set'", "'of'", "'var'", "'..'", "'{'", "'}'", "'array'",
				"'['", "']'", "'true'", "'false'", "'='", "'::'", "'constraint'", "'solve'",
				"'satisfy'", "'minimize'", "'maximize'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, "INT_LITERAL", "FLOAT_LITERAL", "STRING_LITERAL",
				"IDENTIFIER", "VAR_PAR_IDENTIFIER", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__0) {
					{
						{
							setState(52);
							predicateItem();
						}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(58);
								parDeclItem();
							}
						}
					}
					setState(63);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__11 || _la == T__15) {
					{
						{
							setState(64);
							varDeclItem();
						}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__22) {
					{
						{
							setState(70);
							constraintItem();
						}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(76);
				solveItem();
				setState(77);
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
		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
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
				setState(79);
				match(T__0);
				setState(80);
				match(IDENTIFIER);
				setState(81);
				match(T__1);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 805394304L) != 0)) {
					{
						setState(82);
						predParamDeclList();
					}
				}

				setState(85);
				match(T__2);
				setState(86);
				match(T__3);
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
				setState(88);
				predParamDecl();
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__4) {
					{
						{
							setState(89);
							match(T__4);
							setState(90);
							predParamDecl();
						}
					}
					setState(95);
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

		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
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
				setState(96);
				predParamType();
				setState(97);
				match(T__5);
				setState(98);
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
	public static class BasicParTypeContext extends ParserRuleContext {
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
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1); {
				setState(100);
				match(T__6);
			}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2); {
				setState(101);
				match(T__7);
			}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3); {
				setState(102);
				match(T__8);
			}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4); {
				setState(103);
				match(T__9);
				setState(104);
				match(T__10);
				setState(105);
				match(T__7);
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
		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public List<TerminalNode> INT_LITERAL() {
			return getTokens(FlatZincParser.INT_LITERAL);
		}

		public TerminalNode INT_LITERAL(int i) {
			return getToken(FlatZincParser.INT_LITERAL, i);
		}

		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
		}

		public List<TerminalNode> FLOAT_LITERAL() {
			return getTokens(FlatZincParser.FLOAT_LITERAL);
		}

		public TerminalNode FLOAT_LITERAL(int i) {
			return getToken(FlatZincParser.FLOAT_LITERAL, i);
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
			setState(137);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(108);
				match(T__11);
				setState(109);
				basicParType();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(110);
				match(T__11);
				setState(111);
				match(INT_LITERAL);
				setState(112);
				match(T__12);
				setState(113);
				match(INT_LITERAL);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(114);
				match(T__11);
				setState(115);
				match(T__13);
				setState(116);
				intLiteralList();
				setState(117);
				match(T__14);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(119);
				match(T__11);
				setState(120);
				match(FLOAT_LITERAL);
				setState(121);
				match(T__12);
				setState(122);
				match(FLOAT_LITERAL);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(123);
				match(T__11);
				setState(124);
				match(T__9);
				setState(125);
				match(T__10);
				setState(126);
				match(INT_LITERAL);
				setState(127);
				match(T__12);
				setState(128);
				match(INT_LITERAL);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(129);
				match(T__11);
				setState(130);
				match(T__9);
				setState(131);
				match(T__10);
				setState(132);
				match(T__13);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(133);
						intLiteralList();
					}
				}

				setState(136);
				match(T__14);
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
		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
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
				setState(139);
				match(T__15);
				setState(140);
				match(T__16);
				setState(141);
				indexSet();
				setState(142);
				match(T__17);
				setState(143);
				match(T__10);
				setState(144);
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
	public static class PredIndexSetContext extends ParserRuleContext {
		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
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
		enterRule(_localctx, 14, RULE_predIndexSet);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(146);
				indexSet();
			}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2); {
				setState(147);
				match(T__7);
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
	public static class IndexSetContext extends ParserRuleContext {
		public List<TerminalNode> INT_LITERAL() {
			return getTokens(FlatZincParser.INT_LITERAL);
		}

		public TerminalNode INT_LITERAL(int i) {
			return getToken(FlatZincParser.INT_LITERAL, i);
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
		enterRule(_localctx, 16, RULE_indexSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(150);
				match(INT_LITERAL);
				setState(151);
				match(T__12);
				setState(152);
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
	public static class BasicPredParamTypeContext extends ParserRuleContext {
		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public BasicVarTypeContext basicVarType() {
			return getRuleContext(BasicVarTypeContext.class, 0);
		}

		public List<TerminalNode> INT_LITERAL() {
			return getTokens(FlatZincParser.INT_LITERAL);
		}

		public TerminalNode INT_LITERAL(int i) {
			return getToken(FlatZincParser.INT_LITERAL, i);
		}

		public List<TerminalNode> FLOAT_LITERAL() {
			return getTokens(FlatZincParser.FLOAT_LITERAL);
		}

		public TerminalNode FLOAT_LITERAL(int i) {
			return getToken(FlatZincParser.FLOAT_LITERAL, i);
		}

		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
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
		enterRule(_localctx, 18, RULE_basicPredParamType);
		int _la;
		try {
			setState(178);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(154);
				basicParType();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(155);
				basicVarType();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(156);
				match(INT_LITERAL);
				setState(157);
				match(T__12);
				setState(158);
				match(INT_LITERAL);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(159);
				match(FLOAT_LITERAL);
				setState(160);
				match(T__12);
				setState(161);
				match(FLOAT_LITERAL);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(162);
				match(T__13);
				setState(163);
				intLiteralList();
				setState(164);
				match(T__14);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(166);
				match(T__9);
				setState(167);
				match(T__10);
				setState(168);
				match(INT_LITERAL);
				setState(169);
				match(T__12);
				setState(170);
				match(INT_LITERAL);
			}
				break;
			case 7:
				enterOuterAlt(_localctx, 7); {
				setState(171);
				match(T__9);
				setState(172);
				match(T__10);
				setState(173);
				match(T__13);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(174);
						intLiteralList();
					}
				}

				setState(177);
				match(T__14);
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

		public PredIndexSetContext predIndexSet() {
			return getRuleContext(PredIndexSetContext.class, 0);
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
		enterRule(_localctx, 20, RULE_predParamType);
		try {
			setState(188);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__11:
			case T__13:
			case INT_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(180);
				basicPredParamType();
			}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2); {
				setState(181);
				match(T__15);
				setState(182);
				match(T__16);
				setState(183);
				predIndexSet();
				setState(184);
				match(T__17);
				setState(185);
				match(T__10);
				setState(186);
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
		enterRule(_localctx, 22, RULE_expr);
		try {
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__18:
			case T__19:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1); {
				setState(190);
				basicExpr();
			}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2); {
				setState(191);
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
	public static class BasicExprContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() {
			return getToken(FlatZincParser.INT_LITERAL, 0);
		}

		public TerminalNode FLOAT_LITERAL() {
			return getToken(FlatZincParser.FLOAT_LITERAL, 0);
		}

		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
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
			setState(200);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(194);
				match(T__18);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(195);
				match(T__19);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(196);
				match(INT_LITERAL);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(197);
				match(FLOAT_LITERAL);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(198);
				setLiteral();
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(199);
				match(IDENTIFIER);
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
	public static class SetLiteralContext extends ParserRuleContext {
		public IntLiteralListContext intLiteralList() {
			return getRuleContext(IntLiteralListContext.class, 0);
		}

		public List<TerminalNode> INT_LITERAL() {
			return getTokens(FlatZincParser.INT_LITERAL);
		}

		public TerminalNode INT_LITERAL(int i) {
			return getToken(FlatZincParser.INT_LITERAL, i);
		}

		public FloatLiteralListContext floatLiteralList() {
			return getRuleContext(FloatLiteralListContext.class, 0);
		}

		public List<TerminalNode> FLOAT_LITERAL() {
			return getTokens(FlatZincParser.FLOAT_LITERAL);
		}

		public TerminalNode FLOAT_LITERAL(int i) {
			return getToken(FlatZincParser.FLOAT_LITERAL, i);
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
		enterRule(_localctx, 26, RULE_setLiteral);
		int _la;
		try {
			setState(218);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(202);
				match(T__13);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INT_LITERAL) {
					{
						setState(203);
						intLiteralList();
					}
				}

				setState(206);
				match(T__14);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(207);
				match(INT_LITERAL);
				setState(208);
				match(T__12);
				setState(209);
				match(INT_LITERAL);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(210);
				match(T__13);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == FLOAT_LITERAL) {
					{
						setState(211);
						floatLiteralList();
					}
				}

				setState(214);
				match(T__14);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(215);
				match(FLOAT_LITERAL);
				setState(216);
				match(T__12);
				setState(217);
				match(FLOAT_LITERAL);
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
	public static class BasicExprListContext extends ParserRuleContext {
		public List<BasicExprContext> basicExpr() {
			return getRuleContexts(BasicExprContext.class);
		}

		public BasicExprContext basicExpr(int i) {
			return getRuleContext(BasicExprContext.class, i);
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
		enterRule(_localctx, 28, RULE_basicExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(220);
				basicExpr();
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__4) {
					{
						{
							setState(221);
							match(T__4);
							setState(222);
							basicExpr();
						}
					}
					setState(227);
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
		public List<BasicExprContext> basicExpr() {
			return getRuleContexts(BasicExprContext.class);
		}

		public BasicExprContext basicExpr(int i) {
			return getRuleContext(BasicExprContext.class, i);
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
		enterRule(_localctx, 30, RULE_basicLiteralExprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(228);
				basicExpr();
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__4) {
					{
						{
							setState(229);
							match(T__4);
							setState(230);
							basicExpr();
						}
					}
					setState(235);
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
	public static class ArrayLiteralContext extends ParserRuleContext {
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
		enterRule(_localctx, 32, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(236);
				match(T__16);
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2954379264L) != 0)) {
					{
						setState(237);
						basicExprList();
					}
				}

				setState(240);
				match(T__17);
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
		public List<TerminalNode> INT_LITERAL() {
			return getTokens(FlatZincParser.INT_LITERAL);
		}

		public TerminalNode INT_LITERAL(int i) {
			return getToken(FlatZincParser.INT_LITERAL, i);
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
		enterRule(_localctx, 34, RULE_intLiteralList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(242);
				match(INT_LITERAL);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__4) {
					{
						{
							setState(243);
							match(T__4);
							setState(244);
							match(INT_LITERAL);
						}
					}
					setState(249);
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
		public List<TerminalNode> FLOAT_LITERAL() {
			return getTokens(FlatZincParser.FLOAT_LITERAL);
		}

		public TerminalNode FLOAT_LITERAL(int i) {
			return getToken(FlatZincParser.FLOAT_LITERAL, i);
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
		enterRule(_localctx, 36, RULE_floatLiteralList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(250);
				match(FLOAT_LITERAL);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__4) {
					{
						{
							setState(251);
							match(T__4);
							setState(252);
							match(FLOAT_LITERAL);
						}
					}
					setState(257);
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
		public IndexSetContext indexSet() {
			return getRuleContext(IndexSetContext.class, 0);
		}

		public BasicParTypeContext basicParType() {
			return getRuleContext(BasicParTypeContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public BasicLiteralExprListContext basicLiteralExprList() {
			return getRuleContext(BasicLiteralExprListContext.class, 0);
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
		enterRule(_localctx, 38, RULE_parDeclItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(258);
				match(T__15);
				setState(259);
				match(T__16);
				setState(260);
				indexSet();
				setState(261);
				match(T__17);
				setState(262);
				match(T__10);
				setState(263);
				basicParType();
				setState(264);
				match(T__5);
				setState(265);
				match(IDENTIFIER);
				setState(266);
				match(T__20);
				setState(267);
				match(T__16);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2954379264L) != 0)) {
					{
						setState(268);
						basicLiteralExprList();
					}
				}

				setState(271);
				match(T__17);
				setState(272);
				match(T__3);
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

		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
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
		enterRule(_localctx, 40, RULE_varDeclItem);
		int _la;
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1); {
				setState(274);
				basicVarType();
				setState(275);
				match(T__5);
				setState(276);
				match(IDENTIFIER);
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(277);
							match(T__21);
							setState(278);
							annotation();
						}
					}
					setState(283);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__20) {
					{
						setState(284);
						match(T__20);
						setState(285);
						basicExpr();
					}
				}

				setState(288);
				match(T__3);
			}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2); {
				setState(290);
				arrayVarType();
				setState(291);
				match(T__5);
				setState(292);
				match(IDENTIFIER);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(293);
							match(T__21);
							setState(294);
							annotation();
						}
					}
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(300);
				match(T__20);
				setState(301);
				arrayLiteral();
				setState(302);
				match(T__3);
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
		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
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
		enterRule(_localctx, 42, RULE_constraintItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(306);
				match(T__22);
				setState(307);
				match(IDENTIFIER);
				setState(308);
				match(T__1);
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2954510336L) != 0)) {
					{
						setState(309);
						expr();
						setState(314);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__4) {
							{
								{
									setState(310);
									match(T__4);
									setState(311);
									expr();
								}
							}
							setState(316);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(319);
				match(T__2);
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(320);
							match(T__21);
							setState(321);
							annotation();
						}
					}
					setState(326);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(327);
				match(T__3);
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
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
		}

		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class, 0);
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
		enterRule(_localctx, 44, RULE_solveItem);
		int _la;
		try {
			setState(363);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(329);
				match(T__23);
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(330);
							match(T__21);
							setState(331);
							annotation();
						}
					}
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(337);
				match(T__24);
				setState(338);
				match(T__3);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(339);
				match(T__23);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(340);
							match(T__21);
							setState(341);
							annotation();
						}
					}
					setState(346);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(347);
				match(T__25);
				setState(348);
				basicExpr();
				setState(349);
				match(T__3);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(351);
				match(T__23);
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21) {
					{
						{
							setState(352);
							match(T__21);
							setState(353);
							annotation();
						}
					}
					setState(358);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(359);
				match(T__26);
				setState(360);
				basicExpr();
				setState(361);
				match(T__3);
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
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public List<AnnExprContext> annExpr() {
			return getRuleContexts(AnnExprContext.class);
		}

		public AnnExprContext annExpr(int i) {
			return getRuleContext(AnnExprContext.class, i);
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
		enterRule(_localctx, 46, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(365);
				match(IDENTIFIER);
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__1) {
					{
						setState(366);
						match(T__1);
						setState(367);
						annExpr();
						setState(372);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__4) {
							{
								{
									setState(368);
									match(T__4);
									setState(369);
									annExpr();
								}
							}
							setState(374);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(375);
						match(T__2);
					}
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
	public static class AnnExprContext extends ParserRuleContext {
		public List<BasicAnnExprContext> basicAnnExpr() {
			return getRuleContexts(BasicAnnExprContext.class);
		}

		public BasicAnnExprContext basicAnnExpr(int i) {
			return getRuleContext(BasicAnnExprContext.class, i);
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
		enterRule(_localctx, 48, RULE_annExpr);
		int _la;
		try {
			setState(392);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__18:
			case T__19:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1); {
				setState(379);
				basicAnnExpr();
			}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2); {
				setState(380);
				match(T__16);
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4028121088L) != 0)) {
					{
						setState(381);
						basicAnnExpr();
						setState(386);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__4) {
							{
								{
									setState(382);
									match(T__4);
									setState(383);
									basicAnnExpr();
								}
							}
							setState(388);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(391);
				match(T__17);
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
		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(FlatZincParser.IDENTIFIER, 0);
		}

		public TerminalNode STRING_LITERAL() {
			return getToken(FlatZincParser.STRING_LITERAL, 0);
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
		enterRule(_localctx, 50, RULE_basicAnnExpr);
		try {
			setState(398);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 40, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(394);
				basicExpr();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(395);
				match(IDENTIFIER);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(396);
				match(STRING_LITERAL);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(397);
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

	public static final String _serializedATN = "\u0004\u0001#\u0191\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
		+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015" +
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018" +
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0005\u00006\b\u0000\n\u0000\f\u0000" +
		"9\t\u0000\u0001\u0000\u0005\u0000<\b\u0000\n\u0000\f\u0000?\t\u0000\u0001" +
		"\u0000\u0005\u0000B\b\u0000\n\u0000\f\u0000E\t\u0000\u0001\u0000\u0005" +
		"\u0000H\b\u0000\n\u0000\f\u0000K\t\u0000\u0001\u0000\u0001\u0000\u0001" +
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001T\b" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001" +
		"\u0002\u0005\u0002\\\b\u0002\n\u0002\f\u0002_\t\u0002\u0001\u0003\u0001" +
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001" +
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004k\b\u0004\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
		"\u0005\u0003\u0005\u0087\b\u0005\u0001\u0005\u0003\u0005\u008a\b\u0005" +
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006" +
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u0095\b\u0007\u0001\b" +
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001" +
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001" +
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00b0\b\t\u0001" +
		"\t\u0003\t\u00b3\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
		"\n\u0001\n\u0003\n\u00bd\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u00c1" +
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00c9" +
		"\b\f\u0001\r\u0001\r\u0003\r\u00cd\b\r\u0001\r\u0001\r\u0001\r\u0001\r" +
		"\u0001\r\u0001\r\u0003\r\u00d5\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003" +
		"\r\u00db\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00e0\b\u000e" +
		"\n\u000e\f\u000e\u00e3\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005" +
		"\u000f\u00e8\b\u000f\n\u000f\f\u000f\u00eb\t\u000f\u0001\u0010\u0001\u0010" +
		"\u0003\u0010\u00ef\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011" +
		"\u0001\u0011\u0005\u0011\u00f6\b\u0011\n\u0011\f\u0011\u00f9\t\u0011\u0001" +
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00fe\b\u0012\n\u0012\f\u0012" +
		"\u0101\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013" +
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013" +
		"\u0003\u0013\u010e\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014" +
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0118\b\u0014" +
		"\n\u0014\f\u0014\u011b\t\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u011f" +
		"\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001" +
		"\u0014\u0001\u0014\u0005\u0014\u0128\b\u0014\n\u0014\f\u0014\u012b\t\u0014" +
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0131\b\u0014" +
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015" +
		"\u0005\u0015\u0139\b\u0015\n\u0015\f\u0015\u013c\t\u0015\u0003\u0015\u013e" +
		"\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0143\b\u0015" +
		"\n\u0015\f\u0015\u0146\t\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001" +
		"\u0016\u0001\u0016\u0005\u0016\u014d\b\u0016\n\u0016\f\u0016\u0150\t\u0016" +
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016" +
		"\u0157\b\u0016\n\u0016\f\u0016\u015a\t\u0016\u0001\u0016\u0001\u0016\u0001" +
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0163" +
		"\b\u0016\n\u0016\f\u0016\u0166\t\u0016\u0001\u0016\u0001\u0016\u0001\u0016" +
		"\u0001\u0016\u0003\u0016\u016c\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017" +
		"\u0001\u0017\u0001\u0017\u0005\u0017\u0173\b\u0017\n\u0017\f\u0017\u0176" +
		"\t\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u017a\b\u0017\u0001\u0018" +
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0181\b\u0018" +
		"\n\u0018\f\u0018\u0184\t\u0018\u0003\u0018\u0186\b\u0018\u0001\u0018\u0003" +
		"\u0018\u0189\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003" +
		"\u0019\u018f\b\u0019\u0001\u0019\u0000\u0000\u001a\u0000\u0002\u0004\u0006" +
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,." +
		"02\u0000\u0000\u01b3\u00007\u0001\u0000\u0000\u0000\u0002O\u0001\u0000" +
		"\u0000\u0000\u0004X\u0001\u0000\u0000\u0000\u0006`\u0001\u0000\u0000\u0000" +
		"\bj\u0001\u0000\u0000\u0000\n\u0089\u0001\u0000\u0000\u0000\f\u008b\u0001" +
		"\u0000\u0000\u0000\u000e\u0094\u0001\u0000\u0000\u0000\u0010\u0096\u0001" +
		"\u0000\u0000\u0000\u0012\u00b2\u0001\u0000\u0000\u0000\u0014\u00bc\u0001" +
		"\u0000\u0000\u0000\u0016\u00c0\u0001\u0000\u0000\u0000\u0018\u00c8\u0001" +
		"\u0000\u0000\u0000\u001a\u00da\u0001\u0000\u0000\u0000\u001c\u00dc\u0001" +
		"\u0000\u0000\u0000\u001e\u00e4\u0001\u0000\u0000\u0000 \u00ec\u0001\u0000" +
		"\u0000\u0000\"\u00f2\u0001\u0000\u0000\u0000$\u00fa\u0001\u0000\u0000" +
		"\u0000&\u0102\u0001\u0000\u0000\u0000(\u0130\u0001\u0000\u0000\u0000*" +
		"\u0132\u0001\u0000\u0000\u0000,\u016b\u0001\u0000\u0000\u0000.\u016d\u0001" +
		"\u0000\u0000\u00000\u0188\u0001\u0000\u0000\u00002\u018e\u0001\u0000\u0000" +
		"\u000046\u0003\u0002\u0001\u000054\u0001\u0000\u0000\u000069\u0001\u0000" +
		"\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u00008=\u0001" +
		"\u0000\u0000\u000097\u0001\u0000\u0000\u0000:<\u0003&\u0013\u0000;:\u0001" +
		"\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000" +
		"=>\u0001\u0000\u0000\u0000>C\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000" +
		"\u0000@B\u0003(\u0014\u0000A@\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000" +
		"\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DI\u0001\u0000" +
		"\u0000\u0000EC\u0001\u0000\u0000\u0000FH\u0003*\u0015\u0000GF\u0001\u0000" +
		"\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001" +
		"\u0000\u0000\u0000JL\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000" +
		"LM\u0003,\u0016\u0000MN\u0005\u0000\u0000\u0001N\u0001\u0001\u0000\u0000" +
		"\u0000OP\u0005\u0001\u0000\u0000PQ\u0005\u001f\u0000\u0000QS\u0005\u0002" +
		"\u0000\u0000RT\u0003\u0004\u0002\u0000SR\u0001\u0000\u0000\u0000ST\u0001" +
		"\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UV\u0005\u0003\u0000\u0000" +
		"VW\u0005\u0004\u0000\u0000W\u0003\u0001\u0000\u0000\u0000X]\u0003\u0006" +
		"\u0003\u0000YZ\u0005\u0005\u0000\u0000Z\\\u0003\u0006\u0003\u0000[Y\u0001" +
		"\u0000\u0000\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000" +
		"]^\u0001\u0000\u0000\u0000^\u0005\u0001\u0000\u0000\u0000_]\u0001\u0000" +
		"\u0000\u0000`a\u0003\u0014\n\u0000ab\u0005\u0006\u0000\u0000bc\u0005\u001f" +
		"\u0000\u0000c\u0007\u0001\u0000\u0000\u0000dk\u0005\u0007\u0000\u0000" +
		"ek\u0005\b\u0000\u0000fk\u0005\t\u0000\u0000gh\u0005\n\u0000\u0000hi\u0005" +
		"\u000b\u0000\u0000ik\u0005\b\u0000\u0000jd\u0001\u0000\u0000\u0000je\u0001" +
		"\u0000\u0000\u0000jf\u0001\u0000\u0000\u0000jg\u0001\u0000\u0000\u0000" +
		"k\t\u0001\u0000\u0000\u0000lm\u0005\f\u0000\u0000m\u008a\u0003\b\u0004" +
		"\u0000no\u0005\f\u0000\u0000op\u0005\u001c\u0000\u0000pq\u0005\r\u0000" +
		"\u0000q\u008a\u0005\u001c\u0000\u0000rs\u0005\f\u0000\u0000st\u0005\u000e" +
		"\u0000\u0000tu\u0003\"\u0011\u0000uv\u0005\u000f\u0000\u0000v\u008a\u0001" +
		"\u0000\u0000\u0000wx\u0005\f\u0000\u0000xy\u0005\u001d\u0000\u0000yz\u0005" +
		"\r\u0000\u0000z\u008a\u0005\u001d\u0000\u0000{|\u0005\f\u0000\u0000|}" +
		"\u0005\n\u0000\u0000}~\u0005\u000b\u0000\u0000~\u007f\u0005\u001c\u0000" +
		"\u0000\u007f\u0080\u0005\r\u0000\u0000\u0080\u008a\u0005\u001c\u0000\u0000" +
		"\u0081\u0082\u0005\f\u0000\u0000\u0082\u0083\u0005\n\u0000\u0000\u0083" +
		"\u0084\u0005\u000b\u0000\u0000\u0084\u0086\u0005\u000e\u0000\u0000\u0085" +
		"\u0087\u0003\"\u0011\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087" +
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a" +
		"\u0005\u000f\u0000\u0000\u0089l\u0001\u0000\u0000\u0000\u0089n\u0001\u0000" +
		"\u0000\u0000\u0089r\u0001\u0000\u0000\u0000\u0089w\u0001\u0000\u0000\u0000" +
		"\u0089{\u0001\u0000\u0000\u0000\u0089\u0081\u0001\u0000\u0000\u0000\u008a" +
		"\u000b\u0001\u0000\u0000\u0000\u008b\u008c\u0005\u0010\u0000\u0000\u008c" +
		"\u008d\u0005\u0011\u0000\u0000\u008d\u008e\u0003\u0010\b\u0000\u008e\u008f" +
		"\u0005\u0012\u0000\u0000\u008f\u0090\u0005\u000b\u0000\u0000\u0090\u0091" +
		"\u0003\n\u0005\u0000\u0091\r\u0001\u0000\u0000\u0000\u0092\u0095\u0003" +
		"\u0010\b\u0000\u0093\u0095\u0005\b\u0000\u0000\u0094\u0092\u0001\u0000" +
		"\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u000f\u0001\u0000" +
		"\u0000\u0000\u0096\u0097\u0005\u001c\u0000\u0000\u0097\u0098\u0005\r\u0000" +
		"\u0000\u0098\u0099\u0005\u001c\u0000\u0000\u0099\u0011\u0001\u0000\u0000" +
		"\u0000\u009a\u00b3\u0003\b\u0004\u0000\u009b\u00b3\u0003\n\u0005\u0000" +
		"\u009c\u009d\u0005\u001c\u0000\u0000\u009d\u009e\u0005\r\u0000\u0000\u009e" +
		"\u00b3\u0005\u001c\u0000\u0000\u009f\u00a0\u0005\u001d\u0000\u0000\u00a0" +
		"\u00a1\u0005\r\u0000\u0000\u00a1\u00b3\u0005\u001d\u0000\u0000\u00a2\u00a3" +
		"\u0005\u000e\u0000\u0000\u00a3\u00a4\u0003\"\u0011\u0000\u00a4\u00a5\u0005" +
		"\u000f\u0000\u0000\u00a5\u00b3\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005" +
		"\n\u0000\u0000\u00a7\u00a8\u0005\u000b\u0000\u0000\u00a8\u00a9\u0005\u001c" +
		"\u0000\u0000\u00a9\u00aa\u0005\r\u0000\u0000\u00aa\u00b3\u0005\u001c\u0000" +
		"\u0000\u00ab\u00ac\u0005\n\u0000\u0000\u00ac\u00ad\u0005\u000b\u0000\u0000" +
		"\u00ad\u00af\u0005\u000e\u0000\u0000\u00ae\u00b0\u0003\"\u0011\u0000\u00af" +
		"\u00ae\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0" +
		"\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b3\u0005\u000f\u0000\u0000\u00b2" +
		"\u009a\u0001\u0000\u0000\u0000\u00b2\u009b\u0001\u0000\u0000\u0000\u00b2" +
		"\u009c\u0001\u0000\u0000\u0000\u00b2\u009f\u0001\u0000\u0000\u0000\u00b2" +
		"\u00a2\u0001\u0000\u0000\u0000\u00b2\u00a6\u0001\u0000\u0000\u0000\u00b2" +
		"\u00ab\u0001\u0000\u0000\u0000\u00b3\u0013\u0001\u0000\u0000\u0000\u00b4" +
		"\u00bd\u0003\u0012\t\u0000\u00b5\u00b6\u0005\u0010\u0000\u0000\u00b6\u00b7" +
		"\u0005\u0011\u0000\u0000\u00b7\u00b8\u0003\u000e\u0007\u0000\u00b8\u00b9" +
		"\u0005\u0012\u0000\u0000\u00b9\u00ba\u0005\u000b\u0000\u0000\u00ba\u00bb" +
		"\u0003\u0012\t\u0000\u00bb\u00bd\u0001\u0000\u0000\u0000\u00bc\u00b4\u0001" +
		"\u0000\u0000\u0000\u00bc\u00b5\u0001\u0000\u0000\u0000\u00bd\u0015\u0001" +
		"\u0000\u0000\u0000\u00be\u00c1\u0003\u0018\f\u0000\u00bf\u00c1\u0003 " +
		"\u0010\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c0\u00bf\u0001\u0000" +
		"\u0000\u0000\u00c1\u0017\u0001\u0000\u0000\u0000\u00c2\u00c9\u0005\u0013" +
		"\u0000\u0000\u00c3\u00c9\u0005\u0014\u0000\u0000\u00c4\u00c9\u0005\u001c" +
		"\u0000\u0000\u00c5\u00c9\u0005\u001d\u0000\u0000\u00c6\u00c9\u0003\u001a" +
		"\r\u0000\u00c7\u00c9\u0005\u001f\u0000\u0000\u00c8\u00c2\u0001\u0000\u0000" +
		"\u0000\u00c8\u00c3\u0001\u0000\u0000\u0000\u00c8\u00c4\u0001\u0000\u0000" +
		"\u0000\u00c8\u00c5\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000" +
		"\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9\u0019\u0001\u0000\u0000" +
		"\u0000\u00ca\u00cc\u0005\u000e\u0000\u0000\u00cb\u00cd\u0003\"\u0011\u0000" +
		"\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000" +
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00db\u0005\u000f\u0000\u0000" +
		"\u00cf\u00d0\u0005\u001c\u0000\u0000\u00d0\u00d1\u0005\r\u0000\u0000\u00d1" +
		"\u00db\u0005\u001c\u0000\u0000\u00d2\u00d4\u0005\u000e\u0000\u0000\u00d3" +
		"\u00d5\u0003$\u0012\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4\u00d5" +
		"\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00db" +
		"\u0005\u000f\u0000\u0000\u00d7\u00d8\u0005\u001d\u0000\u0000\u00d8\u00d9" +
		"\u0005\r\u0000\u0000\u00d9\u00db\u0005\u001d\u0000\u0000\u00da\u00ca\u0001" +
		"\u0000\u0000\u0000\u00da\u00cf\u0001\u0000\u0000\u0000\u00da\u00d2\u0001" +
		"\u0000\u0000\u0000\u00da\u00d7\u0001\u0000\u0000\u0000\u00db\u001b\u0001" +
		"\u0000\u0000\u0000\u00dc\u00e1\u0003\u0018\f\u0000\u00dd\u00de\u0005\u0005" +
		"\u0000\u0000\u00de\u00e0\u0003\u0018\f\u0000\u00df\u00dd\u0001\u0000\u0000" +
		"\u0000\u00e0\u00e3\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000" +
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u001d\u0001\u0000\u0000" +
		"\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00e9\u0003\u0018\f\u0000" +
		"\u00e5\u00e6\u0005\u0005\u0000\u0000\u00e6\u00e8\u0003\u0018\f\u0000\u00e7" +
		"\u00e5\u0001\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9" +
		"\u00e7\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea" +
		"\u001f\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec" +
		"\u00ee\u0005\u0011\u0000\u0000\u00ed\u00ef\u0003\u001c\u000e\u0000\u00ee" +
		"\u00ed\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef" +
		"\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u0012\u0000\u0000\u00f1" +
		"!\u0001\u0000\u0000\u0000\u00f2\u00f7\u0005\u001c\u0000\u0000\u00f3\u00f4" +
		"\u0005\u0005\u0000\u0000\u00f4\u00f6\u0005\u001c\u0000\u0000\u00f5\u00f3" +
		"\u0001\u0000\u0000\u0000\u00f6\u00f9\u0001\u0000\u0000\u0000\u00f7\u00f5" +
		"\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8#\u0001" +
		"\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa\u00ff\u0005" +
		"\u001d\u0000\u0000\u00fb\u00fc\u0005\u0005\u0000\u0000\u00fc\u00fe\u0005" +
		"\u001d\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fe\u0101\u0001" +
		"\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100\u0001" +
		"\u0000\u0000\u0000\u0100%\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000" +
		"\u0000\u0000\u0102\u0103\u0005\u0010\u0000\u0000\u0103\u0104\u0005\u0011" +
		"\u0000\u0000\u0104\u0105\u0003\u0010\b\u0000\u0105\u0106\u0005\u0012\u0000" +
		"\u0000\u0106\u0107\u0005\u000b\u0000\u0000\u0107\u0108\u0003\b\u0004\u0000" +
		"\u0108\u0109\u0005\u0006\u0000\u0000\u0109\u010a\u0005\u001f\u0000\u0000" +
		"\u010a\u010b\u0005\u0015\u0000\u0000\u010b\u010d\u0005\u0011\u0000\u0000" +
		"\u010c\u010e\u0003\u001e\u000f\u0000\u010d\u010c\u0001\u0000\u0000\u0000" +
		"\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000" +
		"\u010f\u0110\u0005\u0012\u0000\u0000\u0110\u0111\u0005\u0004\u0000\u0000" +
		"\u0111\'\u0001\u0000\u0000\u0000\u0112\u0113\u0003\n\u0005\u0000\u0113" +
		"\u0114\u0005\u0006\u0000\u0000\u0114\u0119\u0005\u001f\u0000\u0000\u0115" +
		"\u0116\u0005\u0016\u0000\u0000\u0116\u0118\u0003.\u0017\u0000\u0117\u0115" +
		"\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000\u0119\u0117" +
		"\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u011e" +
		"\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c\u011d" +
		"\u0005\u0015\u0000\u0000\u011d\u011f\u0003\u0018\f\u0000\u011e\u011c\u0001" +
		"\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001" +
		"\u0000\u0000\u0000\u0120\u0121\u0005\u0004\u0000\u0000\u0121\u0131\u0001" +
		"\u0000\u0000\u0000\u0122\u0123\u0003\f\u0006\u0000\u0123\u0124\u0005\u0006" +
		"\u0000\u0000\u0124\u0129\u0005\u001f\u0000\u0000\u0125\u0126\u0005\u0016" +
		"\u0000\u0000\u0126\u0128\u0003.\u0017\u0000\u0127\u0125\u0001\u0000\u0000" +
		"\u0000\u0128\u012b\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000" +
		"\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012c\u0001\u0000\u0000" +
		"\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012c\u012d\u0005\u0015\u0000" +
		"\u0000\u012d\u012e\u0003 \u0010\u0000\u012e\u012f\u0005\u0004\u0000\u0000" +
		"\u012f\u0131\u0001\u0000\u0000\u0000\u0130\u0112\u0001\u0000\u0000\u0000" +
		"\u0130\u0122\u0001\u0000\u0000\u0000\u0131)\u0001\u0000\u0000\u0000\u0132" +
		"\u0133\u0005\u0017\u0000\u0000\u0133\u0134\u0005\u001f\u0000\u0000\u0134" +
		"\u013d\u0005\u0002\u0000\u0000\u0135\u013a\u0003\u0016\u000b\u0000\u0136" +
		"\u0137\u0005\u0005\u0000\u0000\u0137\u0139\u0003\u0016\u000b\u0000\u0138" +
		"\u0136\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a" +
		"\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b" +
		"\u013e\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d" +
		"\u0135\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e" +
		"\u013f\u0001\u0000\u0000\u0000\u013f\u0144\u0005\u0003\u0000\u0000\u0140" +
		"\u0141\u0005\u0016\u0000\u0000\u0141\u0143\u0003.\u0017\u0000\u0142\u0140" +
		"\u0001\u0000\u0000\u0000\u0143\u0146\u0001\u0000\u0000\u0000\u0144\u0142" +
		"\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0147" +
		"\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0147\u0148" +
		"\u0005\u0004\u0000\u0000\u0148+\u0001\u0000\u0000\u0000\u0149\u014e\u0005" +
		"\u0018\u0000\u0000\u014a\u014b\u0005\u0016\u0000\u0000\u014b\u014d\u0003" +
		".\u0017\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014d\u0150\u0001\u0000" +
		"\u0000\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000" +
		"\u0000\u0000\u014f\u0151\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000" +
		"\u0000\u0000\u0151\u0152\u0005\u0019\u0000\u0000\u0152\u016c\u0005\u0004" +
		"\u0000\u0000\u0153\u0158\u0005\u0018\u0000\u0000\u0154\u0155\u0005\u0016" +
		"\u0000\u0000\u0155\u0157\u0003.\u0017\u0000\u0156\u0154\u0001\u0000\u0000" +
		"\u0000\u0157\u015a\u0001\u0000\u0000\u0000\u0158\u0156\u0001\u0000\u0000" +
		"\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015b\u0001\u0000\u0000" +
		"\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015c\u0005\u001a\u0000" +
		"\u0000\u015c\u015d\u0003\u0018\f\u0000\u015d\u015e\u0005\u0004\u0000\u0000" +
		"\u015e\u016c\u0001\u0000\u0000\u0000\u015f\u0164\u0005\u0018\u0000\u0000" +
		"\u0160\u0161\u0005\u0016\u0000\u0000\u0161\u0163\u0003.\u0017\u0000\u0162" +
		"\u0160\u0001\u0000\u0000\u0000\u0163\u0166\u0001\u0000\u0000\u0000\u0164" +
		"\u0162\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165" +
		"\u0167\u0001\u0000\u0000\u0000\u0166\u0164\u0001\u0000\u0000\u0000\u0167" +
		"\u0168\u0005\u001b\u0000\u0000\u0168\u0169\u0003\u0018\f\u0000\u0169\u016a" +
		"\u0005\u0004\u0000\u0000\u016a\u016c\u0001\u0000\u0000\u0000\u016b\u0149" +
		"\u0001\u0000\u0000\u0000\u016b\u0153\u0001\u0000\u0000\u0000\u016b\u015f" +
		"\u0001\u0000\u0000\u0000\u016c-\u0001\u0000\u0000\u0000\u016d\u0179\u0005" +
		"\u001f\u0000\u0000\u016e\u016f\u0005\u0002\u0000\u0000\u016f\u0174\u0003" +
		"0\u0018\u0000\u0170\u0171\u0005\u0005\u0000\u0000\u0171\u0173\u00030\u0018" +
		"\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0176\u0001\u0000\u0000" +
		"\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000" +
		"\u0000\u0175\u0177\u0001\u0000\u0000\u0000\u0176\u0174\u0001\u0000\u0000" +
		"\u0000\u0177\u0178\u0005\u0003\u0000\u0000\u0178\u017a\u0001\u0000\u0000" +
		"\u0000\u0179\u016e\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000" +
		"\u0000\u017a/\u0001\u0000\u0000\u0000\u017b\u0189\u00032\u0019\u0000\u017c" +
		"\u0185\u0005\u0011\u0000\u0000\u017d\u0182\u00032\u0019\u0000\u017e\u017f" +
		"\u0005\u0005\u0000\u0000\u017f\u0181\u00032\u0019\u0000\u0180\u017e\u0001" +
		"\u0000\u0000\u0000\u0181\u0184\u0001\u0000\u0000\u0000\u0182\u0180\u0001" +
		"\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u0186\u0001" +
		"\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0185\u017d\u0001" +
		"\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0187\u0001" +
		"\u0000\u0000\u0000\u0187\u0189\u0005\u0012\u0000\u0000\u0188\u017b\u0001" +
		"\u0000\u0000\u0000\u0188\u017c\u0001\u0000\u0000\u0000\u01891\u0001\u0000" +
		"\u0000\u0000\u018a\u018f\u0003\u0018\f\u0000\u018b\u018f\u0005\u001f\u0000" +
		"\u0000\u018c\u018f\u0005\u001e\u0000\u0000\u018d\u018f\u0003.\u0017\u0000" +
		"\u018e\u018a\u0001\u0000\u0000\u0000\u018e\u018b\u0001\u0000\u0000\u0000" +
		"\u018e\u018c\u0001\u0000\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000" +
		"\u018f3\u0001\u0000\u0000\u0000)7=CIS]j\u0086\u0089\u0094\u00af\u00b2" +
		"\u00bc\u00c0\u00c8\u00cc\u00d4\u00da\u00e1\u00e9\u00ee\u00f7\u00ff\u010d" +
		"\u0119\u011e\u0129\u0130\u013a\u013d\u0144\u014e\u0158\u0164\u016b\u0174" +
		"\u0179\u0182\u0185\u0188\u018e";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
