package com.flink.scala

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * flink的hello world sample
  *
  * @author chenwu on 2019.8.18
  */
object WordCountSample {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost",9999)
    // W表示任何非字母或者字符
    val count = text.flatMap(_.toLowerCase.split("\\W+")).filter(_.nonEmpty)
      .map((_,1))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)
    count.print()
    env.execute("WordCountSample")
  }

}
