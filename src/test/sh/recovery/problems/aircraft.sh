#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/random-aircraft -m src/test/resources/problems/aircraft-disassembly/aircraft.mzn -m src/test/resources/problems/aircraft-disassembly/B737NG-600-04-Anon.json.dzn -p loc_cap:5

java -jar target/test-run.jar -d target/recover/results/random-aircraft -o target/recover/results/random-aircraft -m src/test/resources/problems/aircraft-disassembly/aircraft.mzn -p 'resource_cost:min(resource_cost)..max(resource_cost)' -p 'sreq:min(sreq)..max(sreq)' -p 'unavailable_resource:min(unavailable_resource)..max(unavailable_resource)' -p 'unavailable_start:min(unavailable_start)..max(unavailable_start)' -p 'unavailable_end:min(unavailable_end)..max(unavailable_end)' -p 'maxDiff:min(maxDiff)..max(maxDiff)' -p 'occupancy:min(occupancy)..max(occupancy)' -p 'loc_cap:min(loc_cap)..max(loc_cap)'
