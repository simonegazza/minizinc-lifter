package me.simonegazza.lift.recovery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.expressions.MiniZincArray;
import me.simonegazza.lift.expressions.MiniZincSet;
import me.simonegazza.lift.parameters.OriginalParameter;
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
 * Randomize a single data file with a specified amount of changes.
 */
@CommandLine.Command(name = "dzn-randomizer", mixinStandardHelpOptions = true, version = "0.1", description = "Randomize a single data file")
public class Randomizer implements Callable<Integer> {
	/**
	 * Input MiniZinc files to parse.
	 * <p>
	 * Multiple files can be provided. Their contents are concatenated and
	 * parsed as a single MiniZinc model.
	 */
	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and DZN file paths", required = true)
	private List<Path> filePaths;

	/**
	 * Parameters that should be randomized.
	 * <p>
	 * Each parameter specification is parsed into a {@link RandomizeRequest}.
	 */
	@Option(names = { "-p", "--parameters" }, arity = "1..*", description = "Parameter to randomize", required = true)
	private Set<String> parameterStrings;

	/**
	 * Folder path where the will be saved.
	 */
	@Option(names = { "-o", "--output" }, description = "Output folder path", required = true)
	private Path outputPath;

	/**
	 * Randomizer entry point.
	 * <p>
	 * Delegates execution to Picocli which handles argument parsing and command
	 * invocation.
	 *
	 * @param args a {@link String} array from the command link as arguments for
	 *                 this program
	 */
	public static void main(String[] args) {
		int exitCode = new CommandLine(new Randomizer()).execute(args);
		System.exit(exitCode);
	}

	/**
	 * A randomization request for a specific parameter.
	 */
	private static class RandomizeRequest {
		/**
		 * The name of the requested parameter.
		 */
		private final String name;

		/**
		 * The number of changes to make to this parameter.
		 */
		private final int amount;

		RandomizeRequest(String request) {
			String[] userData = request.split(":");
			List<String> data = new ArrayList<>();
			Collections.addAll(data, userData);
			if (data.size() == 1) {
				data.add("1");
			}

			name = data.get(0);
			amount = Integer.parseInt(data.get(1));
		}

		/**
		 * Get the name of this request.
		 *
		 * @return the name of this request
		 */
		public String getName() {
			return name;
		}

		/**
		 * Get the amount of changes to be done to this request.
		 *
		 * @return the changes to do to this request
		 */
		public int getAmount() {
			return amount;
		}

	}

	/**
	 * Randomize a given MiniZinc value object.
	 * <p>
	 * It does not support String as of now
	 *
	 * @param value     the value to be randomized or recursively called upon
	 * @param min       the minimum range for the randomization
	 * @param max       the maximum range for the randomization
	 * @param amount    the amount of randomization to do to the value
	 * @param generator a random number generator
	 *
	 * @return the object randomized
	 */
	private Object randomizeObject(
		Object value,
		Optional<Double> min,
		Optional<Double> max,
		int amount,
		Random generator) {
		return switch (value) {
		case Integer vi -> {
			int newValue;
			if (min.isEmpty() && max.isEmpty()) {
				int variation = vi * 10 / 100;
				newValue = generator.nextInt(vi - variation, vi + variation + 1);
			} else {
				newValue = generator.nextInt(min.get().intValue(), max.get().intValue());
			}
			yield newValue + "% CHANGED HERE from " + vi + " to " + newValue + "\n";
		}
		case Double vd -> {
			double newValue;
			if (min.isEmpty() && max.isEmpty()) {
				double variation = vd * 10 / 100;
				newValue = generator.nextDouble(vd - variation, vd + variation + 1);
			} else {
				newValue = generator.nextDouble(min.get(), max.get());
			}
			yield newValue + "% CHANGED HERE from " + vd + " to " + newValue + "\n";
		}
		case MiniZincSet vs -> {
			Object[] set = vs.toArray();

			Optional<Double> newMin = Arrays.stream(set)
				.map(Double.class::cast)
				.min(Double::compare);
			Optional<Double> newMax = Arrays.stream(set)
				.map(Double.class::cast)
				.max(Double::compare);

			if (amount > set.length) {
				throw new IllegalStateException("Too many changes asked");
			}

			// This for is fine since you cannot have set of anything except
			// primitive variables
			for (int i = 0; i < amount; i++) {
				int index = generator.nextInt(set.length);
				Object newValue = randomizeObject(set[index], newMin, newMax, amount, generator);
				set[index] = newValue;
			}
			yield new MiniZincSet(Arrays.asList(set));
		}
		case MiniZincArray va -> {
			List<Object> flattenList = va.flatten();

			Optional<Double> newMin = flattenList.stream()
				.map(Number.class::cast)
				.map(Number::doubleValue)
				.min(Double::compare);
			Optional<Double> newMax = flattenList.stream()
				.map(Number.class::cast)
				.map(Number::doubleValue)
				.max(Double::compare);

			for (int i = 0; i < amount; i++) {
				int index = generator.nextInt(flattenList.size());
				Object newValue = randomizeObject(flattenList.get(index), newMin, newMax, 1, generator);
				flattenList.set(index, newValue);
			}

			yield MiniZincArray.fromFlattened(va.getDimensions(), flattenList);
		}
		case String s -> s;
		case null, default -> throw new IllegalStateException("Unsupported type for randomization");
		};
	}

	/**
	 * Makes a randomized object to a String.
	 *
	 * @param value the object to be as String
	 *
	 * @return the object as String
	 */
	private String stringify(Object value) {
		return switch (value) {
		case Integer vi -> vi.toString();
		case Double vd -> vd.toString();
		case MiniZincSet vs -> Arrays.asList(vs).stream()
			.map(this::stringify)
			.collect(Collectors.joining(", ", "{", "}"));
		case MiniZincArray va -> {
			String dimensionList = va.getDimensions().stream()
				.map(r -> r.lowerBound() + ".." + r.upperBound())
				.collect(Collectors.joining(", "));

			yield va.flatten().stream()
				.map(this::stringify)
				.collect(Collectors.joining(
					", ",
					"array" + va.getDimensions().size() + "d(" + dimensionList + ", [",
					"])"));
		}
		case String s -> s;
		case null, default -> throw new IllegalStateException("Unsupported stringification");
		};
	}

	@Override
	public Integer call() throws Exception {
		// Check output path
		outputPath = outputPath.toAbsolutePath();
		if (!Files.exists(outputPath)) {
			Files.createDirectories(outputPath);
		} else if (Files.isRegularFile(outputPath)) {
			throw new IllegalArgumentException("The output path already exists and it is a file");
		}

		List<RandomizeRequest> parameters = parameterStrings.stream()
			.map(RandomizeRequest::new)
			.toList();

		// Reading and appending files
		StringBuilder sb = new StringBuilder();
		for (Path fp : filePaths) {
			sb.append(Files.readString(fp) + "\n");
		}

		String fullOriginalModel = sb.toString();
		String baseName = filePaths.stream()
			.filter(fp -> fp.toFile().getName().endsWith(".dzn"))
			.map(fp -> {
				String filename = fp.toFile().getName();
				int idx = filename.lastIndexOf(".");
				return filename.substring(0, idx);
			})
			.findFirst()
			.orElse("original");

		CharStream input = CharStreams.fromString(fullOriginalModel);
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);
		ParameterExtractor pe = new ParameterExtractor(parser.model());
		ParameterGraph graph = pe.call();

		Random randomGenerator = new Random(42);
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> env = new HashMap<>();
			List<String> redefinitions = new ArrayList<>();
			for (RandomizeRequest request : parameters) {
				Optional<OriginalParameter> randomizable = graph.getByName(request.getName());

				// Check existence
				if (randomizable.isEmpty()) {
					throw new IllegalArgumentException("Requested lift for "
						+ request.getName()
						+ " but it does not exists");
				}

				OriginalParameter toRandomize = randomizable.get();

				// Compute its value
				Object value = Lifter.computeValue(toRandomize, graph, env);

				// Randomize it
				Object newValue = randomizeObject(
					value,
					Optional.empty(),
					Optional.empty(),
					request.getAmount(),
					randomGenerator);
				String toPrint = toRandomize.toString() + " = " + stringify(newValue) + ";";

				redefinitions.add(toPrint);
			}

			String otherParameters = filePaths.stream()
				.filter(fp -> fp.toFile().getName().endsWith(".dzn"))
				.map(t -> {
					try {
						return Files.readString(t).replaceAll("%.*", "").replaceAll("\\s", "");
					} catch (IOException e) {
						throw new IllegalStateException("Shouldn't be possible");
					}
				}).flatMap(f -> Arrays.stream(f.split(";")))
				.filter(e -> !parameters.stream()
					.map(RandomizeRequest::getName)
					.anyMatch(n -> e.startsWith(n)))
				.collect(Collectors.joining(";\n", "", ";"));

			String finalOutput = redefinitions.stream()
				.collect(Collectors.joining("\n", otherParameters + "\n", ""));

			Files.writeString(
				Path.of(outputPath.toString(), "" + i + "-" + baseName + ".dzn"),
				finalOutput);
		}

		return 0;
	}

}
