package com.spark.scala.study.sql

import org.apache.spark.sql.SparkSession

object TestUserAggrFunction {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("TestUserAggrFunction").master("local").getOrCreate()
    import sparkSession.implicits._
    //    val df = sparkSession.read.json("src/main/resources/salary.json")
    //    df.createOrReplaceTempView("people")
    //    df.show()
    //注册自定义的UDF函数
    //    sparkSession.udf.register("myAggr", MyUserDefinedAggrFunction)
    //    val df2 = sparkSession.sql("select myAggr(salary) as avg_salary from people")
    //    df2.show()
    //以下是用户自定义类的UDF函数,相对来说,逻辑比之前要清楚
    val df = sparkSession.read.json("src/main/resources/salary.json").as[Employee]
    df.createOrReplaceTempView("people")
    df.show()
    val avgSalary = MyTypeSafeAggrFunction.toColumn.name("avg_salary")
    df.select(avgSalary).show()
  }
}
