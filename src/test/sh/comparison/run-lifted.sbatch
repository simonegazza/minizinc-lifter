#!/bin/bash
#SBATCH --job-name=mzn_lifting_comparison
#SBATCH --array=0-49%2
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=00:20:00
#SBATCH --output=logs/%x_%A_%a.out
#SBATCH --error=logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -xeuo pipefail

base="$1"
dir="target/comparison/$base"
model="$dir/${base}_lifted.mzn"
result="target/comparison/results"

[[ -f "$model" ]]

dzn=( "$dir"/unsat_dzn/"${SLURM_ARRAY_TASK_ID}"*.dzn )
[[ -e "${dzn[0]}" ]]

instance=$(basename "${dzn[0]}" .dzn)

workdir="$result/lifted_${base}_${instance}"
mkdir -p "$workdir"

cp "$model" "$workdir/model_0.mzn"

declare -a exclude=()

iter=0

while true; do
    current="$workdir/model_${iter}.mzn"
    output="$workdir/output_${iter}.txt"

    podman run --rm \
        -w /workdir \
        -v ../huub:/huub \
        -v ../huub/share/minizinc/huub/huub.msc:/usr/share/minizinc/solvers/huub.msc \
        -v "$dir":/workdir \
        docker.io/minizinc/minizinc:edge-noble \
        minizinc \
        --solver solutions.huub \
        --disable-warnings \
        --compiler-statistics \
        --statistics \
        --output-output-item \
        --output-objective \
        --canonicalize \
        --output-mode json \
        --json-stream \
        --output-detailed-timing \
        -m "$current" \
        -d "${dzn[0]}" \
        --fzn-flags "-t 60s" \
        > "$output" 2>"$workdir/stderr_${iter}.txt"

    status=$(grep '"type": "status"' "$output" | sed -n 's/.*"status": "\([^"]*\)".*/\1/p')

    if [[ "$status" != "UNSATISFIABLE" ]]; then
        echo "Finished with status $status"
        exit 0
    fi

    mapfile -t core < <(
        grep '"type": "core"' "$output" |
        sed -E 's/.*assumed\[([0-9]+)\].*/\1/g' |
        tr ',' '\n'
    )

    if [[ ${#core[@]} -eq 0 ]]; then
        echo "UNSAT but empty core!"
        exit 1
    fi

    exclude+=( "${core[@]}" )

    next="$workdir/model_$((iter+1)).mzn"
    cp "$current" "$next"

    list=$(IFS=,; echo "${exclude[*]}")

    perl -0pi -e \
        "s/EXCLUDE_ME\s*=\s*\[[^]]*\];/EXCLUDE_ME = [$list];/s" \
        "$next"

    ((++iter))

done
