import argparse, random, importlib, pkgutil
from pathlib import Path

import problems

NUM_INSTANCES = 20
REPETITIONS = 5
PERCENTAGES = [1, 2, 5, 10, 20, 50]

# The generated data should have this shape:
# 4 problems x 5 repetitions x 6 different percentages x 20 in a percentage
# for a total of 2400 instances
# given that we use 5 min timeout and we both run it in the original model and
# the chain model, we should have in total 2400 x 2 x 5 = 24000
# that is a total of 400 hours of compute time

modules = []
for module_info in pkgutil.iter_modules(problems.__path__):
    module = importlib.import_module(f"problems.{module_info.name}")
    if hasattr(module, "generate_percentage_folder"):
        modules.append((module_info.name, module.generate_percentage_folder))

def main(output_dir, seed):
    # Sets the random seed (to all imported sub-modules too)
    random.seed(seed)

    for module_name, generation_function in modules:
        problem_dir = output_dir / module_name
        problem_dir.mkdir(parents=True, exist_ok=True)

        for repetition in range(REPETITIONS):
            repetition_dir = problem_dir / str(repetition)
            repetition_dir.mkdir(parents=True, exist_ok=True)
            for percentage in PERCENTAGES:
                generation_function(repetition_dir, percentage)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate MiniZinc .dzn datasets for a the problems."
    )
    parser.add_argument(
        "output_dir",
        type=Path,
        help="Problem folder directory that will be created if necesary."
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=42,
        help="Optional random seed for reproducibility."
    )

    args = parser.parse_args()
    main(args.output_dir, seed=args.seed)
