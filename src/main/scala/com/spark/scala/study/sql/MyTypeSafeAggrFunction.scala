package com.spark.scala.study.sql

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator

case class Employee(name: String, salary: Long)

case class AvgSalary(var sum: Long, var count: Long)

object MyTypeSafeAggrFunction extends Aggregator[Employee, AvgSalary, Double] {
  override def zero: AvgSalary = new AvgSalary(0L, 0L)

  override def reduce(b: AvgSalary, a: Employee): AvgSalary = {
    b.sum += a.salary
    b.count += 1
    b
  }

  override def merge(b1: AvgSalary, b2: AvgSalary): AvgSalary = {
    b1.sum = b1.sum + b2.sum
    b1.count = b1.count + b2.count
    b1
  }

  override def finish(reduction: AvgSalary): Double = {
    reduction.sum.toDouble / reduction.count
  }

  override def bufferEncoder: Encoder[AvgSalary] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
