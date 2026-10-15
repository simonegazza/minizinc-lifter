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

def generate_flat_status_table(data):
    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")

    lines.append(r"\begin{tabular}{cc|cc|cccc|cccc}")
    lines.append(r"\hhline{~~|~~|~~~~|~~~~}")

    lines.append(
        r"\multirow{2}{*}{\centering Problem} & "
        r"\multirow{2}{*}{\centering Inter Instance Variation} & "
        r"\multicolumn{2}{c|}{Flat Time} & "
        r"\multicolumn{4}{c|}{Status IINGL} & "
        r"\multicolumn{4}{c}{Status Original}\\"
    )

    lines.append(r"\hhline{~~|~~|~~~~|~~~~}")

    lines.append(
        r"& & IINGL & Original & "
        r"SAT & OPT & UNSAT & UNK & "
        r"SAT & OPT & UNSAT & UNK\\"
    )

    lines.append(r"\hhline{--|--|----|----}")

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
                problem_data["flatTime"]["chain"][i],
                problem_data["flatTime"]["1by1"][i],
            ])

            # Statuses per percentage
            chain_status = problem_data["statuses"]["chain"][i]
            row.extend([
                chain_status.get("SATISFIABLE", 0),
                chain_status.get("OPTIMAL_SOLUTION", 0),
                chain_status.get("UNSATISFIABLE", 0),
                chain_status.get("UNKNOWN", 0),
            ])

            onebyone_status = problem_data["statuses"]["1by1"][i]
            row.extend([
                onebyone_status.get("SATISFIABLE", 0),
                onebyone_status.get("OPTIMAL_SOLUTION", 0),
                onebyone_status.get("UNSATISFIABLE", 0),
                onebyone_status.get("UNKNOWN", 0),
            ])

            lines.append(" & ".join(str(value) for value in row) + r"\\")

        lines.append(r"\hline")

    lines.append(r"\end{tabular}")
    lines.append(
        r"\caption{Flat solving time and status distribution per perturbation percentage for all benchmark problems.}"
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
