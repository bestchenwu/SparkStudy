package com.spark.scala.study.sql

import org.apache.spark.sql.SparkSession

object HiveWriteTest {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().config("spark.sql.warehouse.dir", "hdfs://localhost:9000/usr/hive/warehouse")
      .appName("HiveWriteTask")
      .enableHiveSupport().master("local").getOrCreate()
    import sparkSession.implicits._
    val df = sparkSession.read.json("src/main/resources/peopleDt.json")

  }
}
