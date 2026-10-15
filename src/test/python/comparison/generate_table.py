#!/usr/bin/env python3
import os
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

# Final statuses of each method, with their column titles, in the order of the
# table: only the statuses of at least one instance are shown
STATUSES = [
    ("OPTIMAL_SOLUTION", "OPT"),
    ("SATISFIABLE", "SAT"),
    ("UNKNOWN", "UNK"),
    ("UNSATISFIABLE", "UNSAT"),
]
STATUS_TITLE = "Status"

def fmt(x):
    """Pretty formatting for numbers."""
    if isinstance(x, int):
        return str(x)
    if isinstance(x, float):
        # values up to 10^5 fit in the column without scientific notation
        if abs(x) >= 1e6:
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


def collect_statuses(results):
    """Statuses (and titles) of at least one instance, for any method."""
    present = set()
    for pdata in results.values():
        for method in ("soft", "lifted"):
            present |= {s for s, n in pdata[method]["statusCounts"].items() if n > 0}
    known = [s for s, _ in STATUSES]
    return [(s, t) for s, t in STATUSES if s in present] + [(s, s) for s in sorted(present) if s not in known]


def generate_table(results):
    timeouts_title, timeouts_cols = TIMEOUTS
    statuses = collect_statuses(results)
    method_names = {"soft": "CMPCS", "lifted": "Lifted"}

    alignment = (
        "c|c|"
        + "|".join("r" for _ in METHOD_COLUMNS["soft"])
        + "|" + "r" * len(timeouts_cols)
        + "|" + "r" * len(statuses)
        + "|r"
    )

    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        rf"\begin{{tabular}}{{{alignment}}}",
        r"\multirow{2}{*}{Problem} & "
        r"\multirow{2}{*}{Method} & "
        + " &\n".join(rf"\multirow{{2}}{{*}}{{{title}}}" for _, title in METHOD_COLUMNS["soft"])
        + " &\n" + rf"\multicolumn{{{len(timeouts_cols)}}}{{c|}}{{{timeouts_title}}}"
        + " &\n" + rf"\multicolumn{{{len(statuses)}}}{{c|}}{{{STATUS_TITLE}}}"
        + " &\n" + r"\multirow{2}{*}{Borda} \\",
        " & " * (2 + len(METHOD_COLUMNS["soft"]))
        + " & ".join([title for title, _ in timeouts_cols] + [title for _, title in statuses])
        + r" & \\",
        r"\hline",
    ]

    totals = {"soft": 0, "lifted": 0}
    for problem, pdata in results.items():
        for method in ("soft", "lifted"):
            metrics = pdata[method]
            row = [
                rf"\multirow{{2}}{{*}}{{{problem}}}" if method == "soft" else "",
                method_names[method],
            ]
            row += [fmt(metrics[key]) for key, _ in METHOD_COLUMNS[method]]
            row.append(fmt(metrics["unknowns"]))
            # The global budget exists only for Lifted
            row.append("N/A" if method == "soft" else str(global_timeouts(problem, pdata)))
            row += [str(metrics["statusCounts"].get(status, 0)) for status, _ in statuses]
            row.append(f"{metrics['borda']:.2f}")
            totals[method] += metrics["borda"]
            lines.append(" & ".join(row) + r" \\")
        if problem != list(results)[-1]:
            lines.append(r"\hline")

    lines.append(r"\end{tabular}")

    # Borda count over all the problems (sum of the rows) in the caption
    table = "\n".join(lines)
    table += (
        "\n\\caption{Results of the comparison with CMPCS. "
        f"Over all the problems, the Borda count is {totals['lifted']:.2f} for Lifted "
        f"and {totals['soft']:.2f} for CMPCS.}}\n"
        "\\label{tab:comparison}\n"
        "\\end{table*}"
    )

    return table

def generate_flat_status_table(results):
    """
    Table with the flattening time of each method (minimum, geometric mean and
    maximum over the instances; for Lifted, summed over the iterations of each
    instance) and the number of instances for each final status (for Lifted,
    UNSAT means that the global budget expired).
    """
    method_names = {"soft": "CMPCS", "lifted": "Lifted"}
    present = set()
    for pdata in results.values():
        for method in ("soft", "lifted"):
            present |= {s for s, n in pdata[method]["allStatusCounts"].items() if n > 0}
    known = [s for s, _ in STATUSES]
    statuses = [(s, t) for s, t in STATUSES if s in present] + [(s, s) for s in sorted(present) if s not in known]

    lines = [
        r"\begin{table}[t]",
        r"\centering",
        r"\begin{tabular}{c|c|rrr|" + "r" * len(statuses) + "}",
        r"\multirow{2}{*}{Problem} & \multirow{2}{*}{Method} & \multicolumn{3}{c|}{Flat Time} & "
        + rf"\multicolumn{{{len(statuses)}}}{{c}}{{Statuses}} \\",
        r" & & Min & Mean & Max & " + " & ".join(t for _, t in statuses) + r" \\",
        r"\hline",
    ]
    problems = list(results)
    for problem, pdata in results.items():
        for method in ("soft", "lifted"):
            flat = pdata[method]["flatTime"]
            row = [
                rf"\multirow{{2}}{{*}}{{{problem}}}" if method == "soft" else "",
                method_names[method],
                f"{flat['min']:.2f}", f"{flat['mean']:.2f}", f"{flat['max']:.2f}",
            ] + [str(pdata[method]["allStatusCounts"].get(s, 0)) for s, _ in statuses]
            lines.append(" & ".join(row) + r" \\")
        if problem != problems[-1]:
            lines.append(r"\hline")
    lines += [
        r"\end{tabular}",
        r"\caption{Flattening time (in seconds; for Lifted, summed over the iterations of each instance) "
        r"and final status of the instances in the comparison with CMPCS "
        r"(for Lifted, UNSAT denotes the instances stopped by the global budget).}",
        r"\label{tab:comparison-flat-status}",
        r"\end{table}",
    ]
    return "\n".join(lines)


def main(folder):
    results = process(folder)
    return generate_table(results), generate_flat_status_table(results)

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

    table, flat_status_table = main(args.folder)

    if args.output is None:
        print(table)
    else:
        with open(args.output, "w") as f:
            f.write(table)

    # Flattening times and statuses, next to the table (or in the current
    # folder if the table is printed)
    flat_status = os.path.join(os.path.dirname(args.output) if args.output else ".", "flat_status_table.tex")
    with open(flat_status, "w") as f:
        f.write(flat_status_table)
    print(f"Flattening times and statuses written to {flat_status}", file=sys.stderr)
