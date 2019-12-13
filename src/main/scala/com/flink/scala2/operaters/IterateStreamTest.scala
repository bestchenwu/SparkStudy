package com.flink.scala2.operaters

import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
  * Flink的Iterate算子测试类
  *
  * @author chenwu on 2019.12.13
  */
object IterateStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val intStream = env.fromCollection(Seq(1, 3, 5, 8, 9))
    val iterativeStream = intStream.iterate(iteration => {
      val iterationBody = iteration.map(i => i)
      //查看iterate流的源代码可知,前面一部分在获取之后就关闭,只返回后面的内容
      //可见dataStream的336行代码
      //val iterativeStream = stream.iterate(maxWaitTimeMillis)
      //val (feedback, output) = stepFunction(new DataStream[T](iterativeStream))
      //iterativeStream.closeWith(feedback.javaStream)
      //output
      (iterationBody.filter(_ % 2 == 0), iterationBody.filter(_ % 2 != 0))
    })
    iterativeStream.print()
    env.execute("IterateStreamTest")
  }
}
