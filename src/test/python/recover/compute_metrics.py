import argparse, json, statistics
from collections import Counter

def main():
    parser = argparse.ArgumentParser(
        description="Process benchmark results JSON."
    )
    parser.add_argument(
        "input_file",
        help="Path to the input JSON file",
    )
    parser.add_argument(
        "output_file",
        nargs="?",
        help="Path to the output file (defaults to stdout)",
    )
    args = parser.parse_args()

    with open(args.input_file, "r") as f:
        data = json.load(f)

    rows = {}
    for problem_name, problem_data in data.items():
        runs = problem_data["runs"]

        core_variables_per_run = [
            sum(len(it["coreVariables"]) for it in r["statistics"]["iterations"])
            for r in runs]

        iteration_per_run = list(len(r["statistics"]["iterations"]) for r in runs)

        duration = list(r["statistics"]["totalDurationMs"] / 1000.0 for r in runs)

        total_solver_runtime = sum(
            sum(it["durationMs"] for it in r["statistics"]["iterations"]) / 1000.0
            for r in runs
        )

        avg_solver_runtime = total_solver_runtime / len(list(1 for r in runs for _ in r["statistics"]["iterations"]))
        avg_total_solver_runtime = total_solver_runtime / len(runs)

        # Value of the objective function (distance from the original
        # parameters) of the runs that found a solution
        objectives = [
            r["statistics"]["_objective"]
            for r in runs
            if r["statistics"].get("_objective") is not None
        ]

        # Timeouts of the main solver (UNKNOWN): the iteration is completed by
        # the recovery solver, so it is recorded with a different solver (at
        # most once per run, since the procedure ends there)
        main_solver_timeouts = sum(
            1
            for r in runs
            for it in r["statistics"]["iterations"]
            if it["solverUsed"] not in ("solutions.huub", "error")
        )

        final_state_counts = Counter(
            r["statistics"]["finalState"]
            for r in runs
        )

        row = {
            "originalParameterModified": runs[0]["statistics"].get("originalParameterModified"),
            "liftedParameters": runs[0]["statistics"].get("liftedParameters"),

            "meanCoreVariables": round(statistics.mean(core_variables_per_run), 2),
            "minCoreVariables": min(core_variables_per_run),
            "maxCoreVariables": max(core_variables_per_run),

            "meanIterations": round(statistics.mean(iteration_per_run), 2),
            "minIterations": min(iteration_per_run),
            "medianIterations": statistics.median(iteration_per_run),
            "mainSolverTimeouts": main_solver_timeouts,
            "runs": len(runs),
            "maxIterations": max(iteration_per_run),

            "avgTotalDuration": round(statistics.geometric_mean(duration), 2),
            "minTotalDuration": min(duration),
            "maxTotalDuration": max(duration),

            "minObjective": min(objectives) if objectives else None,
            "medianObjective": statistics.median(objectives) if objectives else None,
            "maxObjective": max(objectives) if objectives else None,

            "totalSolverRuntime": round(total_solver_runtime, 2),
            "avgTotalSolverRuntime": round(avg_total_solver_runtime, 2),
            "avgSolverRuntimePerIteration": round(avg_solver_runtime, 2),
        }

        for state, count in sorted(final_state_counts.items()):
            row[f"count_{state}"] = count

        rows.update({problem_name.replace(".sh.txt", "") : row})

    output = json.dumps(rows, indent=2)

    if args.output_file:
        with open(args.output_file, "w") as f:
            f.write(output)
    else:
        print(output)


if __name__ == "__main__":
    main()
