package com.scalaStudy.unit5_method

import java.io.IOException

object HumanTest {

  def main(args: Array[String]): Unit = {
      val children = new Children

      children.speakFather
      children.speakMother
      //因为父类里定义方法的时候不带(),所以调用的时候也不能带括号
      //但是如果父类带括号，这里可以带也可以不带
      println(children.currentAge)
      try{
        children.play(10)
      }catch{
        case e:IOException=>println(e.getCause)
      }

      //链式调用
      val employee = new Employee().setFirstName("rao").setSecondName("shanshan").setEmployeeId(13)
      println(employee)

  }
}
