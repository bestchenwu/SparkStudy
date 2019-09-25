package com.flink.scala.AsyncIO

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.mysql.MysqlClient
import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.async.{AsyncFunction, ResultFuture}

import scala.concurrent.duration.TimeUnit
/**
  * 原文:https://www.cnblogs.com/Springmoon-venn/p/11103558.html
  * 官方文档:https://flink.sojb.cn/dev/stream/operators/asyncio.html
  *
  * @author chenwu on 2019.9.22
  */
object AsyncMysqlRequest {

  case class User(id:Long,userName:String,password:String="")

  class AysncDataBaseRequest extends AsyncFunction[User,User]{

    val mysqlClient:MysqlClient = MysqlClient()

    override def asyncInvoke(input: User, resultFuture: ResultFuture[User]): Unit = {
        val output = mysqlClient.querySingleObjectById("User",Array("password"),input.id)
        val user = User(input.id,input.userName,output(0))
        resultFuture.complete(List(user))
    }
  }

  def main(array:Array[String]): Unit ={
      val env = StreamExecutionEnvironment.getExecutionEnvironment
      val properties = new Properties()
      properties.setProperty("bootstrap.servers", "localhost:9092")
      properties.setProperty("group.id", "test-flink")
      val kafkaStream = env.addSource(new FlinkKafkaConsumer010[String]("test-010",SimpleStringSchema,properties))
      val input = kafkaStream.flatMap(_.split("\\s+")).map(item=>{
          val array = item.split(SymbolConstants.SYMBOL_DH)
          val user = User(array(0).toLong,array(1))
          user
      })
      AsyncDataStream.unorderedWait(input,new AysncDataBaseRequest(),10,TimeUnit.SECONDS,)
  }
}
