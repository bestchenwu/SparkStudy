package com.flink.scala2.watermark.kafkaSource

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 测试kafka0.11链接
  *
  * @author chenwu on 2019
  */
object Kafka011ConnectorTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "test-210-flink")
    //todo:这是一个基础版本,使用kafka自带的字符串解析
//    val kafka011Source = new FlinkKafkaConsumer[String]("test-210", new SimpleStringSchema(), properties)
//    val flinkStream = env.addSource(kafka011Source)
//    val wordCountStream = flinkStream.flatMap(_.split("\\W+")).map((_, 1)).keyBy(0).timeWindowAll(Time.seconds(5l)).sum(1)
//    wordCountStream.print()
//    env.execute("Kafka011ConnectorTest")
  }

}
