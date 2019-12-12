package com.flink.scala2.state

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.{ ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.ConfigConstants
import org.apache.flink.configuration.Configuration
import org.apache.flink.configuration.{ConfigConstants, Configuration, QueryableStateOptions}
import org.apache.flink.util.Collector

/**
  * flink状态计算
  *
  * @author chenwu on 2019.12.6
  */
object ExampleCollectAverageSample {

  /**
    * 定义了一个状态计算器<br/>
    * 当sum值>2的时候输出
    *
    * @author chenwu on 2019.12.6
    */
  class AverageCollectFunction extends RichFlatMapFunction[(Long, Long), (Long, Long)] {

    private var sum: ValueState[(Long, Long)] = _

    override def flatMap(value: (Long, Long), out: Collector[(Long, Long)]): Unit = {
      val tmpSum = sum.value()
      val currentSum = if (tmpSum == null) {
        (0l, 0l)
      } else {
        tmpSum
      }
      val newSum = (currentSum._1 + 1, currentSum._2 + value._2)
      sum.update(newSum)
      if (newSum._2 > 2l) {
        out.collect((newSum._1, newSum._2 / newSum._1))
        sum.clear()
      }
    }

    override def open(parameters: Configuration): Unit = {
      //设置状态的生存时间
      val valueStateDescriptor = new ValueStateDescriptor[(Long, Long)]("average", createTypeInformation[(Long, Long)])
      /**
        * StateTtlConfig.UpdateType包括OnCreateAndWrite和OnReadAndWrite
        * OnCreateAndWrite表示TTL刷新时间权限仅限创建和写入权限
        * OnReadAndWrite表示TTL刷新时间权限也包括读取访问权限
        *
        * StateTtlConfig.StateVisibility包括
        * ReturnExpiredIfNotCleanedUp 即使过期仍然可以返回状态
        * NeverReturnExpired 从不返回过期状态
        */
      //val ttlStateConfig = StateTtlConfig.newBuilder(Time.seconds(1l)).setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite).setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired).build()
      //valueStateDescriptor.enableTimeToLive(ttlStateConfig)
      valueStateDescriptor.setQueryable("CountWindowCoverage")
      sum = getRuntimeContext.getState(valueStateDescriptor)
    }
  }

  def main(args: Array[String]): Unit = {
    System.setProperty("log.file","D:\\logs1\\local.log")
    val config = new Configuration()
    config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true)
    config.setBoolean(QueryableStateOptions.ENABLE_QUERYABLE_STATE_PROXY_SERVER, true)
    config.setString("web.log.path","D:\\logs1\\local.log")
    val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config)
    env.setParallelism(1)
    //val env = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream = env.fromCollection(List((1l, 1l), (1l, 2l), (1l, 3l), (1l, 4l)))
    val outputStream = dataStream.keyBy(_._1).flatMap(new AverageCollectFunction())
    outputStream.print()
    env.execute("ExampleCollectAverageSample")
  }
}
