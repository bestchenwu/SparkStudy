package com.spark.scala.study.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

object SparkMysqlTask {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkMysqlTask").master("local").getOrCreate()
    val df = sparkSession.read.format("jdbc")
      .option("driver","com.mysql.cj.jdbc.Driver")
      .option("url","jdbc:mysql://localhost/spark?userSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT")
      .option("dbtable","student")
      .option("user","sweet")
      .option("password","123456")
      .load()
    import sparkSession.implicits._
    df.filter($"age">30).show()
  }
}
