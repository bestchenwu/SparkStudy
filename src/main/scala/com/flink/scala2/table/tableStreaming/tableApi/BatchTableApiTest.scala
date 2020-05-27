package com.flink.scala2.table.tableStreaming.tableApi

import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.types.Row

/**
  * flink table 批处理的API测试类<br/>
  * 流式处理和批处理的api完全一致
  *
  * @author chenwu on 2020.5.27
  */
object BatchTableApiTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = BatchTableEnvironment.create(env)
    val dataSet = env.fromElements(("sweet", 1), ("jack", 3), ("sweet", 5),("sweet",4))
    val order = tableEnv.fromDataSet(dataSet, 'name, 'data)
    //table batch api 使用group by
    //val tableGroupResult = order.groupBy('name).select('name, 'data.sum as 'dataSum)
    //table batch api 使用filter where ,注意这里的where条件语句里使用的等值表达式用的===(三个等号)
    //val tableGroupResult  = order.filter('data>=3).where('name==="sweet").select('name,'data)
    //table api 使用两个table之间的join
    val dataSet2 = env.fromElements(("sweet", 10), ("jack", 30), ("sweet", 50),("sweet",40))
    val order2 = tableEnv.fromDataSet(dataSet2, 'name2, 'data2)
    //todo:当使用先filter再join的时候会报sql不能被解析
    val tableGroupResult = order.filter('name==="jack").join(order2).where('name==='name2).select('name,'data2)
    //val tableGroupResult = order.join(order2).where('name==='name2).select('name,'data2)
    tableGroupResult.toDataSet[Row].print()
    //env.execute("BatchTableApiTest")
  }
}
