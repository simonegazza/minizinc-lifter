# MiniZinc Automatic Parameter Lifting
This is a MiniZinc to MiniZinc transpiler designed for parameter lifting. It reads a MiniZinc model, analyzes its parameters, and generates a transformed version of the model where selected parameters are *lifted* into decision variables.

This tool relies on an ANTLR4-generated parser to analyze and rewrite the MiniZinc Abstract Syntax Tree (AST), preserving the original formatting and structure during transformation.

## Prerequisites
- **Java Development Kit (JDK):** Version 25 or higher (specified by the Maven compiler release).
- **Maven:** Version 3.8+ (A Maven Wrapper `mvnw` is included in the project).

## Building
This project utilizes Maven for dependency management, test execution, and building.

To compile the code, run any of the tests (both the code tests and the following tests) and generate the jars, it is necessary to build the project first with:

```bash
./mvnw package
```

# Automatic Feasibility Recovery
This repository also contains the implementation and experimental artifacts for the **Automatic Feasibility Recovery** evaluation presented in our paper. The complete experimental pipeline is automated by the `src/test/sh/recovery/recovery.sh` script, which:

## Prerequisites
The experiments require:
* Java 25
* Maven
* Python >= 3.11

All commands are executed from the **root directory of the repository**.

## Experimental pipeline
We will use the fixture problem as running example for this experimentation. The other model are available in `src/test/sh/recovery`

### Step 1 - Generate randomized benchmark
Random benchmark instances are generated using the `test-randomizer` tool.

```bash
java -jar target/test-randomizer.jar \
    -o target/recover/results/random-fixtures \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    -m src/test/resources/problems/fixtures/coffee_table.dzn \
    -p WMin \
    -p HMin
```

The generated instances are stored as `.dzn` files under:
```text
target/recover/results/<problem>/<repetition>.dzn
```

Each `.dzn` file corresponds to one independent randomized repetition of the experiment.

### Step 2 - Run
The recovery procedure is executed using `test-run.jar`.

For the fixture benchmark:
```bash
java -jar target/test-run.jar \
    -d target/recover/results/fixtures \
    -o target/recover/results/fixtures \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    -p WMin:100..2200 \
    -p HMin:100..2200 \
    -p minFix:5..8 \
    -p _NFix:12..24 \
    -p WFix:0..180 \
    -p HFix:0..145
```

The recovery procedure processes every generated instance independently.

For each repetition, all generated recovery models are stored under:
```text
target/recover/results/<problem>/<repetition>/
```

### Step 3 - Collecting statistics
After all experiments have completed, the raw output is aggregated into a single JSON file:

```bash
python3 ./src/test/python/recover.py target/recover/results/ target/recover/run.json
python3 ./src/test/python/compute_metrics.py target/recover/run.json target/recover/metrics.json
python3 ./src/test/python/generate_table.py target/recover/metrics.json target/recover/table.tex
```

## Directory structure

After the experiment completes, the output directory has the following structure:

```text
target/
├─ test-randomizer.jar
├─ test-run.jar
├─ test-classes/
│   └─ random-<problem>/
│       ├─ 1.dzn
│       ├─ 0002.dzn
│       ├─ ...
│       ├─ 0001/
│       │   ├─ ...
│       │   └─ recovered models
│       ├─ 0002/
│       │   └─ ...
│       └─ ...
└─ results/
    ├─ *.txt
    ├─ result.json
    └─ tables.json
```

# Comparison with CMPCS
This repository also contains the implementation and experimental artifacts used to reproduce the **comparison** experiments presented in our paper.

The experiments compare the proposed approach against the baseline models on four benchmark families:
* latin
* TAP
* RSBP
* RCPSP

Instances can be found in `src/test/resources/comparison/data.tar.xz`.

## Prerequisites

The experiments require:
* Java 25
* Maven
* Podman (or Docker, with equivalent commands)
* Slurm workload manager (`sbatch`) for executing the experiments on a computing cluster
* Python (>=3.11) to collect statistics and generate the final tables
* `xz` (or `unxz`) to extract the benchmark archive

All commands below assume that they are executed from the **root directory of this repository**.

## Step 1 - Extract the benchmark data

Create the output directory and extract the benchmark models and data files:

```bash
mkdir -p target/comparison
unxz -c results/comparison/results.tar.xz | tar -xvf - -C target/comparison
```

After extraction, the benchmark instances are available under the `target/comparison/` folder.

## Step 2 - Run the experiments

The scripts accept a single argument specifying the benchmark to execute. The supported values are:
* `latin`
* `TAP`
* `RSBP`
* `RCPSP`

For example:
```bash
sbatch src/test/sh/recovery/run-lifted.sbatch TAP
```
runs the benchmark for the TAP problem with our approach, while
```bash
sbatch src/test/sh/recovery/run-soft.sbatch TAP
```
runs the benchmark for the TAP problem with the soft approach.

## Step 3 - Collect the statistics

After all Slurm jobs have completed, aggregate the raw results into a single dataset:
```bash
python3 src/test/python/comparison/process_all.py target/comparison/results
```

## Step 4 - Generate the LaTeX table

Generate the LaTeX table reported in the paper:
```bash
python3 src/test/python/comparison/generate_table.py target/comparison/results
```

# Inter-Instances Nogood Learning (IINGL)

This repository contains also the implementation and the experimental artifacts used to reproduce the **Inter-Instances Nogood Learning (IINGL)** experiments presented in our paper.

The experiments evaluate the proposed learning methodology on four MiniZinc benchmark problems:
* Graph Coloring
* Knapsack
* Radiation
* MOSP (Minimum Open Stacks Problem)

## Prerequisites

The experiments require:
* Java 25
* Maven (used through the Maven Docker image)
* Podman (or Docker, with equivalent commands)
* Slurm workload manager (`sbatch`) for executing the experiments on a computing cluster
* Python (>=3.11) to collect statistics

All commands below assume that they are executed from the **root directory of this repository**.

The repository root is mounted inside the container as `/workspace`

## Script
A convenience script is placed in `src/test/sh/recovery/iingl-run.sbatch` and it takes the problem name as an argument.

## Step 1 - Generate the instances

Generate the collection of problem instances used during the experiments.

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-generator.jar \
        -o target/iingl/problems/ \
        -a 100
```

The `-a 100` option generates 100 instances for each benchmark family.


## Step 2 - Run
For each benchmark, the experiment consists of two phases:
1. **Preprocessing**, where the information required by the IINGL framework is generated.
2. **Execution**, submitted through Slurm using the provided `iingl-run.sbatch` script.

### Graph Coloring
#### Preprocessing
```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/graph/graph-colouring.mzn \
        -d target/iingl/problems/colouring \
        -p graph
```

#### Execute
```bash
sbatch src/test/sh/iingl/iingl-run.sbatch \
    colouring \
    src/test/resources/iingl/graph-colouring.mzn \
    src/test/resources/problems/graph/graph-colouring.mzn
```

### Knapsack
#### Preprocessing
```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/knapsack/k.mzn \
        -d target/iingl/problems/knapsack \
        -p 'size:min(size)..max(size)' \
        -p 'value:min(value)..max(value)'
```

#### Execute
```bash
sbatch src/test/sh/iingl/iingl-run.sbatch \
    knapsack \
    src/test/resources/iingl/knapsack.mzn \
    src/test/resources/problems/knapsack/k.mzn
```

### Radiation

#### Preprocessing

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/radiation/radiation.mzn \
        -d target/iingl/problems/radiation \
        -p 'Intensity:min(Intensity)..max(Intensity)'
```

#### Execute

```bash
sbatch src/test/sh/iingl/iingl-run.sbatch \
    radiation \
    src/test/resources/iingl/radiation.mzn \
    src/test/resources/problems/radiation/radiation.mzn
```

### MOSP
#### Preprocessing
```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/mosp/mosp.mzn \
        -d target/iingl/problems/mosp \
        -p graph
```

#### Execute
```bash
sbatch src/test/sh/iingl/iingl-run.sbatch \
    mosp \
    src/test/resources/iingl/mosp.mzn \
    src/test/resources/problems/mosp/mosp.mzn
```

## Directory structure

The experiments will be saved with following structure:

```text
target/
├─ ...
├─ iingl-generator.jar
├─ iingl-saver.jar
└─ iingl/
    └─ problems/
        └─ <problem>
            └─ <repetition>
                ├─ 0-0.dzn
                ├─ ...
                ├─ 7-50.dzn
                ├─ chain.mzn
                ├─ chain.txt
                ├─ assumptions.txt
                └─ one-by-one.txt
```

## Step 3 - Statistics and Table genetation
To collect the statistics, simply run:
```python
python3 src/test/python/iingl/iingl.py target/iingl/problems/ target/iingl/run.json
python3 src/test/python/iingl/compute_metrics.py target/iingl/run.json target/iingl/metrics.json
python3 src/test/python/iingl/generate_tables.py target/iingl/metrics.json -o target/iingl/tables
```
Tables are then stored in the `target/iingl/tables` folder.

## Notes
* **All commands are intended to be executed from the repository root**.
* The repository root is mounted into the container as `/workspace`.
* The preprocessing step must be executed before submitting the corresponding Slurm job.
* The Slurm script `src/test/sh/recovery/iingl-run.sbatch` is responsible for launching the actual experimental evaluation for the selected benchmark.
* **You will need the forked version of huub** to reproduce this set of experiments, as presented in the paper.

# Reproducibility of [IINGL](https://doi.org/10.1007/978-3-642-33558-7_19) paper

## Prerequisites

The experiments require:
* Java 25
* Maven
* Podman (or Docker, with equivalent commands)
* Slurm workload manager (`sbatch`) for executing the experiments on a computing cluster
* Python (>=3.11) to collect statistics and generate the final tables
* `xz` (or `unxz`) to extract the benchmark archive

All commands below assume that they are executed from the **root directory of this repository**.

This repository also contains the procedure to reproduce the experiments found [here](https://people.eng.unimelb.edu.au/pstuckey/interprob/).

```bash
mkdir target/reproduce
wget https://people.eng.unimelb.edu.au/pstuckey/interprob/benchmarks.zip -O target/reproduce/benchmarks.zip
unzip target/reproduce/benchmarks.zip -d target/reproduce/
```

Note that a processed benchmark(#step-3---generate-the-no-good-learning-instances) can be found in `results/resources/reproduce/results.tar.xz`

## Step 1 - Fix the old dataset
This dataset requires an old version of MiniZinc. In order to make it work, you need to run the scripts that fix the dataset. There are 2 scritps:
- `src/test/python/fix_radiation.py`: python script **to be run inside the radiation folder**. It fixes a parameter called `Bt_max = 10` in every instance. This originally was a structural parameter derived from a variable, that is now prohibited by MiniZinc.
- `target/reproduce/benchmarks/knapsack/fix.py`: python script **to be run inside the knapsack folder**. Arrays are declared to have length of 100 but actual length is sometimes shorter/longer than declared. There wasn't a check in MiniZinc at the time of writing the old paper so probably it slipped through.

You can directly run these commands:
```bash
cd target/reproduce/benchmarks/radiation/
python3 ../../../../src/test/python/reproduce/fix_radiation.py
cd ../knapsack/
python3 ../../../../src/test/python/reproduce/fix_knapsack.py
cd ../../../../
```

## Step 2 - Re-organize file and folder
To avoid name clashing, we reorganize the dataset using this bash script:
```bash
bash src/test/sh/reproduce/organize.bash
```

## Step 3 - Generate the no-good learning instances
You can run
```bash
bash src/test/sh/reproduce/chainer.bash
```

Note that this script uses java directly. If you need podman (or other containerization tool), you will need to modify the script. Refer to [the preprocessing section here](#step-2---run-1) and the script too.

## Step 4 - Run the experiments
The experiments execution in once again done through `sbatch`, submitted through Slurm using the provided `src/run.sbatch` script.

### Graph Coloring
```bash
for p in 1 2 5 10 20; do
    sbatch src/test/sh/reproduce/run.sbatch \
        gc \
        $p \
        src/test/resources/problems/graph/graph-colouring.mzn
done
```

### Knapsack
```bash
for p in 1 2 5 10 20; do
    sbatch src/test/sh/reproduce/run.sbatch \
        knapsack \
        $p \
        src/test/resources/problems/knapsack/k.mzn
done
```

### Radiation
```bash
for p in 1 2 5 10 20; do
    sbatch src/test/sh/reproduce/run.sbatch \
        radiation \
        $p \
        src/test/resources/problems/radiation/radiation.mzn
done
```

### MOSP
```bash
for p in 1 2 5 10 20; do
    sbatch src/test/sh/reproduce/run.sbatch \
        mosp \
        $p \
        src/test/resources/problems/mosp/mosp.mzn
done
```

## Directory structure

The experiments will be saved with following structure:

```text
target/
├─ ...
└─ reproduce/
    ├─ benchmarks.zip
    └─ problems/
        └─ <problem>
            └─ <percentage>
                └─ <repetition>
                    ├─ <problem>-<percentage>-<repetition>-1.dzn
                    ├─ <problem>-<percentage>-<repetition>-2.dzn
                    ├─ chain.mzn
                    ├─ chain.txt
                    └─ one-by-one.txt
```

## Step 3 - Statistics and Table genetation
To collect the statistics, simply run:
```python
python3 src/test/python/reproduce/analyze.py target/reproduce/problems/ target/reproduce/run.json
python3 src/test/python/reproduce/compute_metrics.py target/reproduce/run.json target/reproduce/metrics.json
python3 src/test/python/reproduce/generate_tables.py target/reproduce/metrics.json -o target/reproduce/tables
```
Tables are then stored in the `target/reproduce/tables` folder.

## Notes
* **All commands are intended to be executed from the repository root**.
* The repository root is mounted into the container as `/workspace`.
* The preprocessing step must be executed before submitting the corresponding Slurm job.
* The Slurm script `src/test/sh/reproduce/run.sbatch` is responsible for launching the actual experimental evaluation for the selected benchmark.
* **You will need the forked version of huub** to reproduce this set of experiments, as presented in the paper.
