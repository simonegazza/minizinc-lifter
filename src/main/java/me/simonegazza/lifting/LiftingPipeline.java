package me.simonegazza.lifting;

import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

public class LiftingPipeline {
    private final Set<String> parameters;

    public LiftingPipeline(Set<String> parameters) {
        this.parameters = parameters;
    }

    public String start(String model) {
        CharStream input = CharStreams.fromString(model);
        Lexer lexer = new MiniZincLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);

        VarInserterVisitor inserter = new VarInserterVisitor(tokens, parameters);
        MiniZincParser parser = new MiniZincParser(tokens);

        inserter.visitModel(parser.model());
        return inserter.getTranspiled();
    }

}
