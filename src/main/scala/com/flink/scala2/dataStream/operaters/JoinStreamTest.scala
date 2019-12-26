package com.flink.scala2.operaters

import org.apache.flink.api.common.functions.JoinFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
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
    val dataStream1 = env.fromElements((1, 2,1000000050000L), (2, 3,1000000054000L), (3, 4,1000000079900L))
    val leftStream = dataStream1.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(Int,Int,Long)](Time.milliseconds(5100l)){
      override def extractTimestamp(element: (Int, Int,Long)): Long = {
          element._3
      }
    })
    //dataStream1.assignAscendingTimestamps(item=>item._1)
    val dataStream2 = env.fromElements((1, 20,1000000059000L), (2, 30,1000000105000L))
    val rightStream = dataStream2.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(Int,Int,Long)](Time.milliseconds(5100l)){
      override def extractTimestamp(element: (Int, Int,Long)): Long = {
        element._3
      }
    })
    val joinStream = leftStream.join(rightStream).where(_._1).equalTo(_._1).window(TumblingEventTimeWindows.of(Time.seconds(10l)))
    val joinResult = joinStream.apply(new JoinFunction[(Int, Int,Long), (Int, Int,Long), (Int, Int, Int,Long)] {
      override def join(first: (Int, Int,Long), second: (Int, Int,Long)): (Int, Int, Int,Long) = {
        (first._1, first._2, second._2,first._3)
      }
    })
    //输出(1,2,20,1000000050000)
    joinResult.print()
    env.execute("JoinStreamTest")
  }

}
