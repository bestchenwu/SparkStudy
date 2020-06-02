package com.scalaStudy.unit4_class

/**
  * 定义包含firstName和secondName的两个变量的类<br/>
  * 试着使用javap -p Person 可以看到:
  * public java.lang.String firstName();
  * public void firstName_$eq(java.lang.String); 这一行就是scala为我们自动生成的修改变量的方法
  *
  * @param firstName
  * @param secondName
  * @author chenwu on 2020.6.2
  */
class Person(var firstName: String, var secondName: String) {

  def this(firstName: String) {
    this(firstName, "default secondName")
  }

  override def toString: String = s"name={$firstName,$secondName}"
}

class Person1(private var name: String) {

  def getName: String = {
    this.name
  }

  override def toString: String = s"name={$name}"
}

case class Person2(var firstName: String, var secondName: String) {
  override def toString: String = s"name={$firstName,$secondName}"
}

object Person2 {

  /**
    * 对于case方法的构造方法重载
    *
    * @return { @link Person2}
    * @author chenwu on 2020.6.2
    */
  def apply(): Person2 = {
    Person2("no firstName", "no secondName")
  }
}

/**
  * 定义私有构造方法的类
  *
  * @param name
  * @author chenwu on 2020.6.2
  */
class Person3 private(name: String) {

}

class Stock {

  private[this] var name: String = ""

  def getName = name

  def compare(otherStock: Stock): Unit = {
    //这里的name就只能被当前类访问,其他同类型的实例都不能访问
    //this.name.compareTo(otherStock.name)
    //需要修改成:
    this.name.compareTo(otherStock.getName)
  }
}

case class Address(val addressid: Int)

class Person4(val name: String, val address: Address) {

  def this(name: String) {
    this(name, Address(0))
  }
}

/**
  * 子类继承父类的时候，父类已经声明的变量名就不能加var或者val,新声明的变量加修饰符即可<br/>
  * 当反编译Employee的时候，会发现:
  * public class com.scalaStudy.unit4_class.Employee extends com.scalaStudy.unit4_class.Person4 {
  * private final java.lang.String name;  //父类的变量都被声明为private final了
  * private final com.scalaStudy.unit4_class.Address address;
  * private final int age;
  * public int age();
  * public java.lang.String toString();
  * public com.scalaStudy.unit4_class.Employee(java.lang.String, com.scalaStudy.unit4_class.Address, int);
  * }
  *
  * @param name
  * @param address
  * @param age
  */
class Employee(name: String, address: Address, val age: Int) extends Person4(name: String, address: Address) {
  override def toString: String = {
    s"name=$name,address=${address.addressid},age=$age"
  }
}

/**
  * 因为父类已经声明了只带name参数的构造函数，所以这里可以不用加address作为继承变量
  *
  * @param name
  * @param age
  */
class Employee2(name: String, val age: Int) extends Person4(name: String) {
  override def toString: String = {
    s"name=$name,address=${address.addressid},age=$age"
  }
}

abstract class Pet(val name: String) {
  //声明一个变量但不赋值
  val age: Int

  def sayHello() = {
    println(s"my age is $age,and name is $name")
  }

  //声明一个方法但不赋值
  def speak: String
}

class Dog(name: String) extends Pet(name) {
  //子类去实现变量
  override val age: Int = 5

  //子类实现父类方法
  override def speak: String = s"I am a dog,name is $name"
}

class Cat(name: String) extends Pet(name) {
  override val age: Int = 10

  override def speak: String = s"I am a cat,name is $name"
}

class OuterClass{

  /**
    * 内部类的作用域边界就是它的外部类
    */
  class InnerClass{
        var x:Int = 1
    }

}