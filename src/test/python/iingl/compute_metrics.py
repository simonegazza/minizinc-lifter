import argparse, json
from collections import Counter
from itertools import accumulate
from statistics import geometric_mean, mean, StatisticsError

ERROR_VALUE = "N/A"

# Output order for the perturbations
percentages = ["1", "2", "5", "10", "20", "50"]

def aggregate_values(values, aggregator):
    try:
        return aggregator(values)
    except (StatisticsError, ValueError):
        raise ValueError(f"Non-numeric value in aggregation: {values}")

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
            chain_flat_times = [chain["flatTime"] for chain in chains]
            one_by_one_flat_times = [
                p["flatTime"]
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
                    ).append(e["solveTime"])

            for type_name in ["chain", "1by1"]:
                time_per_status[type_name] = {
                    status: {
                        "count": len(times),
                        "mean": geometric_mean(times),
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
                    aggregate_values(chain_values, mean)
                )
                results[problem_name][metric]["1by1"].append(
                    aggregate_values(one_values, mean)
                )

                # Cumulative metric
                chain_cumulative = list(accumulate(chain_values))
                one_cumulative = list(accumulate(one_values))

                results[problem_name][f"{metric}Cumulative"]["chain"].append(
                    chain_cumulative[-1]
                    if chain_cumulative
                    else ERROR_VALUE
                )

                results[problem_name][f"{metric}Cumulative"]["1by1"].append(
                    one_cumulative[-1]
                    if one_cumulative
                    else ERROR_VALUE
                )

        # -------------------------------------------------------------
        # Speedups
        # -------------------------------------------------------------
        results[problem_name]["speedupOver1by1"] = [
            one_by_one / chain
            for chain, one_by_one in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["1by1"],
            )
        ]
        results[problem_name]["speedupOver1by1Cumulative"] = [
            one_by_one / chain
            for chain, one_by_one in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["1by1"],
            )
        ]
        results[problem_name]["failureReductionRatio"] = [
            one_by_one / chain
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
