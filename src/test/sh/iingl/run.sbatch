#!/bin/bash
#SBATCH --job-name=mzn_iingl
#SBATCH --array=1,2,5,10,20,50%1
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=8:00:00
#SBATCH --output=target/logs/%x_%A_%a.out
#SBATCH --error=target/logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -euo pipefail
shopt -s nullglob

# ------------------------------------------------------------------
# INPUT
# ------------------------------------------------------------------
# $1 is the problem name (i.e. the name of the folder in target/iingl/problems/ folder)
# $2 is the repetition id (i.e. the name of the repetition in $1 folder)
# $3 is the original model
# $4 is the assumptions model (lifted model with a single instance, taking the
#    .dzn of one instance at a time), e.g. src/test/resources/iingl/knapsack.mzn.
#    Required only by the assumptions configuration.
# $SLURM_ARRAY_TASK_ID is the percentage
#
# $modes (optional, environment) selects the configurations to run, as a
# space separated list among:
#   one-by-one   original model, one instance at a time
#   assumptions  lifted model, one instance at a time: assumptions are
#                substituted with the instance values and nogoods are
#                discarded between instances (no nogood reuse)
#   chain        lifted model, all instances in a row reusing nogoods
# Default: all of them. To run only the assumptions configuration:
#   sbatch --export=ALL,modes=assumptions src/test/sh/iingl/run.sbatch ...

TARGET="target/iingl/problems/$1/$2/$SLURM_ARRAY_TASK_ID"
modes="${modes:-one-by-one assumptions chain}"

CHAIN_MODEL="/minizinc-lifter/$TARGET/chain.mzn"
ONE_BY_ONE_MODEL="/minizinc-lifter/$3"
ASSUMPTIONS_MODEL="${4:+/minizinc-lifter/$4}"

has_mode() {
    [[ " $modes " == *" $1 "* ]]
}

# Fail before running anything if the assumptions model is missing
if has_mode assumptions && [[ -z "$ASSUMPTIONS_MODEL" ]]; then
    echo "Missing assumptions model (4th argument): set modes to skip the assumptions configuration" >&2
    exit 1
fi

# Same container and solver flags for every configuration; the arguments are
# the time limit followed by the MiniZinc model (and data) arguments
run_minizinc() {
    local time_limit="$1"
    shift

    podman run --rm \
        -v ../huub:/huub \
        -v ../huub/share/minizinc/huub/huub.msc:/usr/share/minizinc/solvers/huub.msc \
        -v ./:/minizinc-lifter \
        docker.io/minizinc/minizinc:edge-noble \
        minizinc \
        --solver solutions.huub \
        --disable-warnings \
        --statistics \
        --compiler-statistics \
        --json-stream \
        --output-mode json \
        --output-time \
        --output-objective \
        --output-output-item \
        --canonicalize \
        --fzn-flag "-t $time_limit" \
        "$@"
}

# ------------------------------------------------------------------
# ONE-BY-ONE RUN
# ------------------------------------------------------------------
if has_mode one-by-one; then
    for data in "$TARGET"/*.dzn; do
        echo "$data" >> "$TARGET/one-by-one.txt"

        run_minizinc 300s "$ONE_BY_ONE_MODEL" -d "/minizinc-lifter/$data" \
            >> "$TARGET/one-by-one.txt" 2>&1

        echo "Done for $data"
    done
fi

# ------------------------------------------------------------------
# ASSUMPTIONS RUN (lifted, no nogood reuse)
# ------------------------------------------------------------------
if has_mode assumptions; then
    for data in "$TARGET"/*.dzn; do
        echo "$data" >> "$TARGET/assumptions.txt"

        run_minizinc 300s "$ASSUMPTIONS_MODEL" -d "/minizinc-lifter/$data" \
            >> "$TARGET/assumptions.txt" 2>&1

        echo "Done for $data"
    done
fi

# ------------------------------------------------------------------
# CHAIN RUN
# ------------------------------------------------------------------
if has_mode chain; then
    echo "$CHAIN_MODEL" > "$TARGET/chain.txt"

    run_minizinc 300s "$CHAIN_MODEL" >> "$TARGET/chain.txt" 2>&1

    echo "Done for $CHAIN_MODEL"
fi
