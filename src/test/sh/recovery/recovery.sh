#!/usr/bin/env sh

set -e

mkdir -p target/recover/results/

for e in src/test/sh/recovery/problems/*.sh; do
    name=$(basename "$e")
    sh $e | tee "target/recover/results/$name.txt"
done

python3 ./src/test/python/recover/recover.py target/recover/results/ target/recover/run.json
python3 ./src/test/python/recover/compute_metrics.py target/recover/run.json target/recover/metrics.json
python3 ./src/test/python/recover/generate_table.py target/recover/metrics.json target/recover/table.tex
