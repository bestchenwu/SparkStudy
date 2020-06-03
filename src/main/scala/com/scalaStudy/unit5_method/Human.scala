package com.scalaStudy.unit5_method

import java.io.IOException

trait Human {
  def hello = "the Human trait"
}

trait Mother extends Human {
  override def hello: String = "the Mother trait"
}

trait Father extends Human {
  override def hello: String = "the Father trait"
}

/**
  * scala里面继承多个类,用extends ClassA with ClassB的语法
  */
class Children extends Mother with Father with Human {

  protected var firstName: String = ""
  protected var secondName: String = ""


  def speakMother = {
    //当要使用哪个父类的属性的时候,super后面需要跟具体的父类
    println(super[Mother].hello)
  }

  def speakFather: Unit = {
    println(super[Father].hello)
  }

  def currentAge() = 13

  @throws[IOException]("play have found error")
  def play(i: Int) = {
    if (i > 5) {
      throw new IOException()
    }
  }

  /**
    * 如果要支持调用方使用链式调用,需要将返回值定义为this.type
    *
    * @param firstName
    * @return
    */
  def setFirstName(firstName: String): this.type = {
    this.firstName = firstName
    this
  }

  def setSecondName(secondName: String): this.type = {
    this.secondName = secondName
    this
  }

  override def toString: String = s"my name is {$firstName,$secondName}"
}

class Employee extends Children {

  private var employeeId: Int = 0

  def setEmployeeId(employeeId: Int): this.type = {
    this.employeeId = employeeId
    this
  }

  override def toString: String = s"my name is {$firstName,$secondName},my employeeId is $employeeId"
}
