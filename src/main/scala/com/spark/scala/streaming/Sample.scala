package com.spark.scala.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Sample {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SampleTask").master("local").getOrCreate()
    //返回dataframe
    val lines = sparkSession.readStream.format("socket").option("host","localhost").option("port","9999").load()
    import sparkSession.implicits._
    val words = lines.as[String].flatMap(_.split(" "))
    val wordCounts = words.groupBy("value").count()
    val query = wordCounts.writeStream.format("console").outputMode("complete").start()
    query.awaitTermination()
  }
}
