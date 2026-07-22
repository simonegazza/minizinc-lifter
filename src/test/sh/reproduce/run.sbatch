#!/bin/bash
#SBATCH --job-name=mzn_lifting_reproduce
#SBATCH --array=0-99%2
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=00:05:00
#SBATCH --output=target/logs/%x_%A_%a.out
#SBATCH --error=target/logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -euo pipefail
shopt -s nullglob

# ------------------------------------------------------------------
# INPUT
# ------------------------------------------------------------------
problem=$1
percentage=$2

TARGET="target/reproduce/problems/$problem/$percentage/$SLURM_ARRAY_TASK_ID"

CHAIN_MODEL="/minizinc-lifter/$TARGET/chain.mzn"
ONE_BY_ONE_MODEL="/minizinc-lifter/$2"

# ------------------------------------------------------------------
# REPRODUCIBILITY RUN
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
        --time-limit 60000 \
        --json-stream \
        --output-mode json \
        --output-time \
        --output-objective \
        --output-output-item \
        --canonicalize \
        "$ONE_BY_ONE_MODEL" -d "/minizinc-lifter/$data" \
        >> "$TARGET/one-by-one.txt" 2>&1

done
