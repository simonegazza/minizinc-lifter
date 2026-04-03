package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.jupiter.api.Test;

public class LifterTest {

	private String lift(Path model, List<LiftRequest> cliParameters) throws IOException {

		CharStream input = CharStreams.fromPath(model);

		Lexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		ParameterExtractor pv = new ParameterExtractor();
		ParameterGraph graph = pv.execute(parser.model());

		Lifter lv = new Lifter(tokens, cliParameters, graph);

		return lv.execute(parser.model());
	}

	private void testEnding(
		String testName,
		List<LiftRequest> requests)
		throws IOException, URISyntaxException {

		Path testDir = Path.of(
			getClass().getClassLoader().getResource("lifter").toURI());
		Path original = Paths.get(testDir.toString(), testName, "original.mzn");
		Path lifted = Paths.get(testDir.toString(), testName, "lifted.mzn");
		assertEquals(Files.readString(lifted), lift(original, requests));
	}

	@Test
	public void simpleLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.empty()));
		String testName = "simple";
		testEnding(testName, r);
	}

	@Test
	public void simpleLiftWithBoundsTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.empty()));
		String testName = "simple_bounds";

		testEnding(testName, r);
	}

	@Test
	public void setLiftWithBoundsTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.of("0..1")));
		String testName = "set";

		testEnding(testName, r);
	}

	@Test
	public void simpleDoubleLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.empty()));
		String testName = "double_simple";

		testEnding(testName, r);
	}

	@Test
	public void doubleLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(
			new SimpleLiftRequest("a", Optional.empty()),
			new SimpleLiftRequest("b", Optional.of("0..1")));
		String testName = "double";

		testEnding(testName, r);
	}

	@Test
	public void arrayLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new SimpleLiftRequest("a", Optional.empty()));
		String testName = "array";

		testEnding(testName, r);
	}

	@Test
	public void arrayElementLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new ArrayElementLiftRequest(
			"a",
			Optional.empty(),
			List.of("1")));
		String testName = "array_element";

		testEnding(testName, r);
	}

	@Test
	public void arrayDoubleElementLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(
			new ArrayElementLiftRequest(
				"a",
				Optional.empty(),
				List.of("1")),
			new ArrayElementLiftRequest(
				"a",
				Optional.empty(),
				List.of("3")));
		String testName = "array_element_double";

		testEnding(testName, r);
	}

	@Test
	public void array2DElementLiftTest() throws IOException, URISyntaxException {
		List<LiftRequest> r = List.of(new ArrayElementLiftRequest(
			"a",
			Optional.empty(),
			List.of("2", "2")));
		String testName = "double_array_element";

		testEnding(testName, r);
	}

}
