package com.mysql

import scala.collection.mutable.Buffer

object MysqlClientTest {

  def main(args: Array[String]): Unit = {
    val mysqlClient = new MysqlClient(username = "test", password = "123456", databaseName = "test")
    //      val array = mysqlClient.querySingleObjectById("User",Array[String]("password"),3l)
    //      array.foreach(println)

    val sql = mysqlClient.getInsertOrUpdateSql("student", Buffer("name", "age"))
    println(sql)
  }


}
