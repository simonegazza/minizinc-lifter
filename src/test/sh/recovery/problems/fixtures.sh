#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/fixtures -m src/test/resources/problems/fixtures/cp-model-new.mzn -m src/test/resources/problems/fixtures/coffee_table.dzn -p WMin -p HMin

java -jar target/test-run.jar -d target/recover/results/fixtures -o target/recover/results/fixtures -m src/test/resources/problems/fixtures/cp-model-new.mzn -p 'WMin:WMin - abs(WMin) div 10..WMin + abs(WMin) div 10' -p 'HMin:HMin - abs(HMin) div 10..HMin + abs(HMin) div 10' -p 'minFix:minFix - abs(minFix) div 10..minFix + abs(minFix) div 10' -p '_NFix:min(_NFix)..max(_NFix)' -p 'WFix:min(WFix)..max(WFix)' -p 'HFix:min(HFix)..max(HFix)'
