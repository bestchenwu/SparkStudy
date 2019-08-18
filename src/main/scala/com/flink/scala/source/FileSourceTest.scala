package com.flink.scala.source

import java.io.File

import org.apache.flink.api.common.io.FileInputFormat
import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment


/**
  * 读取文件的流测试
  *
  * @author chenwu on 2019.8.18
  */
object FileSourceTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //val fileStream = env.readTextFile("/data/problem/problem.txt")
    //引入这个类是使用了scala的macro模型,由于flink里面都是使用的TypeInformation
    import org.apache.flink.streaming.api.scala.createTypeInformation
    //这种模式可以读取任意的文件格式
    val fileStream = env.readFile(new TextInputFormat(new Path()),"/data/problem/problem.txt")
    val wordCount = fileStream.flatMap(_.split("\\W+")).filter(_.nonEmpty).map((_, 1)).keyBy(0).sum(1)
    wordCount.print()
    env.execute("FileSourceTest")
  }
}
