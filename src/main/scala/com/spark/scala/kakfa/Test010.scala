package com.spark.scala.kakfa

import java.util.Date

import com.spark.common.util.CommonDateUtil
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{KafkaUtils, LocationStrategies, LocationStrategy}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

import scala.Array

/**
  * 消费kafka topic:test-0-10
  */
object Test010 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Test0101Task")
    val ssc = new StreamingContext(conf, Seconds(1))
    val kafkaParams = Map("bootstrap.servers" -> "bootstrap.servers",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test0-10-spark",
      "enable.auto.commit" -> (true: java.lang.Boolean)

    )
    val topics = Array("test-0-10-0")
    val kafkaStream = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, Subscribe[String, String](topics, kafkaParams))
    val aggregateStream = kafkaStream.map(record => (record.value(), 1)).reduce((tuple1, tuple2) => {
      val newCount = tuple1._2 + tuple2._2
      (tuple1._1, newCount)
    })
    val currentTime = CommonDateUtil.parseDateToStringWithSeconds(new Date())
    aggregateStream.saveAsTextFiles("/usr/spark/test010",currentTime)
    ssc.start()
    ssc.awaitTermination()
  }
}
