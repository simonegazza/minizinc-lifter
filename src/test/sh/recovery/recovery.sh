#!/usr/bin/env sh

# Runs the whole feasibility recovery experiment locally (java and minizinc
# must be in the PATH). On a cluster, use src/test/sh/recovery/run.sbatch and
# then src/test/sh/recovery/collect.sh.

set -e

mkdir -p target/recover/results/

for e in src/test/sh/recovery/problems/*.sh; do
    name=$(basename "$e")
    sh $e | tee "target/recover/results/$name.txt"
done

sh src/test/sh/recovery/collect.sh
