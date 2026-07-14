package me.simonegazza.learning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincCompositeType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * CLI entry point for the Inter-Instances Nogood learning tool.
 * <p>
 * This tool reads one MiniZinc model, and multiple data files. It analyzes its
 * parameters, generates a transformed version of the model where selected
 * parameters are <i>lifted</i> into decision variables, and run the benchmark
 * using a modified version of the Huub solver.
 * <p>
 * The tool relies heavily on the ANTLR visitor pattern and on MiniZinc to
 * analyze and rewrite the MiniZinc AST. Visitors are responsible for extracting
 * semantic information from the parsed models and applying structural
 * transformations.
 * <p>
 * The CLI interface is implemented using Picocli.
 */
@CommandLine.Command(name = "mzn-inter-instance-nogood-learning-saver", mixinStandardHelpOptions = true, version = "0.1", description = "Runs Inter-instance nogood learning benchmark")
public class Saver implements Callable<Integer> {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(Saver.class.getSimpleName());

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
	 * Application entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command line as arguments
	 */
	public static void main(String[] args) {
		new CommandLine(new Saver()).execute(args);
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

		modelPath = modelPath.toAbsolutePath();

		String modelNamePrefix = modelPath.toFile().getName();
		int idx = modelNamePrefix.lastIndexOf(".");
		modelNamePrefix = modelNamePrefix.substring(0, idx);

		// Parse cli arguments
		List<LiftRequest> cliParameters = parameters.stream()
			.map(LiftRequest::parse).toList();

		List<Path> repetitionPaths = Files.list(dataPath.toAbsolutePath())
			.filter(Files::isDirectory)
			.map(Path::toAbsolutePath)
			.sorted()
			.toList();

		for (Path repetition : repetitionPaths) {
			List<Path> dataFiles = Files.list(repetition)
				.filter(Files::isRegularFile)
				.filter(f -> f.toString().endsWith(".dzn"))
				.map(Path::toAbsolutePath)
				.sorted()
				.toList();

			if (dataFiles.size() == 0) {
				throw new IllegalArgumentException("Data folder does not contain any .dzn files");
			} else if (dataFiles.size() != Generator.percentages.size() + 1) {
				throw new IllegalStateException("Not enough data files for " + repetition);
			}

			// Reading and appending files
			StringBuilder primaryModelBuilder = new StringBuilder();
			logger.info("Reading and parsing model file: " + modelPath.toString());
			String originalModel = Files.readString(modelPath);

			primaryModelBuilder.append(originalModel + "\n");
			primaryModelBuilder.append(Files.readString(dataFiles.get(0)) + "\n");

			// Parse the original model
			CharStream input = CharStreams.fromString(primaryModelBuilder.toString());
			Lexer lexer = new MiniZincLexer(input);
			TokenStream tokens = new CommonTokenStream(lexer);
			MiniZincParser parser = new MiniZincParser(tokens);

			// Get the dependency graph of the parameters and verify the
			// existence of the parameters to be lifted
			logger.info("Extracting parameters...");
			ParameterExtractor pe = new ParameterExtractor(parser.model());
			ParameterGraph graph = pe.call();

			List<OriginalParameter> originalLifted = cliParameters.stream()
				.map(r -> {
					Optional<OriginalParameter> toLift = graph.getByName(r.getName());
					if (toLift.isEmpty()) {
						throw new IllegalArgumentException("Requested lift for "
							+ r.getName()
							+ " but it does not exists");
					}

					return toLift;
				})
				.map(Optional::get)
				.toList();

			// Reset the token for the next pass
			tokens.seek(0);

			// Resolve dependencies of the parameters and create base model
			logger.info("Lifting parameter representation...");
			Lifter lifter = new Lifter(
				tokens,
				parser.model(),
				cliParameters,
				graph,
				false);
			String baseModel = lifter.call();

			List<LiftedParameter> liftedParameters = lifter.getLifted();

			// Remove all the parameter declaration
			logger.info("Removing old definitions...");
			Remover remover = new Remover(originalLifted, baseModel);
			baseModel = remover.call();

			// Add the boilerplate and other things for this model
			logger.info("Adding the boilerplate...");
			Learner learner = new Learner(dataFiles.size(), liftedParameters);
			baseModel += learner.call();

			// Add the new aggregated parameter definitions
			logger.info("Aggregating parameters...");
			String allAggregatedParameters = liftedParameters.stream()
				.map(p -> {

					// get the value string from each data file
					List<String> parameterStrings = dataFiles.stream()
						.sorted()
						.map(f -> {
							try {
								return Files.lines(f);
							} catch (IOException e) {
								throw new IllegalStateException("Cannot read from file " + f);
							}
						})
						.map(ls -> ls
							// take only the lines in each data file that
							// starts with the current parameter name
							.filter(l -> l.startsWith(p.getOriginalName()))
							.findAny()
							// there is always one definition per data file
							// so this should not return empty
							.get())
						// clean each definition: remove everything before
						// the (first) equal sign and also the ';' at the
						// end of the parameter, if any
						.map(d -> {
							List<String> split = Arrays.asList(d.split("="));
							String values = split.subList(1, split.size()).stream().collect(Collectors.joining());
							if (values.charAt(values.length() - 1) == ';') {
								return values.substring(0, values.length() - 1);
							} else {
								return values;
							}
						})
						.toList();

					// try to get if these values are arrays. If they are
					// arrays, just concatenate them with "++", otherwise
					// join with ", " and use "[" and "]" to make it an
					// array
					char firstChar = parameterStrings.get(0).strip().charAt(0);
					boolean wasArray;
					Collector<CharSequence, ?, String> joiner;
					if (firstChar == '[' || firstChar == 'a') {
						// standard array definition or arrayXd(...) case
						joiner = Collectors.joining(" ++ ");
						wasArray = true;
					} else {
						// assume it is not an array
						joiner = Collectors.joining(", ", "[", "]");
						wasArray = false;
					}

					String aggregatedValues = parameterStrings.stream().collect(joiner);

					StringBuilder builder = new StringBuilder("array");
					if (wasArray) {
						MiniZincType type = p.getParameter().getType();
						type = ((MiniZincCompositeType) type).getSubtype();

						builder.append("[instances, ");
						String remainingDimensions = p.getDimensions().stream()
							.map(d -> "1.." + d)
							.collect(Collectors.joining(", ", "", ","));
						builder.append(remainingDimensions)
							.append("] of ")
							.append(type)
							.append(": ")
							.append(p.getOriginalName())
							.append(" = array")
							.append(p.getDimensions().size() + 1)
							.append("d(instances, ")
							.append(remainingDimensions);
					} else {
						builder.append("[instances] of int: ")
							.append(p.getOriginalName())
							.append(" = ");
					}
					builder.append(aggregatedValues).append("\n);");

					return builder.toString();
				}).collect(Collectors.joining("\n\n"));

			repetition = repetition.toAbsolutePath().resolve("chain.mzn");
			Files.writeString(repetition, baseModel + "\n\n" + allAggregatedParameters);
		}

		return 0;
	}
}
