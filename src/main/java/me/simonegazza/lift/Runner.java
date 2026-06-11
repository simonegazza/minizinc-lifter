package me.simonegazza.lift;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import me.simonegazza.lift.utils.ApplicationLogger;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * Run data files in a folder one at the time.
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

		List<Integer> correct = new ArrayList<>(filesPath.size());
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

			correct.add(new CommandLine(new Main()).execute(commandArgument.toArray(new String[0])));
		}

		if (correct.stream().allMatch(c -> c == 0)) {
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
