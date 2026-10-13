package me.simonegazza.lift;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.ApplicationLogger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * CLI entry point for the {@code mzn-parameter-lifting} tool.
 * <p>
 * This tool reads one MiniZinc model and generates a transformed version of the
 * model where selected parameters are <i>lifted</i> into decision variables.
 * The lifting is performed by {@link ModelLifter} and does not require any data
 * file: the generated model can be run with any data file for the original
 * model.
 * <p>
 * The tool relies heavily on the ANTLR visitor pattern to analyze and rewrite
 * the MiniZinc AST. Visitors are responsible for extracting semantic
 * information from the parsed models and applying structural transformations.
 * <p>
 * The CLI interface is implemented using Picocli.
 */
@CommandLine.Command(name = "mzn-parameter-lifting", mixinStandardHelpOptions = true, version = "0.1", description = "Lift parameter for MiniZinc models")
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
	 * Computes the prefix used to name the generated models, i.e., the name
	 * (without extension) of the first {@code .mzn} file.
	 *
	 * @param filePaths the input file paths
	 *
	 * @return the name of the first {@code .mzn} file without extension, or
	 *             {@code "original"} if there is none
	 */
	public static String getModelsNamePrefix(List<Path> filePaths) {
		return filePaths.stream()
			.filter(fp -> fp.toFile().getName().endsWith(".mzn"))
			.map(fp -> {
				String filename = fp.toFile().getName();
				int idx = filename.lastIndexOf(".");
				return filename.substring(0, idx);
			})
			.findFirst()
			.orElse("original");
	}

	/**
	 * Input MiniZinc files to parse.
	 * <p>
	 * Multiple files can be provided. Their contents are concatenated and
	 * parsed as a single MiniZinc model.
	 */
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN file paths", required = true)
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
	 * Folder path where the lifted model will be saved.
	 */
	@Option(names = { "-o", "--output" }, description = "Output folder path", required = true)
	private Path outputPath;

	/**
	 * If specified, avoids lifting sets and array of sets in the MiniZinc
	 * model.
	 */
	@Option(names = { "--disallow-sets-lifting" }, description = "Avoid lifting sets and array of sets")
	private boolean setsDisallowed;

	/**
	 * If specified, uses only the parameter specified by the user during
	 * lifting in the objective function.
	 */
	@Option(names = "--maximize-user-params-only", description = "Put only user specified parameters in the maximize objective")
	private boolean maximizeUserParamsOnly;

	/**
	 * Application entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command line as arguments
	 */
	public static void main(String[] args) {
		new CommandLine(new Main()).execute(args);
	}

	/**
	 * Executes the lifting pipeline and writes the lifted model (with
	 * assumptions and objective function) in the output folder.
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

		String liftedModel = new ModelLifter(
			sb.toString(),
			cliParameters,
			setsDisallowed,
			maximizeUserParamsOnly).call()
			.withAssumptions(List.of());

		// Write .mzn to file
		Files.createDirectories(outputPath);
		Path liftedModelPath = outputPath.toAbsolutePath()
			.resolve("lifted-" + getModelsNamePrefix(filePaths) + ".mzn");
		Files.writeString(liftedModelPath, liftedModel);
		logger.info("Lifted model written to " + liftedModelPath);

		return 0;
	}
}
