package com.spark.scala.study.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object HiveTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sparkSession = SparkSession.builder().appName("savePeopleTask").
      enableHiveSupport().master("local").getOrCreate()
    // 使用json读取数据
    val df = sparkSession.read.json("src/main/resources/people.json")
    //bucket,sort,partition 都是hive里的概念
    //bucket表示在表或者分区的基础上对列进行hash,用hash值对桶的个数求余数来决定存储到哪个位置
    //注意以这种方式写入的数据,默认是spark的parqust格式,不能通过hive语句查询
    df.write.bucketBy(2, "name").sortBy("age").saveAsTable("inf.people_bucket")


  }
}
