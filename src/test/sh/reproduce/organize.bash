#!/usr/bin/env bash

set -ueo pipefail

percentages=(1 2 5 10 20)

problems=(gc knapsack mosp radiation)

for problem in ${problems[@]}; do
    for p in ${percentages[@]}; do
        for i in `ls target/reproduce/benchmarks/$problem/$problem-$p-*.dzn`; do
            folder=`echo $i | awk -F"/" '{print $NF }'| awk -F'-' '{print $2"/"$3}'`
            mkdir -p target/reproduce/problems/$problem/$folder
            mv $i target/reproduce/problems/$problem/$folder
        done
    done
    rm -r target/reproduce/benchmarks/$problem/
done

rmdir target/reproduce/benchmarks/
