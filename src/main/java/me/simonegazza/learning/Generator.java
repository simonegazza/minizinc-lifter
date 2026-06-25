package me.simonegazza.learning;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import me.simonegazza.learning.generators.Colouring;
import me.simonegazza.learning.generators.Knapsack;
import me.simonegazza.learning.generators.Mosp;
import me.simonegazza.learning.generators.Problem;
import me.simonegazza.learning.generators.Radiation;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * Generates benchmark instances for all supported optimization problems.
 * <p>
 * For each registered problem, the generator creates a dedicated output
 * directory whose name matches the problem identifier (e.g. {@code knapsack},
 * {@code colouring}, {@code mosp}, {@code radiation}). Inside each directory, a
 * user-defined number of instances is generated and stored as individual files.
 * <p>
 * The number of instances generated for each problem is controlled by the
 * {@code --amount} command-line option. Problem instances are produced through
 * the {@link Problem#generateProblems(List)} method implemented by each
 * concrete problem class.
 */
@CommandLine.Command(name = "mzn-IINGL-generator", mixinStandardHelpOptions = true, version = "0.1", description = "Generate data for Inter-instance nogood learning")
public class Generator implements Callable<Integer> {

	/**
	 * Percentages to generate the problem by.
	 */
	protected static final List<Integer> percentages = List.of(1, 2, 5, 10, 20, 50);

	/**
	 * List of problems to generate.
	 */
	private static final List<Problem<?>> problems = List.of(
		new Knapsack(),
		new Colouring(),
		new Mosp(),
		new Radiation());

	/**
	 * Folder path where data will be saved.
	 */
	@Option(names = { "-o", "--output" }, description = "Output folder path", required = true)
	private Path outputPath;

	/**
	 * Number of instances to generate for each problem.
	 */
	@CommandLine.Option(names = { "-a", "--amount" }, description = "Number of instances per problem", required = true)
	private int amount;

	/**
	 * Application entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command line as arguments
	 */
	public static void main(String[] args) {
		new CommandLine(new Generator()).execute(args);
	}

	@Override
	public Integer call() throws Exception {
		outputPath = outputPath.toAbsolutePath();
		Files.createDirectories(outputPath);

		// Add the original to the percentages
		List<Integer> percentages = new ArrayList<>();
		percentages.add(0);
		percentages.addAll(Generator.percentages);

		for (Problem<?> problem : problems) {
			Path problemDir = outputPath.resolve(problem.getName());
			Files.createDirectories(problemDir);

			for (int i = 0; i < amount; i++) {
				Path generationDir = problemDir.resolve("" + i);
				Files.createDirectories(generationDir);

				List<String> instances = problem.generateProblems(Generator.percentages);

				for (int n = 0; n < instances.size(); n++) {
					Path instanceFile = generationDir.resolve(
						"" + n + "-" + percentages.get(n) + ".dzn");
					Files.writeString(instanceFile, instances.get(n));
				}
			}
		}

		return 0;
	}

}
