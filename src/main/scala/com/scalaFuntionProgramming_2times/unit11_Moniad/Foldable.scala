package com.scalaFuntionProgramming_2times.unit11_Moniad

import com.scalaFuntionProgramming_2times.unit3_datastructure.{Leaf, Node, Tree}

trait Foldable[F[_]] {

  def foldRight[A, B](a: F[A])(z: B)(f: (A, B) => B): B

  def foldLeft[A, B](a: F[A])(z: B)(f: (B, A) => B): B

  def foldMap[A, B](as: F[A])(f: A => B)(m: Moniad[B]): B

  def concatenate[A](as: F[A])(m: Moniad[A]): A = foldLeft(as)(m.zero)(m.op)


  import com.scalaFuntionProgramming_2times.unit11_Moniad.Moniad.listMoniad
  def toList[A](fa:F[A]):List[A] = foldRight(fa)(List[A]())((a:A,list:List[A])=>a :: list)

}

object Foldable {

  val foldableList = new Foldable[List] {
    override def foldRight[A, B](a: List[A])(z: B)(f: (A, B) => B): B = a.foldRight(z)(f)

    override def foldLeft[A, B](a: List[A])(z: B)(f: (B, A) => B): B = a.foldLeft(z)(f)

    override def foldMap[A, B](as: List[A])(f: A => B)(m: Moniad[B]): B = as match {
      case Nil => m.zero
      case h :: t => m.op(f(h), foldMap(t)(f)(m))
    }
  }

  val foldableTree = new Foldable[Tree] {
    override def foldRight[A, B](a: Tree[A])(z: B)(f: (A, B) => B): B = a match {
      case Leaf(value) => f(value, z)
      case Node(_, l, r) => foldRight(l)(foldRight(r)(z)(f))(f)
    }

    override def foldLeft[A, B](a: Tree[A])(z: B)(f: (B, A) => B): B = a match {
      case Leaf(value) => f(z,value)
      case Node(_, l, r) => foldLeft(l)(foldLeft(r)(z)(f))(f)
    }

    override def foldMap[A, B](as: Tree[A])(f: A => B)(m: Moniad[B]): B = ???
  }

  val optionFoldable = new Foldable[Option] {
    override def foldRight[A, B](a: Option[A])(z: B)(f: (A, B) => B): B = a match {
      case Some(aa) => f(aa, z)
      case None => z
    }

    override def foldLeft[A, B](a: Option[A])(z: B)(f: (B, A) => B): B = a match {
      case Some(aa) => f(z, aa)
      case None => z
    }

    override def foldMap[A, B](as: Option[A])(f: A => B)(m: Moniad[B]): B = as match {
      case Some(a) => f(a)
      case None => m.zero
    }
  }


}
