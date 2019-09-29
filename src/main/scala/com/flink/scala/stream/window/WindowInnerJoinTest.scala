package com.flink.scala.stream.window

import org.apache.flink.api.common.functions.JoinFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

import util.control.Breaks._

/**
  * window Inner关联操作实例<br/>
  * 可参见https://blog.csdn.net/xsdxs/article/details/82750254
  *
  * @author chenwu on 2019.9.12
  */
object WindowInnerJoinTest {
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

    //join操作
    val joinStream = leftStream.join(rightStream).where(_._1).equalTo(_._1).window(TumblingEventTimeWindows.of(Time.seconds(winsize))).apply(new JoinFunction[(String,String,Long),(String,String,Long),(String,String,String,Long,Long)]  {
      override def join(first: (String, String, Long), second: (String, String, Long)): (String, String, String, Long, Long) = {
        (first._1,first._2,second._2,first._3,second._3)
      }
    })
    joinStream.print()

    /**
      * 当delay=5000L的时候,输出:
      * 2> (a,1,hangzhou,1000000050000,1000000059000)
      * 2> (a,2,hangzhou,1000000054000,1000000059000)
      */
    /**
      * 当delay=5100L的时候,输出:
      *
      * 2> (a,1,hangzhou,1000000050000,1000000059000)
      * 2> (a,2,hangzhou,1000000054000,1000000059000)
      * 1> (b,5,beijing,1000000100000,1000000105000)
      * 1> (b,6,beijing,1000000108000,1000000105000)
      */
    env.execute("WindowInnerJoinTest")
  }
}
