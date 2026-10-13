#!/usr/bin/env python3
import sys, json, os, argparse

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

            for run in parsed_json.get("runs", []):
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
