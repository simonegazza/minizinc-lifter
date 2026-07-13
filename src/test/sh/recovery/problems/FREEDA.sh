#!/usr/bin/env sh

java -jar target/test-run.jar -o target/recover/results/FREEDA -m src/test/resources/problems/FREEDA/freeda-model-v0.3.mzn -d src/test/resources/problems/FREEDA/test-data -p comReq:MIN_RBOUNDS..MAX_RBOUNDS -p depReq:MIN_RBOUNDS..MAX_RBOUNDS -p nodeCap:MIN_RBOUNDS..MAX_RBOUNDS -p linkCap:MIN_RBOUNDS..MAX_RBOUNDS -p 'cost:0..10' -p 'costBudget:1..5000' -p 'carb:0..10' -p 'carbBudget:1..10000' -p 'energy:1..1000' -p 'energy_dependency:0..100'
