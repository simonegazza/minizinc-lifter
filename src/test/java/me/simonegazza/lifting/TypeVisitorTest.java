package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.visitors.ParameterVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.jupiter.api.Test;

public class TypeVisitorTest {
	private DirectedGraph<OriginalParameter> parse(String model) {
		CharStream input = CharStreams.fromString(model);

		Lexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		return new ParameterVisitor().visitModel(parser.model());
	}

	@Test
	public void undefinedThrowsTest() {
		assertThrowsExactly(
			new IllegalStateException().getClass(),
			() -> parse("int: a;"));
	}

	@Test
	public void simpleParameterTest() {
		var graph = parse("int: a = 1;");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("int", op.getType().toString());
	}

	@Test
	public void simpleTypeExpressionTest() {
		var graph = parse("0..1: a = 1;");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("0..1", op.getType().toString());
	}

	@Test
	public void simpleTypeArrayTest() {
		var graph = parse("array[int] of int: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("array[int] of int", op.getType().toString());
	}

	@Test
	public void doublyCompositeTypeTest() {
		var graph = parse("array[int] of set of int: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("array[int] of set of int", op.getType().toString());
	}

	@Test
	public void multipleCompositeNamedTypeTest() {
		var graph = parse("array[Comps, Flavs, 0..1] of set of Flavs: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals(
			"array[Comps, Flavs, 0..1] of set of Flavs",
			op.getType().toString());
	}

}
