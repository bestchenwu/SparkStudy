package com.flink.scala.window

import java.lang

import org.apache.flink.api.common.functions.CoGroupFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.util.Collector
import scala.collection.JavaConverters._
import scala.util.control.Breaks.{break, breakable}

/**
  * window Left Inner关联操作实例<br/>
  * 可参见https://blog.csdn.net/xsdxs/article/details/82750254
  *
  * @author chenwu on 2019.9.12
  */
object WindowLeftJoinTest {
  def main(args: Array[String]): Unit = {
    val delay = 5100L
    val winsize = 10l
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val dataStream1 = env.addSource(new SourceFunction[(String, String, Long)]() {

      var running: Boolean = true

      override def run(ctx: SourceFunction.SourceContext[(String, String, Long)]): Unit = {
        val elements = List[(String, String, Long)](("a", "1", 1000000050000L), ("a", "2", 1000000054000L), ("a", "3", 1000000079900L), ("a", "4", 1000000115000L), ("b", "5", 1000000100000L), ("b", "6", 1000000108000L))
        breakable {
          for (tuple <- elements) {
            if (!running) {
              break()
            }
            ctx.collect(tuple)
            //模拟窗口时间
            Thread.sleep(1000)
          }
        }

      }

      override def cancel(): Unit = {
        running = false
      }
    }).name("source1")

    val dataStream2 = env.addSource(new SourceFunction[(String, String, Long)] {

      var running: Boolean = true;

      override def run(ctx: SourceFunction.SourceContext[(String, String, Long)]): Unit = {
        val elements = List[(String, String, Long)](("a", "hangzhou", 1000000059000L), ("b", "beijing", 1000000105000L))
        breakable {
          for(element <- elements){
            if(!running){
              break
            }
            ctx.collect(element)
            //模拟窗口时间
            Thread.sleep(1000)
          }
        }
      }

      override def cancel(): Unit = {
        running = false
      }
    }).name("source2")
    val leftStream = dataStream1.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(String,String,Long)](Time.milliseconds(delay)){
      override def extractTimestamp(element: (String, String, Long)): Long = {
        element._3
      }
    })
    val rightStream = dataStream2.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[(String,String,Long)](Time.milliseconds(delay)){
      override def extractTimestamp(element: (String, String, Long)): Long = {
        element._3
      }
    })

    //left join操作
    val leftJoinStream = leftStream.coGroup(rightStream).where(_._1).equalTo(_._1).window(TumblingEventTimeWindows.of(Time.seconds(winsize)))
      .apply(new LeftJoinFunction())
    leftJoinStream.print()
    env.execute("WindowLeftJoinTest")
  }

  class LeftJoinFunction extends CoGroupFunction[(String,String,Long),(String,String,Long),(String,String,String,Long,Long)]{
    override def coGroup(first: lang.Iterable[(String, String, Long)], second: lang.Iterable[(String, String, Long)], out: Collector[(String, String, String, Long, Long)]): Unit = {
        val firstList = first.asScala.toList
        val secondList = second.asScala.toList
        for(firstItem<-firstList){
           var hasItems = false
           for(secondItem<-secondList){
             out.collect((firstItem._1,firstItem._2,secondItem._2,firstItem._3,secondItem._3))
             hasItems = true
           }
           if(!hasItems){
              out.collect((firstItem._1,firstItem._2,"null",firstItem._3,-1))
           }
        }
      //val firstList =
//        for(firstItem<-first){
//            var rightHasElements  = false;
//            for(secondItem<-second){
//              rightHasElements = true;
//              //out.collect(firstItem.)
//            }
//            if(!rightHasElements){
//              out.collect(firstItem)
//            }
//        }
    }
  }

}
