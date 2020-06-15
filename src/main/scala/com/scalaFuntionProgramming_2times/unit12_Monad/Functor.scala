package com.scalaFuntionProgramming_2times.unit12_Monad

trait Functor[F[_]] {

  def map[A, B](a: F[A])(f: A => B): F[B]

  //将(A,B)这样的列表拆分成两个列表,一个列表只包含A，一个列表只包含B，这样的操作也称之为unzip
  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Right(value) => map(value)((a: B) => Right(a))
    case Left(value) => map(value)((a: A) => Left(a))
  }
}

object Functor {

  val listFunctor = new Functor[List] {
    override def map[A, B](a: List[A])(f: A => B): List[B] = a map f
  }
}