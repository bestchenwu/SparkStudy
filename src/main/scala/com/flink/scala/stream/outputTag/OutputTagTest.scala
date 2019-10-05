package com.flink.scala.stream.outputTag

import org.apache.commons.lang.math.NumberUtils
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector
/**
  * 旁路输出
  *
  * 原文可以参考:https://flink.sojb.cn/dev/stream/side_output.html
  *
  * @author chenwu on 2019.9.29
  */
object OutputTagTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(1)
      val outputTag = new OutputTag[String]("side-output")
      val fileStream = env.readTextFile("/data/problem/brand.txt")
      val dataStream = fileStream.flatMap(_.split("\\s+")).filter(!NumberUtils.isDigits(_))
      val afterProcessStream = dataStream.process(new ProcessFunction[String,String] {
        override def processElement(input: String, context: ProcessFunction[String, String]#Context, collector: Collector[String]): Unit = {
            collector.collect(input)
            if(input.startsWith("b")){
              context.output(outputTag,"sideout-"+input)
            }
        }
      })
      //afterProcessStream.print()
      val outputTagStream = afterProcessStream.getSideOutput(outputTag)
     outputTagStream.print()
      env.execute("OutputTagTest")
      //val input:DataStream[String] =
  }
}
