package com.scalaStudy.unit6_object

object PizzaTest {
  def main(args: Array[String]): Unit = {
    println(Pizza.RECTANGLE_PIZZA)
    val pizza = new Pizza(Pizza.RECTANGLE_PIZZA)
    println(pizza)
  }
}
