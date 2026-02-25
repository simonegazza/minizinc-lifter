package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class CorrectGrammarTest {
    private final Path resourcesFolder = Paths.get("resources");

    private String parse(String model) throws IOException {
        CharStream input = CharStreams.fromString(model);

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        ParseTree tree = parser.model();

        PrintingVisitor visitor = new PrintingVisitor(tokens);

        return visitor.visit(tree);
    }

    @Test
    void correctGrammarTest() throws IOException {
        List<Path> directories = Files.list(resourcesFolder).filter(Files::isDirectory).collect(Collectors.toList());

        for (Path dir : directories) {
            List<Path> files = Files.list(dir).filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".mzn") || p.toString().endsWith(".dzn"))
                    .collect(Collectors.toList());

            StringBuilder sb = new StringBuilder();

            for (Path file : files)
                sb.append(Files.readString(file) + "\n");

            String model = sb.toString();
            assertEquals(parse(model), model);
        }

    }
}
