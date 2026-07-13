#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/random-aircraft -m src/test/resources/problems/aircraft-disassembly/aircraft.mzn -m src/test/resources/problems/aircraft-disassembly/B737NG-600-04-Anon.json.dzn -p loc_cap:5

java -jar target/test-run.jar -d target/recover/results/random-aircraft -o target/recover/results/random-aircraft -m src/test/resources/problems/aircraft-disassembly/aircraft.mzn -p 'resource_cost:{750,1000,1250}' -p sreq:0..2 -p unavailable_resource:1..21 -p unavailable_start:0..1800 -p unavailable_end:160..1900 -p 'maxDiff:{50,100}' -p occupancy:1..2 -p loc_cap:1..69
