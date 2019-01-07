package com.spark.scala.study.sql

import org.apache.spark.sql.SparkSession

object HiveReadTest {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().config("spark.sql.warehouse.dir","hdfs://localhost:9000/usr/hive/warehouse")
      .appName("HiveReadTask")
      .enableHiveSupport().master("local").getOrCreate()
    import sparkSession.implicits._
    val sqlContext = sparkSession.sqlContext
    val df = sqlContext.sql("select name,age from inf.people_bucket2").sort($"age")
    df.show(3)
  }
}
