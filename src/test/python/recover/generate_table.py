#!/usr/bin/env python3

import argparse
import json


NAME_MAP = {
    "FREEDA": "FREEDA",
    "aircraft": "Aircraft",
    "fixtures": "Fixtures",
    "ihtc-2024-kletzander": "IHTC",
    "tsptw": "TSPTW",
    # "EchoSched": "EchoSched",
}

ORDER = [
    "FREEDA",
    "aircraft",
    "fixtures",
    "ihtc-2024-kletzander",
    "tsptw",
]


def fmt_int(value):
    return "N/A" if value is None else str(int(value))


def fmt_float(value):
    return f"{value:.2f}"


def generate_table(data):
    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        r"\begin{tabular}{l|cc|ccc|ccc|ccc}",
        r"\hhline{~|~~|~~~|~~~|~~~}",
        r"\multirow{2}{*}{\centering Problem} &"
        r"\multicolumn{2}{c|}{Parameters} &"
        r"\multicolumn{3}{c|}{Core Parameters} &"
        r"\multicolumn{3}{c|}{Iterations} &"
        r"\multicolumn{3}{c}{Process Duration}\\",
        r"\hhline{~|~~|~~~|~~~|~~~}",
        r"&"
        r"Modified & Lifted &"
        r"Min & Avg & Max & "
        r"Min & Avg & Max & "
        r"Min & Avg & Max\\",
        r"\hhline{-|--|---|---|---}",
    ]

    for key in ORDER:
        if key not in data:
            continue

        d = data[key]

        modified = (
            None
            if d["originalParameterModified"] == 0
            else d["originalParameterModified"]
        )

        lines.append(
            f'{NAME_MAP[key]}'
            f' & {fmt_int(modified)}'
            f' & {d["liftedParameters"]}'
            f' & {d["minCoreVariables"]}'
            f' & {d["meanCoreVariables"]}'
            f' & {d["maxCoreVariables"]}'
            f' & {d["minIterations"]}'
            f' & {d["meanIterations"]}'
            f' & {d["maxIterations"]}'
            f' & {fmt_float(d["minTotalDuration"])}'
            f' & {fmt_float(d["avgTotalDuration"])}'
            f' & {fmt_float(d["maxTotalDuration"])}\\\\'
        )

    lines.extend(
        [
            r"\hhline{~|~~|~~~|~~~|~~~}",
            r"\end{tabular}",
            r"\caption{Summary of feasibility restoration experiments}",
            r"\label{tab:recovery}",
            r"\end{table*}",
        ]
    )

    return "\n".join(lines)


def main():
    parser = argparse.ArgumentParser(
        description="Generate a LaTeX table from the experiment summary JSON."
    )
    parser.add_argument(
        "input_json",
        help="Path to the input JSON file.",
    )
    parser.add_argument(
        "output_tex",
        help="Path to the output .tex file.",
    )

    args = parser.parse_args()

    with open(args.input_json, "r") as f:
        data = json.load(f)

    latex = generate_table(data)

    with open(args.output_tex, "w") as f:
        f.write(latex)


if __name__ == "__main__":
    main()