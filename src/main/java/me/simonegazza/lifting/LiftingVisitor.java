package me.simonegazza.lifting;

import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftingVisitor extends MiniZincBaseVisitor<Void> {
    private final String parameterLift;
    private final TokenStreamRewriter rewriter;

    public LiftingVisitor(CommonTokenStream tokens, String parameter) {
        this.parameterLift = parameter;
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public Void visitVarDeclItem(VarDeclItemContext ctx) {
        var declaration = ctx.tiExprAndId();
        if (declaration.IDENT().getText().equals(parameterLift)) {
            // simple variable declaration
            if (declaration.tiExpr().baseTiExpr() != null) {
                var type = declaration.tiExpr().baseTiExpr();
                if (type.getChild(0).getText().equals("var"))
                    throw new IllegalStateException("Requesting lifting of a variable");

                if (type.getChild(0).getText().startsWith("par")) {
                    rewriter.delete(type.getStart());
                }
                System.out.println(type);
                rewriter.insertBefore(type.getStart(), "var ");
            }
            // array declaration
            else {
                throw new InternalError();
            }
        }
        return null;
    }

    public String getTranspiled() {
        return rewriter.getText();
    }
}
