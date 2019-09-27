package com.flink.scala.AsyncIO

import java.util.Properties
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import com.mysql.MysqlClient
import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.configuration.Configuration
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.async.{AsyncFunction, ResultFuture, RichAsyncFunction}

import scala.concurrent.duration.TimeUnit
/**
  * 原文:https://www.cnblogs.com/Springmoon-venn/p/11103558.html
  * 官方文档:https://flink.sojb.cn/dev/stream/operators/asyncio.html
  *
  * @author chenwu on 2019.9.22
  */
object AsyncMysqlRequest {

  case class User(id:Long,userName:String,var password:String="not found"){
    override def toString: String = {
        s"id=${id},userName=${userName},password=${password}"
    }
  }

  /**
    * 不支持timeout的异步请求处理类
    *
    * @author chenwu on 2019.9.27
    */
  class NonRichAsyncDataBaseRequest extends  AsyncFunction[User,User]{

     lazy val mysqlClient:MysqlClient = new MysqlClient(username="test",password="123456",databaseName = "test")
     lazy val executorService:ExecutorService = Executors.newFixedThreadPool(10)

    override def asyncInvoke(input: User, resultFuture: ResultFuture[User]): Unit = {
      executorService.submit(new Runnable{
        override def run(): Unit = {
          println(s"query id=${input.userName} begin")
          val output = mysqlClient.querySingleObjectById("User",Array("password"),input.id)
          val user = User(input.id,input.userName,output(0))
          resultFuture.complete(List(user))
        }
      })
    }
  }

  /**
    * 支持timeout的异步请求处理类
    *
    * @author chenwu on 2019.9.27
    */
  class AysncDataBaseRequest extends RichAsyncFunction[User,User]{

    lazy val mysqlClient:MysqlClient = new MysqlClient(username="test",password="123456",databaseName = "test")
    lazy val executorService:ExecutorService = Executors.newFixedThreadPool(10)

    override def open(parameters: Configuration): Unit = {
      println("init AysncDataBaseRequest")
//      mysqlClient = new MysqlClient(username="test",password="123456",databaseName = "test")
//      executorService  = Executors.newFixedThreadPool(10)
    }

    override def timeout(input: User, resultFuture: ResultFuture[User]): Unit = {
        println(s"query ${input} timeout")
        val list = Array[User](input)
        resultFuture.complete(list)
    }

    override def asyncInvoke(input: User, resultFuture: ResultFuture[User]): Unit = {
        executorService.submit(new Runnable{
          override def run(): Unit = {
            println(s"query id=${input.userName} begin")
            val output = mysqlClient.querySingleObjectById("User",Array("password"),input.id)
            val user = output match{
              case value if value.isEmpty =>input
              case nonEmptyValue => input.password=nonEmptyValue(0);input
            }
            resultFuture.complete(List(user))
          }
        })

    }

    override def close(): Unit = {
        println("close ---")
        super.close()
    }
  }

  def main(array:Array[String]): Unit ={
      val env = StreamExecutionEnvironment.createLocalEnvironment(1)
      val properties = new Properties()
      properties.setProperty("bootstrap.servers", "localhost:9092")
      properties.setProperty("group.id", "test-flink")
      //todo:使用kafka2.0.1
//      val kafkaStream = env.addSource(new FlinkKafkaConsumer010[String]("test-0",new SimpleStringSchema(),properties))
//      val input = kafkaStream.flatMap(_.split("\\s+")).map(item=>{
//          val array = item.split(SymbolConstants.SYMBOL_DH)
//          val user = User(array(0).toLong,array(1))
//          user
//      })
////      val testWithoutTimeoutStream = AsyncDataStream.unorderedWait(input,new NonRichAsyncDataBaseRequest(),500,TimeUnit.SECONDS,10)
////      testWithoutTimeoutStream.print()
//      val testWithTimeoutStream = AsyncDataStream.unorderedWait(input,new AysncDataBaseRequest(),50,TimeUnit.SECONDS,10)
//      testWithTimeoutStream.print()
      env.execute("AsyncMysqlRequest")
  }
}
