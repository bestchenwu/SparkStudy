package com.java8Study.unit5_Stream_operation;

import com.java8Study.unit4_Stream.Dish;
import com.java8Study.unit4_Stream.Type;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 本章描述了
 */
public class StreamOperationTest {

    public static void main(String[] args){
        List<Dish> dishList = new ArrayList<Dish>();
        dishList.add(Dish.of("pork",false,800, Type.MEAT));
        dishList.add(Dish.of("chicken",false,400,Type.MEAT));
        dishList.add(Dish.of("jack",true,55,Type.FISH));
        dishList.add(Dish.of("french",true,530,Type.OTHER));
        dishList.add(Dish.of("salmon",false,450,Type.FISH));
        dishList.add(Dish.of("marry",true,200,Type.OTHER));
        //跳过流中的某几个元素
        //dishList.stream().filter((Dish dish)->dish.getCalories()>=400).skip(2).forEach(System.out::println);
        Stream<Dish> stream = dishList.stream();
        //判断任意一个元素匹配即可
        if(stream.anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        stream = dishList.stream();
        //判断所有元素匹配
        if(stream.allMatch(Dish::isVegetarian)){
            System.out.println("The menu is all vegetarian friendly!!");
        }
        //查找元素
        stream = dishList.stream();
        //查找任意一个
        Optional<Dish> findDish =  stream.filter((Dish dish)->dish.getCalories()>400).findAny();
        System.out.println(findDish.get());
        stream = dishList.stream();
        //返回第一个
        //stream.filter((Dish dish)->dish.getCalories()>400).findFirst();
        //元素求和
        int[] ints = new int[]{1,3,5};
        int result = Arrays.stream(ints).reduce(0,(int a,int b)->(a+b));
        System.out.println(result);
        //或者使用不带初始值的情况
        OptionalInt result1 = Arrays.stream(ints).reduce((int a, int b)->(a+b));
        System.out.println("OptionalInt="+result1.getAsInt());
        //获取最大、最小
        OptionalInt maxInt = Arrays.stream(ints).max();
        OptionalInt minInt = Arrays.stream(ints).min();
        //等价于
        OptionalInt maxInt2 = Arrays.stream(ints).reduce((a,b)->(Math.max(a,b)));
        //或者简化为
        //Arrays.stream(ints).reduce(Math::max);
        //或者
        //Arrays.stream(ints).reduce(Integer::max);
        System.out.println("maxInt value = "+maxInt.getAsInt());
        System.out.println("maxInt2 value = "+maxInt2.getAsInt());
    }
}
