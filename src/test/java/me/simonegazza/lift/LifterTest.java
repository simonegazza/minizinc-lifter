package me.simonegazza.lift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class LifterTest {

	private static ParameterGraph graph(String model) {
		MiniZincParser parser = new MiniZincParser(
			new CommonTokenStream(new MiniZincLexer(CharStreams.fromString(model))));
		return new ParameterExtractor(parser.model()).call();
	}

	private static Set<String> dependencies(ParameterGraph graph, String name) {
		return graph.getAdjacent(graph.getByName(name).get()).stream()
			.map(OriginalParameter::getName)
			.collect(Collectors.toSet());
	}

	private static String lift(String model, String... parameters) {
		return new ModelLifter(
			model,
			List.of(parameters).stream().map(LiftRequest::parse).toList(),
			false,
			false).call().withAssumptions(List.of());
	}

	@Test
	public void generatorVariableIsNotDependencyTest() {
		ParameterGraph g = graph("""
			int: a = sum(b in 1..2)(b);
			int: b = a + 1;
			""");

		assertEquals(Set.of(), dependencies(g, "a"));
		assertEquals(Set.of("a"), dependencies(g, "b"));
	}

	@Test
	public void letLocalIsNotParameterTest() {
		ParameterGraph g = graph("""
			int: n = 3;
			int: tot = let { int: k = 2 } in k * n;
			""");

		assertTrue(g.getByName("k").isEmpty());
		assertEquals(Set.of("n"), dependencies(g, "tot"));
	}

	@Test
	public void backwardClosureTerminatesOnCyclesTest() {
		ParameterGraph g = graph("""
			int: a = b;
			int: b = a;
			""");

		Set<String> closure = assertTimeoutPreemptively(
			Duration.ofSeconds(5),
			() -> g.backwardClosure(g.getByName("a").get()).stream()
				.map(OriginalParameter::getName)
				.collect(Collectors.toSet()));
		assertEquals(Set.of("a", "b"), closure);
	}

	@Test
	public void derivedParameterKeepsDefinitionTest() {
		String model = lift("""
			int: n = 3;
			int: m = n + 1;
			var 0..10: x;
			constraint x > m;
			solve satisfy;
			""", "n:1..5");

		assertTrue(model.contains("m_lifted :: doc_comment(\"m_lifted\") = n_lifted + 1;"));
		assertTrue(model.contains("0 %abs(m_lifted - m)"));
		assertTrue(model.contains("var 1..5: n_lifted :: doc_comment(\"n_lifted\");"));
		assertFalse(model.contains("0 %abs(n_lifted - n)"));
	}

	@Test
	public void requestedParameterIsFreeTest() {
		String model = lift("""
			array[1..2] of int: w = [1, 2];
			var 0..10: x;
			constraint x > w[1];
			solve satisfy;
			""", "w");

		assertTrue(model.contains("array[1..2] of var int: w_lifted :: doc_comment(\"w_lifted\");"));
		assertFalse(model.contains("0 %sum"));
	}

	@Test
	public void boundingConstraintsTest() {
		String model = lift("""
			int: a = 4;
			int: c = 2;
			var 0..a: b;
			var c..10: lo;
			array[1..3] of var c..a: xs;
			var set of 0..a: s;
			var 0..10: untouched;
			solve satisfy;
			""", "a", "c");

		assertTrue(model.contains("constraint b <= (a_lifted);"));
		assertTrue(model.contains("constraint lo >= (c_lifted);"));
		assertTrue(model.contains(
			"constraint forall(lifted_bound_element in xs)(lifted_bound_element >= (c_lifted));"));
		assertTrue(model.contains(
			"constraint forall(lifted_bound_element in xs)(lifted_bound_element <= (a_lifted));"));
		assertTrue(model.contains("constraint s subset (0)..(a_lifted);"));
		assertFalse(model.contains("constraint untouched"));
		assertFalse(model.contains("constraint b >="));
	}

	@Test
	public void varDefinitionUsesLiftedTest() {
		String model = lift("""
			int: n = 3;
			var int: z = n + 1;
			solve satisfy;
			""", "n");

		assertTrue(model.contains("var int: z = n_lifted + 1;"));
	}

	@Test
	public void multiDimensionalIndexSetsTest() {
		String model = lift("""
			array[int, int] of int: a = array2d(1..2, 1..2, [1, 2, 3, 4]);
			var 0..10: x;
			constraint x > a[1, 1];
			solve satisfy;
			""", "a");

		assertTrue(model.contains("array[index_set_1of2(a), index_set_2of2(a)] of var int: a_lifted"));
	}
}
