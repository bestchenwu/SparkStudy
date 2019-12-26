package com.flink.scala2.operaters

import java.util.concurrent.TimeUnit

import com.hbase.HBaseClient
import org.apache.flink.streaming.api.scala.{AsyncDataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.scala.async.{AsyncFunction, ResultFuture}

/**
  * flink 异步IO的测试类
  *
  * @author chenwu on 2019.12.17
  */
object AsyncIOFunctionTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val stream = env.fromCollection(Seq("1000", "1001", "1002"))
    val asyncStream = AsyncDataStream.unorderedWait(stream, new AsyncRequest(), 10*1000l, TimeUnit.MILLISECONDS)
    asyncStream.print()
    env.execute("AsyncIOFunctionTest")
  }
}

class AsyncRequest extends AsyncFunction[String, String] {

  val hbaseClient = new HBaseClient("hbase/babytree-hbase-site.xml")

  override def asyncInvoke(input: String, resultFuture: ResultFuture[String]): Unit = {
    val result = hbaseClient.queryTable(List(input), "course_test", "score", "data")
    val value = result.get(input).get
    //println(value)
    resultFuture.complete(Iterable(value))
  }
}
