#!/usr/bin/env python3
import argparse, json, statistics

def recursively_collect_key(obj, key_name, result):
    if isinstance(obj, dict):
        for key, value in obj.items():
            if key == key_name:
                result.append(value)
            recursively_collect_key(value, key_name, result)
    elif isinstance(obj, list):
        for item in obj:
            recursively_collect_key(item, key_name, result)

def collect_statuses(raw_data):
    result = []
    for instance in raw_data:
        for stream in reversed(instance["combined"]):
            if stream["type"] == "status":
                result.append(stream["status"])
                break
            elif stream["type"] == "statistics" and "status" in stream["statistics"]:
                result.append(stream["statistics"]["status"])
                break
    return result

def main(raw_data):
    flat_times = []
    recursively_collect_key(raw_data, "flatTime", flat_times)

    solve_times = []
    recursively_collect_key(raw_data, "solveTime", solve_times)

    statuses = collect_statuses(raw_data)

    failures = []
    recursively_collect_key(raw_data, "failures", failures)

    cpPropagatorCalls = []
    recursively_collect_key(raw_data, "cpPropagatorCalls", cpPropagatorCalls)

    return {
        "flatTimeMean" : statistics.geometric_mean(flat_times),
        "solveTimeMean" : statistics.geometric_mean(solve_times),
        "statuses" : statuses,
        "failures": statistics.geometric_mean(failures),
        "cpPropagatorCalls": statistics.geometric_mean(cpPropagatorCalls)
    }

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("input_json", help="Input JSON file")
    args = parser.parse_args()

    with open(args.input_json, "r", encoding="utf-8") as f:
        raw_data = json.load(f)
    stats = main(raw_data)

    print(json.dumps(stats, indent=2, ensure_ascii=False))
