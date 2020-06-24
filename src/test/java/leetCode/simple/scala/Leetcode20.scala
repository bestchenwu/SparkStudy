package leetCode.simple.scala

import scala.collection.mutable.Stack

object Leetcode20 {

  def isValid(s: String): Boolean = {
      val stack = Stack[Char]()
      for(char<-s){
          if(stack.isEmpty){
              stack.push(char)
          }else{
              val topChar = stack.top
              if(((char == ')' && topChar=='(')) || ((char == ']' && topChar == '[')) || ((char=='}' && topChar == '{'))){
                  stack.pop()
              }else{
                  stack.push(char)
              }
          }
      }
      stack.isEmpty
  }

  def main(args: Array[String]): Unit = {
      val str = "{[]}"
      println(isValid(str))
  }
}
