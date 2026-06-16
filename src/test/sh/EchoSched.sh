#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/test-classes/random-EchoSched -m src/test/resources/problems/EchoSched/JSP0.mzn src/test/resources/problems/EchoSched/12-12-0-1_7-modified.dzn -p time:3 -p energy:3 -p precedence:2

java -jar target/test-run.jar -d target/test-classes/random-EchoSched -o target/test-classes/random-EchoSched -m src/test/resources/problems/EchoSched/JSP0.mzn -p 'time:1..max(time)' -p 'energy:min(energy)..max(energy)' -p precedence:0..13
