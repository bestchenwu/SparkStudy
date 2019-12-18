package com.flink.scala2.connnector

import java.util.Properties

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
  * 模仿{@link FlinkKafka201Connector}
  *
  * @author chenwu on 2019.12.18
  */
object FlinkKafka201Connector2 {

  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "FlinkKafka201Connector2")
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val kafkaStream = env.addSource(new FlinkKafkaConsumer[MyEvent]("test-210", new MyEventDeserializationSchema2(), properties))
    val minDataStream = kafkaStream.keyBy(_.data).min("time")
    minDataStream.print()
    env.execute("FlinkKafka201Connector2")
  }
}
