import argparse
import json
from collections import Counter
from statistics import geometric_mean, mean, StatisticsError

ERROR_VALUE = "N/A"

perturbations = dict(enumerate(["0%", "1%", "2%", "5%", "10%", "20%", "50%"]))

METRIC_AGGREGATORS = {
    "solveTime": geometric_mean,
    "failures": mean,
    "peakDepth": mean,
    "cpPropagatorCalls": mean,
}


def new_metrics():
    """Returns empty per-problem metric collectors."""
    return {
        name: {
            "aggregator": aggregator,
            "chain": [],
            "assumptions": [],
            "1by1": [],
            "chainCumulative": [],
            "assumptionsCumulative": [],
            "1by1Cumulative": [],
        }
        for name, aggregator in METRIC_AGGREGATORS.items()
    }

def is_number(value):
    return isinstance(value, (int, float)) and not isinstance(value, bool)


def numeric_values(values):
    """Drops the entries of failed instances (ERROR_VALUE or missing)."""
    return [value for value in values if is_number(value)]


def solve_time(p):
    """Solve time in seconds; "time" (ms) is used only when "solveTime" is missing.
    ERROR_VALUE for instances whose run failed."""
    if "solveTime" in p:
        return p["solveTime"]
    if is_number(p.get("time")) and is_number(p.get("flatTime")):
        return p["time"] / 1000 - p["flatTime"]
    return ERROR_VALUE


def cumulative(values):
    """Running sum of a series. A cumulative value is meaningless after a failed
    instance, so every position from the first non-numeric value on is ERROR_VALUE
    (and is then excluded from the aggregation of that position)."""
    result = []
    for value in values:
        if not is_number(value):
            return result + [ERROR_VALUE] * (len(values) - len(result))
        result.append(value if not result else result[-1] + value)
    return result


def speedup(slow, fast):
    """Ratio slow / fast, or ERROR_VALUE when it cannot be computed."""
    if isinstance(slow, (int, float)) and isinstance(fast, (int, float)) and fast != 0:
        return slow / fast
    return ERROR_VALUE


def aggregate_scalar(chain, assumptions, one_by_one, aggregator):
    try:
        return {
            "chain": aggregator(numeric_values(chain)),
            "assumptions": aggregator(numeric_values(assumptions)),
            "1by1": aggregator(numeric_values(one_by_one)),
        }
    except StatisticsError:
        return {
            "chain": ERROR_VALUE,
            "assumptions": ERROR_VALUE,
            "1by1": ERROR_VALUE,
        }

def aggregate_position(repetitions, i, aggregator):
    """Aggregates position i over the repetitions, skipping failed (non-numeric)
    or missing entries; ERROR_VALUE if no repetition has a value there."""
    values = numeric_values(rep[i] for rep in repetitions if i < len(rep))
    try:
        return aggregator(values)
    except (StatisticsError, ValueError):
        return ERROR_VALUE


def aggregate_series(chain, assumptions, one_by_one, aggregator):
    return {
        "chain": [
            aggregate_position(chain, i, aggregator)
            for i in range(len(perturbations))
        ],
        "assumptions": [
            aggregate_position(assumptions, i, aggregator)
            for i in range(len(perturbations))
        ],
        "1by1": [
            aggregate_position(one_by_one, i, aggregator)
            for i in range(len(perturbations))
        ],
    }


def main(file):
    with open(file) as f:
        data = json.load(f)

    results = {}
    for problem_name, repetitions in data.items():
        results[problem_name] = {
            "statuses": {},
            "flatTime": {},
            "solveTime": {},
            "solveTimeCumulative": {},
            "failures": {},
            "failuresCumulative": {},
            "peakDepth": {},
            "peakDepthCumulative": {},
            "cpPropagatorCalls": {},
            "cpPropagatorCallsCumulative": {},
        }

        flat_time_chain = []
        flat_time_assumptions = []
        flat_time_1by1 = []

        statuses_chain = []
        statuses_assumptions = []
        statuses_1by1 = []

        # Collected separately for each problem
        metrics = new_metrics()

        for repetition_index, repetition in repetitions.items():
            chain = repetition["chain.txt"]
            assumptions = repetition["assumptions.txt"]
            one_by_one = repetition["one-by-one.txt"]

            if len(chain["chain"]) != len(one_by_one):
                print(
                    f"[{problem_name}, {repetition_index}] "
                    f"chain has {len(chain['chain'])} instances but "
                    f"one-by-one has {len(one_by_one)}"
                )
            if len(assumptions) != len(one_by_one):
                print(
                    f"[{problem_name}, {repetition_index}] "
                    f"assumptions has {len(assumptions)} instances but "
                    f"one-by-one has {len(one_by_one)}"
                )

            flat_time_chain.append(chain.get("flatTime", ERROR_VALUE))
            flat_time_assumptions.extend(p.get("flatTime", ERROR_VALUE) for p in assumptions)
            flat_time_1by1.extend(p.get("flatTime", ERROR_VALUE) for p in one_by_one)

            solve_chain = [p["solveTime"] for p in chain["chain"]]
            solve_assumptions = [
                solve_time(p)
                for p in assumptions
            ]
            solve_1by1 = [
                solve_time(p)
                for p in one_by_one
            ]

            metrics["solveTime"]["chain"].append(solve_chain)
            metrics["solveTime"]["assumptions"].append(solve_assumptions)
            metrics["solveTime"]["1by1"].append(solve_1by1)
            metrics["solveTime"]["chainCumulative"].append(cumulative(solve_chain))
            metrics["solveTime"]["assumptionsCumulative"].append(cumulative(solve_assumptions))
            metrics["solveTime"]["1by1Cumulative"].append(cumulative(solve_1by1))


            for metric in ["failures", "peakDepth", "cpPropagatorCalls"]:
                chain_values = [p[metric] for p in chain["chain"]]
                assumptions_values = [p.get(metric, ERROR_VALUE) for p in assumptions]
                one_values = [p.get(metric, ERROR_VALUE) for p in one_by_one]

                metrics[metric]["chain"].append(chain_values)
                metrics[metric]["assumptions"].append(assumptions_values)
                metrics[metric]["1by1"].append(one_values)
                metrics[metric]["chainCumulative"].append(cumulative(chain_values))
                metrics[metric]["assumptionsCumulative"].append(cumulative(assumptions_values))
                metrics[metric]["1by1Cumulative"].append(cumulative(one_values))

            statuses_chain.extend(
                p.get("status", ERROR_VALUE)
                for p in chain["chain"]
            )
            statuses_assumptions.extend(
                p.get("status", ERROR_VALUE)
                for p in assumptions
            )
            statuses_1by1.extend(
                p.get("status", ERROR_VALUE)
                for p in one_by_one
            )

        results[problem_name]["statuses"] = {
            "chain": Counter(statuses_chain),
            "assumptions": Counter(statuses_assumptions),
            "1by1": Counter(statuses_1by1),
        }

        results[problem_name]["flatTime"] = aggregate_scalar(
            flat_time_chain,
            flat_time_assumptions,
            flat_time_1by1,
            geometric_mean,
        )

        for metric_name, metric in metrics.items():
            results[problem_name][metric_name] = aggregate_series(
                metric["chain"],
                metric["assumptions"],
                metric["1by1"],
                metric["aggregator"],
            )
            results[problem_name][f"{metric_name}Cumulative"] = aggregate_series(
                metric["chainCumulative"],
                metric["assumptionsCumulative"],
                metric["1by1Cumulative"],
                metric["aggregator"],
            )

        results[problem_name]["speedupOver1by1"] = [
            speedup(o, c)
            for c, o in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["1by1"]
            )
        ]
        results[problem_name]["speedupOverAssumptions"] = [
            speedup(o, c)
            for c, o in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["assumptions"]
            )
        ]
        results[problem_name]["speedupOver1by1Cumulative"] = [
            speedup(o, c)
            for c, o in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["1by1"]
            )
        ]
        results[problem_name]["speedupOverAssumptionsCumulative"] = [
            speedup(o, c)
            for c, o in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["assumptions"]
            )
        ]

    return results


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Process MiniZinc Inter Instances Nogood Learning results."
    )
    parser.add_argument(
        "file",
        help="JSON file containing all results."
    )
    parser.add_argument(
        "output_file",
        nargs="?",
        help="Output file (defaults to stdout)."
    )
    args = parser.parse_args()

    result = main(args.file)

    if args.output_file:
        with open(args.output_file, "w", encoding="utf-8") as f:
            json.dump(result, f, indent=2)
    else:
        print(json.dumps(result, indent=2))
