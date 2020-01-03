package com.flink.scala2.bestPractise

import java.io.File

import org.apache.flink.api.java.utils.ParameterTool

object ParameterToolTest {

  def main(args: Array[String]): Unit = {
    val file = new File("D:/logs/flink.properties")
    //      val parameterTool = ParameterTool.fromPropertiesFile(file)
    val parameterTool = ParameterTool.fromArgs(args)
    val username = parameterTool.get("user.name")
    val password = parameterTool.get("user.password")
    println(username)
    println(password)
  }
}
