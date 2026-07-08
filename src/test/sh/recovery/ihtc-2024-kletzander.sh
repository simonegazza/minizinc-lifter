#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/ihtc-2024-kletzander -m src/test/resources/problems/ihtc-2024-kletzander/model4_opt.mzn -m src/test/resources/problems/ihtc-2024-kletzander/i07.dzn -p due_day:5 -p capacity:3

java -jar target/test-run.jar -d target/recover/results/ihtc-2024-kletzander -o target/recover/results/ihtc-2024-kletzander -m src/test/resources/problems/ihtc-2024-kletzander/model4_opt.mzn -p due_day:0..14 -p 'max_surgery:{0,360,480}' -p release_day:1..13 -p capacity:2..4 -p 'max_ot:{0,480,600,720}'
