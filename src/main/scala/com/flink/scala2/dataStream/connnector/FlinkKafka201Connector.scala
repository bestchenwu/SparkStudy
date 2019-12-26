package com.flink.scala2.connnector

import java.util.Properties

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.streaming.api.{TimeCharacteristic, watermark}
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * 使用flink连接kafka2.0.1,并自定义消息解析器
  *
  * @author chenwu on 2019.12.5
  */
object FlinkKafka201Connector {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "test-210-flink")
    val kafkaSource = new FlinkKafkaConsumer[MyEvent]("test-210", new MyEventDeserializationSchema(), properties)
    //使用kafka自带的时间作为watermark
    //todo:单调递增没有测试通过
    //    kafkaSource.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[MyEvent]() {
    //      override def extractAscendingTimestamp(element: MyEvent): Long = {
    //        element.time
    //      }
    //    })
    kafkaSource.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[MyEvent]() {

      var currentTimeStamp: Long = 0l
      //最多允许十秒的延迟
      val maxOfOrder = 500l

      override def getCurrentWatermark: Watermark = {
        new watermark.Watermark(currentTimeStamp - maxOfOrder)
      }

      override def extractTimestamp(element: MyEvent, previousElementTimestamp: Long): Long = {
        val timeStamp = element.time
        currentTimeStamp = timeStamp max currentTimeStamp
        timeStamp
      }
    })
    val kafkaStream = env.addSource(kafkaSource)
    //这里使用滑动窗口
    //todo:keyby(fields:int*)得到的是一个KeyedStream[T, JavaTuple]
    //todo:keyby(T:=>K)的得到是一个KeyedStream[T,K]
    val wordCountStream = kafkaStream.map(item => (item.data, item.time)).keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(1l)))
    wordCountStream.apply(new WordCountWindowFunction()).print()
    env.execute("FlinkKafka201Connector")
  }

  class WordCountWindowFunction extends WindowFunction[(String, Long), (String, Long,Long), String, TimeWindow] {
    override def apply(key: String, window: TimeWindow, input: Iterable[(String, Long)], out: Collector[(String, Long,Long)]): Unit = {
        val list = input.toList.sortBy(_._2)
        println(s"list=${list},window start:${window.getStart},window end:${window.getEnd}")
        out.collect((key,list.head._2,list.last._2))
    }
  }

}
