package me.simonegazza.recovery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.lift.Main;
import me.simonegazza.lift.ModelLifter;
import me.simonegazza.lift.assumptions.Assumer;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ModelRunner;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * CLI entry point for the automatic feasibility recovery of an unsatisfiable
 * MiniZinc instance.
 * <p>
 * The workflow executed by this class is:
 * <ol>
 * <li>Load the input MiniZinc model and data files.</li>
 * <li>Lift the requested parameters using {@link ModelLifter}.</li>
 * <li>Add the assumptions and run the lifted model</li>
 * <li>Extract the unsat cores</li>
 * <li>Repeat these lasts steps until all unsat core have been found</li>
 * <li>Report unsat cores and solution found, if any</li>
 * </ol>
 * <p>
 * Note that, if no solution are found during unsat core elimination, we run the
 * model one last time using another solver as a last hope to solve the problem.
 * <p>
 * The CLI interface is implemented using Picocli.
 */
@CommandLine.Command(name = "mzn-feasibility-recovery", mixinStandardHelpOptions = true, version = "0.1", description = "Recover the feasibility of a MiniZinc instance by lifting parameters")
public class FeasibilityRecovery implements Callable<Integer> {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger
		.getLogger(FeasibilityRecovery.class.getSimpleName());

	/**
	 * Statistics gathered during the last execution of {@link #call()}.
	 * <p>
	 * Initialised to an empty instance so that {@link #getStats()} never
	 * returns {@code null} even when {@link #call()} has not been invoked.
	 */
	private RunStatistics stats = new RunStatistics();

	/**
	 * Returns the statistics gathered during the last execution of
	 * {@link #call()}.
	 * <p>
	 * If {@link #call()} has not been invoked yet, an empty
	 * {@link RunStatistics} instance with no recorded iterations is returned.
	 *
	 * @return the run statistics; never {@code null}
	 */
	public RunStatistics getStats() {
		return stats;
	}

	/**
	 * Input MiniZinc model files.
	 * <p>
	 * Multiple files can be provided. Their contents are concatenated and
	 * parsed as a single MiniZinc model.
	 */
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN file paths", required = true)
	private List<Path> modelPaths;

	/**
	 * Input MiniZinc data files.
	 * <p>
	 * Their contents are appended to the model.
	 */
	@Option(names = { "-d", "--data" }, arity = "1..*", description = "DZN file paths", required = true)
	private List<Path> dataPaths;

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
	 * @param args a {@link String} array from the command line as arguments
	 */
	public static void main(String[] args) {
		int exitCode = new CommandLine(new FeasibilityRecovery()).execute(args);
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
		// Fixing parameters
		String lastModel = model.lines()
			.filter(r -> !r.contains("include \"experimental/assume\";")
				&& !r.contains("constraint assume(assumed)"))
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
	 * Outcome of a MiniZinc execution, determined from its output.
	 */
	private enum Outcome {

		/**
		 * A solution has been found.
		 */
		SOLUTION,

		/**
		 * The model is unsatisfiable.
		 */
		UNSATISFIABLE,

		/**
		 * The solver did not decide (e.g., time limit reached).
		 */
		UNKNOWN
	}

	/**
	 * A MiniZinc core is printed as {@code %%%mzn-core: [assumed[6], ...]}.
	 */
	private static final String CORE_PREFIX = "%%%mzn-core:";

	/**
	 * The value of the objective function printed by the lifted model.
	 */
	private static final Pattern OBJECTIVE = Pattern
		.compile(Pattern.quote(Assumer.OBJECTIVE_OUTPUT) + "(-?[0-9.eE+-]+)");

	/**
	 * The search time reported in the statistics of a MiniZinc execution.
	 */
	private static final Pattern SOLVE_TIME = Pattern
		.compile("^%%%mzn-stat: solveTime=([0-9.eE+-]+)$");

	/**
	 * An element of the core.
	 */
	private static final Pattern ASSUMPTION = Pattern.compile("assumed\\[(\\d+)\\]");

	/**
	 * Reads the value of the objective function of the last solution in the
	 * output of a MiniZinc execution.
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)} (the last
	 *                          line first)
	 *
	 * @return the value of the objective function, if any
	 */
	private Optional<Number> getObjective(List<String> commandOutput) {
		// The output of the model may not end with a new line, so the value
		// can be at the end of another line
		return commandOutput.stream()
			.map(OBJECTIVE::matcher)
			.filter(Matcher::find)
			.findFirst()
			.map(m -> m.group(1))
			.map(v -> v.contains(".") || v.contains("e")
				? (Number) Double.valueOf(v)
				: (Number) Long.valueOf(v));
	}

	/**
	 * Reads the time spent by the solver in the search from the statistics of a
	 * MiniZinc execution.
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)}
	 *
	 * @return the search time in seconds ({@code 0} if not reported)
	 */
	private double getSolveTime(List<String> commandOutput) {
		return commandOutput.stream()
			.map(SOLVE_TIME::matcher)
			.filter(Matcher::matches)
			.mapToDouble(m -> Double.parseDouble(m.group(1)))
			.sum();
	}

	/**
	 * Determines the outcome of a MiniZinc execution.
	 * <p>
	 * The output can also contain other lines (e.g., the traces of the model),
	 * so the status lines are searched in the whole output.
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)}
	 *
	 * @return the outcome of the execution
	 */
	private Outcome getOutcome(List<String> commandOutput) {
		if (commandOutput.contains("----------")) {
			return Outcome.SOLUTION;
		} else if (commandOutput.contains("=====UNSATISFIABLE=====")) {
			return Outcome.UNSATISFIABLE;
		}
		return Outcome.UNKNOWN;
	}

	/**
	 * Determines the MiniZinc status of an execution: a solution is optimal
	 * only if the search was completed ({@code ==========}).
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)}
	 *
	 * @return {@code OPTIMAL}, {@code SATISFIABLE}, {@code UNSATISFIABLE} or
	 *             {@code UNKNOWN}
	 */
	private String getStatus(List<String> commandOutput) {
		return switch (getOutcome(commandOutput)) {
		case SOLUTION -> commandOutput.contains("==========") ? "OPTIMAL" : "SATISFIABLE";
		case UNSATISFIABLE -> "UNSATISFIABLE";
		case UNKNOWN -> "UNKNOWN";
		};
	}

	/**
	 * Interprets the output produced by the main solver and determines whether
	 * a solution, an unsatisfiable core, or no useful core was obtained.
	 *
	 * @param commandOutput output lines returned by
	 *                          {@link ModelRunner#run(Path, String)}
	 *
	 * @return an {@link Optional} containing the list of variable indices
	 *             extracted from the reported core, an empty list if no core
	 *             could be obtained (the solver did not decide), or
	 *             {@link Optional#empty()} if a solution was found or the core
	 *             is empty (the whole problem class is unsatisfiable)
	 */
	private Optional<List<Integer>> analyzeOutput(List<String> commandOutput) {
		Outcome outcome = getOutcome(commandOutput);
		if (outcome == Outcome.SOLUTION) {
			logger.info("A solution has been found!");
			return Optional.empty();
		}

		Optional<String> coreLine = commandOutput.stream()
			.filter(l -> l.startsWith(CORE_PREFIX))
			.findFirst();
		if (outcome == Outcome.UNKNOWN || coreLine.isEmpty()) {
			// Neither a solution nor a core
			return Optional.of(List.of());
		}

		List<Integer> core = ASSUMPTION.matcher(coreLine.get()).results()
			.map(m -> Integer.valueOf(m.group(1)))
			.toList();

		return core.isEmpty() ? Optional.empty() : Optional.of(core);
	}

	/**
	 * Executes the feasibility recovery pipeline.
	 * <p>
	 * The statistics are always completed, even if the execution fails: the
	 * current iteration is recorded and the final state is set to
	 * {@link RunStatistics.FinalState#ERROR}.
	 *
	 * @return {@code 0} if execution completes successfully
	 *
	 * @throws Exception if parsing, transformation or execution fails
	 */
	@Override
	public Integer call() throws Exception {
		logger.info("Application starts");

		stats = new RunStatistics();
		stats.start();

		try {
			return recover();
		} catch (Exception e) {
			if (stats.getFinalState() == null) {
				stats.endIteration(List.of(), "error");
				stats.finish(RunStatistics.FinalState.ERROR);
				System.out.println(stats);
			}
			throw e;
		}
	}

	/**
	 * Ends the procedure with the given final state, printing the statistics.
	 *
	 * @param state      the final state
	 * @param solverUsed the solver used in the last iteration
	 *
	 * @return {@code 0}
	 */
	private int finish(RunStatistics.FinalState state, String solverUsed) {
		stats.endIteration(List.of(), solverUsed);
		stats.finish(state);
		System.out.println(stats);
		logger.info("Exiting with " + state);
		return 0;
	}

	/**
	 * Lifts the model and iteratively removes the assumptions in the cores
	 * until a solution is found or no progress can be made.
	 *
	 * @return {@code 0} if execution completes successfully
	 *
	 * @throws Exception if parsing, transformation or execution fails
	 */
	private int recover() throws Exception {
		outputPath = outputPath.toAbsolutePath();

		// Parse cli arguments
		List<LiftRequest> cliParameters = parameters.stream()
			.map(LiftRequest::parse).toList();

		// Reading and appending files: the data is appended to the model so
		// that the values of the lifted parameters can be computed
		StringBuilder sb = new StringBuilder();
		for (Path fp : Stream.concat(modelPaths.stream(), dataPaths.stream()).toList()) {
			logger.info("Reading file " + fp.toString() + "...");
			sb.append(Files.readString(fp) + "\n");
		}

		String originalModel = sb.toString();

		stats.countChanges(originalModel);

		String modelsNamePrefix = Main.getModelsNamePrefix(modelPaths);
		Path modelBasePath = outputPath.resolve(modelsNamePrefix);

		// Creates output folder if it does not exists
		Files.createDirectories(outputPath);
		Files.writeString(Path.of(modelBasePath.toString() + ".mzn"), originalModel);

		ModelLifter.LiftedModel lifted = new ModelLifter(
			originalModel,
			cliParameters,
			setsDisallowed,
			maximizeUserParamsOnly).call();

		stats.parameterCount(lifted.liftedParameters());

		List<Integer> assumptions = new ArrayList<>();
		for (int i = 1;; i++) {
			stats.startIteration(i);
			String solverUsed = "solutions.huub";

			// Customize the model
			logger.info("Adding assumptions...");
			String liftedModel = lifted.withAssumptions(assumptions);

			// Write .mzn to file
			logger.info("Writing lifted .mzn with assumptions");
			String ithModelNamePrefix = "" + i + "-" + modelsNamePrefix;
			Path ithBaseModelPath = outputPath.resolve(ithModelNamePrefix);
			Path ithMznModelPath = Path.of(ithBaseModelPath.toString() + ".mzn");
			Files.writeString(ithMznModelPath, liftedModel);

			// Run the .fzn
			logger.info("Running the lifted model...");
			List<String> commandOutput = ModelRunner.run(ithBaseModelPath, solverUsed);
			stats.addSolveTime(getSolveTime(commandOutput));

			Optional<List<Integer>> coreAssumptionIndeces = analyzeOutput(commandOutput);
			if (coreAssumptionIndeces.isEmpty()) {
				getObjective(commandOutput).ifPresent(stats::setObjective);
				stats.setStatus(getStatus(commandOutput));
				return finish(
					getOutcome(commandOutput) == Outcome.SOLUTION
						? RunStatistics.FinalState.SOLUTION_FOUND
						: RunStatistics.FinalState.UNSATISFIABLE,
					solverUsed);
			} else if (coreAssumptionIndeces.get().isEmpty()) {
				// The recovery solver either gives a solution for the current
				// assumptions or proves them unsatisfiable: it never gives a
				// new core, so the procedure always ends here
				logger.info("""
					A solution or an unsat core cannot be found, \
					trying by fixing parameters with another solver:
					""" + recoverySolver);
				solverUsed = recoverySolver;

				List<String> recoveryOuput = runWithFixedParameters(
					recoverySolver,
					Files.readString(ithMznModelPath),
					ithModelNamePrefix);
				stats.addSolveTime(getSolveTime(recoveryOuput));

				getObjective(recoveryOuput).ifPresent(stats::setObjective);
				stats.setStatus(getStatus(recoveryOuput));
				return switch (getOutcome(recoveryOuput)) {
				case SOLUTION -> finish(RunStatistics.FinalState.SOLUTION_FOUND_VIA_RECOVERY, solverUsed);
				case UNSATISFIABLE -> finish(RunStatistics.FinalState.ABORTED, solverUsed);
				case UNKNOWN -> {
					logger.info("Unable to find a recovery given the time limit");
					yield finish(RunStatistics.FinalState.UNKNOWN, solverUsed);
				}
				};
			}

			List<Integer> newNogoodAssumptions = coreAssumptionIndeces.get();
			stats.endIteration(newNogoodAssumptions, solverUsed);
			assumptions.addAll(newNogoodAssumptions);
		}
	}
}
