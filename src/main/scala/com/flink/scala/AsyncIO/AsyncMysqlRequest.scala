package com.flink.scala.AsyncIO

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala._
/**
  * 原文:https://www.cnblogs.com/Springmoon-venn/p/11103558.html
  * 官方文档:https://flink.sojb.cn/dev/stream/operators/asyncio.html
  *
  * @author chenwu on 2019.9.22
  */
object AsyncMysqlRequest {

  def main(array:Array[String]): Unit ={
      val env = StreamExecutionEnvironment.getExecutionEnvironment
      val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "test-flink")
      val kafkaStream = env.addSource(new FlinkKafkaConsumer010[String]("test-010",SimpleStringSchema,properties))
      kafkaStream.flatMap(_.split("\\W+"))
  }
}
