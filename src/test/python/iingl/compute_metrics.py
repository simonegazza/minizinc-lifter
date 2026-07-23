import argparse
import json
from collections import Counter
from itertools import accumulate
from statistics import geometric_mean, mean, StatisticsError

ERROR_VALUE = "N/A"

perturbations = dict(enumerate(["0%", "1%", "2%", "5%", "10%", "20%", "50%"]))

metrics = {
    "solveTime": {
        "aggregator": geometric_mean,
        "chain": [],
        "assumptions": [],
        "1by1": [],
        "chainCumulative": [],
        "assumptionsCumulative": [],
        "1by1Cumulative": [],
    },
    "failures": {
        "aggregator": mean,
        "chain": [],
        "assumptions": [],
        "1by1": [],
        "chainCumulative": [],
        "assumptionsCumulative": [],
        "1by1Cumulative": [],
    },
    "peakDepth": {
        "aggregator": mean,
        "chain": [],
        "assumptions": [],
        "1by1": [],
        "chainCumulative": [],
        "assumptionsCumulative": [],
        "1by1Cumulative": [],
    },
    "cpPropagatorCalls": {
        "aggregator": mean,
        "chain": [],
        "assumptions": [],
        "1by1": [],
        "chainCumulative": [],
        "assumptionsCumulative": [],
        "1by1Cumulative": [],
    }
}

def aggregate_scalar(chain, assumptions, one_by_one, aggregator):
    try:
        return {
            "chain": aggregator(chain),
            "assumptions": aggregator(assumptions),
            "1by1": aggregator(one_by_one),
        }
    except StatisticsError:
        return {
            "chain": ERROR_VALUE,
            "assumptions": ERROR_VALUE,
            "1by1": ERROR_VALUE,
        }

def aggregate_series(chain, assumptions, one_by_one, aggregator):
    try:
        return {
            "chain": [
                aggregator([rep[i] for rep in chain])
                for i in range(len(perturbations))
            ],
            "assumptions": [
                aggregator([rep[i] for rep in assumptions])
                for i in range(len(perturbations))
            ],
            "1by1": [
                aggregator([rep[i] for rep in one_by_one])
                for i in range(len(perturbations))
            ],
        }
    except Exception:
        return {
            "chain": [ERROR_VALUE] * len(perturbations),
            "assumptions": [ERROR_VALUE] * len(perturbations),
            "1by1": [ERROR_VALUE] * len(perturbations),
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

            flat_time_chain.append(chain["flatTime"])
            flat_time_assumptions.extend(p["flatTime"] for p in assumptions)
            flat_time_1by1.extend(p["flatTime"] for p in one_by_one)

            solve_chain = [p["solveTime"] for p in chain["chain"]]
            solve_assumptions = [
                p.get("solveTime", p["time"] - p["flatTime"])
                for p in assumptions
            ]
            solve_1by1 = [
                p.get("solveTime", p["time"] - p["flatTime"])
                for p in one_by_one
            ]

            metrics["solveTime"]["chain"].append(solve_chain)
            metrics["solveTime"]["assumptions"].append(solve_assumptions)
            metrics["solveTime"]["1by1"].append(solve_1by1)
            metrics["solveTime"]["chainCumulative"].append(list(accumulate(solve_chain)))
            metrics["solveTime"]["assumptionsCumulative"].append(list(accumulate(solve_assumptions)))
            metrics["solveTime"]["1by1Cumulative"].append(list(accumulate(solve_1by1)))


            for metric in ["failures", "peakDepth", "cpPropagatorCalls"]:
                chain_values = [p[metric] for p in chain["chain"]]
                assumptions_values = [p.get(metric, "N/A") for p in assumptions]
                one_values = [p.get(metric, "N/A") for p in one_by_one]

                metrics[metric]["chain"].append(chain_values)
                metrics[metric]["assumptions"].append(assumptions_values)
                metrics[metric]["1by1"].append(one_values)
                metrics[metric]["chainCumulative"].append(list(accumulate(chain_values)))
                metrics[metric]["assumptionsCumulative"].append(list(accumulate(assumptions_values)))
                metrics[metric]["1by1Cumulative"].append(list(accumulate(one_values)))

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
            o / c if o != 0 else ERROR_VALUE
            for c, o in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["1by1"]
            )
        ]
        results[problem_name]["speedupOverAssumptions"] = [
            o / c if o != 0 else ERROR_VALUE
            for c, o in zip(
                results[problem_name]["solveTime"]["chain"],
                results[problem_name]["solveTime"]["assumptions"]
            )
        ]
        results[problem_name]["speedupOver1by1Cumulative"] = [
            o / c if o != 0 else ERROR_VALUE
            for c, o in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["1by1"]
            )
        ]
        results[problem_name]["speedupOverAssumptionsCumulative"] = [
            o / c if o != 0 else ERROR_VALUE
            for c, o in zip(
                results[problem_name]["solveTimeCumulative"]["chain"],
                results[problem_name]["solveTimeCumulative"]["assumptions"]
            )
        ]

    return results

def round_floats(obj, digits=3):
    if isinstance(obj, float):
        return f"{obj:.{digits}f}" #round(obj, digits)
    if isinstance(obj, dict):
        return {key: round_floats(value, digits) for key, value in obj.items()}
    if isinstance(obj, list):
        return [round_floats(item, digits) for item in obj]

    return obj

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
    result = round_floats(result)

    if args.output_file:
        with open(args.output_file, "w", encoding="utf-8") as f:
            json.dump(result, f, indent=2)
    else:
        print(json.dumps(result, indent=2))
