package me.simonegazza.lifting.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<K> {
	private Map<K, Set<K>> graph;

	public DirectedGraph() {
		this.graph = new HashMap<K, Set<K>>();
	}

	public void addNode(K key) {
		graph.putIfAbsent(key, new HashSet<K>());
	}

	public void addEdge(K from, K to) {
		addNode(from);
		addNode(to);
		graph.get(from).add(to);
	}

	public Set<K> getNodes() {
		return graph.keySet();
	}

	public Set<K> getAdjacent(K from) {
		return graph.get(from);
	}

	@Override
	public String toString() {
		return graph.toString();
	}
}
