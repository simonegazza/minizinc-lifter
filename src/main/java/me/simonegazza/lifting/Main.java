package me.simonegazza.lifting;

import java.io.IOException;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        try {
            CharStream input = CharStreams.fromFileName(filePath);

            Lexer lexer = new MiniZincLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MiniZincParser parser = new MiniZincParser(tokens);

            ParseTree tree = parser.model();

            LiftingVisitor visitor = new LiftingVisitor(tokens);
            String copy = visitor.visit(tree);

            System.out.println(copy);
        } catch (IOException e) {
            System.err.println("Unable to find file " + filePath);
        }
    }
}
