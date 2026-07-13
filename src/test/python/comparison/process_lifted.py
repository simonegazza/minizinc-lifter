#!/usr/bin/env python3
import argparse, json, statistics, re

def recursively_collect_key(obj, key_name, result):
    if isinstance(obj, dict):
        for key, value in obj.items():
            if key == key_name:
                result.append(value)
            recursively_collect_key(value, key_name, result)
    elif isinstance(obj, list):
        for item in obj:
            recursively_collect_key(item, key_name, result)

def collect_in_array(raw_data, key_name, result):
    for instance in raw_data:
        instance_values = []

        for output in instance["combined"]:
            output_values = []
            recursively_collect_key(output, key_name, output_values)

            instance_values.extend(output_values)

        result.append(instance_values)

def collect_statuses(raw_data):
    result = []
    for instance in raw_data:
        for stream in reversed(instance["combined"]):
            found = False
            for js in reversed(stream):
                if js["type"] == "status":
                    result.append(js["status"])
                    found = True
                    break
                elif js["type"] == "statistics" and "status" in js["statistics"]:
                    result.append(js["statistics"]["status"])
                    found = True
                    break

            if found:
                break

    return result

def main(raw_data):
    # We need to order the keys
    pattern = re.compile(r"^output_(\d+)\.txt$")
    for instance in raw_data:
        txt_names = []
        for txt in instance["combined"]:
            txt_names.extend(txt.keys())

        indices_map = {
            k : int(pattern.match(k).group(1))
            for k in txt_names
        }

        combined = [None for _ in indices_map.keys()]
        for txt in instance["combined"]:
            # There is just one value per txt
            if len(txt.keys()) > 1:
                raise ValueError("A multiple .dzns")
            key = list(txt.keys())[0]
            value = list(txt.values())[0]
            combined[indices_map[key]] = value

        for c in combined:
            if c is None:
                raise ValueError("A output_*.txt is missing")

        instance["combined"] = combined

    flat_times = []
    recursively_collect_key(raw_data, "flatTime", flat_times)

    solve_times = []
    collect_in_array(raw_data, "solveTime", solve_times)
    solve_times = [
        sum(instance)
        for instance in solve_times
    ]

    statuses = collect_statuses(raw_data)

    failures = []
    collect_in_array(raw_data, "failures", failures)
    failures = [
        sum(instance)
        for instance in failures
    ]

    cpPropagatorCalls = []
    collect_in_array(raw_data, "cpPropagatorCalls", cpPropagatorCalls)
    cpPropagatorCalls = [
        sum(instance)
        for instance in cpPropagatorCalls
    ]

    iterations = [len(instance["combined"]) for instance in raw_data]

    return {
        "gMean(iterationSumFlatTime)" : statistics.geometric_mean(flat_times),
        "gMean(iterationSumSolveTime)" : statistics.geometric_mean(solve_times),
        "statuses" : statuses,
        "failures" : statistics.geometric_mean(failures),
        "minIterations" : min(iterations),
        "medianIterations" : int(statistics.median(iterations)),
        "maxIterations" : max(iterations),
        "cpPropagatorCalls" : statistics.geometric_mean(cpPropagatorCalls)
    }

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("input_json", help="Input JSON file")
    args = parser.parse_args()

    with open(args.input_json, "r", encoding="utf-8") as f:
        raw_data = json.load(f)
    stats = main(raw_data)

    print(json.dumps(stats, indent=2, ensure_ascii=False))
