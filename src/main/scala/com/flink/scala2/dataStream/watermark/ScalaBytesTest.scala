package com.flink.scala2.watermark
import scala.collection.JavaConversions._
object ScalaBytesTest {

  def main(args: Array[String]): Unit = {
    val message = Array[Byte](65,66,67)
    val charArray = message.map(item=>item.toChar)
    val str = charArray.mkString("")
    println(str)
  }
}
