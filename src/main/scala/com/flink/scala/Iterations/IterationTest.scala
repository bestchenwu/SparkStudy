package com.flink.scala.Iterations

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * flink的Iteration测试例子
  *
  * @author chenwu on 2019.8.25
  */
object IterationTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.getExecutionEnvironment
      val stream = env.generateSequence(0,1000)
      //由于流的部分有可能永远无法完成,所以有时候需要进行一次切分,
      val integerStream = stream.iterate(iteration=>{
        val minusOne = iteration.map(_-1)
        val stillGreaterThanZero = minusOne.filter(_>0)
        val lowerThanZero = minusOne.filter(_<0)
        (stillGreaterThanZero,lowerThanZero)
      })
      integerStream.print()
      env.execute("IterationTest")
  }
}
