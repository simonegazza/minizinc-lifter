package anonymous.code.lift.utils;

import anonymous.code.lift.parameters.OriginalParameter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A (topolgical) graph of MiniZinc parameters.
 */
public class ParameterGraph extends DirectedGraph<OriginalParameter> {

	/**
	 * Computes all parameters that transitively depend on the given parameter.
	 * <p>
	 * Starting from the input parameter, this method walks the graph in the
	 * reverse direction, collecting every parameter that directly or indirectly
	 * references it.
	 *
	 * @param parameter the parameter from which the reverse dependency
	 *                      traversal starts
	 *
	 * @return all parameters that directly or indirectly depend on the given
	 *             parameter, including the parameter itself
	 */
	public Set<OriginalParameter> backwardClosure(OriginalParameter parameter) {

		Queue<OriginalParameter> queue = new LinkedList<>();
		Set<OriginalParameter> visited = new HashSet<>();
		queue.add(parameter);
		while (!queue.isEmpty()) {
			OriginalParameter current = queue.poll();
			visited.add(current);

			Set<OriginalParameter> dependants = getNodes().stream()
				.filter(p -> getAdjacent(p).contains(current))
				.collect(Collectors.toSet());

			for (OriginalParameter dependant : dependants) {
				queue.add(dependant);

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
