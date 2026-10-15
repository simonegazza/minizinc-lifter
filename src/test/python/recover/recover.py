#!/usr/bin/env python3
import sys, json, os, argparse, re, shlex

def parse_file(filepath):
    """
    Reads a file and finds the JSON that comes after '*********************************************'.
    Returns the parsed JSON dictionary, or None if the pattern or JSON is not found.
    """
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()

        pattern = "*********************************************"
        idx = content.find(pattern)
        if idx == -1:
            return None

        # Look for the JSON part after the pattern
        json_part = content[idx + len(pattern):].strip()

        # We find the first '{' after the pattern to start parsing
        start_idx = json_part.find('{')
        if start_idx == -1:
            return None

        # Robustly parse the JSON object
        decoder = json.JSONDecoder()
        obj, _ = decoder.raw_decode(json_part[start_idx:])
        return obj
    except json.JSONDecodeError as e:
        print(f"JSON decode error in {filepath}: {e}", file=sys.stderr)
        return None
    except Exception as e:
        print(f"Error parsing {filepath}: {e}", file=sys.stderr)
        return None

MARKER = "*********************************************"

# Last MiniZinc line of an execution -> MiniZinc status
STATUS_LINES = {
    "==========": "OPTIMAL",
    "----------": "SATISFIABLE",
    "=====UNSATISFIABLE=====": "UNSATISFIABLE",
    "=====UNKNOWN=====": "UNKNOWN",
}

def runs_from_log(filepath):
    """
    Lines of the log of each run: the output of the executions of a run is
    followed by the statistics of the run (a JSON object with "finalState").
    """
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    idx = content.find(MARKER)
    log = content if idx == -1 else content[:idx]

    decoder = json.JSONDecoder()
    runs = []
    current = []
    position = 0
    for line in log.splitlines(keepends=True):
        if line.strip() == "{":
            try:
                obj, _ = decoder.raw_decode(log, position)
            except json.JSONDecodeError:
                obj = None
            if isinstance(obj, dict) and "finalState" in obj:
                runs.append(current)
                current = []
        current.append(line.rstrip("\n"))
        position += len(line)
    return runs

def status_of_run(lines):
    """
    MiniZinc status of a run, given by its last status line (a solution is
    OPTIMAL only if followed by "=========="). Used for the runs whose
    statistics have no "status".
    """
    last = None
    for line in lines:
        if line.strip() in STATUS_LINES:
            last = STATUS_LINES[line.strip()]
    return last or "UNKNOWN"

def flat_time_of_run(lines):
    """
    Flattening time of a run: sum of the "flatTime" statistics of its MiniZinc
    executions (None if no statistic was recorded). Used for the runs whose
    statistics have no "totalFlatTime".
    """
    times = [
        float(m.group(1))
        for m in (re.match(r"^%%%mzn-stat: flatTime=([0-9.eE+-]+)$", line.strip()) for line in lines)
        if m
    ]
    return sum(times) if times else None

def top_level_elements(value):
    """
    Elements of a value printed by MiniZinc: the elements of an array (sets are
    single elements), or the value itself for a scalar or a set.
    """
    value = value.strip()
    if not (value.startswith("[") and value.endswith("]")):
        return [value]
    elements, depth, current = [], 0, ""
    for char in value[1:-1]:
        if char in "[{(":
            depth += 1
        elif char in "]})":
            depth -= 1
        if char == "," and depth == 0:
            elements.append(current.strip())
            current = ""
        else:
            current += char
    if current.strip():
        elements.append(current.strip())
    return elements

def solution_values(lines):
    """
    Values printed in the last solution of a run ("original <name> = <value>"
    and "lifted   <name> = <value>"), or None if the run has no solution.
    """
    stripped = [line.strip() for line in lines]
    if "----------" not in stripped:
        return None
    last_solution = len(lines) - 1 - stripped[::-1].index("----------")
    values = {}
    for line in lines[:last_solution]:
        m = re.match(r"^(original|lifted)\s+([A-Za-z_][A-Za-z0-9_]*) = (.*)$", line)
        if m:
            values[(m.group(1), m.group(2))] = m.group(3)
    return values

def as_number(value):
    """A printed MiniZinc number, or None if it is not a number."""
    try:
        return float(value)
    except ValueError:
        return None

def bound_value(term, values, data_file):
    """
    Value of a bound of a lifted domain: a number, min/max of the original
    value of a parameter, or a parameter assigned in the data file.
    """
    term = term.strip()
    if as_number(term) is not None:
        return as_number(term)
    m = re.fullmatch(r"(min|max)\(([A-Za-z_][A-Za-z0-9_]*)\)", term)
    if m and ("original", m.group(2)) in values:
        numbers = [as_number(e) for e in top_level_elements(values[("original", m.group(2))])]
        return (min if m.group(1) == "min" else max)(numbers)
    if re.fullmatch(r"[A-Za-z_][A-Za-z0-9_]*", term) and data_file and os.path.isfile(data_file):
        with open(data_file, encoding="utf-8") as f:
            m = re.search(rf"^\s*{term}\s*=\s*(-?[0-9.]+)\s*;", f.read(), re.M)
        if m:
            return float(m.group(1))
    raise ValueError(f"cannot evaluate the bound {term}")

def domain_width(domain, values, data_file):
    """Width u - l of a lifted domain "l..u" or "{v1, ..., vn}"."""
    domain = domain.strip()
    if domain.startswith("{") and domain.endswith("}"):
        numbers = [bound_value(v, values, data_file) for v in domain[1:-1].split(",")]
        return max(numbers) - min(numbers)
    lower, upper = domain.split("..", 1)
    return bound_value(upper, values, data_file) - bound_value(lower, values, data_file)

def repair_of_run(lines, parameters, data_file):
    """
    Distances of the repair of a run (the last solution) from the original
    instance, over the requested parameters (name -> domain), counting each
    element of an array as a parameter:
    - Hamming: number of changed parameters;
    - Distance: mean, over the changed parameters, of |k - k'| / (u - l)
      (Gower distance), where k is the original value, k' the repaired one and
      l..u the lifted domain (0 if u = l).
    Returns (None, None) if the run has no solution.
    """
    values = solution_values(lines)
    if values is None:
        return None, None

    hamming = 0
    distances = []
    for name, domain in parameters.items():
        if ("original", name) not in values or ("lifted", name) not in values:
            print(f"Warning: values of {name} not found in the output", file=sys.stderr)
            continue
        original = top_level_elements(values[("original", name)])
        lifted = top_level_elements(values[("lifted", name)])
        changed = [(o, l) for o, l in zip(original, lifted) if o != l]
        hamming += len(changed)
        if not changed:
            continue
        try:
            width = domain_width(domain, values, data_file) if domain else None
        except ValueError as e:
            print(f"Warning: {name}: {e}, not included in Distance", file=sys.stderr)
            continue
        if width is None:
            print(f"Warning: {name} has no lifted domain, not included in Distance", file=sys.stderr)
            continue
        for o, l in changed:
            k, k1 = as_number(o), as_number(l)
            if k is None or k1 is None:
                print(f"Warning: {name} is not numeric, not included in Distance", file=sys.stderr)
                break
            distances.append(abs(k - k1) / width if width != 0 else 0)

    distance = sum(distances) / len(distances) if distances else (0 if hamming == 0 else None)
    return hamming, distance

def lifted_parameters(problem_script):
    """
    Parameters lifted (-p) by test-run.jar in a problem script, with their
    domain (name -> "l..u", "{...}" or None), and the folder of the data files
    (-d).
    """
    parameters = {}
    data_folder = None
    with open(problem_script, encoding="utf-8") as f:
        for line in f:
            if "test-run.jar" not in line:
                continue
            tokens = shlex.split(line)
            for i, token in enumerate(tokens):
                if token == "-p":
                    name, _, domain = tokens[i + 1].partition(":")
                    parameters[name] = domain or None
                elif token == "-d":
                    data_folder = tokens[i + 1]
    return parameters, data_folder

def main():
    parser = argparse.ArgumentParser(
        description="Parse JSON from .txt files in a folder and combine them into a single JSON file."
    )
    parser.add_argument("input_folder", help="Folder containing .txt files to parse")
    parser.add_argument("output_file", help="Output combined JSON file path")
    parser.add_argument(
        "--allow-failures",
        action="store_true",
        help="Exit with 0 even if some problem or run failed (failures are still reported)",
    )
    parser.add_argument(
        "--problems-folder",
        default="src/test/sh/recovery/problems",
        help="Folder of the problem scripts, to read the lifted parameters (default: %(default)s)",
    )
    args = parser.parse_args()

    input_folder = args.input_folder
    output_file = args.output_file

    if not os.path.isdir(input_folder):
        print(f"Error: Directory '{input_folder}' does not exist.", file=sys.stderr)
        sys.exit(1)

    combined_data = {}
    found_files = 0
    # (file, reason) of every problem or run that did not complete: they are
    # never dropped silently
    failures = []

    # Sort filenames so the output JSON has a predictable order
    for filename in sorted(os.listdir(input_folder)):
        if filename.endswith(".txt"):
            filepath = os.path.join(input_folder, filename)
            parsed_json = parse_file(filepath)

            if parsed_json is None:
                # The runner crashed before printing the statistics (e.g., the
                # instance generation failed)
                failures.append((filename, "no statistics found"))
                continue

            # Store the parsed JSON using the filename as the key
            combined_data[filename] = parsed_json
            found_files += 1

            # Statistics read from the log of each run: the status (for the
            # runs recorded before the "status" statistic) and the Hamming
            # distance of the repair
            runs = parsed_json.get("runs", [])
            log_runs = runs_from_log(filepath)
            script = os.path.join(args.problems_folder, filename[:-len(".txt")])
            parameters, data_folder = lifted_parameters(script) if os.path.isfile(script) else (None, None)
            if parameters is None:
                print(f"{filename}: {script} not found, Hamming distance not computed", file=sys.stderr)
            if len(log_runs) == len(runs):
                for run, lines in zip(runs, log_runs):
                    run["statistics"].setdefault("status", status_of_run(lines))
                    if "totalFlatTime" not in run["statistics"]:
                        run["statistics"]["totalFlatTime"] = flat_time_of_run(lines)
                    if parameters is not None:
                        data_file = os.path.join(data_folder, run["dataFile"]) if data_folder and run.get("dataFile") else None
                        hamming, distance = repair_of_run(lines, parameters, data_file)
                        run["statistics"]["hamming"] = hamming
                        run["statistics"]["distance"] = distance
            else:
                print(f"{filename}: cannot match the runs with the log", file=sys.stderr)

            for run in runs:
                state = run.get("statistics", {}).get("finalState")
                if state is None or state == "ERROR":
                    failures.append((f"{filename}: {run.get('dataFile')}", f"final state {state}"))

    if found_files == 0:
        print("No valid JSON found after the specified pattern in any .txt files.", file=sys.stderr)

    # Write the aggregated dictionary to the output file
    with open(output_file, 'w', encoding='utf-8') as out_f:
        json.dump(combined_data, out_f, indent=2)

    print(f"Successfully processed {found_files} files and saved to {output_file}.")

    if failures:
        print(f"{len(failures)} failure(s):", file=sys.stderr)
        for name, reason in failures:
            print(f"  {name}: {reason}", file=sys.stderr)
        if not args.allow_failures:
            sys.exit(1)

if __name__ == "__main__":
    main()
