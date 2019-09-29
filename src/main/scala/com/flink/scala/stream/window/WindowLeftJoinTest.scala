package com.flink.scala.stream.window

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
    val delay = 5000L
    val winsize = 10l
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val dataStream1 = env.addSource(new SourceFunction[(String, String, Long)]() {

      var running: Boolean = true

      override def run(ctx: SourceFunction.SourceContext[(String, String, Long)]): Unit = {
        val elements = List[(String, String, Long)](("a", "1", 1000000050000L), ("a", "2", 1000000054000L), ("a", "3", 1000000079900L), ("a", "4", 1000000115000L), ("b", "5", 1000000100000L), ("b", "6", 1000000108000L))
        //当delay=5000l的时候,watermark依次为
        //1000000045000   1000000049000   1000000074800  1000000110000  1000000095000 1000000103000
        //窗口依次为1000000050000-1000000060000  1000000060000-1000000070000  1000000070000-1000000080000  1000000080000-1000000090000  1000000090000-1000000100000
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

  /**
    * 通过CoGroupFunction来实现左关联 右关联  全外关联,如果是内联,则使用JoinFunction
    */
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
        //当delay = 5100L时候输出
      /**
        *
        * 2> (a,1,hangzhou,1000000050000,1000000059000)
        * 2> (a,2,hangzhou,1000000054000,1000000059000)
        * 2> (a,3,null,1000000079900,-1)
        * 2> (a,4,null,1000000115000,-1)
        * 1> (b,5,beijing,1000000100000,1000000105000)
        * 1> (b,6,beijing,1000000108000,1000000105000)
        *
        *
        */
        //当delay=5000L时候输出
        //原因是
        /**
          * b 5 1000000100000,b 6  1000000108000 这条数据eventTime晚到了,但是watermark还是与1000000115000相同,均为1000000110000
          * 但是b 5,b 6这两条数据都属于[1000000100000,1000000110000)窗口,由于watermark已经不在这个窗口周期内,所以会被抛弃
          *
          */
        //但是当delay=5100L时候会输出b 5  b 6,是因为:
      /**
        *  b 5 1000000100000,b 6  1000000108000这个时候的watermark = 1000000109900,在window的end范围内
        */

      /**
        * 2> (a,1,hangzhou,1000000050000,1000000059000)
        * 2> (a,2,hangzhou,1000000054000,1000000059000)
        * 2> (a,3,null,1000000079900,-1)
        * 2> (a,4,null,1000000115000,-1)
        */
        //说明
    }
  }

}
