import argparse, json
from collections import Counter
from itertools import accumulate
from statistics import geometric_mean, mean, StatisticsError

ERROR_VALUE = "N/A"

# Output order for the perturbations
percentages = ["1", "2", "5", "10", "20", "50"]

def is_number(value):
    return isinstance(value, (int, float)) and not isinstance(value, bool)

def aggregate_values(values, aggregator):
    """Aggregates the numeric values only: entries of failed instances
    (ERROR_VALUE) are excluded. ERROR_VALUE if nothing can be aggregated."""
    try:
        return aggregator([value for value in values if is_number(value)])
    except (StatisticsError, ValueError):
        return ERROR_VALUE

def cumulative_total(values):
    """Total of the series, or ERROR_VALUE if it is empty or any instance failed
    (a partial total would not be comparable with the other method)."""
    if not values or not all(is_number(value) for value in values):
        return ERROR_VALUE
    return list(accumulate(values))[-1]

def speedup(slow, fast):
    """Ratio slow / fast, or ERROR_VALUE when it cannot be computed."""
    if is_number(slow) and is_number(fast) and fast != 0:
        return slow / fast
    return ERROR_VALUE

def get_metric(problem, metric):
    chain = [
        p.get(metric, ERROR_VALUE)
        for p in problem["chain.txt"]["chain"]
    ]
    one_by_one = [
        p.get(metric, ERROR_VALUE)
        for p in problem["one-by-one.txt"]
    ]
    return chain, one_by_one

def main(file):
    with open(file, encoding="utf-8") as f:
        data = json.load(f)

    results = {}
    for problem_name in data:
        repetitions = data[problem_name]

        # Check that all repetitions contain the expected percentages
        for repetition, repetition_data in enumerate(repetitions):
            if set(percentages) != set(repetition_data.keys()):
                raise ValueError(
                    f"{problem_name}, repetition {repetition} has wrong "
                    f"percentages {set(repetition_data.keys())}"
                )

        results[problem_name] = {
            "statuses": {
                "chain": [],
                "1by1": [],
            },
            "flatTime": {
                "chain": [],
                "1by1": [],
            },
            "solveTime": {
                "chain": [],
                "1by1": [],
            },
            "solveTimeCumulative": {
                "chain": [],
                "1by1": [],
            },
            "failures": {
                "chain": [],
                "1by1": [],
            },
            "failuresCumulative": {
                "chain": [],
                "1by1": [],
            },
            "peakDepth": {
                "chain": [],
                "1by1": [],
            },
            "peakDepthCumulative": {
                "chain": [],
                "1by1": [],
            },
            "cpPropagatorCalls": {
                "chain": [],
                "1by1": [],
            },
            "cpPropagatorCallsCumulative": {
                "chain": [],
                "1by1": [],
            },
            "meanTimePerStatus": {
                "chain": [],
                "1by1": [],
            },
        }

        for percentage in percentages:
            # All experiments for this percentage, across repetitions
            experiments = [repetition[percentage] for repetition in repetitions]

            chains = [experiment["chain.txt"] for experiment in experiments]
            one_by_ones = [experiment["one-by-one.txt"] for experiment in experiments]

            # Check instance counts for every repetition
            for repetition, (chain, one_by_one) in enumerate(zip(chains, one_by_ones)):
                if len(chain["chain"]) != len(one_by_one):
                    print(
                        f"[{problem_name}, repetition {repetition}, "
                        f"{percentage}%] "
                        f"chain has {len(chain['chain'])} instances but "
                        f"one-by-one has {len(one_by_one)}"
                    )

            # ---------------------------------------------------------
            # statuses
            # ---------------------------------------------------------
            chain_statuses = Counter()
            one_by_one_statuses = Counter()

            for chain in chains:
                chain_statuses.update(
                    p.get("status", ERROR_VALUE)
                    for p in chain["chain"]
                )
            for one_by_one in one_by_ones:
                one_by_one_statuses.update(
                    p.get("status", ERROR_VALUE)
                    for p in one_by_one
                )

            results[problem_name]["statuses"]["chain"].append(dict(chain_statuses))
            results[problem_name]["statuses"]["1by1"].append(dict(one_by_one_statuses))

            # ---------------------------------------------------------
            # flatTime
            # ---------------------------------------------------------
            chain_flat_times = [chain.get("flatTime", ERROR_VALUE) for chain in chains]
            one_by_one_flat_times = [
                p.get("flatTime", ERROR_VALUE)
                for one_by_one in one_by_ones
                for p in one_by_one
            ]

            results[problem_name]["flatTime"]["chain"].append(
                aggregate_values(chain_flat_times, geometric_mean)
            )
            results[problem_name]["flatTime"]["1by1"].append(
                aggregate_values(one_by_one_flat_times, geometric_mean)
            )

            # ---------------------------------------------------------
            # mean time per status
            # ---------------------------------------------------------
            time_per_status = {
                "chain": {},
                "1by1": {},
            }

            for chain in chains:
                for e in chain["chain"]:
                    status = e["status"]
                    time_per_status["chain"].setdefault(
                        status, []
                    ).append(e["solveTime"])

            for one_by_one in one_by_ones:
                for e in one_by_one:
                    status = e["status"]
                    time_per_status["1by1"].setdefault(
                        status, []
                    ).append(e.get("solveTime", ERROR_VALUE))

            for type_name in ["chain", "1by1"]:
                time_per_status[type_name] = {
                    status: {
                        "count": len(times),
                        "mean": aggregate_values(times, geometric_mean),
                    }
                    for status, times
                    in time_per_status[type_name].items()
                }

            results[problem_name]["meanTimePerStatus"]["chain"].append(
                time_per_status["chain"]
            )
            results[problem_name]["meanTimePerStatus"]["1by1"].append(
                time_per_status["1by1"]
            )

            # ---------------------------------------------------------
            # other metrics
            # ---------------------------------------------------------
            for metric in ["solveTime", "failures", "peakDepth", "cpPropagatorCalls"]:
                chain_values = []
                one_values = []
                for experiment in experiments:
                    chain_metric, one_metric = get_metric(experiment, metric)

                    chain_values.extend(chain_metric)
                    one_values.extend(one_metric)

                results[problem_name][metric]["chain"].append(
                    aggregate_values(chain_values, mean if metric != "solveTime" else geometric_mean)
                )
                results[problem_name][metric]["1by1"].append(
                    aggregate_values(one_values, mean if metric != "solveTime" else geometric_mean)
                )

                # Cumulative metric
                results[problem_name][f"{metric}Cumulative"]["chain"].append(
                    cumulative_total(chain_values)
                )

                results[problem_name][f"{metric}Cumulative"]["1by1"].append(
                    cumulative_total(one_values)
                )

        # -------------------------------------------------------------
        # Speedups
        # -------------------------------------------------------------
        results[problem_name]["speedupOver1by1"] = [
            speedup(one_by_one, chain)
            for chain, one_by_one in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["1by1"],
            )
        ]
        results[problem_name]["speedupOver1by1Cumulative"] = [
            speedup(one_by_one, chain)
            for chain, one_by_one in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["1by1"],
            )
        ]
        results[problem_name]["failureReductionRatio"] = [
            speedup(one_by_one, chain)
            for chain, one_by_one in zip(
                results[problem_name]["failures"]["chain"],
                results[problem_name]["failures"]["1by1"],
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
