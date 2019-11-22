package com.flink.scala2.watermark

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

/**
  * 如果事件是有序的，那么适合使用如下的有序watermark生成器
  *
  * @tparam T
  */
class InOrderWatermarkGenerator[T] extends AssignerWithPeriodicWatermarks[MyEvent[T]] {

  val maxOutOfOrderTimeStamp = 3500l

  override def getCurrentWatermark: Watermark = {
    new Watermark(System.currentTimeMillis() - maxOutOfOrderTimeStamp)
  }

  override def extractTimestamp(element: MyEvent[T], previousElementTimestamp: Long): Long = {
    element.time
  }
}
