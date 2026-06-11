package me.simonegazza.lift.utils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class responsible for compiling and executing MiniZinc models.
 * <p>
 * This class acts as a thin wrapper around the MiniZinc command-line tools,
 * handling process creation, output collection, error checking, and
 * solver-specific execution details.
 * <p>
 * Both compilation and execution are performed through external processes. Any
 * non-zero exit status is treated as a fatal error and results in an exception
 * being thrown.
 */
public class ModelRunner {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(ModelRunner.class.getSimpleName());

	/**
	 * Collects the output produced by a MiniZinc process and verifies that the
	 * process completed successfully.
	 * <p>
	 * The process output is consumed from standard output, echoed to the
	 * console, and returned as a list of lines. After the process terminates,
	 * the exit status is checked.
	 * <p>
	 * If the process exits with a non-zero status, the captured output is
	 * logged and an exception is thrown.
	 *
	 * @param p the process to monitor
	 *
	 * @return all lines written to the process standard output
	 *
	 * @throws IllegalStateException if the process exits with a non-zero status
	 *                                   or is interrupted while waiting for
	 *                                   completion
	 */
	private static List<String> getOutputAndVerify(Process p) {
		List<String> commandOutput = p.inputReader().lines()
			.peek(System.out::println)
			.toList();

		try {
			int exitCode = p.waitFor();
			if (exitCode != 0) {
				logger.error(commandOutput.stream().collect(Collectors.joining("\n")));
				throw new IllegalStateException("MiniZinc terminated with error code: " + exitCode);
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException("MiniZinc process was interrupted on your system");
		}

		return commandOutput;
	}

	/**
	 * Compiles a MiniZinc model into the solver-specific FlatZinc
	 * representation.
	 * <p>
	 * The MiniZinc {@code --compile} mode is used to generate the intermediate
	 * FlatZinc files without executing the solver. The generated files are
	 * written alongside the source model and may subsequently be used for
	 * analysis or direct solver invocation.
	 *
	 * @param modelBasePath path to the model without file extension (e.g.
	 *                          {@code model} for {@code model.mzn})
	 * @param solverName    MiniZinc solver identifier used during compilation
	 *
	 * @throws IllegalStateException if the MiniZinc process cannot be started
	 *                                   or if compilation fails
	 */
	public static void compile(Path modelBasePath, String solverName) {
		Process p;
		try {
			p = new ProcessBuilder()
				.command(
					"minizinc",
					"--solver", solverName,
					"-w", // suppress warnings
					//// 1 minute timeout expressed in milliseconds
					// "--time-limit", String.valueOf(1000 * 60 * 1),
					// "--verbose",
					"--compile",
					(modelBasePath.toString() + ".mzn"))
				.redirectErrorStream(true)
				.directory(modelBasePath.getParent().toFile())
				.start();
		} catch (IOException e) {
			throw new IllegalStateException("Unable to inherit IO for compiling the model with " + solverName);
		}

		getOutputAndVerify(p);
	}

	/**
	 * Executes a MiniZinc model using the specified solver.
	 * <p>
	 * For most solvers, the model is executed directly through the MiniZinc
	 * command-line interface. Chuffed is handled specially: the precompiled
	 * FlatZinc model is executed through {@code fzn-chuffed} and the results
	 * are post-processed using the corresponding MiniZinc output specification
	 * file ({@code .ozn}).
	 * <p>
	 * A solver time limit of one minute is applied to all executions.
	 *
	 * @param modelBasePath path to the model without file extension (e.g.
	 *                          {@code model} for {@code model.mzn})
	 * @param solverName    MiniZinc solver identifier such as {@code chuffed}
	 *                          or {@code gecode}
	 *
	 * @return the solver output lines in reverse order, allowing callers to
	 *             inspect the final solver messages first
	 *
	 * @throws IllegalStateException    if MiniZinc cannot be located, a
	 *                                      required process cannot be started,
	 *                                      or execution fails
	 * @throws IllegalArgumentException if the Chuffed execution pipeline cannot
	 *                                      be created
	 */
	public static List<String> run(Path modelBasePath, String solverName) {

		Process p;
		if ("chuffed".equals(solverName)) {
			Path fznChuffedPath;
			try {
				fznChuffedPath = Paths.get(
					new ProcessBuilder("minizinc")
						.start()
						.info()
						.command()
						.orElseThrow())
					.toAbsolutePath().getParent().resolve("bin/fzn-chuffed");
			} catch (InvalidPathException e) {
				throw new IllegalStateException("MiniZinc was not found on your machine");
			} catch (IOException e) {
				throw new IllegalStateException("Unable to start the process for finding MiniZinc on your machine");
			}

			try {
				p = ProcessBuilder.startPipeline(List.of(
					new ProcessBuilder()
						.command(
							fznChuffedPath.toString(),
							// "-a",
							// 1 minute timeout expressed in milliseconds
							"--time-out", String.valueOf(1000 * 60 * 1),
							(modelBasePath.toString() + ".fzn"))
						.directory(modelBasePath.getParent().toFile())
						.inheritIO()
						.redirectOutput(ProcessBuilder.Redirect.PIPE),
					new ProcessBuilder()
						.command(
							"minizinc",
							// "--output-objective",
							"--ozn-file",
							(modelBasePath.toString() + ".ozn"))
						.redirectErrorStream(true)
						.directory(modelBasePath.getParent().toFile())
						.redirectError(ProcessBuilder.Redirect.INHERIT)))
					.getLast();
			} catch (IOException e) {
				throw new IllegalArgumentException("Unable to inherit IO for running the model with chuffed");
			}

		} else {
			try {
				p = new ProcessBuilder()
					.command(
						"minizinc",
						"--solver", solverName,
						// "-a",
						"-w", // suppress warnings
						// 1 minute timeout expressed in milliseconds
						"--time-limit", String.valueOf(1000 * 60 * 1),
						// "--verbose",
						(modelBasePath.toString() + ".mzn"))
					.redirectErrorStream(true)
					.directory(modelBasePath.getParent().toFile())
					.start();
			} catch (IOException e) {
				throw new IllegalStateException("Unable to inherit IO for compiling the model with " + solverName);
			}
		}

		return getOutputAndVerify(p).reversed();
	}
}
