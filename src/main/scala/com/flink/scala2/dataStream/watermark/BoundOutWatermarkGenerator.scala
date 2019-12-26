package com.flink.scala2.watermark

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

/**
  * 乱序的水印生成器
  */
class BoundOutWatermarkGenerator[T] extends AssignerWithPeriodicWatermarks[MyEvent] {

  var currentMaxTimeStamp: Long = _
  //表示最大的乱序允许的延迟时间
  val maxOutOfBoundTime: Long = 3500l

  /**
    *   表示当前水印
    */

  override def getCurrentWatermark: Watermark = {
    new Watermark(currentMaxTimeStamp - maxOutOfBoundTime)
  }

  /**
    *   表示当前事件的事件时间
    */

  override def extractTimestamp(t: MyEvent, l: Long): Long = {
    val timestamp = t.time
    currentMaxTimeStamp = timestamp max currentMaxTimeStamp
    timestamp
  }
}
