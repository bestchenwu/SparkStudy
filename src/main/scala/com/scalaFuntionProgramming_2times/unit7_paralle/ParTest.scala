package com.scalaFuntionProgramming_2times.unit7_paralle

import java.util.concurrent.Executors

import com.scalaFuntionProgramming_2times.unit7_paralle.Par.Par

object ParTest {

  def main(args: Array[String]): Unit = {
    val executorService = Executors.newFixedThreadPool(5)

    //    def sum(ints: IndexedSeq[Int]): Par[Int] = {
    //      if (ints.size <= 1) {
    //        Par.unit(ints.headOption getOrElse (0))
    //      } else {
    //        val (l, r) = ints.splitAt(ints.size / 2)
    //        Par.map2(sum(l), sum(r))(_ + _)
    //      }
    //    }
    //
    //    def sumByFork(ints:IndexedSeq[Int]):Par[Int] = {
    //      if (ints.size <= 1) {
    //        Par.unit(ints.headOption getOrElse (0))
    //      } else {
    //        val (l, r) = ints.splitAt(ints.size / 2)
    //        Par.map2(Par.fork(sum(l)), Par.fork(sum(r)))(_ + _)
    //      }
    //    }
    //
    //    val ints = IndexedSeq(1, 3, 5, 7, 8, 10, 55)
    //    val future = sumByFork(ints)(executorService)
    //    println(future.get())

    val traverse = (x: Int) => (s"$x size = ${x.toString.length}")
    val intsList = List(1, 3, 11, 322, 88, 555, 99, 5523242, 2233, 16)
    val stringParList = Par.parFilterByAsync(intsList)(_ % 2 == 0)
    val evenList = Par.run(executorService)(stringParList).get()
    println(evenList)
    //    val parStringList = Par.parMap(intsList)(traverse)
    //    val strings = Par.run(executorService)(parStringList).get()
    //println(strings)
    //executorService.shutdown()
  }
}
