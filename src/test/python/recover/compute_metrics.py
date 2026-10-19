import argparse, json, statistics, sys
from collections import Counter

# Time limit (in seconds) of each execution of a solver
TIME_LIMIT = 60
# Tolerance (in seconds) over the time limit before warning
TOLERANCE = 1

def capped_solve_time(problem_name, run, iteration):
    """
    Search time of an iteration, capped at the time limit of its solver
    executions: the main solver and, if it did not decide (the iteration was
    completed by another solver), the recovery solver. A time over the limit
    (plus a tolerance) is reported as a warning.
    """
    executions = 1 if iteration["solverUsed"] in ("solutions.huub", "error") else 2
    limit = TIME_LIMIT * executions
    solve_time = iteration["solveTime"]
    if solve_time > limit + TOLERANCE:
        print(
            f"Warning: {problem_name}, {run.get('dataFile')}, iteration {iteration['iteration']}: "
            f"search time {solve_time:.2f}s over the limit of {limit}s",
            file=sys.stderr,
        )
    return min(solve_time, limit)

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

        # Runtime: only the time spent by the solvers in the search (as reported
        # by MiniZinc), without the lifting, the generation of the models and
        # the flattening. It is recorded only by the runs executed with the
        # "--statistics" of MiniZinc: for older runs it is not available.
        has_solve_time = all("totalSolveTime" in r["statistics"] for r in runs)
        if not has_solve_time:
            print(f"{problem_name}: no search time recorded, runtime not available", file=sys.stderr)

        # Search time of each iteration, capped at the time limit, and of each
        # run (sum of its iterations)
        if has_solve_time:
            iteration_times = [
                [capped_solve_time(problem_name, r, it) for it in r["statistics"]["iterations"]]
                for r in runs
            ]
            duration = [sum(times) for times in iteration_times]
        else:
            duration = None

        if has_solve_time:
            total_solver_runtime = sum(duration)
            avg_solver_runtime = total_solver_runtime / sum(len(times) for times in iteration_times)
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

        # Distances of the repairs of the runs that found a solution, computed
        # by recover.py: Hamming (number of changed parameters) and Distance
        # (mean Gower distance |k - k'| / (u - l) over the changed parameters)
        hammings = [
            r["statistics"]["hamming"]
            for r in runs
            if r["statistics"].get("hamming") is not None
        ]
        distances = [
            r["statistics"]["distance"]
            for r in runs
            if r["statistics"].get("distance") is not None
        ]
        if not any("hamming" in r["statistics"] for r in runs):
            print(
                f"Warning: {problem_name}: no Hamming/Distance in the input, "
                "regenerate it with recover.py (or collect.sh)",
                file=sys.stderr,
            )

        # Flattening time of each run (sum over its MiniZinc executions)
        flat_times = [
            r["statistics"]["totalFlatTime"]
            for r in runs
            if r["statistics"].get("totalFlatTime") is not None
        ]

        # MiniZinc status of the last execution of each run (a run without
        # status ended with an error)
        statuses = Counter(r["statistics"].get("status") or "ERROR" for r in runs)

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

            "avgTotalDuration": round(statistics.geometric_mean(duration), 2) if duration else None,
            "minTotalDuration": min(duration) if duration else None,
            "maxTotalDuration": max(duration) if duration else None,

            "minObjective": min(objectives) if objectives else None,
            "medianObjective": statistics.median(objectives) if objectives else None,
            "maxObjective": max(objectives) if objectives else None,
            "medianHamming": round(float(statistics.median(hammings)), 2) if hammings else None,
            "meanDistance": round(float(statistics.mean(distances)), 4) if distances else None,

            "statuses": dict(sorted(statuses.items())),

            "minFlatTime": min(flat_times) if flat_times else None,
            "meanFlatTime": round(statistics.geometric_mean(flat_times), 2) if flat_times else None,
            "maxFlatTime": max(flat_times) if flat_times else None,

            "totalSolverRuntime": round(total_solver_runtime, 2) if has_solve_time else None,
            "avgTotalSolverRuntime": round(avg_total_solver_runtime, 2) if has_solve_time else None,
            "avgSolverRuntimePerIteration": round(avg_solver_runtime, 2) if has_solve_time else None,
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
