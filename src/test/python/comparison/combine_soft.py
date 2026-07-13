#!/usr/bin/env python3
import argparse, json, re
from pathlib import Path

def extract_json_values(text):
    decoder = json.JSONDecoder()
    idx = 0
    values = []

    while idx < len(text):
        while idx < len(text) and text[idx].isspace():
            idx += 1

        if idx >= len(text):
            break

        obj, end = decoder.raw_decode(text, idx)
        values.append(obj)
        idx = end

    return values


def main(root : Path, argument):
    pattern = re.compile(rf"^soft_{re.escape(argument)}_(\d+)_.+\.txt$")

    files = []
    for f in root.iterdir():
        if not f.is_file():
            continue

        m = pattern.match(f.name)
        if not m:
            continue

        files.append((int(m.group(1)), f))

    files.sort(key=lambda x: x[0])

    result = []
    for _, txt_file in files:
        with txt_file.open("r", encoding="utf-8") as fp:
            text = fp.read()

        values = extract_json_values(text)

        result.append({
            "instance": str(txt_file),
            "combined": values
        })

    return result


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("root", help="Root folder")
    parser.add_argument("argument", help="Argument used in lifted_<argument>_<n>_*")
    args = parser.parse_args()

    result = main(Path(args.root), args.argument)
    print(json.dumps(result, indent=2, ensure_ascii=False))
