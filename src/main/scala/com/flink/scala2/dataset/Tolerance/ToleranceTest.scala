package com.flink.scala2.dataset.Tolerance

import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.common.restartstrategy.RestartStrategies.FixedDelayRestartStrategyConfiguration
import org.apache.flink.api.scala.ExecutionEnvironment

/**
  * 容错
  */
object ToleranceTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    //env.setNumberOfExecutionRetries(3)
    //重试3次,重试间隔是5000毫秒
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,5000))
  }
}
