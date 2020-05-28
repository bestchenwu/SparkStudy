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
    val dataSet = env.fromElements(("sweet", 1),("sweet", 1),("sweet", 1),("sweet", 1), ("jack", 3), ("sweet", 5),("231",3))
    val order = tableEnv.fromDataSet(dataSet, 'name, 'data)
    //table batch api 使用group by
    //val tableGroupResult = order.groupBy('name).select('name, 'data.sum as 'dataSum)
    //table batch api 使用filter where ,注意这里的where条件语句里使用的等值表达式用的===(三个等号)
    //val tableGroupResult  = order.filter('data>=3).where('name==="sweet").select('name,'data)
    //table api 使用两个table之间的join
    val dataSet2 = env.fromElements(("sweet", 1), ("jack", 3),("sweet",1),("haha",2))
    val order2 = tableEnv.fromDataSet(dataSet2, 'name, 'data)
    //todo:当使用先filter再join的时候会报sql不能被解析
    //val tableGroupResult = order.filter('name==="jack").join(order2).where('name==='name2).select('name,'data2)
    //val tableGroupResult = order.join(order2).where('name==='name2).select('name,'data2)
    //table api 使用union (带去重)以及 union all (不去重)
    //val tableGroupResult = order.union(order2).select('name,'data)
    //这时候输出的sweet 4 只有一条
    //val tableGroupResult = order.unionAll(order2).select('name,'data)
    //这时候输出的sweet 4 有2条
    //table api 使用minus 以及minus all
    //val tableGroupResult = order.minus(order2).select('name,'data)
    //输出sweet,5
    //val tableGroupResult = order.minusAll(order2).select('name,'data)
    //minor all 语义是删除左表和右表重复记录数一样的项数,当左边出现n次,右边出现m次的时候，返回该项 n-m次
    //输出sweet,1 sweet,1 sweet,5
    //按照data降序排序，跳过第一条记录，并只取一条
    //val tableGroupResult = order.orderBy('data.desc).offset(1).fetch(1).select('name,'data)
    //输出jack,3
    //table api like(匹配模式字符串)与similar(匹配正则表达式)
    //val tableGroupResult = order.filter('name like("sw%")).distinct()
    //输出sweet,1 sweet,5
    val tableGroupResult = order.filter('name similar("[0-9]+"))
    //输出231,3
    tableGroupResult.toDataSet[Row].print()

    //env.execute("BatchTableApiTest")
  }
}
