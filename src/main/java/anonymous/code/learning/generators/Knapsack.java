package anonymous.code.learning.generators;

import anonymous.code.lift.utils.exception.UnimplementedException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Generate the Radiation Therapy .dzn files.
 */
public class Knapsack extends Problem<Integer> {
	public Knapsack() {
		super("knapsack", ArrayGenerator.integers(1, 10), 100);
	}

	@Override
	protected String getPreable() {
		return "n = 100;\nk = 125;\n";
	}

	@Override
	public List<String> generateProblems(List<Integer> percentages) {
		List<List<Integer>> sizes = generator.generate(length, percentages);
		List<List<Integer>> values = generator.generate(length, percentages);

		// + 1 because there is always the 0% of change instance
		return IntStream.range(0, percentages.size() + 1).boxed()
			.map(i -> new StringBuilder(getPreable())
				.append("size = " + sizes.get(i).toString() + ";\n")
				.append("value = " + values.get(i).toString() + ";")
				.toString())
			.toList();
	}

	@Override
	protected String stringify(List<Integer> data) {
		throw new UnimplementedException(
			"No need to implement this method for knapsack problem generation");
	}

}
