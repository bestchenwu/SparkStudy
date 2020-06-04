package com.scalaStudy.unit10_collection

object Number extends  Enumeration {

    type Number = Value
    val ONE,TWO,THREE = Value
}

object EnumerationTest {
  def main(args: Array[String]): Unit = {
      val myNumber = Number.ONE
      println(myNumber.id)
  }
}
