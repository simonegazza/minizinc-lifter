import argparse, json
from pathlib import Path
from statistics import StatisticsError, geometric_mean

TABLE_METRICS = [
    ("solveTime", "Solve Time"),
    ("failures", "Failures"),
]

METHODS = [
    ("chain", "Chain"),
    ("1by1", "Individual"),
]

def percentage_sort_key(value):
    try:
        return (0, float(str(value).rstrip("%")))
    except ValueError:
        return (1, str(value))

def latex_percentage(value):
    value = str(value)
    return value if value.endswith(r"\%") else f"{value.rstrip('%')}\\%"

def latex_header():
    metric_columns = len(TABLE_METRICS) * len(METHODS)
    column_spec = "c|" + "|".join("cc" for _ in TABLE_METRICS) + "|c"

    groups = [rf"\multicolumn{{2}}{{c|}}{{{label}}}" for _, label in TABLE_METRICS]
    subheaders = [label for _ in TABLE_METRICS for _, label in METHODS]

    return "\n".join([
        r"\begin{table*}[t]",
        r"\centering",
        rf"\begin{{tabular}}{{{column_spec}}}",
        rf"\hhline{{~|{'~' * metric_columns}|~}}",
        r"\multirow{2}{*}{\centering Diff} & "
        + " & ".join(groups)
        + r" & \multirow{2}{*}{\centering Speedup}"
        + r"\\",
        rf"\hhline{{~|{'~' * metric_columns}|~}}",
        r"& " + " & ".join(subheaders) + r"\\",
        rf"\hhline{{-|{'--|' * len(TABLE_METRICS)}-}}",
    ])

def latex_footer(problem):
    return "\n".join([
        r"\end{tabular}",
        rf"\caption{{Results for {problem}.}}",
        rf"\label{{tab:results-{problem}}}",
        r"\end{table*}",
    ])

def format_value(value, digits=3):
    if isinstance(value, int) and not isinstance(value, bool):
        return str(value)
    try:
        number = float(value)
    except (TypeError, ValueError):
        return str(value)

    if number >= 10000:
        return f"{round(number / 1000)}k"
    return f"{round(number, digits):.{digits}f}"

def compute_problem_metrics(percentage_data):
    rows = []
    flat_times = {"chain": [], "1by1": []}
    status_totals = {
        method: dict.fromkeys(
            ("SATISFIABLE", "OPTIMAL_SOLUTION", "UNSATISFIABLE", "UNKNOWN"), 0
        )
        for method in ("chain", "1by1")
    }

    for percentage in sorted(percentage_data, key=percentage_sort_key):
        result = percentage_data[percentage]
        rows.append({
            "percentage": percentage,
            "metrics": {
                metric_name: result.get(metric_name, {})
                for metric_name, _ in TABLE_METRICS
            },
            "speedup": result.get("speedupOver1by1", "N/A"),
        })

        for method in ("chain", "1by1"):
            try:
                flat_times[method].append(float(result.get("flatTime", {})[method]))
            except (KeyError, TypeError, ValueError):
                pass

            method_status = result.get("statuses", {}).get(method, {})
            for status in status_totals[method]:
                status_totals[method][status] += method_status.get(status, 0)

    def combined_flat_time(method):
        try:
            return geometric_mean(flat_times[method])
        except (StatisticsError, ValueError):
            return "N/A"

    return {
        "rows": rows,
        "flatTime": {
            method: combined_flat_time(method) for method in ("chain", "1by1")
        },
        "statuses": status_totals,
    }

def compute_metrics(data):
    return {
        problem: compute_problem_metrics(percentage_data)
        for problem, percentage_data in data.items()
    }

def generate_table(problem, metrics):
    lines = [latex_header()]
    for row in metrics["rows"]:
        values = [latex_percentage(row["percentage"])]
        for metric_name, _ in TABLE_METRICS:
            metric = row["metrics"][metric_name]
            values.extend(metric.get(method, "N/A") for method, _ in METHODS)
        values.append(row["speedup"])
        lines.append(" & ".join(format_value(value) for value in values) + r"\\")

    lines.append(latex_footer(problem))
    return "\n".join(lines)

def generate_flat_status_table(metrics_by_problem):
    lines = [
        r"\begin{table*}[t]",
        r"\centering",
        r"\begin{tabular}{c|cc|cccc|cccc}",
        r"\hhline{~|~~|~~~~|~~~~}",
        r"\multirow{2}{*}{\centering Problem} & "
        r"\multicolumn{2}{c|}{Flat Time} & "
        r"\multicolumn{4}{c|}{Status Chain} & "
        r"\multicolumn{4}{c}{Status Individual}\\",
        r"\hhline{~|~~|~~~~|~~~~}",
        r"& Chain & Individual & "
        r"SAT & OPT & UNSAT & UNK & "
        r"SAT & OPT & UNSAT & UNK\\",
        r"\hhline{-|--|----|----}",
    ]

    for problem, metrics in metrics_by_problem.items():
        row = [
            problem,
            metrics["flatTime"]["chain"],
            metrics["flatTime"]["1by1"],
        ]
        for method in ("chain", "1by1"):
            row.extend(metrics["statuses"][method].values())

        lines.append(" & ".join(format_value(value) for value in row) + r"\\")

    lines.extend([
        r"\end{tabular}",
        r"\caption{Flat solving time and status distribution for all benchmark problems.}",
        r"\label{tab:flat-status-results}",
        r"\end{table*}",
    ])

    return "\n".join(lines)

def main(data):
    metrics_by_problem = compute_metrics(data)
    tables = {
        problem: generate_table(problem, metrics)
        for problem, metrics in metrics_by_problem.items()
    }
    return tables, generate_flat_status_table(metrics_by_problem)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate LaTeX tables from simplified MiniZinc result JSON."
    )
    parser.add_argument("input", help="Simplified result JSON file")
    parser.add_argument("-o", "--output", default="tables", help="Output directory")
    args = parser.parse_args()

    with open(args.input) as source:
        data = json.load(source)

    all_tables, flat_status_table = main(data)

    output_dir = Path(args.output)
    output_dir.mkdir(parents=True, exist_ok=True)

    for name, table in all_tables.items():
        with open(output_dir / f"{name}.tex", "w") as output:
            output.write(table)

    with open(output_dir / "flat_status_table.tex", "w") as output:
        output.write(flat_status_table)
