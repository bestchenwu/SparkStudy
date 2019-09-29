package com.flink.scala.stream.watermark

import java.text.SimpleDateFormat

import org.apache.flink.api.common.ExecutionConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{TimeCharacteristic, watermark}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * 参考https://blog.csdn.net/xorxos/article/details/80715113
  *
  * @author chenwu on 2019.9.6
  */
object WatermarkTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //todo:默认是0秒,如果没有设置,会一直调用getCurrentWaterMark方法
    //env.getConfig.setAutoWatermarkInterval(10l)
    val socketStream = env.socketTextStream("127.0.0.1", 8099)
    val mapStream = socketStream.map(item => {
      if(item.isEmpty){
        ("",0l,0)
      }else{
        val array = item.split("\\W+")
        val key = array(0)
        val timeStamp = array(1).toLong
        val value = array(2).toInt
        (key, timeStamp,value)
      }

    })
    val watermarkStream = mapStream.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[(String, Long,Int)]() {
      var currentMaxTimestamp = 0l
      val maxOffOrderTS = 10 * 1000l;

      //允许的乱序最多只有10秒

      override def getCurrentWatermark: Watermark = {
        val watermark = new Watermark(currentMaxTimestamp - maxOffOrderTS)
        //println(s"watermark:${watermark}")
        watermark
      }

      override def extractTimestamp(t: (String, Long,Int), previousElementTimestamp: Long): Long = {

        val timestamp = t._2;
        currentMaxTimestamp = timestamp max currentMaxTimestamp
        //这里的watermark实际上是上一条的watermark
        val currentWaterMark = getCurrentWatermark
        //todo:这里的previousElementTimestamp一直为-9223372036854775808
        //println("previousElementTimestamp="+previousElementTimestamp)
        println("key:" + t._1 + ",timestamp=" + t._2 +",value="+t._3+",currentMaxTimestamp="+ currentMaxTimestamp+",watermark="+currentWaterMark.getTimestamp)
        timestamp
      }
    })

    //val window = watermarkStream.keyBy(_._1).timeWindow(Time.seconds(3l))
    val window = watermarkStream.keyBy(_._1).window(TumblingEventTimeWindows.of(Time.seconds(3l)))
    window.apply(new WindowFunctionTest()).print()
    //window.print()
    //输出:
    /**
    key:000001,timestamp=1461756862000,value=1,currentMaxTimestamp=1461756862000,watermark=1461756852000
    key:000001,timestamp=1461756863000,value=2,currentMaxTimestamp=1461756863000,watermark=1461756853000
    key:000001,timestamp=1461756864000,value=3,currentMaxTimestamp=1461756864000,watermark=1461756854000
    key:000001,timestamp=1461756865000,value=4,currentMaxTimestamp=1461756865000,watermark=1461756855000
    key:000001,timestamp=1461756866000,value=5,currentMaxTimestamp=1461756866000,watermark=1461756856000
    key:000001,timestamp=1461756867000,value=6,currentMaxTimestamp=1461756867000,watermark=1461756857000
    key:000001,timestamp=1461756868000,value=7,currentMaxTimestamp=1461756868000,watermark=1461756858000
    key:000001,timestamp=1461756869000,value=8,currentMaxTimestamp=1461756869000,watermark=1461756859000
    key:000001,timestamp=1461756870000,value=9,currentMaxTimestamp=1461756870000,watermark=1461756860000
    key:000001,timestamp=1461756871000,value=10,currentMaxTimestamp=1461756871000,watermark=1461756861000
    key:000001,timestamp=1461756872000,value=11,currentMaxTimestamp=1461756872000,watermark=1461756862000
    key:000001,timestamp=1461756873000,value=12,currentMaxTimestamp=1461756873000,watermark=1461756863000
    key:000001,timestamp=1461756874000,value=13,currentMaxTimestamp=1461756874000,watermark=1461756864000
      //每一条的watermark实际上是上一条的watermark,所以这里的watermark实际上是1461756864000,正好匹配了窗口1461756864000-1461756864000
    key:000001,timestamp=1461756875000,value=14,currentMaxTimestamp=1461756875000,watermark=1461756865000
      //窗口的触发时间是watermark>=window_end_time
      //同时在窗口window_start_time至window_end_time里有数据存在
      //取窗口内数据时候 取[window_start_time,window_end_time)里的数据
    sum=3
    2> (000001,2,1461756862000,1461756863000,1461756861000,1461756864000)
    key:000001,timestamp=1461756876000,value=15,currentMaxTimestamp=1461756876000,watermark=1461756866000
    key:000001,timestamp=1461756877000,value=16,currentMaxTimestamp=1461756877000,watermark=1461756867000
      //这一条的watermark实际上是1461756867000 匹配了窗口1461756864000-1461756867000
    key:000001,timestamp=1461756878000,value=17,currentMaxTimestamp=1461756878000,watermark=1461756868000
    sum=12
    2> (000001,3,1461756864000,1461756866000,1461756864000,1461756867000)
      //todo:这里输出了两次是什么原因
    sum=7
    2> (000001,2,1461756864000,1461756865000,1461756864000,1461756867000)
    **/
    env.execute("WatermarkTest")
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
      out.collect(key, input.size, list.head._2.toString, list.last._2.toString, window.getStart.toString, window.getEnd.toString)
    }
  }

}
