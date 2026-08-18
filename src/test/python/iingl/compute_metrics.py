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

def get_solve_time(problem):
    chain = [p["solveTime"] for p in problem["chain.txt"]["chain"]]
    one_by_one = [
        p.get("solveTime", p["time"] - p["flatTime"])
        for p in problem["one-by-one.txt"]
    ]

    return chain, one_by_one

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

def get_cumulative_metric(problem, metric):
    chain, one_by_one = get_metric(problem, metric)
    return (
        list(accumulate(chain)),
        list(accumulate(one_by_one)),
    )

def main(file):
    with open(file, encoding="utf-8") as f:
        data = json.load(f)

    results = {}
    for problem_name in data:
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
            experiment = data[problem_name][percentage]

            if set(percentages) != set(data[problem_name].keys()):
                raise ValueError(f"{problem_name} has wrong percentages {set(data[problem_name].keys())}")

            chain = experiment["chain.txt"]
            one_by_one = experiment["one-by-one.txt"]

            if len(chain["chain"]) != len(one_by_one):
                print(
                    f"[{problem_name}, {percentage}%] "
                    f"chain has {len(chain["chain"])} instances but "
                    f"one-by-one has {len(one_by_one)}"
                )

            # Statuses per percentage
            results[problem_name]["statuses"]["chain"].append(
                dict(Counter(
                    p.get("status", ERROR_VALUE)
                    for p in chain["chain"]
                ))
            )
            results[problem_name]["statuses"]["1by1"].append(
                dict(Counter(
                    p.get("status", ERROR_VALUE)
                    for p in one_by_one
                ))
            )

            results[problem_name]["flatTime"]["chain"].append(
                chain["flatTime"]
            )
            results[problem_name]["flatTime"]["1by1"].append(
                aggregate_values(
                    [p["flatTime"] for p in one_by_one],
                    geometric_mean,
                )
            )

            solve_chain, solve_1by1 = get_solve_time(experiment)
            results[problem_name]["solveTime"]["chain"].append(
                aggregate_values(solve_chain, geometric_mean)
            )
            results[problem_name]["solveTime"]["1by1"].append(
                aggregate_values(solve_1by1, geometric_mean)
            )


            results[problem_name]["solveTimeCumulative"]["chain"].append(
                list(accumulate(solve_chain))
            )
            results[problem_name]["solveTimeCumulative"]["1by1"].append(
                list(accumulate(solve_1by1))
            )

            time_per_status = {
                "chain": {},
                "1by1": {},
            }
            for e in chain["chain"]:
                status = e["status"]
                time_per_status["chain"].setdefault(status, []).append(e["solveTime"])
            for e in one_by_one:
                status = e["status"]
                time_per_status["1by1"].setdefault(status, []).append(e["solveTime"])
            for type_name in ["chain", "1by1"]:
                time_per_status[type_name] = {
                    status: {
                        "count": len(times),
                        "mean": geometric_mean(times),
                    }
                    for status, times in time_per_status[type_name].items()
                }
            results[problem_name]["meanTimePerStatus"]["chain"].append(
                time_per_status["chain"]
            )
            results[problem_name]["meanTimePerStatus"]["1by1"].append(
                time_per_status["1by1"]
            )

            # Other metrics
            metrics_cumulative = ["solveTimeCumulative"]
            for metric in ["failures", "peakDepth", "cpPropagatorCalls"]:
                metric_name = f"{metric}Cumulative"
                metrics_cumulative.append(metric_name)
                chain_values, one_values = get_metric(
                    experiment,
                    metric,
                )

                results[problem_name][metric]["chain"].append(
                    aggregate_values(chain_values, mean)
                )
                results[problem_name][metric]["1by1"].append(
                    aggregate_values(one_values, mean)
                )

                # Cumulative metric
                results[problem_name][metric_name]["chain"].append(
                    list(accumulate(chain_values))
                )
                results[problem_name][metric_name]["1by1"].append(
                    list(accumulate(one_values))
                )

        for metric in metrics_cumulative:
            for method in ["chain", "1by1"]:
                cumulative = results[problem_name][metric][method]

                results[problem_name][metric][method] = [
                    series[-1] if series else ERROR_VALUE
                    for series in cumulative
                ]

        # Speedups
        results[problem_name]["speedupOver1by1"] = [
            one_by_one / chain
            for chain, one_by_one in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["1by1"]
            )
        ]
        results[problem_name]["speedupOver1by1Cumulative"] = [
            one_by_one / chain
            for chain, one_by_one in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["1by1"],
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
