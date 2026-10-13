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
        ("unknowns", "Call TO"),
    ],
    "lifted": [
        #("gMean(iterationSumFlatTime)", "Flat Time"),
        ("gMean(iterationSumSolveTime)", "Solve Time"),
        ("failures", "Failures"),
        #("cpPropagatorCalls", "CP Calls"),
        ("unknowns", "Call TO"),
    ],
}

# Which comparison groups to include and in what order: each group is a
# multi-column with a title, the function that selects its counts from the
# results of a problem, and its (JSON key, LaTeX column title) sub-columns.
# Modify this to change the table layout.
STATUS_GROUPS = {
    "Status of Lifted vs CMPCS": (
        lambda pdata: pdata["better"]["soft"],
        [
            ("better", "Not Weaker"),
            ("worse", "Weaker"),
            ("MUSes", "Global TO"),
            #("discordant", "Disc."),
        ],
    ),
    "Objective of Lifted vs CMPCS": (
        lambda pdata: pdata["objective"],
        [
            ("better", "Better"),
            ("equal", "Equal"),
            ("worse", "Worse"),
            ("globalTO", "Global TO"),
        ],
    ),
}

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
    alignment = (
        "c|c|"
        + "|".join("r" for _ in METHOD_COLUMNS["soft"])
        + "|"
        + "|".join("r" * len(cols) for _, cols in STATUS_GROUPS.values())
    )

    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")
    lines.append(rf"\begin{{tabular}}{{{alignment}}}")

    # ------------------------------------------------------------
    # Header
    # ------------------------------------------------------------

    group_headers = [
        rf"\multicolumn{{{len(cols)}}}{{c{'|' if i < len(STATUS_GROUPS) - 1 else ''}}}{{{name}}}"
        for i, (name, (_, cols)) in enumerate(STATUS_GROUPS.items())
    ]

    lines.append(
        r"\multirow{2}{*}{Problem} & "
        r"\multirow{2}{*}{Method} & "
        + " &\n".join(
            rf"\multirow{{2}}{{*}}{{{title}}}"
            for _, title in METHOD_COLUMNS["soft"]
        )
        + " &\n" + " &\n".join(group_headers) + r" \\"
    )

    status_headers = []
    for _, cols in STATUS_GROUPS.values():
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

        for select, cols in STATUS_GROUPS.values():
            counts = select(pdata)
            for key, _ in cols:
                row.append(r"\multirow{2}{*}{" + str(counts[key]) + "}")

        lines.append(" & ".join(row) + r" \\")

        # Lifted
        row = ["", "Lifted"]
        metrics = pdata["lifted"]
        for key, _ in METHOD_COLUMNS["lifted"]:
            row.append(fmt(metrics[key]))

        for _, cols in STATUS_GROUPS.values():
            for _ in cols:
                row.append("")

        lines.append(" & ".join(row) + r" \\")

        if i != len(results.items()) - 1:
            lines.append(r"\hline")

    lines.append(r"\end{tabular}")

    table = "\n".join(lines)
    table += (
        "\n\\caption{Results of the comparison with CMPCS.}\n"
        "\\label{tab:comparison}\n"
        "\\end{table*}"
    )

    return table

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
