package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.visitors.EvaluatorVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class EvaluatorVisitorTest {

	private Object eval(String expr, Map<String, Object> env) {
		MiniZincLexer lexer = new MiniZincLexer(CharStreams.fromString(expr));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);
		return new EvaluatorVisitor(env).visit(parser.expr());
	}

	private Object eval(String expr) {
		return eval(expr, new HashMap<String, Object>());
	}

	@Test
	public void simpleExpressionsTest() {
		assertEquals(5, eval("2 + 3"));
		assertEquals(6, eval("2 * 3"));
		assertEquals(-3, eval("-3"));
		assertEquals(true, eval("not false"));
		assertEquals(14, eval("2 + 3 * 4"));
		assertEquals("HelloWorld", eval("\"Hello\" ++ \"World\""));
	}

	@Test
	public void testArrayAccess() {
		Map<String, Object> env = new HashMap<>();
		env.put("Res", IntStream.rangeClosed(1, 5).boxed().toList());
		env.put("bestBounds", IntStream.rangeClosed(1, 5).boxed().toList());
		env.put("cpu", 1);
		env.put("ram", 2);

		assertEquals(1, eval("bestBounds[1]", env));
		assertEquals(1, eval("bestBounds[cpu]", env));
		assertEquals(2, eval("bestBounds[ram]", env));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testArrays() {
		Object result = eval("[1, 2, 3]");
		assertTrue(result instanceof Collection<?>);
		assertEquals(Arrays.asList(1, 2, 3), result);

		result = eval("[[1, 2], [3, 4]]");
		assertTrue(result instanceof List<?>);
		List<Object> arr2d = (List<Object>) result;
		List<Object> firstRow = (List<Object>) arr2d.get(0);
		assertEquals(1, firstRow.get(0));

		Map<String, Object> env = new HashMap<>();
		env.put("Nodes0", IntStream.rangeClosed(1, 3).boxed().toList());
		env.put("Res", IntStream.rangeClosed(1, 5).boxed().toList());
		env.put("bestBounds", IntStream.rangeClosed(1, 5).boxed().toList());
		result = eval(
			"array2d(Nodes0, Res,\n"
				+ "	[bestBounds[r] | r in Res] ++ [\n"
				+ "	3, 16, 512, 99, 0, 1, 1, 1, 1, 0])",
			env);
		arr2d = (List<Object>) result;
		firstRow = (List<Object>) arr2d.get(0);
		assertEquals(1, firstRow.get(0));
	}

	@Test
	public void testGenerators() {
		Object result = eval("[i * i | i in 1..3]");
		assertEquals(Arrays.asList(1, 4, 9), result);

		result = eval("[i + j | i in 1..2, j in 1..3]");
		assertEquals(Arrays.asList(2, 3, 4, 3, 4, 5), result);

		result = eval("[i + j | i, j in 1..2]");
		assertEquals(Arrays.asList(2, 3, 3, 4), result);

		result = eval("[i | i in 1..5 where i mod 2 == 0]");
		assertEquals(Arrays.asList(2, 4), result);
	}

	@Test
	public void testFunctions() {
		Object result = eval("sum([1, 2, 3, 4])");
		assertEquals(10, result);

		result = eval("sum([i | i in 1..4])");
		assertEquals(10, result);

		result = eval("length([1, 2, 3])");
		assertEquals(3, result);

		result = eval("array2d(1..2, 1..3, [1, 2, 3, 4, 5, 6])");
		assertTrue(result instanceof List<?>);
		assertEquals(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)), result);

		result = eval("array2d({1, 2}, {1, 2, 3}, [1, 2, 3, 4, 5, 6])");
		result = eval("array2d(1..2, 1..3, [1, 2, 3, 4, 5, 6])");
		assertTrue(result instanceof List<?>);
		assertEquals(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)), result);

		result = eval("array1d(1..2, [i | i in 3..4])");
		assertEquals(Arrays.asList(3, 4), result);
	}
}
