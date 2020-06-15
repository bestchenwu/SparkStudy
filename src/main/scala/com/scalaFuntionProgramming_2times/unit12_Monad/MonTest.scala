package com.scalaFuntionProgramming_2times.unit12_Monad

object MonTest {

  def main(args: Array[String]): Unit = {
    //import com.scalaFuntionProgramming_2times.unit12_Monad.Mon.listMon
    //    val stringsList = listMon.map2(List(1, 2, 3), List("aa", "bb", "cc"))((a: Int, b: String) => b + a)
    //    println(stringsList)
    import com.scalaFuntionProgramming_2times.unit12_Monad.Mon.optionMon
//    val optionList = List(Some(11),None, Some(222), Some(55))
//    val maybeInts = optionMon.sequence(optionList)
//    println(maybeInts)
    val maybeInts = optionMon.replicateM(3,Some(11))
    println(maybeInts)
  }
}
