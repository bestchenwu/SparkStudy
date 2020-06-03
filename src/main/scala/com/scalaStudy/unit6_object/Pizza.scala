package com.scalaStudy.unit6_object

class Pizza(val pizzaName:String) {
  override def toString: String = s"Pizza is $pizzaName"
}

/**
  * 与类名相同名称的对象名称称为伴生对象
  * 在这里面可以定义静态实例才有的方法和字段
  */
object Pizza{

    val ROUND_PIZZA = "ROUND_PIZZA"
    val RECTANGLE_PIZZA = "RECTANGLE_PIZZA"
}
