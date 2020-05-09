package com.flink.scala2.table.tableStreaming

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.Types
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.scala._
import org.apache.flink.table.sinks.CsvTableSink
/**
  * table api测试
  *
  * @author chenwu on 2020.5.9
  */
object TableAPITest {

  def main(args: Array[String]): Unit = {
    //这里并发度为2的时候会生成两个文件 每个key一个文件
    //当并发度为1的时候只会生成一个文件
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val tableEnv = StreamTableEnvironment.create(env)
    val datastream = env.fromElements(("test", 11), ("maliang", 22),("raoshanshan",33),("raoshanshan",52))
    val userTable = tableEnv.fromDataStream(datastream,'name,'age)
    //或者这样注册也可以
//    tableEnv.registerDataStream("user",datastream,'name,'age)
//    val userTable1 = tableEnv.sqlQuery("select * from user")

    //使用fromDataStream转换的table
    val result = userTable.filter('age>=20).groupBy('name).select("name,age.sum as ageSum")
    //使用registerDataStream转换的table
    //val result = userTable1.filter('age>=20).groupBy('name).select("name,age.sum as ageSum")
    //将结果输出到csvSink
    //由于result是动态变化的,所以需要先转换成缩进模式
    val retractStream = tableEnv.toRetractStream[(String,Integer)](result).map(_._2)
    val resultTable = tableEnv.fromDataStream(retractStream,'name,'ageSum)
    val csvSink = new CsvTableSink("D:\\logs\\flinkSink\\nameAge.csv","|")
    val fieldNames = Array("name","ageSum")
    val filedTypes:Array[TypeInformation[_]] = Array(Types.STRING,Types.INT)
    tableEnv.registerTableSink("nameAge",fieldNames,filedTypes,csvSink)
    resultTable.insertInto("nameAge")
    //result.insertInto("nameAge")
    env.execute("TableAPITest")
  }
}
