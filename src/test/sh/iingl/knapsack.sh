#!/usr/bin/env sh

java -jar target/iingl-runner.jar -m src/test/resources/problems/knapsack/k.mzn -d target/problems/knapsack -p 'size:min(size)..max(size)' -p 'value:min(value)..max(value)'
