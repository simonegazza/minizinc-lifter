#!/usr/bin/env sh

set -e

sh ./mvnw package

java -jar target/iingl-generator.jar -o target/problems/ -a 3

for e in src/test/sh/iingl/*.sh; do
    name=$(basename "$e")
    sh $e | tee "target/problems/$name.txt"
done

python3 src/test/python/iingl.py ./target/problems ./target/problems/result.json
