// Generated from FlatZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.flatzinc;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link FlatZincVisitor}, which
 * can be extended to create a visitor which only needs to handle a subset of
 * the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *                operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class FlatZincBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements FlatZincVisitor<T> {
	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitModel(FlatZincParser.ModelContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPredicateItem(FlatZincParser.PredicateItemContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPredParamDeclList(FlatZincParser.PredParamDeclListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPredParamDecl(FlatZincParser.PredParamDeclContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicParType(FlatZincParser.BasicParTypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicVarType(FlatZincParser.BasicVarTypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitArrayVarType(FlatZincParser.ArrayVarTypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitIndexSet(FlatZincParser.IndexSetContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicPredParamType(FlatZincParser.BasicPredParamTypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPredParamType(FlatZincParser.PredParamTypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPredIndexSet(FlatZincParser.PredIndexSetContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicLiteralExpr(FlatZincParser.BasicLiteralExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicExpr(FlatZincParser.BasicExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitExpr(FlatZincParser.ExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitVarParIdentifier(FlatZincParser.VarParIdentifierContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBoolLiteral(FlatZincParser.BoolLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitIntLiteral(FlatZincParser.IntLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitFloatLiteral(FlatZincParser.FloatLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitSetLiteral(FlatZincParser.SetLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitArrayLiteral(FlatZincParser.ArrayLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitParArrayLiteral(FlatZincParser.ParArrayLiteralContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitIntLiteralList(FlatZincParser.IntLiteralListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitFloatLiteralList(FlatZincParser.FloatLiteralListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicExprList(FlatZincParser.BasicExprListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicLiteralExprList(FlatZincParser.BasicLiteralExprListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitExprList(FlatZincParser.ExprListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitAnnExprList(FlatZincParser.AnnExprListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitParDeclItem(FlatZincParser.ParDeclItemContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitVarDeclItem(FlatZincParser.VarDeclItemContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitConstraintItem(FlatZincParser.ConstraintItemContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitSolveItem(FlatZincParser.SolveItemContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitAnnotations(FlatZincParser.AnnotationsContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitAnnotation(FlatZincParser.AnnotationContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitAnnExpr(FlatZincParser.AnnExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicAnnExpr(FlatZincParser.BasicAnnExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitBasicAnnExprList(FlatZincParser.BasicAnnExprListContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitIdentifier(FlatZincParser.IdentifierContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitStringLiteral(FlatZincParser.StringLiteralContext ctx) {
		return visitChildren(ctx);
	}
}
