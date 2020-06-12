package com.scalaFuntionProgramming_2times.unit3_datastructure

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Node[A](nodeValue: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  /**
    * 统计树的节点数
    *
    * @param tree
    * @tparam A
    * @return
    */
  def getSize[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Node(_, left, right) => 1 + getSize(left) + getSize(right)
  }

  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(value) => value
    case Node(nodeValue, left, right) => Math.max(nodeValue, Math.max(maximum(left), maximum(right)))
  }

  def getDepth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Node(nodeValue, left, right) => 1 + Math.max(getDepth(left), getDepth(right))
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Leaf(value) => Leaf(f(value))
    case Node(nodeValue, left, right) => Node(f(nodeValue), map(left)(f), map(right)(f))
  }

  def fold[A,B](t: Tree[A])(f: A => B)(g: (B,B) => B): B = t match{
    case Leaf(value) => f(value)
    case Node(nodeValue,left,right)=>g(f(nodeValue),g(fold(left)(f)(g),fold(right)(f)(g)))
  }
}

