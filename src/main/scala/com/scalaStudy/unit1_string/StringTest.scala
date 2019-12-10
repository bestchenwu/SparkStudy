package com.scalaStudy.unit1_string

object StringTest extends App {

  //    val str = "scala"
  //    print(str.take(2).drop(2).capitalize)

  //多行字符串
  val str =
    """hello
      |world
      |sweet
    """.stripMargin
  str.split(' ')
  println(str)
}
