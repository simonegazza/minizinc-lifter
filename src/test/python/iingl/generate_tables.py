import json, argparse, os
from pathlib import Path

# ==========================
# TABLE CONFIGURATION
# ==========================

# Select which metrics appear in the normal table
MAIN_TABLE_KEYS = [
    ("solveTime", "Mean Time"),
    ("solveTimeCumulative", "Cumulative Time"),
    #("failures", "Failures"),
    ("failuresCumulative", "Cumulative Failures"),
    #("peakDepth", "Peak Depth"),
    #("cpPropagatorCallsCumulative", "CP Calls Cumulative"),
    #("cpPropagatorCalls", "CP Propagator Calls"),
]

# Select which metrics appear in the cumulative table
CUMULATIVE_TABLE_KEYS = [
    ("solveTimeCumulative", "Cumulative Time"),
    ("failuresCumulative", "Cumulative Failures"),
]

# Select whether speedup is included
INCLUDE_SPEEDUP = True
INCLUDE_CUMULATIVE_SPEEDUP = True

# Select statuses to show
STATUS_KEYS = [
    ("SATISFIABLE", "SAT"),
    ("OPTIMAL_SOLUTION", "OPT"),
    ("UNSATISFIABLE", "UNSAT"),
    ("UNKNOWN", "UNK"),
]


DIFFS = ["1\\%", "2\\%", "5\\%", "10\\%", "20\\%", "50\\%"]

def latex_header(cumulative=False):
    if not cumulative:
        tabular = "c|" + "|".join(["cc"] * len(MAIN_TABLE_KEYS))

        if INCLUDE_SPEEDUP:
            tabular += "|c"

        header = r"""\begin{table*}[t]
\centering
"""
        header += rf"\begin{{tabular}}{{{tabular}}}" + "\n"
        #header += r"\hhline{~|" + "~" * (len(MAIN_TABLE_KEYS) * 2) + ("|~~" if INCLUDE_SPEEDUP else "") + "}" + "\n"
        header += r"    \multirow{2}{*}{\centering Diff} &" + "\n"

        groups = []
        for _, name in MAIN_TABLE_KEYS:
            groups.append(rf"    \multicolumn{{2}}{{c|}}{{{name}}}")

        if INCLUDE_SPEEDUP:
            groups.append(r"    \multirow{2}{*}{Speedup}")

        header += " &\n".join(groups) + r" \\" + "\n"
        header += r"\hhline{~" + "~" * (len(MAIN_TABLE_KEYS) * 2) + ("~" if INCLUDE_SPEEDUP else "") + "}" + "\n"
        header += "& "

        subheaders = []
        for _ in MAIN_TABLE_KEYS:
            #subheaders.extend(["Chain", "Assumptions", "Individual"])
            subheaders.extend(["Chain", "Original"])
        #if INCLUDE_SPEEDUP:
        #    #subheaders.extend(["Over assumptions", "Over individual"])
        #    subheaders.extend(["Over individual"])

        header += " & ".join(subheaders) + r" \\" + "\n"
        header += r"\hhline{-" + "--" * len(MAIN_TABLE_KEYS)
        if INCLUDE_SPEEDUP:
            header += "-"
        header += "}\n"

        return header

    else:
        groups = []
        for _, name in CUMULATIVE_TABLE_KEYS:
            groups.append(rf"\multicolumn{{2}}{{c|}}{{{name}}}")
        if INCLUDE_CUMULATIVE_SPEEDUP:
            groups.append(r"\multicolumn{1}{c}{Speedup Cumulative}")

        tabular = "c|" + "|".join(["cc"] * len(CUMULATIVE_TABLE_KEYS))
        if INCLUDE_CUMULATIVE_SPEEDUP:
            tabular += "|cc"

        header = r"""
\begin{table*}[t]
\centering
"""
        header += rf"\begin{{tabular}}{{{tabular}}}" + "\n"
        header += r"\hhline{~|" + "~"*(len(CUMULATIVE_TABLE_KEYS) * 2) + ("|~" if INCLUDE_CUMULATIVE_SPEEDUP else "") + "}" + "\n"
        header += r"\multirow{2}{*}{\centering Diff} & "
        header += " & ".join(groups) + r" \\" + "\n"
        header += r"\hhline{~|" + "~"*(len(CUMULATIVE_TABLE_KEYS) * 2) + ("|~" if INCLUDE_CUMULATIVE_SPEEDUP else "") + "}" + "\n"
        header += "& "

        subs = []
        for _ in CUMULATIVE_TABLE_KEYS:
            #subs.extend(["Chain", "Assumptions", "Individual"])
            subs.extend(["Chain", "Original"])
        if INCLUDE_CUMULATIVE_SPEEDUP:
            #subs.extend(["Over Assumptions", "Over Individual"])
            subs.extend(["Over Original"])

        header += " & ".join(subs) + r" \\" + "\n"
        header += r"\hhline{-|" + "---|" * len(CUMULATIVE_TABLE_KEYS)
        if INCLUDE_CUMULATIVE_SPEEDUP:
            header += "--"
        header += "}"
        header += "\n"

        return header

def latex_footer(problem, cumulative=False):
    suffix = "cumulative" if cumulative else "main"
    return rf"""
\end{{tabular}}
\caption{{\iingl results for {problem}.}}
\label{{tab:iingl-{problem}-{suffix}}}
\end{{table*}}
"""

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

def generate_main_table(problem, data):
    lines = []
    lines.append(latex_header(False))

    for i, diff in enumerate(DIFFS):
        values = [diff]
        for key, _ in MAIN_TABLE_KEYS:
            values.append(data[key]["chain"][i])
            #values.append(data[key]["assumptions"][i])
            values.append(data[key]["1by1"][i])
        if INCLUDE_SPEEDUP:
            #values.append(data["speedupOverAssumptions"][i])
            values.append(data["speedupOver1by1"][i])
        lines.append(row(values))

    lines.append(latex_footer(problem, False))

    return "\n".join(lines)

def generate_cumulative_table(problem, data):
    lines = []
    lines.append(latex_header(True))
    for i, diff in enumerate(DIFFS):
        values = [diff]
        for key, _ in CUMULATIVE_TABLE_KEYS:
            values.append(data[key]["chain"][i])
            #values.append(data[key]["assumptions"][i])
            values.append(data[key]["1by1"][i])
        if INCLUDE_CUMULATIVE_SPEEDUP:
            #values.append(data["speedupOverAssumptionsCumulative"][i])
            values.append(data["speedupOver1by1Cumulative"][i])
        lines.append(row(values))

    lines.append(latex_footer(problem, True))

    return "\n".join(lines)

def generate_flat_status_table(data):
    lines = []

    lines.append(r"\begin{table*}[t]")
    lines.append(r"\centering")

    lines.append(r"\begin{tabular}{cc|cc|cccc|cccc}")
    lines.append(r"\hhline{~~|~~|~~~~|~~~~}")

    lines.append(
        r"\multirow{2}{*}{\centering Problem} & "
        r"\multirow{2}{*}{\centering Diff} & "
        r"\multicolumn{2}{c|}{Flat Time} & "
        r"\multicolumn{4}{c|}{Status Chain} & "
        #r"\multicolumn{4}{c|}{Status Assumptions} & "
        r"\multicolumn{4}{c}{Status Individual}\\"
    )

    lines.append(r"\hhline{~~|~~|~~~~|~~~~}")

    lines.append(
        #r"& & Chain & Assumptions & Individual & "
        r"& & Chain & Individual & "
        r"SAT & OPT & UNSAT & UNK & "
        #r"SAT & OPT & UNSAT & UNK & "
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
                #problem_data["flatTime"]["assumptions"][i],
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

            # assumptions_status = problem_data["statuses"]["assumptions"][i]
            # row.extend([
            #     assumptions_status.get("SATISFIABLE", 0),
            #     assumptions_status.get("OPTIMAL_SOLUTION", 0),
            #     assumptions_status.get("UNSATISFIABLE", 0),
            #     assumptions_status.get("UNKNOWN", 0),
            # ])

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
        problem : (
            generate_main_table(problem, problem_data),
            generate_cumulative_table(problem, problem_data)
        )
        for problem, problem_data in data.items()
    }

    return result, generate_flat_status_table(data)

def round_floats(obj, digits=3):
    if isinstance(obj, float):
        return f"{obj:.{digits}f}" #round(obj, digits)
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

    all_tables, flat_status_table = main(round_floats(data))

    output_dir = Path(args.output)
    output_dir.mkdir(exist_ok=True)

    for name, tables in all_tables.items():
        t, t_cumulative = tables
        with open(output_dir / f"{name}.tex", "w") as f:
            f.write(t)
            #f.write("\n\n")
            #f.write(t_cumulative)

    with open(os.path.join(output_dir, "flat_status_table.tex"), "w") as f:
        f.write(flat_status_table)
