package com.spark.scala.study.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object HiveTest {
  def main(args: Array[String]): Unit = {
    //第一种方式,使用saveAsTable的形式
    //spark.sql.hive.convertMetastoreParquet参数表示不使用spark的默认parquet
    //    val sparkSession = SparkSession.builder().appName("savePeopleTask").
    //      enableHiveSupport().master("local").config("spark.sql.hive.convertMetastoreParquet", "false").getOrCreate()
    //    // 使用json读取数据
    //    val df = sparkSession.read.json("src/main/resources/people.json")
    //    //bucket,sort,partition 都是hive里的概念
    //    //bucket表示在表或者分区的基础上对列进行hash,用hash值对桶的个数求余数来决定存储到哪个位置
    //    //df.write.bucketBy(1, "name").sortBy("age").saveAsTable("inf.people_bucket")
    //    //注意保存到hive表的时候,使用的是parqust默认格式,这种格式只能被spark读取
    //    //另外两边都要是没有分区 没有桶概念
    //    df.write.saveAsTable("inf.people_bucket")
    //第二种方式,使用spark sql
    //这种方式提前创建好表,保存到hive时候使用可被hadoop等可识别的textOutput格式
    val sparkSession = SparkSession.builder().appName("savePeopleTask").
      enableHiveSupport().master("local").getOrCreate()
    val df = sparkSession.read.json("src/main/resources/people.json")
    df.createOrReplaceTempView("people")
    val sqlContext = sparkSession.sqlContext
    sqlContext.sql("insert into inf.people_bucket2 select name,age from people")
  }
}
