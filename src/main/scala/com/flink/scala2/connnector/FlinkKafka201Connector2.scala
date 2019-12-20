package com.flink.scala2.connnector

import java.util.Properties

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition

/**
  * 模仿{@link FlinkKafka201Connector}
  *
  * @author chenwu on 2019.12.18
  */
object FlinkKafka201Connector2 {

  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("group.id", "test-210-flink")
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val kafkaConsumer = new FlinkKafkaConsumer[MyEvent]("test-210", new MyEventDeserializationSchema2(), properties)
    //kafkaConsumer.setStartFromEarliest() //从最早开始消费
    //kafkaConsumer.setStartFromTimestamp(11)
    //精准化指定kafka消费的各个partition的offset
    //    val specificStartOffsets = new java.util.HashMap[KafkaTopicPartition, java.lang.Long]()
    //    specificStartOffsets.put(new KafkaTopicPartition("test-210",0),23l)
    //    kafkaConsumer.setStartFromSpecificOffsets(specificStartOffsets)
    val kafkaStream = env.addSource(kafkaConsumer)
    val minDataStream = kafkaStream.keyBy(_.data).min("time")
    minDataStream.print()
    env.execute("FlinkKafka201Connector2")
  }
}
