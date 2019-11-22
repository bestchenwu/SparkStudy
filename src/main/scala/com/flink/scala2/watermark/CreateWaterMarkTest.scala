package com.flink.scala2.watermark

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * 生成时间戳
  *
  * @author chenwu on 2019.11.21
  */
class CreateWaterMarkTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment()
      env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
  }

}
