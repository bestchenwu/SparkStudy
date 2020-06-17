package com.scalaFuntionProgramming_2times.unit14_IO

import NEWIO._

object NewIOTest {
  def main(args: Array[String]): Unit = {
    val returnIO = Return("hello world")
    val suspendIO = Suspend(() => "hello raoshanshan")
    val flatMapIO = FlatMap(returnIO, (x: String) => Return(x * 2))
    val result = NEWIO.run(flatMapIO)
    println(result)
  }
}
