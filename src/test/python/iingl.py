import argparse, ast, json
from pathlib import Path

def parse_stats_file(path):
    blocks = []
    current = None
    #stat_end_count = 0

    with open(path, "r", encoding="utf-8") as f:
        for line in f:
            line = line.strip()

            if line == "%%%mzn-stat-end":
                #stat_end_count += 1
                if current is not None:
                    blocks.append(current)
                    current = None
                continue

            if not line.startswith("%%%mzn-stat: "):
                continue

            # Ignore everything before the second stats-end
            #if stat_end_count < 2:
            #    continue

            if current is None:
                current = {}

            content = line.removeprefix("%%%mzn-stat: ")
            key, value = content.split("=")

            current[key] = value = ast.literal_eval(value)

    return blocks

def main(folder):
    statistics = {"chain": {}, "one-by-one": {}}
    for path in Path(folder).rglob("chain.txt"):
        problem = path.parent.parent.name

        chain = parse_stats_file(path)
        init = chain[0] | chain[1]
        init["chain"] = chain[2:-1] # Last one should be "nSolutions" block

        problem = statistics["chain"].setdefault(problem, [])
        problem.append(init)
    
    for path in Path(folder).rglob("one-by-one.txt"):
        problem = path.parent.parent.name

        chain = parse_stats_file(path)
        init = chain[0]
        init["chain"] = chain[1:]

        problem = statistics["one-by-one"].setdefault(problem, [])
        problem.append(init)

    return statistics

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Process experiments for inter-instances no good learning."
    )
    parser.add_argument(
        "folder",
        help="Path folder containing all the experiments",
    )
    parser.add_argument(
        "output_file",
        nargs="?",
        help="Path to the output file (defaults to stdout)",
    )
    args = parser.parse_args()
    
    result = main(args.folder)

    if args.output_file is None:
        print(json.dumps(result, indent=2))
    else:
        with open(args.output_file, "w") as f:
            json.dump(result, f)
