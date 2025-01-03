import org.apache.spark.sql.{SparkSession}
import org.apache.spark.sql.functions._

import java.time.Instant
import java.time.Duration

object SparkExample {
  def main(args: Array[String]): Unit = {
    val startTime = Instant.now()

    val spark = SparkSession.builder()
      .appName("Word Count")
      .master("local[4]") 
      .config("spark.sql.shuffle.partitions", "8") 
      .getOrCreate()

    import spark.implicits._

    spark.sparkContext.setLogLevel("ERROR")

    val lines = spark.read.textFile("data/textinput.txt").persist() 

    val words = lines.flatMap(line => line.split("\\s+"))

    val wordCounts = words.rdd 
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .toDF("word", "count")

    //wordCounts.show()

    val endTime = Instant.now()
    val duration = Duration.between(startTime, endTime)

    println(f"Time taken by Spark (Scala): ${duration.toMillis / 1000.0}%.2f Seconds")

    spark.stop()
  }
}
