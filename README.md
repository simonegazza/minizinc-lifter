# mzn-parameter-lifting

**Parameter lifting parser**

`mzn-parameter-lifting` is a MiniZinc to MiniZinc transpiler designed for parameter lifting. It reads a MiniZinc model, analyzes its parameters, and generates a transformed version of the model where selected parameters are *lifted* into decision variables.

This tool relies on an ANTLR4-generated parser to analyze and rewrite the MiniZinc Abstract Syntax Tree (AST), preserving the original formatting and structure during transformation.

## Features

- **AST-based Transformation:** Uses ANTLR4 to parse MiniZinc and precisely lift the requested parameters into decision variables.
- **Dependency Graph:** Analyzes parameter dependencies using a directed graph.
- **CLI Interface:** Provides an easy-to-use Command Line Interface built with Picocli.

## Prerequisites

- **Java Development Kit (JDK):** Version 25 or higher (specified by the Maven compiler release).
- **Maven:** Version 3.8+ (A Maven Wrapper `mvnw` is included in the project).

## Building

This project utilizes Maven for dependency management, test execution, and building.

To compile the code and automatically generate the ANTLR4 parser:

```bash
./mvnw clean compile
```

To run tests:

```bash
./mvnw test
```

### Code Style & Formatting

The project enforce rigorous code style standards using Eclipse Formatter (via Spotless) and Checkstyle.

- Spotlight automatically formats the code during the `compile` phase.
- Checkstyle verifies the style during `test` and `verify` phases.

To manually re-format the code without full compilation:

```bash
./mvnw spotless:apply
```

## Usage

You can run the tool directly through the `exec-maven-plugin` or build a distributable jar.

**Using Maven Exec plugin:**

```bash
./mvnw exec:java -Dexec.args="--models <model_file> --parameters <parameter_name> [--output <output_file>]"
```

### Command Line Options

```text
Usage: mzn-parameter-lifting [-hV] [-o=<outputFile>] -m=<filePaths>...
                             [-m=<filePaths>...]... -p=<parameters>...
                             [-p=<parameters>...]...
Parameter lifts a MiniZinc Model
  -h, --help            Show this help message and exit.
  -m, --models=<filePaths>...
                        MZN and MDN file paths
  -o, --output=<outputFile>
                        Output file path (default prints to console)
  -p, --parameters=<parameters>...
                        Parameter to lift the model with
  -V, --version         Print version information and exit.
```

### Workflow

1. **Input:** The tool reads one or more input MiniZinc files using the `-m` (or `--models`) flag.
2. **Parameters Extraction:** It discovers all parameters and their dependencies.
3. **Lifting:** Requested parameters (specified via `-p` or `--parameters`) are replaced with equivalent decision variables.
4. **Output:** The transformed model is either printed to the standard output or written to an output file specified by `-o` (or `--output`).

## License

This project is distributed under a **Dual License** model:

- **Non-Commercial Use:** You are free to use, study, modify, and distribute this software for personal, academic, or non-profit purposes free of charge.
- **Commercial Use:** Any commercial use of this software — including integrating it into a commercial product, using it to provide a commercial service, or using it within a commercial organization for profit — requires a separate paid license.

To request a commercial license, please contact the author to negotiate the terms.
