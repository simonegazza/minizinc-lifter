#!/bin/bash
#SBATCH --job-name=mzn_iingl
#SBATCH --array=1,2,5,10,20,50%1
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=5:00:00
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
# $SLURM_ARRAY_TASK_ID is the percentage

TARGET="target/iingl/problems/$1/$2/$SLURM_ARRAY_TASK_ID"

CHAIN_MODEL="/minizinc-lifter/$TARGET/chain.mzn"
ONE_BY_ONE_MODEL="/minizinc-lifter/$3"

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
        --fzn-flag "-t 300s" \
        "$ONE_BY_ONE_MODEL" -d "/minizinc-lifter/$data" \
        >> "$TARGET/one-by-one.txt" 2>&1

    echo "Done for $data"
done

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
    --fzn-flag "-t 300s" \
    "$CHAIN_MODEL" >> "$TARGET/chain.txt" 2>&1

echo "Done for $CHAIN_MODEL"
