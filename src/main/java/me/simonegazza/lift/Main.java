package me.simonegazza.lift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import me.simonegazza.antlr.flatzinc.FlatZincLexer;
import me.simonegazza.antlr.flatzinc.FlatZincParser;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.assumptions.Assumer;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.FlatZincVisitor;
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
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(Main.class.getSimpleName());

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
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and MDN file paths", required = true)
	private List<Path> filePaths;

	/**
	 * Parameters that should be lifted.
	 * <p>
	 * Each parameter specification is parsed into a {@link LiftRequest}. A lift
	 * request describes which parameter should become a decision variable and
	 * may also contain additional information such as bounds.
	 */
	@Option(names = { "-p",
			"--parameters" }, arity = "1..*", description = "Parameter to lift the model with", required = true)
	private Set<String> parameters;

	/**
	 * Folder path where models will be saved.
	 * <p>
	 * If not provided, a folder will be created with that name.
	 */
	@Option(names = { "-o", "--output" }, description = "Output folder path", required = true)
	private Path outputPath;

	/**
	 * Helper method to print {@link RevokedAssumption}.
	 *
	 * @param assumptions the list of sets of assumptions to print out
	 */
	private void printAssumptions(List<Set<RevokedAssumption>> assumptions) {
		logger.info("Unsat cores found:");
		for (int j = 1; j < assumptions.size(); j++) {
			logger.info("(iteration " + j + ") " + assumptions.get(j - 1).stream()
				.sorted()
				.map(RevokedAssumption::toString)
				.collect(Collectors.joining(", ")));
		}
	}

	/**
	 * Helper method that passes the control to MiniZinc either to run the
	 * compilation or to actually run the model.
	 *
	 * @param modelPath  the path to the model to run or compile
	 * @param compile    whether we turn on compilation or run
	 * @param solverName the solver name to pass to MiniZinc (like "chuffed" or
	 *                       "gecode")
	 *
	 * @return the last 10 rows of the output
	 *
	 * @throws IOException          can occur when inheriting IO
	 * @throws InterruptedException in case command get stopped by the OS
	 */
	private List<String> runCommand(Path modelPath, boolean compile, String solverName)
		throws IOException, InterruptedException {
		List<String> command = List.of(
			"minizinc",
			"--solver", solverName,
			"-w", // suppress warnings
			// 1 minute timeout expressed in milliseconds
			"--time-limit", String.valueOf(1000 * 60 * 1),
			// "--verbose",
			modelPath.toString());

		if (compile) {
			command = new ArrayList<>(command);
			command.add("--compile");
		}

		ProcessBuilder compilationPB = new ProcessBuilder(command);
		compilationPB.redirectErrorStream(true);
		compilationPB.directory(modelPath.getParent().toFile());

		Process compilationProcess = compilationPB.start();

		InputStreamReader isr = new InputStreamReader(compilationProcess.getInputStream());
		BufferedReader reader = new BufferedReader(isr);
		List<String> result = reader.lines()
			.peek(System.out::println)
			.toList();

		int exitCode = compilationProcess.waitFor();
		if (exitCode != 0) {
			throw new IllegalStateException("MiniZinc terminated with error code: " + exitCode);
		}

		return result.reversed();
	}

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
		logger.info("Application starts");

		// Parse cli arguments
		List<LiftRequest> cliParameters = parameters.stream()
			.map(LiftRequest::parse).toList();

		// Reading and appending files
		StringBuilder sb = new StringBuilder();
		for (Path fp : filePaths) {
			logger.info("Reading file " + fp.toString() + "...");
			sb.append(Files.readString(fp) + "\n");
		}

		String originalModel = sb.toString();
		String modelsNamePrefix = filePaths.stream()
			.filter(fp -> fp.toFile().getName().endsWith(".mzn"))
			.map(fp -> {
				String filename = fp.toFile().getName();
				int idx = filename.lastIndexOf(".");
				return filename.substring(0, idx);
			})
			.findFirst()
			.orElse("original");
		Path originalModelPath = Path.of(outputPath.toString(), modelsNamePrefix + ".mzn");

		// Creates output folder if it does not exists
		outputPath = outputPath.resolve(outputPath);
		Files.createDirectories(outputPath);
		Files.writeString(originalModelPath, originalModel);

		// Parse the original model
		logger.info("Parsing the original model...");
		CharStream input = CharStreams.fromString(originalModel);
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		// Get the dependency graph of the parameters and verify the existence
		// of the parameters to be lifted
		ParameterExtractor pe = new ParameterExtractor();
		ParameterGraph graph = pe.execute(parser.model());
		for (LiftRequest request : cliParameters) {
			Optional<OriginalParameter> toLift = graph.getByName(request.getName());
			if (toLift.isEmpty()) {
				throw new IllegalArgumentException(
					"Requested lift for " + request.getName() + " but it does not exists");
			}

		}

		// Reset the token for the next pass
		tokens.seek(0);

		// Resolve the dependencies of the parameters and create base model
		logger.info("Lifting parameter representation...");
		Lifter lifter = new Lifter(tokens, cliParameters, graph);
		String baseModel = lifter.execute(parser.model());

		List<Set<RevokedAssumption>> assumptions = new ArrayList<>();
		for (int i = 1;; i++) {
			// Customize the model
			logger.info("Adding assumptions...");
			Assumer assumer = new Assumer(
				baseModel,
				lifter.getLifted(),
				assumptions.stream()
					.flatMap(Set::stream)
					.sorted()
					.collect(Collectors.toSet()));
			String liftedModel = assumer.execute();

			// Write .mzn to file
			logger.info("Writing lifted .mzn with assumptions");
			Path liftedModelPath = Path.of(outputPath.toString(), "" + i + "-" + modelsNamePrefix + ".mzn")
				.toAbsolutePath();
			Files.writeString(liftedModelPath, liftedModel);

			// Compile the .mzn and get the .fzn
			logger.info("Compiling the .mzn...");
			runCommand(liftedModelPath, true, "chuffed");
			Path fznLiftedPath = Path.of(outputPath.toString(), "" + i + "-" + modelsNamePrefix + ".fzn")
				.toAbsolutePath();

			// Run the .fzn
			logger.info("Running the lifted model...");
			// var lastLinesCommandOutput = runCommand(fznLiftedPath, false);
			// Currently running the .mzn (which means we compile the file two
			// times) due to a bug in MiniZinc
			List<String> commandOutput = runCommand(liftedModelPath, false, "chuffed");

			// Check if we found a solution
			if ("=====UNKNOWN=====".equals(commandOutput.get(0))) {
				logger.info("""
					A solution or an unsat core cannot be found, \
					trying to recover by running the solver with parameter values \
					fixed (and without assumptions)""");

				// Remove lines regarding chuffed and add the fixing of
				// parameters
				String lastModel = liftedModel.lines()
					.filter(r -> (!r.contains("include \"chuffed.mzn\";")
						&& !r.contains("array[int] of var bool: assumed = ")
						&& !r.contains("assume(assumed)")))
					.collect(Collectors.joining("\n"));
				lastModel += "\nconstraint forall(i in index_set(params))(params_lifted[i] = params[i]);\n";

				// Write .mzn to file
				logger.info("Writing lifted last .mzn without assumptions and parameters fixed!");
				Path lastModelPath = Path.of(outputPath.toString(), "last-" + modelsNamePrefix + ".mzn")
					.toAbsolutePath();
				Files.writeString(lastModelPath, lastModel);

				commandOutput = runCommand(lastModelPath, false, "gecode");

				if ("----------".equals(commandOutput.get(1))) {
					logger.info("A solution has been found!");
				}

				if ("==========".equals(commandOutput.get(0))) {
					logger.info("The optimal solution has been found!");
				}

				printAssumptions(assumptions);
				return 0;

			} else if ("----------".equals(commandOutput.get(2))) {
				logger.info("A solution has been found, exiting...");
				printAssumptions(assumptions);
				return 0;
			}

			// Get the nogoods
			logger.info("Extracting nogoods...");
			String nogoodLine = commandOutput.get(0);
			List<String> nogoods = Pattern.compile(",")
				.splitAsStream(
					nogoodLine.substring(2, nogoodLine.length() - 1))
				.map(s -> s.substring(3))
				.toList();

			// Parse the .fzn
			CharStream fznInput = CharStreams.fromPath(fznLiftedPath);
			Lexer fznLexer = new FlatZincLexer(fznInput);
			TokenStream fznTokens = new CommonTokenStream(fznLexer);
			FlatZincParser fznParser = new FlatZincParser(fznTokens);

			// Visit the .fzn for original names and indexes of parameters
			FlatZincVisitor fznVisitor = new FlatZincVisitor(fznLiftedPath, lifter.getLifted(), nogoods);
			Set<RevokedAssumption> newNogoodAssumptions = fznVisitor.execute(fznParser.model());
			logger.info("Found new assumptions: " + newNogoodAssumptions.stream()
				.map(RevokedAssumption::toString)
				.collect(Collectors.joining(", ")));
			assumptions.add(newNogoodAssumptions);
		}
	}
}
