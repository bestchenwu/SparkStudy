package com.java8Study.unit5_Stream_operation;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;
/**
 * 构建勾股数
 *
 * @author chenwu on 2019.10.11
 */
public class pythagoreanTriples {

    public static void main(String[] args){
        //这里务必加上boxed(),才能在方法体里面使用mapToObj
        Stream<int[]> result  = IntStream.rangeClosed(1,20).boxed()
                .flatMap(a->
                            IntStream.rangeClosed(1,20)
                                        .filter(b->Math.sqrt(a*a+b*b)%1==0)
                                        .mapToObj(b->new int[]{a,b,(int)(a*a+b*b)}));
        result.forEach(a->System.out.println(Arrays.toString(a)));
    }
}
