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
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * CLI entry point for the {@code mzn-parameter-lifting} tool.
 * <p>
 * This tool reads one MiniZinc model, analyzes its parameters, and generates a
 * transformed version of the model where selected parameters are <i>lifted</i>
 * into decision variables.
 * <p>
 * The typical workflow executed by this class is:
 * <ol>
 * <li>Load the input MiniZinc files.</li>
 * <li>Parse them using ANTLR ({@link MiniZincLexer} and
 * {@link MiniZincParser}).</li>
 * <li>Traverse the AST with {@link ParameterExtractor} to discover parameters
 * and their dependencies.</li>
 * <li>Validate the parameters requested via CLI.</li>
 * <li>Apply the lifting transformation using {@link Lifter}.</li>
 * <li>Output the transformed model.</li>
 * </ol>
 * <p>
 * The tool relies heavily on the ANTLR visitor pattern to analyze and rewrite
 * the MiniZinc AST. Visitors are responsible for extracting semantic
 * information from the parsed model and applying structural transformations.
 * <p>
 * The CLI interface is implemented using Picocli.
 */
@CommandLine.Command(name = "mzn-parameter-lifting", mixinStandardHelpOptions = true, version = "0.1", description = "Parameter lifts a MiniZinc Model")
public class Main implements Callable<Integer> {

	/**
	 * Extracts the exact original source text corresponding to an ANTLR rule
	 * context.
	 * <p>
	 * ANTLR contexts normally expose structured information (tokens, children,
	 * etc.) but not the full original text segment (with, e.g., spaces). This
	 * helper reconstructs the precise substring from the underlying
	 * {@link CharStream}.
	 * <p>
	 * This is particularly useful when rewriting the model because it allows
	 * the transformation logic to preserve the original formatting and
	 * whitespace.
	 *
	 * @param ctx the parser rule context
	 *
	 * @return the exact text fragment from the original source
	 */
	public static String getFullText(ParserRuleContext ctx) {
		CharStream stream = ctx.start.getInputStream();
		return stream.getText(new Interval(
			ctx.getStart().getStartIndex(),
			ctx.getStop().getStopIndex()));
	}

	/**
	 * Input MiniZinc files to parse.
	 * <p>
	 * Multiple files can be provided. Their contents are concatenated and
	 * parsed as a single MiniZinc model.
	 */
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and MDN file paths")
	private List<String> filePaths;

	/**
	 * Parameters that should be lifted.
	 * <p>
	 * Each parameter specification is parsed into a {@link LiftRequest}. A lift
	 * request describes which parameter should become a decision variable and
	 * may also contain additional information such as bounds.
	 */
	@Option(names = { "-p", "--parameters" }, arity = "1..*", description = "Parameter to lift the model with")
	private Set<String> parameters;

	/**
	 * Optional output file.
	 * <p>
	 * If not provided, the transformed model is printed to stdout.
	 */
	@Option(names = { "-o", "--output" }, description = "Output file path (default prints to console)")
	private Optional<File> outputFile = Optional.empty();

	/**
	 * Application entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command link as arguments for
	 *                 this program
	 */
	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	/**
	 * Executes the lifting pipeline.
	 *
	 * @return {@code 0} if execution completes successfully
	 *
	 * @throws Exception if parsing or transformation fails
	 */
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

		ParameterExtractor pe = new ParameterExtractor();
		DirectedGraph<OriginalParameter> graph = pe.execute(parser.model());
		tokens.seek(0);

		for (LiftRequest request : cliParameters) {
			Optional<OriginalParameter> toLift = graph.getNodes().stream()
				.filter(p -> p.getName().equals(request.getName()))
				.findAny();
			if (toLift.isEmpty())
				throw new IllegalArgumentException(
					"Requested lift for " + request.getName() + " but it does not exists");
		}

		Lifter lifter = new Lifter(tokens, cliParameters, graph);
		String liftedModel = lifter.execute(parser.model());

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
