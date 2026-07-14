#!/usr/bin/env python3
from pathlib import Path

from process_all import main as process

# Metrics to include for each method (JSON key -> LaTeX column title)
METHOD_COLUMNS = {
    "soft": [
        #("flatTimeMean", "Flat Time"),
        ("solveTimeMean", "Solve Time"),
        ("failures", "Failures"),
        #("cpPropagatorCalls", "CP Calls"),
        ("unknowns", "UNK"),
    ],
    "lifted": [
        #("gMean(iterationSumFlatTime)", "Flat Time"),
        ("gMean(iterationSumSolveTime)", "Solve Time"),
        ("failures", "Failures"),
        #("cpPropagatorCalls", "CP Calls"),
        ("unknowns", "UNK"),
    ],
}

# Which status groups to include and in what order.
# Modify this to change the table layout.
STATUS_GROUPS = {
    "status": [
        ("better", "Better"),
        ("worse", "Worse"),
        ("MUSes", "MUSes"),
        #("discordant", "Disc."),
    ]
}

# ---------------------------------------------------------------------

def fmt(x):
    """Pretty formatting for numbers."""
    if isinstance(x, int):
        return str(x)
    if isinstance(x, float):
        if abs(x) >= 1e5:
            return f"{x:.2e}"
        if abs(x) >= 100:
            return f"{x:.2f}"
        if abs(x) >= 1:
            return f"{x:.4f}"
        return f"{x:.4f}"
    return str(x)


def generate_table(results):
    n_status_cols = sum(len(v) for v in STATUS_GROUPS.values())

    alignment = (
        "c|c|"
        + "|".join("r" for _ in METHOD_COLUMNS["soft"])
        + "|"
        + "|".join("r" * len(v) for v in STATUS_GROUPS.values())
    )

    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")
    lines.append(rf"\begin{{tabular}}{{{alignment}}}")

    # ------------------------------------------------------------
    # Header
    # ------------------------------------------------------------

    lines.append(
        r"\multirow{2}{*}{Problem} & "
        r"\multirow{2}{*}{Method} & "
        + " & ".join(
            rf"\multirow{{2}}{{*}}{{{title}}}"
            for _, title in METHOD_COLUMNS["soft"]
        )
        + rf" & \multicolumn{{{n_status_cols}}}{{c}}{{Status}} \\"
    )

    status_headers = []
    for cols in STATUS_GROUPS.values():
        status_headers.extend(title for _, title in cols)

    lines.append(
        " & " * (2 + len(METHOD_COLUMNS["soft"]))
        + " & ".join(status_headers)
        + r" \\"
    )

    lines.append(r"\hline")

    # ------------------------------------------------------------
    # Body
    # ------------------------------------------------------------
    for i, (problem, pdata) in enumerate(results.items()):
        # Soft
        row = [rf"\multirow{{2}}{{*}}{{{problem}}}", "CMPCS"]
        metrics = pdata["soft"]
        for key, _ in METHOD_COLUMNS["soft"]:
            row.append(fmt(metrics[key]))

        for _, cols in STATUS_GROUPS.items():
            for key, _ in cols:
                row.append(str(pdata["better"]["soft"][key]))

        lines.append(" & ".join(row) + r" \\")

        # Lifted
        row = ["", "Lifted"]
        metrics = pdata["lifted"]
        for key, _ in METHOD_COLUMNS["lifted"]:
            row.append(fmt(metrics[key]))

        for key, _ in STATUS_GROUPS["status"]:
            row.append(str(pdata["better"]["soft"][key]))

        lines.append(" & ".join(row) + r" \\")

        if i != len(results.items()) - 1:
            lines.append(r"\hline")

    lines.append(r"\end{tabular}")

    return "\n".join(lines)

def main(folder):
    return generate_table(process(folder))

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(
        description="Generate a LaTeX table from benchmark results."
    )
    parser.add_argument(
        "folder",
        type=Path,
        help="Folder containing the benchmark results."
    )
    parser.add_argument(
        "-o", "--output",
        metavar="FILE",
        help="Write the generated LaTeX table to FILE instead of stdout."
    )
    args = parser.parse_args()

    table = main(args.folder)

    if args.output is None:
        print(table)
    else:
        with open(args.output, "w") as f:
            f.write(table)
