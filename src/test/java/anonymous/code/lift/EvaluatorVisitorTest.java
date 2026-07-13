package anonymous.code.lift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import anonymous.code.antlr.minizinc.MiniZincLexer;
import anonymous.code.antlr.minizinc.MiniZincParser;
import anonymous.code.lift.expressions.MiniZincArray;
import anonymous.code.lift.visitors.EvaluatorVisitor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	public void testArrays() {
		MiniZincArray result = ((MiniZincArray) eval("[1, 2, 3]"));
		assertEquals(Arrays.asList(1, 2, 3), result.flatten());

		result = ((MiniZincArray) eval("[|1, 2 | 3, 4|]"));
		assertEquals(1, result.getMiniZinc(1, 1));

	}

}
