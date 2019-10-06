package com.java8Study.unit3_Function;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args){
        Function<Integer,Integer> f = (Integer x)-> x.intValue()+1;
        Function<Integer,Integer> g = (Integer x)->x.intValue()*2;
        //等效于g(f())
        Function<Integer,Integer> andThen = f.andThen(g);
        //等效于f(g())
        Function<Integer,Integer> compose = f.compose(g);
        Integer result = andThen.apply(1);
        //第一个输出4
        System.out.println(result);
        Integer result1 = compose.apply(1);
        //第二个输出3
        System.out.println(result1);
    }
}
