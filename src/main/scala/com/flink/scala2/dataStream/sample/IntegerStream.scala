package com.flink.scala2.sample

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object IntegerStream {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val integerStream = env.generateSequence(0, 10)
    val iterateStream = integerStream.iterate(
      iteration => {
        val iteration_1 = iteration.map(_ - 5)
        val lowerThan0 = iteration_1.filter(_ < 0)
        val higherThan0 = iteration_1.filter(_ >= 0)
        (lowerThan0, higherThan0)
      })
    //todo:为什么<5的那部分没有打印出来
    iterateStream.print()
    env.execute("IntegerStream")
  }
}
