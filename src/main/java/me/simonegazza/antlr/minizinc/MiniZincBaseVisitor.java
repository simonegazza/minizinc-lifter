// Generated from MiniZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.minizinc;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link MiniZincVisitor}, which
 * can be extended to create a visitor which only needs to handle a subset of
 * the available methods.
 *
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class MiniZincBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MiniZincVisitor<T> {
    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitModel(MiniZincParser.ModelContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitItem(MiniZincParser.ItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIncludeItem(MiniZincParser.IncludeItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitVarDeclItem(MiniZincParser.VarDeclItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitEnumItem(MiniZincParser.EnumItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitEnumCasesList(MiniZincParser.EnumCasesListContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitEnumCases(MiniZincParser.EnumCasesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitTypeInstSynItem(MiniZincParser.TypeInstSynItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAssignItem(MiniZincParser.AssignItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitConstraintItem(MiniZincParser.ConstraintItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitSolveItem(MiniZincParser.SolveItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitOutputItem(MiniZincParser.OutputItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAnnotationItem(MiniZincParser.AnnotationItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitPredicateItem(MiniZincParser.PredicateItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitTestItem(MiniZincParser.TestItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitFunctionItem(MiniZincParser.FunctionItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitOperationItemTail(MiniZincParser.OperationItemTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitParams(MiniZincParser.ParamsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitTiExprAndId(MiniZincParser.TiExprAndIdContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitTiExpr(MiniZincParser.TiExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitBaseTiExpr(MiniZincParser.BaseTiExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitBaseTiExprTail(MiniZincParser.BaseTiExprTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitBaseType(MiniZincParser.BaseTypeContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayTiExpr(MiniZincParser.ArrayTiExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayAccessTail(MiniZincParser.ArrayAccessTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitFieldAccessTail(MiniZincParser.FieldAccessTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAnnotations(MiniZincParser.AnnotationsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAnnotation(MiniZincParser.AnnotationContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitExprAtomHead(MiniZincParser.ExprAtomHeadContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAnnLiteral(MiniZincParser.AnnLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitExprAtomTail(MiniZincParser.ExprAtomTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitExpr(MiniZincParser.ExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIffExpr(MiniZincParser.IffExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitImplExpr(MiniZincParser.ImplExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitOrExpr(MiniZincParser.OrExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitXorExpr(MiniZincParser.XorExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAndExpr(MiniZincParser.AndExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitCompareExpr(MiniZincParser.CompareExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitSetExpr(MiniZincParser.SetExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitRangeExpr(MiniZincParser.RangeExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitAddExpr(MiniZincParser.AddExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitMultExpr(MiniZincParser.MultExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitPowExpr(MiniZincParser.PowExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitUnaryExpr(MiniZincParser.UnaryExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitPrimary(MiniZincParser.PrimaryContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitPostfix(MiniZincParser.PostfixContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitQuantifierExpr(MiniZincParser.QuantifierExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitCallSuffix(MiniZincParser.CallSuffixContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitLiteral(MiniZincParser.LiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitSetLiteral(MiniZincParser.SetLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayLiteral(MiniZincParser.ArrayLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayLiteral2d(MiniZincParser.ArrayLiteral2dContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayRow(MiniZincParser.ArrayRowContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitTupleLiteral(MiniZincParser.TupleLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitRecordLiteral(MiniZincParser.RecordLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIfThenElseExpr(MiniZincParser.IfThenElseExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitLetExpr(MiniZincParser.LetExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitLetItem(MiniZincParser.LetItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitCompTail(MiniZincParser.CompTailContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitGeneratorList(MiniZincParser.GeneratorListContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitGenerator(MiniZincParser.GeneratorContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIndexedArrayLiteral(MiniZincParser.IndexedArrayLiteralContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIndexedArrayLiteral2d(MiniZincParser.IndexedArrayLiteral2dContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIndexedArrayRow(MiniZincParser.IndexedArrayRowContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIndexTuple(MiniZincParser.IndexTupleContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitArrayComp(MiniZincParser.ArrayCompContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitIndexedArrayComp(MiniZincParser.IndexedArrayCompContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitCallExpr(MiniZincParser.CallExprContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public T visitGenCallExpr(MiniZincParser.GenCallExprContext ctx) {
        return visitChildren(ctx);
    }
}
