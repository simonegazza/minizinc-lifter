// Generated from MiniZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.minizinc;

import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape" })
public class MiniZincParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
		T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
		T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
		T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
		T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
		T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
		T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, T__50 = 51, T__51 = 52,
		T__52 = 53, T__53 = 54, T__54 = 55, T__55 = 56, T__56 = 57, T__57 = 58, T__58 = 59,
		T__59 = 60, T__60 = 61, T__61 = 62, T__62 = 63, T__63 = 64, T__64 = 65, T__65 = 66,
		T__66 = 67, T__67 = 68, T__68 = 69, T__69 = 70, T__70 = 71, T__71 = 72, T__72 = 73,
		T__73 = 74, T__74 = 75, T__75 = 76, T__76 = 77, T__77 = 78, EQ = 79, INT_LITERAL = 80,
		FLOAT_LITERAL = 81, DOLLAR_IDENT = 82, IDENT = 83, STRING_LITERAL = 84, COMMENT = 85,
		WS = 86;
	public static final int RULE_model = 0, RULE_item = 1, RULE_includeItem = 2, RULE_varDeclItem = 3,
		RULE_enumItem = 4, RULE_enumCasesList = 5, RULE_enumCases = 6, RULE_typeInstSynItem = 7,
		RULE_assignItem = 8, RULE_constraintItem = 9, RULE_solveItem = 10, RULE_outputItem = 11,
		RULE_annotationItem = 12, RULE_predicateItem = 13, RULE_testItem = 14,
		RULE_functionItem = 15, RULE_operationItemTail = 16, RULE_params = 17,
		RULE_tiExprAndId = 18, RULE_tiExpr = 19, RULE_baseTiExpr = 20, RULE_baseTiExprTail = 21,
		RULE_baseType = 22, RULE_arrayTiExpr = 23, RULE_arrayAccessTail = 24,
		RULE_fieldAccessTail = 25, RULE_annotations = 26, RULE_annotation = 27,
		RULE_exprAtomHead = 28, RULE_annLiteral = 29, RULE_exprAtomTail = 30,
		RULE_expr = 31, RULE_iffExpr = 32, RULE_implExpr = 33, RULE_orExpr = 34,
		RULE_xorExpr = 35, RULE_andExpr = 36, RULE_compareExpr = 37, RULE_setExpr = 38,
		RULE_rangeExpr = 39, RULE_addExpr = 40, RULE_multExpr = 41, RULE_powExpr = 42,
		RULE_unaryExpr = 43, RULE_primary = 44, RULE_postfix = 45, RULE_quantifierExpr = 46,
		RULE_callSuffix = 47, RULE_literal = 48, RULE_setLiteral = 49, RULE_arrayLiteral = 50,
		RULE_arrayLiteral2d = 51, RULE_arrayRow = 52, RULE_tupleLiteral = 53,
		RULE_recordLiteral = 54, RULE_ifThenElseExpr = 55, RULE_letExpr = 56,
		RULE_letItem = 57, RULE_compTail = 58, RULE_generatorList = 59, RULE_generator = 60,
		RULE_indexedArrayLiteral = 61, RULE_indexedArrayLiteral2d = 62, RULE_indexedArrayRow = 63,
		RULE_indexTuple = 64, RULE_arrayComp = 65, RULE_indexedArrayComp = 66,
		RULE_callExpr = 67, RULE_genCallExpr = 68, RULE_ident = 69;

	private static String[] makeRuleNames() {
		return new String[] {
				"model", "item", "includeItem", "varDeclItem", "enumItem", "enumCasesList",
				"enumCases", "typeInstSynItem", "assignItem", "constraintItem", "solveItem",
				"outputItem", "annotationItem", "predicateItem", "testItem", "functionItem",
				"operationItemTail", "params", "tiExprAndId", "tiExpr", "baseTiExpr",
				"baseTiExprTail", "baseType", "arrayTiExpr", "arrayAccessTail", "fieldAccessTail",
				"annotations", "annotation", "exprAtomHead", "annLiteral", "exprAtomTail",
				"expr", "iffExpr", "implExpr", "orExpr", "xorExpr", "andExpr", "compareExpr",
				"setExpr", "rangeExpr", "addExpr", "multExpr", "powExpr", "unaryExpr",
				"primary", "postfix", "quantifierExpr", "callSuffix", "literal", "setLiteral",
				"arrayLiteral", "arrayLiteral2d", "arrayRow", "tupleLiteral", "recordLiteral",
				"ifThenElseExpr", "letExpr", "letItem", "compTail", "generatorList",
				"generator", "indexedArrayLiteral", "indexedArrayLiteral2d", "indexedArrayRow",
				"indexTuple", "arrayComp", "indexedArrayComp", "callExpr", "genCallExpr",
				"ident"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
				null, "';'", "'include'", "'any'", "':'", "'enum'", "'++'", "'{'", "','",
				"'}'", "'_'", "'('", "')'", "'anon_enum'", "'type'", "'constraint'",
				"'::'", "'solve'", "'satisfy'", "'minimize'", "'maximize'", "'output'",
				"'annotation'", "'predicate'", "'test'", "'function'", "'var'", "'par'",
				"'opt'", "'set of'", "'ann'", "'bool'", "'int'", "'float'", "'string'",
				"'array'", "'['", "']'", "'of'", "'list'", "'.'", "'true'", "'false'",
				"'<->'", "'->'", "'\\/'", "'xor'", "'/\\'", "'<'", "'>'", "'<='", "'>='",
				"'!='", "'in'", "'subset'", "'superset'", "'union'", "'diff'", "'symdiff'",
				"'intersect'", "'..'", "'+'", "'-'", "'*'", "'/'", "'div'", "'mod'",
				"'pow'", "'not'", "'|'", "'[|'", "'|]'", "'if'", "'then'", "'elseif'",
				"'else'", "'endif'", "'let'", "'where'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, "EQ", "INT_LITERAL", "FLOAT_LITERAL",
				"DOLLAR_IDENT", "IDENT", "STRING_LITERAL", "COMMENT", "WS"
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
		return "MiniZinc.g4";
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

	public MiniZincParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModelContext extends ParserRuleContext {
		public TerminalNode EOF() {
			return getToken(MiniZincParser.EOF, 0);
		}

		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}

		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class, i);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterModel(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitModel(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitModel(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917536311903701164L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 127509L) != 0)) {
					{
						{
							setState(140);
							item();
						}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
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
	public static class ItemContext extends ParserRuleContext {
		public IncludeItemContext includeItem() {
			return getRuleContext(IncludeItemContext.class, 0);
		}

		public VarDeclItemContext varDeclItem() {
			return getRuleContext(VarDeclItemContext.class, 0);
		}

		public EnumItemContext enumItem() {
			return getRuleContext(EnumItemContext.class, 0);
		}

		public TypeInstSynItemContext typeInstSynItem() {
			return getRuleContext(TypeInstSynItemContext.class, 0);
		}

		public AssignItemContext assignItem() {
			return getRuleContext(AssignItemContext.class, 0);
		}

		public ConstraintItemContext constraintItem() {
			return getRuleContext(ConstraintItemContext.class, 0);
		}

		public SolveItemContext solveItem() {
			return getRuleContext(SolveItemContext.class, 0);
		}

		public OutputItemContext outputItem() {
			return getRuleContext(OutputItemContext.class, 0);
		}

		public PredicateItemContext predicateItem() {
			return getRuleContext(PredicateItemContext.class, 0);
		}

		public TestItemContext testItem() {
			return getRuleContext(TestItemContext.class, 0);
		}

		public FunctionItemContext functionItem() {
			return getRuleContext(FunctionItemContext.class, 0);
		}

		public AnnotationItemContext annotationItem() {
			return getRuleContext(AnnotationItemContext.class, 0);
		}

		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_item);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(148);
				includeItem();
				setState(149);
				match(T__0);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(151);
				varDeclItem();
				setState(152);
				match(T__0);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(154);
				enumItem();
				setState(155);
				match(T__0);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(157);
				typeInstSynItem();
				setState(158);
				match(T__0);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(160);
				assignItem();
				setState(161);
				match(T__0);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(163);
				constraintItem();
				setState(164);
				match(T__0);
			}
				break;
			case 7:
				enterOuterAlt(_localctx, 7); {
				setState(166);
				solveItem();
				setState(167);
				match(T__0);
			}
				break;
			case 8:
				enterOuterAlt(_localctx, 8); {
				setState(169);
				outputItem();
				setState(170);
				match(T__0);
			}
				break;
			case 9:
				enterOuterAlt(_localctx, 9); {
				setState(172);
				predicateItem();
				setState(173);
				match(T__0);
			}
				break;
			case 10:
				enterOuterAlt(_localctx, 10); {
				setState(175);
				testItem();
				setState(176);
				match(T__0);
			}
				break;
			case 11:
				enterOuterAlt(_localctx, 11); {
				setState(178);
				functionItem();
				setState(179);
				match(T__0);
			}
				break;
			case 12:
				enterOuterAlt(_localctx, 12); {
				setState(181);
				annotationItem();
				setState(182);
				match(T__0);
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
	public static class IncludeItemContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() {
			return getToken(MiniZincParser.STRING_LITERAL, 0);
		}

		public IncludeItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_includeItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIncludeItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIncludeItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIncludeItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IncludeItemContext includeItem() throws RecognitionException {
		IncludeItemContext _localctx = new IncludeItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_includeItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(186);
				match(T__1);
				setState(187);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclItemContext extends ParserRuleContext {
		public TiExprAndIdContext tiExprAndId() {
			return getRuleContext(TiExprAndIdContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterVarDeclItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitVarDeclItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitVarDeclItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final VarDeclItemContext varDeclItem() throws RecognitionException {
		VarDeclItemContext _localctx = new VarDeclItemContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclItem);
		int _la;
		try {
			setState(203);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(189);
				tiExprAndId();
				setState(190);
				annotations();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(191);
						match(EQ);
						setState(192);
						expr();
					}
				}

			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(195);
				match(T__2);
				setState(196);
				match(T__3);
				setState(197);
				ident();
				setState(198);
				annotations();
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(199);
						match(EQ);
						setState(200);
						expr();
					}
				}

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
	public static class EnumItemContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public EnumCasesListContext enumCasesList() {
			return getRuleContext(EnumCasesListContext.class, 0);
		}

		public EnumItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_enumItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterEnumItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitEnumItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitEnumItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final EnumItemContext enumItem() throws RecognitionException {
		EnumItemContext _localctx = new EnumItemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_enumItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(205);
				match(T__4);
				setState(206);
				ident();
				setState(207);
				annotations();
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(208);
						match(EQ);
						setState(209);
						enumCasesList();
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
	public static class EnumCasesListContext extends ParserRuleContext {
		public List<EnumCasesContext> enumCases() {
			return getRuleContexts(EnumCasesContext.class);
		}

		public EnumCasesContext enumCases(int i) {
			return getRuleContext(EnumCasesContext.class, i);
		}

		public EnumCasesListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_enumCasesList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterEnumCasesList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitEnumCasesList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitEnumCasesList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final EnumCasesListContext enumCasesList() throws RecognitionException {
		EnumCasesListContext _localctx = new EnumCasesListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_enumCasesList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(212);
				enumCases();
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__5) {
					{
						{
							setState(213);
							match(T__5);
							setState(214);
							enumCases();
						}
					}
					setState(219);
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
	public static class EnumCasesContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}

		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class, i);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public EnumCasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_enumCases;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterEnumCases(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitEnumCases(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitEnumCases(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final EnumCasesContext enumCases() throws RecognitionException {
		EnumCasesContext _localctx = new EnumCasesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_enumCases);
		int _la;
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1); {
				setState(220);
				match(T__6);
				setState(221);
				ident();
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(222);
							match(T__7);
							setState(223);
							ident();
						}
					}
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(229);
				match(T__8);
			}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2); {
				setState(231);
				match(T__9);
				setState(232);
				match(T__10);
				setState(233);
				expr();
				setState(234);
				match(T__11);
			}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 3); {
				setState(236);
				ident();
				setState(237);
				match(T__10);
				setState(238);
				ident();
				setState(239);
				match(T__11);
			}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4); {
				setState(241);
				match(T__12);
				setState(242);
				match(T__10);
				setState(243);
				expr();
				setState(244);
				match(T__11);
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
	public static class TypeInstSynItemContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class, 0);
		}

		public TypeInstSynItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_typeInstSynItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterTypeInstSynItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitTypeInstSynItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitTypeInstSynItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TypeInstSynItemContext typeInstSynItem() throws RecognitionException {
		TypeInstSynItemContext _localctx = new TypeInstSynItemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeInstSynItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(248);
				match(T__13);
				setState(249);
				ident();
				setState(250);
				annotations();
				setState(251);
				match(EQ);
				setState(252);
				tiExpr();
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
	public static class AssignItemContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public AssignItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAssignItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAssignItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAssignItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AssignItemContext assignItem() throws RecognitionException {
		AssignItemContext _localctx = new AssignItemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(254);
				ident();
				setState(255);
				match(EQ);
				setState(256);
				expr();
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode STRING_LITERAL() {
			return getToken(MiniZincParser.STRING_LITERAL, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterConstraintItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitConstraintItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitConstraintItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ConstraintItemContext constraintItem() throws RecognitionException {
		ConstraintItemContext _localctx = new ConstraintItemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_constraintItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(258);
				match(T__14);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__15) {
					{
						setState(259);
						match(T__15);
						setState(260);
						match(STRING_LITERAL);
					}
				}

				setState(263);
				expr();
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
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterSolveItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitSolveItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitSolveItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final SolveItemContext solveItem() throws RecognitionException {
		SolveItemContext _localctx = new SolveItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_solveItem);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(265);
				match(T__16);
				setState(266);
				annotations();
				setState(267);
				match(T__17);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(269);
				match(T__16);
				setState(270);
				annotations();
				setState(271);
				match(T__18);
				setState(272);
				expr();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(274);
				match(T__16);
				setState(275);
				annotations();
				setState(276);
				match(T__19);
				setState(277);
				expr();
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
	public static class OutputItemContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode STRING_LITERAL() {
			return getToken(MiniZincParser.STRING_LITERAL, 0);
		}

		public OutputItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_outputItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterOutputItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitOutputItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitOutputItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final OutputItemContext outputItem() throws RecognitionException {
		OutputItemContext _localctx = new OutputItemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_outputItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(281);
				match(T__20);
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__15) {
					{
						setState(282);
						match(T__15);
						setState(283);
						match(STRING_LITERAL);
					}
				}

				setState(286);
				expr();
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
	public static class AnnotationItemContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public ParamsContext params() {
			return getRuleContext(ParamsContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public AnnotationItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annotationItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAnnotationItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAnnotationItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAnnotationItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnotationItemContext annotationItem() throws RecognitionException {
		AnnotationItemContext _localctx = new AnnotationItemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_annotationItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(288);
				match(T__21);
				setState(289);
				ident();
				setState(290);
				params();
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(291);
						match(EQ);
						setState(292);
						expr();
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
	public static class PredicateItemContext extends ParserRuleContext {
		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterPredicateItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitPredicateItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitPredicateItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PredicateItemContext predicateItem() throws RecognitionException {
		PredicateItemContext _localctx = new PredicateItemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_predicateItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(295);
				match(T__22);
				setState(296);
				operationItemTail();
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
	public static class TestItemContext extends ParserRuleContext {
		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class, 0);
		}

		public TestItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_testItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterTestItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitTestItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitTestItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TestItemContext testItem() throws RecognitionException {
		TestItemContext _localctx = new TestItemContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_testItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(298);
				match(T__23);
				setState(299);
				operationItemTail();
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
	public static class FunctionItemContext extends ParserRuleContext {
		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class, 0);
		}

		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class, 0);
		}

		public FunctionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterFunctionItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitFunctionItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitFunctionItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FunctionItemContext functionItem() throws RecognitionException {
		FunctionItemContext _localctx = new FunctionItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_functionItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(301);
				match(T__24);
				setState(302);
				tiExpr();
				setState(303);
				match(T__3);
				setState(304);
				operationItemTail();
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
	public static class OperationItemTailContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public ParamsContext params() {
			return getRuleContext(ParamsContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(MiniZincParser.EQ, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public OperationItemTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_operationItemTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterOperationItemTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitOperationItemTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitOperationItemTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final OperationItemTailContext operationItemTail() throws RecognitionException {
		OperationItemTailContext _localctx = new OperationItemTailContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_operationItemTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(306);
				ident();
				setState(307);
				params();
				setState(308);
				annotations();
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(309);
						match(EQ);
						setState(310);
						expr();
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
	public static class ParamsContext extends ParserRuleContext {
		public List<TiExprAndIdContext> tiExprAndId() {
			return getRuleContexts(TiExprAndIdContext.class);
		}

		public TiExprAndIdContext tiExprAndId(int i) {
			return getRuleContext(TiExprAndIdContext.class, i);
		}

		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_params;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterParams(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitParams(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitParams(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__10) {
					{
						setState(313);
						match(T__10);
						setState(314);
						tiExprAndId();
						setState(319);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(315);
									match(T__7);
									setState(316);
									tiExprAndId();
								}
							}
							setState(321);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(322);
						match(T__11);
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
	public static class TiExprAndIdContext extends ParserRuleContext {
		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TiExprAndIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_tiExprAndId;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterTiExprAndId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitTiExprAndId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitTiExprAndId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TiExprAndIdContext tiExprAndId() throws RecognitionException {
		TiExprAndIdContext _localctx = new TiExprAndIdContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_tiExprAndId);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(326);
				tiExpr();
				setState(327);
				match(T__3);
				setState(328);
				ident();
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
	public static class TiExprContext extends ParserRuleContext {
		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class, 0);
		}

		public ArrayTiExprContext arrayTiExpr() {
			return getRuleContext(ArrayTiExprContext.class, 0);
		}

		public TiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_tiExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterTiExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitTiExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitTiExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TiExprContext tiExpr() throws RecognitionException {
		TiExprContext _localctx = new TiExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_tiExpr);
		try {
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__6:
			case T__10:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__35:
			case T__40:
			case T__41:
			case T__60:
			case T__61:
			case T__67:
			case T__69:
			case T__71:
			case T__76:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case DOLLAR_IDENT:
			case IDENT:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(330);
				baseTiExpr();
			}
				break;
			case T__34:
			case T__38:
				enterOuterAlt(_localctx, 2); {
				setState(331);
				arrayTiExpr();
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
	public static class BaseTiExprContext extends ParserRuleContext {
		public BaseTiExprTailContext baseTiExprTail() {
			return getRuleContext(BaseTiExprTailContext.class, 0);
		}

		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class, 0);
		}

		public TerminalNode DOLLAR_IDENT() {
			return getToken(MiniZincParser.DOLLAR_IDENT, 0);
		}

		public BaseTiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_baseTiExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterBaseTiExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitBaseTiExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitBaseTiExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BaseTiExprContext baseTiExpr() throws RecognitionException {
		BaseTiExprContext _localctx = new BaseTiExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_baseTiExpr);
		int _la;
		try {
			setState(350);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__10:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__35:
			case T__40:
			case T__41:
			case T__60:
			case T__61:
			case T__67:
			case T__69:
			case T__71:
			case T__76:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case DOLLAR_IDENT:
			case IDENT:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__25 || _la == T__26) {
					{
						setState(334);
						_la = _input.LA(1);
						if (!(_la == T__25 || _la == T__26)) {
							_errHandler.recoverInline(this);
						} else {
							if (_input.LA(1) == Token.EOF)
								matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
					}
				}

				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__27) {
					{
						setState(337);
						match(T__27);
					}
				}

				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__28) {
					{
						setState(340);
						match(T__28);
					}
				}

				setState(343);
				baseTiExprTail();
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__5) {
					{
						setState(344);
						match(T__5);
						setState(345);
						baseTiExpr();
					}
				}

			}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2); {
				setState(348);
				match(T__2);
				setState(349);
				match(DOLLAR_IDENT);
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
	public static class BaseTiExprTailContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class, 0);
		}

		public TerminalNode DOLLAR_IDENT() {
			return getToken(MiniZincParser.DOLLAR_IDENT, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public BaseTiExprTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_baseTiExprTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterBaseTiExprTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitBaseTiExprTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitBaseTiExprTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BaseTiExprTailContext baseTiExprTail() throws RecognitionException {
		BaseTiExprTailContext _localctx = new BaseTiExprTailContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_baseTiExprTail);
		try {
			setState(357);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 22, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(352);
				ident();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(353);
				baseType();
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(354);
				match(DOLLAR_IDENT);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(355);
				match(T__29);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(356);
				expr();
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
	public static class BaseTypeContext extends ParserRuleContext {
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_baseType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterBaseType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitBaseType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitBaseType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(359);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0))) {
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
	public static class ArrayTiExprContext extends ParserRuleContext {
		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class, 0);
		}

		public List<TiExprContext> tiExpr() {
			return getRuleContexts(TiExprContext.class);
		}

		public TiExprContext tiExpr(int i) {
			return getRuleContext(TiExprContext.class, i);
		}

		public ArrayTiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayTiExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayTiExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayTiExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayTiExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayTiExprContext arrayTiExpr() throws RecognitionException {
		ArrayTiExprContext _localctx = new ArrayTiExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arrayTiExpr);
		int _la;
		try {
			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__34:
				enterOuterAlt(_localctx, 1); {
				setState(361);
				match(T__34);
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__35) {
					{
						setState(362);
						match(T__35);
						setState(363);
						tiExpr();
						setState(368);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(364);
									match(T__7);
									setState(365);
									tiExpr();
								}
							}
							setState(370);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(371);
						match(T__36);
					}
				}

				setState(375);
				match(T__37);
				setState(376);
				baseTiExpr();
			}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2); {
				setState(377);
				match(T__38);
				setState(378);
				match(T__37);
				setState(379);
				baseTiExpr();
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
	public static class ArrayAccessTailContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public ArrayAccessTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayAccessTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayAccessTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayAccessTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayAccessTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayAccessTailContext arrayAccessTail() throws RecognitionException {
		ArrayAccessTailContext _localctx = new ArrayAccessTailContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arrayAccessTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(382);
				match(T__35);
				setState(383);
				expr();
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(384);
							match(T__7);
							setState(385);
							expr();
						}
					}
					setState(390);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(391);
				match(T__36);
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
	public static class FieldAccessTailContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public TerminalNode INT_LITERAL() {
			return getToken(MiniZincParser.INT_LITERAL, 0);
		}

		public FieldAccessTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_fieldAccessTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterFieldAccessTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitFieldAccessTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitFieldAccessTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FieldAccessTailContext fieldAccessTail() throws RecognitionException {
		FieldAccessTailContext _localctx = new FieldAccessTailContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fieldAccessTail);
		try {
			setState(397);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(393);
				match(T__39);
				setState(394);
				ident();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(395);
				match(T__39);
				setState(396);
				match(INT_LITERAL);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAnnotations(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAnnotations(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__15) {
					{
						{
							setState(399);
							match(T__15);
							setState(400);
							annotation();
						}
					}
					setState(405);
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
		public ExprAtomHeadContext exprAtomHead() {
			return getRuleContext(ExprAtomHeadContext.class, 0);
		}

		public ExprAtomTailContext exprAtomTail() {
			return getRuleContext(ExprAtomTailContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAnnotation(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAnnotation(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAnnotation(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(406);
				exprAtomHead();
				setState(407);
				exprAtomTail();
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
	public static class ExprAtomHeadContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public CallSuffixContext callSuffix() {
			return getRuleContext(CallSuffixContext.class, 0);
		}

		public TerminalNode INT_LITERAL() {
			return getToken(MiniZincParser.INT_LITERAL, 0);
		}

		public TerminalNode FLOAT_LITERAL() {
			return getToken(MiniZincParser.FLOAT_LITERAL, 0);
		}

		public TerminalNode STRING_LITERAL() {
			return getToken(MiniZincParser.STRING_LITERAL, 0);
		}

		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class, 0);
		}

		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class, 0);
		}

		public ArrayLiteral2dContext arrayLiteral2d() {
			return getRuleContext(ArrayLiteral2dContext.class, 0);
		}

		public IndexedArrayLiteralContext indexedArrayLiteral() {
			return getRuleContext(IndexedArrayLiteralContext.class, 0);
		}

		public IndexedArrayLiteral2dContext indexedArrayLiteral2d() {
			return getRuleContext(IndexedArrayLiteral2dContext.class, 0);
		}

		public TupleLiteralContext tupleLiteral() {
			return getRuleContext(TupleLiteralContext.class, 0);
		}

		public RecordLiteralContext recordLiteral() {
			return getRuleContext(RecordLiteralContext.class, 0);
		}

		public ArrayCompContext arrayComp() {
			return getRuleContext(ArrayCompContext.class, 0);
		}

		public IndexedArrayCompContext indexedArrayComp() {
			return getRuleContext(IndexedArrayCompContext.class, 0);
		}

		public AnnLiteralContext annLiteral() {
			return getRuleContext(AnnLiteralContext.class, 0);
		}

		public IfThenElseExprContext ifThenElseExpr() {
			return getRuleContext(IfThenElseExprContext.class, 0);
		}

		public LetExprContext letExpr() {
			return getRuleContext(LetExprContext.class, 0);
		}

		public CallExprContext callExpr() {
			return getRuleContext(CallExprContext.class, 0);
		}

		public GenCallExprContext genCallExpr() {
			return getRuleContext(GenCallExprContext.class, 0);
		}

		public ExprAtomHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_exprAtomHead;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterExprAtomHead(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitExprAtomHead(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitExprAtomHead(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExprAtomHeadContext exprAtomHead() throws RecognitionException {
		ExprAtomHeadContext _localctx = new ExprAtomHeadContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_exprAtomHead);
		int _la;
		try {
			setState(437);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 30, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(409);
				match(T__10);
				setState(410);
				expr();
				setState(411);
				match(T__11);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(413);
				ident();
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__10) {
					{
						setState(414);
						callSuffix();
					}
				}

			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(417);
				match(T__9);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(418);
				match(T__40);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(419);
				match(T__41);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(420);
				match(INT_LITERAL);
			}
				break;
			case 7:
				enterOuterAlt(_localctx, 7); {
				setState(421);
				match(FLOAT_LITERAL);
			}
				break;
			case 8:
				enterOuterAlt(_localctx, 8); {
				setState(422);
				match(STRING_LITERAL);
			}
				break;
			case 9:
				enterOuterAlt(_localctx, 9); {
				setState(423);
				setLiteral();
			}
				break;
			case 10:
				enterOuterAlt(_localctx, 10); {
				setState(424);
				arrayLiteral();
			}
				break;
			case 11:
				enterOuterAlt(_localctx, 11); {
				setState(425);
				arrayLiteral2d();
			}
				break;
			case 12:
				enterOuterAlt(_localctx, 12); {
				setState(426);
				indexedArrayLiteral();
			}
				break;
			case 13:
				enterOuterAlt(_localctx, 13); {
				setState(427);
				indexedArrayLiteral2d();
			}
				break;
			case 14:
				enterOuterAlt(_localctx, 14); {
				setState(428);
				tupleLiteral();
			}
				break;
			case 15:
				enterOuterAlt(_localctx, 15); {
				setState(429);
				recordLiteral();
			}
				break;
			case 16:
				enterOuterAlt(_localctx, 16); {
				setState(430);
				arrayComp();
			}
				break;
			case 17:
				enterOuterAlt(_localctx, 17); {
				setState(431);
				indexedArrayComp();
			}
				break;
			case 18:
				enterOuterAlt(_localctx, 18); {
				setState(432);
				annLiteral();
			}
				break;
			case 19:
				enterOuterAlt(_localctx, 19); {
				setState(433);
				ifThenElseExpr();
			}
				break;
			case 20:
				enterOuterAlt(_localctx, 20); {
				setState(434);
				letExpr();
			}
				break;
			case 21:
				enterOuterAlt(_localctx, 21); {
				setState(435);
				callExpr();
			}
				break;
			case 22:
				enterOuterAlt(_localctx, 22); {
				setState(436);
				genCallExpr();
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
	public static class AnnLiteralContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public AnnLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAnnLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAnnLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAnnLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AnnLiteralContext annLiteral() throws RecognitionException {
		AnnLiteralContext _localctx = new AnnLiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_annLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(439);
				ident();
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__10) {
					{
						setState(440);
						match(T__10);
						setState(441);
						expr();
						setState(446);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(442);
									match(T__7);
									setState(443);
									expr();
								}
							}
							setState(448);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(449);
						match(T__11);
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
	public static class ExprAtomTailContext extends ParserRuleContext {
		public ArrayAccessTailContext arrayAccessTail() {
			return getRuleContext(ArrayAccessTailContext.class, 0);
		}

		public ExprAtomTailContext exprAtomTail() {
			return getRuleContext(ExprAtomTailContext.class, 0);
		}

		public FieldAccessTailContext fieldAccessTail() {
			return getRuleContext(FieldAccessTailContext.class, 0);
		}

		public ExprAtomTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_exprAtomTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterExprAtomTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitExprAtomTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitExprAtomTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExprAtomTailContext exprAtomTail() throws RecognitionException {
		ExprAtomTailContext _localctx = new ExprAtomTailContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_exprAtomTail);
		try {
			setState(460);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__8:
			case T__15:
			case T__17:
			case T__18:
			case T__19:
			case EQ:
				enterOuterAlt(_localctx, 1); {
			}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2); {
				setState(454);
				arrayAccessTail();
				setState(455);
				exprAtomTail();
			}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3); {
				setState(457);
				fieldAccessTail();
				setState(458);
				exprAtomTail();
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
		public IffExprContext iffExpr() {
			return getRuleContext(IffExprContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(462);
				iffExpr();
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
	public static class IffExprContext extends ParserRuleContext {
		public List<ImplExprContext> implExpr() {
			return getRuleContexts(ImplExprContext.class);
		}

		public ImplExprContext implExpr(int i) {
			return getRuleContext(ImplExprContext.class, i);
		}

		public IffExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_iffExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIffExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIffExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIffExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IffExprContext iffExpr() throws RecognitionException {
		IffExprContext _localctx = new IffExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_iffExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(464);
				implExpr();
				setState(469);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 34, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(465);
								match(T__42);
								setState(466);
								implExpr();
							}
						}
					}
					setState(471);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 34, _ctx);
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
	public static class ImplExprContext extends ParserRuleContext {
		public List<OrExprContext> orExpr() {
			return getRuleContexts(OrExprContext.class);
		}

		public OrExprContext orExpr(int i) {
			return getRuleContext(OrExprContext.class, i);
		}

		public ImplExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_implExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterImplExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitImplExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitImplExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ImplExprContext implExpr() throws RecognitionException {
		ImplExprContext _localctx = new ImplExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_implExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(472);
				orExpr();
				setState(477);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(473);
								match(T__43);
								setState(474);
								orExpr();
							}
						}
					}
					setState(479);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
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
	public static class OrExprContext extends ParserRuleContext {
		public List<XorExprContext> xorExpr() {
			return getRuleContexts(XorExprContext.class);
		}

		public XorExprContext xorExpr(int i) {
			return getRuleContext(XorExprContext.class, i);
		}

		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_orExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterOrExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitOrExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitOrExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_orExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(480);
				xorExpr();
				setState(485);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 36, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(481);
								match(T__44);
								setState(482);
								xorExpr();
							}
						}
					}
					setState(487);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 36, _ctx);
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
	public static class XorExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}

		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class, i);
		}

		public XorExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_xorExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterXorExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitXorExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitXorExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final XorExprContext xorExpr() throws RecognitionException {
		XorExprContext _localctx = new XorExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_xorExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(488);
				andExpr();
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 37, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(489);
								match(T__45);
								setState(490);
								andExpr();
							}
						}
					}
					setState(495);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 37, _ctx);
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
	public static class AndExprContext extends ParserRuleContext {
		public List<CompareExprContext> compareExpr() {
			return getRuleContexts(CompareExprContext.class);
		}

		public CompareExprContext compareExpr(int i) {
			return getRuleContext(CompareExprContext.class, i);
		}

		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_andExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAndExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAndExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAndExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_andExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(496);
				compareExpr();
				setState(501);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 38, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(497);
								match(T__46);
								setState(498);
								compareExpr();
							}
						}
					}
					setState(503);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 38, _ctx);
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
	public static class CompareExprContext extends ParserRuleContext {
		public List<SetExprContext> setExpr() {
			return getRuleContexts(SetExprContext.class);
		}

		public SetExprContext setExpr(int i) {
			return getRuleContext(SetExprContext.class, i);
		}

		public List<TerminalNode> EQ() {
			return getTokens(MiniZincParser.EQ);
		}

		public TerminalNode EQ(int i) {
			return getToken(MiniZincParser.EQ, i);
		}

		public CompareExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_compareExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterCompareExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitCompareExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitCompareExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final CompareExprContext compareExpr() throws RecognitionException {
		CompareExprContext _localctx = new CompareExprContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_compareExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(504);
				setExpr();
				setState(509);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 39, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(505);
								_la = _input.LA(1);
								if (!(((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & 2147483903L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(506);
								setExpr();
							}
						}
					}
					setState(511);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 39, _ctx);
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
	public static class SetExprContext extends ParserRuleContext {
		public List<RangeExprContext> rangeExpr() {
			return getRuleContexts(RangeExprContext.class);
		}

		public RangeExprContext rangeExpr(int i) {
			return getRuleContext(RangeExprContext.class, i);
		}

		public SetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_setExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterSetExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitSetExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitSetExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final SetExprContext setExpr() throws RecognitionException {
		SetExprContext _localctx = new SetExprContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_setExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(512);
				rangeExpr();
				setState(517);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 40, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(513);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 1080863910568919040L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(514);
								rangeExpr();
							}
						}
					}
					setState(519);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 40, _ctx);
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
	public static class RangeExprContext extends ParserRuleContext {
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}

		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class, i);
		}

		public RangeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_rangeExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterRangeExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitRangeExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitRangeExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final RangeExprContext rangeExpr() throws RecognitionException {
		RangeExprContext _localctx = new RangeExprContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_rangeExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(520);
				addExpr();
				setState(525);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 41, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(521);
								match(T__59);
								setState(522);
								addExpr();
							}
						}
					}
					setState(527);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 41, _ctx);
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
	public static class AddExprContext extends ParserRuleContext {
		public List<MultExprContext> multExpr() {
			return getRuleContexts(MultExprContext.class);
		}

		public MultExprContext multExpr(int i) {
			return getRuleContext(MultExprContext.class, i);
		}

		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_addExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterAddExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitAddExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitAddExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_addExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(528);
				multExpr();
				setState(533);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 42, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(529);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917529027641081920L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(530);
								multExpr();
							}
						}
					}
					setState(535);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 42, _ctx);
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
	public static class MultExprContext extends ParserRuleContext {
		public List<PowExprContext> powExpr() {
			return getRuleContexts(PowExprContext.class);
		}

		public PowExprContext powExpr(int i) {
			return getRuleContext(PowExprContext.class, i);
		}

		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_multExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterMultExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitMultExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitMultExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final MultExprContext multExpr() throws RecognitionException {
		MultExprContext _localctx = new MultExprContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_multExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(536);
				powExpr();
				setState(541);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 43, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(537);
								_la = _input.LA(1);
								if (!(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & 15L) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									if (_input.LA(1) == Token.EOF)
										matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								setState(538);
								powExpr();
							}
						}
					}
					setState(543);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 43, _ctx);
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
	public static class PowExprContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}

		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class, i);
		}

		public PowExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_powExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterPowExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitPowExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitPowExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PowExprContext powExpr() throws RecognitionException {
		PowExprContext _localctx = new PowExprContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_powExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(544);
				unaryExpr();
				setState(549);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 44, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(545);
								match(T__66);
								setState(546);
								unaryExpr();
							}
						}
					}
					setState(551);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 44, _ctx);
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
	public static class UnaryExprContext extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class, 0);
		}

		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class, 0);
		}

		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_unaryExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterUnaryExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitUnaryExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitUnaryExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unaryExpr);
		int _la;
		try {
			setState(555);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
			case T__61:
			case T__67:
				enterOuterAlt(_localctx, 1); {
				setState(552);
				_la = _input.LA(1);
				if (!(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 131L) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF)
						matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(553);
				unaryExpr();
			}
				break;
			case T__6:
			case T__10:
			case T__35:
			case T__40:
			case T__41:
			case T__69:
			case T__71:
			case T__76:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case IDENT:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2); {
				setState(554);
				primary();
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
	public static class PrimaryContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class, 0);
		}

		public List<PostfixContext> postfix() {
			return getRuleContexts(PostfixContext.class);
		}

		public PostfixContext postfix(int i) {
			return getRuleContext(PostfixContext.class, i);
		}

		public QuantifierExprContext quantifierExpr() {
			return getRuleContext(QuantifierExprContext.class, 0);
		}

		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public IfThenElseExprContext ifThenElseExpr() {
			return getRuleContext(IfThenElseExprContext.class, 0);
		}

		public LetExprContext letExpr() {
			return getRuleContext(LetExprContext.class, 0);
		}

		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_primary;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterPrimary(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitPrimary(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitPrimary(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_primary);
		try {
			int _alt;
			setState(601);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 52, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(557);
				literal();
				setState(561);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 46, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(558);
								postfix();
							}
						}
					}
					setState(563);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 46, _ctx);
				}
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(564);
				quantifierExpr();
				setState(568);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 47, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(565);
								postfix();
							}
						}
					}
					setState(570);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 47, _ctx);
				}
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(571);
				ident();
				setState(575);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 48, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(572);
								postfix();
							}
						}
					}
					setState(577);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 48, _ctx);
				}
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(578);
				match(T__10);
				setState(579);
				expr();
				setState(580);
				match(T__11);
				setState(584);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 49, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(581);
								postfix();
							}
						}
					}
					setState(586);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 49, _ctx);
				}
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(587);
				ifThenElseExpr();
				setState(591);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 50, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(588);
								postfix();
							}
						}
					}
					setState(593);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 50, _ctx);
				}
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(594);
				letExpr();
				setState(598);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 51, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(595);
								postfix();
							}
						}
					}
					setState(600);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 51, _ctx);
				}
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
	public static class PostfixContext extends ParserRuleContext {
		public CallSuffixContext callSuffix() {
			return getRuleContext(CallSuffixContext.class, 0);
		}

		public ArrayAccessTailContext arrayAccessTail() {
			return getRuleContext(ArrayAccessTailContext.class, 0);
		}

		public FieldAccessTailContext fieldAccessTail() {
			return getRuleContext(FieldAccessTailContext.class, 0);
		}

		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_postfix;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterPostfix(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitPostfix(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitPostfix(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_postfix);
		try {
			setState(606);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1); {
				setState(603);
				callSuffix();
			}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2); {
				setState(604);
				arrayAccessTail();
			}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3); {
				setState(605);
				fieldAccessTail();
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
	public static class QuantifierExprContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public GeneratorListContext generatorList() {
			return getRuleContext(GeneratorListContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public QuantifierExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_quantifierExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterQuantifierExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitQuantifierExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitQuantifierExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final QuantifierExprContext quantifierExpr() throws RecognitionException {
		QuantifierExprContext _localctx = new QuantifierExprContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_quantifierExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(608);
				ident();
				setState(609);
				match(T__10);
				setState(610);
				generatorList();
				setState(611);
				match(T__11);
				setState(612);
				match(T__10);
				setState(613);
				expr();
				setState(614);
				match(T__11);
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
	public static class CallSuffixContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public CallSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_callSuffix;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterCallSuffix(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitCallSuffix(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitCallSuffix(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final CallSuffixContext callSuffix() throws RecognitionException {
		CallSuffixContext _localctx = new CallSuffixContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_callSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(616);
				match(T__10);
				setState(625);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(617);
						expr();
						setState(622);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(618);
									match(T__7);
									setState(619);
									expr();
								}
							}
							setState(624);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(627);
				match(T__11);
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
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() {
			return getToken(MiniZincParser.INT_LITERAL, 0);
		}

		public TerminalNode FLOAT_LITERAL() {
			return getToken(MiniZincParser.FLOAT_LITERAL, 0);
		}

		public TerminalNode STRING_LITERAL() {
			return getToken(MiniZincParser.STRING_LITERAL, 0);
		}

		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class, 0);
		}

		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class, 0);
		}

		public ArrayLiteral2dContext arrayLiteral2d() {
			return getRuleContext(ArrayLiteral2dContext.class, 0);
		}

		public TupleLiteralContext tupleLiteral() {
			return getRuleContext(TupleLiteralContext.class, 0);
		}

		public RecordLiteralContext recordLiteral() {
			return getRuleContext(RecordLiteralContext.class, 0);
		}

		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_literal;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_literal);
		try {
			setState(639);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 56, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(629);
				match(T__40);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(630);
				match(T__41);
			}
				break;
			case 3:
				enterOuterAlt(_localctx, 3); {
				setState(631);
				match(INT_LITERAL);
			}
				break;
			case 4:
				enterOuterAlt(_localctx, 4); {
				setState(632);
				match(FLOAT_LITERAL);
			}
				break;
			case 5:
				enterOuterAlt(_localctx, 5); {
				setState(633);
				match(STRING_LITERAL);
			}
				break;
			case 6:
				enterOuterAlt(_localctx, 6); {
				setState(634);
				setLiteral();
			}
				break;
			case 7:
				enterOuterAlt(_localctx, 7); {
				setState(635);
				arrayLiteral();
			}
				break;
			case 8:
				enterOuterAlt(_localctx, 8); {
				setState(636);
				arrayLiteral2d();
			}
				break;
			case 9:
				enterOuterAlt(_localctx, 9); {
				setState(637);
				tupleLiteral();
			}
				break;
			case 10:
				enterOuterAlt(_localctx, 10); {
				setState(638);
				recordLiteral();
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterSetLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitSetLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitSetLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final SetLiteralContext setLiteral() throws RecognitionException {
		SetLiteralContext _localctx = new SetLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_setLiteral);
		int _la;
		try {
			setState(659);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 59, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(641);
				match(T__6);
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(642);
						expr();
						setState(647);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(643);
									match(T__7);
									setState(644);
									expr();
								}
							}
							setState(649);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(652);
				match(T__8);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(653);
				match(T__6);
				setState(654);
				expr();
				setState(655);
				match(T__68);
				setState(656);
				compTail();
				setState(657);
				match(T__8);
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class, 0);
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
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_arrayLiteral);
		int _la;
		try {
			int _alt;
			setState(682);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 63, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(661);
				match(T__35);
				setState(670);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(662);
						expr();
						setState(667);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 60, _ctx);
						while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
							if (_alt == 1) {
								{
									{
										setState(663);
										match(T__7);
										setState(664);
										expr();
									}
								}
							}
							setState(669);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input, 60, _ctx);
						}
					}
				}

				setState(673);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__7) {
					{
						setState(672);
						match(T__7);
					}
				}

				setState(675);
				match(T__36);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(676);
				match(T__35);
				setState(677);
				expr();
				setState(678);
				match(T__68);
				setState(679);
				compTail();
				setState(680);
				match(T__36);
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
	public static class ArrayLiteral2dContext extends ParserRuleContext {
		public List<ArrayRowContext> arrayRow() {
			return getRuleContexts(ArrayRowContext.class);
		}

		public ArrayRowContext arrayRow(int i) {
			return getRuleContext(ArrayRowContext.class, i);
		}

		public ArrayLiteral2dContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayLiteral2d;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayLiteral2d(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayLiteral2d(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayLiteral2d(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteral2dContext arrayLiteral2d() throws RecognitionException {
		ArrayLiteral2dContext _localctx = new ArrayLiteral2dContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_arrayLiteral2d);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(684);
				match(T__69);
				setState(693);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(685);
						arrayRow();
						setState(690);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__68) {
							{
								{
									setState(686);
									match(T__68);
									setState(687);
									arrayRow();
								}
							}
							setState(692);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(695);
				match(T__70);
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
	public static class ArrayRowContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public ArrayRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayRow;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayRow(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayRow(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayRow(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayRowContext arrayRow() throws RecognitionException {
		ArrayRowContext _localctx = new ArrayRowContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_arrayRow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(697);
				expr();
				setState(702);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(698);
							match(T__7);
							setState(699);
							expr();
						}
					}
					setState(704);
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
	public static class TupleLiteralContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public TupleLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_tupleLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterTupleLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitTupleLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitTupleLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TupleLiteralContext tupleLiteral() throws RecognitionException {
		TupleLiteralContext _localctx = new TupleLiteralContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_tupleLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(705);
				match(T__10);
				setState(706);
				expr();
				setState(707);
				match(T__7);
				setState(708);
				expr();
				setState(713);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(709);
							match(T__7);
							setState(710);
							expr();
						}
					}
					setState(715);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(716);
				match(T__11);
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
	public static class RecordLiteralContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}

		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class, i);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public RecordLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_recordLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterRecordLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitRecordLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitRecordLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final RecordLiteralContext recordLiteral() throws RecognitionException {
		RecordLiteralContext _localctx = new RecordLiteralContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_recordLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(718);
				match(T__10);
				setState(719);
				ident();
				setState(720);
				match(T__3);
				setState(721);
				expr();
				setState(722);
				match(T__7);
				setState(723);
				ident();
				setState(724);
				match(T__3);
				setState(725);
				expr();
				setState(733);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(726);
							match(T__7);
							setState(727);
							ident();
							setState(728);
							match(T__3);
							setState(729);
							expr();
						}
					}
					setState(735);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(736);
				match(T__11);
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
	public static class IfThenElseExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public IfThenElseExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ifThenElseExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIfThenElseExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIfThenElseExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIfThenElseExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IfThenElseExprContext ifThenElseExpr() throws RecognitionException {
		IfThenElseExprContext _localctx = new IfThenElseExprContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_ifThenElseExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(738);
				match(T__71);
				setState(739);
				expr();
				setState(740);
				match(T__72);
				setState(741);
				expr();
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__73) {
					{
						{
							setState(742);
							match(T__73);
							setState(743);
							expr();
							setState(744);
							match(T__72);
							setState(745);
							expr();
						}
					}
					setState(751);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(754);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__74) {
					{
						setState(752);
						match(T__74);
						setState(753);
						expr();
					}
				}

				setState(756);
				match(T__75);
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
	public static class LetExprContext extends ParserRuleContext {
		public List<LetItemContext> letItem() {
			return getRuleContexts(LetItemContext.class);
		}

		public LetItemContext letItem(int i) {
			return getRuleContext(LetItemContext.class, i);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public LetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_letExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterLetExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitLetExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitLetExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final LetExprContext letExpr() throws RecognitionException {
		LetExprContext _localctx = new LetExprContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_letExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(758);
				match(T__76);
				setState(759);
				match(T__6);
				setState(760);
				letItem();
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__0) {
					{
						{
							setState(761);
							match(T__0);
							setState(762);
							letItem();
						}
					}
					setState(767);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(768);
				match(T__8);
				setState(769);
				match(T__52);
				setState(770);
				expr();
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
	public static class LetItemContext extends ParserRuleContext {
		public VarDeclItemContext varDeclItem() {
			return getRuleContext(VarDeclItemContext.class, 0);
		}

		public ConstraintItemContext constraintItem() {
			return getRuleContext(ConstraintItemContext.class, 0);
		}

		public LetItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_letItem;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterLetItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitLetItem(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitLetItem(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final LetItemContext letItem() throws RecognitionException {
		LetItemContext _localctx = new LetItemContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_letItem);
		try {
			setState(774);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__6:
			case T__10:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__38:
			case T__40:
			case T__41:
			case T__60:
			case T__61:
			case T__67:
			case T__69:
			case T__71:
			case T__76:
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case DOLLAR_IDENT:
			case IDENT:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1); {
				setState(772);
				varDeclItem();
			}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2); {
				setState(773);
				constraintItem();
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
	public static class CompTailContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}

		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class, i);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public CompTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_compTail;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterCompTail(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitCompTail(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitCompTail(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final CompTailContext compTail() throws RecognitionException {
		CompTailContext _localctx = new CompTailContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_compTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(776);
				generator();
				setState(779);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__77) {
					{
						setState(777);
						match(T__77);
						setState(778);
						expr();
					}
				}

				setState(785);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(781);
							match(T__7);
							setState(782);
							generator();
						}
					}
					setState(787);
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
	public static class GeneratorListContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}

		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class, i);
		}

		public GeneratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_generatorList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterGeneratorList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitGeneratorList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitGeneratorList(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final GeneratorListContext generatorList() throws RecognitionException {
		GeneratorListContext _localctx = new GeneratorListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_generatorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(788);
				generator();
				setState(793);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(789);
							match(T__7);
							setState(790);
							generator();
						}
					}
					setState(795);
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
	public static class GeneratorContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}

		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class, i);
		}

		public GeneratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_generator;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterGenerator(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitGenerator(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitGenerator(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final GeneratorContext generator() throws RecognitionException {
		GeneratorContext _localctx = new GeneratorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_generator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(798);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENT: {
					setState(796);
					ident();
				}
					break;
				case T__9: {
					setState(797);
					match(T__9);
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(807);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(800);
							match(T__7);
							setState(803);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case IDENT: {
								setState(801);
								ident();
							}
								break;
							case T__9: {
								setState(802);
								match(T__9);
							}
								break;
							default:
								throw new NoViableAltException(this);
							}
						}
					}
					setState(809);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(810);
				match(T__52);
				setState(811);
				expr();
				setState(814);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 79, _ctx)) {
				case 1: {
					setState(812);
					match(T__77);
					setState(813);
					expr();
				}
					break;
				}
				setState(817);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 80, _ctx)) {
				case 1: {
					setState(816);
					match(T__7);
				}
					break;
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
	public static class IndexedArrayLiteralContext extends ParserRuleContext {
		public List<IndexTupleContext> indexTuple() {
			return getRuleContexts(IndexTupleContext.class);
		}

		public IndexTupleContext indexTuple(int i) {
			return getRuleContext(IndexTupleContext.class, i);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public IndexedArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexedArrayLiteral;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIndexedArrayLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIndexedArrayLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIndexedArrayLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayLiteralContext indexedArrayLiteral() throws RecognitionException {
		IndexedArrayLiteralContext _localctx = new IndexedArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_indexedArrayLiteral);
		int _la;
		try {
			setState(849);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 85, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(819);
				match(T__35);
				setState(833);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(820);
						indexTuple();
						setState(821);
						match(T__3);
						setState(822);
						expr();
						setState(830);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(823);
									match(T__7);
									setState(824);
									indexTuple();
									setState(825);
									match(T__3);
									setState(826);
									expr();
								}
							}
							setState(832);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(835);
				match(T__36);
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(836);
				match(T__35);
				setState(846);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(837);
						indexTuple();
						setState(838);
						match(T__3);
						setState(839);
						expr();
						setState(842);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(840);
									match(T__7);
									setState(841);
									expr();
								}
							}
							setState(844);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == T__7);
					}
				}

				setState(848);
				match(T__36);
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
	public static class IndexedArrayLiteral2dContext extends ParserRuleContext {
		public List<IndexedArrayRowContext> indexedArrayRow() {
			return getRuleContexts(IndexedArrayRowContext.class);
		}

		public IndexedArrayRowContext indexedArrayRow(int i) {
			return getRuleContext(IndexedArrayRowContext.class, i);
		}

		public IndexedArrayLiteral2dContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexedArrayLiteral2d;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIndexedArrayLiteral2d(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIndexedArrayLiteral2d(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIndexedArrayLiteral2d(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayLiteral2dContext indexedArrayLiteral2d() throws RecognitionException {
		IndexedArrayLiteral2dContext _localctx = new IndexedArrayLiteral2dContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_indexedArrayLiteral2d);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(851);
				match(T__69);
				setState(860);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(852);
						indexedArrayRow();
						setState(857);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__68) {
							{
								{
									setState(853);
									match(T__68);
									setState(854);
									indexedArrayRow();
								}
							}
							setState(859);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(862);
				match(T__70);
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
	public static class IndexedArrayRowContext extends ParserRuleContext {
		public List<IndexTupleContext> indexTuple() {
			return getRuleContexts(IndexTupleContext.class);
		}

		public IndexTupleContext indexTuple(int i) {
			return getRuleContext(IndexTupleContext.class, i);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public IndexedArrayRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexedArrayRow;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIndexedArrayRow(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIndexedArrayRow(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIndexedArrayRow(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayRowContext indexedArrayRow() throws RecognitionException {
		IndexedArrayRowContext _localctx = new IndexedArrayRowContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_indexedArrayRow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				{
					setState(864);
					indexTuple();
					setState(865);
					match(T__3);
					setState(866);
					expr();
				}
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(868);
							match(T__7);
							{
								setState(869);
								indexTuple();
								setState(870);
								match(T__3);
								setState(871);
								expr();
							}
						}
					}
					setState(877);
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
	public static class IndexTupleContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public IndexTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexTuple;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIndexTuple(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIndexTuple(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIndexTuple(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexTupleContext indexTuple() throws RecognitionException {
		IndexTupleContext _localctx = new IndexTupleContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_indexTuple);
		int _la;
		try {
			setState(890);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 90, _ctx)) {
			case 1:
				enterOuterAlt(_localctx, 1); {
				setState(878);
				expr();
			}
				break;
			case 2:
				enterOuterAlt(_localctx, 2); {
				setState(879);
				match(T__10);
				setState(880);
				expr();
				setState(885);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7) {
					{
						{
							setState(881);
							match(T__7);
							setState(882);
							expr();
						}
					}
					setState(887);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(888);
				match(T__11);
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
	public static class ArrayCompContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class, 0);
		}

		public ArrayCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arrayComp;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterArrayComp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitArrayComp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitArrayComp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ArrayCompContext arrayComp() throws RecognitionException {
		ArrayCompContext _localctx = new ArrayCompContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_arrayComp);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(892);
				match(T__35);
				setState(893);
				expr();
				setState(894);
				match(T__68);
				setState(895);
				compTail();
				setState(896);
				match(T__36);
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
	public static class IndexedArrayCompContext extends ParserRuleContext {
		public IndexTupleContext indexTuple() {
			return getRuleContext(IndexTupleContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class, 0);
		}

		public IndexedArrayCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_indexedArrayComp;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIndexedArrayComp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIndexedArrayComp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIndexedArrayComp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayCompContext indexedArrayComp() throws RecognitionException {
		IndexedArrayCompContext _localctx = new IndexedArrayCompContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_indexedArrayComp);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(898);
				match(T__35);
				setState(899);
				indexTuple();
				setState(900);
				match(T__3);
				setState(901);
				expr();
				setState(902);
				match(T__68);
				setState(903);
				compTail();
				setState(904);
				match(T__36);
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
	public static class CallExprContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public CallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_callExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterCallExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitCallExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitCallExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final CallExprContext callExpr() throws RecognitionException {
		CallExprContext _localctx = new CallExprContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_callExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(906);
				ident();
				setState(907);
				match(T__10);
				setState(916);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0)
					|| ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
						setState(908);
						expr();
						setState(913);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == T__7) {
							{
								{
									setState(909);
									match(T__7);
									setState(910);
									expr();
								}
							}
							setState(915);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(918);
				match(T__11);
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
	public static class GenCallExprContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class, 0);
		}

		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class, 0);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public GenCallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_genCallExpr;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterGenCallExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitGenCallExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitGenCallExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final GenCallExprContext genCallExpr() throws RecognitionException {
		GenCallExprContext _localctx = new GenCallExprContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_genCallExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(920);
				ident();
				setState(921);
				match(T__10);
				setState(922);
				compTail();
				setState(923);
				match(T__11);
				setState(924);
				match(T__10);
				setState(925);
				expr();
				setState(926);
				match(T__11);
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
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() {
			return getToken(MiniZincParser.IDENT, 0);
		}

		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ident;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).enterIdent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof MiniZincListener)
				((MiniZincListener) listener).exitIdent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MiniZincVisitor)
				return ((MiniZincVisitor<? extends T>) visitor).visitIdent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(928);
				match(IDENT);
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

	public static final String _serializedATN = "\u0004\u0001V\u03a3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002" +
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002" +
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002" +
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002" +
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002" +
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002" +
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0001" +
		"\u0000\u0005\u0000\u008e\b\u0000\n\u0000\f\u0000\u0091\t\u0000\u0001\u0000" +
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
		"\u0001\u0001\u0003\u0001\u00b9\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002" +
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00c2\b\u0003" +
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003" +
		"\u0003\u0003\u00ca\b\u0003\u0003\u0003\u00cc\b\u0003\u0001\u0004\u0001" +
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00d3\b\u0004\u0001" +
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00d8\b\u0005\n\u0005\f\u0005" +
		"\u00db\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006" +
		"\u00e1\b\u0006\n\u0006\f\u0006\u00e4\t\u0006\u0001\u0006\u0001\u0006\u0001" +
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001" +
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001" +
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00f7\b\u0006\u0001\u0007\u0001" +
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b" +
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0003\t\u0106\b\t\u0001\t\u0001" +
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0118\b\n\u0001\u000b" +
		"\u0001\u000b\u0001\u000b\u0003\u000b\u011d\b\u000b\u0001\u000b\u0001\u000b" +
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0126\b\f\u0001\r\u0001" +
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f" +
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010" +
		"\u0001\u0010\u0001\u0010\u0003\u0010\u0138\b\u0010\u0001\u0011\u0001\u0011" +
		"\u0001\u0011\u0001\u0011\u0005\u0011\u013e\b\u0011\n\u0011\f\u0011\u0141" +
		"\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0145\b\u0011\u0001\u0012" +
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0003\u0013" +
		"\u014d\b\u0013\u0001\u0014\u0003\u0014\u0150\b\u0014\u0001\u0014\u0003" +
		"\u0014\u0153\b\u0014\u0001\u0014\u0003\u0014\u0156\b\u0014\u0001\u0014" +
		"\u0001\u0014\u0001\u0014\u0003\u0014\u015b\b\u0014\u0001\u0014\u0001\u0014" +
		"\u0003\u0014\u015f\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015" +
		"\u0001\u0015\u0003\u0015\u0166\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017" +
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u016f\b\u0017" +
		"\n\u0017\f\u0017\u0172\t\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0176" +
		"\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003" +
		"\u0017\u017d\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005" +
		"\u0018\u0183\b\u0018\n\u0018\f\u0018\u0186\t\u0018\u0001\u0018\u0001\u0018" +
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u018e\b\u0019" +
		"\u0001\u001a\u0001\u001a\u0005\u001a\u0192\b\u001a\n\u001a\f\u001a\u0195" +
		"\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01a0\b\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001" +
		"\u001c\u0001\u001c\u0003\u001c\u01b6\b\u001c\u0001\u001d\u0001\u001d\u0001" +
		"\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u01bd\b\u001d\n\u001d\f\u001d" +
		"\u01c0\t\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u01c4\b\u001d\u0001" +
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001" +
		"\u001e\u0003\u001e\u01cd\b\u001e\u0001\u001f\u0001\u001f\u0001 \u0001" +
		" \u0001 \u0005 \u01d4\b \n \f \u01d7\t \u0001!\u0001!\u0001!\u0005!\u01dc" +
		"\b!\n!\f!\u01df\t!\u0001\"\u0001\"\u0001\"\u0005\"\u01e4\b\"\n\"\f\"\u01e7" +
		"\t\"\u0001#\u0001#\u0001#\u0005#\u01ec\b#\n#\f#\u01ef\t#\u0001$\u0001" +
		"$\u0001$\u0005$\u01f4\b$\n$\f$\u01f7\t$\u0001%\u0001%\u0001%\u0005%\u01fc" +
		"\b%\n%\f%\u01ff\t%\u0001&\u0001&\u0001&\u0005&\u0204\b&\n&\f&\u0207\t" +
		"&\u0001\'\u0001\'\u0001\'\u0005\'\u020c\b\'\n\'\f\'\u020f\t\'\u0001(\u0001" +
		"(\u0001(\u0005(\u0214\b(\n(\f(\u0217\t(\u0001)\u0001)\u0001)\u0005)\u021c" +
		"\b)\n)\f)\u021f\t)\u0001*\u0001*\u0001*\u0005*\u0224\b*\n*\f*\u0227\t" +
		"*\u0001+\u0001+\u0001+\u0003+\u022c\b+\u0001,\u0001,\u0005,\u0230\b,\n" +
		",\f,\u0233\t,\u0001,\u0001,\u0005,\u0237\b,\n,\f,\u023a\t,\u0001,\u0001" +
		",\u0005,\u023e\b,\n,\f,\u0241\t,\u0001,\u0001,\u0001,\u0001,\u0005,\u0247" +
		"\b,\n,\f,\u024a\t,\u0001,\u0001,\u0005,\u024e\b,\n,\f,\u0251\t,\u0001" +
		",\u0001,\u0005,\u0255\b,\n,\f,\u0258\t,\u0003,\u025a\b,\u0001-\u0001-" +
		"\u0001-\u0003-\u025f\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001" +
		".\u0001.\u0001/\u0001/\u0001/\u0001/\u0005/\u026d\b/\n/\f/\u0270\t/\u0003" +
		"/\u0272\b/\u0001/\u0001/\u00010\u00010\u00010\u00010\u00010\u00010\u0001" +
		"0\u00010\u00010\u00010\u00030\u0280\b0\u00011\u00011\u00011\u00011\u0005" +
		"1\u0286\b1\n1\f1\u0289\t1\u00031\u028b\b1\u00011\u00011\u00011\u00011" +
		"\u00011\u00011\u00011\u00031\u0294\b1\u00012\u00012\u00012\u00012\u0005" +
		"2\u029a\b2\n2\f2\u029d\t2\u00032\u029f\b2\u00012\u00032\u02a2\b2\u0001" +
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00032\u02ab\b2\u00013\u0001" +
		"3\u00013\u00013\u00053\u02b1\b3\n3\f3\u02b4\t3\u00033\u02b6\b3\u00013" +
		"\u00013\u00014\u00014\u00014\u00054\u02bd\b4\n4\f4\u02c0\t4\u00015\u0001" +
		"5\u00015\u00015\u00015\u00015\u00055\u02c8\b5\n5\f5\u02cb\t5\u00015\u0001" +
		"5\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0001" +
		"6\u00016\u00016\u00016\u00056\u02dc\b6\n6\f6\u02df\t6\u00016\u00016\u0001" +
		"7\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u00057\u02ec" +
		"\b7\n7\f7\u02ef\t7\u00017\u00017\u00037\u02f3\b7\u00017\u00017\u00018" +
		"\u00018\u00018\u00018\u00018\u00058\u02fc\b8\n8\f8\u02ff\t8\u00018\u0001" +
		"8\u00018\u00018\u00019\u00019\u00039\u0307\b9\u0001:\u0001:\u0001:\u0003" +
		":\u030c\b:\u0001:\u0001:\u0005:\u0310\b:\n:\f:\u0313\t:\u0001;\u0001;" +
		"\u0001;\u0005;\u0318\b;\n;\f;\u031b\t;\u0001<\u0001<\u0003<\u031f\b<\u0001" +
		"<\u0001<\u0001<\u0003<\u0324\b<\u0005<\u0326\b<\n<\f<\u0329\t<\u0001<" +
		"\u0001<\u0001<\u0001<\u0003<\u032f\b<\u0001<\u0003<\u0332\b<\u0001=\u0001" +
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0005=\u033d\b=\n=" +
		"\f=\u0340\t=\u0003=\u0342\b=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001" +
		"=\u0001=\u0004=\u034b\b=\u000b=\f=\u034c\u0003=\u034f\b=\u0001=\u0003" +
		"=\u0352\b=\u0001>\u0001>\u0001>\u0001>\u0005>\u0358\b>\n>\f>\u035b\t>" +
		"\u0003>\u035d\b>\u0001>\u0001>\u0001?\u0001?\u0001?\u0001?\u0001?\u0001" +
		"?\u0001?\u0001?\u0001?\u0005?\u036a\b?\n?\f?\u036d\t?\u0001@\u0001@\u0001" +
		"@\u0001@\u0001@\u0005@\u0374\b@\n@\f@\u0377\t@\u0001@\u0001@\u0003@\u037b" +
		"\b@\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001B\u0001B\u0001B\u0001" +
		"B\u0001B\u0001B\u0001B\u0001B\u0001C\u0001C\u0001C\u0001C\u0001C\u0005" +
		"C\u0390\bC\nC\fC\u0393\tC\u0003C\u0395\bC\u0001C\u0001C\u0001D\u0001D" +
		"\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001E\u0001E\u0001E\u0000" +
		"\u0000F\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018" +
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080" +
		"\u0082\u0084\u0086\u0088\u008a\u0000\u0007\u0001\u0000\u001a\u001b\u0001" +
		"\u0000\u001f\"\u0002\u000007OO\u0001\u00008;\u0002\u0000\u0006\u0006=" +
		">\u0001\u0000?B\u0002\u0000=>DD\u03eb\u0000\u008f\u0001\u0000\u0000\u0000" +
		"\u0002\u00b8\u0001\u0000\u0000\u0000\u0004\u00ba\u0001\u0000\u0000\u0000" +
		"\u0006\u00cb\u0001\u0000\u0000\u0000\b\u00cd\u0001\u0000\u0000\u0000\n" +
		"\u00d4\u0001\u0000\u0000\u0000\f\u00f6\u0001\u0000\u0000\u0000\u000e\u00f8" +
		"\u0001\u0000\u0000\u0000\u0010\u00fe\u0001\u0000\u0000\u0000\u0012\u0102" +
		"\u0001\u0000\u0000\u0000\u0014\u0117\u0001\u0000\u0000\u0000\u0016\u0119" +
		"\u0001\u0000\u0000\u0000\u0018\u0120\u0001\u0000\u0000\u0000\u001a\u0127" +
		"\u0001\u0000\u0000\u0000\u001c\u012a\u0001\u0000\u0000\u0000\u001e\u012d" +
		"\u0001\u0000\u0000\u0000 \u0132\u0001\u0000\u0000\u0000\"\u0144\u0001" +
		"\u0000\u0000\u0000$\u0146\u0001\u0000\u0000\u0000&\u014c\u0001\u0000\u0000" +
		"\u0000(\u015e\u0001\u0000\u0000\u0000*\u0165\u0001\u0000\u0000\u0000," +
		"\u0167\u0001\u0000\u0000\u0000.\u017c\u0001\u0000\u0000\u00000\u017e\u0001" +
		"\u0000\u0000\u00002\u018d\u0001\u0000\u0000\u00004\u0193\u0001\u0000\u0000" +
		"\u00006\u0196\u0001\u0000\u0000\u00008\u01b5\u0001\u0000\u0000\u0000:" +
		"\u01b7\u0001\u0000\u0000\u0000<\u01cc\u0001\u0000\u0000\u0000>\u01ce\u0001" +
		"\u0000\u0000\u0000@\u01d0\u0001\u0000\u0000\u0000B\u01d8\u0001\u0000\u0000" +
		"\u0000D\u01e0\u0001\u0000\u0000\u0000F\u01e8\u0001\u0000\u0000\u0000H" +
		"\u01f0\u0001\u0000\u0000\u0000J\u01f8\u0001\u0000\u0000\u0000L\u0200\u0001" +
		"\u0000\u0000\u0000N\u0208\u0001\u0000\u0000\u0000P\u0210\u0001\u0000\u0000" +
		"\u0000R\u0218\u0001\u0000\u0000\u0000T\u0220\u0001\u0000\u0000\u0000V" +
		"\u022b\u0001\u0000\u0000\u0000X\u0259\u0001\u0000\u0000\u0000Z\u025e\u0001" +
		"\u0000\u0000\u0000\\\u0260\u0001\u0000\u0000\u0000^\u0268\u0001\u0000" +
		"\u0000\u0000`\u027f\u0001\u0000\u0000\u0000b\u0293\u0001\u0000\u0000\u0000" +
		"d\u02aa\u0001\u0000\u0000\u0000f\u02ac\u0001\u0000\u0000\u0000h\u02b9" +
		"\u0001\u0000\u0000\u0000j\u02c1\u0001\u0000\u0000\u0000l\u02ce\u0001\u0000" +
		"\u0000\u0000n\u02e2\u0001\u0000\u0000\u0000p\u02f6\u0001\u0000\u0000\u0000" +
		"r\u0306\u0001\u0000\u0000\u0000t\u0308\u0001\u0000\u0000\u0000v\u0314" +
		"\u0001\u0000\u0000\u0000x\u031e\u0001\u0000\u0000\u0000z\u0351\u0001\u0000" +
		"\u0000\u0000|\u0353\u0001\u0000\u0000\u0000~\u0360\u0001\u0000\u0000\u0000" +
		"\u0080\u037a\u0001\u0000\u0000\u0000\u0082\u037c\u0001\u0000\u0000\u0000" +
		"\u0084\u0382\u0001\u0000\u0000\u0000\u0086\u038a\u0001\u0000\u0000\u0000" +
		"\u0088\u0398\u0001\u0000\u0000\u0000\u008a\u03a0\u0001\u0000\u0000\u0000" +
		"\u008c\u008e\u0003\u0002\u0001\u0000\u008d\u008c\u0001\u0000\u0000\u0000" +
		"\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000" +
		"\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0092\u0001\u0000\u0000\u0000" +
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0000\u0000\u0001" +
		"\u0093\u0001\u0001\u0000\u0000\u0000\u0094\u0095\u0003\u0004\u0002\u0000" +
		"\u0095\u0096\u0005\u0001\u0000\u0000\u0096\u00b9\u0001\u0000\u0000\u0000" +
		"\u0097\u0098\u0003\u0006\u0003\u0000\u0098\u0099\u0005\u0001\u0000\u0000" +
		"\u0099\u00b9\u0001\u0000\u0000\u0000\u009a\u009b\u0003\b\u0004\u0000\u009b" +
		"\u009c\u0005\u0001\u0000\u0000\u009c\u00b9\u0001\u0000\u0000\u0000\u009d" +
		"\u009e\u0003\u000e\u0007\u0000\u009e\u009f\u0005\u0001\u0000\u0000\u009f" +
		"\u00b9\u0001\u0000\u0000\u0000\u00a0\u00a1\u0003\u0010\b\u0000\u00a1\u00a2" +
		"\u0005\u0001\u0000\u0000\u00a2\u00b9\u0001\u0000\u0000\u0000\u00a3\u00a4" +
		"\u0003\u0012\t\u0000\u00a4\u00a5\u0005\u0001\u0000\u0000\u00a5\u00b9\u0001" +
		"\u0000\u0000\u0000\u00a6\u00a7\u0003\u0014\n\u0000\u00a7\u00a8\u0005\u0001" +
		"\u0000\u0000\u00a8\u00b9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0003\u0016" +
		"\u000b\u0000\u00aa\u00ab\u0005\u0001\u0000\u0000\u00ab\u00b9\u0001\u0000" +
		"\u0000\u0000\u00ac\u00ad\u0003\u001a\r\u0000\u00ad\u00ae\u0005\u0001\u0000" +
		"\u0000\u00ae\u00b9\u0001\u0000\u0000\u0000\u00af\u00b0\u0003\u001c\u000e" +
		"\u0000\u00b0\u00b1\u0005\u0001\u0000\u0000\u00b1\u00b9\u0001\u0000\u0000" +
		"\u0000\u00b2\u00b3\u0003\u001e\u000f\u0000\u00b3\u00b4\u0005\u0001\u0000" +
		"\u0000\u00b4\u00b9\u0001\u0000\u0000\u0000\u00b5\u00b6\u0003\u0018\f\u0000" +
		"\u00b6\u00b7\u0005\u0001\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000" +
		"\u00b8\u0094\u0001\u0000\u0000\u0000\u00b8\u0097\u0001\u0000\u0000\u0000" +
		"\u00b8\u009a\u0001\u0000\u0000\u0000\u00b8\u009d\u0001\u0000\u0000\u0000" +
		"\u00b8\u00a0\u0001\u0000\u0000\u0000\u00b8\u00a3\u0001\u0000\u0000\u0000" +
		"\u00b8\u00a6\u0001\u0000\u0000\u0000\u00b8\u00a9\u0001\u0000\u0000\u0000" +
		"\u00b8\u00ac\u0001\u0000\u0000\u0000\u00b8\u00af\u0001\u0000\u0000\u0000" +
		"\u00b8\u00b2\u0001\u0000\u0000\u0000\u00b8\u00b5\u0001\u0000\u0000\u0000" +
		"\u00b9\u0003\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0002\u0000\u0000" +
		"\u00bb\u00bc\u0005T\u0000\u0000\u00bc\u0005\u0001\u0000\u0000\u0000\u00bd" +
		"\u00be\u0003$\u0012\u0000\u00be\u00c1\u00034\u001a\u0000\u00bf\u00c0\u0005" +
		"O\u0000\u0000\u00c0\u00c2\u0003>\u001f\u0000\u00c1\u00bf\u0001\u0000\u0000" +
		"\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00cc\u0001\u0000\u0000" +
		"\u0000\u00c3\u00c4\u0005\u0003\u0000\u0000\u00c4\u00c5\u0005\u0004\u0000" +
		"\u0000\u00c5\u00c6\u0003\u008aE\u0000\u00c6\u00c9\u00034\u001a\u0000\u00c7" +
		"\u00c8\u0005O\u0000\u0000\u00c8\u00ca\u0003>\u001f\u0000\u00c9\u00c7\u0001" +
		"\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cc\u0001" +
		"\u0000\u0000\u0000\u00cb\u00bd\u0001\u0000\u0000\u0000\u00cb\u00c3\u0001" +
		"\u0000\u0000\u0000\u00cc\u0007\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005" +
		"\u0005\u0000\u0000\u00ce\u00cf\u0003\u008aE\u0000\u00cf\u00d2\u00034\u001a" +
		"\u0000\u00d0\u00d1\u0005O\u0000\u0000\u00d1\u00d3\u0003\n\u0005\u0000" +
		"\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000" +
		"\u00d3\t\u0001\u0000\u0000\u0000\u00d4\u00d9\u0003\f\u0006\u0000\u00d5" +
		"\u00d6\u0005\u0006\u0000\u0000\u00d6\u00d8\u0003\f\u0006\u0000\u00d7\u00d5" +
		"\u0001\u0000\u0000\u0000\u00d8\u00db\u0001\u0000\u0000\u0000\u00d9\u00d7" +
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u000b" +
		"\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc\u00dd" +
		"\u0005\u0007\u0000\u0000\u00dd\u00e2\u0003\u008aE\u0000\u00de\u00df\u0005" +
		"\b\u0000\u0000\u00df\u00e1\u0003\u008aE\u0000\u00e0\u00de\u0001\u0000" +
		"\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000" +
		"\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e5\u0001\u0000" +
		"\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\t\u0000" +
		"\u0000\u00e6\u00f7\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\n\u0000\u0000" +
		"\u00e8\u00e9\u0005\u000b\u0000\u0000\u00e9\u00ea\u0003>\u001f\u0000\u00ea" +
		"\u00eb\u0005\f\u0000\u0000\u00eb\u00f7\u0001\u0000\u0000\u0000\u00ec\u00ed" +
		"\u0003\u008aE\u0000\u00ed\u00ee\u0005\u000b\u0000\u0000\u00ee\u00ef\u0003" +
		"\u008aE\u0000\u00ef\u00f0\u0005\f\u0000\u0000\u00f0\u00f7\u0001\u0000" +
		"\u0000\u0000\u00f1\u00f2\u0005\r\u0000\u0000\u00f2\u00f3\u0005\u000b\u0000" +
		"\u0000\u00f3\u00f4\u0003>\u001f\u0000\u00f4\u00f5\u0005\f\u0000\u0000" +
		"\u00f5\u00f7\u0001\u0000\u0000\u0000\u00f6\u00dc\u0001\u0000\u0000\u0000" +
		"\u00f6\u00e7\u0001\u0000\u0000\u0000\u00f6\u00ec\u0001\u0000\u0000\u0000" +
		"\u00f6\u00f1\u0001\u0000\u0000\u0000\u00f7\r\u0001\u0000\u0000\u0000\u00f8" +
		"\u00f9\u0005\u000e\u0000\u0000\u00f9\u00fa\u0003\u008aE\u0000\u00fa\u00fb" +
		"\u00034\u001a\u0000\u00fb\u00fc\u0005O\u0000\u0000\u00fc\u00fd\u0003&" +
		"\u0013\u0000\u00fd\u000f\u0001\u0000\u0000\u0000\u00fe\u00ff\u0003\u008a" +
		"E\u0000\u00ff\u0100\u0005O\u0000\u0000\u0100\u0101\u0003>\u001f\u0000" +
		"\u0101\u0011\u0001\u0000\u0000\u0000\u0102\u0105\u0005\u000f\u0000\u0000" +
		"\u0103\u0104\u0005\u0010\u0000\u0000\u0104\u0106\u0005T\u0000\u0000\u0105" +
		"\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106" +
		"\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0003>\u001f\u0000\u0108\u0013" +
		"\u0001\u0000\u0000\u0000\u0109\u010a\u0005\u0011\u0000\u0000\u010a\u010b" +
		"\u00034\u001a\u0000\u010b\u010c\u0005\u0012\u0000\u0000\u010c\u0118\u0001" +
		"\u0000\u0000\u0000\u010d\u010e\u0005\u0011\u0000\u0000\u010e\u010f\u0003" +
		"4\u001a\u0000\u010f\u0110\u0005\u0013\u0000\u0000\u0110\u0111\u0003>\u001f" +
		"\u0000\u0111\u0118\u0001\u0000\u0000\u0000\u0112\u0113\u0005\u0011\u0000" +
		"\u0000\u0113\u0114\u00034\u001a\u0000\u0114\u0115\u0005\u0014\u0000\u0000" +
		"\u0115\u0116\u0003>\u001f\u0000\u0116\u0118\u0001\u0000\u0000\u0000\u0117" +
		"\u0109\u0001\u0000\u0000\u0000\u0117\u010d\u0001\u0000\u0000\u0000\u0117" +
		"\u0112\u0001\u0000\u0000\u0000\u0118\u0015\u0001\u0000\u0000\u0000\u0119" +
		"\u011c\u0005\u0015\u0000\u0000\u011a\u011b\u0005\u0010\u0000\u0000\u011b" +
		"\u011d\u0005T\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011d" +
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u011f" +
		"\u0003>\u001f\u0000\u011f\u0017\u0001\u0000\u0000\u0000\u0120\u0121\u0005" +
		"\u0016\u0000\u0000\u0121\u0122\u0003\u008aE\u0000\u0122\u0125\u0003\"" +
		"\u0011\u0000\u0123\u0124\u0005O\u0000\u0000\u0124\u0126\u0003>\u001f\u0000" +
		"\u0125\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000" +
		"\u0126\u0019\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u0017\u0000\u0000" +
		"\u0128\u0129\u0003 \u0010\u0000\u0129\u001b\u0001\u0000\u0000\u0000\u012a" +
		"\u012b\u0005\u0018\u0000\u0000\u012b\u012c\u0003 \u0010\u0000\u012c\u001d" +
		"\u0001\u0000\u0000\u0000\u012d\u012e\u0005\u0019\u0000\u0000\u012e\u012f" +
		"\u0003&\u0013\u0000\u012f\u0130\u0005\u0004\u0000\u0000\u0130\u0131\u0003" +
		" \u0010\u0000\u0131\u001f\u0001\u0000\u0000\u0000\u0132\u0133\u0003\u008a" +
		"E\u0000\u0133\u0134\u0003\"\u0011\u0000\u0134\u0137\u00034\u001a\u0000" +
		"\u0135\u0136\u0005O\u0000\u0000\u0136\u0138\u0003>\u001f\u0000\u0137\u0135" +
		"\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138!\u0001" +
		"\u0000\u0000\u0000\u0139\u013a\u0005\u000b\u0000\u0000\u013a\u013f\u0003" +
		"$\u0012\u0000\u013b\u013c\u0005\b\u0000\u0000\u013c\u013e\u0003$\u0012" +
		"\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013e\u0141\u0001\u0000\u0000" +
		"\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000" +
		"\u0000\u0140\u0142\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000" +
		"\u0000\u0142\u0143\u0005\f\u0000\u0000\u0143\u0145\u0001\u0000\u0000\u0000" +
		"\u0144\u0139\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000" +
		"\u0145#\u0001\u0000\u0000\u0000\u0146\u0147\u0003&\u0013\u0000\u0147\u0148" +
		"\u0005\u0004\u0000\u0000\u0148\u0149\u0003\u008aE\u0000\u0149%\u0001\u0000" +
		"\u0000\u0000\u014a\u014d\u0003(\u0014\u0000\u014b\u014d\u0003.\u0017\u0000" +
		"\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000" +
		"\u014d\'\u0001\u0000\u0000\u0000\u014e\u0150\u0007\u0000\u0000\u0000\u014f" +
		"\u014e\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150" +
		"\u0152\u0001\u0000\u0000\u0000\u0151\u0153\u0005\u001c\u0000\u0000\u0152" +
		"\u0151\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153" +
		"\u0155\u0001\u0000\u0000\u0000\u0154\u0156\u0005\u001d\u0000\u0000\u0155" +
		"\u0154\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156" +
		"\u0157\u0001\u0000\u0000\u0000\u0157\u015a\u0003*\u0015\u0000\u0158\u0159" +
		"\u0005\u0006\u0000\u0000\u0159\u015b\u0003(\u0014\u0000\u015a\u0158\u0001" +
		"\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b\u015f\u0001" +
		"\u0000\u0000\u0000\u015c\u015d\u0005\u0003\u0000\u0000\u015d\u015f\u0005" +
		"R\u0000\u0000\u015e\u014f\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000" +
		"\u0000\u0000\u015f)\u0001\u0000\u0000\u0000\u0160\u0166\u0003\u008aE\u0000" +
		"\u0161\u0166\u0003,\u0016\u0000\u0162\u0166\u0005R\u0000\u0000\u0163\u0166" +
		"\u0005\u001e\u0000\u0000\u0164\u0166\u0003>\u001f\u0000\u0165\u0160\u0001" +
		"\u0000\u0000\u0000\u0165\u0161\u0001\u0000\u0000\u0000\u0165\u0162\u0001" +
		"\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0164\u0001" +
		"\u0000\u0000\u0000\u0166+\u0001\u0000\u0000\u0000\u0167\u0168\u0007\u0001" +
		"\u0000\u0000\u0168-\u0001\u0000\u0000\u0000\u0169\u0175\u0005#\u0000\u0000" +
		"\u016a\u016b\u0005$\u0000\u0000\u016b\u0170\u0003&\u0013\u0000\u016c\u016d" +
		"\u0005\b\u0000\u0000\u016d\u016f\u0003&\u0013\u0000\u016e\u016c\u0001" +
		"\u0000\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u016e\u0001" +
		"\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171\u0173\u0001" +
		"\u0000\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0174\u0005" +
		"%\u0000\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u016a\u0001\u0000" +
		"\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000" +
		"\u0000\u0000\u0177\u0178\u0005&\u0000\u0000\u0178\u017d\u0003(\u0014\u0000" +
		"\u0179\u017a\u0005\'\u0000\u0000\u017a\u017b\u0005&\u0000\u0000\u017b" +
		"\u017d\u0003(\u0014\u0000\u017c\u0169\u0001\u0000\u0000\u0000\u017c\u0179" +
		"\u0001\u0000\u0000\u0000\u017d/\u0001\u0000\u0000\u0000\u017e\u017f\u0005" +
		"$\u0000\u0000\u017f\u0184\u0003>\u001f\u0000\u0180\u0181\u0005\b\u0000" +
		"\u0000\u0181\u0183\u0003>\u001f\u0000\u0182\u0180\u0001\u0000\u0000\u0000" +
		"\u0183\u0186\u0001\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000" +
		"\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u0187\u0001\u0000\u0000\u0000" +
		"\u0186\u0184\u0001\u0000\u0000\u0000\u0187\u0188\u0005%\u0000\u0000\u0188" +
		"1\u0001\u0000\u0000\u0000\u0189\u018a\u0005(\u0000\u0000\u018a\u018e\u0003" +
		"\u008aE\u0000\u018b\u018c\u0005(\u0000\u0000\u018c\u018e\u0005P\u0000" +
		"\u0000\u018d\u0189\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000" +
		"\u0000\u018e3\u0001\u0000\u0000\u0000\u018f\u0190\u0005\u0010\u0000\u0000" +
		"\u0190\u0192\u00036\u001b\u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0192" +
		"\u0195\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193" +
		"\u0194\u0001\u0000\u0000\u0000\u01945\u0001\u0000\u0000\u0000\u0195\u0193" +
		"\u0001\u0000\u0000\u0000\u0196\u0197\u00038\u001c\u0000\u0197\u0198\u0003" +
		"<\u001e\u0000\u01987\u0001\u0000\u0000\u0000\u0199\u019a\u0005\u000b\u0000" +
		"\u0000\u019a\u019b\u0003>\u001f\u0000\u019b\u019c\u0005\f\u0000\u0000" +
		"\u019c\u01b6\u0001\u0000\u0000\u0000\u019d\u019f\u0003\u008aE\u0000\u019e" +
		"\u01a0\u0003^/\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u019f\u01a0\u0001" +
		"\u0000\u0000\u0000\u01a0\u01b6\u0001\u0000\u0000\u0000\u01a1\u01b6\u0005" +
		"\n\u0000\u0000\u01a2\u01b6\u0005)\u0000\u0000\u01a3\u01b6\u0005*\u0000" +
		"\u0000\u01a4\u01b6\u0005P\u0000\u0000\u01a5\u01b6\u0005Q\u0000\u0000\u01a6" +
		"\u01b6\u0005T\u0000\u0000\u01a7\u01b6\u0003b1\u0000\u01a8\u01b6\u0003" +
		"d2\u0000\u01a9\u01b6\u0003f3\u0000\u01aa\u01b6\u0003z=\u0000\u01ab\u01b6" +
		"\u0003|>\u0000\u01ac\u01b6\u0003j5\u0000\u01ad\u01b6\u0003l6\u0000\u01ae" +
		"\u01b6\u0003\u0082A\u0000\u01af\u01b6\u0003\u0084B\u0000\u01b0\u01b6\u0003" +
		":\u001d\u0000\u01b1\u01b6\u0003n7\u0000\u01b2\u01b6\u0003p8\u0000\u01b3" +
		"\u01b6\u0003\u0086C\u0000\u01b4\u01b6\u0003\u0088D\u0000\u01b5\u0199\u0001" +
		"\u0000\u0000\u0000\u01b5\u019d\u0001\u0000\u0000\u0000\u01b5\u01a1\u0001" +
		"\u0000\u0000\u0000\u01b5\u01a2\u0001\u0000\u0000\u0000\u01b5\u01a3\u0001" +
		"\u0000\u0000\u0000\u01b5\u01a4\u0001\u0000\u0000\u0000\u01b5\u01a5\u0001" +
		"\u0000\u0000\u0000\u01b5\u01a6\u0001\u0000\u0000\u0000\u01b5\u01a7\u0001" +
		"\u0000\u0000\u0000\u01b5\u01a8\u0001\u0000\u0000\u0000\u01b5\u01a9\u0001" +
		"\u0000\u0000\u0000\u01b5\u01aa\u0001\u0000\u0000\u0000\u01b5\u01ab\u0001" +
		"\u0000\u0000\u0000\u01b5\u01ac\u0001\u0000\u0000\u0000\u01b5\u01ad\u0001" +
		"\u0000\u0000\u0000\u01b5\u01ae\u0001\u0000\u0000\u0000\u01b5\u01af\u0001" +
		"\u0000\u0000\u0000\u01b5\u01b0\u0001\u0000\u0000\u0000\u01b5\u01b1\u0001" +
		"\u0000\u0000\u0000\u01b5\u01b2\u0001\u0000\u0000\u0000\u01b5\u01b3\u0001" +
		"\u0000\u0000\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b69\u0001\u0000" +
		"\u0000\u0000\u01b7\u01c3\u0003\u008aE\u0000\u01b8\u01b9\u0005\u000b\u0000" +
		"\u0000\u01b9\u01be\u0003>\u001f\u0000\u01ba\u01bb\u0005\b\u0000\u0000" +
		"\u01bb\u01bd\u0003>\u001f\u0000\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bd" +
		"\u01c0\u0001\u0000\u0000\u0000\u01be\u01bc\u0001\u0000\u0000\u0000\u01be" +
		"\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c1\u0001\u0000\u0000\u0000\u01c0" +
		"\u01be\u0001\u0000\u0000\u0000\u01c1\u01c2\u0005\f\u0000\u0000\u01c2\u01c4" +
		"\u0001\u0000\u0000\u0000\u01c3\u01b8\u0001\u0000\u0000\u0000\u01c3\u01c4" +
		"\u0001\u0000\u0000\u0000\u01c4;\u0001\u0000\u0000\u0000\u01c5\u01cd\u0001" +
		"\u0000\u0000\u0000\u01c6\u01c7\u00030\u0018\u0000\u01c7\u01c8\u0003<\u001e" +
		"\u0000\u01c8\u01cd\u0001\u0000\u0000\u0000\u01c9\u01ca\u00032\u0019\u0000" +
		"\u01ca\u01cb\u0003<\u001e\u0000\u01cb\u01cd\u0001\u0000\u0000\u0000\u01cc" +
		"\u01c5\u0001\u0000\u0000\u0000\u01cc\u01c6\u0001\u0000\u0000\u0000\u01cc" +
		"\u01c9\u0001\u0000\u0000\u0000\u01cd=\u0001\u0000\u0000\u0000\u01ce\u01cf" +
		"\u0003@ \u0000\u01cf?\u0001\u0000\u0000\u0000\u01d0\u01d5\u0003B!\u0000" +
		"\u01d1\u01d2\u0005+\u0000\u0000\u01d2\u01d4\u0003B!\u0000\u01d3\u01d1" +
		"\u0001\u0000\u0000\u0000\u01d4\u01d7\u0001\u0000\u0000\u0000\u01d5\u01d3" +
		"\u0001\u0000\u0000\u0000\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6A\u0001" +
		"\u0000\u0000\u0000\u01d7\u01d5\u0001\u0000\u0000\u0000\u01d8\u01dd\u0003" +
		"D\"\u0000\u01d9\u01da\u0005,\u0000\u0000\u01da\u01dc\u0003D\"\u0000\u01db" +
		"\u01d9\u0001\u0000\u0000\u0000\u01dc\u01df\u0001\u0000\u0000\u0000\u01dd" +
		"\u01db\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de" +
		"C\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01e0\u01e5" +
		"\u0003F#\u0000\u01e1\u01e2\u0005-\u0000\u0000\u01e2\u01e4\u0003F#\u0000" +
		"\u01e3\u01e1\u0001\u0000\u0000\u0000\u01e4\u01e7\u0001\u0000\u0000\u0000" +
		"\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000" +
		"\u01e6E\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000\u0000\u0000\u01e8" +
		"\u01ed\u0003H$\u0000\u01e9\u01ea\u0005.\u0000\u0000\u01ea\u01ec\u0003" +
		"H$\u0000\u01eb\u01e9\u0001\u0000\u0000\u0000\u01ec\u01ef\u0001\u0000\u0000" +
		"\u0000\u01ed\u01eb\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000" +
		"\u0000\u01eeG\u0001\u0000\u0000\u0000\u01ef\u01ed\u0001\u0000\u0000\u0000" +
		"\u01f0\u01f5\u0003J%\u0000\u01f1\u01f2\u0005/\u0000\u0000\u01f2\u01f4" +
		"\u0003J%\u0000\u01f3\u01f1\u0001\u0000\u0000\u0000\u01f4\u01f7\u0001\u0000" +
		"\u0000\u0000\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f5\u01f6\u0001\u0000" +
		"\u0000\u0000\u01f6I\u0001\u0000\u0000\u0000\u01f7\u01f5\u0001\u0000\u0000" +
		"\u0000\u01f8\u01fd\u0003L&\u0000\u01f9\u01fa\u0007\u0002\u0000\u0000\u01fa" +
		"\u01fc\u0003L&\u0000\u01fb\u01f9\u0001\u0000\u0000\u0000\u01fc\u01ff\u0001" +
		"\u0000\u0000\u0000\u01fd\u01fb\u0001\u0000\u0000\u0000\u01fd\u01fe\u0001" +
		"\u0000\u0000\u0000\u01feK\u0001\u0000\u0000\u0000\u01ff\u01fd\u0001\u0000" +
		"\u0000\u0000\u0200\u0205\u0003N\'\u0000\u0201\u0202\u0007\u0003\u0000" +
		"\u0000\u0202\u0204\u0003N\'\u0000\u0203\u0201\u0001\u0000\u0000\u0000" +
		"\u0204\u0207\u0001\u0000\u0000\u0000\u0205\u0203\u0001\u0000\u0000\u0000" +
		"\u0205\u0206\u0001\u0000\u0000\u0000\u0206M\u0001\u0000\u0000\u0000\u0207" +
		"\u0205\u0001\u0000\u0000\u0000\u0208\u020d\u0003P(\u0000\u0209\u020a\u0005" +
		"<\u0000\u0000\u020a\u020c\u0003P(\u0000\u020b\u0209\u0001\u0000\u0000" +
		"\u0000\u020c\u020f\u0001\u0000\u0000\u0000\u020d\u020b\u0001\u0000\u0000" +
		"\u0000\u020d\u020e\u0001\u0000\u0000\u0000\u020eO\u0001\u0000\u0000\u0000" +
		"\u020f\u020d\u0001\u0000\u0000\u0000\u0210\u0215\u0003R)\u0000\u0211\u0212" +
		"\u0007\u0004\u0000\u0000\u0212\u0214\u0003R)\u0000\u0213\u0211\u0001\u0000" +
		"\u0000\u0000\u0214\u0217\u0001\u0000\u0000\u0000\u0215\u0213\u0001\u0000" +
		"\u0000\u0000\u0215\u0216\u0001\u0000\u0000\u0000\u0216Q\u0001\u0000\u0000" +
		"\u0000\u0217\u0215\u0001\u0000\u0000\u0000\u0218\u021d\u0003T*\u0000\u0219" +
		"\u021a\u0007\u0005\u0000\u0000\u021a\u021c\u0003T*\u0000\u021b\u0219\u0001" +
		"\u0000\u0000\u0000\u021c\u021f\u0001\u0000\u0000\u0000\u021d\u021b\u0001" +
		"\u0000\u0000\u0000\u021d\u021e\u0001\u0000\u0000\u0000\u021eS\u0001\u0000" +
		"\u0000\u0000\u021f\u021d\u0001\u0000\u0000\u0000\u0220\u0225\u0003V+\u0000" +
		"\u0221\u0222\u0005C\u0000\u0000\u0222\u0224\u0003V+\u0000\u0223\u0221" +
		"\u0001\u0000\u0000\u0000\u0224\u0227\u0001\u0000\u0000\u0000\u0225\u0223" +
		"\u0001\u0000\u0000\u0000\u0225\u0226\u0001\u0000\u0000\u0000\u0226U\u0001" +
		"\u0000\u0000\u0000\u0227\u0225\u0001\u0000\u0000\u0000\u0228\u0229\u0007" +
		"\u0006\u0000\u0000\u0229\u022c\u0003V+\u0000\u022a\u022c\u0003X,\u0000" +
		"\u022b\u0228\u0001\u0000\u0000\u0000\u022b\u022a\u0001\u0000\u0000\u0000" +
		"\u022cW\u0001\u0000\u0000\u0000\u022d\u0231\u0003`0\u0000\u022e\u0230" +
		"\u0003Z-\u0000\u022f\u022e\u0001\u0000\u0000\u0000\u0230\u0233\u0001\u0000" +
		"\u0000\u0000\u0231\u022f\u0001\u0000\u0000\u0000\u0231\u0232\u0001\u0000" +
		"\u0000\u0000\u0232\u025a\u0001\u0000\u0000\u0000\u0233\u0231\u0001\u0000" +
		"\u0000\u0000\u0234\u0238\u0003\\.\u0000\u0235\u0237\u0003Z-\u0000\u0236" +
		"\u0235\u0001\u0000\u0000\u0000\u0237\u023a\u0001\u0000\u0000\u0000\u0238" +
		"\u0236\u0001\u0000\u0000\u0000\u0238\u0239\u0001\u0000\u0000\u0000\u0239" +
		"\u025a\u0001\u0000\u0000\u0000\u023a\u0238\u0001\u0000\u0000\u0000\u023b" +
		"\u023f\u0003\u008aE\u0000\u023c\u023e\u0003Z-\u0000\u023d\u023c\u0001" +
		"\u0000\u0000\u0000\u023e\u0241\u0001\u0000\u0000\u0000\u023f\u023d\u0001" +
		"\u0000\u0000\u0000\u023f\u0240\u0001\u0000\u0000\u0000\u0240\u025a\u0001" +
		"\u0000\u0000\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0242\u0243\u0005" +
		"\u000b\u0000\u0000\u0243\u0244\u0003>\u001f\u0000\u0244\u0248\u0005\f" +
		"\u0000\u0000\u0245\u0247\u0003Z-\u0000\u0246\u0245\u0001\u0000\u0000\u0000" +
		"\u0247\u024a\u0001\u0000\u0000\u0000\u0248\u0246\u0001\u0000\u0000\u0000" +
		"\u0248\u0249\u0001\u0000\u0000\u0000\u0249\u025a\u0001\u0000\u0000\u0000" +
		"\u024a\u0248\u0001\u0000\u0000\u0000\u024b\u024f\u0003n7\u0000\u024c\u024e" +
		"\u0003Z-\u0000\u024d\u024c\u0001\u0000\u0000\u0000\u024e\u0251\u0001\u0000" +
		"\u0000\u0000\u024f\u024d\u0001\u0000\u0000\u0000\u024f\u0250\u0001\u0000" +
		"\u0000\u0000\u0250\u025a\u0001\u0000\u0000\u0000\u0251\u024f\u0001\u0000" +
		"\u0000\u0000\u0252\u0256\u0003p8\u0000\u0253\u0255\u0003Z-\u0000\u0254" +
		"\u0253\u0001\u0000\u0000\u0000\u0255\u0258\u0001\u0000\u0000\u0000\u0256" +
		"\u0254\u0001\u0000\u0000\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257" +
		"\u025a\u0001\u0000\u0000\u0000\u0258\u0256\u0001\u0000\u0000\u0000\u0259" +
		"\u022d\u0001\u0000\u0000\u0000\u0259\u0234\u0001\u0000\u0000\u0000\u0259" +
		"\u023b\u0001\u0000\u0000\u0000\u0259\u0242\u0001\u0000\u0000\u0000\u0259" +
		"\u024b\u0001\u0000\u0000\u0000\u0259\u0252\u0001\u0000\u0000\u0000\u025a" +
		"Y\u0001\u0000\u0000\u0000\u025b\u025f\u0003^/\u0000\u025c\u025f\u0003" +
		"0\u0018\u0000\u025d\u025f\u00032\u0019\u0000\u025e\u025b\u0001\u0000\u0000" +
		"\u0000\u025e\u025c\u0001\u0000\u0000\u0000\u025e\u025d\u0001\u0000\u0000" +
		"\u0000\u025f[\u0001\u0000\u0000\u0000\u0260\u0261\u0003\u008aE\u0000\u0261" +
		"\u0262\u0005\u000b\u0000\u0000\u0262\u0263\u0003v;\u0000\u0263\u0264\u0005" +
		"\f\u0000\u0000\u0264\u0265\u0005\u000b\u0000\u0000\u0265\u0266\u0003>" +
		"\u001f\u0000\u0266\u0267\u0005\f\u0000\u0000\u0267]\u0001\u0000\u0000" +
		"\u0000\u0268\u0271\u0005\u000b\u0000\u0000\u0269\u026e\u0003>\u001f\u0000" +
		"\u026a\u026b\u0005\b\u0000\u0000\u026b\u026d\u0003>\u001f\u0000\u026c" +
		"\u026a\u0001\u0000\u0000\u0000\u026d\u0270\u0001\u0000\u0000\u0000\u026e" +
		"\u026c\u0001\u0000\u0000\u0000\u026e\u026f\u0001\u0000\u0000\u0000\u026f" +
		"\u0272\u0001\u0000\u0000\u0000\u0270\u026e\u0001\u0000\u0000\u0000\u0271" +
		"\u0269\u0001\u0000\u0000\u0000\u0271\u0272\u0001\u0000\u0000\u0000\u0272" +
		"\u0273\u0001\u0000\u0000\u0000\u0273\u0274\u0005\f\u0000\u0000\u0274_" +
		"\u0001\u0000\u0000\u0000\u0275\u0280\u0005)\u0000\u0000\u0276\u0280\u0005" +
		"*\u0000\u0000\u0277\u0280\u0005P\u0000\u0000\u0278\u0280\u0005Q\u0000" +
		"\u0000\u0279\u0280\u0005T\u0000\u0000\u027a\u0280\u0003b1\u0000\u027b" +
		"\u0280\u0003d2\u0000\u027c\u0280\u0003f3\u0000\u027d\u0280\u0003j5\u0000" +
		"\u027e\u0280\u0003l6\u0000\u027f\u0275\u0001\u0000\u0000\u0000\u027f\u0276" +
		"\u0001\u0000\u0000\u0000\u027f\u0277\u0001\u0000\u0000\u0000\u027f\u0278" +
		"\u0001\u0000\u0000\u0000\u027f\u0279\u0001\u0000\u0000\u0000\u027f\u027a" +
		"\u0001\u0000\u0000\u0000\u027f\u027b\u0001\u0000\u0000\u0000\u027f\u027c" +
		"\u0001\u0000\u0000\u0000\u027f\u027d\u0001\u0000\u0000\u0000\u027f\u027e" +
		"\u0001\u0000\u0000\u0000\u0280a\u0001\u0000\u0000\u0000\u0281\u028a\u0005" +
		"\u0007\u0000\u0000\u0282\u0287\u0003>\u001f\u0000\u0283\u0284\u0005\b" +
		"\u0000\u0000\u0284\u0286\u0003>\u001f\u0000\u0285\u0283\u0001\u0000\u0000" +
		"\u0000\u0286\u0289\u0001\u0000\u0000\u0000\u0287\u0285\u0001\u0000\u0000" +
		"\u0000\u0287\u0288\u0001\u0000\u0000\u0000\u0288\u028b\u0001\u0000\u0000" +
		"\u0000\u0289\u0287\u0001\u0000\u0000\u0000\u028a\u0282\u0001\u0000\u0000" +
		"\u0000\u028a\u028b\u0001\u0000\u0000\u0000\u028b\u028c\u0001\u0000\u0000" +
		"\u0000\u028c\u0294\u0005\t\u0000\u0000\u028d\u028e\u0005\u0007\u0000\u0000" +
		"\u028e\u028f\u0003>\u001f\u0000\u028f\u0290\u0005E\u0000\u0000\u0290\u0291" +
		"\u0003t:\u0000\u0291\u0292\u0005\t\u0000\u0000\u0292\u0294\u0001\u0000" +
		"\u0000\u0000\u0293\u0281\u0001\u0000\u0000\u0000\u0293\u028d\u0001\u0000" +
		"\u0000\u0000\u0294c\u0001\u0000\u0000\u0000\u0295\u029e\u0005$\u0000\u0000" +
		"\u0296\u029b\u0003>\u001f\u0000\u0297\u0298\u0005\b\u0000\u0000\u0298" +
		"\u029a\u0003>\u001f\u0000\u0299\u0297\u0001\u0000\u0000\u0000\u029a\u029d" +
		"\u0001\u0000\u0000\u0000\u029b\u0299\u0001\u0000\u0000\u0000\u029b\u029c" +
		"\u0001\u0000\u0000\u0000\u029c\u029f\u0001\u0000\u0000\u0000\u029d\u029b" +
		"\u0001\u0000\u0000\u0000\u029e\u0296\u0001\u0000\u0000\u0000\u029e\u029f" +
		"\u0001\u0000\u0000\u0000\u029f\u02a1\u0001\u0000\u0000\u0000\u02a0\u02a2" +
		"\u0005\b\u0000\u0000\u02a1\u02a0\u0001\u0000\u0000\u0000\u02a1\u02a2\u0001" +
		"\u0000\u0000\u0000\u02a2\u02a3\u0001\u0000\u0000\u0000\u02a3\u02ab\u0005" +
		"%\u0000\u0000\u02a4\u02a5\u0005$\u0000\u0000\u02a5\u02a6\u0003>\u001f" +
		"\u0000\u02a6\u02a7\u0005E\u0000\u0000\u02a7\u02a8\u0003t:\u0000\u02a8" +
		"\u02a9\u0005%\u0000\u0000\u02a9\u02ab\u0001\u0000\u0000\u0000\u02aa\u0295" +
		"\u0001\u0000\u0000\u0000\u02aa\u02a4\u0001\u0000\u0000\u0000\u02abe\u0001" +
		"\u0000\u0000\u0000\u02ac\u02b5\u0005F\u0000\u0000\u02ad\u02b2\u0003h4" +
		"\u0000\u02ae\u02af\u0005E\u0000\u0000\u02af\u02b1\u0003h4\u0000\u02b0" +
		"\u02ae\u0001\u0000\u0000\u0000\u02b1\u02b4\u0001\u0000\u0000\u0000\u02b2" +
		"\u02b0\u0001\u0000\u0000\u0000\u02b2\u02b3\u0001\u0000\u0000\u0000\u02b3" +
		"\u02b6\u0001\u0000\u0000\u0000\u02b4\u02b2\u0001\u0000\u0000\u0000\u02b5" +
		"\u02ad\u0001\u0000\u0000\u0000\u02b5\u02b6\u0001\u0000\u0000\u0000\u02b6" +
		"\u02b7\u0001\u0000\u0000\u0000\u02b7\u02b8\u0005G\u0000\u0000\u02b8g\u0001" +
		"\u0000\u0000\u0000\u02b9\u02be\u0003>\u001f\u0000\u02ba\u02bb\u0005\b" +
		"\u0000\u0000\u02bb\u02bd\u0003>\u001f\u0000\u02bc\u02ba\u0001\u0000\u0000" +
		"\u0000\u02bd\u02c0\u0001\u0000\u0000\u0000\u02be\u02bc\u0001\u0000\u0000" +
		"\u0000\u02be\u02bf\u0001\u0000\u0000\u0000\u02bfi\u0001\u0000\u0000\u0000" +
		"\u02c0\u02be\u0001\u0000\u0000\u0000\u02c1\u02c2\u0005\u000b\u0000\u0000" +
		"\u02c2\u02c3\u0003>\u001f\u0000\u02c3\u02c4\u0005\b\u0000\u0000\u02c4" +
		"\u02c9\u0003>\u001f\u0000\u02c5\u02c6\u0005\b\u0000\u0000\u02c6\u02c8" +
		"\u0003>\u001f\u0000\u02c7\u02c5\u0001\u0000\u0000\u0000\u02c8\u02cb\u0001" +
		"\u0000\u0000\u0000\u02c9\u02c7\u0001\u0000\u0000\u0000\u02c9\u02ca\u0001" +
		"\u0000\u0000\u0000\u02ca\u02cc\u0001\u0000\u0000\u0000\u02cb\u02c9\u0001" +
		"\u0000\u0000\u0000\u02cc\u02cd\u0005\f\u0000\u0000\u02cdk\u0001\u0000" +
		"\u0000\u0000\u02ce\u02cf\u0005\u000b\u0000\u0000\u02cf\u02d0\u0003\u008a" +
		"E\u0000\u02d0\u02d1\u0005\u0004\u0000\u0000\u02d1\u02d2\u0003>\u001f\u0000" +
		"\u02d2\u02d3\u0005\b\u0000\u0000\u02d3\u02d4\u0003\u008aE\u0000\u02d4" +
		"\u02d5\u0005\u0004\u0000\u0000\u02d5\u02dd\u0003>\u001f\u0000\u02d6\u02d7" +
		"\u0005\b\u0000\u0000\u02d7\u02d8\u0003\u008aE\u0000\u02d8\u02d9\u0005" +
		"\u0004\u0000\u0000\u02d9\u02da\u0003>\u001f\u0000\u02da\u02dc\u0001\u0000" +
		"\u0000\u0000\u02db\u02d6\u0001\u0000\u0000\u0000\u02dc\u02df\u0001\u0000" +
		"\u0000\u0000\u02dd\u02db\u0001\u0000\u0000\u0000\u02dd\u02de\u0001\u0000" +
		"\u0000\u0000\u02de\u02e0\u0001\u0000\u0000\u0000\u02df\u02dd\u0001\u0000" +
		"\u0000\u0000\u02e0\u02e1\u0005\f\u0000\u0000\u02e1m\u0001\u0000\u0000" +
		"\u0000\u02e2\u02e3\u0005H\u0000\u0000\u02e3\u02e4\u0003>\u001f\u0000\u02e4" +
		"\u02e5\u0005I\u0000\u0000\u02e5\u02ed\u0003>\u001f\u0000\u02e6\u02e7\u0005" +
		"J\u0000\u0000\u02e7\u02e8\u0003>\u001f\u0000\u02e8\u02e9\u0005I\u0000" +
		"\u0000\u02e9\u02ea\u0003>\u001f\u0000\u02ea\u02ec\u0001\u0000\u0000\u0000" +
		"\u02eb\u02e6\u0001\u0000\u0000\u0000\u02ec\u02ef\u0001\u0000\u0000\u0000" +
		"\u02ed\u02eb\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001\u0000\u0000\u0000" +
		"\u02ee\u02f2\u0001\u0000\u0000\u0000\u02ef\u02ed\u0001\u0000\u0000\u0000" +
		"\u02f0\u02f1\u0005K\u0000\u0000\u02f1\u02f3\u0003>\u001f\u0000\u02f2\u02f0" +
		"\u0001\u0000\u0000\u0000\u02f2\u02f3\u0001\u0000\u0000\u0000\u02f3\u02f4" +
		"\u0001\u0000\u0000\u0000\u02f4\u02f5\u0005L\u0000\u0000\u02f5o\u0001\u0000" +
		"\u0000\u0000\u02f6\u02f7\u0005M\u0000\u0000\u02f7\u02f8\u0005\u0007\u0000" +
		"\u0000\u02f8\u02fd\u0003r9\u0000\u02f9\u02fa\u0005\u0001\u0000\u0000\u02fa" +
		"\u02fc\u0003r9\u0000\u02fb\u02f9\u0001\u0000\u0000\u0000\u02fc\u02ff\u0001" +
		"\u0000\u0000\u0000\u02fd\u02fb\u0001\u0000\u0000\u0000\u02fd\u02fe\u0001" +
		"\u0000\u0000\u0000\u02fe\u0300\u0001\u0000\u0000\u0000\u02ff\u02fd\u0001" +
		"\u0000\u0000\u0000\u0300\u0301\u0005\t\u0000\u0000\u0301\u0302\u00055" +
		"\u0000\u0000\u0302\u0303\u0003>\u001f\u0000\u0303q\u0001\u0000\u0000\u0000" +
		"\u0304\u0307\u0003\u0006\u0003\u0000\u0305\u0307\u0003\u0012\t\u0000\u0306" +
		"\u0304\u0001\u0000\u0000\u0000\u0306\u0305\u0001\u0000\u0000\u0000\u0307" +
		"s\u0001\u0000\u0000\u0000\u0308\u030b\u0003x<\u0000\u0309\u030a\u0005" +
		"N\u0000\u0000\u030a\u030c\u0003>\u001f\u0000\u030b\u0309\u0001\u0000\u0000" +
		"\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u0311\u0001\u0000\u0000" +
		"\u0000\u030d\u030e\u0005\b\u0000\u0000\u030e\u0310\u0003x<\u0000\u030f" +
		"\u030d\u0001\u0000\u0000\u0000\u0310\u0313\u0001\u0000\u0000\u0000\u0311" +
		"\u030f\u0001\u0000\u0000\u0000\u0311\u0312\u0001\u0000\u0000\u0000\u0312" +
		"u\u0001\u0000\u0000\u0000\u0313\u0311\u0001\u0000\u0000\u0000\u0314\u0319" +
		"\u0003x<\u0000\u0315\u0316\u0005\b\u0000\u0000\u0316\u0318\u0003x<\u0000" +
		"\u0317\u0315\u0001\u0000\u0000\u0000\u0318\u031b\u0001\u0000\u0000\u0000" +
		"\u0319\u0317\u0001\u0000\u0000\u0000\u0319\u031a\u0001\u0000\u0000\u0000" +
		"\u031aw\u0001\u0000\u0000\u0000\u031b\u0319\u0001\u0000\u0000\u0000\u031c" +
		"\u031f\u0003\u008aE\u0000\u031d\u031f\u0005\n\u0000\u0000\u031e\u031c" +
		"\u0001\u0000\u0000\u0000\u031e\u031d\u0001\u0000\u0000\u0000\u031f\u0327" +
		"\u0001\u0000\u0000\u0000\u0320\u0323\u0005\b\u0000\u0000\u0321\u0324\u0003" +
		"\u008aE\u0000\u0322\u0324\u0005\n\u0000\u0000\u0323\u0321\u0001\u0000" +
		"\u0000\u0000\u0323\u0322\u0001\u0000\u0000\u0000\u0324\u0326\u0001\u0000" +
		"\u0000\u0000\u0325\u0320\u0001\u0000\u0000\u0000\u0326\u0329\u0001\u0000" +
		"\u0000\u0000\u0327\u0325\u0001\u0000\u0000\u0000\u0327\u0328\u0001\u0000" +
		"\u0000\u0000\u0328\u032a\u0001\u0000\u0000\u0000\u0329\u0327\u0001\u0000" +
		"\u0000\u0000\u032a\u032b\u00055\u0000\u0000\u032b\u032e\u0003>\u001f\u0000" +
		"\u032c\u032d\u0005N\u0000\u0000\u032d\u032f\u0003>\u001f\u0000\u032e\u032c" +
		"\u0001\u0000\u0000\u0000\u032e\u032f\u0001\u0000\u0000\u0000\u032f\u0331" +
		"\u0001\u0000\u0000\u0000\u0330\u0332\u0005\b\u0000\u0000\u0331\u0330\u0001" +
		"\u0000\u0000\u0000\u0331\u0332\u0001\u0000\u0000\u0000\u0332y\u0001\u0000" +
		"\u0000\u0000\u0333\u0341\u0005$\u0000\u0000\u0334\u0335\u0003\u0080@\u0000" +
		"\u0335\u0336\u0005\u0004\u0000\u0000\u0336\u033e\u0003>\u001f\u0000\u0337" +
		"\u0338\u0005\b\u0000\u0000\u0338\u0339\u0003\u0080@\u0000\u0339\u033a" +
		"\u0005\u0004\u0000\u0000\u033a\u033b\u0003>\u001f\u0000\u033b\u033d\u0001" +
		"\u0000\u0000\u0000\u033c\u0337\u0001\u0000\u0000\u0000\u033d\u0340\u0001" +
		"\u0000\u0000\u0000\u033e\u033c\u0001\u0000\u0000\u0000\u033e\u033f\u0001" +
		"\u0000\u0000\u0000\u033f\u0342\u0001\u0000\u0000\u0000\u0340\u033e\u0001" +
		"\u0000\u0000\u0000\u0341\u0334\u0001\u0000\u0000\u0000\u0341\u0342\u0001" +
		"\u0000\u0000\u0000\u0342\u0343\u0001\u0000\u0000\u0000\u0343\u0352\u0005" +
		"%\u0000\u0000\u0344\u034e\u0005$\u0000\u0000\u0345\u0346\u0003\u0080@" +
		"\u0000\u0346\u0347\u0005\u0004\u0000\u0000\u0347\u034a\u0003>\u001f\u0000" +
		"\u0348\u0349\u0005\b\u0000\u0000\u0349\u034b\u0003>\u001f\u0000\u034a" +
		"\u0348\u0001\u0000\u0000\u0000\u034b\u034c\u0001\u0000\u0000\u0000\u034c" +
		"\u034a\u0001\u0000\u0000\u0000\u034c\u034d\u0001\u0000\u0000\u0000\u034d" +
		"\u034f\u0001\u0000\u0000\u0000\u034e\u0345\u0001\u0000\u0000\u0000\u034e" +
		"\u034f\u0001\u0000\u0000\u0000\u034f\u0350\u0001\u0000\u0000\u0000\u0350" +
		"\u0352\u0005%\u0000\u0000\u0351\u0333\u0001\u0000\u0000\u0000\u0351\u0344" +
		"\u0001\u0000\u0000\u0000\u0352{\u0001\u0000\u0000\u0000\u0353\u035c\u0005" +
		"F\u0000\u0000\u0354\u0359\u0003~?\u0000\u0355\u0356\u0005E\u0000\u0000" +
		"\u0356\u0358\u0003~?\u0000\u0357\u0355\u0001\u0000\u0000\u0000\u0358\u035b" +
		"\u0001\u0000\u0000\u0000\u0359\u0357\u0001\u0000\u0000\u0000\u0359\u035a" +
		"\u0001\u0000\u0000\u0000\u035a\u035d\u0001\u0000\u0000\u0000\u035b\u0359" +
		"\u0001\u0000\u0000\u0000\u035c\u0354\u0001\u0000\u0000\u0000\u035c\u035d" +
		"\u0001\u0000\u0000\u0000\u035d\u035e\u0001\u0000\u0000\u0000\u035e\u035f" +
		"\u0005G\u0000\u0000\u035f}\u0001\u0000\u0000\u0000\u0360\u0361\u0003\u0080" +
		"@\u0000\u0361\u0362\u0005\u0004\u0000\u0000\u0362\u0363\u0003>\u001f\u0000" +
		"\u0363\u036b\u0001\u0000\u0000\u0000\u0364\u0365\u0005\b\u0000\u0000\u0365" +
		"\u0366\u0003\u0080@\u0000\u0366\u0367\u0005\u0004\u0000\u0000\u0367\u0368" +
		"\u0003>\u001f\u0000\u0368\u036a\u0001\u0000\u0000\u0000\u0369\u0364\u0001" +
		"\u0000\u0000\u0000\u036a\u036d\u0001\u0000\u0000\u0000\u036b\u0369\u0001" +
		"\u0000\u0000\u0000\u036b\u036c\u0001\u0000\u0000\u0000\u036c\u007f\u0001" +
		"\u0000\u0000\u0000\u036d\u036b\u0001\u0000\u0000\u0000\u036e\u037b\u0003" +
		">\u001f\u0000\u036f\u0370\u0005\u000b\u0000\u0000\u0370\u0375\u0003>\u001f" +
		"\u0000\u0371\u0372\u0005\b\u0000\u0000\u0372\u0374\u0003>\u001f\u0000" +
		"\u0373\u0371\u0001\u0000\u0000\u0000\u0374\u0377\u0001\u0000\u0000\u0000" +
		"\u0375\u0373\u0001\u0000\u0000\u0000\u0375\u0376\u0001\u0000\u0000\u0000" +
		"\u0376\u0378\u0001\u0000\u0000\u0000\u0377\u0375\u0001\u0000\u0000\u0000" +
		"\u0378\u0379\u0005\f\u0000\u0000\u0379\u037b\u0001\u0000\u0000\u0000\u037a" +
		"\u036e\u0001\u0000\u0000\u0000\u037a\u036f\u0001\u0000\u0000\u0000\u037b" +
		"\u0081\u0001\u0000\u0000\u0000\u037c\u037d\u0005$\u0000\u0000\u037d\u037e" +
		"\u0003>\u001f\u0000\u037e\u037f\u0005E\u0000\u0000\u037f\u0380\u0003t" +
		":\u0000\u0380\u0381\u0005%\u0000\u0000\u0381\u0083\u0001\u0000\u0000\u0000" +
		"\u0382\u0383\u0005$\u0000\u0000\u0383\u0384\u0003\u0080@\u0000\u0384\u0385" +
		"\u0005\u0004\u0000\u0000\u0385\u0386\u0003>\u001f\u0000\u0386\u0387\u0005" +
		"E\u0000\u0000\u0387\u0388\u0003t:\u0000\u0388\u0389\u0005%\u0000\u0000" +
		"\u0389\u0085\u0001\u0000\u0000\u0000\u038a\u038b\u0003\u008aE\u0000\u038b" +
		"\u0394\u0005\u000b\u0000\u0000\u038c\u0391\u0003>\u001f\u0000\u038d\u038e" +
		"\u0005\b\u0000\u0000\u038e\u0390\u0003>\u001f\u0000\u038f\u038d\u0001" +
		"\u0000\u0000\u0000\u0390\u0393\u0001\u0000\u0000\u0000\u0391\u038f\u0001" +
		"\u0000\u0000\u0000\u0391\u0392\u0001\u0000\u0000\u0000\u0392\u0395\u0001" +
		"\u0000\u0000\u0000\u0393\u0391\u0001\u0000\u0000\u0000\u0394\u038c\u0001" +
		"\u0000\u0000\u0000\u0394\u0395\u0001\u0000\u0000\u0000\u0395\u0396\u0001" +
		"\u0000\u0000\u0000\u0396\u0397\u0005\f\u0000\u0000\u0397\u0087\u0001\u0000" +
		"\u0000\u0000\u0398\u0399\u0003\u008aE\u0000\u0399\u039a\u0005\u000b\u0000" +
		"\u0000\u039a\u039b\u0003t:\u0000\u039b\u039c\u0005\f\u0000\u0000\u039c" +
		"\u039d\u0005\u000b\u0000\u0000\u039d\u039e\u0003>\u001f\u0000\u039e\u039f" +
		"\u0005\f\u0000\u0000\u039f\u0089\u0001\u0000\u0000\u0000\u03a0\u03a1\u0005" +
		"S\u0000\u0000\u03a1\u008b\u0001\u0000\u0000\u0000]\u008f\u00b8\u00c1\u00c9" +
		"\u00cb\u00d2\u00d9\u00e2\u00f6\u0105\u0117\u011c\u0125\u0137\u013f\u0144" +
		"\u014c\u014f\u0152\u0155\u015a\u015e\u0165\u0170\u0175\u017c\u0184\u018d" +
		"\u0193\u019f\u01b5\u01be\u01c3\u01cc\u01d5\u01dd\u01e5\u01ed\u01f5\u01fd" +
		"\u0205\u020d\u0215\u021d\u0225\u022b\u0231\u0238\u023f\u0248\u024f\u0256" +
		"\u0259\u025e\u026e\u0271\u027f\u0287\u028a\u0293\u029b\u029e\u02a1\u02aa" +
		"\u02b2\u02b5\u02be\u02c9\u02dd\u02ed\u02f2\u02fd\u0306\u030b\u0311\u0319" +
		"\u031e\u0323\u0327\u032e\u0331\u033e\u0341\u034c\u034e\u0351\u0359\u035c" +
		"\u036b\u0375\u037a\u0391\u0394";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
