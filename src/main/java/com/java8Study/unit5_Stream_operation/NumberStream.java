package com.java8Study.unit5_Stream_operation;

import com.java8Study.unit4_Stream.Dish;
import com.java8Study.unit4_Stream.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 数值流<br/>
 * 可以将流通过intToStream方法转换为数值流,这样就可以调用max,sum等了
 *
 * @author chenwu on 2019.10.11
 */
public class NumberStream {

    public static void main(String[] args){
        List<Dish> dishList = new ArrayList<Dish>();
        dishList.add(Dish.of("pork",false,800, Type.MEAT));
        dishList.add(Dish.of("chicken",false,400,Type.MEAT));
        dishList.add(Dish.of("jack",true,55,Type.FISH));
        dishList.add(Dish.of("french",true,530,Type.OTHER));
        dishList.add(Dish.of("salmon",false,450,Type.FISH));
        dishList.add(Dish.of("marry",true,200,Type.OTHER));
        //转换为数值流
        int sum = dishList.stream().mapToInt(Dish::getCalories).sum();
        //转换为对象流
        //dishList.stream().mapToInt(Dish::getCalories).boxed();
        //range不包含结束值,产生0-100的数值范围
        //获取0-100的所有偶数
        long evenCount = IntStream.range(0,100).filter(n->n%2==0).count();
        System.out.println(evenCount);
        //不包含结束值
        //IntStream.rangeClosed(0,100);
    }
}
