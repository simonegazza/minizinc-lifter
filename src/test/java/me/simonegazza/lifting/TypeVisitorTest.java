package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Optional;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.visitors.ParameterExtractor;
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
		ParameterExtractor pe = new ParameterExtractor();

		return pe.execute(parser.model());
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
		assertEquals("var int", op.getType().lift(Optional.empty()));
	}

	@Test
	public void simpleTypeExpressionTest() {
		var graph = parse("0..1: a = 1;");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("0..1", op.getType().toString());
		assertEquals("var 0..1", op.getType().lift(Optional.empty()));

	}

	@Test
	public void simpleTypeArrayTest() {
		var graph = parse("array[int] of int: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("array[int] of int", op.getType().toString());
		assertEquals("array[int] of var int", op.getType().lift(Optional.empty()));
	}

	@Test
	public void doublyCompositeTypeTest() {
		var graph = parse("array[int] of set of int: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals("array[int] of set of int", op.getType().toString());
		assertEquals("array[int] of var set of int", op.getType().lift(Optional.empty()));
	}

	@Test
	public void multipleCompositeNamedTypeTest() {
		var graph = parse("array[Comps, Flavs, 0..1] of set of Flavs: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals(
			"array[Comps, Flavs, 0..1] of set of Flavs",
			op.getType().toString());
		assertEquals(
			"array[Comps, Flavs, 0..1] of var set of Flavs",
			op.getType().lift(Optional.empty()));
	}

	@Test
	public void arrayOfArrayTypeTest() {
		var graph = parse("array[Comps, Flavs, 0..1] of array[0..1] of array[0..1] of 0..1: a = [1];");
		var parameter = graph.getNodes().stream().findAny();

		OriginalParameter op = parameter.get();
		assertEquals(
			"array[Comps, Flavs, 0..1] of array[0..1] of array[0..1] of 0..1",
			op.getType().toString());
		assertEquals(
			"array[Comps, Flavs, 0..1] of array[0..1] of array[0..1] of var 0..1",
			op.getType().lift(Optional.empty()));
	}

}
