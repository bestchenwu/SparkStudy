package com.spark.scala.study.sql

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

/**
  * 将RDD转换为dataFrame或者dataSet
  */
object RDDToDataSetTask {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().master("local").appName("RDDToDataSetTask").getOrCreate()
    import sparkSession.implicits._
    //    val ds = sparkSession.sparkContext.textFile("src/main/resources/people.txt")
    //      .map(str => str.split(","))
    //      .map(array=>Person(array(0),array(1).toLong))
    //      .toDS()
    //    //ds.filter($"age">10).show()
    //    ds.createOrReplaceTempView("people")
    //    val otherDF = sparkSession.sql("select name,age from people where age between 10 and 15")
    //    otherDF.show()

    //another program to make a df without create Class
    val rdd = sparkSession.sparkContext.textFile("src/main/resources/people.txt").map(_.split(","))
    // 先将rdd转化为rowRDD(RDD的元素是ROW)
    val rowRDD = rdd.map(array => Row(array(0), array(1).toInt))
    // 创建
    val fieldArray = Array(StructField("name", StringType), StructField("age", IntegerType))
    val structType = new StructType(fieldArray)
    val df = sparkSession.createDataFrame(rowRDD, structType)
    df.filter($"age" > 14).show()
  }
}
