import random

C = 35  # number of customers
DENSITY = 0.20


def generate_base_instance():
    """
    Symmetric graph of the customers that share at least one product: every
    customer shares its products with itself.
    """
    graph = [
        [i == j for j in range(C)]
        for i in range(C)
    ]

    for i in range(C):
        for j in range(i + 1, C):
            if random.random() < DENSITY:
                graph[i][j] = graph[j][i] = True

    return graph

def apply_changes(graph, percentage):
    new_graph = [row[:] for row in graph]

    # Undirected edges: changing (i, j) also changes (j, i).
    positions = [(i, j) for i in range(C) for j in range(i + 1, C)]
    num_changes = max(1, round(len(positions) * percentage / 100))

    changed_positions = random.sample(positions, num_changes)

    for i, j in changed_positions:
        new_graph[i][j] = new_graph[j][i] = not new_graph[i][j]

    return new_graph

def flatten_graph(graph):
    flat = []
    for i in range(C):
        for j in range(C):
            flat.append("true" if graph[i][j] else "false")
    return flat

def write_dzn(path, graph):
    flat = flatten_graph(graph)
    content = (
        f"c = {C};\n"
        f"graph = array2d(1..{C}, 1..{C}, [{', '.join(flat)}]);\n"
    )
    path.write_text(content, encoding="utf-8")

def generate_percentage_folder(base_dir, percentage, n_instances):
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    graph = generate_base_instance()
    write_dzn(folder / "00.dzn", graph)

    current_graph = graph

    for i in range(1, n_instances):
        current_graph = apply_changes(current_graph, percentage)
        write_dzn(folder / f"{i:02d}.dzn", current_graph)
