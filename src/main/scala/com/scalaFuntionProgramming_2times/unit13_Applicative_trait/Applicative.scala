package com.scalaFuntionProgramming_2times.unit13_Applicative_trait

import com.scalaFuntionProgramming_2times.unit12_Monad.Functor

trait Applicative[F[_]] extends Functor[F] {

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]

  def unit[A](a: => A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = map2(fa, unit(()))((a: A, _) => f(a))

  def traverse[A, B](as: List[A])(f: A => F[B]): F[List[B]] = as.foldRight(unit(List[B]()))((a, list) => map2(f(a), list)(_ :: _))

  def sequence[A](fas: List[F[A]]): F[List[A]] = fas.foldRight(unit(List[A]()))((a: F[A], list: F[List[A]]) => map2(a, list)(_ :: _))

  def replication[A](a: F[A], m: Int): F[List[A]] = sequence(List.fill(m)(a))

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((_, _))

  def apply[A, B](fab: F[A => B])(fa: F[A]): F[B] = map2(fa, fab)((aa, ff) => ff(aa))

  def mapViaApply[A, B](fa: F[A])(f: A => B): F[B] = apply(unit(f))(fa)

  def map3[A, B, C, D](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => D): F[D] = {
    val productFab = product(fa, fb)
    map2(productFab, fc)((item1, c) => f(item1._1, item1._2, c))
  }
}

object Applicative {

  val optionApplicative = new Applicative[Option] {
    override def map2[A, B, C](fa: Option[A], fb: Option[B])(f: (A, B) => C): Option[C] = fa flatMap (aa => fb.map(bb => f(aa, bb)))

    override def unit[A](a: => A): Option[A] = Some(a)
  }
}
