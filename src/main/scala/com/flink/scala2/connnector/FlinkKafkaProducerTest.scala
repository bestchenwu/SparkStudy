package com.flink.scala2.connnector

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
  * 写入消息到kafka的测试类
  *
  * @author chenwu on 2019.12.20
  */
object FlinkKafkaProducerTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val dataStream = env.fromCollection(Seq[String]("hello1", "world1", "sweet1"))
    val kafkaProducer = new FlinkKafkaProducer[String]("127.0.0.1:9092", "test-210", new SimpleStringSchema())
    kafkaProducer.setWriteTimestampToKafka(true)
    dataStream.addSink(kafkaProducer)
    env.execute("FlinkKafkaProducerTest")
  }
}
