package com.scalaFuntionProgramming_2times.unit12_Monad

import com.scalaFuntionProgramming_2times.unit7_paralle.Par
import com.scalaFuntionProgramming_2times.unit7_paralle.Par.Par
import shapeless.OpticDefns.compose

trait Mon[F[_]] extends Functor[F] {

  def unit[A](a: => A): F[A]

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)((a: A) => unit(f(a)))

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = flatMap(fa)((a: A) => (map(fb)(f(a, _))))

  def sequence[A](lma: List[F[A]]): F[List[A]] = lma match {
    case Nil => unit(List())
    case h :: t => map2(h, sequence(t))(_ :: _)
  }

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] = sequence(la map f)

  def replicateM[A](n: Int, ma: F[A]): F[List[A]] = sequence(List.fill(n)(ma))

  def product[A, B](ma: F[A], mb: F[B]): F[(A, B)] = map2(ma, mb)((_, _))

  //  def filterM[A](ms: List[A])(f: A => F[Boolean]): F[List[A]] = {
  ////    ms.foldRight(unit(List[A]()))((x,y) =>
  ////      compose(f, (b: Boolean) => if (b) map2(unit(x),y)(_ :: _) else y)(x))
  ////  }

  def compse[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = (a: A) => flatMap(flatMap(unit(a))(f))(g)

  //这样可以定义Monad结合原则
  //compse(compse(f,g),h) = compose(f,compse(g,h))

  def join[A](mm: F[F[A]]): F[A] = flatMap(mm)(item => item)
}

object Mon {

  val parMon = new Mon[Par] {
    override def unit[A](a: => A): Par[A] = Par.unit(a)

    override def flatMap[A, B](fa: Par[A])(f: A => Par[B]): Par[B] = Par.flatMap(fa)(f)
  }

  val optionMon = new Mon[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)
  }

  val listMon = new Mon[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)
  }

  val streamMon = new Mon[Stream] {
    override def unit[A](a: => A): Stream[A] = Stream(a)

    override def flatMap[A, B](fa: Stream[A])(f: A => Stream[B]): Stream[B] = fa.flatMap(f)
  }

  case class Id[A](value: A)

  val idMon = new Mon[Id] {
    override def unit[A](a: => A): Id[A] = Id(a)

    override def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = f(fa.value)
  }
}

