package com.scalaStudy.unit1_string

package object StringUtils {

    implicit  class StringWrapper(val s:String){

        def increment() = s.map(c=>(c+1).toChar)
    }
}
