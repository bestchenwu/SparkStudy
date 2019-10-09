package com.java8Study.unit4_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
/**
 * 使用JAVA流式运算,Stream类似于scala中的stream,表示流式计算<br/>
 * 流式计算用collect触发计算操作<br/>
 * 一个流通常包含一个数据源,一个中间操作链,一个终端操作(例如collect,count,foreach等等)<br/>
 * 这些操作与流数据没有关系
 *
 * @author chenwu on 2019.10.7
 */
public class StreamTest {

    public static void main(String[] args){
        List<Dish> dishList = new ArrayList<Dish>();
        dishList.add(Dish.of("pork",false,800,Type.MEAT));
        dishList.add(Dish.of("chicken",false,400,Type.MEAT));
        dishList.add(Dish.of("french",true,530,Type.OTHER));
        dishList.add(Dish.of("salmon",false,450,Type.FISH));
        //以下是使用lambda表达式
        List<String> resultList = dishList.stream().filter((Dish dish)->dish.isVegetarian())
                .map((Dish dish)->dish.getName())
                .limit(3)
                .collect(toList());
        //也可以使用函数值的方式
        List<String> resultList1 = dishList.stream().filter(Dish::isVegetarian)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        //System.out.println(resultList);
        //System.out.println(resultList1);
        //流只能被遍历一次,如果要遍历第二次,则需要重新从源头建立流
        Stream<String> stream = resultList1.stream();
        //stream.forEach((String item)->System.out.println(item));
        //在执行第二遍的时候会报stream has already been operated upon or closed的错误
        //stream.forEach(System.out::println);
        //limit操作在这里起了短路的作用,所以不用计算全部值,另外filter和map在一次遍历的过程中就做了合并操作,
        //称之为循环合并
        List<String> resultList2  = dishList.stream().filter((Dish dish)->{
            System.out.println("filter:"+dish);
            return dish.getCalories()>300;
        }).map((Dish dish)->{
            System.out.println("map:"+dish);
            return dish.getName();
        }).limit(2).collect(toList());
    }
}
