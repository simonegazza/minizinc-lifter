import argparse, json, statistics
from pathlib import Path

from combine_soft import main as combine_soft
from combine_lifted import main as combine_lifted
from process_soft import main as process_soft
from process_lifted import main as process_lifted

STATUSES = ["UNKNOWN", "SATISFIABLE", "OPTIMAL_SOLUTION", "UNSATISFIABLE"]

PROBLEMS = ["latin", "TAP", "RSBP", "RCPSP"]

def first_soft_better(lifted_status, soft_status):
    if lifted_status not in STATUSES:
        raise ValueError(f"Not a proper status: {lifted_status}")
    if soft_status not in STATUSES:
        raise ValueError(f"Not a proper status: {soft_status}")

    if lifted_status == "UNKNOWN":
        if soft_status == "UNKNOWN":
            return True
        else:
            return False
    elif lifted_status == "SATISFIABLE":
        if soft_status == "UNKNOWN" or soft_status == "SATISFIABLE":
            return True
        elif soft_status == "UNSATISFIABLE":
            print("Status are discordant")
            return
        else:
            return False
    elif lifted_status == "OPTIMAL_SOLUTION":
        if soft_status == "UNSATISFIABLE":
            print("Status are discordant")
            return
        return True
    elif lifted_status == "UNSATISFIABLE":
        if soft_status == "OPTIMAL_SOLUTION" or soft_status == "SATISFIABLE":
            return "MUSes"
        return True

def first_hard_better(lifted_status, soft_status):
    if lifted_status == "UNKNOWN":
        return False
    elif lifted_status == "SATISFIABLE":
        if soft_status == "UNKNOWN":
            return True
        if soft_status == "UNSATISFIABLE":
            print("Status are discordant")
            return
        return False
    elif lifted_status == "OPTIMAL_SOLUTION":
        if soft_status == "UNSATISFIABLE":
            print("Status are discordant")
            return
        if soft_status == "OPTIMAL_SOLUTION":
            return False
        return True
    elif lifted_status == "UNSATISFIABLE":
        if soft_status == "OPTIMAL_SOLUTION" or soft_status == "SATISFIABLE":
            return "MUSes"
        return False

def compare_objectives(lifted_status, lifted_objective, soft_objective):
    """
    Compares the objective (the L1 distance from the original parameters,
    to be minimised) of Lifted against CMPCS.
    """
    # The lifted procedure was stopped by the global time limit while
    # removing cores (as for the "Global TO" of the statuses)
    if lifted_status == "UNSATISFIABLE":
        return "globalTO"
    # A method without a solution is worse than one with a solution; if both
    # have no solution, none of the two is better
    if lifted_objective is None and soft_objective is None:
        return "equal"
    if lifted_objective is None:
        return "worse"
    if soft_objective is None:
        return "better"
    if lifted_objective < soft_objective:
        return "better"
    if lifted_objective > soft_objective:
        return "worse"
    return "equal"

def borda_points(status, objective, time, other_status, other_objective, other_time):
    """
    Points of a method against the other on an instance, following the Borda
    count of the MiniZinc Challenge for optimization problems (the objective,
    the L1 distance from the original parameters, is minimized): a method that
    does not solve the instance scores 0; otherwise it scores 1 if it gives a
    better answer, i.e. (in this order) it solves the instance and the other
    does not, it proves optimality and the other does not, or its objective is
    better; 0 if the answer is worse; and, if the answers are
    indistinguishable, other_time / (other_time + time).
    """
    def solved(s, o):
        return s in ("OPTIMAL_SOLUTION", "SATISFIABLE") and o is not None

    if not solved(status, objective):
        return 0
    if not solved(other_status, other_objective):
        return 1
    optimal = status == "OPTIMAL_SOLUTION"
    other_optimal = other_status == "OPTIMAL_SOLUTION"
    if optimal != other_optimal:
        return 1 if optimal else 0
    if objective != other_objective:
        return 1 if objective < other_objective else 0
    if time + other_time == 0:
        return 0.5
    return other_time / (other_time + time)

def borda(lifted, soft):
    """Borda points of Lifted and CMPCS summed over the instances of a problem."""
    instances = list(zip(
        lifted["statuses"], lifted["objectives"], lifted["instanceSolveTimes"],
        soft["statuses"], soft["objectives"], soft["instanceSolveTimes"]))
    lifted_points = sum(borda_points(*i[:3], *i[3:]) for i in instances)
    soft_points = sum(borda_points(*i[3:], *i[:3]) for i in instances)
    return lifted_points, soft_points

def status_counts(statuses, exclude=()):
    """Number of instances for each final status."""
    counts = {}
    for s in statuses:
        if s not in exclude:
            counts[s] = counts.get(s, 0) + 1
    return counts

def main(main_folder : Path):
    result = {}
    for problem in PROBLEMS:
        soft_combined = combine_soft(main_folder, problem)
        lifted_combined = combine_lifted(main_folder, problem)

        soft_processed = process_soft(soft_combined)
        lifted_processed = process_lifted(lifted_combined)

        if len(soft_processed["statuses"]) != len(lifted_processed["statuses"]):
            raise ValueError("Not every instance was reproduced")

        soft_processed["unknowns"] = len([s for s in soft_processed["statuses"] if s == "UNKNOWN"])
        lifted_processed["unknowns"] = len([s for s in lifted_processed["statuses"] if s == "UNKNOWN"])

        soft_better_status = [
            first_soft_better(lifted_status, soft_status)
            for (lifted_status, soft_status) in zip(
                lifted_processed["statuses"],
                soft_processed["statuses"])
        ]

        hard_better_status = [
            first_hard_better(lifted_status, soft_status)
            for (lifted_status, soft_status) in zip(
                lifted_processed["statuses"],
                soft_processed["statuses"])
        ]

        objective_comparison = [
            compare_objectives(lifted_status, lifted_objective, soft_objective)
            for (lifted_status, lifted_objective, soft_objective) in zip(
                lifted_processed["statuses"],
                lifted_processed["objectives"],
                soft_processed["objectives"])
        ]

        soft_processed["statuses_all"] = list(soft_processed["statuses"])
        lifted_processed["statuses_all"] = list(lifted_processed["statuses"])

        lifted_borda, soft_borda = borda(lifted_processed, soft_processed)
        soft_processed["borda"] = soft_borda
        lifted_processed["borda"] = lifted_borda

        # Final statuses; for Lifted, UNSATISFIABLE means that the global budget
        # expired while removing cores (reported as a global timeout)
        soft_processed["statusCounts"] = status_counts(soft_processed["statuses"])
        lifted_processed["statusCounts"] = status_counts(lifted_processed["statuses"], exclude=("UNSATISFIABLE",))

        del soft_processed["statuses"]
        del lifted_processed["statuses"]
        # All the final statuses and the flattening time of each instance (for
        # the flat/status table)
        for processed in (soft_processed, lifted_processed):
            processed["allStatusCounts"] = status_counts(processed["statuses_all"])
            flat = processed.pop("instanceFlatTimes")
            processed["flatTime"] = {
                "min": min(flat),
                "mean": statistics.geometric_mean(flat),
                "max": max(flat),
            }
            del processed["statuses_all"]

        del soft_processed["instanceSolveTimes"]
        del lifted_processed["instanceSolveTimes"]
        del soft_processed["objectives"]
        del lifted_processed["objectives"]

        result[problem] = {
            "soft" : soft_processed,
            "lifted" : lifted_processed,
            "better" : {
                "total" : len(soft_better_status),
                "soft" : {
                    "better" : len([s for s in soft_better_status if s == True]),
                    "worse" : len([s for s in soft_better_status if s == False]),
                    "MUSes" : len([s for s in soft_better_status if s == "MUSes"]),
                    "discordant" : len([s for s in soft_better_status if s == None])
                },
                "hard" : {
                    "better" : len([s for s in hard_better_status if s == True]),
                    "worse" : len([s for s in hard_better_status if s == False]),
                    "MUSes" : len([s for s in soft_better_status if s == "MUSes"]),
                    "discordant" : len([s for s in hard_better_status if s == None])
                }
            },
            "objective" : {
                key : len([o for o in objective_comparison if o == key])
                for key in ["better", "equal", "worse", "globalTO"]
            }
        }

    return result

if __name__ == "__main__":
    # From the scripts folder you should run:
    # ```python
    # python3 process_all.py ../results
    # ````

    parser = argparse.ArgumentParser()
    parser.add_argument("root", help="Root experiment folder")
    args = parser.parse_args()

    print(json.dumps(
        main(Path(args.root)),
        indent=2,
        ensure_ascii=False))
