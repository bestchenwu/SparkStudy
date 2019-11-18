package com.flink.scala2.sample

import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.streaming.api.functions.source.FileProcessingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 这里实际上使用了package.scala包里面的createTypeInformation匿名方法
  */

import org.apache.flink.streaming.api.scala._

/**
  * 单词计数案例
  *
  * @author chenwu on 2019.11.18
  */
object WordCountSample {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    //val socketStream = env.socketTextStream("127.0.0.1", 9999)
    val file_path="D:\\logs\\1.txt"

    //val textStream = env.readFile(inputFormat=new TextInputFormat(file_path),filePath=file_path,watchType=FileProcessingMode.PROCESS_CONTINUOUSLY,interval=2l)
    //val wordStream = textStream.flatMap(_.split("\\W+")).filter(_.nonEmpty).map((_, 1)).keyBy(0).sum(1)
    val wordStream = env.readTextFile(file_path).flatMap(_.split("\\W+")).filter(_.nonEmpty).map((_, 1)).keyBy(0).timeWindow(Time.seconds(2l)).sum(1)
    wordStream.print()
    env.execute("WordCountSample")
  }
}
