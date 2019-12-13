package com.flink.scala2.operaters

import java.lang

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala.{OutputTag, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.util.Collector

/**
  * 测试flink的split算子<br/>
  * 从flink1.7以后 使用sideout来代替以前的split<br/>
  * 可以参考:https://www.cnblogs.com/jason1990/p/11610130.html
  *
  * @author chenwu on 2019.12.13
  */
object SplitStreamTest {

  private val ODD_TAG: OutputTag[String] = new OutputTag[String]("ODD")
  //奇数
  private val EVEN_TAG: OutputTag[String] = new OutputTag[String]("EVEN") //偶数

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val intStream = env.fromCollection(Seq(1, 2, 4, 5, 6, 8, 9))
    val splitStream = intStream.process(new ProcessFunction[Int, String] {
      override def processElement(value: Int, ctx: ProcessFunction[Int, String]#Context, out: Collector[String]): Unit = {
        if (value % 2 == 0) {
          ctx.output(EVEN_TAG, (value + 5).toString)
        } else {
          ctx.output(ODD_TAG, (value + 10).toString)
        }
      }
    }).name("SplitStream")
    val odd_stream = splitStream.getSideOutput(ODD_TAG)
    val even_stream = splitStream.getSideOutput(EVEN_TAG)
    //println("odd_stream")
    odd_stream.print()
    //println("even_stream")
    //even_stream.print()
    env.execute("SplitStreamTest")
  }
}
