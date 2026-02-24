// Generated from MiniZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.minizinc;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniZincParser}.
 *
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface MiniZincVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link MiniZincParser#model}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitModel(MiniZincParser.ModelContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#item}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitItem(MiniZincParser.ItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#includeItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIncludeItem(MiniZincParser.IncludeItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#varDeclItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitVarDeclItem(MiniZincParser.VarDeclItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#enumItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitEnumItem(MiniZincParser.EnumItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#enumCasesList}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitEnumCasesList(MiniZincParser.EnumCasesListContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#enumCases}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitEnumCases(MiniZincParser.EnumCasesContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#typeInstSynItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitTypeInstSynItem(MiniZincParser.TypeInstSynItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#assignItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAssignItem(MiniZincParser.AssignItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#constraintItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitConstraintItem(MiniZincParser.ConstraintItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#solveItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitSolveItem(MiniZincParser.SolveItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#outputItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitOutputItem(MiniZincParser.OutputItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#annotationItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAnnotationItem(MiniZincParser.AnnotationItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#predicateItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitPredicateItem(MiniZincParser.PredicateItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#testItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitTestItem(MiniZincParser.TestItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#functionItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitFunctionItem(MiniZincParser.FunctionItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#operationItemTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitOperationItemTail(MiniZincParser.OperationItemTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#params}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitParams(MiniZincParser.ParamsContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#tiExprAndId}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitTiExprAndId(MiniZincParser.TiExprAndIdContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#tiExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitTiExpr(MiniZincParser.TiExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#baseTiExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitBaseTiExpr(MiniZincParser.BaseTiExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#baseTiExprTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitBaseTiExprTail(MiniZincParser.BaseTiExprTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#baseType}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitBaseType(MiniZincParser.BaseTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayTiExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayTiExpr(MiniZincParser.ArrayTiExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayAccessTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayAccessTail(MiniZincParser.ArrayAccessTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#fieldAccessTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitFieldAccessTail(MiniZincParser.FieldAccessTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#annotations}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAnnotations(MiniZincParser.AnnotationsContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#annotation}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAnnotation(MiniZincParser.AnnotationContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#exprAtomHead}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitExprAtomHead(MiniZincParser.ExprAtomHeadContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#annLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAnnLiteral(MiniZincParser.AnnLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#exprAtomTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitExprAtomTail(MiniZincParser.ExprAtomTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#expr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitExpr(MiniZincParser.ExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#iffExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIffExpr(MiniZincParser.IffExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#implExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitImplExpr(MiniZincParser.ImplExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#orExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitOrExpr(MiniZincParser.OrExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#xorExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitXorExpr(MiniZincParser.XorExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#andExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAndExpr(MiniZincParser.AndExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#compareExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitCompareExpr(MiniZincParser.CompareExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#setExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitSetExpr(MiniZincParser.SetExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#rangeExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitRangeExpr(MiniZincParser.RangeExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#addExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitAddExpr(MiniZincParser.AddExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#multExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitMultExpr(MiniZincParser.MultExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#powExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitPowExpr(MiniZincParser.PowExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#unaryExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitUnaryExpr(MiniZincParser.UnaryExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#primary}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitPrimary(MiniZincParser.PrimaryContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#postfix}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitPostfix(MiniZincParser.PostfixContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#quantifierExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitQuantifierExpr(MiniZincParser.QuantifierExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#callSuffix}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitCallSuffix(MiniZincParser.CallSuffixContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#literal}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitLiteral(MiniZincParser.LiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#setLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitSetLiteral(MiniZincParser.SetLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayLiteral(MiniZincParser.ArrayLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayLiteral2d(MiniZincParser.ArrayLiteral2dContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayRow}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayRow(MiniZincParser.ArrayRowContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#tupleLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitTupleLiteral(MiniZincParser.TupleLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#recordLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitRecordLiteral(MiniZincParser.RecordLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#ifThenElseExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIfThenElseExpr(MiniZincParser.IfThenElseExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#letExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitLetExpr(MiniZincParser.LetExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#letItem}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitLetItem(MiniZincParser.LetItemContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#compTail}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitCompTail(MiniZincParser.CompTailContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#generatorList}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitGeneratorList(MiniZincParser.GeneratorListContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#generator}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitGenerator(MiniZincParser.GeneratorContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#indexedArrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIndexedArrayLiteral(MiniZincParser.IndexedArrayLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#indexedArrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIndexedArrayLiteral2d(MiniZincParser.IndexedArrayLiteral2dContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#indexedArrayRow}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIndexedArrayRow(MiniZincParser.IndexedArrayRowContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#indexTuple}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIndexTuple(MiniZincParser.IndexTupleContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#arrayComp}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitArrayComp(MiniZincParser.ArrayCompContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#indexedArrayComp}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitIndexedArrayComp(MiniZincParser.IndexedArrayCompContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#callExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitCallExpr(MiniZincParser.CallExprContext ctx);

    /**
     * Visit a parse tree produced by {@link MiniZincParser#genCallExpr}.
     *
     * @param ctx
     *            the parse tree
     * @return the visitor result
     */
    T visitGenCallExpr(MiniZincParser.GenCallExprContext ctx);
}
