package com.scalaStudy.unit12_file

import scala.io.Source
import java.io.{File => JFile}

object FileTest {

  def main(args: Array[String]): Unit = {
    val fileName = "/people.txt"
    val jfile = getClass.getResourceAsStream(fileName)
    val bufferedSource = Source.fromInputStream(jfile)
    val lines = bufferedSource.getLines()
    lines.foreach(println)
    bufferedSource.close
  }
}
