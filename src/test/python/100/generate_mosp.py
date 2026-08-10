#!/usr/bin/env python3

import argparse, random
from pathlib import Path

C = 35  # number of customers
P = 35  # number of products
DENSITY = 0.20

PERCENTAGES = [1, 2, 5, 10, 20, 50]
NUM_INSTANCES = 100


def generate_base_instance():
    graph = [
        [False for _ in range(P)]
        for _ in range(C)
    ]

    for i in range(C):
        for j in range(P):
            if random.random() < DENSITY:
                graph[i][j] = True

    # Ensure every customer requires at least one product.
    for i in range(C):
        if not any(graph[i]):
            graph[i][random.randrange(P)] = True

    # Ensure every product is required by at least one customer.
    for j in range(P):
        if not any(graph[i][j] for i in range(C)):
            graph[random.randrange(C)][j] = True

    return graph

def apply_changes(graph, percentage):
    new_graph = [row[:] for row in graph]

    positions = [(i, j) for i in range(C) for j in range(P)]
    num_changes = max(1, round(len(positions) * percentage / 100))

    changed_positions = random.sample(positions, num_changes)

    for i, j in changed_positions:
        new_graph[i][j] = not new_graph[i][j]

    # Keep every customer with at least one required product.
    for i in range(C):
        if not any(new_graph[i]):
            new_graph[i][random.randrange(P)] = True

    # Keep every product required by at least one customer.
    for j in range(P):
        if not any(new_graph[i][j] for i in range(C)):
            new_graph[random.randrange(C)][j] = True

    return new_graph

def flatten_graph(graph):
    flat = []
    for i in range(C):
        for j in range(P):
            flat.append("true" if graph[i][j] else "false")
    return flat

def write_dzn(path, graph):
    flat = flatten_graph(graph)
    content = (
        f"c = {C};\n"
        f"graph = array2d(1..{C}, 1..{P}, [{', '.join(flat)}]);\n"
    )
    path.write_text(content, encoding="utf-8")

def generate_percentage_folder(base_dir, percentage):
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    graph = generate_base_instance()
    write_dzn(folder / "00.dzn", graph)

    current_graph = graph

    for i in range(1, NUM_INSTANCES):
        current_graph = apply_changes(current_graph, percentage)
        write_dzn(folder / f"{i:02d}.dzn", current_graph)

def main(output_dir, seed):
    random.seed(seed)

    mosp_dir = output_dir / "mosp"
    mosp_dir.mkdir(parents=True, exist_ok=True)

    for percentage in PERCENTAGES:
        generate_percentage_folder(mosp_dir, percentage)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate MiniZinc .dzn datasets for MOSP."
    )
    parser.add_argument(
        "output_dir",
        type=Path,
        help="Directory where the mosp folder will be created."
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=42,
        help="Optional random seed for reproducibility."
    )

    args = parser.parse_args()
    main(args.output_dir, seed=args.seed)
