#!/usr/bin/env bash

set -eoux pipefail

percentages=(1 2 5 10 20)

problems=(graph knapsack mosp radiation)

declare -A models
models[graph]="graph-colouring.mzn"
models[knapsack]="k.mzn"
models[mosp]="mosp.mzn"
models[radiation]="radiation.mzn"

declare -A parameters
parameters[graph]="-p graph"
parameters[knapsack]="-p size -p value"
parameters[mosp]="-p graph"
parameters[radiation]="-p Intensity"

declare -A name
name[graph]="gc"
name[knapsack]="knapsack"
name[mosp]="mosp"
name[radiation]="radiation"

for problem in ${problems[@]}; do
    for p in ${percentages[@]}; do
        java -jar target/iingl-saver.jar -m src/test/resources/problems/$problem/${models[$problem]} -d target/reproduce/problems/${name[$problem]}/$p ${parameters[$problem]}
    done
done
