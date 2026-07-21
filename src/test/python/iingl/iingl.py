import argparse, json
from itertools import chain
from pathlib import Path

def parse_chain(path):
    initial = {}
    iterations = {}
    with open(path, encoding="utf-8") as f:
        for line in f:
            line = line.strip()

            if not line.startswith("{"):
                continue

            try:
                obj = json.loads(line)
            except json.JSONDecodeError:
                continue

            if obj.get("type") != "statistics":
                continue

            stats = obj["statistics"]

            # Global statistics
            if "blockType" not in stats:
                initial.update(stats)

            # Init statistics
            elif stats["blockType"] == "init":
                initial.update(stats)
            # Keep only the last statistics for each iteration
            elif stats["blockType"] == "complete":
                iterations[int(stats["iteration"])] = stats

    initial["path"] = str(path)

    return {**initial, "chain": [iterations[i] for i in sorted(iterations)]}

def parse_one_by_one(path):
    problems = []
    current = None
    with open(path, encoding="utf-8") as f:
        for line in f:
            line = line.strip()

            if not line:
                continue

            if line.endswith(".dzn"):
                current = {"path": line}
                continue

            if not line.startswith("{"):
                continue

            try:
                obj = json.loads(line)
            except json.JSONDecodeError:
                continue

            if current is None:
                continue

            t = obj["type"]

            if t == "statistics":
                stats = obj["statistics"]
                current.update(stats)

                # Se non arriverà mai uno status, almeno salva quello
                if "status" in stats:
                    current["status"] = stats["status"]

                # Fine istanza
                if "nSolutions" in stats:
                    problems.append(current)
                    current = None

            elif t == "status":
                current["status"] = obj["status"]
                current["time"] = obj.get("time")

            elif t == "time":
                current["time"] = obj["time"]

    return problems


def main(folder):
    folder = Path(folder)

    paths = sorted(
        chain(
            folder.rglob("chain.txt"),
            folder.rglob("assumptions.txt"),
            folder.rglob("one-by-one.txt"))
    )

    results = {}
    for path in paths:
        rel = path.relative_to(folder)

        # Assumes:
        # folder/problem/method/repetition/{chain.txt|assumptions.txt|one-by-one.txt}
        problem = rel.parts[0]
        method = rel.parts[1]
        repetition = rel.parts[2]

        if path.name == "chain.txt":
            entry = parse_chain(path)
        elif path.name == "assumptions.txt":
            entry = parse_one_by_one(path)
        else:
            entry = parse_one_by_one(path)

        repetition_dict = results.setdefault(problem, {}).setdefault(method, {})
        repetition_dict[repetition] = entry

    return results


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Process MiniZinc experiment statistics."
    )
    parser.add_argument(
        "folder",
        help="Folder containing all experiments.",
    )
    parser.add_argument(
        "output_file",
        nargs="?",
        help="Output JSON file (defaults to stdout).",
    )
    args = parser.parse_args()

    result = main(args.folder)

    if args.output_file is None:
        print(json.dumps(result, indent=2))
    else:
        with open(args.output_file, "w", encoding="utf-8") as f:
            json.dump(result, f, indent=2)
