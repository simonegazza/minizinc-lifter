package me.simonegazza.lift;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import picocli.CommandLine;
import picocli.CommandLine.Option;

@CommandLine.Command(name = "mzn-parameter-lifting", mixinStandardHelpOptions = true, version = "0.1", description = "Parameter lifts a MiniZinc Model")
public class Main implements Callable<Integer> {

	/**
	 * Given a rule context, it extrapolates all the text from the corresponding
	 * {@link org.antlr.v4.runtime.CharStream}, comprised of the spaces (as if
	 * it was the original text)
	 *
	 * @param ctx the context to get the values from
	 *
	 * @return the original program text
	 */
	public static String getFullText(ParserRuleContext ctx) {
		CharStream stream = ctx.start.getInputStream();
		return stream.getText(new Interval(
			ctx.getStart().getStartIndex(),
			ctx.getStop().getStopIndex()));
	}

	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and MDN file paths")
	private List<String> filePaths;

	@Option(names = { "-p", "--parameters" }, arity = "1..*", description = "Parameter to lift the model with")
	private Set<String> parameters;

	@Option(names = { "-o", "--output" }, description = "Output file path (default prints to console)")
	private Optional<File> outputFile = Optional.empty();

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() throws Exception {
		if (filePaths == null)
			throw new IllegalArgumentException("No file to parse, exiting");
		else if (parameters == null)
			throw new IllegalArgumentException("No lifting asked, nothing to do");

		List<LiftRequest> cliParameters = parameters.stream()
			.map(LiftRequest::parse).toList();

		StringBuilder sb = new StringBuilder();
		for (String fp : filePaths)
			sb.append(Files.readString(Path.of(fp)) + "\n");

		CharStream input = CharStreams.fromString(sb.toString());
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		ParameterVisitor pv = new ParameterVisitor();
		DirectedGraph<OriginalParameter> graph = pv.visitModel(parser.model());

		for (LiftRequest request : cliParameters) {
			List<OriginalParameter> toLift = graph.getNodes().stream()
				.filter(p -> p.getName().equals(request.getName()))
				.toList();

			// Check parameter existence
			if (toLift.isEmpty())
				throw new IllegalArgumentException(
					"Requested lift for " + request.getName() + " but it does not exists");
		}

		Lifter lv = new Lifter(tokens, cliParameters, graph);
		String liftedModel = lv.execute(parser.model());

		if (outputFile.isEmpty()) {
			System.out.println(liftedModel);
		} else {
			PrintWriter pw = new PrintWriter(outputFile.get());
			pw.println(liftedModel);
			pw.close();
		}

		return 0;
	}
}
