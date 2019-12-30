package com.flink.scala2.dataset.sink

import java.io.File

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  * 将结果写到文件上面
  *
  * @author chenwu on 2019.12.30
  */
object FileSinkTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val wordDataSet = env.fromElements("hello", "world", "joking")
    wordDataSet.map(item => item.toUpperCase).writeAsText("file:///logs/dataset/files/word.txt", WriteMode.OVERWRITE)
    env.execute("FileSinkTest")
  }
}
