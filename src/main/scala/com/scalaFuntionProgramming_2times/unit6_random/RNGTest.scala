package com.scalaFuntionProgramming_2times.unit6_random

object RNGTest {

  def main(args: Array[String]): Unit = {
    val simpleRNG = SimpleRNG(11l)
    //    val tuple = simpleRNG.ints(5)(simpleRNG)
    //    println(tuple._1)

    //    val doubleRND = simpleRNG.randomDouble
    //    val randomDoubleResult = doubleRND(simpleRNG)
    //    println(randomDoubleResult)

    //    val list = List.fill(5)(simpleRNG.int)
    //    val rngList = simpleRNG.sequence(list)
    //    val resultList = rngList(simpleRNG)._1
    //    println(resultList)

    val randA = simpleRNG.int
    import simpleRNG.RAND
    val g: Int => RAND[Int] = (x: Int) => (rng: RNG) => {
      val (ints, rng2) = rng.nextInt
      (ints + x, rng2)
    }
    val randB = simpleRNG.flatMap(randA)(g)
    val result = randB(simpleRNG)._1
    println(result)
    //    val randB = simpleRNG flatMap(randA)(g)
  }
}
