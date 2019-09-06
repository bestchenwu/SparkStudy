package com.flink.scala.watermark

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{TimeCharacteristic, watermark}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
/**
  * 参考https://blog.csdn.net/xorxos/article/details/80715113
  *
  * @author chenwu on 2019.9.6
  */
object WatermarkTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val socketStream = env.socketTextStream("127.0.0.1", 8099)
    val mapStream = socketStream.map(item => {
      val array = item.split(",")
      val key = array(0)
      val timeStamp = array(1).toLong
      (key, timeStamp)
    })
    val watermarkStream = mapStream.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[(String,Long)](){
      val watermark:Watermark = null
      var currentMaxTimestamp = 0l
      val maxOffOrderTS = 10*1000l;//允许的乱序最多只有10秒

      override def getCurrentWatermark: Watermark = {
        val a = new Watermark(currentMaxTimestamp-maxOffOrderTS)
        a
      }

      override def extractTimestamp(t: (String,Long), previousElementTimestamp: Long): Long = {
        val timestamp = t._2;
        currentMaxTimestamp = timestamp max currentMaxTimestamp
        println("timestamp:" + t._1 +","+ t._2 +  currentMaxTimestamp +   ","+ watermark.toString)
        timestamp
      }
    })

    val window = watermarkStream.keyBy(_._1).timeWindow(Time.seconds(10l))
    //window.print()
    env.execute("WatermarkTest")
  }
}
