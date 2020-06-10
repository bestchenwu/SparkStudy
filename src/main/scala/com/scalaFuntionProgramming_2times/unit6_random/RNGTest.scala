package com.scalaFuntionProgramming_2times.unit6_random

object RNGTest {

  def main(args: Array[String]): Unit = {
    val simpleRNG = SimpleRNG(11l)
    //    val tuple = simpleRNG.ints(5)(simpleRNG)
    //    println(tuple._1)

    val doubleRND = simpleRNG.randomDouble
    val randomDoubleResult = doubleRND(simpleRNG)
    println(randomDoubleResult)
  }
}
