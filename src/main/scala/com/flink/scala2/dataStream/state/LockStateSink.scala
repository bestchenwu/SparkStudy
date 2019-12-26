package com.flink.scala2.state

import java.util
import java.util.Collections

import org.apache.flink.streaming.api.checkpoint.ListCheckpointed
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}
import scala.collection.JavaConversions._

/**
  * 有状态的源函数<br/>
  * 在故障恢复时候需要精确一次的语义
  *
  * @author chenwu on 2019.12.6
  */
class LockStateSink extends RichParallelSourceFunction[List[Long]] with ListCheckpointed[List[Long]] {

  @volatile
  private var isRunning: Boolean = true

  private var offset: List[Long] = List()

  private var offset_number:Long = 0l


  override def snapshotState(checkpointId: Long, timestamp: Long): util.List[List[Long]] = {
        val javaList = new util.ArrayList[List[Long]]()
        javaList.add(offset)
        Collections.unmodifiableList(javaList)
  }

  override def restoreState(state: util.List[List[Long]]): Unit = {
    for (s <- state) {
      offset = s
    }
  }

  override def run(ctx: SourceFunction.SourceContext[List[Long]]): Unit = {
    val lock = ctx.getCheckpointLock
    while (isRunning) {
      lock.synchronized({
        ctx.collect(offset)
        offset_number += 1
        offset = List(offset_number)
      })
    }

  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
