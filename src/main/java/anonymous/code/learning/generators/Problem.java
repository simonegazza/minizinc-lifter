package anonymous.code.learning.generators;

import java.util.List;

/**
 * Generates .dzn problems for a given problem.
 *
 * @param <T> generator element type
 */
public abstract class Problem<T> {
	/**
	 * Data generator.
	 */
	protected final ArrayGenerator<T> generator;

	/**
	 * The length of the array.
	 */
	protected final int length;

	/**
	 * Problem name.
	 */
	private final String name;

	protected Problem(String name, ArrayGenerator<T> generator, int length) {
		this.generator = generator;
		this.length = length;
		this.name = name;
	}

	/**
	 * Get the .dzn string version of the current data.
	 *
	 * @param data the data to be made as string
	 *
	 * @return the stringiy data
	 */
	protected abstract String stringify(List<T> data);

	/**
	 * Get the preamble for the corresponding .dzn file.
	 *
	 * @return the preamble.
	 */
	protected abstract String getPreable();

	/**
	 * Get the name for the corresponding problem.
	 *
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Generate the problem .dzn file content.
	 *
	 * @param percentages generates the problem with the given rate of change
	 *
	 * @return the generated .dzn file content
	 */
	public List<String> generateProblems(List<Integer> percentages) {
		return generator.generate(length, percentages).stream()
			.map(this::stringify)
			.map(d -> getPreable() + "\n" + d)
			.toList();
	}
}
