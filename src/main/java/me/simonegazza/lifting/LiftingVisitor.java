package me.simonegazza.lifting;

import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftingVisitor extends MiniZincBaseVisitor<String> {
    private final TokenStreamRewriter rewriter;

    public LiftingVisitor(CommonTokenStream tokens) {
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public String visitModel(ModelContext ctx) {
        return rewriter.getText(ctx.getSourceInterval());
    }
}
