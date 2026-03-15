package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.jupiter.api.Test;

public class LifterTest {

	private String lift(String model, List<LiftRequest> cliParameters) {

		CharStream input = CharStreams.fromString(model);

		Lexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		ParameterVisitor pv = new ParameterVisitor();
		DirectedGraph<OriginalParameter> graph = pv.visitModel(parser.model());

		Lifter lv = new Lifter(tokens, cliParameters, graph);

		return lv.execute(parser.model());
	}

	@Test
	public void noLiftTest() {
		List<LiftRequest> r = new ArrayList<>();
		String model = "int: a = 1;";

		assertEquals(model, lift(model, r));
	}

	@Test
	public void simpleLiftTest() {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.empty()));
		String model = "int: a = 1;";

		assertEquals("int: a = 1;\nvar int: a_lifted;\n", lift(model, r));
	}

	@Test
	public void simpleLiftWithBoundsTest() {
		List<LiftRequest> r = List.of(new SimpleLiftRequest(
			"a", Optional.of("0..1")));
		String model = "int: a = 1;";

		assertEquals("int: a = 1;\nvar 0..1: a_lifted;\n", lift(model, r));
	}

	@Test
	public void setLiftWithBoundsTest() {
		List<LiftRequest> r = List.of(new SimpleLiftRequest(
			"a", Optional.of("0..1")));
		String model = "set of int: a = 1;";

		assertEquals("set of int: a = 1;\nvar set of 0..1: a_lifted;\n", lift(model, r));
	}

	@Test
	public void arrayLiftWithBoundsTest() {
		List<LiftRequest> r = List.of(new SimpleLiftRequest(
			"a", Optional.of("0..1")));
		String model = "array[int] of int: a = [1];";

		assertEquals("array[int] of int: a = [1];\narray[int] of var 0..1: a_lifted;\n", lift(model, r));
	}

}
