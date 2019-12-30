package com.flink.scala2.dataset.source

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

/**
  * 文件目录测试<br/>
  * 当输入路径是目录时候,枚举目录下的文件
  *
  * @author chenwu on 2019.12.30
  */
object FileDirectoryTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val paremeters = new Configuration()
    //默认情况下flink fileSource不会枚举嵌套文件,添加此参数后会递归遍历目录
    paremeters.setBoolean("recursive.file.enumeration", true)
    val files = env.readTextFile("file:///logs/dataset/files/").withParameters(paremeters)
    files.print()
  }
}
