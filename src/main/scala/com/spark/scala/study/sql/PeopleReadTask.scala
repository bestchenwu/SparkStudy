package com.spark.scala.study.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf}

object PeopleReadTask {

  def main(args: Array[String]): Unit = {
      val conf = new SparkConf()
      val sparkSession = SparkSession.builder().appName("").master("local").config(conf).getOrCreate()
      // 使用json读取数据
      val df = sparkSession.read.json("src/main/resources/people.json")
      import sparkSession.implicits._
      //df.createOrReplaceTempView("people")

    // 可以将df作为临时表供其他人查询
//    val dataFrame = sparkSession.sql("select name,age from people")
//    dataFrame.show()
    // to use $ operation

    //    df.filter($"age">21).show()
    //    print(df.groupBy($"name").count().show())

    //val rdd = sc.textFile("src/main/resources/people.txt").map(str=>str.split(","))
    //可以利用序列将对象数组转化为dataset
    //dataSet和dataFrame有着完全相同的方法,两者的区别是:
    //dataFrame准确的说是dataSet[Row],每一列的值需要通过row.getString("columnName")去解析
    //dataSet当定义了具体的class后,可以使用类的方法
    //val dataSet = Seq(Person("mary",11),Person("jack",22)).toDS()
    val dataSet = df.as[Person]
    //dataSet.filter($"age">11)
    df.foreach(row=>{
      println(row.getAs[String]("name"))
    })
    println("the below is use dataSet")
    dataSet.foreach(person=>{
      println(person.age)
    })
  }
}
