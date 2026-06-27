# MiniZinc Automatic Parameter Lifting
This is a MiniZinc to MiniZinc transpiler designed for parameter lifting. It reads a MiniZinc model, analyzes its parameters, and generates a transformed version of the model where selected parameters are *lifted* into decision variables.

This tool relies on an ANTLR4-generated parser to analyze and rewrite the MiniZinc Abstract Syntax Tree (AST), preserving the original formatting and structure during transformation.

## Prerequisites
- **Java Development Kit (JDK):** Version 25 or higher (specified by the Maven compiler release).
- **Maven:** Version 3.8+ (A Maven Wrapper `mvnw` is included in the project).

## Building
This project utilizes Maven for dependency management, test execution, and building.

To compile the code, run test and generate the jars:

```bash
./mvnw package
```

# Automatic Satisfiability Recovery
This repository also contains the implementation and experimental artifacts for the **Automatic Satisfiability Recovery** evaluation presented in our paper.

The complete experimental pipeline is automated by the `recovery.sh` script, which:

1. builds the project,
2. generates randomized benchmark instances,
3. executes the satisfiability recovery procedure,
4. collects the experimental results, and
5. produces the tables used in the paper.

## Prerequisites
The experiments require:
* Java 25
* Maven
* Python 3

All commands are executed from the **root directory of the repository**.

## Running the complete experiment
The satisfiability recovery experimental pipeline can be reproduced by executing:

```bash
sh recovery.sh
```

The script performs all required steps automatically on your local machine.

## Experimental pipeline
We will use the fixture problem as running example for this experimentation. The other model are available in `src/test/sh/recovery`

### Step 1 - Generate randomized benchmark
Random benchmark instances are generated using the `test-randomizer` tool.

```bash
java -jar target/test-randomizer.jar \
    -o target/test-classes/random-fixtures \
    -m src/test/resources/problems/fixtures/cp-model-new.mzn \
    src/test/resources/problems/fixtures/coffee_table.dzn \
    -p WMin \
    -p HMin
```

The generated instances are stored as `.dzn` files under:
```text
target/test-classes/random-<problem>/<repetition>.dzn
```

Each `.dzn` file corresponds to one independent randomized repetition of the experiment.

### Step 2 - Run
The recovery procedure is executed using `test-run.jar`.

For the fixture benchmark:
```bash
java -jar target/test-run.jar \
    -d target/test-classes/random-fixtures \
    -o target/test-classes/random-fixtures \
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
target/test-classes/random-<problem>/<repetition>/
```

### Step 3 - Collecting statistics
After all experiments have completed, the raw output is aggregated into a single JSON file:

```bash
python3 ./src/test/python/parse.py \
    ./target/results \
    ./target/results/result.json
```

Then, the paper statistics are obtained by running:
```bash
python3 ./src/test/python/generate_tables.py \
    ./target/results/result.json \
    ./target/results/tables.json
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
* Python (>=3.14) to collect statistics

All commands below assume that they are executed from the **root directory of this repository**.

The repository root is mounted inside the container as:

```text
/workspace
```

## Step 1 - Build the project

Compile the project and generate the required JAR files.

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    /workspace/mvnw clean package
```

This produces the executable JAR files under:

```text
target/
```

## Step 2 - Generate the instances

Generate the collection of problem instances used during the experiments.

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-generator.jar \
        -o target/problems/ \
        -a 100
```

The generated instances are stored under:

```text
target/problems/
```

The `-a 100` option generates 100 instances for each benchmark family.


## Step 3 - Run
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
        -d target/problems/colouring \
        -p graph
```

#### Execute
```bash
sbatch iingl-run.sbatch \
    colouring \
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
        -d target/problems/knapsack \
        -p 'size:min(size)..max(size)' \
        -p 'value:min(value)..max(value)'
```

### Execute
```bash
sbatch iingl-run.sbatch \
    knapsack \
    src/test/resources/problems/knapsack/k.mzn
```

## Radiation

### Preprocessing

```bash
podman run \
    -v ./:/workspace \
    -w /workspace \
    docker.io/library/maven:3.9-eclipse-temurin-25 \
    java -jar target/iingl-saver.jar \
        -m src/test/resources/problems/radiation/radiation.mzn \
        -d target/problems/radiation \
        -p 'Intensity:min(Intensity)..max(Intensity)'
```

### Execute

```bash
sbatch iingl-run.sbatch \
    radiation \
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
        -d target/problems/mosp \
        -p graph
```

#### Execute
```bash
sbatch iingl-run.sbatch \
    mosp \
    src/test/resources/problems/mosp/mosp.mzn
```

# Directory structure

The experiments will be saved with following structure:

```text
target/
├─ ...
├─ iingl-generator.jar
├─ iingl-saver.jar
└─ problems/
    └─ <problem>
      └─ <repetition>
          ├─ 0-0.dzn
          ├─ ...
          ├─ 7-50.dzn
          ├─ chain.mzn
          ├─ chain.txt
          └─ one-by-one.txt
```

## Step 4 - Statistics
To collect the statistics, simpli run:
```python
python3 src/test/python/iingl.py target/problems
```

# Notes
* All commands are intended to be executed from the repository root.
* The repository root is mounted into the container as `/workspace`.
* The preprocessing step must be executed before submitting the corresponding Slurm job.
* The Slurm script `iingl-run.sbatch` is responsible for launching the actual experimental evaluation for the selected benchmark.
* You will need the forked version of huub, as presented in the paper.
