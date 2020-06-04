package com.scalaStudy.unit9_FunctionPrograming_closure

class Foo {

  def exec(f: String => Unit, name: String) = {
    f(name)
  }
}
