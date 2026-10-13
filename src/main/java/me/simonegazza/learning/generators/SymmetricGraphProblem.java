package me.simonegazza.learning.generators;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates .dzn problems whose data is an undirected graph, given as a
 * symmetric boolean adjacency matrix {@code graph}.
 * <p>
 * Only the pairs {@code i < j} are generated (and perturbed), so that changing
 * an edge always changes both {@code graph[i,j]} and {@code graph[j,i]}. The
 * perturbation percentages refer to the undirected edges.
 */
public abstract class SymmetricGraphProblem extends Problem<Boolean> {

	/**
	 * Number of nodes.
	 */
	private final int nodes;

	/**
	 * Value of the diagonal of the adjacency matrix.
	 */
	private final boolean diagonal;

	/**
	 * Creates a problem on an undirected graph.
	 *
	 * @param name     the problem name
	 * @param nodes    the number of nodes
	 * @param diagonal the value of {@code graph[i,i]}
	 */
	protected SymmetricGraphProblem(String name, int nodes, boolean diagonal) {
		super(name, ArrayGenerator.booleans(), nodes * (nodes - 1) / 2);
		this.nodes = nodes;
		this.diagonal = diagonal;
	}

	/**
	 * Builds the symmetric adjacency matrix from the pairs {@code i < j}, in
	 * row-major order.
	 *
	 * @param pairs the values of the pairs {@code i < j}, row by row
	 *
	 * @return the flattened adjacency matrix
	 */
	private List<Boolean> toMatrix(List<Boolean> pairs) {
		Boolean[][] matrix = new Boolean[nodes][nodes];
		int k = 0;
		for (int i = 0; i < nodes; i++) {
			matrix[i][i] = diagonal;
			for (int j = i + 1; j < nodes; j++) {
				matrix[i][j] = pairs.get(k);
				matrix[j][i] = pairs.get(k);
				k++;
			}
		}

		List<Boolean> result = new ArrayList<>();
		for (Boolean[] row : matrix) {
			result.addAll(List.of(row));
		}
		return result;
	}

	@Override
	protected String stringify(List<Boolean> data) {
		return "graph = array2d(1.." + nodes + ", 1.." + nodes + ", " + toMatrix(data) + ");";
	}
}
