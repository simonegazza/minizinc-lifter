#!/usr/bin/env sh

java -jar target/test-randomizer.jar -o target/recover/results/ihtc-2024-kletzander -m src/test/resources/problems/ihtc-2024-kletzander/model4_opt.mzn -m src/test/resources/problems/ihtc-2024-kletzander/i07.dzn -p due_day:5 -p capacity:3

java -jar target/test-run.jar -d target/recover/results/ihtc-2024-kletzander -o target/recover/results/ihtc-2024-kletzander -m src/test/resources/problems/ihtc-2024-kletzander/model4_opt.mzn -p 'due_day:min(due_day)..max(due_day)' -p 'max_surgery:min(max_surgery)..max(max_surgery)' -p 'release_day:min(release_day)..max(release_day)' -p 'capacity:min(capacity)..max(capacity)' -p 'max_ot:min(max_ot)..max(max_ot)'
