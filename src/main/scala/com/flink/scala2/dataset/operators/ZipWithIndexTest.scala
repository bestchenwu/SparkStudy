package com.flink.scala2.dataset.operators

import org.apache.flink.api.java.utils.DataSetUtils
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, createTypeInformation}

/**
  * flink 数据集中压缩数据元
  *
  * @author chenwu on 2020.1.2
  */
object ZipWithIndexTest {

  def main(args: Array[String]): Unit = {
      val env = ExecutionEnvironment.createLocalEnvironment(2)
      val input:DataSet[String]  = env.fromElements("A","B","C","D")
    //todo:scala怎么使用zipWithIndex
    //DataSetUtils.zipWithIndex(input)
      //
  }
}
