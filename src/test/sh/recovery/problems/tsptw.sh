#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/tsptw -m src/test/resources/problems/tsptw/tsptw.mzn src/test/resources/problems/tsptw/n20w160.001.dzn -p late:5

java -jar target/test-run.jar -d target/recover/results/tsptw -o target/recover/results/tsptw -m src/test/resources/problems/tsptw/tsptw.mzn -p early:0..272 -p late:70..500 -p duration:0..55
