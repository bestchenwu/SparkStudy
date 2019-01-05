package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 学习来自于官方http://spark.apache.org/docs/2.3.0/的例子
  */
object OfficialStudy {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("OfficialStudy").setMaster("local")
    val sc = new SparkContext(conf)
    // 创建累加器 Accumulator
    val sumCount = sc.longAccumulator("sumCount")
    val array = Array(1, 2, 3, 4)
    val rdd = sc.parallelize(array)
    rdd.foreach(x=>sumCount.add(x))
    print("sumCount="+sumCount.value)
    // val reduceRDD = rdd.reduce((a, b) => a + b)
    // 可读不可变
//    val bc = sc.broadcast(Array(3,4,5))
//    println(bc.getClass)
    //println("reduceRDD=" + reduceRDD)
  }
}
