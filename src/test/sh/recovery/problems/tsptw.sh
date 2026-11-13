#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/tsptw -m src/test/resources/problems/tsptw/tsptw.mzn src/test/resources/problems/tsptw/n20w160.001.dzn -p late:5

java -jar target/test-run.jar -d target/recover/results/tsptw -o target/recover/results/tsptw -m src/test/resources/problems/tsptw/tsptw.mzn -p 'early:min(early)..max(early)' -p 'late:min(late)..max(late)' -p 'duration:min(duration)..max(duration)'
