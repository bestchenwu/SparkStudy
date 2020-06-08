package com.scalaFuntionProgramming_2times.unit3_datastructure

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class node[A](nodeValue: A, left: Tree[A], right: Tree[A]) extends Tree[A]

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
    case node(_, left, right) => 1 + getSize(left) + getSize(right)
  }

  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(value) => value
    case node(nodeValue, left, right) => Math.max(nodeValue, Math.max(maximum(left), maximum(right)))
  }

  def getDepth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case node(nodeValue, left, right) => 1 + Math.max(getDepth(left), getDepth(right))
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Leaf(value) => Leaf(f(value))
    case node(nodeValue, left, right) => node(f(nodeValue), map(left)(f), map(right)(f))
  }

}

