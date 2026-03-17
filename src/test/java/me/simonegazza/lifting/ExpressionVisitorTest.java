package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.visitors.ExpressionVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ExpressionVisitorTest {

	private Object eval(String expr) {
		MiniZincLexer lexer = new MiniZincLexer(CharStreams.fromString(expr));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);
		return new ExpressionVisitor().visit(parser.expr());
	}

	// @Test
	public void testSimpleExpressions() {
		assertEquals(5, eval("2 + 3"));
		assertEquals(6, eval("2 * 3"));
		assertEquals(-3, eval("-3"));
		assertEquals(true, eval("not false"));
		assertEquals(14, eval("2 + 3 * 4"));
		assertEquals("HelloWorld", eval("\"Hello\" ++ \"World\""));
	}

	// @Test
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
	}

	// @Test
	public void testGenerators() {
		Object result = eval("[i * i | i in 1..3]");
		assertEquals(Arrays.asList(1, 4, 9), result);

		result = eval("[i + j | i in 1..2, j in 1..3]");
		assertEquals(Arrays.asList(2, 3, 4, 3, 4, 5), result);

		result = eval("[i | i in 1..5 where i mod 2 == 0]");
		assertEquals(Arrays.asList(2, 4), result);
	}

//	@Test
//	public void testFunctions() {
//		Object result = eval("sum([1, 2, 3, 4])");
//		assertEquals(10, result);
//
//		result = eval("sum([i | i in 1..4])");
//		assertEquals(10, result);
//
//		result = eval("length([1, 2, 3])");
//		assertEquals(3, result);
//
//		result = eval("array2d(1..2, 1..3, [1, 2, 3, 4, 5, 6])");
//		assertTrue(result instanceof MiniZincArray);
//		MiniZincArray arrND = (MiniZincArray) result;
//		assertEquals(
//			new RangeExpression(1, 2),
//			((RangeExpression) arrND.getDimensions().get(0)));
//		assertEquals(
//			new RangeExpression(1, 3),
//			((RangeExpression) arrND.getDimensions().get(1)));
//		assertEquals(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)), arrND.getArray());
//
//		result = eval("array2d({1, 2}, {1, 2, 3}, [1, 2, 3, 4, 5, 6])");
//		assertTrue(result instanceof MiniZincArray);
//		arrND = (MiniZincArray) result;
//		assertEquals(Set.of(1, 2), arrND.getDimensions().get(0));
//		assertEquals(Set.of(1, 2, 3), arrND.getDimensions().get(1));
//
//		result = eval("array1d(1..2, [i | i in 3..4])");
//		assertTrue(result instanceof MiniZincArray);
//	}
}
