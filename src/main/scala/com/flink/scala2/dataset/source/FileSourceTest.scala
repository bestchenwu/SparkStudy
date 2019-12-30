package com.flink.scala2.dataset.source

import org.apache.flink.api.scala.ExecutionEnvironment

/**
  * 这是介绍flink的文件源的测试
  *
  * @author chenwu on 2019.12.30
  */
object FileSourceTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val fileSourceDataset = env.readTextFile("file:///logs/dataset/readFile.txt")
    val hDataset = fileSourceDataset.filter(_.contains("h"))
    hDataset.print()
    //这里print方法默认情况下会调用execute
    //env.execute("FileSourceTest")
  }
}
