#!/usr/bin/env python3
import argparse, json, re
from pathlib import Path

def extract_json_values(text):
    """
    Extract consecutive JSON values from a text file.
    Assumes the file contains valid JSON objects/arrays one after another.
    """
    decoder = json.JSONDecoder()
    idx = 0
    values = []

    while idx < len(text):
        # Skip whitespace
        while idx < len(text) and text[idx].isspace():
            idx += 1

        if idx >= len(text):
            break

        obj, end = decoder.raw_decode(text, idx)
        values.append(obj)
        idx = end

    return values

def main(root : Path, argument):
    folder_pattern = re.compile(rf"^lifted_{re.escape(argument)}_(\d+)_.+$")
    output_pattern = re.compile(r"^output_(\d+)\.txt$")

    folders = []
    for child in root.iterdir():
        if not child.is_dir():
            continue

        m = folder_pattern.match(child.name)
        if not m:
            continue

        folders.append((int(m.group(1)), child))

    folders.sort(key=lambda x: x[0])

    result = []

    for _, folder in folders:
        output_files = []

        for f in folder.iterdir():
            if not f.is_file():
                continue

            m = output_pattern.match(f.name)
            if not m:
                continue

            output_files.append((int(m.group(1)), f))

        output_files.sort(key=lambda x: x[0])

        combined = []

        for _, txt_file in output_files:
            with txt_file.open("r", encoding="utf-8") as fp:
                text = fp.read()

            values = extract_json_values(text)

            combined.append({
                txt_file.name: values
            })

        result.append({
            "instance": str(folder),
            "combined": combined
        })

    return result

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("root", help="Root folder")
    parser.add_argument("argument", help="Argument used in lifted_<argument>_<n>_*")
    args = parser.parse_args()

    result = main(Path(args.root), args.argument)
    print(json.dumps(result, indent=2, ensure_ascii=False))
