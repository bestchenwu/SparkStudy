package com.scalaFuntionProgramming_2times.unit13_Applicative_trait

object ApplicativeTest {

  def main(args: Array[String]): Unit = {
    import com.scalaFuntionProgramming_2times.unit13_Applicative_trait.Applicative.optionApplicative
//    val optionList = List(Some(3), Some(5), Some(-2))
//    val maybeInts = optionApplicative.sequence(optionList)
//    println(maybeInts)

      val f = (x:Int)=>2*x
      val maybeInt = optionApplicative.apply(Some(f))(Some(4))
      println(maybeInt)
  }
}
