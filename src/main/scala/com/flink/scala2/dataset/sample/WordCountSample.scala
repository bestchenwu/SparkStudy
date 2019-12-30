package com.flink.scala2.dataset.sample

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.createTypeInformation

/**
  * 批处理的wordCount例子<br/>
  * 正则表达式例子:https://www.runoob.com/java/java-regular-expressions.html
  *
  * @author chenwu on 2019.12.30
  */
object WordCountSample {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val dataSet = env.fromElements("Who's there?", "I think I hear them. Stand, ho! Who's there?")
    //匹配所有非字母和数字的字符,等价于[^a-zA-Z0-9_],与之相对于的是\w  匹配所有字母和数字
    val resultSet = dataSet.flatMap(_.split("\\W+")).map((_, 1)).groupBy(0).sum(1)
    resultSet.print()
    env.execute("WordCountSample")
  }
}
