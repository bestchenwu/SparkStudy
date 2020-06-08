package com.scalaFuntionProgramming_2times.unit3_datastructure

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

sealed trait List[+A]

/**
  * Nothing是所有类型的子类,因此泛化后List[Nothing]也是所有List的子类<br/>
  * case object相比于单纯的object,多了toString等方法
  */
case object Nil extends List[Nothing]

case class Crons[A](val head: A, val tail: List[A]) extends List[A]

object List {

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case Crons(head, tail) => head + sum(tail)
  }

  def sum2(list: List[Int]): Int = foldRight(list, 0)(_ + _)

  def product(list: List[Int]): Int = list match {
    case Nil => 1
    case Crons(0, _) => 1
    case Crons(head, tail) => head * product(tail)
  }

  def apply[A](param: A*): List[A] = {
    if (param.isEmpty) Nil
    else Crons(param.head, apply(param.tail: _*))
  }

  def tail[A](list: List[A]) = list match {
    case Nil => Nil
    case Crons(_, tail) => tail
  }

  def setHead[A](list: List[A], newHead: A) = list match {
    case Nil => Nil
    case Crons(_, tail) => Crons(newHead, tail)
  }

  def drop[A](list: List[A], n: Int): List[A] = (list, n) match {
    case (Nil, _) => Nil
    case (list, 0) => list
    case (Crons(_, tail), m) => drop(tail, m - 1)
  }

  /**
    * 删除符合f函数的所有元素
    *
    * @param list
    * @param f
    * @tparam A
    * @return List
    */
  def dropWhile[A](list: List[A], f: A => Boolean): List[A] = list match {
    case Nil => Nil
    case Crons(head, tail) => if (f(head)) dropWhile(tail, f) else Crons(head, dropWhile(tail, f))
  }

  //todo:这个函数当碰到第一个头部元素不符合条件的时候,不就返回原列表了吗
  def dropWhile2[A](list: List[A])(f: A => Boolean): List[A] = list match {
    case Crons(head, tail) if (f(head)) => dropWhile2(tail)(f)
    case _ => list
  }

  def append[A](list1: List[A], list2: List[A]): List[A] = list1 match {
    case Nil => list2
    case Crons(head, tail) => Crons(head, append(tail, list2))
  }

  /**
    * 返回列表里除最后一个元素以外的所有元素
    *
    * @param list
    * @tparam A
    * @return
    */
  def init[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case Crons(_, Nil) => Nil
    case Crons(head, tail) => Crons(head, init(tail))
    //    case Crons(head, tail) => tail match {
    //      case Nil => List.apply(head)
    //      case Crons(_: A, Nil) => List.apply(head)
    //      case _ => Crons(head, init(tail))
    //    }
  }

  /**
    * 从右往左求递归
    *
    * @param list
    * @param z
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldRight[A, B](list: List[A], z: B)(f: (A, B) => B): B = list match {
    case Nil => z
    case Crons(head, tail) => f(head, foldRight(tail, z)(f))
  }

  def length[A](list: List[A]): Int = foldRight(list, 0)((_: A, y: Int) => y + 1)

  /**
    * foldRight有可能会造成stack over flow的错误，所以这里采用了尾递归的方式
    *
    * @param list
    * @param z
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldLeft[A, B](list: List[A], z: B)(f: (A, B) => B): B = {

    @tailrec
    def loop(list: List[A], z: B): B = list match {
      case Nil => z
      case Crons(head, tail) => loop(tail, f(head, z))
    }

    loop(list, z)
  }

  /**
    * 反转链表
    *
    * @param list
    * @tparam A
    * @return
    */
  def reverseList[A](list: List[A]): List[A] = {
    var resultList: List[A] = Nil

    @tailrec
    def loop(list: List[A]): List[A] = {
      list match {
        case Nil => resultList
        case Crons(head, tail) => {
          resultList = Crons(head, resultList)
          loop(tail)
        }
      }
    }

    loop(list)
  }

  /**
    * 这里List[A]()实际上调用的就是List.apply()方法
    *
    * @param list
    * @tparam A
    * @return
    */
  def reverseList2[A](list: List[A]): List[A] = foldLeft(list, List[A]())((a: A, b: List[A]) => Crons(a, b))

  def appendByFold[A](list1: List[A], list2: List[A]): List[A] = foldRight(list1, list2)((x: A, y: List[A]) => Crons(x, y))

  def appendAllList[A](list: List[List[A]]): List[A] = foldRight(list, Nil: List[A])(append)

  def add1(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case Crons(head, tail) => Crons(head + 1, add1(tail))
  }

  def add1ByFold(list: List[Int]): List[Int] = foldRight(list, Nil: List[Int])((h, t) => Crons(h + 1, t))

  def map[A, B](list: List[A])(f: A => B): List[B] = foldRight(list, Nil: List[B])((h, t) => Crons(f(h), t))

  /**
    * 找出所有符合f函数的元素
    *
    * @param list
    * @param f
    * @tparam A
    * @return
    */
  def filter[A](list: List[A])(f: A => Boolean): List[A] = foldRight(list, Nil: List[A])((h, t) => if (f(h)) Crons(h, t) else t)

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = foldRight(list, Nil: List[B])((h, t) => append(f(h), t))

  def addList(list1: List[Int], list2: List[Int]): List[Int] = (list1, list2) match {
    case (list1, Nil) => list1
    case (Nil, list2) => list2
    case (Crons(head1, tail1), Crons(head2, tail2)) => Crons(head1 + head2, addList(tail1, tail2))
  }


}

