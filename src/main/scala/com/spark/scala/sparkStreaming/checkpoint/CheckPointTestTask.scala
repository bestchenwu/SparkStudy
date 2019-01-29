package com.spark.scala.sparkStreaming.checkpoint

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object BlackWord {

  private var instance: Broadcast[Seq[String]] = null

  def getInstance(sc: SparkContext): Broadcast[Seq[String]] = {
    if (instance == null) {
      synchronized {
        instance = sc.broadcast(Seq("a", "b", "c"))
      }
    }
    instance
  }
}

object WordCounter {

  @volatile private var instance: LongAccumulator = null

  def getAccumator(sc: SparkContext): LongAccumulator = {
    if (instance == null) {
      synchronized {
        instance = sc.longAccumulator("WordCounter")
      }

    }
    instance
  }
}

object CheckPointTestTask {

  def createContext(ip: String, port: Int, checkpointDirectory: String, sparkContext: SparkContext) = {
    val ssc = new StreamingContext(sparkContext, Seconds(10))
    ssc.checkpoint(checkpointDirectory)
    val stream = ssc.socketTextStream(ip, port, StorageLevel.DISK_ONLY)
    val wordCounts = stream.flatMap(str => str.split("\\s+")).map(str => (str, 1)).reduceByKey(_ + _)
    wordCounts.foreachRDD(rdd => {
      val accumulator = WordCounter.getAccumator(rdd.sparkContext)
      val blackWordList = BlackWord.getInstance(rdd.sparkContext).value
      val wordCountsStr = rdd.filter(str => {
        for (blackWord <- blackWordList) {
          if (str._1.contains(blackWord)) {
            accumulator.add(str._2)
          }
        }
        true
      }).collect().mkString("[", ", ", "]")
      println(s"wordCounts = ${wordCountsStr}")
      println(s"droupCounts = ${accumulator.value}")
      rdd.persist(StorageLevel.DISK_ONLY)
      rdd.checkpoint()
    })
    ssc
  }

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("CheckPointTestTask")
    val sc = new SparkContext(sparkConf)
    val checkpointDir = args(0)
    val streamingContext = StreamingContext.getOrCreate(checkpointDir, () => createContext("localhost", 8755, checkpointDir, sc))
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
