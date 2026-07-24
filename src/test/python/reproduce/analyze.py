import argparse, json
from pathlib import Path

def parse_chain(path):
    initial = {}
    iterations = {}
    with path.open(encoding="utf-8") as f:
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

            stats = obj.get("statistics", {})

            if "blockType" not in stats or stats["blockType"] == "init":
                initial.update(stats)
            elif stats["blockType"] == "complete":
                iterations[int(stats["iteration"])] = stats

    return {**initial, "path": str(path), "chain": [iterations[i] for i in sorted(iterations)]}


def parse_one_by_one(path):
    problems = []
    current = None
    with path.open(encoding="utf-8") as f:
        for line in f:
            line = line.strip()

            if not line:
                continue

            if line.endswith(".dzn"):
                current = {"path": line}
                continue

            if not line.startswith("{") or current is None:
                continue

            try:
                obj = json.loads(line)
            except json.JSONDecodeError:
                continue

            message_type = obj.get("type")

            if message_type == "statistics":
                stats = obj.get("statistics", {})
                current.update(stats)

                if "status" in stats:
                    current["status"] = stats["status"]

                if "nSolutions" in stats:
                    problems.append(current)
                    current = None

            elif message_type == "status":
                current["status"] = obj.get("status")
                current["time"] = obj.get("time")

            elif message_type == "time":
                current["time"] = obj.get("time")

    return problems

def main(folder):
    """
    Folder expected structure: <problem>/<percentage>/<repetition>/{chain.txt, one-by-one.txt}
    """
    folder = Path(folder)
    results = {}

    paths = sorted(
        list(folder.rglob("chain.txt"))
        +
        list(folder.rglob("one-by-one.txt"))
    )

    for path in paths:
        rel = path.relative_to(folder)
        problem, percentage, repetition, filename = rel.parts

        repetition_dict = (
            results
            .setdefault(problem, {})
            .setdefault(percentage, {})
            .setdefault(repetition, {})
        )

        if filename == "chain.txt":
            repetition_dict["chain"] = parse_chain(path)
        elif filename == "one-by-one.txt":
            repetition_dict["one_by_one"] = parse_one_by_one(path)

    return results


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Process MiniZinc reproduction experiment statistics."
    )
    parser.add_argument(
        "folder",
        help="Folder target/reproduce/problems containing the experiments.",
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
