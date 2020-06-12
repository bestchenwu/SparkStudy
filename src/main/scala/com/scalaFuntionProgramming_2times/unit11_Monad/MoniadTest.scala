package com.scalaFuntionProgramming_2times.unit11_Monad

import java.util.concurrent.{ExecutorService, Executors}

import com.scalaFuntionProgramming_2times.unit7_paralle.Par
import com.scalaFuntionProgramming_2times.unit7_paralle.Par.Par

object MoniadTest {

  def main(args: Array[String]): Unit = {
    //    val ints = IndexedSeq[Int](1, 3, 55, 332, 254,979, 2321, 55411)
    ////    val m = Moniad.stringMoniad
    //    ////    val f: (Int => String) = (i: Int) => s"a$i"
    //    ////    val stringPar:Par[String] = Moniad.parFoldMap(ints,m)(f)
    //    ////    val executorService = Executors.newFixedThreadPool(3)
    //    ////    val result = Par.run(executorService)(stringPar).get()
    //    ////    println(result)
    //    ////    executorService.shutdown()
    //    val booleanResult = Moniad.checkIndexedSexIsSorted(ints)
    //    println(booleanResult)

    val words = "abdfs fsfsd fsfs"
    val result = Moniad.countWords(words)
    println(result)
  }
}
