package me.simonegazza.lifting;

import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class VarInserterVisitor extends MiniZincBaseVisitor<Void> {
    private final String parameterLift;
    private final TokenStreamRewriter rewriter;

    public VarInserterVisitor(CommonTokenStream tokens, String parameter) {
        this.parameterLift = parameter;
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public Void visitVarDeclItem(VarDeclItemContext ctx) {
        // TODO: implement initializations for new parameters
        TiExprAndIdContext declaration = ctx.tiExprAndId();
        TiExprContext typeExpr = declaration.tiExpr();
        BaseTiExprContext type;
        if (declaration.IDENT().getText().equals(parameterLift)) {
            // simple variable declaration
            if (declaration.tiExpr().baseTiExpr() != null)
                type = typeExpr.baseTiExpr();
            else // array declaration
                type = typeExpr.arrayTiExpr().baseTiExpr();

            if (type.getChild(0).getText().equals("var"))
                throw new IllegalStateException("Requesting lifting of a variable");

            if (type.getChild(0).getText().startsWith("par"))
                rewriter.delete(type.getStart());

            rewriter.insertBefore(type.getStart(), "var ");
        }
        return null;
    }

    public String getTranspiled() {
        return rewriter.getText();
    }
}
