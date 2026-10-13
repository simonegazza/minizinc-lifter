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

## Parameter lifting
The lifting is performed by `lifter.jar` and only needs the MiniZinc model: no data file is required. The lifted model (with assumptions and the objective function) is written to `<output>/lifted-<model>.mzn` and can be run with any data file of the original model.

```bash
java -jar target/lifter.jar \
    -o target/lifted \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    -p WMin:100..2200 \
    -p HMin:100..2200
```

## Feasibility recovery of a single instance
The feasibility recovery of a single instance is performed by `recovery.jar`, that needs both the model (`-m`) and the data (`-d`):

```bash
java -jar target/recovery.jar \
    -o target/recovered \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    -d src/test/resources/problems/fixtures/coffee_table.dzn \
    -p WMin:100..2200 \
    -p HMin:100..2200
```

# Automatic Feasibility Recovery
This repository also contains the implementation and experimental artifacts for the **Automatic Feasibility Recovery** evaluation presented in our paper. As the other experiments, it is executed on a computing cluster through Slurm and Podman (see [Running on the cluster](#running-on-the-cluster)); the same pipeline can also be executed locally with the `src/test/sh/recovery/recovery.sh` script.

## Prerequisites
The experiments require:
* Java 25
* Maven
* Python >= 3.11
* Podman (or Docker, with equivalent commands) and the Slurm workload manager (`sbatch`), to execute the experiments on a computing cluster
* MiniZinc with the Huub solver, only to execute the experiments locally (`minizinc` must be in the `PATH`)

All commands are executed from the **root directory of the repository**.

## Running on the cluster
The feasibility recovery tool (Java) runs MiniZinc, so both must be in the same container. No dedicated image is needed: the Slurm script uses the MiniZinc image of the other experiments (`docker.io/minizinc/minizinc:edge-noble`) and mounts in it, read-only, the Java runtime of the Maven image used to run the other Java tools (`docker.io/library/maven:3.9-eclipse-temurin-25`, through Podman's `--mount type=image`).

As for the other experiments, Huub is expected in `../huub` and it is mounted in the container. The jars must be built before (see [Building](#building)); if Java 25 is not available on the cluster, they can be built with Podman too:

```bash
podman run --rm -v ./:/workspace -w /workspace docker.io/library/maven:3.9-eclipse-temurin-25 ./mvnw package
```

Then, each task of the Slurm array executes one of the scripts in `src/test/sh/recovery/problems/` (Step 1 and Step 2 below, for one problem) inside the container:

```bash
mkdir -p target/logs
sbatch src/test/sh/recovery/run.sbatch
```

The statistics of each problem are written to `target/recover/results/<problem>.sh.txt` (and the logs to `target/recover/results/<problem>.sh.err`). When all the tasks have completed, the statistics are collected (Step 3 below) with:

```bash
sh src/test/sh/recovery/collect.sh
```

The steps below describe what is executed for each problem.

## Experimental pipeline
We will use the fixture problem as running example for this experimentation. The other scripts are available in `src/test/sh/recovery/problems`.

### Step 1 - Generate randomized benchmark
Random benchmark instances are generated using the `test-randomizer` tool.

```bash
java -jar target/test-randomizer.jar \
    -o target/recover/results/fixtures \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    -m src/test/resources/problems/fixtures/coffee_table.dzn \
    -p WMin \
    -p HMin
```

The generated instances are stored as `.dzn` files under:
```text
target/recover/results/<problem>/<repetition>-<data>.dzn
```

Each `.dzn` file corresponds to one independent randomized repetition of the experiment.

### Step 2 - Run
The recovery procedure is executed using `test-run.jar`, that runs the feasibility recovery (as `recovery.jar`) for every `.dzn` file in the data folder.

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
After all experiments have completed, the raw output is aggregated into a single JSON file, the metrics are computed (including the minimum, average and maximum value of the objective function, i.e. the distance of the recovered parameters from the original ones) and the LaTeX table is generated. This is done by `src/test/sh/recovery/collect.sh`:

```bash
python3 ./src/test/python/recover/recover.py target/recover/results/ target/recover/run.json
python3 ./src/test/python/recover/compute_metrics.py target/recover/run.json target/recover/metrics.json
python3 ./src/test/python/recover/generate_table.py target/recover/metrics.json target/recover/table.tex
```

## Directory structure

After the experiment completes, the output directory has the following structure:

```text
target/
├─ test-randomizer.jar
├─ test-run.jar
└─ recover/
    ├─ run.json                       # statistics of all the problems (recover.py)
    ├─ metrics.json                   # aggregated metrics (compute_metrics.py)
    ├─ table.tex                      # LaTeX table (generate_table.py)
    └─ results/
        ├─ <problem>.sh.txt           # output of src/test/sh/recovery/problems/<problem>.sh
        ├─ <problem>.sh.err           # its logs (only when executed with run.sbatch)
        └─ <problem>/
            ├─ <repetition>-<data>.dzn       # randomized instance (test-randomizer.jar)
            ├─ ...
            └─ <repetition>-<data>/          # one folder per instance (test-run.jar)
                ├─ <model>.mzn               # original model with the instance data
                ├─ 1-<model>.mzn             # lifted model of the first iteration
                ├─ 2-<model>.mzn             # lifted model of the second iteration (core removed)
                ├─ ...
                └─ recovery-<i>-<model>.mzn  # only if the recovery solver was used at iteration i
```

The folder of a problem is the one given with `-o`/`-d` in its script (e.g., `random-aircraft` for `aircraft.sh`). FREEDA does not use randomized instances: its instances are read from `src/test/resources/problems/FREEDA/test-data`, so `target/recover/results/FREEDA/` only contains one folder per instance.

The statistics of each run are printed by `test-run.jar` after the `*********************************************` line of `<problem>.sh.txt`. A problem without statistics, or a run that ended with an error, makes `recover.py` exit with an error listing them (use `--allow-failures` to produce `run.json` anyway).

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

Create the output directories and extract the benchmark models and data files (the archive has a top-level `data/` folder):

```bash
mkdir -p target/comparison/{results,tables} logs
tar -xvf src/test/resources/comparison/data.tar.xz -C target/comparison
```

After extraction, each benchmark is available as `target/comparison/data/<benchmark>/`, containing the `<benchmark>_lifted.mzn` and `<benchmark>_soft.mzn` models and the `unsat_dzn/` instances used by the Slurm scripts. The `logs/` folder is required by the `--output`/`--error` options of the Slurm scripts.

The outputs of our runs are archived in `results/comparison/results.tar.xz` (they extract to a `results/` folder, i.e. the content of `target/comparison/results`).

## Step 2 - Run the experiments

The scripts accept a single argument specifying the benchmark to execute. The supported values are:
* `latin`
* `TAP`
* `RSBP`
* `RCPSP`

For example:
```bash
sbatch src/test/sh/comparison/run-lifted.sbatch TAP
```
runs the benchmark for the TAP problem with our approach, while
```bash
sbatch src/test/sh/comparison/run-soft.sbatch TAP
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
python3 src/test/python/comparison/generate_table.py target/comparison/results > target/comparison/tables/table.tex
```

By default, the table compare the values of the objective function of the two methods (column *Objective of Lifted vs CMPCS*). To remove it, use the `--no-objectives` flag:

```bash
python3 src/test/python/comparison/generate_table.py --no-objectives target/comparison/results > target/comparison/tables/table.tex
```

# Coarse and increasing change Inter-Instances Nogood Learning (IINGL)

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
A convenience script is placed in `src/test/sh/coarse/run.sbatch`: it takes as arguments the problem name (i.e. the folder in `target/coarse/problems/`), the model with assumptions and the original model.

## Step 1 - Generate the instances

Generate the collection of problem instances used during the experiments.

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/coarse-generator.jar \
        -o target/coarse/problems/ \
        -a 100
```

The `-a 100` option generates 100 instances for each benchmark family.


## Step 2 - Run
For each benchmark, the experiment consists of two phases:
1. **Preprocessing**, that generates the chain model.
2. **Execution**, submitted through Slurm using the provided `src/test/sh/coarse/run.sbatch` script.

### Graph Coloring
#### Preprocessing
```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/graph/graph-colouring.mzn \
        -d target/coarse/problems/colouring \
        -p graph
```

#### Execute
```bash
sbatch src/test/sh/coarse/run.sbatch \
    colouring \
    src/test/resources/coarse/graph-colouring.mzn \
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
        -d target/coarse/problems/knapsack \
        -p 'size:min(size)..max(size)' \
        -p 'value:min(value)..max(value)'
```

#### Execute
```bash
sbatch src/test/sh/coarse/run.sbatch \
    knapsack \
    src/test/resources/coarse/knapsack.mzn \
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
        -d target/coarse/problems/radiation \
        -p 'Intensity:min(Intensity)..max(Intensity)'
```

#### Execute
```bash
sbatch src/test/sh/coarse/run.sbatch \
    radiation \
    src/test/resources/coarse/radiation.mzn \
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
        -d target/coarse/problems/mosp \
        -p graph
```

#### Execute
```bash
sbatch src/test/sh/coarse/run.sbatch \
    mosp \
    src/test/resources/coarse/mosp.mzn \
    src/test/resources/problems/mosp/mosp.mzn
```

## Directory structure
The experiments will be saved with following structure:

```text
target/
├─ ...
├─ coarse-generator.jar
├─ iingl-saver.jar
└─ coarse/
    └─ problems/
        └─ <problem>
            └─ <repetition>
                ├─ 0-0.dzn
                ├─ ...
                ├─ 6-50.dzn
                ├─ chain.mzn
                ├─ chain.txt
                ├─ assumptions.txt
                └─ one-by-one.txt
```

## Step 3 - Statistics and Table genetation
To collect the statistics, simply run:
```python
python3 src/test/python/coarse/coarse.py target/coarse/problems/ target/coarse/run.json
python3 src/test/python/coarse/compute_metrics.py target/coarse/run.json target/coarse/metrics.json
python3 src/test/python/coarse/generate_tables.py target/coarse/metrics.json -o target/coarse/tables
```
Tables are then stored in the `target/coarse/tables` folder.

## Notes
* **All commands are intended to be executed from the repository root**.
* The repository root is mounted into the container as `/workspace`.
* The preprocessing step must be executed before submitting the corresponding Slurm job.
* The Slurm script `src/test/sh/coarse/run.sbatch` is responsible for launching the actual experimental evaluation for the selected benchmark.
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

Note that a processed benchmark can be found in `results/reproduce/results.tar.xz`

## Step 1 - Fix the old dataset
This dataset requires an old version of MiniZinc. In order to make it work, you need to run the scripts that fix the dataset. There are 2 scritps:
- `src/test/python/reproduce/fix_radiation.py`: python script **to be run inside the radiation folder**. It fixes the parameter `Bt_max` to `max(Intensity)` in every instance (this is `Bt_max = 10` for every instance of the benchmark). This originally was a structural parameter derived from a variable, that is now prohibited by MiniZinc. The script can be run more than once: an existing `Bt_max` assignment is replaced.
- `src/test/python/reproduce/fix_knapsack.py`: python script **to be run inside the knapsack folder**. Arrays are declared to have length of 100 but actual length is sometimes shorter/longer than declared. There wasn't a check in MiniZinc at the time of writing the old paper so probably it slipped through.

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
The experiments execution in once again done through `sbatch`, submitted through Slurm using the provided `src/test/sh/reproduce/run.sbatch` script.

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

# Finer IINGL

Note that a processed benchmark can be found in `results/iingl/problems.tar.xz`

## Prerequisites
The experiments require:
* Java 25
* Maven
* Podman (or Docker, with equivalent commands)
* Slurm workload manager (`sbatch`) for executing the experiments on a computing cluster
* Python (>=3.11) to collect statistics and generate the final tables
* `xz` (or `unxz`) to extract the benchmark archive

All commands below assume that they are executed from the **root directory of this repository**.

## Generate the dataset from scratch
You can re-generate the dataset from scratch by running these commands:
```bash
python3 src/test/python/iingl/generate_problems.py target/iingl/problems
```

This generates the colouring, knapsack, radiation and MOSP instances only: there is no generator for FREEDA. The FREEDA instances used in the paper are taken from the processed benchmark, keeping only the `.dzn` instances (`chain.mzn` is regenerated by the preprocessing and the `.txt` outputs would otherwise be appended to by the runs):
```bash
mkdir -p target/iingl
tar -xJf results/iingl/problems.tar.xz -C target/iingl problems/FREEDA
find target/iingl/problems/FREEDA \( -name '*.txt' -o -name 'chain.mzn' -o -name '.DS_Store' \) -delete
```

## Preprocessing
Generated instances needs to be saved into model, just like in the [coarse version](#coarse-and-increasing-change-inter-instances-nogood-learning-iingl).

### Graph Coloring
```bash
for r in `ls target/iingl/problems/colouring`; do
    podman run \
        -v ./:/workspace \
        -w /workspace \
        docker.io/library/maven:3.9-eclipse-temurin-25 \
        java -jar target/iingl-saver.jar \
            -m src/test/resources/problems/graph/gc-simplified.mzn \
            -d target/iingl/problems/colouring/$r \
            -p graph;
done
```

### Knapsack
```bash
for r in `ls target/iingl/problems/knapsack`; do
    podman run \
        -v ./:/workspace \
        -w /workspace \
        docker.io/library/maven:3.9-eclipse-temurin-25 \
        java -jar target/iingl-saver.jar \
            -m src/test/resources/problems/knapsack/k.mzn \
            -d target/iingl/problems/knapsack/$r \
            -p 'size:min(size)..max(size)' \
            -p 'value:min(value)..max(value)';
done
```

### Radiation
```bash
for r in `ls target/iingl/problems/radiation`; do
    podman run \
        -v ./:/workspace \
        -w /workspace \
        docker.io/library/maven:3.9-eclipse-temurin-25 \
        java -jar target/iingl-saver.jar \
            -m src/test/resources/problems/radiation/radiation.mzn \
            -d target/iingl/problems/radiation/$r \
            -p 'Intensity:min(Intensity)..max(Intensity)';
done
```

### MOSP
```bash
for r in `ls target/iingl/problems/mosp`; do
    podman run \
        -v ./:/workspace \
        -w /workspace \
        docker.io/library/maven:3.9-eclipse-temurin-25 \
        java -jar target/iingl-saver.jar \
            -m src/test/resources/problems/mosp/mosp.mzn \
            -d target/iingl/problems/mosp/$r \
            -p graph;
done
```

### FREEDA
```bash
for r in `ls target/iingl/problems/FREEDA`; do
    podman run \
        -v ./:/workspace \
        -w /workspace \
        docker.io/library/maven:3.9-eclipse-temurin-25 \
        java -jar target/iingl-saver.jar \
            -m src/test/resources/problems/FREEDA/freeda-model-v0.3.mzn \
            -d target/iingl/problems/FREEDA/$r \
            -p depReq -p linkCap -p nodeCap -p energy -p energy_dependency -p comReq -p cost -p carb -p costBudget -p carbBudget;
done
```

## Execution
### Graph Coloring
```bash
for r in `ls target/iingl/problems/colouring`; do
    sbatch src/test/sh/iingl/run.sbatch \
        colouring \
        $r \
        src/test/resources/problems/graph/gc-simplified.mzn;
done
```

### Knapsack
```bash
for r in `ls target/iingl/problems/knapsack`; do
    sbatch src/test/sh/iingl/run.sbatch \
        knapsack \
        $r \
        src/test/resources/problems/knapsack/k.mzn;
done
```

### Radiation
```bash
for r in `ls target/iingl/problems/radiation`; do
    sbatch src/test/sh/iingl/run.sbatch \
        radiation \
        $r \
        src/test/resources/problems/radiation/radiation.mzn;
done
```

### MOSP
```bash
for r in `ls target/iingl/problems/mosp`; do
    sbatch src/test/sh/iingl/run.sbatch \
        mosp \
        $r \
        src/test/resources/problems/mosp/mosp.mzn;
done
```

### FREEDA
```bash
for r in `ls target/iingl/problems/FREEDA`; do
    sbatch src/test/sh/iingl/run.sbatch \
        FREEDA \
        $r \
        src/test/resources/problems/FREEDA/freeda-model-v0.3.mzn;
done
```

## Directory structure
The experiments will be saved with following structure:
```text
target/
├─ ...
├─ iingl-saver.jar
└─ iingl/
    └─ problems/
        └─ <problem>
            └─ <repetition>
                └─ <percentage>
                    ├─ 00.dzn
                    ├─ ...
                    ├─ 19.dzn
                    ├─ chain.mzn
                    ├─ chain.txt
                    └─ one-by-one.txt
```
