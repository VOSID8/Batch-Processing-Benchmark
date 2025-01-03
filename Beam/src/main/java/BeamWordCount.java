package com.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class BeamWordCount {
  public static void main(String[] args) {
    Instant startTime = Instant.now();
    String inputsDir = "../data/textinput.txt";
    String outputsPrefix = "../data/output";

    PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
    Pipeline pipeline = Pipeline.create(options);
    pipeline
        .apply("Read lines", TextIO.read().from(inputsDir))
        .apply("Find words", FlatMapElements.into(TypeDescriptors.strings())
            .via((String line) -> Arrays.asList(line.split("[^\\p{L}]+"))))
        .apply("Filter empty words", Filter.by((String word) -> !word.isEmpty()))
        .apply("Count words", Count.perElement())
        .apply("Write results", MapElements.into(TypeDescriptors.strings())
            .via((KV<String, Long> wordCount) ->
                  wordCount.getKey() + ": " + wordCount.getValue()))
        .apply(TextIO.write().to(outputsPrefix));
    pipeline.run();
    
    Instant endTime = Instant.now();
    Duration timeElapsed = Duration.between(startTime, endTime);
    System.out.printf("Time taken by Beam (Java): %.2f Seconds%n", timeElapsed.toMillis() / 1000.0);
  }
}