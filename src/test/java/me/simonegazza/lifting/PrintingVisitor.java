package me.simonegazza.lifting;

import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

public class PrintingVisitor extends MiniZincBaseVisitor<String> {
    private final TokenStreamRewriter rewriter;

    public PrintingVisitor(CommonTokenStream tokens) {
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public String visit(ParseTree tree) {
        return rewriter.getText();
    }
}
