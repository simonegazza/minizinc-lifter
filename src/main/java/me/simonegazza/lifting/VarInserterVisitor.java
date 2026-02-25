package me.simonegazza.lifting;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class VarInserterVisitor extends MiniZincBaseVisitor<Void> {
    private final Set<String> parameters;
    private final TokenStreamRewriter rewriter;

    public VarInserterVisitor(TokenStream tokens, Set<String> parameters) {
        this.parameters = new HashSet<String>(parameters);
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public Void visitVarDeclItem(VarDeclItemContext ctx) {
        TiExprAndIdContext declaration = ctx.tiExprAndId();
        String ident = declaration.IDENT().getText();

        Optional<String> parameter = parameters.stream().filter(p -> p.equals(ident)).findFirst();
        if (parameter.isPresent()) {
            TiExprContext typeExpr = declaration.tiExpr();
            BaseTiExprContext type;
            if (declaration.tiExpr().baseTiExpr() != null) // simple variable declaration
                type = typeExpr.baseTiExpr();
            else // array declaration
                type = typeExpr.arrayTiExpr().baseTiExpr();

            if (type.getChild(0).getText().equals("var"))
                throw new IllegalStateException("Requesting lifting of a variable");

            if (type.getChild(0).getText().startsWith("par"))
                rewriter.delete(type.getStart());

            rewriter.insertBefore(type.getStart(), "var ");

            parameters.remove(parameter.get());
        }
        return null;
    }

    public String getTranspiled() {
        for (String param : parameters)
            System.out.println("WARNING: declaration for parameter \"" + param + "\" was not found");

        return rewriter.getText();
    }
}
