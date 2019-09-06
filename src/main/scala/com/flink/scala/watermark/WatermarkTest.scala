package com.flink.scala.watermark

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.api.scala.createTypeInformation

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
  }
}
