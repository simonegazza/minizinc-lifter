#!/usr/bin/env python3

import argparse, random
from pathlib import Path

M = 15
N = 12
BEAMTIME = 30
BT_MAX = 10

PERCENTAGES = [1, 2, 5, 10, 20, 50]
NUM_INSTANCES = 100


def generate_base_instance():
    return [
        [random.randint(1, BT_MAX) for _ in range(N)]
        for _ in range(M)
    ]

def apply_changes(matrix, percentage):
    new_matrix = [row[:] for row in matrix]

    positions = [(i, j) for i in range(M) for j in range(N)]
    num_changes = max(1, round(len(positions) * percentage / 100))

    changed_positions = random.sample(positions, num_changes)

    for i, j in changed_positions:
        current = new_matrix[i][j]
        choices = [v for v in range(1, BT_MAX + 1) if v != current]
        new_matrix[i][j] = random.choice(choices)

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

def generate_percentage_folder(base_dir, percentage):
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    matrix = generate_base_instance()
    write_dzn(folder / "00.dzn", matrix)

    current_matrix = matrix

    for i in range(1, NUM_INSTANCES):
        current_matrix = apply_changes(current_matrix, percentage)
        write_dzn(folder / f"{i:02d}.dzn", current_matrix)

def main(output_dir, seed):
    random.seed(seed)

    radiation_dir = output_dir / "radiation"
    radiation_dir.mkdir(parents=True, exist_ok=True)

    for percentage in PERCENTAGES:
        generate_percentage_folder(radiation_dir, percentage)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate MiniZinc .dzn datasets for the Radiation Therapy Problem."
    )

    parser.add_argument(
        "output_dir",
        type=Path,
        help="Directory where the radiation folder will be created."
    )

    parser.add_argument(
        "--seed",
        type=int,
        default=42,
        help="Optional random seed for reproducibility."
    )

    args = parser.parse_args()
    main(args.output_dir, seed=args.seed)
