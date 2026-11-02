import json, argparse, os
from pathlib import Path

# ==========================
# TABLE CONFIGURATION
# ==========================

# Label for the first column and for the two compared methods
DIFF_LABEL = "Diff"
# (in the order of the columns: solving each instance independently, then IINGL)
METHOD_LABELS = ("Independently", r"\iingl")

# Each group: (data key, group title, ratio key or None, ratio label or None)
# The ratio column, if present, is placed inside the group after the two methods.
MAIN_TABLE_GROUPS = [
    ("solveTime",           "Mean Time",           "speedupOver1by1",       "Speedup"),
    ("solveTimeCumulative", "Cumulative Time",     "speedupOver1by1Cumulative", "Speedup"),
    ("failuresCumulative",  "Cumulative Failures", "failureReductionRatio", "Ratio"),
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
    _, _, ratio_key, _ = group
    return 2 + (1 if ratio_key else 0)

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

    # Sub-headers: methods, plus ratio label where present
    subs = [""]
    for _, _, ratio_key, ratio_label in groups:
        subs.extend(METHOD_LABELS)
        if ratio_key:
            subs.append(ratio_label)
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
        for key, _, ratio_key, _ in groups:
            values.append(data[key]["1by1"][i])
            values.append(data[key]["chain"][i])
            if ratio_key:
                values.append(data[ratio_key][i])
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
    chain_statuses = present_statuses(data, "chain")
    onebyone_statuses = present_statuses(data, "1by1")
    rule = lambda char: (
        rf"\hhline{{{char * 2}|{char * 2}|{char * 2}|{char * len(onebyone_statuses)}"
        rf"|{char * len(chain_statuses)}}}"
    )

    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")

    lines.append(
        r"\begin{tabular}{cc|cc|cc|" + "c" * len(onebyone_statuses)
        + "|" + "c" * len(chain_statuses) + "}"
    )
    lines.append(rule("~"))

    lines.append(
        r"\multirow{2}{*}{\centering Problem} & "
        r"\multirow{2}{*}{\centering Diff} & "
        r"\multicolumn{2}{c|}{Flat Time} & "
        r"\multicolumn{2}{c|}{Flat Time Share} & "
        rf"\multicolumn{{{len(onebyone_statuses)}}}{{c|}}{{Status Independently}} & "
        rf"\multicolumn{{{len(chain_statuses)}}}{{c}}{{Status \iingl}}\\"
    )

    lines.append(rule("~"))

    lines.append(
        r"& & Independently & \iingl & Independently & \iingl & "
        + " & ".join(title for _, title in onebyone_statuses + chain_statuses)
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
            row.extend([
                problem_data["flatTime"]["1by1"][i],
                problem_data["flatTime"]["chain"][i],
            ])

            # Flat shares (percentages) per percentage
            row.extend(
                fmt_share(problem_data["flatShare"][type_name][i])
                for type_name in ("1by1", "chain")
            )

            # Statuses per percentage
            onebyone_status = problem_data["statuses"]["1by1"][i]
            row.extend(onebyone_status.get(status, 0) for status, _ in onebyone_statuses)

            chain_status = problem_data["statuses"]["chain"][i]
            row.extend(chain_status.get(status, 0) for status, _ in chain_statuses)

            lines.append(" & ".join(str(value) for value in row) + r"\\")

        lines.append(r"\hline")

    lines.append(r"\end{tabular}")
    lines.append(
        r"\caption{Flattening time and final status of the \iingl experiments for each "
        r"perturbation rate. \emph{Flat Time} is the flattening time of each chain of 20 "
        r"instances, \ie of the lifted model once for \iingl and of the 20 instances of "
        r"the original model, summed, for the baseline, aggregated over the five chains "
        r"by geometric mean; \emph{Flat Time Share} is the fraction of the overall time "
        r"(flattening and search), summed over the 100 instances of each configuration, "
        r"spent in flattening by each approach; the statuses count the 100 instances of "
        r"each configuration.}"
    )
    lines.append(r"\label{tab:flat-status-results}")
    lines.append(r"\end{table*}")

    return "\n".join(lines)

def main(data):
    result = {
        problem: generate_table(problem, problem_data, MAIN_TABLE_GROUPS, "main")
        for problem, problem_data in data.items()
    }
    return result, generate_flat_status_table(data)

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

    tables, flat_status_table = main(round_floats(data))

    output_dir = Path(args.output)
    output_dir.mkdir(exist_ok=True)

    for name, table in tables.items():
        with open(output_dir / f"{name}.tex", "w") as f:
            f.write(table)

    with open(os.path.join(output_dir, "flat_status_table.tex"), "w") as f:
        f.write(flat_status_table)
