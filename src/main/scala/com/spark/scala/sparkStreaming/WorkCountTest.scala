package com.spark.scala.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WorkCountTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WorkCountTest").setMaster("local");
    val ssc = new StreamingContext(conf, Seconds(10));
    //val stream = ssc.socketTextStream("localhost", 9999)
    val stream = ssc.textFileStream("hdfs://10.40.11.12:8020/user/chenwu/hadoop/word_count.txt")
    //val stream = ssc.textFileStream("D:\\logs\\")
    val words = stream.flatMap(_.split(","));
    val pairs = words.map(word => (word, 1));
    val wordCounts = pairs.reduceByKey(_ + _);
    //直接测试可以这样看
    wordCounts.print();
    //保存到redis环境中供测试查看

    //    wordCounts.foreachRDD(rdd => {
    //      rdd.foreachPartition(iter => {
    //        val redissionClient = BabytreeRedissionClient("redisClusterDataset80G.properties")
    //        val list = iter.toMap
    //        redissionClient.multiSet(list, 1l, async = false)
    //        redissionClient.close()
    //      })
    //    })
    ssc.start();
    ssc.awaitTermination();

  }
}
