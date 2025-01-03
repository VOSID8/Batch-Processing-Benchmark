@echo off
setlocal

pip install Pandas
pip install PySpark
pip install findspark

where sbt > nul 2>&1
if %errorlevel% neq 0 (
    echo Error: sbt is not installed or not in PATH.
    exit /B 1
)

echo Compiling Spark with Scala code...
start /B sbt clean compile package

echo Compiling Polars with Rust code...
start /B cargo clean && cargo build

echo Compiling Hadoop code...
start /B hdfs dfs -rm -r /output
start /B hdfs dfs -mkdir -p /hdfs/input
start /B hdfs dfs -put data/textinput.txt /hdfs/input

cd code/Hadoop
mvn clean install

exit /B
