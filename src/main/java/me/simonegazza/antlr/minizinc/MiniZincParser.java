// Generated from MiniZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.minizinc;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MiniZincParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, EQ=79, INT_LITERAL=80, 
		FLOAT_LITERAL=81, DOLLAR_IDENT=82, IDENT=83, STRING_LITERAL=84, COMMENT=85, 
		WS=86;
	public static final int
		RULE_model = 0, RULE_item = 1, RULE_includeItem = 2, RULE_varDeclItem = 3, 
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
		RULE_callExpr = 67, RULE_genCallExpr = 68;
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
			"indexTuple", "arrayComp", "indexedArrayComp", "callExpr", "genCallExpr"
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
	public String getGrammarFileName() { return "MiniZinc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniZincParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModelContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MiniZincParser.EOF, 0); }
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}
		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917536311903701164L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 127509L) != 0)) {
				{
				{
				setState(138);
				item();
				setState(139);
				match(T__0);
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
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
	public static class ItemContext extends ParserRuleContext {
		public IncludeItemContext includeItem() {
			return getRuleContext(IncludeItemContext.class,0);
		}
		public VarDeclItemContext varDeclItem() {
			return getRuleContext(VarDeclItemContext.class,0);
		}
		public EnumItemContext enumItem() {
			return getRuleContext(EnumItemContext.class,0);
		}
		public TypeInstSynItemContext typeInstSynItem() {
			return getRuleContext(TypeInstSynItemContext.class,0);
		}
		public AssignItemContext assignItem() {
			return getRuleContext(AssignItemContext.class,0);
		}
		public ConstraintItemContext constraintItem() {
			return getRuleContext(ConstraintItemContext.class,0);
		}
		public SolveItemContext solveItem() {
			return getRuleContext(SolveItemContext.class,0);
		}
		public OutputItemContext outputItem() {
			return getRuleContext(OutputItemContext.class,0);
		}
		public PredicateItemContext predicateItem() {
			return getRuleContext(PredicateItemContext.class,0);
		}
		public TestItemContext testItem() {
			return getRuleContext(TestItemContext.class,0);
		}
		public FunctionItemContext functionItem() {
			return getRuleContext(FunctionItemContext.class,0);
		}
		public AnnotationItemContext annotationItem() {
			return getRuleContext(AnnotationItemContext.class,0);
		}
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_item);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				includeItem();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				varDeclItem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				enumItem();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(151);
				typeInstSynItem();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(152);
				assignItem();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(153);
				constraintItem();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(154);
				solveItem();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(155);
				outputItem();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(156);
				predicateItem();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(157);
				testItem();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(158);
				functionItem();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(159);
				annotationItem();
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
	public static class IncludeItemContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(MiniZincParser.STRING_LITERAL, 0); }
		public IncludeItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_includeItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIncludeItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIncludeItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIncludeItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncludeItemContext includeItem() throws RecognitionException {
		IncludeItemContext _localctx = new IncludeItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_includeItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__1);
			setState(163);
			match(STRING_LITERAL);
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
	public static class VarDeclItemContext extends ParserRuleContext {
		public TiExprAndIdContext tiExprAndId() {
			return getRuleContext(TiExprAndIdContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public VarDeclItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterVarDeclItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitVarDeclItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitVarDeclItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclItemContext varDeclItem() throws RecognitionException {
		VarDeclItemContext _localctx = new VarDeclItemContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclItem);
		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				tiExprAndId();
				setState(166);
				annotations();
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(167);
					match(EQ);
					setState(168);
					expr();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(T__2);
				setState(172);
				match(T__3);
				setState(173);
				match(IDENT);
				setState(174);
				annotations();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(175);
					match(EQ);
					setState(176);
					expr();
					}
				}

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
	public static class EnumItemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public EnumCasesListContext enumCasesList() {
			return getRuleContext(EnumCasesListContext.class,0);
		}
		public EnumItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterEnumItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitEnumItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitEnumItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumItemContext enumItem() throws RecognitionException {
		EnumItemContext _localctx = new EnumItemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_enumItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__4);
			setState(182);
			match(IDENT);
			setState(183);
			annotations();
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(184);
				match(EQ);
				setState(185);
				enumCasesList();
				}
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
	public static class EnumCasesListContext extends ParserRuleContext {
		public List<EnumCasesContext> enumCases() {
			return getRuleContexts(EnumCasesContext.class);
		}
		public EnumCasesContext enumCases(int i) {
			return getRuleContext(EnumCasesContext.class,i);
		}
		public EnumCasesListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumCasesList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterEnumCasesList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitEnumCasesList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitEnumCasesList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumCasesListContext enumCasesList() throws RecognitionException {
		EnumCasesListContext _localctx = new EnumCasesListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_enumCasesList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			enumCases();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(189);
				match(T__5);
				setState(190);
				enumCases();
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class EnumCasesContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(MiniZincParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MiniZincParser.IDENT, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public EnumCasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumCases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterEnumCases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitEnumCases(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitEnumCases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumCasesContext enumCases() throws RecognitionException {
		EnumCasesContext _localctx = new EnumCasesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_enumCases);
		int _la;
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				match(T__6);
				setState(197);
				match(IDENT);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(198);
					match(T__7);
					setState(199);
					match(IDENT);
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(205);
				match(T__8);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(T__9);
				setState(207);
				match(T__10);
				setState(208);
				expr();
				setState(209);
				match(T__11);
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				match(IDENT);
				setState(212);
				match(T__10);
				setState(213);
				match(IDENT);
				setState(214);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				match(T__12);
				setState(216);
				match(T__10);
				setState(217);
				expr();
				setState(218);
				match(T__11);
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
	public static class TypeInstSynItemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class,0);
		}
		public TypeInstSynItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeInstSynItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterTypeInstSynItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitTypeInstSynItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitTypeInstSynItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeInstSynItemContext typeInstSynItem() throws RecognitionException {
		TypeInstSynItemContext _localctx = new TypeInstSynItemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeInstSynItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__13);
			setState(223);
			match(IDENT);
			setState(224);
			annotations();
			setState(225);
			match(EQ);
			setState(226);
			tiExpr();
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
	public static class AssignItemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAssignItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAssignItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAssignItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignItemContext assignItem() throws RecognitionException {
		AssignItemContext _localctx = new AssignItemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(IDENT);
			setState(229);
			match(EQ);
			setState(230);
			expr();
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
	public static class ConstraintItemContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(MiniZincParser.STRING_LITERAL, 0); }
		public ConstraintItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterConstraintItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitConstraintItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitConstraintItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintItemContext constraintItem() throws RecognitionException {
		ConstraintItemContext _localctx = new ConstraintItemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_constraintItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__14);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(233);
				match(T__15);
				setState(234);
				match(STRING_LITERAL);
				}
			}

			setState(237);
			expr();
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
	public static class SolveItemContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SolveItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solveItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterSolveItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitSolveItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitSolveItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SolveItemContext solveItem() throws RecognitionException {
		SolveItemContext _localctx = new SolveItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_solveItem);
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(T__16);
				setState(240);
				annotations();
				setState(241);
				match(T__17);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(T__16);
				setState(244);
				annotations();
				setState(245);
				match(T__18);
				setState(246);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(T__16);
				setState(249);
				annotations();
				setState(250);
				match(T__19);
				setState(251);
				expr();
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
	public static class OutputItemContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(MiniZincParser.STRING_LITERAL, 0); }
		public OutputItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterOutputItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitOutputItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitOutputItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputItemContext outputItem() throws RecognitionException {
		OutputItemContext _localctx = new OutputItemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_outputItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__20);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(256);
				match(T__15);
				setState(257);
				match(STRING_LITERAL);
				}
			}

			setState(260);
			expr();
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
	public static class AnnotationItemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AnnotationItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAnnotationItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAnnotationItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAnnotationItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationItemContext annotationItem() throws RecognitionException {
		AnnotationItemContext _localctx = new AnnotationItemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_annotationItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(T__21);
			setState(263);
			match(IDENT);
			setState(264);
			params();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(265);
				match(EQ);
				setState(266);
				expr();
				}
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
	public static class PredicateItemContext extends ParserRuleContext {
		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class,0);
		}
		public PredicateItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterPredicateItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitPredicateItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitPredicateItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateItemContext predicateItem() throws RecognitionException {
		PredicateItemContext _localctx = new PredicateItemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_predicateItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__22);
			setState(270);
			operationItemTail();
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
	public static class TestItemContext extends ParserRuleContext {
		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class,0);
		}
		public TestItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterTestItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitTestItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitTestItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestItemContext testItem() throws RecognitionException {
		TestItemContext _localctx = new TestItemContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_testItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__23);
			setState(273);
			operationItemTail();
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
	public static class FunctionItemContext extends ParserRuleContext {
		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class,0);
		}
		public OperationItemTailContext operationItemTail() {
			return getRuleContext(OperationItemTailContext.class,0);
		}
		public FunctionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterFunctionItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitFunctionItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitFunctionItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionItemContext functionItem() throws RecognitionException {
		FunctionItemContext _localctx = new FunctionItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_functionItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(T__24);
			setState(276);
			tiExpr();
			setState(277);
			match(T__3);
			setState(278);
			operationItemTail();
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
	public static class OperationItemTailContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniZincParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public OperationItemTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationItemTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterOperationItemTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitOperationItemTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitOperationItemTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationItemTailContext operationItemTail() throws RecognitionException {
		OperationItemTailContext _localctx = new OperationItemTailContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_operationItemTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(IDENT);
			setState(281);
			params();
			setState(282);
			annotations();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(283);
				match(EQ);
				setState(284);
				expr();
				}
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
	public static class ParamsContext extends ParserRuleContext {
		public List<TiExprAndIdContext> tiExprAndId() {
			return getRuleContexts(TiExprAndIdContext.class);
		}
		public TiExprAndIdContext tiExprAndId(int i) {
			return getRuleContext(TiExprAndIdContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(287);
				match(T__10);
				setState(288);
				tiExprAndId();
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(289);
					match(T__7);
					setState(290);
					tiExprAndId();
					}
					}
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(296);
				match(T__11);
				}
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
	public static class TiExprAndIdContext extends ParserRuleContext {
		public TiExprContext tiExpr() {
			return getRuleContext(TiExprContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public TiExprAndIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tiExprAndId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterTiExprAndId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitTiExprAndId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitTiExprAndId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TiExprAndIdContext tiExprAndId() throws RecognitionException {
		TiExprAndIdContext _localctx = new TiExprAndIdContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_tiExprAndId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			tiExpr();
			setState(301);
			match(T__3);
			setState(302);
			match(IDENT);
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
	public static class TiExprContext extends ParserRuleContext {
		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class,0);
		}
		public ArrayTiExprContext arrayTiExpr() {
			return getRuleContext(ArrayTiExprContext.class,0);
		}
		public TiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tiExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterTiExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitTiExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitTiExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TiExprContext tiExpr() throws RecognitionException {
		TiExprContext _localctx = new TiExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_tiExpr);
		try {
			setState(306);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				baseTiExpr();
				}
				break;
			case T__34:
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				arrayTiExpr();
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
	public static class BaseTiExprContext extends ParserRuleContext {
		public BaseTiExprTailContext baseTiExprTail() {
			return getRuleContext(BaseTiExprTailContext.class,0);
		}
		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class,0);
		}
		public TerminalNode DOLLAR_IDENT() { return getToken(MiniZincParser.DOLLAR_IDENT, 0); }
		public BaseTiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseTiExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterBaseTiExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitBaseTiExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitBaseTiExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTiExprContext baseTiExpr() throws RecognitionException {
		BaseTiExprContext _localctx = new BaseTiExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_baseTiExpr);
		int _la;
		try {
			setState(324);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__25 || _la==T__26) {
					{
					setState(308);
					_la = _input.LA(1);
					if ( !(_la==T__25 || _la==T__26) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__27) {
					{
					setState(311);
					match(T__27);
					}
				}

				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(314);
					match(T__28);
					}
				}

				setState(317);
				baseTiExprTail();
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(318);
					match(T__5);
					setState(319);
					baseTiExpr();
					}
				}

				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				match(T__2);
				setState(323);
				match(DOLLAR_IDENT);
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
	public static class BaseTiExprTailContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode DOLLAR_IDENT() { return getToken(MiniZincParser.DOLLAR_IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BaseTiExprTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseTiExprTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterBaseTiExprTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitBaseTiExprTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitBaseTiExprTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTiExprTailContext baseTiExprTail() throws RecognitionException {
		BaseTiExprTailContext _localctx = new BaseTiExprTailContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_baseTiExprTail);
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(326);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(327);
				baseType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(328);
				match(DOLLAR_IDENT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(329);
				match(T__29);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(330);
				expr();
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
	public static class BaseTypeContext extends ParserRuleContext {
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) ) {
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
	public static class ArrayTiExprContext extends ParserRuleContext {
		public BaseTiExprContext baseTiExpr() {
			return getRuleContext(BaseTiExprContext.class,0);
		}
		public List<TiExprContext> tiExpr() {
			return getRuleContexts(TiExprContext.class);
		}
		public TiExprContext tiExpr(int i) {
			return getRuleContext(TiExprContext.class,i);
		}
		public ArrayTiExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTiExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayTiExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayTiExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayTiExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTiExprContext arrayTiExpr() throws RecognitionException {
		ArrayTiExprContext _localctx = new ArrayTiExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arrayTiExpr);
		int _la;
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__34:
				enterOuterAlt(_localctx, 1);
				{
				setState(335);
				match(T__34);
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__35) {
					{
					setState(336);
					match(T__35);
					setState(337);
					tiExpr();
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(338);
						match(T__7);
						setState(339);
						tiExpr();
						}
						}
						setState(344);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(345);
					match(T__36);
					}
				}

				setState(349);
				match(T__37);
				setState(350);
				baseTiExpr();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				match(T__38);
				setState(352);
				match(T__37);
				setState(353);
				baseTiExpr();
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
	public static class ArrayAccessTailContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayAccessTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccessTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayAccessTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayAccessTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayAccessTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayAccessTailContext arrayAccessTail() throws RecognitionException {
		ArrayAccessTailContext _localctx = new ArrayAccessTailContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arrayAccessTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(T__35);
			setState(357);
			expr();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(358);
				match(T__7);
				setState(359);
				expr();
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(365);
			match(T__36);
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
	public static class FieldAccessTailContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public TerminalNode INT_LITERAL() { return getToken(MiniZincParser.INT_LITERAL, 0); }
		public FieldAccessTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccessTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterFieldAccessTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitFieldAccessTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitFieldAccessTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldAccessTailContext fieldAccessTail() throws RecognitionException {
		FieldAccessTailContext _localctx = new FieldAccessTailContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fieldAccessTail);
		try {
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				match(T__39);
				setState(368);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				match(T__39);
				setState(370);
				match(INT_LITERAL);
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
	public static class AnnotationsContext extends ParserRuleContext {
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAnnotations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(373);
				match(T__15);
				setState(374);
				annotation();
				}
				}
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class AnnotationContext extends ParserRuleContext {
		public ExprAtomHeadContext exprAtomHead() {
			return getRuleContext(ExprAtomHeadContext.class,0);
		}
		public ExprAtomTailContext exprAtomTail() {
			return getRuleContext(ExprAtomTailContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			exprAtomHead();
			setState(381);
			exprAtomTail();
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
	public static class ExprAtomHeadContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public CallSuffixContext callSuffix() {
			return getRuleContext(CallSuffixContext.class,0);
		}
		public TerminalNode INT_LITERAL() { return getToken(MiniZincParser.INT_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(MiniZincParser.FLOAT_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MiniZincParser.STRING_LITERAL, 0); }
		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ArrayLiteral2dContext arrayLiteral2d() {
			return getRuleContext(ArrayLiteral2dContext.class,0);
		}
		public IndexedArrayLiteralContext indexedArrayLiteral() {
			return getRuleContext(IndexedArrayLiteralContext.class,0);
		}
		public IndexedArrayLiteral2dContext indexedArrayLiteral2d() {
			return getRuleContext(IndexedArrayLiteral2dContext.class,0);
		}
		public TupleLiteralContext tupleLiteral() {
			return getRuleContext(TupleLiteralContext.class,0);
		}
		public RecordLiteralContext recordLiteral() {
			return getRuleContext(RecordLiteralContext.class,0);
		}
		public ArrayCompContext arrayComp() {
			return getRuleContext(ArrayCompContext.class,0);
		}
		public IndexedArrayCompContext indexedArrayComp() {
			return getRuleContext(IndexedArrayCompContext.class,0);
		}
		public AnnLiteralContext annLiteral() {
			return getRuleContext(AnnLiteralContext.class,0);
		}
		public IfThenElseExprContext ifThenElseExpr() {
			return getRuleContext(IfThenElseExprContext.class,0);
		}
		public LetExprContext letExpr() {
			return getRuleContext(LetExprContext.class,0);
		}
		public CallExprContext callExpr() {
			return getRuleContext(CallExprContext.class,0);
		}
		public GenCallExprContext genCallExpr() {
			return getRuleContext(GenCallExprContext.class,0);
		}
		public ExprAtomHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAtomHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterExprAtomHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitExprAtomHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitExprAtomHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprAtomHeadContext exprAtomHead() throws RecognitionException {
		ExprAtomHeadContext _localctx = new ExprAtomHeadContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_exprAtomHead);
		int _la;
		try {
			setState(411);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				match(T__10);
				setState(384);
				expr();
				setState(385);
				match(T__11);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(IDENT);
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(388);
					callSuffix();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				match(T__9);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(392);
				match(T__40);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(393);
				match(T__41);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(394);
				match(INT_LITERAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(395);
				match(FLOAT_LITERAL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(396);
				match(STRING_LITERAL);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(397);
				setLiteral();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(398);
				arrayLiteral();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(399);
				arrayLiteral2d();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(400);
				indexedArrayLiteral();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(401);
				indexedArrayLiteral2d();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(402);
				tupleLiteral();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(403);
				recordLiteral();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(404);
				arrayComp();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(405);
				indexedArrayComp();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(406);
				annLiteral();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(407);
				ifThenElseExpr();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(408);
				letExpr();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(409);
				callExpr();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(410);
				genCallExpr();
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
	public static class AnnLiteralContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AnnLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAnnLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAnnLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAnnLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnLiteralContext annLiteral() throws RecognitionException {
		AnnLiteralContext _localctx = new AnnLiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_annLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(IDENT);
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(414);
				match(T__10);
				setState(415);
				expr();
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(416);
					match(T__7);
					setState(417);
					expr();
					}
					}
					setState(422);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(423);
				match(T__11);
				}
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
	public static class ExprAtomTailContext extends ParserRuleContext {
		public ArrayAccessTailContext arrayAccessTail() {
			return getRuleContext(ArrayAccessTailContext.class,0);
		}
		public ExprAtomTailContext exprAtomTail() {
			return getRuleContext(ExprAtomTailContext.class,0);
		}
		public FieldAccessTailContext fieldAccessTail() {
			return getRuleContext(FieldAccessTailContext.class,0);
		}
		public ExprAtomTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAtomTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterExprAtomTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitExprAtomTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitExprAtomTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprAtomTailContext exprAtomTail() throws RecognitionException {
		ExprAtomTailContext _localctx = new ExprAtomTailContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_exprAtomTail);
		try {
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__8:
			case T__15:
			case T__17:
			case T__18:
			case T__19:
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(428);
				arrayAccessTail();
				setState(429);
				exprAtomTail();
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3);
				{
				setState(431);
				fieldAccessTail();
				setState(432);
				exprAtomTail();
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
	public static class ExprContext extends ParserRuleContext {
		public IffExprContext iffExpr() {
			return getRuleContext(IffExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			iffExpr();
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
	public static class IffExprContext extends ParserRuleContext {
		public List<ImplExprContext> implExpr() {
			return getRuleContexts(ImplExprContext.class);
		}
		public ImplExprContext implExpr(int i) {
			return getRuleContext(ImplExprContext.class,i);
		}
		public IffExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iffExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIffExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIffExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIffExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IffExprContext iffExpr() throws RecognitionException {
		IffExprContext _localctx = new IffExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_iffExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			implExpr();
			setState(443);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(439);
					match(T__42);
					setState(440);
					implExpr();
					}
					} 
				}
				setState(445);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
	public static class ImplExprContext extends ParserRuleContext {
		public List<OrExprContext> orExpr() {
			return getRuleContexts(OrExprContext.class);
		}
		public OrExprContext orExpr(int i) {
			return getRuleContext(OrExprContext.class,i);
		}
		public ImplExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterImplExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitImplExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitImplExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplExprContext implExpr() throws RecognitionException {
		ImplExprContext _localctx = new ImplExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_implExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			orExpr();
			setState(451);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(447);
					match(T__43);
					setState(448);
					orExpr();
					}
					} 
				}
				setState(453);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
	public static class OrExprContext extends ParserRuleContext {
		public List<XorExprContext> xorExpr() {
			return getRuleContexts(XorExprContext.class);
		}
		public XorExprContext xorExpr(int i) {
			return getRuleContext(XorExprContext.class,i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_orExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			xorExpr();
			setState(459);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(455);
					match(T__44);
					setState(456);
					xorExpr();
					}
					} 
				}
				setState(461);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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
	public static class XorExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public XorExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterXorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitXorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitXorExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XorExprContext xorExpr() throws RecognitionException {
		XorExprContext _localctx = new XorExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_xorExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			andExpr();
			setState(467);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(463);
					match(T__45);
					setState(464);
					andExpr();
					}
					} 
				}
				setState(469);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
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
	public static class AndExprContext extends ParserRuleContext {
		public List<CompareExprContext> compareExpr() {
			return getRuleContexts(CompareExprContext.class);
		}
		public CompareExprContext compareExpr(int i) {
			return getRuleContext(CompareExprContext.class,i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_andExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			compareExpr();
			setState(475);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(471);
					match(T__46);
					setState(472);
					compareExpr();
					}
					} 
				}
				setState(477);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
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
	public static class CompareExprContext extends ParserRuleContext {
		public List<SetExprContext> setExpr() {
			return getRuleContexts(SetExprContext.class);
		}
		public SetExprContext setExpr(int i) {
			return getRuleContext(SetExprContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(MiniZincParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(MiniZincParser.EQ, i);
		}
		public CompareExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compareExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterCompareExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitCompareExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitCompareExpr(this);
			else return visitor.visitChildren(this);
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
			setState(478);
			setExpr();
			setState(483);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(479);
					_la = _input.LA(1);
					if ( !(((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & 2147483903L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(480);
					setExpr();
					}
					} 
				}
				setState(485);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
	public static class SetExprContext extends ParserRuleContext {
		public List<RangeExprContext> rangeExpr() {
			return getRuleContexts(RangeExprContext.class);
		}
		public RangeExprContext rangeExpr(int i) {
			return getRuleContext(RangeExprContext.class,i);
		}
		public SetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterSetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitSetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitSetExpr(this);
			else return visitor.visitChildren(this);
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
			setState(486);
			rangeExpr();
			setState(491);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(487);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1080863910568919040L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(488);
					rangeExpr();
					}
					} 
				}
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
	public static class RangeExprContext extends ParserRuleContext {
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public RangeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterRangeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitRangeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitRangeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeExprContext rangeExpr() throws RecognitionException {
		RangeExprContext _localctx = new RangeExprContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_rangeExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			addExpr();
			setState(499);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(495);
					match(T__59);
					setState(496);
					addExpr();
					}
					} 
				}
				setState(501);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
	public static class AddExprContext extends ParserRuleContext {
		public List<MultExprContext> multExpr() {
			return getRuleContexts(MultExprContext.class);
		}
		public MultExprContext multExpr(int i) {
			return getRuleContext(MultExprContext.class,i);
		}
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
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
			setState(502);
			multExpr();
			setState(507);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(503);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917529027641081920L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(504);
					multExpr();
					}
					} 
				}
				setState(509);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
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
	public static class MultExprContext extends ParserRuleContext {
		public List<PowExprContext> powExpr() {
			return getRuleContexts(PowExprContext.class);
		}
		public PowExprContext powExpr(int i) {
			return getRuleContext(PowExprContext.class,i);
		}
		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
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
			setState(510);
			powExpr();
			setState(515);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(511);
					_la = _input.LA(1);
					if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & 15L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(512);
					powExpr();
					}
					} 
				}
				setState(517);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
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
	public static class PowExprContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public PowExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterPowExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitPowExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitPowExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowExprContext powExpr() throws RecognitionException {
		PowExprContext _localctx = new PowExprContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_powExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			unaryExpr();
			setState(523);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(519);
					match(T__66);
					setState(520);
					unaryExpr();
					}
					} 
				}
				setState(525);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
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
	public static class UnaryExprContext extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unaryExpr);
		int _la;
		try {
			setState(529);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
			case T__61:
			case T__67:
				enterOuterAlt(_localctx, 1);
				{
				setState(526);
				_la = _input.LA(1);
				if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 131L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(527);
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
				enterOuterAlt(_localctx, 2);
				{
				setState(528);
				primary();
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
	public static class PrimaryContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public List<PostfixContext> postfix() {
			return getRuleContexts(PostfixContext.class);
		}
		public PostfixContext postfix(int i) {
			return getRuleContext(PostfixContext.class,i);
		}
		public QuantifierExprContext quantifierExpr() {
			return getRuleContext(QuantifierExprContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfThenElseExprContext ifThenElseExpr() {
			return getRuleContext(IfThenElseExprContext.class,0);
		}
		public LetExprContext letExpr() {
			return getRuleContext(LetExprContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_primary);
		try {
			int _alt;
			setState(575);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(531);
				literal();
				setState(535);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(532);
						postfix();
						}
						} 
					}
					setState(537);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(538);
				quantifierExpr();
				setState(542);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(539);
						postfix();
						}
						} 
					}
					setState(544);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(545);
				match(IDENT);
				setState(549);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(546);
						postfix();
						}
						} 
					}
					setState(551);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(552);
				match(T__10);
				setState(553);
				expr();
				setState(554);
				match(T__11);
				setState(558);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(555);
						postfix();
						}
						} 
					}
					setState(560);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(561);
				ifThenElseExpr();
				setState(565);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(562);
						postfix();
						}
						} 
					}
					setState(567);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(568);
				letExpr();
				setState(572);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(569);
						postfix();
						}
						} 
					}
					setState(574);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
				}
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
	public static class PostfixContext extends ParserRuleContext {
		public CallSuffixContext callSuffix() {
			return getRuleContext(CallSuffixContext.class,0);
		}
		public ArrayAccessTailContext arrayAccessTail() {
			return getRuleContext(ArrayAccessTailContext.class,0);
		}
		public FieldAccessTailContext fieldAccessTail() {
			return getRuleContext(FieldAccessTailContext.class,0);
		}
		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_postfix);
		try {
			setState(580);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(577);
				callSuffix();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(578);
				arrayAccessTail();
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3);
				{
				setState(579);
				fieldAccessTail();
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
	public static class QuantifierExprContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public GeneratorListContext generatorList() {
			return getRuleContext(GeneratorListContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public QuantifierExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifierExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterQuantifierExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitQuantifierExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitQuantifierExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantifierExprContext quantifierExpr() throws RecognitionException {
		QuantifierExprContext _localctx = new QuantifierExprContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_quantifierExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			match(IDENT);
			setState(583);
			match(T__10);
			setState(584);
			generatorList();
			setState(585);
			match(T__11);
			setState(586);
			match(T__10);
			setState(587);
			expr();
			setState(588);
			match(T__11);
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
	public static class CallSuffixContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterCallSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitCallSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitCallSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallSuffixContext callSuffix() throws RecognitionException {
		CallSuffixContext _localctx = new CallSuffixContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_callSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(590);
			match(T__10);
			setState(599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
				{
				setState(591);
				expr();
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(592);
					match(T__7);
					setState(593);
					expr();
					}
					}
					setState(598);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(601);
			match(T__11);
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
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() { return getToken(MiniZincParser.INT_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(MiniZincParser.FLOAT_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MiniZincParser.STRING_LITERAL, 0); }
		public SetLiteralContext setLiteral() {
			return getRuleContext(SetLiteralContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ArrayLiteral2dContext arrayLiteral2d() {
			return getRuleContext(ArrayLiteral2dContext.class,0);
		}
		public TupleLiteralContext tupleLiteral() {
			return getRuleContext(TupleLiteralContext.class,0);
		}
		public RecordLiteralContext recordLiteral() {
			return getRuleContext(RecordLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_literal);
		try {
			setState(613);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(603);
				match(T__40);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(604);
				match(T__41);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(605);
				match(INT_LITERAL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(606);
				match(FLOAT_LITERAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(607);
				match(STRING_LITERAL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(608);
				setLiteral();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(609);
				arrayLiteral();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(610);
				arrayLiteral2d();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(611);
				tupleLiteral();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(612);
				recordLiteral();
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
	public static class SetLiteralContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class,0);
		}
		public SetLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterSetLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitSetLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitSetLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetLiteralContext setLiteral() throws RecognitionException {
		SetLiteralContext _localctx = new SetLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_setLiteral);
		int _la;
		try {
			setState(633);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(615);
				match(T__6);
				setState(624);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
					setState(616);
					expr();
					setState(621);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(617);
						match(T__7);
						setState(618);
						expr();
						}
						}
						setState(623);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(626);
				match(T__8);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(627);
				match(T__6);
				setState(628);
				expr();
				setState(629);
				match(T__68);
				setState(630);
				compTail();
				setState(631);
				match(T__8);
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
	public static class ArrayLiteralContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class,0);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_arrayLiteral);
		int _la;
		try {
			setState(653);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(635);
				match(T__35);
				setState(644);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
					setState(636);
					expr();
					setState(641);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(637);
						match(T__7);
						setState(638);
						expr();
						}
						}
						setState(643);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(646);
				match(T__36);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(647);
				match(T__35);
				setState(648);
				expr();
				setState(649);
				match(T__68);
				setState(650);
				compTail();
				setState(651);
				match(T__36);
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
	public static class ArrayLiteral2dContext extends ParserRuleContext {
		public List<ArrayRowContext> arrayRow() {
			return getRuleContexts(ArrayRowContext.class);
		}
		public ArrayRowContext arrayRow(int i) {
			return getRuleContext(ArrayRowContext.class,i);
		}
		public ArrayLiteral2dContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral2d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayLiteral2d(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayLiteral2d(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayLiteral2d(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteral2dContext arrayLiteral2d() throws RecognitionException {
		ArrayLiteral2dContext _localctx = new ArrayLiteral2dContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_arrayLiteral2d);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655);
			match(T__69);
			setState(664);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
				{
				setState(656);
				arrayRow();
				setState(661);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__68) {
					{
					{
					setState(657);
					match(T__68);
					setState(658);
					arrayRow();
					}
					}
					setState(663);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(666);
			match(T__70);
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
	public static class ArrayRowContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayRow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayRowContext arrayRow() throws RecognitionException {
		ArrayRowContext _localctx = new ArrayRowContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_arrayRow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			expr();
			setState(673);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(669);
				match(T__7);
				setState(670);
				expr();
				}
				}
				setState(675);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class TupleLiteralContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TupleLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterTupleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitTupleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitTupleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleLiteralContext tupleLiteral() throws RecognitionException {
		TupleLiteralContext _localctx = new TupleLiteralContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_tupleLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(676);
			match(T__10);
			setState(677);
			expr();
			setState(678);
			match(T__7);
			setState(679);
			expr();
			setState(684);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(680);
				match(T__7);
				setState(681);
				expr();
				}
				}
				setState(686);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(687);
			match(T__11);
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
	public static class RecordLiteralContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(MiniZincParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MiniZincParser.IDENT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RecordLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterRecordLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitRecordLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitRecordLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordLiteralContext recordLiteral() throws RecognitionException {
		RecordLiteralContext _localctx = new RecordLiteralContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_recordLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			match(T__10);
			setState(690);
			match(IDENT);
			setState(691);
			match(T__3);
			setState(692);
			expr();
			setState(693);
			match(T__7);
			setState(694);
			match(IDENT);
			setState(695);
			match(T__3);
			setState(696);
			expr();
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(697);
				match(T__7);
				setState(698);
				match(IDENT);
				setState(699);
				match(T__3);
				setState(700);
				expr();
				}
				}
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(706);
			match(T__11);
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
	public static class IfThenElseExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfThenElseExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenElseExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIfThenElseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIfThenElseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIfThenElseExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfThenElseExprContext ifThenElseExpr() throws RecognitionException {
		IfThenElseExprContext _localctx = new IfThenElseExprContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_ifThenElseExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			match(T__71);
			setState(709);
			expr();
			setState(710);
			match(T__72);
			setState(711);
			expr();
			setState(719);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__73) {
				{
				{
				setState(712);
				match(T__73);
				setState(713);
				expr();
				setState(714);
				match(T__72);
				setState(715);
				expr();
				}
				}
				setState(721);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(724);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__74) {
				{
				setState(722);
				match(T__74);
				setState(723);
				expr();
				}
			}

			setState(726);
			match(T__75);
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
	public static class LetExprContext extends ParserRuleContext {
		public List<LetItemContext> letItem() {
			return getRuleContexts(LetItemContext.class);
		}
		public LetItemContext letItem(int i) {
			return getRuleContext(LetItemContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterLetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitLetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitLetExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetExprContext letExpr() throws RecognitionException {
		LetExprContext _localctx = new LetExprContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_letExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			match(T__76);
			setState(729);
			match(T__6);
			setState(730);
			letItem();
			setState(735);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(731);
				match(T__0);
				setState(732);
				letItem();
				}
				}
				setState(737);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(738);
			match(T__8);
			setState(739);
			match(T__52);
			setState(740);
			expr();
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
	public static class LetItemContext extends ParserRuleContext {
		public VarDeclItemContext varDeclItem() {
			return getRuleContext(VarDeclItemContext.class,0);
		}
		public ConstraintItemContext constraintItem() {
			return getRuleContext(ConstraintItemContext.class,0);
		}
		public LetItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterLetItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitLetItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitLetItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetItemContext letItem() throws RecognitionException {
		LetItemContext _localctx = new LetItemContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_letItem);
		try {
			setState(744);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(742);
				varDeclItem();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(743);
				constraintItem();
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
	public static class CompTailContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}
		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CompTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterCompTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitCompTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitCompTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompTailContext compTail() throws RecognitionException {
		CompTailContext _localctx = new CompTailContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_compTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746);
			generator();
			setState(749);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77) {
				{
				setState(747);
				match(T__77);
				setState(748);
				expr();
				}
			}

			setState(755);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(751);
				match(T__7);
				setState(752);
				generator();
				}
				}
				setState(757);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class GeneratorListContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}
		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public GeneratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterGeneratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitGeneratorList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitGeneratorList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorListContext generatorList() throws RecognitionException {
		GeneratorListContext _localctx = new GeneratorListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_generatorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(758);
			generator();
			setState(763);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(759);
				match(T__7);
				setState(760);
				generator();
				}
				}
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(768);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77) {
				{
				setState(766);
				match(T__77);
				setState(767);
				expr();
				}
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
	public static class GeneratorContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> IDENT() { return getTokens(MiniZincParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MiniZincParser.IDENT, i);
		}
		public GeneratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterGenerator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitGenerator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitGenerator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorContext generator() throws RecognitionException {
		GeneratorContext _localctx = new GeneratorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_generator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==IDENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(775);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(771);
				match(T__7);
				setState(772);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==IDENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(777);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(778);
			match(T__52);
			setState(779);
			expr();
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
	public static class IndexedArrayLiteralContext extends ParserRuleContext {
		public List<IndexTupleContext> indexTuple() {
			return getRuleContexts(IndexTupleContext.class);
		}
		public IndexTupleContext indexTuple(int i) {
			return getRuleContext(IndexTupleContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IndexedArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedArrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIndexedArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIndexedArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIndexedArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayLiteralContext indexedArrayLiteral() throws RecognitionException {
		IndexedArrayLiteralContext _localctx = new IndexedArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_indexedArrayLiteral);
		int _la;
		try {
			setState(811);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(781);
				match(T__35);
				setState(795);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
					setState(782);
					indexTuple();
					setState(783);
					match(T__3);
					setState(784);
					expr();
					setState(792);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(785);
						match(T__7);
						setState(786);
						indexTuple();
						setState(787);
						match(T__3);
						setState(788);
						expr();
						}
						}
						setState(794);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(797);
				match(T__36);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(798);
				match(T__35);
				setState(808);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
					{
					setState(799);
					indexTuple();
					setState(800);
					match(T__3);
					setState(801);
					expr();
					setState(804); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(802);
						match(T__7);
						setState(803);
						expr();
						}
						}
						setState(806); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__7 );
					}
				}

				setState(810);
				match(T__36);
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
	public static class IndexedArrayLiteral2dContext extends ParserRuleContext {
		public List<IndexedArrayRowContext> indexedArrayRow() {
			return getRuleContexts(IndexedArrayRowContext.class);
		}
		public IndexedArrayRowContext indexedArrayRow(int i) {
			return getRuleContext(IndexedArrayRowContext.class,i);
		}
		public IndexedArrayLiteral2dContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedArrayLiteral2d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIndexedArrayLiteral2d(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIndexedArrayLiteral2d(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIndexedArrayLiteral2d(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayLiteral2dContext indexedArrayLiteral2d() throws RecognitionException {
		IndexedArrayLiteral2dContext _localctx = new IndexedArrayLiteral2dContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_indexedArrayLiteral2d);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(813);
			match(T__69);
			setState(822);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
				{
				setState(814);
				indexedArrayRow();
				setState(819);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__68) {
					{
					{
					setState(815);
					match(T__68);
					setState(816);
					indexedArrayRow();
					}
					}
					setState(821);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(824);
			match(T__70);
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
	public static class IndexedArrayRowContext extends ParserRuleContext {
		public List<IndexTupleContext> indexTuple() {
			return getRuleContexts(IndexTupleContext.class);
		}
		public IndexTupleContext indexTuple(int i) {
			return getRuleContext(IndexTupleContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IndexedArrayRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedArrayRow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIndexedArrayRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIndexedArrayRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIndexedArrayRow(this);
			else return visitor.visitChildren(this);
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
			setState(826);
			indexTuple();
			setState(827);
			match(T__3);
			setState(828);
			expr();
			}
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(830);
				match(T__7);
				{
				setState(831);
				indexTuple();
				setState(832);
				match(T__3);
				setState(833);
				expr();
				}
				}
				}
				setState(839);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class IndexTupleContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IndexTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexTuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIndexTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIndexTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIndexTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexTupleContext indexTuple() throws RecognitionException {
		IndexTupleContext _localctx = new IndexTupleContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_indexTuple);
		int _la;
		try {
			setState(852);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(840);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(841);
				match(T__10);
				setState(842);
				expr();
				setState(847);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(843);
					match(T__7);
					setState(844);
					expr();
					}
					}
					setState(849);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(850);
				match(T__11);
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
	public static class ArrayCompContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class,0);
		}
		public ArrayCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayComp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterArrayComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitArrayComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitArrayComp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCompContext arrayComp() throws RecognitionException {
		ArrayCompContext _localctx = new ArrayCompContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_arrayComp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(854);
			match(T__35);
			setState(855);
			expr();
			setState(856);
			match(T__68);
			setState(857);
			compTail();
			setState(858);
			match(T__36);
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
	public static class IndexedArrayCompContext extends ParserRuleContext {
		public IndexTupleContext indexTuple() {
			return getRuleContext(IndexTupleContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class,0);
		}
		public IndexedArrayCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedArrayComp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterIndexedArrayComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitIndexedArrayComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitIndexedArrayComp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedArrayCompContext indexedArrayComp() throws RecognitionException {
		IndexedArrayCompContext _localctx = new IndexedArrayCompContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_indexedArrayComp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(860);
			match(T__35);
			setState(861);
			indexTuple();
			setState(862);
			match(T__3);
			setState(863);
			expr();
			setState(864);
			match(T__68);
			setState(865);
			compTail();
			setState(866);
			match(T__36);
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
	public static class CallExprContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExprContext callExpr() throws RecognitionException {
		CallExprContext _localctx = new CallExprContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_callExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(868);
			match(IDENT);
			setState(869);
			match(T__10);
			setState(878);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6917535693430327424L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 111125L) != 0)) {
				{
				setState(870);
				expr();
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(871);
					match(T__7);
					setState(872);
					expr();
					}
					}
					setState(877);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(880);
			match(T__11);
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
	public static class GenCallExprContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(MiniZincParser.IDENT, 0); }
		public CompTailContext compTail() {
			return getRuleContext(CompTailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public GenCallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genCallExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).enterGenCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniZincListener ) ((MiniZincListener)listener).exitGenCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniZincVisitor ) return ((MiniZincVisitor<? extends T>)visitor).visitGenCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenCallExprContext genCallExpr() throws RecognitionException {
		GenCallExprContext _localctx = new GenCallExprContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_genCallExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(882);
			match(IDENT);
			setState(883);
			match(T__10);
			setState(884);
			compTail();
			setState(885);
			match(T__11);
			setState(886);
			match(T__10);
			setState(887);
			expr();
			setState(888);
			match(T__11);
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
		"\u0004\u0001V\u037b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000\u008e\b\u0000\n\u0000\f\u0000\u0091\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u00a1\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00aa"+
		"\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u00b2\b\u0003\u0003\u0003\u00b4\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00bb\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00c0\b\u0005\n\u0005"+
		"\f\u0005\u00c3\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006\u00c9\b\u0006\n\u0006\f\u0006\u00cc\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00dd\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0003\t\u00ec\b\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00fe\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u0103\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u010c\b\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u011e\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u0124\b\u0011\n\u0011\f\u0011\u0127\t\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u012b\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0133\b\u0013\u0001\u0014"+
		"\u0003\u0014\u0136\b\u0014\u0001\u0014\u0003\u0014\u0139\b\u0014\u0001"+
		"\u0014\u0003\u0014\u013c\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u0141\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0145\b\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u014c\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u0155\b\u0017\n\u0017\f\u0017\u0158"+
		"\t\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u015c\b\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0163\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0169\b\u0018"+
		"\n\u0018\f\u0018\u016c\t\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0174\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0005\u001a\u0178\b\u001a\n\u001a\f\u001a\u017b\t\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u0186\b\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0003\u001c\u019c\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u01a3\b\u001d\n\u001d\f\u001d\u01a6\t\u001d\u0001"+
		"\u001d\u0001\u001d\u0003\u001d\u01aa\b\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01b3"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0005 \u01ba\b "+
		"\n \f \u01bd\t \u0001!\u0001!\u0001!\u0005!\u01c2\b!\n!\f!\u01c5\t!\u0001"+
		"\"\u0001\"\u0001\"\u0005\"\u01ca\b\"\n\"\f\"\u01cd\t\"\u0001#\u0001#\u0001"+
		"#\u0005#\u01d2\b#\n#\f#\u01d5\t#\u0001$\u0001$\u0001$\u0005$\u01da\b$"+
		"\n$\f$\u01dd\t$\u0001%\u0001%\u0001%\u0005%\u01e2\b%\n%\f%\u01e5\t%\u0001"+
		"&\u0001&\u0001&\u0005&\u01ea\b&\n&\f&\u01ed\t&\u0001\'\u0001\'\u0001\'"+
		"\u0005\'\u01f2\b\'\n\'\f\'\u01f5\t\'\u0001(\u0001(\u0001(\u0005(\u01fa"+
		"\b(\n(\f(\u01fd\t(\u0001)\u0001)\u0001)\u0005)\u0202\b)\n)\f)\u0205\t"+
		")\u0001*\u0001*\u0001*\u0005*\u020a\b*\n*\f*\u020d\t*\u0001+\u0001+\u0001"+
		"+\u0003+\u0212\b+\u0001,\u0001,\u0005,\u0216\b,\n,\f,\u0219\t,\u0001,"+
		"\u0001,\u0005,\u021d\b,\n,\f,\u0220\t,\u0001,\u0001,\u0005,\u0224\b,\n"+
		",\f,\u0227\t,\u0001,\u0001,\u0001,\u0001,\u0005,\u022d\b,\n,\f,\u0230"+
		"\t,\u0001,\u0001,\u0005,\u0234\b,\n,\f,\u0237\t,\u0001,\u0001,\u0005,"+
		"\u023b\b,\n,\f,\u023e\t,\u0003,\u0240\b,\u0001-\u0001-\u0001-\u0003-\u0245"+
		"\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u0001/\u0005/\u0253\b/\n/\f/\u0256\t/\u0003/\u0258\b/\u0001/"+
		"\u0001/\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00010\u00030\u0266\b0\u00011\u00011\u00011\u00011\u00051\u026c\b1\n"+
		"1\f1\u026f\t1\u00031\u0271\b1\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00031\u027a\b1\u00012\u00012\u00012\u00012\u00052\u0280\b2\n"+
		"2\f2\u0283\t2\u00032\u0285\b2\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00032\u028e\b2\u00013\u00013\u00013\u00013\u00053\u0294\b3\n"+
		"3\f3\u0297\t3\u00033\u0299\b3\u00013\u00013\u00014\u00014\u00014\u0005"+
		"4\u02a0\b4\n4\f4\u02a3\t4\u00015\u00015\u00015\u00015\u00015\u00015\u0005"+
		"5\u02ab\b5\n5\f5\u02ae\t5\u00015\u00015\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00056\u02be\b6\n6"+
		"\f6\u02c1\t6\u00016\u00016\u00017\u00017\u00017\u00017\u00017\u00017\u0001"+
		"7\u00017\u00017\u00057\u02ce\b7\n7\f7\u02d1\t7\u00017\u00017\u00037\u02d5"+
		"\b7\u00017\u00017\u00018\u00018\u00018\u00018\u00018\u00058\u02de\b8\n"+
		"8\f8\u02e1\t8\u00018\u00018\u00018\u00018\u00019\u00019\u00039\u02e9\b"+
		"9\u0001:\u0001:\u0001:\u0003:\u02ee\b:\u0001:\u0001:\u0005:\u02f2\b:\n"+
		":\f:\u02f5\t:\u0001;\u0001;\u0001;\u0005;\u02fa\b;\n;\f;\u02fd\t;\u0001"+
		";\u0001;\u0003;\u0301\b;\u0001<\u0001<\u0001<\u0005<\u0306\b<\n<\f<\u0309"+
		"\t<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0005=\u0317\b=\n=\f=\u031a\t=\u0003=\u031c\b=\u0001="+
		"\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0004=\u0325\b=\u000b=\f=\u0326"+
		"\u0003=\u0329\b=\u0001=\u0003=\u032c\b=\u0001>\u0001>\u0001>\u0001>\u0005"+
		">\u0332\b>\n>\f>\u0335\t>\u0003>\u0337\b>\u0001>\u0001>\u0001?\u0001?"+
		"\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0005?\u0344\b?\n?\f"+
		"?\u0347\t?\u0001@\u0001@\u0001@\u0001@\u0001@\u0005@\u034e\b@\n@\f@\u0351"+
		"\t@\u0001@\u0001@\u0003@\u0355\b@\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001C\u0001"+
		"C\u0001C\u0001C\u0001C\u0005C\u036a\bC\nC\fC\u036d\tC\u0003C\u036f\bC"+
		"\u0001C\u0001C\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001"+
		"D\u0001D\u0000\u0000E\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u0000\b\u0001\u0000\u001a"+
		"\u001b\u0001\u0000\u001f\"\u0002\u000007OO\u0001\u00008;\u0002\u0000\u0006"+
		"\u0006=>\u0001\u0000?B\u0002\u0000=>DD\u0002\u0000\n\nSS\u03c0\u0000\u008f"+
		"\u0001\u0000\u0000\u0000\u0002\u00a0\u0001\u0000\u0000\u0000\u0004\u00a2"+
		"\u0001\u0000\u0000\u0000\u0006\u00b3\u0001\u0000\u0000\u0000\b\u00b5\u0001"+
		"\u0000\u0000\u0000\n\u00bc\u0001\u0000\u0000\u0000\f\u00dc\u0001\u0000"+
		"\u0000\u0000\u000e\u00de\u0001\u0000\u0000\u0000\u0010\u00e4\u0001\u0000"+
		"\u0000\u0000\u0012\u00e8\u0001\u0000\u0000\u0000\u0014\u00fd\u0001\u0000"+
		"\u0000\u0000\u0016\u00ff\u0001\u0000\u0000\u0000\u0018\u0106\u0001\u0000"+
		"\u0000\u0000\u001a\u010d\u0001\u0000\u0000\u0000\u001c\u0110\u0001\u0000"+
		"\u0000\u0000\u001e\u0113\u0001\u0000\u0000\u0000 \u0118\u0001\u0000\u0000"+
		"\u0000\"\u012a\u0001\u0000\u0000\u0000$\u012c\u0001\u0000\u0000\u0000"+
		"&\u0132\u0001\u0000\u0000\u0000(\u0144\u0001\u0000\u0000\u0000*\u014b"+
		"\u0001\u0000\u0000\u0000,\u014d\u0001\u0000\u0000\u0000.\u0162\u0001\u0000"+
		"\u0000\u00000\u0164\u0001\u0000\u0000\u00002\u0173\u0001\u0000\u0000\u0000"+
		"4\u0179\u0001\u0000\u0000\u00006\u017c\u0001\u0000\u0000\u00008\u019b"+
		"\u0001\u0000\u0000\u0000:\u019d\u0001\u0000\u0000\u0000<\u01b2\u0001\u0000"+
		"\u0000\u0000>\u01b4\u0001\u0000\u0000\u0000@\u01b6\u0001\u0000\u0000\u0000"+
		"B\u01be\u0001\u0000\u0000\u0000D\u01c6\u0001\u0000\u0000\u0000F\u01ce"+
		"\u0001\u0000\u0000\u0000H\u01d6\u0001\u0000\u0000\u0000J\u01de\u0001\u0000"+
		"\u0000\u0000L\u01e6\u0001\u0000\u0000\u0000N\u01ee\u0001\u0000\u0000\u0000"+
		"P\u01f6\u0001\u0000\u0000\u0000R\u01fe\u0001\u0000\u0000\u0000T\u0206"+
		"\u0001\u0000\u0000\u0000V\u0211\u0001\u0000\u0000\u0000X\u023f\u0001\u0000"+
		"\u0000\u0000Z\u0244\u0001\u0000\u0000\u0000\\\u0246\u0001\u0000\u0000"+
		"\u0000^\u024e\u0001\u0000\u0000\u0000`\u0265\u0001\u0000\u0000\u0000b"+
		"\u0279\u0001\u0000\u0000\u0000d\u028d\u0001\u0000\u0000\u0000f\u028f\u0001"+
		"\u0000\u0000\u0000h\u029c\u0001\u0000\u0000\u0000j\u02a4\u0001\u0000\u0000"+
		"\u0000l\u02b1\u0001\u0000\u0000\u0000n\u02c4\u0001\u0000\u0000\u0000p"+
		"\u02d8\u0001\u0000\u0000\u0000r\u02e8\u0001\u0000\u0000\u0000t\u02ea\u0001"+
		"\u0000\u0000\u0000v\u02f6\u0001\u0000\u0000\u0000x\u0302\u0001\u0000\u0000"+
		"\u0000z\u032b\u0001\u0000\u0000\u0000|\u032d\u0001\u0000\u0000\u0000~"+
		"\u033a\u0001\u0000\u0000\u0000\u0080\u0354\u0001\u0000\u0000\u0000\u0082"+
		"\u0356\u0001\u0000\u0000\u0000\u0084\u035c\u0001\u0000\u0000\u0000\u0086"+
		"\u0364\u0001\u0000\u0000\u0000\u0088\u0372\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0003\u0002\u0001\u0000\u008b\u008c\u0005\u0001\u0000\u0000\u008c"+
		"\u008e\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008e"+
		"\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0000\u0000\u0001\u0093"+
		"\u0001\u0001\u0000\u0000\u0000\u0094\u00a1\u0003\u0004\u0002\u0000\u0095"+
		"\u00a1\u0003\u0006\u0003\u0000\u0096\u00a1\u0003\b\u0004\u0000\u0097\u00a1"+
		"\u0003\u000e\u0007\u0000\u0098\u00a1\u0003\u0010\b\u0000\u0099\u00a1\u0003"+
		"\u0012\t\u0000\u009a\u00a1\u0003\u0014\n\u0000\u009b\u00a1\u0003\u0016"+
		"\u000b\u0000\u009c\u00a1\u0003\u001a\r\u0000\u009d\u00a1\u0003\u001c\u000e"+
		"\u0000\u009e\u00a1\u0003\u001e\u000f\u0000\u009f\u00a1\u0003\u0018\f\u0000"+
		"\u00a0\u0094\u0001\u0000\u0000\u0000\u00a0\u0095\u0001\u0000\u0000\u0000"+
		"\u00a0\u0096\u0001\u0000\u0000\u0000\u00a0\u0097\u0001\u0000\u0000\u0000"+
		"\u00a0\u0098\u0001\u0000\u0000\u0000\u00a0\u0099\u0001\u0000\u0000\u0000"+
		"\u00a0\u009a\u0001\u0000\u0000\u0000\u00a0\u009b\u0001\u0000\u0000\u0000"+
		"\u00a0\u009c\u0001\u0000\u0000\u0000\u00a0\u009d\u0001\u0000\u0000\u0000"+
		"\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u0003\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u0002\u0000\u0000"+
		"\u00a3\u00a4\u0005T\u0000\u0000\u00a4\u0005\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a6\u0003$\u0012\u0000\u00a6\u00a9\u00034\u001a\u0000\u00a7\u00a8\u0005"+
		"O\u0000\u0000\u00a8\u00aa\u0003>\u001f\u0000\u00a9\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00b4\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0005\u0003\u0000\u0000\u00ac\u00ad\u0005\u0004\u0000"+
		"\u0000\u00ad\u00ae\u0005S\u0000\u0000\u00ae\u00b1\u00034\u001a\u0000\u00af"+
		"\u00b0\u0005O\u0000\u0000\u00b0\u00b2\u0003>\u001f\u0000\u00b1\u00af\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b3\u00a5\u0001\u0000\u0000\u0000\u00b3\u00ab\u0001"+
		"\u0000\u0000\u0000\u00b4\u0007\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005"+
		"\u0005\u0000\u0000\u00b6\u00b7\u0005S\u0000\u0000\u00b7\u00ba\u00034\u001a"+
		"\u0000\u00b8\u00b9\u0005O\u0000\u0000\u00b9\u00bb\u0003\n\u0005\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\t\u0001\u0000\u0000\u0000\u00bc\u00c1\u0003\f\u0006\u0000\u00bd"+
		"\u00be\u0005\u0006\u0000\u0000\u00be\u00c0\u0003\f\u0006\u0000\u00bf\u00bd"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u000b"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c5"+
		"\u0005\u0007\u0000\u0000\u00c5\u00ca\u0005S\u0000\u0000\u00c6\u00c7\u0005"+
		"\b\u0000\u0000\u00c7\u00c9\u0005S\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00dd\u0005\t\u0000"+
		"\u0000\u00ce\u00cf\u0005\n\u0000\u0000\u00cf\u00d0\u0005\u000b\u0000\u0000"+
		"\u00d0\u00d1\u0003>\u001f\u0000\u00d1\u00d2\u0005\f\u0000\u0000\u00d2"+
		"\u00dd\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005S\u0000\u0000\u00d4\u00d5"+
		"\u0005\u000b\u0000\u0000\u00d5\u00d6\u0005S\u0000\u0000\u00d6\u00dd\u0005"+
		"\f\u0000\u0000\u00d7\u00d8\u0005\r\u0000\u0000\u00d8\u00d9\u0005\u000b"+
		"\u0000\u0000\u00d9\u00da\u0003>\u001f\u0000\u00da\u00db\u0005\f\u0000"+
		"\u0000\u00db\u00dd\u0001\u0000\u0000\u0000\u00dc\u00c4\u0001\u0000\u0000"+
		"\u0000\u00dc\u00ce\u0001\u0000\u0000\u0000\u00dc\u00d3\u0001\u0000\u0000"+
		"\u0000\u00dc\u00d7\u0001\u0000\u0000\u0000\u00dd\r\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0005\u000e\u0000\u0000\u00df\u00e0\u0005S\u0000\u0000\u00e0"+
		"\u00e1\u00034\u001a\u0000\u00e1\u00e2\u0005O\u0000\u0000\u00e2\u00e3\u0003"+
		"&\u0013\u0000\u00e3\u000f\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005S\u0000"+
		"\u0000\u00e5\u00e6\u0005O\u0000\u0000\u00e6\u00e7\u0003>\u001f\u0000\u00e7"+
		"\u0011\u0001\u0000\u0000\u0000\u00e8\u00eb\u0005\u000f\u0000\u0000\u00e9"+
		"\u00ea\u0005\u0010\u0000\u0000\u00ea\u00ec\u0005T\u0000\u0000\u00eb\u00e9"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0003>\u001f\u0000\u00ee\u0013\u0001"+
		"\u0000\u0000\u0000\u00ef\u00f0\u0005\u0011\u0000\u0000\u00f0\u00f1\u0003"+
		"4\u001a\u0000\u00f1\u00f2\u0005\u0012\u0000\u0000\u00f2\u00fe\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0005\u0011\u0000\u0000\u00f4\u00f5\u00034\u001a"+
		"\u0000\u00f5\u00f6\u0005\u0013\u0000\u0000\u00f6\u00f7\u0003>\u001f\u0000"+
		"\u00f7\u00fe\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005\u0011\u0000\u0000"+
		"\u00f9\u00fa\u00034\u001a\u0000\u00fa\u00fb\u0005\u0014\u0000\u0000\u00fb"+
		"\u00fc\u0003>\u001f\u0000\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd\u00ef"+
		"\u0001\u0000\u0000\u0000\u00fd\u00f3\u0001\u0000\u0000\u0000\u00fd\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fe\u0015\u0001\u0000\u0000\u0000\u00ff\u0102"+
		"\u0005\u0015\u0000\u0000\u0100\u0101\u0005\u0010\u0000\u0000\u0101\u0103"+
		"\u0005T\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0105\u0003"+
		">\u001f\u0000\u0105\u0017\u0001\u0000\u0000\u0000\u0106\u0107\u0005\u0016"+
		"\u0000\u0000\u0107\u0108\u0005S\u0000\u0000\u0108\u010b\u0003\"\u0011"+
		"\u0000\u0109\u010a\u0005O\u0000\u0000\u010a\u010c\u0003>\u001f\u0000\u010b"+
		"\u0109\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c"+
		"\u0019\u0001\u0000\u0000\u0000\u010d\u010e\u0005\u0017\u0000\u0000\u010e"+
		"\u010f\u0003 \u0010\u0000\u010f\u001b\u0001\u0000\u0000\u0000\u0110\u0111"+
		"\u0005\u0018\u0000\u0000\u0111\u0112\u0003 \u0010\u0000\u0112\u001d\u0001"+
		"\u0000\u0000\u0000\u0113\u0114\u0005\u0019\u0000\u0000\u0114\u0115\u0003"+
		"&\u0013\u0000\u0115\u0116\u0005\u0004\u0000\u0000\u0116\u0117\u0003 \u0010"+
		"\u0000\u0117\u001f\u0001\u0000\u0000\u0000\u0118\u0119\u0005S\u0000\u0000"+
		"\u0119\u011a\u0003\"\u0011\u0000\u011a\u011d\u00034\u001a\u0000\u011b"+
		"\u011c\u0005O\u0000\u0000\u011c\u011e\u0003>\u001f\u0000\u011d\u011b\u0001"+
		"\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e!\u0001\u0000"+
		"\u0000\u0000\u011f\u0120\u0005\u000b\u0000\u0000\u0120\u0125\u0003$\u0012"+
		"\u0000\u0121\u0122\u0005\b\u0000\u0000\u0122\u0124\u0003$\u0012\u0000"+
		"\u0123\u0121\u0001\u0000\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000"+
		"\u0125\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000"+
		"\u0126\u0128\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000"+
		"\u0128\u0129\u0005\f\u0000\u0000\u0129\u012b\u0001\u0000\u0000\u0000\u012a"+
		"\u011f\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b"+
		"#\u0001\u0000\u0000\u0000\u012c\u012d\u0003&\u0013\u0000\u012d\u012e\u0005"+
		"\u0004\u0000\u0000\u012e\u012f\u0005S\u0000\u0000\u012f%\u0001\u0000\u0000"+
		"\u0000\u0130\u0133\u0003(\u0014\u0000\u0131\u0133\u0003.\u0017\u0000\u0132"+
		"\u0130\u0001\u0000\u0000\u0000\u0132\u0131\u0001\u0000\u0000\u0000\u0133"+
		"\'\u0001\u0000\u0000\u0000\u0134\u0136\u0007\u0000\u0000\u0000\u0135\u0134"+
		"\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0138"+
		"\u0001\u0000\u0000\u0000\u0137\u0139\u0005\u001c\u0000\u0000\u0138\u0137"+
		"\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013b"+
		"\u0001\u0000\u0000\u0000\u013a\u013c\u0005\u001d\u0000\u0000\u013b\u013a"+
		"\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013d"+
		"\u0001\u0000\u0000\u0000\u013d\u0140\u0003*\u0015\u0000\u013e\u013f\u0005"+
		"\u0006\u0000\u0000\u013f\u0141\u0003(\u0014\u0000\u0140\u013e\u0001\u0000"+
		"\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0145\u0001\u0000"+
		"\u0000\u0000\u0142\u0143\u0005\u0003\u0000\u0000\u0143\u0145\u0005R\u0000"+
		"\u0000\u0144\u0135\u0001\u0000\u0000\u0000\u0144\u0142\u0001\u0000\u0000"+
		"\u0000\u0145)\u0001\u0000\u0000\u0000\u0146\u014c\u0005S\u0000\u0000\u0147"+
		"\u014c\u0003,\u0016\u0000\u0148\u014c\u0005R\u0000\u0000\u0149\u014c\u0005"+
		"\u001e\u0000\u0000\u014a\u014c\u0003>\u001f\u0000\u014b\u0146\u0001\u0000"+
		"\u0000\u0000\u014b\u0147\u0001\u0000\u0000\u0000\u014b\u0148\u0001\u0000"+
		"\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014a\u0001\u0000"+
		"\u0000\u0000\u014c+\u0001\u0000\u0000\u0000\u014d\u014e\u0007\u0001\u0000"+
		"\u0000\u014e-\u0001\u0000\u0000\u0000\u014f\u015b\u0005#\u0000\u0000\u0150"+
		"\u0151\u0005$\u0000\u0000\u0151\u0156\u0003&\u0013\u0000\u0152\u0153\u0005"+
		"\b\u0000\u0000\u0153\u0155\u0003&\u0013\u0000\u0154\u0152\u0001\u0000"+
		"\u0000\u0000\u0155\u0158\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000"+
		"\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0159\u0001\u0000"+
		"\u0000\u0000\u0158\u0156\u0001\u0000\u0000\u0000\u0159\u015a\u0005%\u0000"+
		"\u0000\u015a\u015c\u0001\u0000\u0000\u0000\u015b\u0150\u0001\u0000\u0000"+
		"\u0000\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0005&\u0000\u0000\u015e\u0163\u0003(\u0014\u0000\u015f"+
		"\u0160\u0005\'\u0000\u0000\u0160\u0161\u0005&\u0000\u0000\u0161\u0163"+
		"\u0003(\u0014\u0000\u0162\u014f\u0001\u0000\u0000\u0000\u0162\u015f\u0001"+
		"\u0000\u0000\u0000\u0163/\u0001\u0000\u0000\u0000\u0164\u0165\u0005$\u0000"+
		"\u0000\u0165\u016a\u0003>\u001f\u0000\u0166\u0167\u0005\b\u0000\u0000"+
		"\u0167\u0169\u0003>\u001f\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0169"+
		"\u016c\u0001\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016a"+
		"\u016b\u0001\u0000\u0000\u0000\u016b\u016d\u0001\u0000\u0000\u0000\u016c"+
		"\u016a\u0001\u0000\u0000\u0000\u016d\u016e\u0005%\u0000\u0000\u016e1\u0001"+
		"\u0000\u0000\u0000\u016f\u0170\u0005(\u0000\u0000\u0170\u0174\u0005S\u0000"+
		"\u0000\u0171\u0172\u0005(\u0000\u0000\u0172\u0174\u0005P\u0000\u0000\u0173"+
		"\u016f\u0001\u0000\u0000\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174"+
		"3\u0001\u0000\u0000\u0000\u0175\u0176\u0005\u0010\u0000\u0000\u0176\u0178"+
		"\u00036\u001b\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0178\u017b\u0001"+
		"\u0000\u0000\u0000\u0179\u0177\u0001\u0000\u0000\u0000\u0179\u017a\u0001"+
		"\u0000\u0000\u0000\u017a5\u0001\u0000\u0000\u0000\u017b\u0179\u0001\u0000"+
		"\u0000\u0000\u017c\u017d\u00038\u001c\u0000\u017d\u017e\u0003<\u001e\u0000"+
		"\u017e7\u0001\u0000\u0000\u0000\u017f\u0180\u0005\u000b\u0000\u0000\u0180"+
		"\u0181\u0003>\u001f\u0000\u0181\u0182\u0005\f\u0000\u0000\u0182\u019c"+
		"\u0001\u0000\u0000\u0000\u0183\u0185\u0005S\u0000\u0000\u0184\u0186\u0003"+
		"^/\u0000\u0185\u0184\u0001\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000"+
		"\u0000\u0186\u019c\u0001\u0000\u0000\u0000\u0187\u019c\u0005\n\u0000\u0000"+
		"\u0188\u019c\u0005)\u0000\u0000\u0189\u019c\u0005*\u0000\u0000\u018a\u019c"+
		"\u0005P\u0000\u0000\u018b\u019c\u0005Q\u0000\u0000\u018c\u019c\u0005T"+
		"\u0000\u0000\u018d\u019c\u0003b1\u0000\u018e\u019c\u0003d2\u0000\u018f"+
		"\u019c\u0003f3\u0000\u0190\u019c\u0003z=\u0000\u0191\u019c\u0003|>\u0000"+
		"\u0192\u019c\u0003j5\u0000\u0193\u019c\u0003l6\u0000\u0194\u019c\u0003"+
		"\u0082A\u0000\u0195\u019c\u0003\u0084B\u0000\u0196\u019c\u0003:\u001d"+
		"\u0000\u0197\u019c\u0003n7\u0000\u0198\u019c\u0003p8\u0000\u0199\u019c"+
		"\u0003\u0086C\u0000\u019a\u019c\u0003\u0088D\u0000\u019b\u017f\u0001\u0000"+
		"\u0000\u0000\u019b\u0183\u0001\u0000\u0000\u0000\u019b\u0187\u0001\u0000"+
		"\u0000\u0000\u019b\u0188\u0001\u0000\u0000\u0000\u019b\u0189\u0001\u0000"+
		"\u0000\u0000\u019b\u018a\u0001\u0000\u0000\u0000\u019b\u018b\u0001\u0000"+
		"\u0000\u0000\u019b\u018c\u0001\u0000\u0000\u0000\u019b\u018d\u0001\u0000"+
		"\u0000\u0000\u019b\u018e\u0001\u0000\u0000\u0000\u019b\u018f\u0001\u0000"+
		"\u0000\u0000\u019b\u0190\u0001\u0000\u0000\u0000\u019b\u0191\u0001\u0000"+
		"\u0000\u0000\u019b\u0192\u0001\u0000\u0000\u0000\u019b\u0193\u0001\u0000"+
		"\u0000\u0000\u019b\u0194\u0001\u0000\u0000\u0000\u019b\u0195\u0001\u0000"+
		"\u0000\u0000\u019b\u0196\u0001\u0000\u0000\u0000\u019b\u0197\u0001\u0000"+
		"\u0000\u0000\u019b\u0198\u0001\u0000\u0000\u0000\u019b\u0199\u0001\u0000"+
		"\u0000\u0000\u019b\u019a\u0001\u0000\u0000\u0000\u019c9\u0001\u0000\u0000"+
		"\u0000\u019d\u01a9\u0005S\u0000\u0000\u019e\u019f\u0005\u000b\u0000\u0000"+
		"\u019f\u01a4\u0003>\u001f\u0000\u01a0\u01a1\u0005\b\u0000\u0000\u01a1"+
		"\u01a3\u0003>\u001f\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a3\u01a6"+
		"\u0001\u0000\u0000\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000\u01a4\u01a5"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a7\u0001\u0000\u0000\u0000\u01a6\u01a4"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\f\u0000\u0000\u01a8\u01aa\u0001"+
		"\u0000\u0000\u0000\u01a9\u019e\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001"+
		"\u0000\u0000\u0000\u01aa;\u0001\u0000\u0000\u0000\u01ab\u01b3\u0001\u0000"+
		"\u0000\u0000\u01ac\u01ad\u00030\u0018\u0000\u01ad\u01ae\u0003<\u001e\u0000"+
		"\u01ae\u01b3\u0001\u0000\u0000\u0000\u01af\u01b0\u00032\u0019\u0000\u01b0"+
		"\u01b1\u0003<\u001e\u0000\u01b1\u01b3\u0001\u0000\u0000\u0000\u01b2\u01ab"+
		"\u0001\u0000\u0000\u0000\u01b2\u01ac\u0001\u0000\u0000\u0000\u01b2\u01af"+
		"\u0001\u0000\u0000\u0000\u01b3=\u0001\u0000\u0000\u0000\u01b4\u01b5\u0003"+
		"@ \u0000\u01b5?\u0001\u0000\u0000\u0000\u01b6\u01bb\u0003B!\u0000\u01b7"+
		"\u01b8\u0005+\u0000\u0000\u01b8\u01ba\u0003B!\u0000\u01b9\u01b7\u0001"+
		"\u0000\u0000\u0000\u01ba\u01bd\u0001\u0000\u0000\u0000\u01bb\u01b9\u0001"+
		"\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000\u01bcA\u0001\u0000"+
		"\u0000\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01be\u01c3\u0003D\""+
		"\u0000\u01bf\u01c0\u0005,\u0000\u0000\u01c0\u01c2\u0003D\"\u0000\u01c1"+
		"\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c5\u0001\u0000\u0000\u0000\u01c3"+
		"\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4"+
		"C\u0001\u0000\u0000\u0000\u01c5\u01c3\u0001\u0000\u0000\u0000\u01c6\u01cb"+
		"\u0003F#\u0000\u01c7\u01c8\u0005-\u0000\u0000\u01c8\u01ca\u0003F#\u0000"+
		"\u01c9\u01c7\u0001\u0000\u0000\u0000\u01ca\u01cd\u0001\u0000\u0000\u0000"+
		"\u01cb\u01c9\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000"+
		"\u01ccE\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000\u01ce"+
		"\u01d3\u0003H$\u0000\u01cf\u01d0\u0005.\u0000\u0000\u01d0\u01d2\u0003"+
		"H$\u0000\u01d1\u01cf\u0001\u0000\u0000\u0000\u01d2\u01d5\u0001\u0000\u0000"+
		"\u0000\u01d3\u01d1\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000"+
		"\u0000\u01d4G\u0001\u0000\u0000\u0000\u01d5\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d6\u01db\u0003J%\u0000\u01d7\u01d8\u0005/\u0000\u0000\u01d8\u01da"+
		"\u0003J%\u0000\u01d9\u01d7\u0001\u0000\u0000\u0000\u01da\u01dd\u0001\u0000"+
		"\u0000\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01db\u01dc\u0001\u0000"+
		"\u0000\u0000\u01dcI\u0001\u0000\u0000\u0000\u01dd\u01db\u0001\u0000\u0000"+
		"\u0000\u01de\u01e3\u0003L&\u0000\u01df\u01e0\u0007\u0002\u0000\u0000\u01e0"+
		"\u01e2\u0003L&\u0000\u01e1\u01df\u0001\u0000\u0000\u0000\u01e2\u01e5\u0001"+
		"\u0000\u0000\u0000\u01e3\u01e1\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e4K\u0001\u0000\u0000\u0000\u01e5\u01e3\u0001\u0000"+
		"\u0000\u0000\u01e6\u01eb\u0003N\'\u0000\u01e7\u01e8\u0007\u0003\u0000"+
		"\u0000\u01e8\u01ea\u0003N\'\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000"+
		"\u01ea\u01ed\u0001\u0000\u0000\u0000\u01eb\u01e9\u0001\u0000\u0000\u0000"+
		"\u01eb\u01ec\u0001\u0000\u0000\u0000\u01ecM\u0001\u0000\u0000\u0000\u01ed"+
		"\u01eb\u0001\u0000\u0000\u0000\u01ee\u01f3\u0003P(\u0000\u01ef\u01f0\u0005"+
		"<\u0000\u0000\u01f0\u01f2\u0003P(\u0000\u01f1\u01ef\u0001\u0000\u0000"+
		"\u0000\u01f2\u01f5\u0001\u0000\u0000\u0000\u01f3\u01f1\u0001\u0000\u0000"+
		"\u0000\u01f3\u01f4\u0001\u0000\u0000\u0000\u01f4O\u0001\u0000\u0000\u0000"+
		"\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f6\u01fb\u0003R)\u0000\u01f7\u01f8"+
		"\u0007\u0004\u0000\u0000\u01f8\u01fa\u0003R)\u0000\u01f9\u01f7\u0001\u0000"+
		"\u0000\u0000\u01fa\u01fd\u0001\u0000\u0000\u0000\u01fb\u01f9\u0001\u0000"+
		"\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fcQ\u0001\u0000\u0000"+
		"\u0000\u01fd\u01fb\u0001\u0000\u0000\u0000\u01fe\u0203\u0003T*\u0000\u01ff"+
		"\u0200\u0007\u0005\u0000\u0000\u0200\u0202\u0003T*\u0000\u0201\u01ff\u0001"+
		"\u0000\u0000\u0000\u0202\u0205\u0001\u0000\u0000\u0000\u0203\u0201\u0001"+
		"\u0000\u0000\u0000\u0203\u0204\u0001\u0000\u0000\u0000\u0204S\u0001\u0000"+
		"\u0000\u0000\u0205\u0203\u0001\u0000\u0000\u0000\u0206\u020b\u0003V+\u0000"+
		"\u0207\u0208\u0005C\u0000\u0000\u0208\u020a\u0003V+\u0000\u0209\u0207"+
		"\u0001\u0000\u0000\u0000\u020a\u020d\u0001\u0000\u0000\u0000\u020b\u0209"+
		"\u0001\u0000\u0000\u0000\u020b\u020c\u0001\u0000\u0000\u0000\u020cU\u0001"+
		"\u0000\u0000\u0000\u020d\u020b\u0001\u0000\u0000\u0000\u020e\u020f\u0007"+
		"\u0006\u0000\u0000\u020f\u0212\u0003V+\u0000\u0210\u0212\u0003X,\u0000"+
		"\u0211\u020e\u0001\u0000\u0000\u0000\u0211\u0210\u0001\u0000\u0000\u0000"+
		"\u0212W\u0001\u0000\u0000\u0000\u0213\u0217\u0003`0\u0000\u0214\u0216"+
		"\u0003Z-\u0000\u0215\u0214\u0001\u0000\u0000\u0000\u0216\u0219\u0001\u0000"+
		"\u0000\u0000\u0217\u0215\u0001\u0000\u0000\u0000\u0217\u0218\u0001\u0000"+
		"\u0000\u0000\u0218\u0240\u0001\u0000\u0000\u0000\u0219\u0217\u0001\u0000"+
		"\u0000\u0000\u021a\u021e\u0003\\.\u0000\u021b\u021d\u0003Z-\u0000\u021c"+
		"\u021b\u0001\u0000\u0000\u0000\u021d\u0220\u0001\u0000\u0000\u0000\u021e"+
		"\u021c\u0001\u0000\u0000\u0000\u021e\u021f\u0001\u0000\u0000\u0000\u021f"+
		"\u0240\u0001\u0000\u0000\u0000\u0220\u021e\u0001\u0000\u0000\u0000\u0221"+
		"\u0225\u0005S\u0000\u0000\u0222\u0224\u0003Z-\u0000\u0223\u0222\u0001"+
		"\u0000\u0000\u0000\u0224\u0227\u0001\u0000\u0000\u0000\u0225\u0223\u0001"+
		"\u0000\u0000\u0000\u0225\u0226\u0001\u0000\u0000\u0000\u0226\u0240\u0001"+
		"\u0000\u0000\u0000\u0227\u0225\u0001\u0000\u0000\u0000\u0228\u0229\u0005"+
		"\u000b\u0000\u0000\u0229\u022a\u0003>\u001f\u0000\u022a\u022e\u0005\f"+
		"\u0000\u0000\u022b\u022d\u0003Z-\u0000\u022c\u022b\u0001\u0000\u0000\u0000"+
		"\u022d\u0230\u0001\u0000\u0000\u0000\u022e\u022c\u0001\u0000\u0000\u0000"+
		"\u022e\u022f\u0001\u0000\u0000\u0000\u022f\u0240\u0001\u0000\u0000\u0000"+
		"\u0230\u022e\u0001\u0000\u0000\u0000\u0231\u0235\u0003n7\u0000\u0232\u0234"+
		"\u0003Z-\u0000\u0233\u0232\u0001\u0000\u0000\u0000\u0234\u0237\u0001\u0000"+
		"\u0000\u0000\u0235\u0233\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000"+
		"\u0000\u0000\u0236\u0240\u0001\u0000\u0000\u0000\u0237\u0235\u0001\u0000"+
		"\u0000\u0000\u0238\u023c\u0003p8\u0000\u0239\u023b\u0003Z-\u0000\u023a"+
		"\u0239\u0001\u0000\u0000\u0000\u023b\u023e\u0001\u0000\u0000\u0000\u023c"+
		"\u023a\u0001\u0000\u0000\u0000\u023c\u023d\u0001\u0000\u0000\u0000\u023d"+
		"\u0240\u0001\u0000\u0000\u0000\u023e\u023c\u0001\u0000\u0000\u0000\u023f"+
		"\u0213\u0001\u0000\u0000\u0000\u023f\u021a\u0001\u0000\u0000\u0000\u023f"+
		"\u0221\u0001\u0000\u0000\u0000\u023f\u0228\u0001\u0000\u0000\u0000\u023f"+
		"\u0231\u0001\u0000\u0000\u0000\u023f\u0238\u0001\u0000\u0000\u0000\u0240"+
		"Y\u0001\u0000\u0000\u0000\u0241\u0245\u0003^/\u0000\u0242\u0245\u0003"+
		"0\u0018\u0000\u0243\u0245\u00032\u0019\u0000\u0244\u0241\u0001\u0000\u0000"+
		"\u0000\u0244\u0242\u0001\u0000\u0000\u0000\u0244\u0243\u0001\u0000\u0000"+
		"\u0000\u0245[\u0001\u0000\u0000\u0000\u0246\u0247\u0005S\u0000\u0000\u0247"+
		"\u0248\u0005\u000b\u0000\u0000\u0248\u0249\u0003v;\u0000\u0249\u024a\u0005"+
		"\f\u0000\u0000\u024a\u024b\u0005\u000b\u0000\u0000\u024b\u024c\u0003>"+
		"\u001f\u0000\u024c\u024d\u0005\f\u0000\u0000\u024d]\u0001\u0000\u0000"+
		"\u0000\u024e\u0257\u0005\u000b\u0000\u0000\u024f\u0254\u0003>\u001f\u0000"+
		"\u0250\u0251\u0005\b\u0000\u0000\u0251\u0253\u0003>\u001f\u0000\u0252"+
		"\u0250\u0001\u0000\u0000\u0000\u0253\u0256\u0001\u0000\u0000\u0000\u0254"+
		"\u0252\u0001\u0000\u0000\u0000\u0254\u0255\u0001\u0000\u0000\u0000\u0255"+
		"\u0258\u0001\u0000\u0000\u0000\u0256\u0254\u0001\u0000\u0000\u0000\u0257"+
		"\u024f\u0001\u0000\u0000\u0000\u0257\u0258\u0001\u0000\u0000\u0000\u0258"+
		"\u0259\u0001\u0000\u0000\u0000\u0259\u025a\u0005\f\u0000\u0000\u025a_"+
		"\u0001\u0000\u0000\u0000\u025b\u0266\u0005)\u0000\u0000\u025c\u0266\u0005"+
		"*\u0000\u0000\u025d\u0266\u0005P\u0000\u0000\u025e\u0266\u0005Q\u0000"+
		"\u0000\u025f\u0266\u0005T\u0000\u0000\u0260\u0266\u0003b1\u0000\u0261"+
		"\u0266\u0003d2\u0000\u0262\u0266\u0003f3\u0000\u0263\u0266\u0003j5\u0000"+
		"\u0264\u0266\u0003l6\u0000\u0265\u025b\u0001\u0000\u0000\u0000\u0265\u025c"+
		"\u0001\u0000\u0000\u0000\u0265\u025d\u0001\u0000\u0000\u0000\u0265\u025e"+
		"\u0001\u0000\u0000\u0000\u0265\u025f\u0001\u0000\u0000\u0000\u0265\u0260"+
		"\u0001\u0000\u0000\u0000\u0265\u0261\u0001\u0000\u0000\u0000\u0265\u0262"+
		"\u0001\u0000\u0000\u0000\u0265\u0263\u0001\u0000\u0000\u0000\u0265\u0264"+
		"\u0001\u0000\u0000\u0000\u0266a\u0001\u0000\u0000\u0000\u0267\u0270\u0005"+
		"\u0007\u0000\u0000\u0268\u026d\u0003>\u001f\u0000\u0269\u026a\u0005\b"+
		"\u0000\u0000\u026a\u026c\u0003>\u001f\u0000\u026b\u0269\u0001\u0000\u0000"+
		"\u0000\u026c\u026f\u0001\u0000\u0000\u0000\u026d\u026b\u0001\u0000\u0000"+
		"\u0000\u026d\u026e\u0001\u0000\u0000\u0000\u026e\u0271\u0001\u0000\u0000"+
		"\u0000\u026f\u026d\u0001\u0000\u0000\u0000\u0270\u0268\u0001\u0000\u0000"+
		"\u0000\u0270\u0271\u0001\u0000\u0000\u0000\u0271\u0272\u0001\u0000\u0000"+
		"\u0000\u0272\u027a\u0005\t\u0000\u0000\u0273\u0274\u0005\u0007\u0000\u0000"+
		"\u0274\u0275\u0003>\u001f\u0000\u0275\u0276\u0005E\u0000\u0000\u0276\u0277"+
		"\u0003t:\u0000\u0277\u0278\u0005\t\u0000\u0000\u0278\u027a\u0001\u0000"+
		"\u0000\u0000\u0279\u0267\u0001\u0000\u0000\u0000\u0279\u0273\u0001\u0000"+
		"\u0000\u0000\u027ac\u0001\u0000\u0000\u0000\u027b\u0284\u0005$\u0000\u0000"+
		"\u027c\u0281\u0003>\u001f\u0000\u027d\u027e\u0005\b\u0000\u0000\u027e"+
		"\u0280\u0003>\u001f\u0000\u027f\u027d\u0001\u0000\u0000\u0000\u0280\u0283"+
		"\u0001\u0000\u0000\u0000\u0281\u027f\u0001\u0000\u0000\u0000\u0281\u0282"+
		"\u0001\u0000\u0000\u0000\u0282\u0285\u0001\u0000\u0000\u0000\u0283\u0281"+
		"\u0001\u0000\u0000\u0000\u0284\u027c\u0001\u0000\u0000\u0000\u0284\u0285"+
		"\u0001\u0000\u0000\u0000\u0285\u0286\u0001\u0000\u0000\u0000\u0286\u028e"+
		"\u0005%\u0000\u0000\u0287\u0288\u0005$\u0000\u0000\u0288\u0289\u0003>"+
		"\u001f\u0000\u0289\u028a\u0005E\u0000\u0000\u028a\u028b\u0003t:\u0000"+
		"\u028b\u028c\u0005%\u0000\u0000\u028c\u028e\u0001\u0000\u0000\u0000\u028d"+
		"\u027b\u0001\u0000\u0000\u0000\u028d\u0287\u0001\u0000\u0000\u0000\u028e"+
		"e\u0001\u0000\u0000\u0000\u028f\u0298\u0005F\u0000\u0000\u0290\u0295\u0003"+
		"h4\u0000\u0291\u0292\u0005E\u0000\u0000\u0292\u0294\u0003h4\u0000\u0293"+
		"\u0291\u0001\u0000\u0000\u0000\u0294\u0297\u0001\u0000\u0000\u0000\u0295"+
		"\u0293\u0001\u0000\u0000\u0000\u0295\u0296\u0001\u0000\u0000\u0000\u0296"+
		"\u0299\u0001\u0000\u0000\u0000\u0297\u0295\u0001\u0000\u0000\u0000\u0298"+
		"\u0290\u0001\u0000\u0000\u0000\u0298\u0299\u0001\u0000\u0000\u0000\u0299"+
		"\u029a\u0001\u0000\u0000\u0000\u029a\u029b\u0005G\u0000\u0000\u029bg\u0001"+
		"\u0000\u0000\u0000\u029c\u02a1\u0003>\u001f\u0000\u029d\u029e\u0005\b"+
		"\u0000\u0000\u029e\u02a0\u0003>\u001f\u0000\u029f\u029d\u0001\u0000\u0000"+
		"\u0000\u02a0\u02a3\u0001\u0000\u0000\u0000\u02a1\u029f\u0001\u0000\u0000"+
		"\u0000\u02a1\u02a2\u0001\u0000\u0000\u0000\u02a2i\u0001\u0000\u0000\u0000"+
		"\u02a3\u02a1\u0001\u0000\u0000\u0000\u02a4\u02a5\u0005\u000b\u0000\u0000"+
		"\u02a5\u02a6\u0003>\u001f\u0000\u02a6\u02a7\u0005\b\u0000\u0000\u02a7"+
		"\u02ac\u0003>\u001f\u0000\u02a8\u02a9\u0005\b\u0000\u0000\u02a9\u02ab"+
		"\u0003>\u001f\u0000\u02aa\u02a8\u0001\u0000\u0000\u0000\u02ab\u02ae\u0001"+
		"\u0000\u0000\u0000\u02ac\u02aa\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001"+
		"\u0000\u0000\u0000\u02ad\u02af\u0001\u0000\u0000\u0000\u02ae\u02ac\u0001"+
		"\u0000\u0000\u0000\u02af\u02b0\u0005\f\u0000\u0000\u02b0k\u0001\u0000"+
		"\u0000\u0000\u02b1\u02b2\u0005\u000b\u0000\u0000\u02b2\u02b3\u0005S\u0000"+
		"\u0000\u02b3\u02b4\u0005\u0004\u0000\u0000\u02b4\u02b5\u0003>\u001f\u0000"+
		"\u02b5\u02b6\u0005\b\u0000\u0000\u02b6\u02b7\u0005S\u0000\u0000\u02b7"+
		"\u02b8\u0005\u0004\u0000\u0000\u02b8\u02bf\u0003>\u001f\u0000\u02b9\u02ba"+
		"\u0005\b\u0000\u0000\u02ba\u02bb\u0005S\u0000\u0000\u02bb\u02bc\u0005"+
		"\u0004\u0000\u0000\u02bc\u02be\u0003>\u001f\u0000\u02bd\u02b9\u0001\u0000"+
		"\u0000\u0000\u02be\u02c1\u0001\u0000\u0000\u0000\u02bf\u02bd\u0001\u0000"+
		"\u0000\u0000\u02bf\u02c0\u0001\u0000\u0000\u0000\u02c0\u02c2\u0001\u0000"+
		"\u0000\u0000\u02c1\u02bf\u0001\u0000\u0000\u0000\u02c2\u02c3\u0005\f\u0000"+
		"\u0000\u02c3m\u0001\u0000\u0000\u0000\u02c4\u02c5\u0005H\u0000\u0000\u02c5"+
		"\u02c6\u0003>\u001f\u0000\u02c6\u02c7\u0005I\u0000\u0000\u02c7\u02cf\u0003"+
		">\u001f\u0000\u02c8\u02c9\u0005J\u0000\u0000\u02c9\u02ca\u0003>\u001f"+
		"\u0000\u02ca\u02cb\u0005I\u0000\u0000\u02cb\u02cc\u0003>\u001f\u0000\u02cc"+
		"\u02ce\u0001\u0000\u0000\u0000\u02cd\u02c8\u0001\u0000\u0000\u0000\u02ce"+
		"\u02d1\u0001\u0000\u0000\u0000\u02cf\u02cd\u0001\u0000\u0000\u0000\u02cf"+
		"\u02d0\u0001\u0000\u0000\u0000\u02d0\u02d4\u0001\u0000\u0000\u0000\u02d1"+
		"\u02cf\u0001\u0000\u0000\u0000\u02d2\u02d3\u0005K\u0000\u0000\u02d3\u02d5"+
		"\u0003>\u001f\u0000\u02d4\u02d2\u0001\u0000\u0000\u0000\u02d4\u02d5\u0001"+
		"\u0000\u0000\u0000\u02d5\u02d6\u0001\u0000\u0000\u0000\u02d6\u02d7\u0005"+
		"L\u0000\u0000\u02d7o\u0001\u0000\u0000\u0000\u02d8\u02d9\u0005M\u0000"+
		"\u0000\u02d9\u02da\u0005\u0007\u0000\u0000\u02da\u02df\u0003r9\u0000\u02db"+
		"\u02dc\u0005\u0001\u0000\u0000\u02dc\u02de\u0003r9\u0000\u02dd\u02db\u0001"+
		"\u0000\u0000\u0000\u02de\u02e1\u0001\u0000\u0000\u0000\u02df\u02dd\u0001"+
		"\u0000\u0000\u0000\u02df\u02e0\u0001\u0000\u0000\u0000\u02e0\u02e2\u0001"+
		"\u0000\u0000\u0000\u02e1\u02df\u0001\u0000\u0000\u0000\u02e2\u02e3\u0005"+
		"\t\u0000\u0000\u02e3\u02e4\u00055\u0000\u0000\u02e4\u02e5\u0003>\u001f"+
		"\u0000\u02e5q\u0001\u0000\u0000\u0000\u02e6\u02e9\u0003\u0006\u0003\u0000"+
		"\u02e7\u02e9\u0003\u0012\t\u0000\u02e8\u02e6\u0001\u0000\u0000\u0000\u02e8"+
		"\u02e7\u0001\u0000\u0000\u0000\u02e9s\u0001\u0000\u0000\u0000\u02ea\u02ed"+
		"\u0003x<\u0000\u02eb\u02ec\u0005N\u0000\u0000\u02ec\u02ee\u0003>\u001f"+
		"\u0000\u02ed\u02eb\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001\u0000\u0000"+
		"\u0000\u02ee\u02f3\u0001\u0000\u0000\u0000\u02ef\u02f0\u0005\b\u0000\u0000"+
		"\u02f0\u02f2\u0003x<\u0000\u02f1\u02ef\u0001\u0000\u0000\u0000\u02f2\u02f5"+
		"\u0001\u0000\u0000\u0000\u02f3\u02f1\u0001\u0000\u0000\u0000\u02f3\u02f4"+
		"\u0001\u0000\u0000\u0000\u02f4u\u0001\u0000\u0000\u0000\u02f5\u02f3\u0001"+
		"\u0000\u0000\u0000\u02f6\u02fb\u0003x<\u0000\u02f7\u02f8\u0005\b\u0000"+
		"\u0000\u02f8\u02fa\u0003x<\u0000\u02f9\u02f7\u0001\u0000\u0000\u0000\u02fa"+
		"\u02fd\u0001\u0000\u0000\u0000\u02fb\u02f9\u0001\u0000\u0000\u0000\u02fb"+
		"\u02fc\u0001\u0000\u0000\u0000\u02fc\u0300\u0001\u0000\u0000\u0000\u02fd"+
		"\u02fb\u0001\u0000\u0000\u0000\u02fe\u02ff\u0005N\u0000\u0000\u02ff\u0301"+
		"\u0003>\u001f\u0000\u0300\u02fe\u0001\u0000\u0000\u0000\u0300\u0301\u0001"+
		"\u0000\u0000\u0000\u0301w\u0001\u0000\u0000\u0000\u0302\u0307\u0007\u0007"+
		"\u0000\u0000\u0303\u0304\u0005\b\u0000\u0000\u0304\u0306\u0007\u0007\u0000"+
		"\u0000\u0305\u0303\u0001\u0000\u0000\u0000\u0306\u0309\u0001\u0000\u0000"+
		"\u0000\u0307\u0305\u0001\u0000\u0000\u0000\u0307\u0308\u0001\u0000\u0000"+
		"\u0000\u0308\u030a\u0001\u0000\u0000\u0000\u0309\u0307\u0001\u0000\u0000"+
		"\u0000\u030a\u030b\u00055\u0000\u0000\u030b\u030c\u0003>\u001f\u0000\u030c"+
		"y\u0001\u0000\u0000\u0000\u030d\u031b\u0005$\u0000\u0000\u030e\u030f\u0003"+
		"\u0080@\u0000\u030f\u0310\u0005\u0004\u0000\u0000\u0310\u0318\u0003>\u001f"+
		"\u0000\u0311\u0312\u0005\b\u0000\u0000\u0312\u0313\u0003\u0080@\u0000"+
		"\u0313\u0314\u0005\u0004\u0000\u0000\u0314\u0315\u0003>\u001f\u0000\u0315"+
		"\u0317\u0001\u0000\u0000\u0000\u0316\u0311\u0001\u0000\u0000\u0000\u0317"+
		"\u031a\u0001\u0000\u0000\u0000\u0318\u0316\u0001\u0000\u0000\u0000\u0318"+
		"\u0319\u0001\u0000\u0000\u0000\u0319\u031c\u0001\u0000\u0000\u0000\u031a"+
		"\u0318\u0001\u0000\u0000\u0000\u031b\u030e\u0001\u0000\u0000\u0000\u031b"+
		"\u031c\u0001\u0000\u0000\u0000\u031c\u031d\u0001\u0000\u0000\u0000\u031d"+
		"\u032c\u0005%\u0000\u0000\u031e\u0328\u0005$\u0000\u0000\u031f\u0320\u0003"+
		"\u0080@\u0000\u0320\u0321\u0005\u0004\u0000\u0000\u0321\u0324\u0003>\u001f"+
		"\u0000\u0322\u0323\u0005\b\u0000\u0000\u0323\u0325\u0003>\u001f\u0000"+
		"\u0324\u0322\u0001\u0000\u0000\u0000\u0325\u0326\u0001\u0000\u0000\u0000"+
		"\u0326\u0324\u0001\u0000\u0000\u0000\u0326\u0327\u0001\u0000\u0000\u0000"+
		"\u0327\u0329\u0001\u0000\u0000\u0000\u0328\u031f\u0001\u0000\u0000\u0000"+
		"\u0328\u0329\u0001\u0000\u0000\u0000\u0329\u032a\u0001\u0000\u0000\u0000"+
		"\u032a\u032c\u0005%\u0000\u0000\u032b\u030d\u0001\u0000\u0000\u0000\u032b"+
		"\u031e\u0001\u0000\u0000\u0000\u032c{\u0001\u0000\u0000\u0000\u032d\u0336"+
		"\u0005F\u0000\u0000\u032e\u0333\u0003~?\u0000\u032f\u0330\u0005E\u0000"+
		"\u0000\u0330\u0332\u0003~?\u0000\u0331\u032f\u0001\u0000\u0000\u0000\u0332"+
		"\u0335\u0001\u0000\u0000\u0000\u0333\u0331\u0001\u0000\u0000\u0000\u0333"+
		"\u0334\u0001\u0000\u0000\u0000\u0334\u0337\u0001\u0000\u0000\u0000\u0335"+
		"\u0333\u0001\u0000\u0000\u0000\u0336\u032e\u0001\u0000\u0000\u0000\u0336"+
		"\u0337\u0001\u0000\u0000\u0000\u0337\u0338\u0001\u0000\u0000\u0000\u0338"+
		"\u0339\u0005G\u0000\u0000\u0339}\u0001\u0000\u0000\u0000\u033a\u033b\u0003"+
		"\u0080@\u0000\u033b\u033c\u0005\u0004\u0000\u0000\u033c\u033d\u0003>\u001f"+
		"\u0000\u033d\u0345\u0001\u0000\u0000\u0000\u033e\u033f\u0005\b\u0000\u0000"+
		"\u033f\u0340\u0003\u0080@\u0000\u0340\u0341\u0005\u0004\u0000\u0000\u0341"+
		"\u0342\u0003>\u001f\u0000\u0342\u0344\u0001\u0000\u0000\u0000\u0343\u033e"+
		"\u0001\u0000\u0000\u0000\u0344\u0347\u0001\u0000\u0000\u0000\u0345\u0343"+
		"\u0001\u0000\u0000\u0000\u0345\u0346\u0001\u0000\u0000\u0000\u0346\u007f"+
		"\u0001\u0000\u0000\u0000\u0347\u0345\u0001\u0000\u0000\u0000\u0348\u0355"+
		"\u0003>\u001f\u0000\u0349\u034a\u0005\u000b\u0000\u0000\u034a\u034f\u0003"+
		">\u001f\u0000\u034b\u034c\u0005\b\u0000\u0000\u034c\u034e\u0003>\u001f"+
		"\u0000\u034d\u034b\u0001\u0000\u0000\u0000\u034e\u0351\u0001\u0000\u0000"+
		"\u0000\u034f\u034d\u0001\u0000\u0000\u0000\u034f\u0350\u0001\u0000\u0000"+
		"\u0000\u0350\u0352\u0001\u0000\u0000\u0000\u0351\u034f\u0001\u0000\u0000"+
		"\u0000\u0352\u0353\u0005\f\u0000\u0000\u0353\u0355\u0001\u0000\u0000\u0000"+
		"\u0354\u0348\u0001\u0000\u0000\u0000\u0354\u0349\u0001\u0000\u0000\u0000"+
		"\u0355\u0081\u0001\u0000\u0000\u0000\u0356\u0357\u0005$\u0000\u0000\u0357"+
		"\u0358\u0003>\u001f\u0000\u0358\u0359\u0005E\u0000\u0000\u0359\u035a\u0003"+
		"t:\u0000\u035a\u035b\u0005%\u0000\u0000\u035b\u0083\u0001\u0000\u0000"+
		"\u0000\u035c\u035d\u0005$\u0000\u0000\u035d\u035e\u0003\u0080@\u0000\u035e"+
		"\u035f\u0005\u0004\u0000\u0000\u035f\u0360\u0003>\u001f\u0000\u0360\u0361"+
		"\u0005E\u0000\u0000\u0361\u0362\u0003t:\u0000\u0362\u0363\u0005%\u0000"+
		"\u0000\u0363\u0085\u0001\u0000\u0000\u0000\u0364\u0365\u0005S\u0000\u0000"+
		"\u0365\u036e\u0005\u000b\u0000\u0000\u0366\u036b\u0003>\u001f\u0000\u0367"+
		"\u0368\u0005\b\u0000\u0000\u0368\u036a\u0003>\u001f\u0000\u0369\u0367"+
		"\u0001\u0000\u0000\u0000\u036a\u036d\u0001\u0000\u0000\u0000\u036b\u0369"+
		"\u0001\u0000\u0000\u0000\u036b\u036c\u0001\u0000\u0000\u0000\u036c\u036f"+
		"\u0001\u0000\u0000\u0000\u036d\u036b\u0001\u0000\u0000\u0000\u036e\u0366"+
		"\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000\u0000\u0000\u036f\u0370"+
		"\u0001\u0000\u0000\u0000\u0370\u0371\u0005\f\u0000\u0000\u0371\u0087\u0001"+
		"\u0000\u0000\u0000\u0372\u0373\u0005S\u0000\u0000\u0373\u0374\u0005\u000b"+
		"\u0000\u0000\u0374\u0375\u0003t:\u0000\u0375\u0376\u0005\f\u0000\u0000"+
		"\u0376\u0377\u0005\u000b\u0000\u0000\u0377\u0378\u0003>\u001f\u0000\u0378"+
		"\u0379\u0005\f\u0000\u0000\u0379\u0089\u0001\u0000\u0000\u0000Y\u008f"+
		"\u00a0\u00a9\u00b1\u00b3\u00ba\u00c1\u00ca\u00dc\u00eb\u00fd\u0102\u010b"+
		"\u011d\u0125\u012a\u0132\u0135\u0138\u013b\u0140\u0144\u014b\u0156\u015b"+
		"\u0162\u016a\u0173\u0179\u0185\u019b\u01a4\u01a9\u01b2\u01bb\u01c3\u01cb"+
		"\u01d3\u01db\u01e3\u01eb\u01f3\u01fb\u0203\u020b\u0211\u0217\u021e\u0225"+
		"\u022e\u0235\u023c\u023f\u0244\u0254\u0257\u0265\u026d\u0270\u0279\u0281"+
		"\u0284\u028d\u0295\u0298\u02a1\u02ac\u02bf\u02cf\u02d4\u02df\u02e8\u02ed"+
		"\u02f3\u02fb\u0300\u0307\u0318\u031b\u0326\u0328\u032b\u0333\u0336\u0345"+
		"\u034f\u0354\u036b\u036e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}