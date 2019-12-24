package com.flink.scala2.connnector

import java.util.Properties

import org.apache.flink.api.common.serialization
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
  * 从topic里读取数据,并使用exactly-once模式发送消息到kafka
  *
  * 参考文章:https://www.jianshu.com/p/9641c858ff62
  * @author chenwu on 2019.12.24
  */
object KafkaExactlyOnceProducerTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("group.id", "flink-exactly-once")
    val kafkaConsumer = new FlinkKafkaConsumer[String]("test-210", new SimpleStringSchema(), properties)
    val kafkaStream = env.addSource(kafkaConsumer)
    //默认情况下使用至少一次的发送模式
    val kafkaProducer = new FlinkKafkaProducer[String]("test-211", new KeyedSerializationSchemaWrapper[String](new serialization.SimpleStringSchema()), properties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE)
    kafkaStream.addSink(kafkaProducer)
    env.execute("KafkaExactlyOnceProducerTest")
  }
}
