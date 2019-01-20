package com.spark.scala.sparkStreaming

import com.spark.scala.sparkStreaming.reciever.CustomerReciever
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SocketSampleTask {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SocketSampleTask")
    val sparkContext = new SparkContext(sparkConf)
    val sc = new StreamingContext(sparkContext, Seconds(5))
    val sts = sc.receiverStream(new CustomerReciever("localhost", 9587))
    //val sts = sc.socketTextStream("localhost", 9587)
    val wrodCounts = sts.flatMap(_.split("\\s+")).map(str => (str, 1)).reduceByKey(_ + _)
    wrodCounts.print()
    sc.start()
    sc.awaitTermination()
  }
}
