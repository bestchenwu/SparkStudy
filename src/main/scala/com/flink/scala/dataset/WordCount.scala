package com.flink.scala.dataset

import org.apache.flink.api.scala._

/**
  * 批处理的例子<br/>
  * https://flink.sojb.cn/dev/batch/
  *
  * @author chenwu on 2019.9.29
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val text = env.fromElements("Who 's there ?", "I think I hear them. Stand, ho! Who's there?")
    val nonEmptyData = text.flatMap(_.split("\\W+")).filter(_.nonEmpty)
    nonEmptyData.print()
    //批处理  不用执行execute
    //env.execute("WordCount")
  }
}
