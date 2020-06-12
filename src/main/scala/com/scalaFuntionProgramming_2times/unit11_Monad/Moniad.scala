package com.scalaFuntionProgramming_2times.unit11_Monad

import com.scalaFuntionProgramming_2times.unit7_paralle.Par
import com.scalaFuntionProgramming_2times.unit7_paralle.Par.Par
import com.spark.constants.SymbolConstants

trait Moniad[A] {

  def op(a1: A, a2: A): A

  def zero: A
}

object Moniad {

  val stringMoniad: Moniad[String] = new Moniad[String] {
    override def op(a1: String, a2: String): String = if (a1.isEmpty) a2 else a1 + SymbolConstants.SYMBOL_DH + a2

    override def zero: String = ""
  }

  def listMoniad[A] = new Moniad[List[A]] {
    override def op(a1: List[A], a2: List[A]): List[A] = a1 ++ a2

    override def zero: List[A] = List()
  }

  val intAddition: Moniad[Int] = new Moniad[Int] {
    override def op(a1: Int, a2: Int): Int = a1 + a2

    override def zero: Int = 0
  }

  val intMultiplication: Moniad[Int] = new Moniad[Int] {
    override def op(a1: Int, a2: Int): Int = a1 * a2

    override def zero: Int = 1
  }

  val booleanOr: Moniad[Boolean] = new Moniad[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2

    override def zero: Boolean = true
  }

  val booleanAnd: Moniad[Boolean] = new Moniad[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2

    override def zero: Boolean = true
  }

  def optionMoniad[A]: Moniad[Option[A]] = new Moniad[Option[A]] {
    override def op(a1: Option[A], a2: Option[A]): Option[A] = if (a1.isEmpty) a2 else a1

    override def zero: Option[A] = None
  }

  def endoMoniod[A]: Moniad[A => A] = new Moniad[A => A] {
    override def op(a1: A => A, a2: A => A): A => A = a1

    override def zero: A => A = (a: A) => a
  }

  def par[A](m: Moniad[A]): Moniad[Par[A]] = new Moniad[Par[A]] {
    override def op(a1: Par[A], a2: Par[A]): Par[A] = Par.map2(a1, a2)(m.op)

    override def zero: Par[A] = Par.unit(m.zero)
  }

  def concetenate[A](as: List[A], m: Moniad[A]): A = as.foldLeft(m.zero)(m.op)

  def foldMap[A, B](as: List[A], m: Moniad[B])(f: A => B): B = concetenate(as.map(f), m)

  def foldMap[A, B](v: IndexedSeq[A], m: Moniad[B])(f: A => B): B = {
    if (v.size <= 1) {
      if (v.isEmpty) m.zero else f(v.headOption.get)
    } else {
      val (left, right) = v.splitAt(v.length / 2)
      m.op(foldMap(left, m)(f), foldMap(right, m)(f))
    }
  }

  def parFoldMap[A, B](v: IndexedSeq[A], m: Moniad[B])(f: A => B): Par[B] = {
    val seqParB: IndexedSeq[Par[B]] = v.map(Par.asyncF(f))
    val mParB: Moniad[Par[B]] = par(m)
    val result: Par[B] = seqParB.foldLeft(mParB.zero)(mParB.op)
    result
  }


  //检查as是否是有序的
  def checkIndexedSexIsSorted(as: IndexedSeq[Int]): Boolean = {

    val sortedMoniod = new Moniad[Option[(Int, Int, Boolean)]] {
      override def op(a1: Option[(Int, Int, Boolean)], a2: Option[(Int, Int, Boolean)]): Option[(Int, Int, Boolean)] = {
        (a1, a2) match {
          case (a1, None) => a1
          case (None, a2) => a2
          case (Some((x1, y1, p)), Some((x2, y2, q))) => Some((x1 min x2, y1 max y2, p && q && y1 <= x2))
        }
      }

      override def zero: Option[(Int, Int, Boolean)] = None
    }
    foldMap(as, sortedMoniod)((a: Int) => Some(a, a, true)).map(_._3).getOrElse(true)
  }

  sealed trait WC

  case class Stub(strings: String) extends WC

  case class Part(left: String, words: Int, right: String) extends WC

  val WCMoniads: Moniad[WC] = new Moniad[WC] {
    override def op(a1: WC, a2: WC): WC = {
      (a1, a2) match {
        case (Stub(a), Stub(b)) => Stub(a + b)
        case (Stub(a), Part(l, words, r)) => Part(l + a, words, r)
        case (Part(l, words, r), Stub(b)) => Part(l, words, r + b)
        case (Part(l1, w1, b1), Part(l2, w2, b2)) => Part(l1, w1 + (if ((b1 + l2).isEmpty) 0 else 1) + w2, b2)
      }
    }

    override def zero: WC = Stub("")
  }

  /**
    * 统计字符串中被空格区分开的单词个数
    *
    * @param words
    * @return
    */
  def countWords(words: String): Int = {
    def wc(c: Char): WC = {
      if (c.isWhitespace) {
        Part("", 0, "")
      } else {
        Stub(c.toString)
      }
    }

    val resultWC = foldMap(words, WCMoniads)(wc)

    def unStub(s: String) = s.length min 1

    resultWC match {
      case Stub(c) => unStub(c)
      case Part(l, words, r) => unStub(l) + words + unStub(r)
    }
  }
}
