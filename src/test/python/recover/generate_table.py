#!/usr/bin/env python3

import argparse
import json

NAME_MAP = {
    "FREEDA": "FREEDA",
    "aircraft": "Aircraft",
    "fixtures": "Fixtures",
    "ihtc-2024-kletzander": "IHTC",
    "tsptw": "TSPTW",
    "EchoSched": "EchoSched"
}

ORDER = [
    "FREEDA",
    "aircraft",
    "fixtures",
    "ihtc-2024-kletzander",
    "tsptw",
    "EchoSched"
]

def fmt_int(value):
    return "N/A" if value is None else str(int(value))

def fmt_float(value):
    return "N/A" if value is None else f"{value:.2f}"


def fmt_mean(value):
    """Mean with one decimal, missing values as N/A."""
    return "N/A" if value is None else f"{value:.1f}"


def fmt_distance(value):
    """Distance (in [0, 1]) with three decimals, missing values as N/A."""
    return "N/A" if value is None else f"{value:.3f}"


# Time limit (in seconds) of each solver call
TIME_LIMIT = 60


def fmt_runtime(value):
    """Runtime in seconds, or TO when it exceeds the time limit."""
    if value is not None and value > TIME_LIMIT:
        return "TO"
    return fmt_float(value)

# MiniZinc statuses of the repairs, with the abbreviation and the description
# used in the caption (only for the statuses that occur)
STATUSES = {
    "SATISFIABLE": ("SAT", "a repair found within the time limit but not proven optimal"),
    "UNKNOWN": ("UNK", "no repair found within the time limit"),
    "UNSATISFIABLE": ("UNSAT", "a proof that no repair exists"),
    "ERROR": ("ERR", "an error during the recovery"),
}


def status_caption(data):
    """
    Sentence of the caption summarizing the statuses of the repairs: the
    problems with instances whose repair is not proven optimal, with their
    statuses.
    """
    exceptions = []
    used = []
    for key in ORDER:
        if key not in data:
            continue
        counts = [
            (status, n)
            for status, n in sorted(data[key]["statuses"].items())
            if n > 0 and status != "OPTIMAL"
        ]
        if counts:
            parts = []
            for status, n in counts:
                abbreviation = STATUSES.get(status, (status, None))[0]
                parts.append(f"{n} {abbreviation}")
                if status not in used:
                    used.append(status)
            exceptions.append(f"{NAME_MAP[key]} ({', '.join(parts)})")

    if not exceptions:
        return "The repair is proven optimal on every instance."

    def join(items):
        return items[0] if len(items) == 1 else ", ".join(items[:-1]) + " and " + items[-1]

    known = [s for s in used if s in STATUSES]
    legend = join([f"{STATUSES[s][0]} denotes {STATUSES[s][1]}" for s in known]) if known else ""
    return (
        "The repair is proven optimal on every instance except "
        + join(exceptions)
        + (f", where {legend}." if legend else ".")
    )


def generate_table(data):
    groups = [2, 3, 3, 2]

    def hhline(char):
        """Horizontal rule over all the column groups ("~" none, "-" all)."""
        return r"\hhline{" + char + "|" + "|".join(char * n for n in groups) + "}"

    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        r"\begin{tabular}{l|" + "|".join("c" * n for n in groups) + "}",
        hhline("~"),
        r"\multirow{2}{*}{\centering Problem} &"
        r"\multicolumn{2}{c|}{Parameters} &"
        r"\multicolumn{3}{c|}{Core Size} &"
        r"\multicolumn{3}{c|}{Runtime} &"
        r"\multicolumn{2}{c}{Objectives}\\",
        hhline("~"),
        r"&"
        r"Modified & Lifted &"
        r"Min & Avg & Max & "
        r"Min & Mean & Max & "
        r"Distance & Hamming\\",
        hhline("-"),
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
            f' & {fmt_runtime(d["minTotalDuration"])}'
            f' & {fmt_runtime(d["avgTotalDuration"])}'
            f' & {fmt_runtime(d["maxTotalDuration"])}'
            f' & {fmt_distance(d.get("meanDistance"))}'
            f' & {fmt_mean(d.get("meanHamming"))}\\\\'
        )

    lines.extend(
        [
            hhline("~"),
            r"\end{tabular}",
            r"\caption{Summary of feasibility-recovery experiments. " + status_caption(data) + "}",
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

    # Iterations and timeouts are not in the table: (min, mean, max)
    # iterations and the timeouts of the main solver for each benchmark
    print("Iterations (min, mean, max) and main solver timeouts:")
    for key in ORDER:
        if key in data:
            d = data[key]
            print(
                f'  {NAME_MAP[key]}: ({d["minIterations"]}, {d["meanIterations"]}, {d["maxIterations"]}), '
                f'timeouts {d["mainSolverTimeouts"]}/{d["runs"]}'
            )

    with open(args.output_tex, "w") as f:
        f.write(latex)


if __name__ == "__main__":
    main()
