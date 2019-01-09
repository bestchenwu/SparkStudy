package com.spark.scala.study.sql

import org.apache.spark.sql.{SaveMode, SparkSession}

object HiveWriteTest {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().config("spark.sql.warehouse.dir", "hdfs://localhost:9000/usr/hive/warehouse")
      //.config("spark.sql.hive.convertMetastoreParquet",false)
      .appName("HiveWriteTask")
      .enableHiveSupport().master("local").getOrCreate()
    import sparkSession.implicits._
    val df = sparkSession.read.json("src/main/resources/peopleDt.json")
    df.show(3)

    //开启hive动态分区
    val sqlContext = sparkSession.sqlContext
    sqlContext.setConf("hive.exec.dynamic.partition", "true")
    sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")
    //由于这里列的顺序性不可控,所以不推荐采取下述方式,会报partition名称的不一样
    //df.write.mode(SaveMode.Append).format("hive").partitionBy("dt").saveAsTable("inf.people_bucket3")
    df.createOrReplaceTempView("people")
    sqlContext.sql("insert into table inf.people_bucket3 partition(dt) select name,age,dt from people")
    sparkSession.stop()
  }
}
