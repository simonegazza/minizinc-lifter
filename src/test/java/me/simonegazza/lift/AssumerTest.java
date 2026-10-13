package me.simonegazza.lift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Pattern;
import me.simonegazza.lift.assumptions.Assumer;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.requests.LiftRequest;
import org.junit.jupiter.api.Test;

public class AssumerTest {

	private static ModelLifter.LiftedModel lift(String model, String... parameters) {
		return new ModelLifter(
			model,
			List.of(parameters).stream().map(LiftRequest::parse).toList(),
			false,
			false).call();
	}

	private static long count(String text, String regex) {
		return Pattern.compile(regex).matcher(text).results().count();
	}

	@Test
	public void simpleAndArrayShareTypeTest() {
		var lifted = lift("""
			int: a = 1;
			array[1..2] of int: b = [1, 2];
			var int: x;
			constraint x = a + b[1];
			solve satisfy;
			""", "a", "b");

		var groups = Assumer.collectParameterTypes(lifted.liftedParameters());
		assertEquals(List.of("int"), List.copyOf(groups.keySet()));

		String model = lifted.withAssumptions(List.of());
		assertEquals(1, count(model, "array\\[int\\] of int: params_int ="));
		assertEquals(1, count(model, "array\\[int\\] of var int: params_int_lifted ="));
	}

	@Test
	public void floatArrayObjectiveTest() {
		var lifted = lift("""
			array[1..2] of float: b = [1.0, 2.0];
			var float: x;
			constraint x = b[1];
			solve satisfy;
			""", "b");

		String model = lifted.withAssumptions(List.of());
		assertTrue(model.contains("var float: objective_lifted"));
	}

	@Test
	public void deterministicOrderTest() {
		String source = """
			int: a = 1;
			array[1..2] of int: b = [1, 2];
			array[1..2] of float: c = [1.0, 2.0];
			int: d = 3;
			var float: x;
			constraint x = a + b[1] + c[1] + d;
			solve satisfy;
			""";

		String first = lift(source, "d", "c", "b", "a").withAssumptions(List.of());
		for (int i = 0; i < 10; i++) {
			assertEquals(first, lift(source, "a", "b", "c", "d").withAssumptions(List.of()));
		}
	}

	@Test
	public void floatRangeTypeTest() {
		var lifted = lift("""
			0.0..10.0: p = 1.5;
			var float: x;
			constraint x = p;
			solve satisfy;
			""", "p");

		assertEquals(List.of("float"), List.copyOf(Assumer.collectParameterTypes(lifted.liftedParameters()).keySet()));
		assertTrue(lifted.withAssumptions(List.of()).contains("var float: objective_lifted"));
	}

	@Test
	public void intRangeTypeTest() {
		var lifted = lift("""
			1..10: p = 3;
			var int: x;
			constraint x = p;
			solve satisfy;
			""", "p");

		assertEquals(List.of("int"), List.copyOf(Assumer.collectParameterTypes(lifted.liftedParameters()).keySet()));
	}

	@Test
	public void revokedAssumptionOrderTest() {
		var first = new RevokedAssumption("a_lifted", List.of(1, 2));
		var second = new RevokedAssumption("a_lifted", List.of(1, 3));

		assertTrue(first.compareTo(second) < 0);
		assertTrue(second.compareTo(first) > 0);
		assertEquals(0, first.compareTo(new RevokedAssumption("a_lifted", List.of(1, 2))));
	}
}
