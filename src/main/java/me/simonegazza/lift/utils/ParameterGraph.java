package me.simonegazza.lift.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.lift.parameters.OriginalParameter;

/**
 * A (topolgical) graph of MiniZinc parameters.
 */
public class ParameterGraph extends DirectedGraph<OriginalParameter> {

	/**
	 * Computes the transitive closure of dependencies for a given parameter.
	 * <p>
	 * This method returns all parameters that directly or indirectly depend on
	 * the given one. It iteratively expands the set until a fixpoint is
	 * reached.
	 *
	 * @param parameter the starting parameter
	 *
	 * @return the set of dependent parameters (including the input)
	 */
	public Set<OriginalParameter> dependsOn(OriginalParameter parameter) {

		Queue<OriginalParameter> stack = new LinkedList<>();
		Set<OriginalParameter> visited = new HashSet<>();
		stack.add(parameter);
		while (!stack.isEmpty()) {
			OriginalParameter current = stack.poll();
			visited.add(current);

			Set<OriginalParameter> dependencies = getNodes().stream()
				.filter(p -> getAdjacent(p).stream()
					.anyMatch(a -> a.equals(current)))
				.collect(Collectors.toSet());

			for (OriginalParameter adj : dependencies) {
				stack.add(adj);
			}
		}

		return visited;
	}

	/**
	 * Returns an optional of the parameter based on the name.
	 *
	 * @param name the string name of the {@link OriginalParameter}
	 *
	 * @return an optional maybe containing the {@link OriginalParameter}
	 */
	public Optional<OriginalParameter> getByName(String name) {
		return getNodes().stream()
			.filter(p -> p.getName().equals(name))
			.findAny();
	}
}
