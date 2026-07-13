#!/bin/bash
#SBATCH --job-name=mzn_lifting_comparison
#SBATCH --array=0-49%2
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=00:02:00
#SBATCH --output=logs/%x_%A_%a.out
#SBATCH --error=logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -xeuo pipefail

base="$1"
dir="target/comparison/$base"
model="$dir/${base}_soft.mzn"
result="target/comparison/results"

[[ -f "$model" ]]

dzn=( "$dir"/unsat_dzn/"${SLURM_ARRAY_TASK_ID}"*.dzn )
[[ -e "${dzn[0]}" ]]

instance=$(basename "${dzn[0]}" .dzn)

mkdir -p $result
outfile="$result/soft_${base}_${instance}.txt"
errfile="$result/soft_${base}_${instance}.err"

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
	-m "$model" \
	-d "${dzn[0]}" \
	--fzn-flags "-t 60s" \
	> "$outfile" 2>"$errfile"
