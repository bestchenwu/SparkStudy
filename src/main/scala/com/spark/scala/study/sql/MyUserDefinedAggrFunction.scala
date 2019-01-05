package com.spark.scala.study.sql

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.MutableAggregationBuffer
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction
import org.apache.spark.sql.types._

object MyUserDefinedAggrFunction extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = new StructType(Array(StructField("salary", LongType)))

  override def bufferSchema: StructType = new StructType(Array(StructField("sum", LongType), StructField("count", LongType)))

  override def dataType: DataType = FloatType

  //same input to same output
  override def deterministic: Boolean = true

  //MutableAggregationBuffer这里等同于ROW
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L
    buffer(1) = 0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getLong(0) + input.getLong(0)
    buffer(1) = buffer.getLong(1) + 1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }

  override def evaluate(buffer: Row): Float = {
    buffer.getLong(0).toFloat / buffer.getLong(1)
  }
}


