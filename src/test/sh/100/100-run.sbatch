#!/bin/bash
#SBATCH --job-name=mzn_iingl_100
#SBATCH --array={1,2,5,10,20,50}%1
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=06:00:00
#SBATCH --output=target/logs/%x_%A_%a.out
#SBATCH --error=target/logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -euo pipefail
shopt -s nullglob

# ------------------------------------------------------------------
# INPUT
# ------------------------------------------------------------------
TARGET="target/100/problems/$1/$SLURM_ARRAY_TASK_ID"

CHAIN_MODEL="/minizinc-lifter/$TARGET/chain.mzn"
ASSUMPTIONS_MODEL="/minizinc-lifter/$2"
ONE_BY_ONE_MODEL="/minizinc-lifter/$3"

# ------------------------------------------------------------------
# CHAIN RUN
# ------------------------------------------------------------------
echo "$CHAIN_MODEL" > "$TARGET/chain.txt"

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
    --fzn-flag "-t 60s" \
    "$CHAIN_MODEL" >> "$TARGET/chain.txt" 2>&1

echo "Done for $CHAIN_MODEL"

# ------------------------------------------------------------------
# ONE-BY-ONE WITH ASSUMPTIONS
# ------------------------------------------------------------------
for data in "$TARGET"/*.dzn; do
    echo "$data" >> "$TARGET/assumptions.txt"

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
        --fzn-flag "-t 60s" \
        "$ASSUMPTIONS_MODEL" -d "/minizinc-lifter/$data" \
        >> "$TARGET/assumptions.txt" 2>&1

    echo "Done for $data"
done

# ------------------------------------------------------------------
# ONE-BY-ONE RUN
# ------------------------------------------------------------------
for data in "$TARGET"/*.dzn; do
    echo "$data" >> "$TARGET/one-by-one.txt"

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
        --fzn-flag "-t 60s" \
        "$ONE_BY_ONE_MODEL" -d "/minizinc-lifter/$data" \
        >> "$TARGET/one-by-one.txt" 2>&1

    echo "Done for $data"
done
