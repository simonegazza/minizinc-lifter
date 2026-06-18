package me.simonegazza.lift;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import me.simonegazza.lift.utils.ApplicationLogger;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * Batch driver that runs the lifting procedure for every {@code .dzn} data file
 * found in a given folder.
 * <p>
 * For each data file, a fresh {@link Main} instance is created and executed.
 * The {@link RunStatistics} produced by each run are collected into a
 * {@link BatchRunStatistics} object. When all files have been processed, the
 * aggregate statistics are serialised to JSON and printed to standard output.
 * <p>
 * A run that raises an uncaught exception is recorded as failed; its partial
 * statistics (collected up to the point of failure) are still included in the
 * aggregate output.
 */
@CommandLine.Command(name = "mzn-runner", mixinStandardHelpOptions = true, version = "0.1", description = "Run a model with data files contained in a folder")
public class Runner implements Callable<Integer> {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(Runner.class.getSimpleName());

	/**
	 * Input MiniZinc model file path.
	 */
	@Option(names = { "-m", "--model" }, arity = "1", description = "MZN model file path", required = true)
	private Path modelPath;

	/**
	 * Folder where the data is.
	 */
	@Option(names = { "-d", "--data-folder" }, description = "Data folder path", required = true)
	private Path dataPath;

	/**
	 * Parameters that should be lifted as Strings.
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
	 * Runner entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command link as arguments for
	 *                 this program
	 */
	public static void main(String[] args) {
		int exitCode = new CommandLine(new Runner()).execute(args);
		System.exit(exitCode);
	}

	/**
	 * Executes the lifting procedure for every {@code .dzn} file in the data
	 * folder, collects the per-run statistics, and prints the aggregated JSON
	 * to standard output.
	 *
	 * @return {@code 0} if all runs completed without throwing an exception;
	 *             {@code 1} otherwise
	 *
	 * @throws Exception if the data folder cannot be listed or is empty
	 */
	@Override
	public Integer call() throws Exception {
		logger.info("Runner starts");

		List<Path> filesPath = Files.list(dataPath)
			.filter(Files::isRegularFile)
			.filter(f -> f.toString().endsWith(".dzn"))
			.toList();

		logger.info("""
			Runner will ask the satisfiability \
			recovery process with the following files: """ + filesPath);

		if (filesPath.size() == 0) {
			throw new IllegalArgumentException("Data folder is empty!");
		}

		BatchRunStatistics batchStats = new BatchRunStatistics();
		List<Optional<Throwable>> correct = new ArrayList<>(filesPath.size());
		for (Path dznPath : filesPath) {
			logger.info("Running data file " + dznPath.toString());

			List<String> ps = parameters.stream()
				.flatMap(n -> List.of("-p", n).stream())
				.toList();

			String filename = dznPath.getFileName().toString().split("\\.")[0];
			Path mainOutPath = outputPath.toAbsolutePath().resolve(filename);

			List<String> commandArgument = new ArrayList<>(ps);
			commandArgument.add("-m");
			commandArgument.add(modelPath.toAbsolutePath().toString());
			commandArgument.add("-m");
			commandArgument.add(dznPath.toAbsolutePath().toString());
			commandArgument.add("-o");
			commandArgument.add(mainOutPath.toString());

			Main mainInstance = new Main();
			try {
				new CommandLine(mainInstance).execute(commandArgument.toArray(new String[0]));
				correct.add(Optional.empty());
			} catch (Throwable t) {
				correct.add(Optional.of(t));
			}
			batchStats.add(dznPath, mainInstance.getStats());
		}

		System.out.println("*********************************************");
		System.out.println(batchStats);

		if (correct.stream().allMatch(Optional::isEmpty)) {
			return 0;
		} else {
			logger.info("Some run failed:");
			IntStream.range(0, correct.size()).boxed()
				.map(i -> "\nRun " + i + " with data file " + filesPath.get(i))
				.forEach(logger::error);

			return 1;
		}
	}
}
