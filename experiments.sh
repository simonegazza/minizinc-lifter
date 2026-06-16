#!/usr/bin/env sh

set -e

sh ./mvnw clean package

mkdir -p target/results

for e in src/test/sh/*.sh; do
    # GET THE BASE NAME FOR $e AND USE IT AS BELOW
    name=$(basename "$e")
    sh $e | tee "target/results/$name.txt"
done

python3 ./src/test/python/parse.py ./target/results ./target/results/result.json
python3 ./src/test/python/generate_tables.py ./target/results/result.json ./target/results/tables.json
