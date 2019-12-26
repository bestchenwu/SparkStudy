package com.flink.scala2.outputTag

import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala.{OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.util.Collector

/**
  * flink旁路输出的例子
  *
  * @author chenwu on 2019.12.26
  */
object OutputTagTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val outputTag = new OutputTag[Int]("sideout")
    val intStream = env.fromCollection(Seq[Int](1, 5, 7, 8, 9))
    val mainStream = intStream.process(new ProcessFunction[Int, String] {
      override def processElement(value: Int, ctx: ProcessFunction[Int, String]#Context, out: Collector[String]): Unit = {
          if(value%2!=0){
              ctx.output(outputTag,value)
          }
      }
    })
    mainStream.getSideOutput(outputTag).print()
    env.execute("OutputTagTest")
  }
}
