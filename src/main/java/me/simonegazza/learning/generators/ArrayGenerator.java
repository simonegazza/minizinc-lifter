package me.simonegazza.learning.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility class for generating and perturbing one-dimensional and
 * two-dimensional arrays represented as {@link List}s.
 * <p>
 * The class is generic and relies on a {@link Supplier} to generate random
 * values of type {@code T}. This allows it to be used with any type, such as
 * {@link Integer}, {@link Boolean}, {@link String}, or custom classes.
 * <p>
 * In addition to generating random arrays, the class can create:
 * <ul>
 * <li>Independent perturbations of an existing instance.</li>
 * <li>Incremental perturbation chains, where each perturbation level extends
 * the previous one by modifying previously untouched positions.</li>
 * </ul>
 * <h2>Example</h2>
 *
 * <pre>{@code
 * Generator<Integer> ints = Generator.integers();
 *
 * List<Integer> original = ints.generateArray(1000);
 *
 * List<Generator.PerturbationStep<Integer>> chain = ints.generatePerturbationChain(original, 1, 2, 5, 10, 20);
 *
 * // chain.get(0) -> 1%
 * // chain.get(1) -> 2%
 * // chain.get(2) -> 5%
 * // ...
 * }</pre>
 *
 * @param <T> element type
 */
public final class ArrayGenerator<T> {

	/**
	 * Random generator used throughout the class.
	 */
	private static final Random R = new Random(42);

	/**
	 * Creates an integer generator.
	 *
	 * @param min minimum for the generation
	 * @param max maximum for generation
	 *
	 * @return integer generator
	 */
	public static ArrayGenerator<Integer> integers(int min, int max) {
		return new ArrayGenerator<>(() -> R.nextInt(min, max));
	}

	/**
	 * Creates a boolean generator.
	 *
	 * @return boolean generator
	 */
	public static ArrayGenerator<Boolean> booleans() {
		return new ArrayGenerator<>(R::nextBoolean);
	}

	/**
	 * Supplier used to generate random values.
	 */
	private final Supplier<T> supplier;

	private ArrayGenerator(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	/**
	 * Validates perturbation percentages.
	 *
	 * @param percentages perturbation levels
	 */
	private static void validatePercentages(List<Integer> percentages) {
		int previous = -1;
		for (int percentage : percentages) {
			if (percentage < 0 || percentage > 100) {
				throw new IllegalArgumentException(
					"Percentage must be in [0,100]: "
						+ percentage);
			}
			if (percentage <= previous) {
				throw new IllegalArgumentException(
					"Percentages must be strictly increasing.");
			}
			previous = percentage;
		}
	}

	/**
	 * Generates an incremental perturbation chain.
	 * <p>
	 * Given perturbation levels:
	 *
	 * <pre>
	 * 1, 2, 5, 10, 20
	 * </pre>
	 *
	 * the method generates:
	 *
	 * <pre>
	 * I1  = +1%
	 * I2  = +1% additional
	 * I5  = +3% additional
	 * I10 = +5% additional
	 * I20 = +10% additional
	 * </pre>
	 *
	 * so that:
	 *
	 * <pre>
	 * perturbation_between(I0, I20) = 20%
	 * </pre>
	 *
	 * and no position is perturbed twice.
	 *
	 * @param length      the length of the generated arrays
	 * @param percentages cumulative perturbation levels
	 *
	 * @return the generated chain
	 */
	public List<List<T>> generate(int length, List<Integer> percentages) {
		validatePercentages(percentages);

		List<List<T>> result = new ArrayList<>();

		List<T> original = Stream.generate(supplier).limit(length).toList();
		List<T> current = new ArrayList<>(original);

		result.add(original);

		List<Integer> available = IntStream.range(0, length)
			.boxed()
			.collect(ArrayList::new,
				ArrayList::add,
				ArrayList::addAll);

		int previousPercentage = 0;
		for (int percentage : percentages) {
			int delta = percentage - previousPercentage;
			int modifications = delta * length / 100;

			Collections.shuffle(available, R);

			List<Integer> modified = new ArrayList<>(available.subList(0, modifications));

			available.subList(0, modifications).clear();

			current = new ArrayList<>(current);
			for (int index : modified) {
				current.set(index, supplier.get());
			}

			result.add(current);

			previousPercentage = percentage;
		}

		return result;
	}

}
