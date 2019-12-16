package com.flink.scala2.operaters

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * 综合使用Flink的reduce和process function
  *
  * @author chenwu on 2019.12.16
  */
object ReduceAndProcessFuntionTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val stream = env.fromElements(("a", 1l, 1000000050025L), ("a", 2l, 1000000050010L), ("b", 5l, 1000000050020L), ("b", 8l, 1000000050030L), ("c", 15l, 1000000050040L))
    val watermarkStream = stream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[(String, Long, Long)] {
      override def extractAscendingTimestamp(element: (String, Long, Long)): Long = element._3
    })
    val minItemInWindowStream = watermarkStream.keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(5l))).reduce((item1, item2) => {
      //取窗口内时间最小的元素
      if (item1._3 < item2._3) {
        item1
      } else {
        item2
      }
    }, (key: String, window: TimeWindow, items: Iterable[(String, Long, Long)], out: Collector[(Long, (String, Long, Long))]) => {
      val minItem = items.iterator.next()
      out.collect((window.getStart, minItem))
    })
    minItemInWindowStream.print()
    env.execute("ReduceAndProcessFuntionTest")
  }
}
