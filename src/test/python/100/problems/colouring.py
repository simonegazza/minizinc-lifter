#!/usr/bin/env python3

import random
from collections import deque

N = 51
PERCENTAGES = [1, 2, 5, 10, 20, 50]
EDGE_PROBABILITY = 0.72
REPETITIONS = 5
NUM_INSTANCES = 20


def is_connected(graph):
    """Check weak connectivity of the directed graph."""
    visited = [False] * N
    queue = deque([0])
    visited[0] = True

    while queue:
        u = queue.popleft()
        for v in range(N):
            if u == v:
                continue
            if graph[u][v] or graph[v][u]:
                if not visited[v]:
                    visited[v] = True
                    queue.append(v)

    return all(visited)

def generate_base_graph():
    graph = [
        [False for _ in range(N)]
        for _ in range(N)
    ]

    # Create a connected backbone.
    for i in range(N - 1):
        if random.random() < 0.5:
            graph[i][i + 1] = True
        else:
            graph[i + 1][i] = True

    # Add many additional directed edges.
    for i in range(N):
        for j in range(N):
            if i == j:
                continue
            if not graph[i][j] and random.random() < EDGE_PROBABILITY:
                graph[i][j] = True

    return graph

def apply_changes(graph, percentage):
    new_graph = [row[:] for row in graph]

    positions = [(i, j) for i in range(N) for j in range(N) if i != j]
    num_changes = max(1, round(len(positions) * percentage / 100))

    changed = 0
    attempts = 0
    max_attempts = num_changes * 50

    while changed < num_changes and attempts < max_attempts:
        attempts += 1
        i, j = random.choice(positions)

        old_value = new_graph[i][j]
        new_graph[i][j] = not old_value

        if is_connected(new_graph):
            changed += 1
        else:
            new_graph[i][j] = old_value

    if max_attempts == attempts:
        raise RuntimeError("Too many attempts used")

    return new_graph

def flatten_graph(graph):
    flat = []
    for i in range(N):
        for j in range(N):
            flat.append("true" if graph[i][j] else "false")
    return flat

def write_dzn(path, graph) -> None:
    flat = flatten_graph(graph)
    content = (
        f"n = {N};\n\n"
        f"graph = array2d(1..{N}, 1..{N}, [{', '.join(flat)}]);\n"
    )
    path.write_text(content, encoding="utf-8")

def generate_percentage_folder(base_dir, percentage) -> None:
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    graph = generate_base_graph()
    write_dzn(folder / "00.dzn", graph)

    current_graph = graph

    for i in range(1, NUM_INSTANCES):
        current_graph = apply_changes(current_graph, percentage)
        write_dzn(folder / f"{i:02d}.dzn", current_graph)
