package com.flink.scala2.dataset.iteration

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, createTypeInformation}

/**
  * flink迭代算子 测试
  *
  * @author chenwu on 2019.12.30
  */
object IterationTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val intial = env.fromElements(0)
    //这方法也称为蒙特卡罗方法
    //假定有M个点,均匀的分布在一个正方形里面,那么位于内切圆的点的个数为N,假定圆半径为R
    //N/M =  (PI*R*R)/(2R*2R) =>N/M = PI/4 =>PI = 4*N/M
    val result = intial.iterate(10000) { datasetInput: DataSet[Int] => {
      val output = datasetInput.map(i => {
        val x = Math.random()
        val y = Math.random()
        i + (if (x * x + y * y < 1) 1 else 0)
      })
      output
    }
    }
    val piResult = result.map(c => c / 10000.0 * 4)
    piResult.print()
  }
}
