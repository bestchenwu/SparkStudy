package com.flink.scala2.operaters

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * flink的窗口计算函数测试类
  *
  * @author chenwu on 2019.12.16
  */
object ProcessWindowFunctionTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val stream = env.fromElements(("a", 1l, 1000000050000L), ("a", 2l, 1000000050010L), ("b", 5l, 1000000050020L), ("b", 8l, 1000000050030L), ("c", 15l, 1000000050040L))
    val watermarkStream = stream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[(String, Long, Long)] {
      override def extractAscendingTimestamp(element: (String, Long, Long)): Long = element._3
    })
    val addSumStream = watermarkStream.keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(5l))).process(new AddSumProcessWindowFunction)
    addSumStream.print()
    env.execute("AggregationTest")
  }
}

/**
  * 求总和的聚合函数
  */
class AddSumProcessWindowFunction extends ProcessWindowFunction[(String, Long, Long), Long, String, TimeWindow] {


  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    println("open window")
  }

  override def process(key: String, context: Context, elements: Iterable[(String, Long, Long)], out: Collector[Long]): Unit = {
    println("process key:"+key)
    val sumResult = elements.foldRight(0l)((a: (String, Long, Long), b: Long) => a._2 + b)
    out.collect(sumResult)
  }

  override def close(): Unit = {
      super.close()
      println("close window")
  }

}
