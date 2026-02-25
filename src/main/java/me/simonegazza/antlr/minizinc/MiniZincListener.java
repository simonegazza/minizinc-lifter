// Generated from MiniZinc.g4 by ANTLR 4.13.2
package me.simonegazza.antlr.minizinc;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniZincParser}.
 */
public interface MiniZincListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link MiniZincParser#model}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterModel(MiniZincParser.ModelContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#model}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitModel(MiniZincParser.ModelContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#item}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterItem(MiniZincParser.ItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#item}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitItem(MiniZincParser.ItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#includeItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIncludeItem(MiniZincParser.IncludeItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#includeItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIncludeItem(MiniZincParser.IncludeItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#varDeclItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterVarDeclItem(MiniZincParser.VarDeclItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#varDeclItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitVarDeclItem(MiniZincParser.VarDeclItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#enumItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterEnumItem(MiniZincParser.EnumItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#enumItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitEnumItem(MiniZincParser.EnumItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#enumCasesList}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterEnumCasesList(MiniZincParser.EnumCasesListContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#enumCasesList}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitEnumCasesList(MiniZincParser.EnumCasesListContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#enumCases}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterEnumCases(MiniZincParser.EnumCasesContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#enumCases}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitEnumCases(MiniZincParser.EnumCasesContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#typeInstSynItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterTypeInstSynItem(MiniZincParser.TypeInstSynItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#typeInstSynItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitTypeInstSynItem(MiniZincParser.TypeInstSynItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#assignItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAssignItem(MiniZincParser.AssignItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#assignItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAssignItem(MiniZincParser.AssignItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#constraintItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterConstraintItem(MiniZincParser.ConstraintItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#constraintItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitConstraintItem(MiniZincParser.ConstraintItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#solveItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterSolveItem(MiniZincParser.SolveItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#solveItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitSolveItem(MiniZincParser.SolveItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#outputItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterOutputItem(MiniZincParser.OutputItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#outputItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitOutputItem(MiniZincParser.OutputItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#annotationItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAnnotationItem(MiniZincParser.AnnotationItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#annotationItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAnnotationItem(MiniZincParser.AnnotationItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#predicateItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterPredicateItem(MiniZincParser.PredicateItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#predicateItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitPredicateItem(MiniZincParser.PredicateItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#testItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterTestItem(MiniZincParser.TestItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#testItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitTestItem(MiniZincParser.TestItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#functionItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterFunctionItem(MiniZincParser.FunctionItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#functionItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitFunctionItem(MiniZincParser.FunctionItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#operationItemTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterOperationItemTail(MiniZincParser.OperationItemTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#operationItemTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitOperationItemTail(MiniZincParser.OperationItemTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#params}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterParams(MiniZincParser.ParamsContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#params}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitParams(MiniZincParser.ParamsContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#tiExprAndId}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterTiExprAndId(MiniZincParser.TiExprAndIdContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#tiExprAndId}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitTiExprAndId(MiniZincParser.TiExprAndIdContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#tiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterTiExpr(MiniZincParser.TiExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#tiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitTiExpr(MiniZincParser.TiExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#baseTiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterBaseTiExpr(MiniZincParser.BaseTiExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#baseTiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitBaseTiExpr(MiniZincParser.BaseTiExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#baseTiExprTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterBaseTiExprTail(MiniZincParser.BaseTiExprTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#baseTiExprTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitBaseTiExprTail(MiniZincParser.BaseTiExprTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#baseType}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterBaseType(MiniZincParser.BaseTypeContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#baseType}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitBaseType(MiniZincParser.BaseTypeContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayTiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayTiExpr(MiniZincParser.ArrayTiExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayTiExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayTiExpr(MiniZincParser.ArrayTiExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayAccessTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayAccessTail(MiniZincParser.ArrayAccessTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayAccessTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayAccessTail(MiniZincParser.ArrayAccessTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#fieldAccessTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterFieldAccessTail(MiniZincParser.FieldAccessTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#fieldAccessTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitFieldAccessTail(MiniZincParser.FieldAccessTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#annotations}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAnnotations(MiniZincParser.AnnotationsContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#annotations}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAnnotations(MiniZincParser.AnnotationsContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#annotation}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAnnotation(MiniZincParser.AnnotationContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#annotation}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAnnotation(MiniZincParser.AnnotationContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#exprAtomHead}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterExprAtomHead(MiniZincParser.ExprAtomHeadContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#exprAtomHead}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitExprAtomHead(MiniZincParser.ExprAtomHeadContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#annLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAnnLiteral(MiniZincParser.AnnLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#annLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAnnLiteral(MiniZincParser.AnnLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#exprAtomTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterExprAtomTail(MiniZincParser.ExprAtomTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#exprAtomTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitExprAtomTail(MiniZincParser.ExprAtomTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#expr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterExpr(MiniZincParser.ExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#expr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitExpr(MiniZincParser.ExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#iffExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIffExpr(MiniZincParser.IffExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#iffExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIffExpr(MiniZincParser.IffExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#implExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterImplExpr(MiniZincParser.ImplExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#implExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitImplExpr(MiniZincParser.ImplExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#orExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterOrExpr(MiniZincParser.OrExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#orExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitOrExpr(MiniZincParser.OrExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#xorExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterXorExpr(MiniZincParser.XorExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#xorExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitXorExpr(MiniZincParser.XorExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#andExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAndExpr(MiniZincParser.AndExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#andExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAndExpr(MiniZincParser.AndExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#compareExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterCompareExpr(MiniZincParser.CompareExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#compareExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitCompareExpr(MiniZincParser.CompareExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#setExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterSetExpr(MiniZincParser.SetExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#setExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitSetExpr(MiniZincParser.SetExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#rangeExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterRangeExpr(MiniZincParser.RangeExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#rangeExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitRangeExpr(MiniZincParser.RangeExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#addExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterAddExpr(MiniZincParser.AddExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#addExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitAddExpr(MiniZincParser.AddExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#multExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterMultExpr(MiniZincParser.MultExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#multExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitMultExpr(MiniZincParser.MultExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#powExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterPowExpr(MiniZincParser.PowExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#powExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitPowExpr(MiniZincParser.PowExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#unaryExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterUnaryExpr(MiniZincParser.UnaryExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#unaryExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitUnaryExpr(MiniZincParser.UnaryExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#primary}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterPrimary(MiniZincParser.PrimaryContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#primary}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitPrimary(MiniZincParser.PrimaryContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#postfix}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterPostfix(MiniZincParser.PostfixContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#postfix}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitPostfix(MiniZincParser.PostfixContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#quantifierExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterQuantifierExpr(MiniZincParser.QuantifierExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#quantifierExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitQuantifierExpr(MiniZincParser.QuantifierExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#callSuffix}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterCallSuffix(MiniZincParser.CallSuffixContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#callSuffix}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitCallSuffix(MiniZincParser.CallSuffixContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#literal}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterLiteral(MiniZincParser.LiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#literal}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitLiteral(MiniZincParser.LiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#setLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterSetLiteral(MiniZincParser.SetLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#setLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitSetLiteral(MiniZincParser.SetLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayLiteral(MiniZincParser.ArrayLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayLiteral(MiniZincParser.ArrayLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayLiteral2d(MiniZincParser.ArrayLiteral2dContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayLiteral2d(MiniZincParser.ArrayLiteral2dContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayRow}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayRow(MiniZincParser.ArrayRowContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayRow}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayRow(MiniZincParser.ArrayRowContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#tupleLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterTupleLiteral(MiniZincParser.TupleLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#tupleLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitTupleLiteral(MiniZincParser.TupleLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#recordLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterRecordLiteral(MiniZincParser.RecordLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#recordLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitRecordLiteral(MiniZincParser.RecordLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#ifThenElseExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIfThenElseExpr(MiniZincParser.IfThenElseExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#ifThenElseExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIfThenElseExpr(MiniZincParser.IfThenElseExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#letExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterLetExpr(MiniZincParser.LetExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#letExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitLetExpr(MiniZincParser.LetExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#letItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterLetItem(MiniZincParser.LetItemContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#letItem}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitLetItem(MiniZincParser.LetItemContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#compTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterCompTail(MiniZincParser.CompTailContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#compTail}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitCompTail(MiniZincParser.CompTailContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#generatorList}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterGeneratorList(MiniZincParser.GeneratorListContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#generatorList}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitGeneratorList(MiniZincParser.GeneratorListContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#generator}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterGenerator(MiniZincParser.GeneratorContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#generator}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitGenerator(MiniZincParser.GeneratorContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#indexedArrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIndexedArrayLiteral(MiniZincParser.IndexedArrayLiteralContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#indexedArrayLiteral}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIndexedArrayLiteral(MiniZincParser.IndexedArrayLiteralContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#indexedArrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIndexedArrayLiteral2d(MiniZincParser.IndexedArrayLiteral2dContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#indexedArrayLiteral2d}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIndexedArrayLiteral2d(MiniZincParser.IndexedArrayLiteral2dContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#indexedArrayRow}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIndexedArrayRow(MiniZincParser.IndexedArrayRowContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#indexedArrayRow}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIndexedArrayRow(MiniZincParser.IndexedArrayRowContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#indexTuple}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIndexTuple(MiniZincParser.IndexTupleContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#indexTuple}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIndexTuple(MiniZincParser.IndexTupleContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#arrayComp}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterArrayComp(MiniZincParser.ArrayCompContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#arrayComp}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitArrayComp(MiniZincParser.ArrayCompContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#indexedArrayComp}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIndexedArrayComp(MiniZincParser.IndexedArrayCompContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#indexedArrayComp}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIndexedArrayComp(MiniZincParser.IndexedArrayCompContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#callExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterCallExpr(MiniZincParser.CallExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#callExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitCallExpr(MiniZincParser.CallExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#genCallExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterGenCallExpr(MiniZincParser.GenCallExprContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#genCallExpr}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitGenCallExpr(MiniZincParser.GenCallExprContext ctx);
    /**
     * Enter a parse tree produced by {@link MiniZincParser#ident}.
     *
     * @param ctx
     *            the parse tree
     */
    void enterIdent(MiniZincParser.IdentContext ctx);
    /**
     * Exit a parse tree produced by {@link MiniZincParser#ident}.
     *
     * @param ctx
     *            the parse tree
     */
    void exitIdent(MiniZincParser.IdentContext ctx);
}
