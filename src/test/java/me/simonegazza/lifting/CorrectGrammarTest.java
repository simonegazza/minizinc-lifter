package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class CorrectGrammarTest {
	private String parse(String model) {
		CharStream input = CharStreams.fromString(model);

		Lexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		ParseTree tree = parser.model();

		PrintingVisitor visitor = new PrintingVisitor(tokens);

		return visitor.visit(tree);
	}

	@TestFactory
	List<DynamicTest> eachFileTests() throws URISyntaxException, IOException {
		Path problemsDir = Path.of(
			getClass().getClassLoader().getResource("problems").toURI());

		return Files.walk(problemsDir)
			.filter(Files::isRegularFile)
			.filter(p -> p.toString().endsWith(".mzn") || p.toString().endsWith(".dzn"))
			.map(f -> DynamicTest.dynamicTest(
				f.getName(f.getNameCount() - 2).toString(),
				() -> {
					String model = Files.readString(f);
					assertEquals(parse(model), model);
				}))
			.toList();
	}
}
