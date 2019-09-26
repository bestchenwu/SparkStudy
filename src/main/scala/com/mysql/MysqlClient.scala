package com.mysql

import java.sql.{Connection, DriverManager, ResultSet, Statement}

import com.spark.constants.SymbolConstants

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
class MysqlClient(host:String="localhost",port:Int=3306,username:String,password:String,databaseName:String) {

  private val connectionUrl={
    try{
      Class.forName("com.mysql.cj.jdbc.Driver")
      "jdbc:mysql://"+host+":"+port+"/"+databaseName
    }catch {
      case e:Exception=>throw new IllegalStateException(e)
    }
  }

  /**
    * 获取mysql链接
    *
    * @return Connection
    * @author chenwu on 2019.9.25
    */
  private def getConnection:Connection={
    val connection = DriverManager.getConnection(connectionUrl)
    connection
  }

  def querySingleObjectById(tableName:String,columns:Array[String],id:Long): Array[String] ={
      val connection = getConnection

      val sql =s"select ${columns.mkString(SymbolConstants.SYMBOL_DH)} from ${tableName} where id=${id}"
      val statement = connection.createStatement();
      val resultSet = statement.executeQuery(sql)
      val array = ArrayBuffer[String]()
      if(resultSet!=null && resultSet.first()){
          for(column<-columns){
              val value = resultSet.getString(column)
              if(value==null||value.isEmpty){
                  array.append(SymbolConstants.NAString)
              }else{
                  array.append(value)
              }
          }
      }
      quietlyCloseConnection(connection,statement,resultSet)
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
  private def quietlyCloseConnection(connection:Connection,statement:Statement,resultSet:ResultSet=null)={
    if(resultSet!=null){
      try{
        resultSet.close()
      }catch{
        //ignore the exception
        case _:Exception=>
      }
    }
    if(statement!=null){
      try{
        statement.close()
      }catch{
        case _:Exception=>
      }
    }
    if(connection!=null){
      try{
        connection.close()
      }catch{
        case _:Exception=>
      }
    }
  }
}
