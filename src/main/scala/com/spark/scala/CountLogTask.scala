package com.spark.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 统计日志里的文件行数
  *
  * @author chenwu on 2018.12.31
  */
object CountLogTask {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CountLogTask")
    val sc = new SparkContext(conf)
    val fileRDD = sc.textFile("/usr/spark/youwei-store-service.log")
    val mysqlStrRDD = fileRDD.filter(str => {
      if (str.contains("MySQL")) {
        true
      } else {
        false
      }

    })
    println(("count=" + mysqlStrRDD.count()))
    sc.stop()
  }
}
