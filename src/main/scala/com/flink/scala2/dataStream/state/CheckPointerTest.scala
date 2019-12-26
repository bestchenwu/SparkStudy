package com.flink.scala2.state

import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * flink的checkpoint测试
  *
  * @author chenwu on 2019.12.6
  */
object CheckPointerTest {

  def main(args: Array[String]): Unit = {
        val env = StreamExecutionEnvironment.createLocalEnvironment(1)
        //单位是毫秒,这里表示5秒启用一次检查点
        //默认情况下使用恰好一次的语义
        env.enableCheckpointing(5*1000l,CheckpointingMode.EXACTLY_ONCE)
        //checkpoint操作需要在6秒内完成,否则会丢弃
        env.getCheckpointConfig.setCheckpointTimeout(6*1000l)
        //默认情况下,状态都存储在TaskManager的内存中名叫MemoryStateBackend
        //如果要持久化,可以选择FsStateBackend,RocksDBStateBackend
        val fsStateBackend = new FsStateBackend("hdfs://namenode:40010/flink/checkpoints",false)
        val rocksDBStateBackend = new RocksDBStateBackend("hdfs://namenode:40010/flink/checkpoints")
        env.setStateBackend(fsStateBackend)
  }
}
