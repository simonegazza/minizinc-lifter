package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    private String parse(Path path) throws IOException {
        CharStream input = CharStreams.fromFileName(path.toString());

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        ParseTree tree = parser.model();

        PrintingVisitor visitor = new PrintingVisitor(tokens);

        return visitor.visit(tree);
    }

    private String readModel(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    @Test
    void correctGrammarTest() throws IOException {
        try (Stream<Path> stream = Files.walk(resourcesFolder)) {
            List<Path> files = stream.filter(Files::isRegularFile).filter(p -> p.toString().endsWith("mzn"))
                    .collect(Collectors.toList());

            for (Path file : files)
                assertEquals(parse(file), readModel(file));
        }
    }

}
