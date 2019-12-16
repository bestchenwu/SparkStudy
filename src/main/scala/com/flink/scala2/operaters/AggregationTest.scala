package com.flink.scala2.operaters

import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.{AscendingTimestampExtractor, BoundedOutOfOrdernessTimestampExtractor}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * flink聚合函数的测试类
  *
  * @author chenwu on 2019.12.16
  */
object AggregationTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val stream = env.fromElements(("a", 1l,1000000050000L), ("a", 2l,1000000050010L), ("b", 5l,1000000050020L), ("b",8l, 1000000050030L), ("c", 15l,1000000050040L))
    val watermarkStream = stream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor[(String,Long,Long)]{
      override def extractAscendingTimestamp(element: (String, Long, Long)): Long = element._3
    })
    val aggregateStream = watermarkStream.keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(5l))).aggregate(new AverageAggregateFunction)
    aggregateStream.print()
    env.execute("AggregationTest")
  }

}

/**
  * 求平均值的窗口聚合函数
  *
  * @author chenwu on 2019.12.16
  */
class AverageAggregateFunction extends AggregateFunction[(String, Long,Long), (Long, Long), Float] {
  override def createAccumulator(): (Long, Long) = (0l, 0l)

  override def add(value: (String, Long,Long), accumulator: (Long, Long)): (Long, Long) = (accumulator._1 + value._2, accumulator._2+1l)

  override def getResult(accumulator: (Long, Long)): Float = accumulator._1.toFloat / accumulator._2

  override def merge(a: (Long, Long), b: (Long, Long)): (Long, Long) = (a._1 + b._1, a._2 + b._2)
}
