package com.flink.scala2.watermark

import java.util.Properties

import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
//实际上是引入了包对象org.apache.flink.streaming.api.scala的createTypeInformation方法
import org.apache.flink.streaming.api.scala._

/**
  * 自定义kafka schema
  */
object SelfDefinedKafkaWaterMark {

  def main(args: Array[String]): Unit = {
    val kafkaSource = new FlinkKafkaConsumer[MyEvent[String]]("topic", new MyEventDeserializationSchema[String](), new Properties());
    kafkaSource.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[MyEvent[String]] {
      override def extractAscendingTimestamp(element: MyEvent[String]): Long = element.time
    })
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    val stream = env.addSource(kafkaSource)
    val outputStream = stream.map(item => item.data).keyBy(1).timeWindow(Time.seconds(10l)).reduce(_ + _)
    outputStream.print()
    env.execute("KafkaWaterMark")
  }
}
