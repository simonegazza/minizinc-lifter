import json, argparse, os
from pathlib import Path

# ==========================
# TABLE CONFIGURATION
# ==========================

# Select which metrics appear in the normal table
MAIN_TABLE_KEYS = [
    #("solveTime", "Solve Time (s)"),
    ("solveTimeCumulative", "Solve Time Cumulative"),
    #("failures", "Failures"),
    ("failuresCumulative", "Failures Cumulative"),
    #("peakDepth", "Peak Depth"),
    #("cpPropagatorCallsCumulative", "CP Calls Cumulative"),
    #("cpPropagatorCalls", "CP Propagator Calls"),
]

# Select which metrics appear in the cumulative table
CUMULATIVE_TABLE_KEYS = [
    ("solveTimeCumulative", "Cumulative Solve Time"),
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


DIFFS = ["0\\%", "1\\%", "2\\%", "5\\%", "10\\%", "20\\%", "50\\%"]

def latex_header(cumulative=False):
    if not cumulative:
        tabular = "c|" + "|".join(["ccc"] * len(MAIN_TABLE_KEYS))

        if INCLUDE_SPEEDUP:
            tabular += "|cc"

        header = r"""
\begin{table*}[t]
\centering
"""
        header += rf"\begin{{tabular}}{{{tabular}}}" + "\n"
        header += r"\hhline{~|" + "~" * (len(MAIN_TABLE_KEYS) * 3) + ("|~~" if INCLUDE_SPEEDUP else "") + "}" + "\n"
        header += r"\multirow{3}{*}{\centering Diff} & "

        groups = []
        for _, name in MAIN_TABLE_KEYS:
            groups.append(rf"\multicolumn{{3}}{{c|}}{{{name}}}")

        if INCLUDE_SPEEDUP:
            groups.append(r"\multicolumn{{2}}{{c|}}{Speedup}")

        header += " & ".join(groups) + r"\\" + "\n"
        header += r"\hhline{~|" + "~" * (len(MAIN_TABLE_KEYS) * 3) + ("|~" if INCLUDE_SPEEDUP else "") + "}" + "\n"
        header += "& "

        subheaders = []
        for _ in MAIN_TABLE_KEYS:
            subheaders.extend(["Chain", "Assumptions", "Individual"])
        if INCLUDE_SPEEDUP:
            subheaders.extend(["Over assumptions", "Over individual"])

        header += " & ".join(subheaders) + r"\\" + "\n"
        header += r"\hhline{-|" + "--|" * len(MAIN_TABLE_KEYS)
        if INCLUDE_SPEEDUP:
            header += "--"
        header += "}"
        header += "\n"

        return header

    else:
        groups = []
        for _, name in CUMULATIVE_TABLE_KEYS:
            groups.append(rf"\multicolumn{{3}}{{c|}}{{{name}}}")
        if INCLUDE_CUMULATIVE_SPEEDUP:
            groups.append(r"\multicolumn{2}{c}{Speedup Cumulative}")

        tabular = "c|" + "|".join(["ccc"] * len(CUMULATIVE_TABLE_KEYS))
        if INCLUDE_CUMULATIVE_SPEEDUP:
            tabular += "|cc"

        header = r"""
\begin{table*}[t]
\centering
"""
        header += rf"\begin{{tabular}}{{{tabular}}}" + "\n"
        header += r"\hhline{~|" + "~"*(len(CUMULATIVE_TABLE_KEYS) * 3) + ("|~" if INCLUDE_CUMULATIVE_SPEEDUP else "") + "}" + "\n"
        header += r"\multirow{2}{*}{\centering Diff} & "
        header += " & ".join(groups) + r"\\" + "\n"
        header += r"\hhline{~|" + "~"*(len(CUMULATIVE_TABLE_KEYS) * 3) + ("|~" if INCLUDE_CUMULATIVE_SPEEDUP else "") + "}" + "\n"
        header += "& "

        subs = []
        for _ in CUMULATIVE_TABLE_KEYS:
            subs.extend(["Chain", "Assumptions", "Individual"])
        if INCLUDE_CUMULATIVE_SPEEDUP:
            subs.extend(["Over baseline", "Over individual"])

        header += " & ".join(subs) + r"\\" + "\n"
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
            if float(v) > 1000:
                v = str(int(float(v) / 1000)) + "k"
                vs.append(v)
            else:
                vs.append(str(v))
        except:
            vs.append(str(v))
    return " & ".join(v for v in vs) + r"\\"

def generate_main_table(problem, data):
    lines = []
    lines.append(latex_header(False))

    for i, diff in enumerate(DIFFS):
        values = [diff]
        for key, _ in MAIN_TABLE_KEYS:
            values.append(data[key]["chain"][i])
            values.append(data[key]["assumptions"][i])
            values.append(data[key]["1by1"][i])
        if INCLUDE_SPEEDUP:
            values.append(data["speedupOverAssumptions"][i])
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
            values.append(data[key]["assumptions"][i])
            values.append(data[key]["1by1"][i])
        if INCLUDE_CUMULATIVE_SPEEDUP:
            values.append(data["speedupOverAssumptionsCumulative"][i])
            values.append(data["speedupOver1by1Cumulative"][i])
        lines.append(row(values))

    lines.append(latex_footer(problem, True))

    return "\n".join(lines)

# def generate_flat_status_table(data):
#     lines = []

#     # Header
#     lines.append(r"\begin{table*}[t]")
#     lines.append(r"\centering")
#     lines.append(r"\begin{tabular}{c|cc|cccc|cccc}")
#     lines.append(r"\hhline{~|~~|~~~~|~~~~}")

#     lines.append(
#         r"\multirow{2}{*}{\centering Problem} & "
#         r"\multicolumn{2}{c|}{Flat Time} & "
#         r"\multicolumn{4}{c|}{Status Chain} & "
#         r"\multicolumn{4}{c|}{Status Baseline} & "
#         r"\multicolumn{4}{c}{Status Individual}\\"
#     )

#     lines.append(r"\hhline{~|~~|~~~~|~~~~}")

#     lines.append(
#         r"& Chain & Baseline & Individual & "
#         r"SAT & OPT & UNSAT & UNK & "
#         r"SAT & OPT & UNSAT & UNK\\"
#     )

#     lines.append(r"\hhline{-|--|----|----}")


#     # Body
#     for problem, problem_data in data.items():
#         row = [
#             problem,
#             problem_data["flatTime"]["chain"],
#             problem_data["flatTime"]["assumptions"],
#             problem_data["flatTime"]["1by1"]
#         ]


#         # Status Chain
#         chain_status = problem_data["statuses"].get("chain", {})

#         row.extend([
#             chain_status.get("SATISFIABLE", 0),
#             chain_status.get("OPTIMAL_SOLUTION", 0),
#             chain_status.get("UNSATISFIABLE", 0),
#             chain_status.get("UNKNOWN", 0),
#         ])

#         # Status assumptions
#         onebyone_status = problem_data["statuses"].get("assumptions", {})

#         row.extend([
#             onebyone_status.get("SATISFIABLE", 0),
#             onebyone_status.get("OPTIMAL_SOLUTION", 0),
#             onebyone_status.get("UNSATISFIABLE", 0),
#             onebyone_status.get("UNKNOWN", 0),
#         ])

#         # Status One-by-one
#         onebyone_status = problem_data["statuses"].get("1by1", {})

#         row.extend([
#             onebyone_status.get("SATISFIABLE", 0),
#             onebyone_status.get("OPTIMAL_SOLUTION", 0),
#             onebyone_status.get("UNSATISFIABLE", 0),
#             onebyone_status.get("UNKNOWN", 0),
#         ])


#         # Convert everything to string
#         lines.append(" & ".join(str(value) for value in row) + r"\\")


#     # Footer
#     lines.append(r"\hhline{-|--|----|----}")
#     lines.append(r"\end{tabular}")
#     lines.append(r"\caption{Flat solving time and status distribution for all benchmark problems.}")
#     lines.append(r"\label{tab:flat-status-results}")
#     lines.append(r"\end{table*}")

#     return "\n".join(lines)

def main(input_file, output_dir):
    output_dir = Path(output_dir)
    output_dir.mkdir(exist_ok=True)

    with open(input_file) as f:
        data = json.load(f)

    for problem, problem_data in data.items():
        #main_table = generate_main_table(problem, problem_data)
        cumulative_table = generate_cumulative_table(problem, problem_data)

        with open(output_dir / f"{problem}.tex", "w") as f:
            #f.write(main_table)
            #f.write("\n\n")
            f.write(cumulative_table)

    # flat_status_table = generate_flat_status_table(data)

    # with open(os.path.join(output_dir, "flat_status_table.tex"), "w") as f:
    #     f.write(flat_status_table)


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

    main(args.input, args.output)
