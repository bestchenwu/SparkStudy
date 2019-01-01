package com.spark.scala

object MyEnumeration extends Enumeration {

  //TODO:这里不加type 语法也可以跑的通
  //type MyEnumeration = Value

  val ONE, TWO, THREE = Value
}

