package me.simonegazza.lift.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Simple directed graph implementation based on adjacency sets.
 * <p>
 * This class is used to represent dependencies between elements. In the context
 * of the lifting tool, it models relationships between parameters, where an
 * edge {@code A -> B} means that {@code A} depends on {@code B}.
 * <p>
 * The graph is backed by a {@link Map} where:
 * <ul>
 * <li>Keys are nodes</li>
 * <li>Values are the set of adjacent (outgoing) nodes</li>
 * </ul>
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>No cycle detection is performed</li>
 * <li>No null checks are enforced</li>
 * <li>Nodes are automatically created when adding edges</li>
 * </ul>
 *
 * @param <K> the node type
 */
public class DirectedGraph<K> {

	/**
	 * Adjacency representation of the graph.
	 * <p>
	 * Each key maps to the set of nodes it has outgoing edges to.
	 */
	protected Map<K, Set<K>> graph;

	/**
	 * Creates an empty directed graph.
	 */
	public DirectedGraph() {
		graph = new HashMap<K, Set<K>>();
	}

	/**
	 * Adds a node to the graph if it does not already exist.
	 *
	 * @param key the node to add
	 */
	public void addNode(K key) {
		graph.putIfAbsent(key, new HashSet<K>());
	}

	/**
	 * Adds a directed edge from {@code from} to {@code to}.
	 * <p>
	 * If either node does not exist, it is automatically added.
	 *
	 * @param from the source node
	 * @param to   the target node
	 */
	public void addEdge(K from, K to) {
		addNode(from);
		addNode(to);
		graph.get(from).add(to);
	}

	/**
	 * Returns all nodes in the graph.
	 *
	 * @return the set of nodes
	 */
	public Set<K> getNodes() {
		return graph.keySet();
	}

	/**
	 * Returns the nodes directly reachable from the given node.
	 *
	 * @param from the source node
	 *
	 * @return the set of adjacent nodes, or {@code null} if the node is not
	 *             present
	 */
	public Set<K> getAdjacent(K from) {
		return graph.get(from);
	}

}
