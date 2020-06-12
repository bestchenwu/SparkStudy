package com.scalaFuntionProgramming_2times.unit7_paralle

import java.util.concurrent.{Callable, ExecutorService, Future, TimeUnit}

import com.scalaFuntionProgramming_2times.unit11_Monad.Moniad
import com.scalaFuntionProgramming_2times.unit7_paralle.Par.{map, map2}

/**
  * 并行化的工具类
  */


object Par {

  type Par[A] = ExecutorService => Future[A]

  case class UnitFuture[A](run: A) extends Future[A] {
    override def cancel(mayInterruptIfRunning: Boolean): Boolean = true

    override def isCancelled: Boolean = true

    override def isDone: Boolean = true

    override def get(): A = run

    override def get(timeout: Long, unit: TimeUnit): A = run
  }

  def run[A](executorService: ExecutorService)(a: Par[A]): Future[A] = a(executorService)

  def unit[A](a: => A): Par[A] = (_: ExecutorService) => UnitFuture(a)

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (executorService: ExecutorService) => {
    val aa = a(executorService).get()
    val bb = b(executorService).get()
    UnitFuture(f(aa, bb))
  }

  def fork[A](a: => Par[A]): Par[A] = (es: ExecutorService) => {
    es.submit(new Callable[A] {
      override def call(): A = a(es).get()
    })
  }

  def asyncF[A, B](f: A => B): A => Par[B] = (a: A) => fork(unit(f(a)))

  def map[A, B](a: Par[A])(f: A => B): Par[B] = map2(a, unit(()))((a, _) => f(a))

  def sortPar(parList: Par[List[Int]]): Par[List[Int]] = map(parList)(_.sorted)

  //def parMap[A,B](ps:List[A])(f:A=>B):Par[List[B]] =

  def sequence[A](ps: List[Par[A]]): Par[List[A]] = ps match {
    case Nil => unit(Nil)
    case h :: t => map2(h, sequence(t))(_ :: _)
  }

  def parMap[A, B](ps: List[A])(f: A => B): Par[List[B]] = {
    val parList = ps map (asyncF(f))
    sequence(parList)
  }

  def parFilter[A](ps: List[A])(f: A => Boolean): Par[List[A]] = ps match {
    case Nil => unit(Nil)
    case h :: t => if (f(h)) map2(unit(h), parFilter(t)(f))(_ :: _) else parFilter(t)(f)
  }

  def parFilterByAsync[A](ps: List[A])(f: A => Boolean): Par[List[A]] = {
    val paList: List[Par[List[A]]] = ps.map(asyncF((a: A) => if (f(a)) List(a) else List()))
    map(sequence(paList))(_.flatten)
  }

//  def par[A](m:Moniad[A]):Moniad[Par[A]] = {
//
//
//
//    override def op(a1: Par[A], a2: Par[A]): Par[A] = ???
//
//    override def zero: Par[A] = unit(()=>None[A])
//  }
}
