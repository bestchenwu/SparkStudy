package com.scalaStudy.unit19_type

class Grand

class Father extends Grand

class Child extends Father

class InvariantClass[A]

//协变  如果B是A的子类型，那么CovariantClass[B]也是CovariantClass[A]的子类型
class CovariantClass[+A]

//逆变  如果B是A的子类型，那么ContravariantClass[B]是ContravariantClass[A]的父类型
class ContravariantClass[-A]

object TypeTest {

  def main(args: Array[String]): Unit = {

    def InvariantMethod(item: InvariantClass[Father]): Unit = {
      println(item.getClass.getSimpleName)
    }

    def CovariantMethod(item: CovariantClass[Father]): Unit = {
      println(item.getClass.getSimpleName)
    }

    def ContravariantMethod(item: ContravariantClass[Father]): Unit = {
      println(item.getClass.getSimpleName)
    }

    //编译不通过，因为这里只能接受InvariantClass[Child]的类型
    //InvariantMethod(new InvariantClass[Child]())

    CovariantMethod(new CovariantClass[Child]())
    //编译不通过,因为CovariantClass[Grand]是CovariantClass[Fater]的父类型
    //CovariantMethod(new CovariantClass[Grand]())

    ContravariantMethod(new ContravariantClass[Grand])
    //编译不通过,这里ContravariantClass[Child]实际上是ContravariantClass[Father]的父类型
    //ContravariantMethod(new ContravariantClass[Child])

    /**
      * 接受一个具备speak方法的对象，并调用speak
      *
      * @param obj
      * @tparam A
      * @author chenwu on 2020.6.5
      */
    def callBack[A <: {def speak() : Unit}](obj: A) = {
      obj.speak()
    }

    class Dog{
        def speak()={ println("dog")}
    }

    class Cat{
        def speak()={
          println("cat")
        }
    }

    callBack(new Dog())
    callBack(new Cat())
  }
}
