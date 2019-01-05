package com.spark.scala.study.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object HiveTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sparkSession = SparkSession.builder().appName("").master("local").config(conf).getOrCreate()
    // 使用json读取数据
    val df = sparkSession.read.json("src/main/resources/people.json")
    //bucket,sort,partition 都是hive里的概念
    //bucket表示在表或者分区的基础上对列进行hash,用hash值对桶的个数求余数来决定存储到哪个位置
    df.write.bucketBy(42,"name").sortBy("age").saveAsTable("people_bucket")
  }
}
