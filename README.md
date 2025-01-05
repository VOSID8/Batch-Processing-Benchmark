# Batch Processing Performance Benchmark
## Overview

This project benchmarks the performance of various batch processing tools on a large dataset. It demonstrates the efficiency and speed of each tool by processing a generated text file and timing the execution. The project compares the performance of the following tools:

- Apache Spark with Scala
- Apache Hadoop with Java
- Polars with Rust
- Apache Spark with Python (PySpark)
- Pandas with Numpy (Python)
- Apache Beam with Java

Each tool processes a text file (textinput.txt) containing 160 million words, analyzing the occurrences of specific words. The performance of each tool is measured and ranked based on the time taken to complete the task.

## Dataset Description

The input text file is made up of 10 random words, which are visionofsid, Scala, Data, Java, Rust, Go, Spark, Sid, Beam, Pandas.
The file contains 16 million rows, with each row comprising 10 random words from the given options. This means the project applies batch processing on 160 million words.

## Results

![Batch Processing Tools](https://github.com/user-attachments/assets/a691087f-5b3a-4e4f-8f83-e3c81a6684cf)

Note: The execution time may vary slightly across runs, but the performance ranking remains consistent.

## Setup Instructions

### 1. Clone the Repository
``` git clone https://github.com/your-repo-name/batch-processing-benchmark.git```
### 2. Setup Environments
For python related libraries, compile_project.bat does the work for you. You don't need to do anything separetly apart froms setting up python and pip. For Spark, Hadoop and Beam, you need to set up the required system variables as well as configuring. For polars you are required to Install Rust and the cargo package manager.
### 3. Compile the project
Navigate and run
``` compile_project.bat ```
### 4. Run the Project
Run the project using ``run_project.bat``
### 5. Run Beam
Due to inadequate memory requirements (atleast on my laptop), beam is to be run separately. Navigate to the beam folder to run ```run_beam.bat```

## Project details
Batch processing code files are placed under the code (Apart from beam, for the reason stated above). Animation folder contains the code done for the project's result animation above using Doodle (Scala's computer graphic library)

## Discussion and more
Refer to: https://medium.com/@siddharthbanga/benchmarking-batch-processing-tools-performance-analysis-26a8c844c4ce



