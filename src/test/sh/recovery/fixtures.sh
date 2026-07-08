#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/fixtures -m src/test/resources/problems/fixtures/cp-model-new.mzn -m src/test/resources/problems/fixtures/coffee_table.dzn -p WMin -p HMin

java -jar target/test-run.jar -d target/recover/results/fixtures -o target/recover/results/fixtures -m src/test/resources/problems/fixtures/cp-model-new.mzn -p WMin:100..2200 -p HMin:100..2200 -p minFix:5..8 -p _NFix:12..24 -p WFix:0..180 -p HFix:0..145
