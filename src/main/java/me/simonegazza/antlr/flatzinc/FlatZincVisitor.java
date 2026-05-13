// Generated from FlatZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.flatzinc;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FlatZincParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *                operations with no return type.
 */
public interface FlatZincVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FlatZincParser#model}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitModel(FlatZincParser.ModelContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#predicateItem}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitPredicateItem(FlatZincParser.PredicateItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#predParamDeclList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitPredParamDeclList(FlatZincParser.PredParamDeclListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#predParamDecl}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitPredParamDecl(FlatZincParser.PredParamDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicParType}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicParType(FlatZincParser.BasicParTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicVarType}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicVarType(FlatZincParser.BasicVarTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#arrayVarType}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitArrayVarType(FlatZincParser.ArrayVarTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#indexSet}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitIndexSet(FlatZincParser.IndexSetContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicPredParamType}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicPredParamType(FlatZincParser.BasicPredParamTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#predParamType}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitPredParamType(FlatZincParser.PredParamTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#predIndexSet}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitPredIndexSet(FlatZincParser.PredIndexSetContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicLiteralExpr}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicLiteralExpr(FlatZincParser.BasicLiteralExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicExpr}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicExpr(FlatZincParser.BasicExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#expr}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitExpr(FlatZincParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#varParIdentifier}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitVarParIdentifier(FlatZincParser.VarParIdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#boolLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBoolLiteral(FlatZincParser.BoolLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#intLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitIntLiteral(FlatZincParser.IntLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#floatLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitFloatLiteral(FlatZincParser.FloatLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#setLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitSetLiteral(FlatZincParser.SetLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#arrayLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitArrayLiteral(FlatZincParser.ArrayLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#parArrayLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitParArrayLiteral(FlatZincParser.ParArrayLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#intLiteralList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitIntLiteralList(FlatZincParser.IntLiteralListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#floatLiteralList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitFloatLiteralList(FlatZincParser.FloatLiteralListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicExprList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicExprList(FlatZincParser.BasicExprListContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link FlatZincParser#basicLiteralExprList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicLiteralExprList(FlatZincParser.BasicLiteralExprListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#exprList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitExprList(FlatZincParser.ExprListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#annExprList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitAnnExprList(FlatZincParser.AnnExprListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#parDeclItem}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitParDeclItem(FlatZincParser.ParDeclItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#varDeclItem}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitVarDeclItem(FlatZincParser.VarDeclItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#constraintItem}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitConstraintItem(FlatZincParser.ConstraintItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#solveItem}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitSolveItem(FlatZincParser.SolveItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#annotations}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitAnnotations(FlatZincParser.AnnotationsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#annotation}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitAnnotation(FlatZincParser.AnnotationContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#annExpr}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitAnnExpr(FlatZincParser.AnnExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicAnnExpr}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicAnnExpr(FlatZincParser.BasicAnnExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#basicAnnExprList}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitBasicAnnExprList(FlatZincParser.BasicAnnExprListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#identifier}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitIdentifier(FlatZincParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link FlatZincParser#stringLiteral}.
	 *
	 * @param ctx the parse tree
	 *
	 * @return the visitor result
	 */
	T visitStringLiteral(FlatZincParser.StringLiteralContext ctx);
}
