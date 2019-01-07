package com.spark.scala.study.sql

import org.apache.spark.sql.SparkSession

object HiveReadTest {

  def main(args: Array[String]): Unit = {
    //spark.sql.warehouse.dir表示hive存储table的位置
    //如果不指定,则spark会在当前的spark_home目录下建立一个spark_warehouse用于存放table,一个metastore_db存放hive元数据
    //    val sparkSession = SparkSession.builder().config("spark.sql.warehouse.dir","hdfs://localhost:9000/usr/hive/warehouse")
    //      .appName("HiveReadTask")
    //      .enableHiveSupport().master("local").getOrCreate()
    //    import sparkSession.implicits._
    //    val sqlContext = sparkSession.sqlContext
    //    //读取hive格式为textfile,也就是默认格式
    //    val df = sqlContext.sql("select name,age from inf.people_bucket2").sort($"age")
    //    df.show(3)
    //对于spark默认存储的格式parquet,则需要在读取的时候指定format(如果指定了format,就不需要指定inputFormat,outputFormat)
    //反之如果指定了inputFormat,outputFormat,就不需要指定format
    //默认的format包括'sequencefile', 'rcfile', 'orc', 'parquet', 'textfile' and 'avro'
    val sparkSession = SparkSession.builder()
      .config("spark.sql.warehouse.dir", "hdfs://localhost:9000/usr/hive/warehouse")
      .config("fileFormat", "parquet")
      .appName("HiveReadTask")
      .enableHiveSupport().master("local").getOrCreate()
    import sparkSession.implicits._
    val sqlContext = sparkSession.sqlContext
    val df = sqlContext.sql("select name,age from inf.people_bucket").sort($"age")
    df.show()
  }
}
