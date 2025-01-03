@echo off
setlocal

where python > nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Python is not installed or not in PATH.
    exit /B 1
)

where pip > nul 2>&1
if %errorlevel% neq 0 (
    echo Error: pip is not installed or not in PATH.
    exit /B 1
)

pip install Pandas
pip install PySpark
pip install findspark

where sbt > nul 2>&1
if %errorlevel% neq 0 (
    echo Error: sbt is not installed or not in PATH.
    exit /B 1
)

if not exist "build.sbt" (
    echo Error: build.sbt file not found in the project directory.
    exit /B 1
)

echo Compiling Spark with Scala code...
start /B sbt clean compile package

echo Compiling Polars with Rust code...
start /B cargo clean && cargo build

if %errorlevel% neq 0 (
    echo Error: Failed to compile Scala code.
    exit /B 1
)

echo Compiling Hadoop code...
start /B hdfs dfs -rm -r /output
start /B hdfs dfs -mkdir -p /hdfs/input
start /B hdfs dfs -put data/textinput.txt /hdfs/input

cd code/Hadoop
mvn clean install

exit /B
