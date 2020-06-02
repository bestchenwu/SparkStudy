package com.scalaStudy.unit4_class


/**
  * {@link Person}的测试类
  *
  * @author chenwu on 2020.6.2
  */
object PersonTest {

  def main(args: Array[String]): Unit = {
    //    val person = new Person("rao", "shanshan")
    //    person.firstName_$eq("ren")
    //    println(person)
    //    val person1 = new Person1("maliang")
    //    //试图去访问私有变量name，会报错
    //    //person1.name = ""
    //    //只能通过访问私有方法的形式去访问变量
    //    println(person1.getName)

    //    val person2 = new Person("haha")
    //    println(person2)

    //    val person3 = Person2()
    //    println(person3)
    //todo:不能访问私有构造函数
    //      val person3 = new Person3("haha")

    //    val employee = new Employee("lisisi", Address(11), 22)
    //    println(employee)
    //    val employee2 = new Employee2("lisisi",27)
    //    println(employee2)

//    val cat = new Cat("lily")
//    val dog = new Dog("jack")
//    cat.sayHello()
//    dog.sayHello()
//    println(cat.speak)
//    println(dog.speak)

      val oc1 = new OuterClass
      val oc2 = new OuterClass
      val ic1 = new oc1.InnerClass
      val ic2 = new oc2.InnerClass
      ic1.x = 3
      ic2.x = 5
      println(s"ic1.x=${ic1.x}")
  }
}


