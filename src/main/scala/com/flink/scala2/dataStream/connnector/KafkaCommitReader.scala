package com.flink.scala2.connnector


import java.time.Duration
import java.util.{Properties}

import com.common.CommonDateUtil
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConversions._

/**
  * 只读取提交的消息
  *
  * @author chenwu on 2019.12.24
  */
object KafkaCommitReader {

  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("group.id", "flink-commit-read")
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("isolation.level", "read_committed")
    val consumer = new KafkaConsumer[String, String](properties)
    consumer.subscribe(List("test-211"))
    while (true) {
      val records = consumer.poll(Duration.ofMillis(100))
      val nowDate = CommonDateUtil.getNowTimeBySeconds
      for (record <- records) {
        println(s"read commit records,date is ${nowDate},record is ${record}")
      }
    }
  }
}
