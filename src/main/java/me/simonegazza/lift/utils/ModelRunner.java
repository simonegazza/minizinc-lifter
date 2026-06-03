package me.simonegazza.lift.utils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ModelRunner {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(ModelRunner.class.getSimpleName());

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
	 * Helper method that passes the control to MiniZinc to compile the model.
	 *
	 * @param modelBasePath the path to the model to compile without the ending
	 *                          extensions
	 * @param solverName    solver name for the compilation process
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
	 * Helper method that passes the control to MiniZinc to run the model.
	 *
	 * @param modelBasePath the path to the model to run without the ending
	 *                          extensions
	 * @param solverName    the solver name to pass to MiniZinc (like "chuffed"
	 *                          or "gecode")
	 *
	 * @return the last 10 rows of the output
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
