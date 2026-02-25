package me.simonegazza.lifting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftingVisitor extends MiniZincBaseVisitor<Void> {
    private final Set<String> parameters;
    private final TokenStreamRewriter rewriter;
    private final TokenStream tokens;

    private List<String> initializations;

    public LiftingVisitor(TokenStream tokens, Set<String> parameters) {
        this.tokens = tokens;
        this.parameters = new HashSet<String>(parameters);
        this.rewriter = new TokenStreamRewriter(tokens);
        this.initializations = new ArrayList<>();
    }

    private String getLiftedName(String originalName) {
        return originalName + "_lifted";
    }

    @Override
    public Void visitIdent(IdentContext ctx) {
        System.out.println(ctx.getText());

        if (parameters.stream().anyMatch(p -> p.equals(ctx.getText()))) {
            rewriter.replace(ctx.getStart(), ctx.getStop(), getLiftedName(ctx.getText()));
        }

        return null;
    }

    @Override
    public Void visitVarDeclItem(VarDeclItemContext ctx) {
        TiExprAndIdContext declarationLhs = ctx.tiExprAndId();
        IdentContext ident = declarationLhs.ident();

        if (parameters.stream().anyMatch(p -> p.equals(ident.getText()))) {
            TiExprContext typeExpr = declarationLhs.tiExpr();
            BaseTiExprContext type;
            if (declarationLhs.tiExpr().baseTiExpr() != null) // simple variable declaration
                type = typeExpr.baseTiExpr();
            else // array declaration
                type = typeExpr.arrayTiExpr().baseTiExpr();

            if (type.getChild(0).getText().equals("var"))
                throw new IllegalStateException("Requesting lifting of a variable");

            if (type.getChild(0).getText().equals("par"))
                rewriter.delete(type.getStart());

            rewriter.insertBefore(type.getStart(), "var ");
            rewriter.replace(ident.getStart(), ident.getStop(), getLiftedName(ident.getText()));

            if (ctx.expr() != null)
                initializations.add(tokens.getText(ctx));

            return null;
        } else
            // They could be present in the dimension of an array
            return super.visitVarDeclItem(ctx);
    }

    public String getTranspiled() {
        StringBuilder sb = new StringBuilder(rewriter.getText());
        for (String e : initializations)
            sb.append(e + ";");
        return sb.toString();
    }
}
