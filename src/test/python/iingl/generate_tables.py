import json, argparse, os
from pathlib import Path

# ==========================
# TABLE CONFIGURATION
# ==========================

# Label for the first column and for the two compared methods
DIFF_LABEL = "Diff"
# Value of the metrics that cannot be computed (see compute_metrics.py)
ERROR_VALUE = "N/A"
# Compared methods, in the order of the columns: (data key, column label).
# No Reuse is the lifted model solving each instance alone, with the assumptions
# of that instance and without reusing the nogoods: it is "N/A" where the
# assumptions configuration was not run.
METHODS = [
    ("1by1", "Independently"),
    ("assumptions", "No Reuse"),
    ("chain", r"\iingl"),
]
METHOD_LABELS = tuple(label for _, label in METHODS)

# Short labels of the methods in the per-problem tables
SHORT_LABELS = {
    "1by1": "Ind.",
    "assumptions": "No Reuse",
    "chain": r"\iingl",
}

# Each group: (data key, group title, [method, ...], [(ratio key, ratio label), ...])
# The ratio columns are placed inside the group after the methods; each ratio is
# the value of a method over the value of \iingl (above 1 when \iingl is better).
MAIN_TABLE_GROUPS = [
    ("solveTime", "Mean Time", ["1by1", "assumptions", "chain"], [
        ("speedupOver1by1", "Speedup"),
    ]),
    ("solveTimeCumulative", "Cumulative Time", ["1by1", "chain"], [
        ("speedupOver1by1Cumulative", "Speedup"),
    ]),
    ("failuresCumulative", "Cumulative Failures", ["1by1", "chain"], [
        ("failureReductionRatio", "Ratio"),
    ]),
]

# Select statuses to show
STATUS_KEYS = [
    ("SATISFIABLE", "SAT"),
    ("OPTIMAL_SOLUTION", "OPT"),
    ("UNSATISFIABLE", "UNSAT"),
    ("UNKNOWN", "UNK"),
]

DIFFS = ["1\\%", "2\\%", "5\\%", "10\\%", "20\\%", "50\\%"]

def group_width(group):
    _, _, methods, ratios = group
    return len(methods) + len(ratios)

def latex_header(groups):
    tabular = "c|" + "|".join("c" * group_width(g) for g in groups)

    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        rf"\begin{{tabular}}{{{tabular}}}",
    ]

    # Group titles
    cells = [rf"\multirow{{2}}{{*}}{{{DIFF_LABEL}}}"]
    for idx, g in enumerate(groups):
        align = "c|" if idx < len(groups) - 1 else "c"
        cells.append(rf"\multicolumn{{{group_width(g)}}}{{{align}}}{{{g[1]}}}")
    lines.append("\n& ".join(cells) + r" \\")

    # Sub-headers: methods, then the ratio labels
    subs = [""]
    for _, _, methods, ratios in groups:
        subs.extend(SHORT_LABELS[method] for method in methods)
        subs.extend(label for _, label in ratios)
    lines.append(" & ".join(subs) + r" \\")
    lines.append(r"\hline")

    return "\n".join(lines)

def latex_footer(problem, suffix):
    return rf"""
\end{{tabular}}
\caption{{\iingl results for {problem}.}}
\label{{tab:iingl-{problem}-{suffix}}}
\end{{table*}}
"""

def generate_table(problem, data, groups, suffix):
    lines = [latex_header(groups)]
    for i, diff in enumerate(DIFFS):
        values = [diff]
        for key, _, methods, ratios in groups:
            values.extend(data[key][method][i] for method in methods)
            values.extend(data[ratio][i] for ratio, _ in ratios)
        lines.append(row(values))
    lines.append(latex_footer(problem, suffix))
    return "\n".join(lines)

def row(values):
    vs = []
    for v in values:
        try:
            if float(v) > 10_000:
                v = str(int(float(v) / 1000)) + "k"
                vs.append(v)
            else:
                vs.append(str(v))
        except:
            vs.append(str(v))
    return " & ".join(v for v in vs) + r" \\"

# Statuses of the flat/status table, with their column titles, in order
STATUS_COLUMNS = [
    ("SATISFIABLE", "SAT"),
    ("OPTIMAL_SOLUTION", "OPT"),
    ("UNSATISFIABLE", "UNSAT"),
    ("UNKNOWN", "UNK"),
]

def present_statuses(data, method):
    """Statuses (and titles) of at least one instance of the method, in any
    problem and percentage: the other statuses have no column."""
    return [
        (status, title)
        for status, title in STATUS_COLUMNS
        if any(
            counts.get(status, 0) > 0
            for problem_data in data.values()
            for counts in problem_data["statuses"][method]
        )
    ]

def fmt_share(value):
    """Percentage with one decimal, or the value itself if not a number."""
    try:
        return f"{float(value):.1f}\\%"
    except ValueError:
        return str(value)

def generate_flat_status_table(data):
    # Status columns of each method: statuses never reached have no column, and a
    # method without any status (e.g. No Reuse never run) has no status group
    status_groups = [
        (method, label, statuses)
        for method, label in METHODS
        if (statuses := present_statuses(data, method))
    ]
    n = len(METHODS)
    rule = lambda char: (
        rf"\hhline{{{char * 2}|{char * n}|{char * n}|"
        + "|".join(char * len(statuses) for _, _, statuses in status_groups)
        + "}"
    )

    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")
    lines.append(r"\resizebox{\textwidth}{!}{%")

    lines.append(
        r"\begin{tabular}{cc|" + "c" * n + "|" + "c" * n + "|"
        + "|".join("c" * len(statuses) for _, _, statuses in status_groups) + "}"
    )
    lines.append(rule("~"))

    titles = [
        r"\multirow{2}{*}{\centering Problem}",
        r"\multirow{2}{*}{\centering Diff}",
        rf"\multicolumn{{{n}}}{{c|}}{{Flat Time}}",
        rf"\multicolumn{{{n}}}{{c|}}{{Flat Time Share}}",
    ]
    for idx, (_, label, statuses) in enumerate(status_groups):
        align = "c|" if idx < len(status_groups) - 1 else "c"
        titles.append(rf"\multicolumn{{{len(statuses)}}}{{{align}}}{{Status {label}}}")
    lines.append(" & ".join(titles) + r"\\")

    lines.append(rule("~"))

    lines.append(
        "& & " + " & ".join(METHOD_LABELS * 2) + " & "
        + " & ".join(title for _, _, statuses in status_groups for _, title in statuses)
        + r"\\"
    )

    lines.append(rule("-"))

    for problem, problem_data in data.items():
        for i, diff in enumerate(DIFFS):

            row = []

            # Print problem name only on the first percentage row
            if i == 0:
                row.append(rf"\multirow{{{len(DIFFS)}}}{{*}}{{{problem}}}")
            else:
                row.append("")

            row.append(diff)

            # Flat times per percentage
            row.extend(problem_data["flatTime"][method][i] for method, _ in METHODS)

            # Flat shares (percentages) per percentage
            row.extend(
                fmt_share(problem_data["flatShare"][method][i]) for method, _ in METHODS
            )

            # Statuses per percentage
            for method, _, statuses in status_groups:
                counts = problem_data["statuses"][method][i]
                # No counts at all: the configuration was not run (N/A, not 0)
                row.extend(
                    counts.get(status, 0) if counts else ERROR_VALUE
                    for status, _ in statuses
                )

            lines.append(" & ".join(str(value) for value in row) + r"\\")

        lines.append(r"\hline")

    lines.append(r"\end{tabular}}")
    lines.append(
        r"\caption{Flattening time and final status of the \iingl experiments for each "
        r"perturbation rate. \emph{Flat Time} is the flattening time of each chain of 20 "
        r"instances, \ie of the lifted model once for \iingl, of the lifted model for "
        r"each of the 20 instances, summed, for No Reuse, and of the 20 instances of "
        r"the original model, summed, for the baseline, aggregated over the five chains "
        r"by geometric mean; \emph{Flat Time Share} is the fraction of the overall time "
        r"(flattening and search), summed over the 100 instances of each configuration, "
        r"spent in flattening by each approach; the statuses count the 100 instances of "
        r"each configuration.}"
    )
    lines.append(r"\label{tab:flat-status-results}")
    lines.append(r"\end{table*}")

    return "\n".join(lines)

# Assumptions table: the lifted model solving each instance alone, substituting
# the assumptions and discarding the nogoods between instances, separates the
# cost of the lifting (Independently vs Lifted) from the benefit of the nogood
# reuse (Lifted vs \iingl).
ASSUMPTIONS_LABELS = ("Indep.", "Lifted", r"\iingl")

# Each group: (data key, group title, [(ratio key, ratio label), ...])
ASSUMPTIONS_GROUPS = [
    ("solveTime", "Mean Time", [
        ("liftingSpeedup", "Lift"),
        ("speedupOverAssumptions", "Nogood"),
    ]),
    ("solveTimeCumulative", "Cumulative Time", [
        ("liftingSpeedupCumulative", "Lift"),
        ("speedupOverAssumptionsCumulative", "Nogood"),
    ]),
    ("failuresCumulative", "Cumulative Failures", [
        ("liftingFailureRatio", "Lift"),
        ("failureReductionRatioOverAssumptions", "Nogood"),
    ]),
]

def has_assumptions(problem_data, i):
    return problem_data["solveTime"]["assumptions"][i] != ERROR_VALUE

def generate_assumptions_table(data):
    """Rows only for the problems and percentages where the assumptions configuration was run,
    None if it was never run."""
    rows = {
        problem: [i for i in range(len(DIFFS)) if has_assumptions(problem_data, i)]
        for problem, problem_data in data.items()
    }
    rows = {problem: indices for problem, indices in rows.items() if indices}
    if not rows:
        return None

    lifted_statuses = present_statuses(
        {problem: data[problem] for problem in rows}, "assumptions"
    )
    widths = [len(ASSUMPTIONS_LABELS) + len(ratios) for _, _, ratios in ASSUMPTIONS_GROUPS]
    widths.append(len(lifted_statuses))

    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        r"\resizebox{\textwidth}{!}{%",
        r"\begin{tabular}{cc|" + "|".join("c" * w for w in widths) + "}",
    ]

    titles = [r"\multirow{2}{*}{Problem}", rf"\multirow{{2}}{{*}}{{{DIFF_LABEL}}}"]
    for (_, title, _), width in zip(ASSUMPTIONS_GROUPS, widths):
        titles.append(rf"\multicolumn{{{width}}}{{c|}}{{{title}}}")
    titles.append(rf"\multicolumn{{{widths[-1]}}}{{c}}{{Status Lifted}}")
    lines.append(" & ".join(titles) + r" \\")

    subs = ["", ""]
    for _, _, ratios in ASSUMPTIONS_GROUPS:
        subs.extend(ASSUMPTIONS_LABELS)
        subs.extend(label for _, label in ratios)
    subs.extend(title for _, title in lifted_statuses)
    lines.append(" & ".join(subs) + r" \\")
    lines.append(r"\hline")

    for problem, indices in rows.items():
        problem_data = data[problem]
        for n, i in enumerate(indices):
            values = [
                rf"\multirow{{{len(indices)}}}{{*}}{{{problem}}}" if n == 0 else "",
                DIFFS[i],
            ]
            for key, _, ratios in ASSUMPTIONS_GROUPS:
                values.extend(
                    problem_data[key][method][i]
                    for method in ("1by1", "assumptions", "chain")
                )
                values.extend(problem_data[ratio][i] for ratio, _ in ratios)
            statuses = problem_data["statuses"]["assumptions"][i]
            values.extend(statuses.get(status, 0) for status, _ in lifted_statuses)
            lines.append(row(values))
        lines.append(r"\hline")

    lines.append(r"\end{tabular}}")
    lines.append(
        r"\caption{Cost of the lifting and benefit of the nogood reuse. \emph{Indep.} solves each instance "
        r"with the original model, \emph{Lifted} with the lifted model substituting "
        r"the assumptions of that instance and discarding the nogoods between "
        r"instances, \iingl with the lifted model reusing the nogoods along the "
        r"chain. \emph{Lift} is the ratio Indep./Lifted (below 1 when the lifting "
        r"costs), \emph{Nogood} the ratio Lifted/\iingl (above 1 when the nogood "
        r"reuse helps): their product is the overall ratio Indep./\iingl.}"
    )
    lines.append(r"\label{tab:iingl-assumptions}")
    lines.append(r"\end{table*}")

    return "\n".join(lines)

def main(data):
    result = {
        problem: generate_table(problem, problem_data, MAIN_TABLE_GROUPS, "main")
        for problem, problem_data in data.items()
    }
    return result, generate_flat_status_table(data), generate_assumptions_table(data)

def round_floats(obj, digits=3):
    if isinstance(obj, float):
        return f"{obj:.{digits}f}"
    if isinstance(obj, dict):
        return {key: round_floats(value, digits) for key, value in obj.items()}
    if isinstance(obj, list):
        return [round_floats(item, digits) for item in obj]

    return obj

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate LaTeX tables from iingl JSON results."
    )
    parser.add_argument("input", help="Input JSON file")
    parser.add_argument(
        "-o",
        "--output",
        default="tables",
        help="Output directory"
    )
    args = parser.parse_args()

    with open(args.input) as f:
        data = json.load(f)

    tables, flat_status_table, assumptions_table = main(round_floats(data))

    output_dir = Path(args.output)
    output_dir.mkdir(exist_ok=True)

    for name, table in tables.items():
        with open(output_dir / f"{name}.tex", "w") as f:
            f.write(table)

    with open(os.path.join(output_dir, "flat_status_table.tex"), "w") as f:
        f.write(flat_status_table)

    if assumptions_table is not None:
        with open(output_dir / "assumptions_table.tex", "w") as f:
            f.write(assumptions_table)
