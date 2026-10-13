package me.simonegazza.lift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.learning.generators.ArrayGenerator;
import me.simonegazza.lift.expressions.MiniZincArray;
import me.simonegazza.lift.expressions.MiniZincArray.IndexRange;
import me.simonegazza.lift.expressions.MiniZincSet;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class ExpressionsTest {

	private static MiniZincArray array(Object... values) {
		return MiniZincArray.fromFlattened(List.of(new IndexRange(1, values.length)), List.of(values));
	}

	@Test
	public void setIsSortedTest() {
		MiniZincSet set = new MiniZincSet(List.of(16, 1, 7));

		assertEquals(1, set.lowerBound());
		assertEquals(16, set.upperBound());
	}

	@Test
	public void setHashCodeIndependentFromOrderTest() {
		MiniZincSet first = new MiniZincSet(List.of(17, 1));
		MiniZincSet second = new MiniZincSet(List.of(1, 17, 1, 1));

		assertEquals(first, second);
		assertEquals(first.hashCode(), second.hashCode());
	}

	@Test
	public void arraySetEqualityTest() {
		MiniZincSet set = new MiniZincSet(List.of(1, 2));

		assertTrue(array(1, 2).equals(set));
		assertTrue(set.equals(array(2, 1)));
		assertFalse(set.equals(array(1)));
		assertFalse(array(1).equals(set));
	}

	@Test
	public void concatIndexSetTest() {
		MiniZincArray result = array(1, 2).concat(array(3, 4));

		assertEquals(List.of(new IndexRange(1, 4)), result.getDimensions());
		assertEquals(4, result.getDimensions().getFirst().size());
		assertEquals(4, result.getMiniZinc(4));
	}

	@Test
	public void boolDimensionsTest() {
		MiniZincParser parser = new MiniZincParser(
			new CommonTokenStream(new MiniZincLexer(CharStreams.fromString("bool: flag = true;"))));
		ParameterGraph graph = new ParameterExtractor(parser.model()).call();
		OriginalParameter flag = graph.getByName("flag").get();
		Lifter.computeValue(flag, graph, new HashMap<>());

		assertEquals(List.of(1), flag.getDimensions());
	}

	@Test
	public void perturbationsAreActualChangesTest() {
		int length = 180;
		List<Integer> percentages = List.of(1, 2, 5, 10, 20, 50);
		for (ArrayGenerator<?> generator : List.of(ArrayGenerator.booleans(), ArrayGenerator.integers(1, 10))) {
			List<? extends List<?>> chain = new ArrayList<>(generator.generate(length, percentages));
			List<?> original = chain.getFirst();

			for (int i = 0; i < percentages.size(); i++) {
				List<?> step = chain.get(i + 1);
				long changed = java.util.stream.IntStream.range(0, length)
					.filter(j -> !original.get(j).equals(step.get(j)))
					.count();
				assertEquals(percentages.get(i) * length / 100, changed);
			}
			assertNotEquals(original, chain.getLast());
		}
	}
}
