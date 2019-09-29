package com.flink.scala.stream.source

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * scala 序列源的测试
  *
  * @author chenwu on 2019.8.18
  */
object SeqScalaTest {
  def main(args: Array[String]): Unit = {
    //val env = StreamExecutionEnvironment.getExecutionEnvironment
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    val longStream = env.generateSequence(1l,100l).filter(_%7l==0).map(_*2)
    longStream.print()
    env.execute("SeqScalaTest")
  }
}
