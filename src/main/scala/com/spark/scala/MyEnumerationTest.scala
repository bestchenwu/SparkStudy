package com.spark.scala

object MyEnumerationTest extends App {

  val myEnumeration = MyEnumeration.ONE
  print(myEnumeration.eq(MyEnumeration.TWO))
}
