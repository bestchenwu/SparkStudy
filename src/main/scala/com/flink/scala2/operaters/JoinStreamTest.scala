package com.flink.scala2.operaters

import org.apache.flink.api.common.functions.JoinFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.{TimeWindow, Window}

/**
  * 测试flink的join算子
  *
  * @author chenwu on 2019.12.12
  */
object JoinStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val dataStream1 = env.fromElements((1, 2), (2, 3), (3, 4))
    dataStream1.assignAscendingTimestamps(item=>item._1)
    val dataStream2 = env.fromElements((1, 20), (2, 30), (3, 40))
    dataStream2.assignAscendingTimestamps(item=>item._1)
    val joinStream = dataStream1.join(dataStream2).where(_._1).equalTo(_._1).window(TumblingEventTimeWindows.of(Time.seconds(5l)))
    val joinResult = joinStream.apply(new JoinFunction[(Int, Int), (Int, Int), (Int, Int, Int)] {
      override def join(first: (Int, Int), second: (Int, Int)): (Int, Int, Int) = {
        (first._1, first._2, second._2)
      }
    })
    joinResult.print()
    env.execute("JoinStreamTest")
  }

}
