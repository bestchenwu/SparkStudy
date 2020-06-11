package com.scalaFuntionProgramming_2times.unit6_random

object StateTest {

  def main(args: Array[String]): Unit = {
    val state = State[String, Int](str => (str.length, str))
    val unitState = state.unit(5)
    val (ints, str1) = unitState.run("abcdefgh")
    println((ints, str1))
  }
}
