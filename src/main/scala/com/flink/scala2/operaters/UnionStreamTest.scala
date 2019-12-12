package com.flink.scala2.operaters

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
  * 测试flink的union算子
  *
  * @author chenwu on 2019.12.12
  */
object UnionStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val dataStream1 = env.fromElements((1, 1), (2, 2), (3, 3))
    val dataStream2 = env.fromElements((1, 10), (2, 2), (3, 30))
    val unionStream = dataStream1.union(dataStream2)
    val outputStream = unionStream.keyBy(0).sum(1)
    /**
      *  为什么把原数组也输出了一遍
      *
      * (1,1)
      * (2,2)
      * (3,3)
      * (1,11)
      * (2,4)
      * (3,33)
      */
    outputStream.print();
    env.execute("JoinStreamTest")
  }
}
