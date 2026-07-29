import argparse, json
from collections import Counter
from statistics import StatisticsError, geometric_mean, mean

ERROR_VALUE = "N/A"

METRICS = {
    "solveTime": geometric_mean,
    "failures": mean,
    "peakDepth": mean,
    "cpPropagatorCalls": mean,
}

def second_in_pair(values):
    """Return the measured result from a two-entry input/output pair."""
    if isinstance(values, list) and len(values) >= 2 and isinstance(values[1], dict):
        return values[1]
    return {}

def numeric_values(values):
    return [
        value
        for value in values
        if isinstance(value, (int, float)) and not isinstance(value, bool)
    ]

def aggregate(values, aggregator):
    values = numeric_values(values)

    if not values:
        return ERROR_VALUE

    try:
        return aggregator(values)
    except (StatisticsError, ValueError):
        return ERROR_VALUE

def solve_time(instance):
    if "solveTime" in instance:
        return instance["solveTime"] if instance["solveTime"] < 60 else 60.0

    time = instance.get("time")
    flat_time = instance.get("flatTime")

    if isinstance(time, (int, float)) and isinstance(flat_time, (int, float)):
        t = time - flat_time
        return t if t < 60.0 else 60.0

    return ERROR_VALUE

def speedup(chain, one_by_one):
    if (
        isinstance(chain, (int, float))
        and isinstance(one_by_one, (int, float))
        and chain != 0
    ):
        return one_by_one / chain
    return ERROR_VALUE


def process_percentage(problem_name, percentage, repetitions):
    flat_times = {"chain": [], "1by1": []}
    statuses = {"chain": [], "1by1": []}
    metric_values = {
        metric_name: {"chain": [], "1by1": []}
        for metric_name in METRICS
    }

    for repetition_name, repetition in repetitions.items():
        chain_run = repetition.get("chain", {})
        chain_instance = second_in_pair(chain_run.get("chain", []))
        one_by_one_instance = second_in_pair(repetition.get("one_by_one", []))

        if not chain_instance or not one_by_one_instance:
            print(
                f"[{problem_name}, {percentage}, {repetition_name}] "
                "missing the measured chain or 1by1 result"
            )
            continue

        flat_times["chain"].append(chain_run.get("flatTime"))
        flat_times["1by1"].append(one_by_one_instance.get("flatTime"))
        statuses["chain"].append(chain_instance.get("status", ERROR_VALUE))
        statuses["1by1"].append(one_by_one_instance.get("status", ERROR_VALUE))

        for metric_name in METRICS:
            value = solve_time if metric_name == "solveTime" else lambda item: item.get(metric_name, ERROR_VALUE)
            metric_values[metric_name]["chain"].append(value(chain_instance))
            metric_values[metric_name]["1by1"].append(value(one_by_one_instance))

    results = {
        "statuses": {
            method: dict(Counter(values))
            for method, values in statuses.items()
        },
        "flatTime": {
            method: aggregate(values, geometric_mean)
            for method, values in flat_times.items()
        },
    }

    for metric_name, aggregator in METRICS.items():
        results[metric_name] = {
            method: aggregate(values, aggregator)
            for method, values in metric_values[metric_name].items()
        }

    results["speedupOver1by1"] = speedup(
        results["solveTime"]["chain"],
        results["solveTime"]["1by1"],
    )
    return results


def main(file):
    with open(file, encoding="utf-8") as source:
        data = json.load(source)

    return {
        problem_name: {
            percentage: process_percentage(problem_name, percentage, repetitions)
            for percentage, repetitions in percentages.items()
        }
        for problem_name, percentages in data.items()
    }


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Aggregate MiniZinc reproduction experiment results."
    )
    parser.add_argument("file", help="JSON produced by the statistics parsing script.")
    parser.add_argument("output_file", nargs="?", help="Output JSON file (defaults to stdout).")
    args = parser.parse_args()

    result = main(args.file)

    if args.output_file:
        with open(args.output_file, "w", encoding="utf-8") as output:
            json.dump(result, output, indent=2)
    else:
        print(json.dumps(result, indent=2))
