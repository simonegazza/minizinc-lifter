import random

M = 15
N = 12
BEAMTIME = 30
BT_MAX = 10

def row_complexity(row):
    """Mimimum time (total positive variation) required to build the row."""
    c = row[0]
    for j in range(1, len(row)):
        c += max(row[j] - row[j - 1], 0)
    return c

def generate_row(budget, lo=1, hi=BT_MAX):
    """Generate one random row without surpassing 'budget'"""
    row = [random.randint(lo, hi)]
    used = row[0]
    for _ in range(1, N):
        remaining = budget - used
        max_up = min(hi - row[-1], max(0, remaining))
        min_down = lo - row[-1]
        delta = random.randint(min_down, max_up)
        row.append(row[-1] + delta)
        if delta > 0:
            used += delta
    return row

def generate_base_instance():
    return [generate_row(BEAMTIME) for _ in range(M)]

def apply_changes(matrix, percentage, budget=BEAMTIME):
    new_matrix = [row[:] for row in matrix]

    positions = [(i, j) for i in range(M) for j in range(N)]
    num_changes = max(1, round(len(positions) * percentage / 100))
    changed_positions = random.sample(positions, num_changes)

    for i, j in changed_positions:
        row = new_matrix[i]
        current = row[j]
        candidates = [v for v in range(1, BT_MAX + 1) if v != current]
        random.shuffle(candidates)

        for v in candidates:
            trial_row = row[:]
            trial_row[j] = v
            if row_complexity(trial_row) <= budget:
                new_matrix[i][j] = v
                break

    return new_matrix

def flatten_matrix(matrix):
    flat = []
    for i in range(M):
        for j in range(N):
            flat.append(str(matrix[i][j]))
    return flat

def write_dzn(path, matrix):
    flat = flatten_matrix(matrix)

    content = (
        f"m = {M};\n"
        f"n = {N};\n"
        f"Beamtime = {BEAMTIME};\n"
        f"Bt_max = {BT_MAX};\n\n"
        f"Intensity = array2d(1..{M}, 1..{N}, [{', '.join(flat)}]);\n"
    )

    path.write_text(content, encoding="utf-8")

def generate_percentage_folder(base_dir, percentage, n_instances):
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    matrix = generate_base_instance()
    write_dzn(folder / "00.dzn", matrix)

    current_matrix = matrix

    for i in range(1, n_instances):
        current_matrix = apply_changes(current_matrix, percentage)
        write_dzn(folder / f"{i:02d}.dzn", current_matrix)
