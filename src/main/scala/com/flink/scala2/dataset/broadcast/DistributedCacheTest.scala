package com.flink.scala2.dataset.broadcast

import java.io.File

import com.common.CommonFileUtil
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, createTypeInformation}
import org.apache.flink.configuration.Configuration

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * flink 分布式缓存的工具使用
  *
  * @author chenwu on 2019.12.30
  */
object DistributedCacheTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    env.registerCachedFile("file:///logs/dataset/files/word.txt", "localFile")
    val input: DataSet[String] = env.fromElements("raoshanshan")
    val result = input.map(new RichMapFunction[String, String] {

      var fileCache: mutable.Buffer[String] = mutable.Buffer[String]()

      override def open(parameters: Configuration): Unit = {
        val file: File = getRuntimeContext.getDistributedCache.getFile("localFile")
        fileCache = asScalaBuffer(CommonFileUtil.readFile(file.getAbsolutePath, false))
      }

      override def map(value: String): String = {
        fileCache.map(item => item + " " + value).mkString(",")
      }
    })
    result.print()
  }
}
