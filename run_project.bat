@echo off
setlocal enabledelayedexpansion

set SPARK_SCALA_FILE=code\SparkExample.scala
set PYSPARK_FILE=code\pyspark_example.py
set SPARK_JAR_FILE=target\scala-2.12\sparkwordcount_2.12-1.0.jar
set PANDAS_FILE=code\pandas_example.py
set HDFS_FILE_PATH=\hdfs\input\textinput.txt
set HADOOP_JAR_FILE=code\hadoop\target\WordCountHadoop-1.0-SNAPSHOT.jar

echo Running Spark job...
start /B spark-submit --class SparkExample --master local[4] "%SPARK_JAR_FILE%"
echo Spark job is running...

echo Running Hadoop job...
start /B hadoop jar "%HADOOP_JAR_FILE%" com.hadoop.HadoopEventProcessing "%HDFS_FILE_PATH%" \output
echo Hadoop job is running...

echo Running PySpark job...
start /B python "%PYSPARK_FILE%"
echo PySpark job is running...

echo Running Pandas job...
start /B python "%PANDAS_FILE%"
echo Pandas job is running...

echo Running Polars with rust job...
start /B cargo run --target-dir=rust-target
echo Pandas job is running...

exit /B

