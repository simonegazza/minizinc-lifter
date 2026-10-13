#!/bin/bash
#SBATCH --job-name=mzn_recovery
#SBATCH --array=0-5%1
#SBATCH --cpus-per-task=1
#SBATCH --mem=8G
#SBATCH --time=03:00:00
#SBATCH --output=target/logs/%x_%A_%a.out
#SBATCH --error=target/logs/%x_%A_%a.err
#SBATCH --requeue
#SBATCH --nodelist=critical001

set -euo pipefail

# ------------------------------------------------------------------
# INPUT
# ------------------------------------------------------------------
# $SLURM_ARRAY_TASK_ID selects the problem, i.e. the script in
# src/test/sh/recovery/problems/ that generates its randomized instances and
# runs the feasibility recovery on each of them.
# The list is explicit, so that the mapping does not depend on the locale.
problems=(EchoSched FREEDA aircraft fixtures ihtc-2024-kletzander tsptw)

name="${problems[$SLURM_ARRAY_TASK_ID]}"
script="src/test/sh/recovery/problems/$name.sh"

# The feasibility recovery (Java) runs MiniZinc, so both are needed in the same
# container: the MiniZinc image of the other experiments is used, and the Java
# runtime is taken from the Maven image used to run the other Java tools,
# mounted (read-only) in /java. Both images are based on Ubuntu noble.
JAVA_IMAGE="docker.io/library/maven:3.9-eclipse-temurin-25"
podman image exists "$JAVA_IMAGE" || podman pull "$JAVA_IMAGE"

mkdir -p target/recover/results

# ------------------------------------------------------------------
# RUN
# ------------------------------------------------------------------
# The statistics (stdout) are collected by src/test/sh/recovery/collect.sh,
# the logs (stderr) are kept apart. The output files are truncated, so that a
# requeued task does not append to the previous run.
podman run --rm \
    -v ../huub:/huub \
    -v ../huub/share/minizinc/huub/huub.msc:/usr/share/minizinc/solvers/huub.msc \
    -v ./:/minizinc-lifter \
    --mount type=image,source="$JAVA_IMAGE",destination=/java \
    -w /minizinc-lifter \
    docker.io/minizinc/minizinc:edge-noble \
    sh -c 'PATH="/java/opt/java/openjdk/bin:$PATH" sh "$0"' "$script" \
    > "target/recover/results/$name.sh.txt" \
    2> "target/recover/results/$name.sh.err"

echo "Done for $name"
