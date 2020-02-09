package com.mysql

import java.sql.{Connection, DriverManager, ResultSet, Statement}
import java.util.concurrent.atomic.AtomicInteger

import com.spark.constants.SymbolConstants

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * mysql的client
  *
  * @param host
  * @param port
  * @param username
  * @param password
  * @param databaseName
  */
class MysqlClient(host: String = "localhost", port: Int = 3306, username: String, password: String, databaseName: String) {

  private val connectionUrl = {
    try {
      //Class.forName("com.mysql.jdbc.Driver")
      Class.forName("com.mysql.cj.jdbc.Driver")
      "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?serverTimezone=UTC"
    } catch {
      case e: Exception => throw new IllegalStateException(e)
    }
  }

  /**
    * 获取mysql链接
    *
    * @return Connection
    * @author chenwu on 2019.9.25
    */
  private def getConnection: Connection = {
    val connection = DriverManager.getConnection(connectionUrl, username, password)
    connection
  }

  def querySingleObjectById(tableName: String, columns: Array[String], id: Long): Array[String] = {
    val connection = getConnection

    val sql = s"select ${columns.mkString(SymbolConstants.SYMBOL_DH)} from ${tableName} where id=${id}"
    val statement = connection.createStatement();
    val resultSet = statement.executeQuery(sql)
    val array = ArrayBuffer[String]()
    if (resultSet != null && resultSet.first()) {
      for (column <- columns) {
        val value = resultSet.getString(column)
        if (value == null || value.isEmpty) {
          array.append(SymbolConstants.NAString)
        } else {
          array.append(value)
        }
      }
    }
    quietlyCloseConnection(connection, statement, resultSet)
    array.toArray
  }

  /**
    * 关闭数据库链接等
    *
    * @param connection
    * @param statement
    * @param resultSet
    * @author chenwu on 2019.9.25
    */
  private def quietlyCloseConnection(connection: Connection, statement: Statement, resultSet: ResultSet = null) = {
    if (resultSet != null) {
      try {
        resultSet.close()
      } catch {
        //ignore the exception
        case _: Exception =>
      }
    }
    if (statement != null) {
      try {
        statement.close()
      } catch {
        case _: Exception =>
      }
    }
    if (connection != null) {
      try {
        connection.close()
      } catch {
        case _: Exception =>
      }
    }
  }

  /**
    * 批量往mysql里插入数据<br/>
    *
    * @param tableName
    * @param columnNameList  列名集合
    * @param columnValueList 列的值组成的list
    * @tparam T
    * @author chenwu on 2020.2.9
    */
  def batchInsertIntoTable[T <: Object](tableName: String, columnNameList: mutable.Buffer[String], columnValueList: mutable.Buffer[mutable.Buffer[T]]): Unit = {
    val insertUpdateSql = getInsertOrUpdateSql(tableName, columnNameList)
    val connection = getConnection
    connection.setAutoCommit(false)
    val preparedStatement = connection.prepareStatement(insertUpdateSql)
    val index = new AtomicInteger(1)
    for (valueList <- columnValueList) {
      for (value <- valueList) {
        val clazz = value.getClass
        clazz.getSimpleName match {
          case "String" => preparedStatement.setString(index.getAndIncrement(), value.asInstanceOf[String])
          case "Integer" => preparedStatement.setInt(index.getAndIncrement(), value.asInstanceOf[Int])
          case _ => throw new IllegalArgumentException(s"not supported ${value},and ${clazz}")
        }
      }
      preparedStatement.addBatch()
    }
    connection.commit()
    if (preparedStatement != null) {
      preparedStatement.close()
    }
    if (connection != null) {
      connection.close()
    }

  }

  /**
    * 获取插入或者更新的sql
    *
    * @param tableName
    * @param columnNameList
    * @return String
    * @author chenwu on 2020.2.9
    */
  def getInsertOrUpdateSql(tableName: String, columnNameList: mutable.Buffer[String]) = {
    val stringBuffer = new StringBuffer(s"insert into ${tableName}(")
    for (i <- 1 to columnNameList.size) {
      stringBuffer.append(columnNameList(i - 1))
      if (i != columnNameList.size) {
        stringBuffer.append(",")
      }
    }
    stringBuffer.append(") value(")
    for (i <- 1 to columnNameList.size) {
      stringBuffer.append("?")
      if (i != columnNameList.size) {
        stringBuffer.append(",")
      }
    }
    stringBuffer.append(")")
    stringBuffer.toString
  }
}
