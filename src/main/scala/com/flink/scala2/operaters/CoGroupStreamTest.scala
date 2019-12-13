package com.flink.scala2.operaters

import java.lang

import org.apache.flink.api.common.functions.CoGroupFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.util.Collector

import scala.collection.JavaConversions._

/**
  * Flink的CoGroup测试
  */
object CoGroupStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val dataStream1 = env.fromElements((1, 2, 1000000050000L), (1, 50, 1000000050008L),(2, 3, 1000000050002L), (3, 4, 1000000079900L))
    val leftStream = dataStream1.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(Int, Int, Long)](Time.milliseconds(5100l)) {
      override def extractTimestamp(element: (Int, Int, Long)): Long = {
        element._3
      }
    })
    //dataStream1.assignAscendingTimestamps(item=>item._1)
    val dataStream2 = env.fromElements((1, 20, 1000000059000L), (2, 30, 1000000059002L))
    val rightStream = dataStream2.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(Int, Int, Long)](Time.milliseconds(5100l)) {
      override def extractTimestamp(element: (Int, Int, Long)): Long = {
        element._3
      }
    })
    val cogroupStream = leftStream.coGroup(rightStream).where(_._1).equalTo(_._1).window(TumblingEventTimeWindows.of(Time.seconds(10l)))
    val joinResult = cogroupStream.apply(new CoGroupFunction[(Int, Int, Long), (Int, Int, Long), (Int, Int, Int, Long)] {
      override def coGroup(first: lang.Iterable[(Int, Int, Long)], second: lang.Iterable[(Int, Int, Long)], out: Collector[(Int, Int, Int, Long)]): Unit = {
        val firstIterable = iterableAsScalaIterable(first)
        val first1 = firstIterable.foldRight("")((item, str) => str + "," + item._1 + ":" + item._2)
        val secondIterable = iterableAsScalaIterable(second)
        val second1 = secondIterable.foldRight("")((item,str) => str + "," + item._1 + ":" + item._2)
        println("first1:"+first1)
        println("second1:"+second1)
        if(firstIterable.nonEmpty){
          val firstIterator = firstIterable.iterator.next()
          if(secondIterable.nonEmpty){
            val secondIterator = secondIterable.iterator.next()
            out.collect((firstIterator._1,firstIterator._2,secondIterator._2,firstIterator._3))
          }else{
            out.collect((firstIterator._1,firstIterator._2,-1,-1l))
          }
        }
      }
    })
    joinResult.print()
    env.execute("CoGroupStreamTest")
  }
}
