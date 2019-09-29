package com.flink.scala.stream.watermark

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object KafkaWatermarkTest1 {

  def main(args: Array[String]): Unit = {
    val property = new Properties()
    property.setProperty("bootstrap.servers", "localhost:9092")
    property.setProperty("group.id", "test-flink")
    //todo:使用kafka 2.0.1
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
//    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
//    val kafkaSourceStream = env.addSource(new FlinkKafkaConsumer010[String]("test-0", new SimpleStringSchema(), property))
//    val kafkaStream = kafkaSourceStream.map(item => {
//      val array = item.split(",")
//      val (id, value, timestamp) = (array(0), array(1).toLong, array(2).toInt)
//      (id, value, timestamp)
//    })
//    val waterStream = kafkaStream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[(String,Long,Int)]{
//      override def extractAscendingTimestamp(element: (String, Long, Int)): Long = element._2
//    })
//    val sumStream = waterStream.keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(3l)))
//    sumStream.apply(new WindowFunctionTest())
    env.execute("KafkaWatermarkTest1")

    /**
      * 依次输入
      * 000001,1461756862000,1
      * 000001,1461756863000,2
      * 000001,1461756864000,3
      * 000001,1461756865000,4
      * 000001,1461756866000,5
      * 000001,1461756867000,6
      * 000001,1461756868000,7
      * 000001,1461756869000,8
      * 000001,1461756870000,9
      */
    /**
      * 依次输出:
      * sum=3
      * list:List((000001,1461756862000,1), (000001,1461756863000,2)),window:1461756861000-1461756864000
      * sum=12
      * list:List((000001,1461756864000,3), (000001,1461756865000,4), (000001,1461756866000,5)),window:1461756864000-1461756867000
      * sum=21
      * list:List((000001,1461756867000,6), (000001,1461756868000,7), (000001,1461756869000,8)),window:1461756867000-1461756870000
      */
  }

  /**
    * collector 依次输出key，窗口内元素个数，窗口内最早元素的时间，窗口内最晚元素的时间，窗口自身开始时间，窗口自身结束时间
    *
    * @author chenwu on 2019.9.7
    */
  class WindowFunctionTest extends WindowFunction[(String, Long,Int), (String, Int, String, String, String, String), String, TimeWindow] {
    override def apply(key: String, window: TimeWindow, input: Iterable[(String, Long,Int)], out: Collector[(String, Int, String, String, String, String)]): Unit = {
      val list = input.toList.sortBy(_._2)
      val sum = input.toList.map(_._3).sum
      println(s"sum=${sum}")
      println(s"list:${input.toList},window:${window.getStart}-${window.getEnd}")
      out.collect(key, input.size, list.head._2.toString, list.last._2.toString, window.getStart.toString, window.getEnd.toString)
    }
  }
}
