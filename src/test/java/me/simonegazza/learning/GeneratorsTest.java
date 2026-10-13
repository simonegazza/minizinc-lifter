package me.simonegazza.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import me.simonegazza.learning.generators.Colouring;
import me.simonegazza.learning.generators.Mosp;
import me.simonegazza.learning.generators.SymmetricGraphProblem;
import org.junit.jupiter.api.Test;

public class GeneratorsTest {

	private static boolean[][] graph(String instance) {
		String values = instance.substring(instance.lastIndexOf('[') + 1, instance.lastIndexOf(']'));
		List<Boolean> flat = Arrays.stream(values.split(",")).map(String::strip).map(Boolean::valueOf).toList();
		int n = (int) Math.sqrt(flat.size());
		boolean[][] result = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = flat.get(i * n + j);
			}
		}
		return result;
	}

	@Test
	public void symmetricGraphsTest() {
		List<Integer> percentages = List.of(1, 2, 5, 10, 20, 50);
		for (SymmetricGraphProblem problem : List.of(new Colouring(), new Mosp())) {
			List<String> instances = problem.generateProblems(percentages);
			boolean[][] original = graph(instances.getFirst());
			int n = original.length;
			int pairs = n * (n - 1) / 2;

			for (int k = 0; k < instances.size(); k++) {
				boolean[][] g = graph(instances.get(k));
				int changed = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						assertEquals(g[i][j], g[j][i], problem.getName() + " is not symmetric");
						if (i < j && g[i][j] != original[i][j]) {
							changed++;
						}
					}
				}
				int expected = k == 0 ? 0 : percentages.get(k - 1) * pairs / 100;
				assertEquals(expected, changed, problem.getName() + " changed edges");
			}
		}
	}
}
