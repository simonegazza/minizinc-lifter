package me.simonegazza.lift;

import java.io.IOException;
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
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ModelRunner;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.flatzinc.ConstraintToVariables;
import me.simonegazza.lift.visitors.flatzinc.QuickXPlain;
import me.simonegazza.lift.visitors.flatzinc.VariableCoreExtractor;
import me.simonegazza.lift.visitors.minizinc.Lifter;
import me.simonegazza.lift.visitors.minizinc.ParameterExtractor;
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
 * <li>Compile to FlatZinc and run the model</li>
 * <li>Extract the unsat cores</li>
 * <li>Repeat these lasts steps until all unsat core have been found</li>
 * <li>Report unsat cores and solution found, if any</li>
 * </ol>
 * <p>
 * Note that, if no solution are found during unsat core elimination, we run the
 * model one last time using another solver as a last hope to solve the problem.
 * <p>
 * The tool relies heavily on the ANTLR visitor pattern and on MiniZinc to
 * analyze and rewrite the MiniZinc AST. Visitors are responsible for extracting
 * semantic information from the parsed models and applying structural
 * transformations.
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
	 * Input MiniZinc files to parse.
	 * <p>
	 * Multiple files can be provided. Their contents are concatenated and
	 * parsed as a single MiniZinc model.
	 */
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and DZN file paths", required = true)
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
	 * If specified, use the provided identifier as solver for solution recovery
	 * attempt.
	 */
	@Option(names = {
			"--recovery-solver" }, defaultValue = "gecode", description = "Solver used when running the recovery model (default: ${DEFAULT-VALUE})")
	private String recoverySolver;

	/**
	 * If specified, use the provided identifier as solver for the QuickXPlain
	 * algorithm.
	 */
	@Option(names = "--quickxplain-solver", defaultValue = "cp-sat", description = "Solver used by QuickXPlain (default: ${DEFAULT-VALUE})")
	private String quickXPlainSolver;

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
	 * @param args a {@link String} array from the command link as arguments for
	 *                 this program
	 */
	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	/**
	 * Executes a recovery version of the generated MiniZinc model using fixed
	 * parameter values instead of assumptions.
	 * <p>
	 * This method is used when the goal is to determine whether a concrete
	 * solution exists for the current parameter assignment rather than to
	 * extract an unsatisfiable core.
	 *
	 * @param solverName       the MiniZinc solver identifier to use when
	 *                             executing the recovery model
	 * @param model            the MiniZinc model source code
	 * @param modelsNamePrefix prefix used to generate the recovery model
	 *                             filename
	 *
	 * @return the output produced by {@link ModelRunner#run(Path, String)}
	 *
	 * @throws IOException if the recovery model cannot be written to disk
	 */
	private List<String> runWithFixedParameters(
		String solverName,
		String model,
		String modelsNamePrefix) throws IOException {
		// Remove lines regarding chuffed and add the fixing of parameters
		String lastModel = model.lines()
			.filter(r -> !r.contains("include \"chuffed.mzn\";") && !r.contains("assume(assumed)"))
			.collect(Collectors.joining("\n"));
		lastModel += "\nconstraint forall(assumed);\n";

		// Write .mzn to file
		logger.info("""
			Runnig a recovery version of the last .mzn without assumptions and \
			with parameters fixed! \
			""");
		Path lastModelPath = outputPath.resolve("recovery-" + modelsNamePrefix);
		Files.writeString(Path.of(lastModelPath.toString() + ".mzn"), lastModel);

		return ModelRunner.run(lastModelPath, solverName);
	}

	/**
	 * Runs the QuickXPlain algorithm.
	 * <p>
	 * The conflicting constraints returned by {@link QuickXPlain} are converted
	 * into a FlatZinc model and traversed to collect all variable names
	 * referenced by the failing constraints.
	 *
	 * @param baseModelPath path to the base model used by QuickXPlain
	 * @param lifted        lifted parameters used to initialize the QuickXPlain
	 *                          search
	 *
	 * @return the set of variable names appearing in the conflicting
	 *             constraints identified by QuickXPlain
	 */
	private Set<String> runQuickExplain(Path baseModelPath, List<LiftedParameter> lifted) {
		QuickXPlain qx = new QuickXPlain(baseModelPath, lifted, quickXPlainSolver);

		String failingConstraints = qx.call().stream()
			// add "solve satisfy" to these constraints accepted by the parser
			.collect(Collectors.joining("\n", "", "\nsolve satisfy;"));

		// Parse the constraints to get the variable names
		CharStream constraintInput = CharStreams.fromString(failingConstraints);
		Lexer constriantLexer = new FlatZincLexer(constraintInput);
		TokenStream constraintTokens = new CommonTokenStream(constriantLexer);
		FlatZincParser constaintParser = new FlatZincParser(constraintTokens);
		ConstraintToVariables variableCollector = new ConstraintToVariables();

		return variableCollector.visitModel(constaintParser.model());
	}

	/**
	 * Interprets the output produced by a MiniZinc execution and determines
	 * whether a solution, an unsatisfiable core, or no useful core was
	 * obtained.
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)}
	 *
	 * @return an {@link Optional} containing the set of variables extracted
	 *             from the reported nogood, or {@link Optional#empty()} if no
	 *             core could be obtained
	 *
	 * @throws IndexOutOfBoundsException if {@code commandOutput} is
	 *                                       unexpectedly empty
	 */
	private Optional<Set<String>> analyzeOutput(List<String> commandOutput) {
		if (commandOutput.size() > 2 && commandOutput.contains("----------")) {
			logger.info("A solution has been found!");
			return Optional.empty();
		}

		if ("% Time limit exceeded!".equals(commandOutput.get(0))
			||
			commandOutput.get(0).contains("UNKNOWN")
			||
			// Rule for when we fix parameters
			commandOutput.get(0).contains("UNSATISFIABLE")) {
			return Optional.of(Set.of());
		} else {
			// solution was not found but a core was correctly given
			logger.info("Extracting nogoods...");
			String nogoodLine = commandOutput.get(0);
			return Optional.of(Pattern.compile(",")
				.splitAsStream(
					nogoodLine.substring(2, nogoodLine.length() - 1))
				.map(s -> s.substring(3))
				.collect(Collectors.toSet()));
		}
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

		outputPath = outputPath.toAbsolutePath();

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
		Path modelBasePath = outputPath.resolve(modelsNamePrefix);

		// Creates output folder if it does not exists
		Files.createDirectories(outputPath);
		Files.writeString(Path.of(modelBasePath.toString() + ".mzn"), originalModel);

		// Parse the original model
		logger.info("Parsing the original model...");
		CharStream input = CharStreams.fromString(originalModel);
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		// Get the dependency graph of the parameters and verify the existence
		// of the parameters to be lifted
		ParameterExtractor pe = new ParameterExtractor(parser.model());
		ParameterGraph graph = pe.call();
		for (LiftRequest request : cliParameters) {
			Optional<OriginalParameter> toLift = graph.getByName(request.getName());
			if (toLift.isEmpty()) {
				throw new IllegalArgumentException("Requested lift for "
					+ request.getName()
					+ " but it does not exists");
			}
			OriginalParameter p = toLift.get();
			if (setsDisallowed && (p.getType() instanceof MiniZincSetType
				|| p.getType() instanceof MiniZincArrayType t
					&& t.getSubtype() instanceof MiniZincSetType)) {
				throw new IllegalArgumentException("Requested lift for "
					+ request.getName()
					+ " that has set type but also requesting to disallowing sets");
			}
		}

		// Reset the token for the next pass
		tokens.seek(0);

		// Resolve the dependencies of the parameters and create base model
		logger.info("Lifting parameter representation...");
		Lifter lifter = new Lifter(
			tokens,
			parser.model(),
			cliParameters,
			graph,
			setsDisallowed);
		String baseModel = lifter.call();

		List<LiftedParameter> liftedParameters = lifter.getLifted();

		List<LiftedParameter> parameterToMaximize;
		if (maximizeUserParamsOnly) {
			parameterToMaximize = liftedParameters.stream()
				.filter(p -> cliParameters.stream()
					.anyMatch(c -> c.getName().equals(p.getOriginalName())))
				.toList();
		} else {
			parameterToMaximize = liftedParameters;
		}

		List<Set<RevokedAssumption>> assumptions = new ArrayList<>();
		for (int i = 1;; i++) {
			// Customize the model
			logger.info("Adding assumptions...");
			Assumer assumer = new Assumer(
				baseModel,
				liftedParameters,
				parameterToMaximize,
				assumptions.stream()
					.flatMap(Set::stream)
					.sorted()
					.collect(Collectors.toSet()));
			String liftedModel = assumer.call();

			// Write .mzn to file
			logger.info("Writing lifted .mzn with assumptions");
			String ithModelNamePrefix = "" + i + "-" + modelsNamePrefix;
			Path ithBaseModelPath = outputPath.resolve(ithModelNamePrefix);
			Path ithMznModelPath = Path.of(ithBaseModelPath.toString() + ".mzn");
			Files.writeString(ithMznModelPath, liftedModel);

			// Compile the .mzn and get the .fzn
			logger.info("Compiling the .mzn...");
			ModelRunner.compile(ithBaseModelPath, "chuffed");
			Path fznLiftedPath = Path.of(ithBaseModelPath.toString() + ".fzn");

			// Run the .fzn
			logger.info("Running the lifted model...");
			List<String> commandOutput = ModelRunner.run(ithBaseModelPath, "chuffed");

			Set<String> coreInvolvedVariables;
			Optional<Set<String>> anyVariable = analyzeOutput(commandOutput);
			if (anyVariable.isEmpty()) {
				logger.info("Exiting");
				return 0;
			} else if (anyVariable.get().isEmpty()) {
				logger.info("A solution or an unsat core cannot be found, trying with another solver: "
					+ recoverySolver);

				List<String> recoveryOuput = runWithFixedParameters(
					recoverySolver,
					Files.readString(ithMznModelPath),
					ithModelNamePrefix);

				Optional<Set<String>> recoveryAssumptions = analyzeOutput(recoveryOuput);
				if (recoveryAssumptions.isEmpty()) {
					logger.info("Exiting");
					return 0;
				} else if (recoveryAssumptions.get().isEmpty()) {
					logger.info("""
						I'll run QuickXPlain trying to find a solution, but be aware that the process \
						might get in a loop if the solver answered with UNKNOWN \
						""");
					coreInvolvedVariables = runQuickExplain(
						ithBaseModelPath,
						liftedParameters);
				} else {
					logger.info("I've tried, sorry!");
					throw new IllegalStateException("""
						Tried to recover by running another solver and QuickXPlain \
						but it was impossible to find a solution or get a core \
						""");
				}
			} else {
				coreInvolvedVariables = anyVariable.get();
			}

			// Parse the .fzn
			CharStream fznInput = CharStreams.fromPath(fznLiftedPath);
			Lexer fznLexer = new FlatZincLexer(fznInput);
			TokenStream fznTokens = new CommonTokenStream(fznLexer);
			FlatZincParser fznParser = new FlatZincParser(fznTokens);

			// Visit the .fzn for original names and indexes of parameters
			VariableCoreExtractor coreExtractor = new VariableCoreExtractor(
				fznLiftedPath,
				liftedParameters,
				coreInvolvedVariables,
				fznParser.model());
			Set<RevokedAssumption> newNogoodAssumptions = coreExtractor.call();
			logger.info("Found new assumptions: " + newNogoodAssumptions.stream()
				.map(RevokedAssumption::toString)
				.collect(Collectors.joining(", ")));
			assumptions.add(newNogoodAssumptions);
		}
	}
}
