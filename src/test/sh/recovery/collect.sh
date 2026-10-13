#!/usr/bin/env sh

# Collects the statistics of the feasibility recovery (target/recover/results/)
# and generates the metrics and the LaTeX table.

set -e

python3 ./src/test/python/recover/recover.py target/recover/results/ target/recover/run.json
python3 ./src/test/python/recover/compute_metrics.py target/recover/run.json target/recover/metrics.json
python3 ./src/test/python/recover/generate_table.py target/recover/metrics.json target/recover/table.tex
