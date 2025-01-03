from pyspark.sql import SparkSession
from pyspark.sql.functions import col, desc
import findspark
import time

def main():
    start_time = time.time()

    findspark.init()
    spark = SparkSession.builder \
        .appName("Word Count") \
        .master("local[4]") \
        .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    lines = spark.read.text("data/textinput.txt").rdd.flatMap(lambda line: line[0].split())

    word_counts = lines.map(lambda word: (word, 1)) \
                    .reduceByKey(lambda a, b: a + b) \
                    .toDF(["word", "count"])

    # word_counts.show()

    end_time = time.time()
    elapsed_time = end_time - start_time

    print(f"Time taken by PySpark: {elapsed_time:.2f} Seconds")

    spark.stop()


if __name__ == "__main__":
    main()

