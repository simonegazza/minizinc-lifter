#!/usr/bin/env python3
import sys
from pathlib import Path

from process_all import main as process

# Metrics to include for each method (JSON key -> LaTeX column title)
METHOD_COLUMNS = {
    "soft": [
        #("flatTimeMean", "Flat Time"),
        ("solveTimeMean", "Solve Time"),
        ("failures", "Failures"),
        #("cpPropagatorCalls", "CP Calls"),
    ],
    "lifted": [
        #("gMean(iterationSumFlatTime)", "Flat Time"),
        ("gMean(iterationSumSolveTime)", "Solve Time"),
        ("failures", "Failures"),
        #("cpPropagatorCalls", "CP Calls"),
    ],
}

# Timeouts multi-column: "Call" (instances whose last solver call ended with
# status Unknown) is given for each method, "Global" (instances for which the
# global budget of the lifted procedure expired) only for Lifted ("N/A" for
# CMPCS, which has no global budget)
TIMEOUTS = ("Timeouts", [("Call", "unknowns"), ("Global", "globalTO")])

# Which comparison groups to include and in what order: each group is a
# multi-column with a title, the function that selects its counts from the
# results of a problem, and its (JSON key, LaTeX column title) sub-columns.
# Modify this to change the table layout.
# Title of the group comparing the objective values, generated only on request
OBJECTIVE_GROUP = "Objective of Lifted vs CMPCS"

STATUS_GROUPS = {
    "Status of Lifted vs CMPCS": (
        lambda pdata: pdata["better"]["soft"],
        [
            ("better", "Not Weaker"),
            ("worse", "Weaker"),
            #("discordant", "Disc."),
        ],
    ),
    OBJECTIVE_GROUP: (
        lambda pdata: pdata["objective"],
        [
            ("better", "Better"),
            ("equal", "Equal"),
            ("worse", "Worse"),
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


def global_timeouts(problem, pdata):
    """
    Instances for which the global budget of the lifted procedure expired
    (the same count must result from the comparison of the statuses).
    """
    global_to = pdata["objective"]["globalTO"]
    if global_to != pdata["better"]["soft"]["MUSes"]:
        print(
            f"Warning: {problem}: {global_to} global timeouts from the objectives, "
            f"{pdata['better']['soft']['MUSes']} from the statuses",
            file=sys.stderr,
        )
    return global_to


def generate_table(results, objectives=False):
    # The comparison of the objectives is included only if requested
    groups = {
        name: group
        for name, group in STATUS_GROUPS.items()
        if objectives or name != OBJECTIVE_GROUP
    }

    timeouts_title, timeouts_cols = TIMEOUTS
    alignment = (
        "c|c|"
        + "|".join("r" for _ in METHOD_COLUMNS["soft"])
        + "|"
        + "r" * len(timeouts_cols)
        + "|"
        + "|".join("r" * len(cols) for _, cols in groups.values())
    )

    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")
    lines.append(rf"\begin{{tabular}}{{{alignment}}}")

    # ------------------------------------------------------------
    # Header
    # ------------------------------------------------------------

    group_headers = [
        rf"\multicolumn{{{len(cols)}}}{{c{'|' if i < len(groups) - 1 else ''}}}{{{name}}}"
        for i, (name, (_, cols)) in enumerate(groups.items())
    ]

    lines.append(
        r"\multirow{2}{*}{Problem} & "
        r"\multirow{2}{*}{Method} & "
        + " &\n".join(
            rf"\multirow{{2}}{{*}}{{{title}}}"
            for _, title in METHOD_COLUMNS["soft"]
        )
        + " &\n" + rf"\multicolumn{{{len(timeouts_cols)}}}{{c|}}{{{timeouts_title}}}"
        + " &\n" + " &\n".join(group_headers) + r" \\"
    )

    status_headers = [title for title, _ in timeouts_cols]
    for _, cols in groups.values():
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

        row.append(fmt(metrics["unknowns"]))
        row.append("N/A")

        for select, cols in groups.values():
            counts = select(pdata)
            for key, _ in cols:
                row.append(r"\multirow{2}{*}{" + str(counts[key]) + "}")

        lines.append(" & ".join(row) + r" \\")

        # Lifted
        row = ["", "Lifted"]
        metrics = pdata["lifted"]
        for key, _ in METHOD_COLUMNS["lifted"]:
            row.append(fmt(metrics[key]))

        row.append(fmt(metrics["unknowns"]))
        row.append(str(global_timeouts(problem, pdata)))

        for _, cols in groups.values():
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

def main(folder, objectives=False):
    return generate_table(process(folder), objectives)

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
    parser.add_argument(
        "--no-objectives",
        action="store_false",
        help=f"Also compare the values of the objective function ('{OBJECTIVE_GROUP}' columns)."
    )
    args = parser.parse_args()

    table = main(args.folder, args.no_objectives)

    if args.output is None:
        print(table)
    else:
        with open(args.output, "w") as f:
            f.write(table)
