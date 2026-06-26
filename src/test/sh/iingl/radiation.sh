#!/usr/bin/env sh

java -jar target/iingl-saver.jar -m src/test/resources/problems/radiation/radiation.mzn -d target/problems/radiation -p 'Intensity:min(Intensity)..max(Intensity)'