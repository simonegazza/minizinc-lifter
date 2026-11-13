#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/EchoSched -m src/test/resources/problems/EchoSched/JSP0.mzn -m src/test/resources/problems/EchoSched/12-12-0-1_7-modified.dzn -p time:3 -p energy:3 -p precedence:2

java -jar target/test-run.jar -d target/recover/results/EchoSched -o target/recover/results/EchoSched -m src/test/resources/problems/EchoSched/JSP0.mzn -p 'time:min(time)..max(time)' -p 'energy:min(energy)..max(energy)' -p 'precedence:min(precedence)..max(precedence)'
