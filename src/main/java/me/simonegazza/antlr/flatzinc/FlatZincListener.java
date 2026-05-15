// Generated from FlatZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.flatzinc;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FlatZincParser}.
 */
public interface FlatZincListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FlatZincParser#model}.
	 *
	 * @param ctx the parse tree
	 */
	void enterModel(FlatZincParser.ModelContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#model}.
	 *
	 * @param ctx the parse tree
	 */
	void exitModel(FlatZincParser.ModelContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#predicateItem}.
	 *
	 * @param ctx the parse tree
	 */
	void enterPredicateItem(FlatZincParser.PredicateItemContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#predicateItem}.
	 *
	 * @param ctx the parse tree
	 */
	void exitPredicateItem(FlatZincParser.PredicateItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#predParamDeclList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterPredParamDeclList(FlatZincParser.PredParamDeclListContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#predParamDeclList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitPredParamDeclList(FlatZincParser.PredParamDeclListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#predParamDecl}.
	 *
	 * @param ctx the parse tree
	 */
	void enterPredParamDecl(FlatZincParser.PredParamDeclContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#predParamDecl}.
	 *
	 * @param ctx the parse tree
	 */
	void exitPredParamDecl(FlatZincParser.PredParamDeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicParType}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicParType(FlatZincParser.BasicParTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicParType}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicParType(FlatZincParser.BasicParTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicVarType}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicVarType(FlatZincParser.BasicVarTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicVarType}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicVarType(FlatZincParser.BasicVarTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#arrayVarType}.
	 *
	 * @param ctx the parse tree
	 */
	void enterArrayVarType(FlatZincParser.ArrayVarTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#arrayVarType}.
	 *
	 * @param ctx the parse tree
	 */
	void exitArrayVarType(FlatZincParser.ArrayVarTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#predIndexSet}.
	 *
	 * @param ctx the parse tree
	 */
	void enterPredIndexSet(FlatZincParser.PredIndexSetContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#predIndexSet}.
	 *
	 * @param ctx the parse tree
	 */
	void exitPredIndexSet(FlatZincParser.PredIndexSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#indexSet}.
	 *
	 * @param ctx the parse tree
	 */
	void enterIndexSet(FlatZincParser.IndexSetContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#indexSet}.
	 *
	 * @param ctx the parse tree
	 */
	void exitIndexSet(FlatZincParser.IndexSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicPredParamType}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicPredParamType(FlatZincParser.BasicPredParamTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicPredParamType}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicPredParamType(FlatZincParser.BasicPredParamTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#predParamType}.
	 *
	 * @param ctx the parse tree
	 */
	void enterPredParamType(FlatZincParser.PredParamTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#predParamType}.
	 *
	 * @param ctx the parse tree
	 */
	void exitPredParamType(FlatZincParser.PredParamTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#expr}.
	 *
	 * @param ctx the parse tree
	 */
	void enterExpr(FlatZincParser.ExprContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#expr}.
	 *
	 * @param ctx the parse tree
	 */
	void exitExpr(FlatZincParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicExpr(FlatZincParser.BasicExprContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicExpr(FlatZincParser.BasicExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#setLiteral}.
	 *
	 * @param ctx the parse tree
	 */
	void enterSetLiteral(FlatZincParser.SetLiteralContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#setLiteral}.
	 *
	 * @param ctx the parse tree
	 */
	void exitSetLiteral(FlatZincParser.SetLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicExprList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicExprList(FlatZincParser.BasicExprListContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicExprList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicExprList(FlatZincParser.BasicExprListContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link FlatZincParser#basicLiteralExprList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicLiteralExprList(FlatZincParser.BasicLiteralExprListContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link FlatZincParser#basicLiteralExprList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicLiteralExprList(FlatZincParser.BasicLiteralExprListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#arrayLiteral}.
	 *
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(FlatZincParser.ArrayLiteralContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#arrayLiteral}.
	 *
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(FlatZincParser.ArrayLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#intLiteralList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterIntLiteralList(FlatZincParser.IntLiteralListContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#intLiteralList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitIntLiteralList(FlatZincParser.IntLiteralListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#floatLiteralList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterFloatLiteralList(FlatZincParser.FloatLiteralListContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#floatLiteralList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitFloatLiteralList(FlatZincParser.FloatLiteralListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#parDeclItem}.
	 *
	 * @param ctx the parse tree
	 */
	void enterParDeclItem(FlatZincParser.ParDeclItemContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#parDeclItem}.
	 *
	 * @param ctx the parse tree
	 */
	void exitParDeclItem(FlatZincParser.ParDeclItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#varDeclItem}.
	 *
	 * @param ctx the parse tree
	 */
	void enterVarDeclItem(FlatZincParser.VarDeclItemContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#varDeclItem}.
	 *
	 * @param ctx the parse tree
	 */
	void exitVarDeclItem(FlatZincParser.VarDeclItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#constraintItem}.
	 *
	 * @param ctx the parse tree
	 */
	void enterConstraintItem(FlatZincParser.ConstraintItemContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#constraintItem}.
	 *
	 * @param ctx the parse tree
	 */
	void exitConstraintItem(FlatZincParser.ConstraintItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#solveItem}.
	 *
	 * @param ctx the parse tree
	 */
	void enterSolveItem(FlatZincParser.SolveItemContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#solveItem}.
	 *
	 * @param ctx the parse tree
	 */
	void exitSolveItem(FlatZincParser.SolveItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#annotation}.
	 *
	 * @param ctx the parse tree
	 */
	void enterAnnotation(FlatZincParser.AnnotationContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#annotation}.
	 *
	 * @param ctx the parse tree
	 */
	void exitAnnotation(FlatZincParser.AnnotationContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#annExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void enterAnnExpr(FlatZincParser.AnnExprContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#annExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void exitAnnExpr(FlatZincParser.AnnExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link FlatZincParser#basicAnnExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void enterBasicAnnExpr(FlatZincParser.BasicAnnExprContext ctx);

	/**
	 * Exit a parse tree produced by {@link FlatZincParser#basicAnnExpr}.
	 *
	 * @param ctx the parse tree
	 */
	void exitBasicAnnExpr(FlatZincParser.BasicAnnExprContext ctx);
}
