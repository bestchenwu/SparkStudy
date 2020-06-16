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
    //    val maybeInts = optionMon.replicateM(3,Some(11))
    //    println(maybeInts)
    //证明identy法则 compose(f,unit) == compose(unit,f)
//    val intToMaybeInt = optionMon.compse((x: Int) => Some(x * 5), (y: Int) => optionMon.unit(y))
//    val intToMaybeInt2 = optionMon.compse((y: Int) => optionMon.unit(y), (x: Int) => Some(x * 5))
//    println(intToMaybeInt(9))
//    println(intToMaybeInt2(9))

    val maybeSomeInt = optionMon.unit(Some(5))
    println(optionMon.join(maybeSomeInt))
  }
}
