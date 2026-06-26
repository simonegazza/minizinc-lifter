#!/bin/bash
#SBATCH --job-name=test_array
#SBATCH --array=1-100%10
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=00:20:00
#SBATCH --output=logs/%x_%A_%a.out
#SBATCH --error=logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --chdir=~/minizinc-lifter/
#SBATCH --mail-type=FAIL
#SBATCH --mail-user=simone.gazza@monash.edu
#SBATCH --mail-type=ARRAY_TASKS
#SBATCH --nodelist=critical001

set -euo pipefail

shopt -s nullglob

TARGET="target/problems/$1/$SLURM_ARRAY_TASK_ID"

for model in $TARGET/*.mzn; do
	echo $model >> chain.txt
	minizinc --solver solutions.huub \
		--disable-warnings \
		--statistics \
		--compiler-statistics \
		--disable-warnings \
		--time-limit 60000 \
		--json-stream \
		--output-mode json \
		--output-detailed-timing \
		--output-time \
		--output-objective \
		--output-output-item \
		--verbose \
		--canonicalize \
		model >> chain.txt 2>&1
done

for data in $TARGET/*.dzn; do
	echo $data >> one-by-one.txt
	minzinc --solver solutions.huub \
		--disable-warnings \
		--statistics \
		--compiler-statistics \
		--disable-warnings \
		--time-limit 60000 \
		--json-stream \
		--output-mode json \
		--output-detailed-timing \
		--output-time \
		--output-objective \
		--output-output-item \
		--verbose \
		--canonicalize \
		$1 -d $data >> one-by-one.txt 2>&1
done
