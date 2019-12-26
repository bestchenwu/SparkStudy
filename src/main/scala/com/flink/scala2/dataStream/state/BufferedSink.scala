package com.flink.scala2.state

import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.runtime.state.{FunctionInitializationContext, FunctionSnapshotContext}
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.scala.createTypeInformation
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._
/**
  * 托管算子状态
  *
  * @author chenwu on 2019.12.6
  */
class BufferedSink(threshHold: Int = 0) extends SinkFunction[(String, Int)] with CheckpointedFunction {

  private var listState: ListState[(String, Int)] = _
  private val listBuffer: ListBuffer[(String, Int)] = ListBuffer()


  override def invoke(value: (String, Int), context: SinkFunction.Context[_]): Unit = {
    listBuffer += value
    if (listBuffer.size == threshHold) {
      for (element <- listBuffer) {
        //发送element 到sink
      }
      listBuffer.clear()
    }
  }

  /**
    * 每当执行检查点时候,都会snapshotState()被调用
    *
    * @param context
    */
  override def snapshotState(context: FunctionSnapshotContext): Unit = {
    listState.clear()
    for (element <- listBuffer) {
      listState.add(element)
    }
  }

  /**
    * 初始化或者从早期检查点恢复时候
    *
    * @param context
    */
  override def initializeState(context: FunctionInitializationContext): Unit = {
    val descriptor = new ListStateDescriptor[(String, Int)]("buffered-state", createTypeInformation[(String, Int)])
    val checkpointState = context.getOperatorStateStore.getListState(descriptor)
    if (context.isRestored) {
      for (item <- checkpointState.get()) {
        listBuffer += item
      }
    }
  }
}
