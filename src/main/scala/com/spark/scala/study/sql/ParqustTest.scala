package com.spark.scala.study.sql

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * apache和twitter联合推出的列式存储结构
  */
object ParqustTest {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("ParqustTest").master("local").getOrCreate()
      val df = sparkSession.read.load("src/main/resources/users.parquet")
    df.select("name","favorite_color").write.mode(SaveMode.Append).format("csv").save("src/main/resources/userFavor.csv")
  }
}
