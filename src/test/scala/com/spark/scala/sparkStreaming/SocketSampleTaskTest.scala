package com.spark.scala.sparkStreaming

object SocketSampleTaskTest {

  def main(args: Array[String]): Unit = {
    val str = "hello   world       jack"
    val array = str.split("\\s+")
    array.foreach(println)
  }
}
