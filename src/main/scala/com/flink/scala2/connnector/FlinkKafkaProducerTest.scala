package com.flink.scala2.connnector

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer

/**
  * 写入消息到kafka的测试类
  *
  * @author chenwu on 2019.12.20
  */
object FlinkKafkaProducerTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(1)
      env.fromCollection(Seq())
      val kafkaProducer = new FlinkKafkaProducer[String]("127.0.0.1:9092","test-210",new SimpleStringSchema())

  }
}
