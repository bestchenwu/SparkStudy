package com.flink.scala2.table.tableStreaming

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
/**
  * flink stream转换为table
  *
  * @author chenwu on 2020.4.10
  */

case class User(userName:String,age:Int)

object StreamToTableTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(2)
      val tableEnv = StreamTableEnvironment.create(env)
      val dataStream = env.fromCollection(Array(User("raoshanshan",11),User("maliang",222)))
      val dataStream1 = env.fromCollection(Array(User("lisisi",33)))
      //可以将datastream直接转换为表
      val queryTable = tableEnv.fromDataStream(dataStream,'userName,'age)
      //也可以将datastream注册为表
      tableEnv.registerDataStream("queryTable1",dataStream1)
      val resultTable = tableEnv.sqlQuery("select * from "+queryTable+" where userName = 'raoshanshan' union select * from queryTable1")
      val resultStream = tableEnv.toRetractStream[(String,Integer)](resultTable)
      resultStream.print()
      env.execute("StreamToTableTest")
  }
}
