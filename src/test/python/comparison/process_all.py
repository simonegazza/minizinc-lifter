import argparse, json
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

        del soft_processed["statuses"]
        del lifted_processed["statuses"]
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
