package com.flink.scala.stream.outputTag

import java.util.concurrent.TimeUnit

import org.apache.commons.lang.math.NumberUtils
import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.common.time.Time
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
      //env.execute("OutputTagTest")
      //查看执行计划,可以打开https://flink.apache.org/visualizer/查看
      //println(env.getExecutionPlan)
      //如果发生故障,系统会尝试重新启动作业3次,并在连续重启尝试之间等待10秒
      env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,Time.of(10,TimeUnit.SECONDS)))
      //val input:DataStream[String] =
  }
}
