package com.flink.scala.state

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.StateTtlConfig.{StateVisibility, UpdateType}
import org.apache.flink.api.common.state.{StateTtlConfig, ValueState, ValueStateDescriptor}
import org.apache.flink.api.common.time.Time
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.util.Collector

/**
  * 使用flink的状态函数
  */

class CountWindowAverage extends RichFlatMapFunction[(Long, Double), (Long, Double)] {

  private var sum: ValueState[(Long, Double)] = _


  override def open(parameters: Configuration): Unit = {

    val stateDescriptor = new ValueStateDescriptor[(Long, Double)]({
      "average"
    }, createTypeInformation[(Long, Double)])
    //设置value的过期时间为1秒
    //设置value的可见状态是从不返回过期数据 对于敏感数据场景非常合适
    //设置TTL更新策略为仅限创建和写入权限,默认也是这种配置
    val ttlConfig = StateTtlConfig.newBuilder(Time.milliseconds(1)).setStateVisibility(StateVisibility.NeverReturnExpired).setUpdateType(UpdateType.OnCreateAndWrite).build()
    stateDescriptor.enableTimeToLive(ttlConfig)
    sum = getRuntimeContext.getState(stateDescriptor)
  }

  override def flatMap(in: (Long, Double), collector: Collector[(Long, Double)]): Unit = {
    val value = sum.value
    val currentSum = if (value != null) {

      value
    } else {
      (0l, 0d)
    }
    val newSum = (currentSum._1 + 1, currentSum._2 + in._2)
    sum.update(newSum)
    //这个时候设置睡眠时间100毫秒,可以让数据顺利过期
    Thread.sleep(100)
    if (newSum._1 >= 2) {
      collector.collect(in._1, newSum._2 / newSum._1)
      sum.clear()
    }
  }
}

object CountWindowAverageTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)

    //keyBy(1) 只针对元组类型
    val stream = env.fromCollection(List((1l, 3d), (1l, 5d), (1l, 7d), (1l, 4d), (1l, 2d))).keyBy(_._1).flatMap(new CountWindowAverage())
    //stream.writeAsText("/data/problem/testFlink.txt",WriteMode.OVERWRITE)
    stream.print()
    env.execute("CountWindowAverageTest")
  }
}
