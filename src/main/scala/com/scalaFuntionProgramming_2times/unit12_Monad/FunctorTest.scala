package com.scalaFuntionProgramming_2times.unit12_Monad

object FunctorTest {

  def main(args: Array[String]): Unit = {
    val tupleList = List(("aa", 1), ("bb", 2), ("cc", 5))
    import Functor.listFunctor
    val (stringList, intList) = listFunctor.distribute(tupleList)
    println(stringList)
    println(intList)
  }

}
