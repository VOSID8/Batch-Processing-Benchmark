@echo off
setlocal enabledelayedexpansion

echo Running Beam job...
mvn clean compile exec:java -Dexec.mainClass=com.beam.BeamWordCount
echo Beam job is running...



