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

# Compared methods:
# - chain: lifted model, all the instances in a row reusing the nogoods (IINGL)
# - 1by1: original model, one instance at a time
# - assumptions: lifted model, one instance at a time, with the assumptions
#   substituted and the nogoods discarded between instances (ablation: it
#   isolates the cost of the lifting from the benefit of the nogood reuse)
METHODS = ["chain", "1by1", "assumptions"]

# The ablation is optional: it may be run only on some problems and
# percentages. Its metrics are ERROR_VALUE where it is missing.
OPTIONAL_METHODS = {"assumptions"}

def instances(experiment, method):
    """Per-instance statistics of the method in one experiment."""
    if method == "chain":
        return experiment["chain.txt"]["chain"]
    if method == "1by1":
        return experiment["one-by-one.txt"]
    return experiment["assumptions.txt"]

def get_metric(problem, metric, method):
    return [p.get(metric, ERROR_VALUE) for p in instances(problem, method)]

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
            metric: {method: [] for method in METHODS}
            for metric in [
                "statuses",
                "flatTime",
                "solveTime",
                "solveTimeCumulative",
                "failures",
                "failuresCumulative",
                "peakDepth",
                "peakDepthCumulative",
                "cpPropagatorCalls",
                "cpPropagatorCallsCumulative",
                "meanTimePerStatus",
                "flatShare",
            ]
        }

        for percentage in percentages:
            # All experiments for this percentage, across repetitions
            experiments = [repetition[percentage] for repetition in repetitions]

            # Methods available in every repetition of this percentage: an
            # optional method run only on some repetitions is not aggregated,
            # since it would not be comparable with the other methods
            available = [
                method for method in METHODS
                if method not in OPTIONAL_METHODS
                or all("assumptions.txt" in experiment for experiment in experiments)
            ]

            # Check instance counts for every repetition
            for repetition, experiment in enumerate(experiments):
                expected = len(instances(experiment, "1by1"))
                for method in available:
                    found = len(instances(experiment, method))
                    if found != expected:
                        print(
                            f"[{problem_name}, repetition {repetition}, "
                            f"{percentage}%] "
                            f"{method} has {found} instances but "
                            f"1by1 has {expected}"
                        )

            for method in METHODS:
                if method not in available:
                    for metric in results[problem_name]:
                        results[problem_name][metric][method].append(
                            {} if metric in ("statuses", "meanTimePerStatus") else ERROR_VALUE
                        )
                    continue

                # -----------------------------------------------------
                # statuses
                # -----------------------------------------------------
                statuses = Counter()
                for experiment in experiments:
                    statuses.update(
                        p.get("status", ERROR_VALUE)
                        for p in instances(experiment, method)
                    )
                results[problem_name]["statuses"][method].append(dict(statuses))

                # -----------------------------------------------------
                # flatTime
                # -----------------------------------------------------
                # Flattening time of each chain: the lifted model is flattened
                # once for the whole chain, while the original model (1by1) and
                # the single-instance lifted model (assumptions) once per
                # instance (summed over the instances of the chain)
                if method == "chain":
                    flat_times = [
                        experiment["chain.txt"].get("flatTime", ERROR_VALUE)
                        for experiment in experiments
                    ]
                else:
                    flat_times = [
                        cumulative_total(get_metric(experiment, "flatTime", method))
                        for experiment in experiments
                    ]
                results[problem_name]["flatTime"][method].append(
                    aggregate_values(flat_times, geometric_mean)
                )

                # -----------------------------------------------------
                # mean time per status
                # -----------------------------------------------------
                time_per_status = {}
                for experiment in experiments:
                    for e in instances(experiment, method):
                        time_per_status.setdefault(
                            e.get("status", ERROR_VALUE), []
                        ).append(e.get("solveTime", ERROR_VALUE))

                results[problem_name]["meanTimePerStatus"][method].append({
                    status: {
                        "count": len(times),
                        "mean": aggregate_values(times, geometric_mean),
                    }
                    for status, times in time_per_status.items()
                })

                # -----------------------------------------------------
                # other metrics
                # -----------------------------------------------------
                for metric in ["solveTime", "failures", "peakDepth", "cpPropagatorCalls"]:
                    values = []
                    for experiment in experiments:
                        values.extend(get_metric(experiment, metric, method))

                    results[problem_name][metric][method].append(
                        aggregate_values(values, mean if metric != "solveTime" else geometric_mean)
                    )

                    # Cumulative metric
                    results[problem_name][f"{metric}Cumulative"][method].append(
                        cumulative_total(values)
                    )

                # -----------------------------------------------------
                # flat share: percentage of the overall time (flattening
                # and search, summed over the instances of all the chains)
                # spent in flattening
                # -----------------------------------------------------
                flat_total = cumulative_total(flat_times)
                solve_total = results[problem_name]["solveTimeCumulative"][method][-1]
                if is_number(flat_total) and is_number(solve_total) and flat_total + solve_total > 0:
                    share = 100 * flat_total / (flat_total + solve_total)
                else:
                    share = ERROR_VALUE
                results[problem_name]["flatShare"][method].append(share)

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

        # -------------------------------------------------------------
        # Ablation: the overall speedup of the chain over 1by1 splits into
        #   speedupOver1by1 = liftingSpeedup * speedupOverAssumptions
        # where
        #   liftingSpeedup = 1by1 / assumptions  (< 1: the lifting costs)
        #   speedupOverAssumptions  = assumptions / chain (> 1: nogood reuse helps)
        # -------------------------------------------------------------
        for suffix in ["", "Cumulative"]:
            results[problem_name][f"liftingSpeedup{suffix}"] = [
                speedup(one_by_one, assumptions)
                for assumptions, one_by_one in zip(
                    results[problem_name][f"solveTime{suffix}"]["assumptions"],
                    results[problem_name][f"solveTime{suffix}"]["1by1"],
                )
            ]
            results[problem_name][f"speedupOverAssumptions{suffix}"] = [
                speedup(assumptions, chain)
                for chain, assumptions in zip(
                    results[problem_name][f"solveTime{suffix}"]["chain"],
                    results[problem_name][f"solveTime{suffix}"]["assumptions"],
                )
            ]
        results[problem_name]["liftingFailureRatio"] = [
            speedup(one_by_one, assumptions)
            for assumptions, one_by_one in zip(
                results[problem_name]["failures"]["assumptions"],
                results[problem_name]["failures"]["1by1"],
            )
        ]
        results[problem_name]["failureReductionRatioOverAssumptions"] = [
            speedup(assumptions, chain)
            for chain, assumptions in zip(
                results[problem_name]["failures"]["chain"],
                results[problem_name]["failures"]["assumptions"],
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
