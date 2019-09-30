package com.flink.scala.dataset.zipWithIndex

import org.apache.flink.api.java.utils.DataSetUtils
import org.apache.flink.api.scala._

/**
  * ZipWithIndex 压缩
  *
  * @author chenwu on 2019.9.30
  */
object ZipWithIndexTest {

  def main(args: Array[String]): Unit = {
      val env = ExecutionEnvironment.getExecutionEnvironment
      env.setParallelism(2)
      val input:DataSet[String] = env.fromElements("A","B","C","D")
      //todo:scala版本没有zipWithIndex方法
      //DataSetUtils.zipWithIndex(input)
      //input
  }
}
