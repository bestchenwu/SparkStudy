package com.java8Study.unit6_Collector;

import com.java8Study.unit4_Stream.Dish;
import com.java8Study.unit4_Stream.Type;
import scala.collection.immutable.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.partitioningBy;
/**
 * Collector的测试
 *
 * @author chenwu on 2019.10.11
 */
public class CollectorTest {

    public static void main(String[] args) {
        List<Dish> dishList = new ArrayList<Dish>();
        dishList.add(Dish.of("pork", false, 800, Type.MEAT));
        dishList.add(Dish.of("chicken", true, 400, Type.MEAT));
        dishList.add(Dish.of("french", true, 530, Type.OTHER));
        dishList.add(Dish.of("salmon", false, 450, Type.FISH));
        //dishList.stream().collect(Collectors.counting());
        //System.out.println(dishList.stream().map(Dish::getCalories).collect(Collectors.maxBy(Integer::compareTo)));
        //System.out.println(dishList.stream().map(Dish::getCalories).collect(Collectors.reducing((d1, d2) -> Math.min(d1, d2))));
        //String shortMenu = dishList.stream()
         //       .collect(Collectors.reducing("",Dish::getName, (d1, d2) -> d1+d2));
        //分组(一级分组)
        //如果不指定第二个参数,则默认收集器为toList()
        Map<String,List<Dish>> map = dishList.stream().collect(Collectors.groupingBy(Dish::getName));
        //二级分组
        Map<String,Map<String,List<Dish>>> map1  = dishList.stream().collect(Collectors.groupingBy(Dish::getName,Collectors.groupingBy(dish->{
            if(dish.getCalories()>400){
                return "high";
            }else{
                return "low";
            }
        })));
       Map<Type,Set<Boolean>> set = dishList.stream().collect(groupingBy(Dish::getType,mapping(Dish::isVegetarian,toSet()))) ;
        //System.out.println(set);
        //分区
        //分区可以看做是分组的一种特殊形式，但区别在于分区的函数需要为返回boolean的函数，统称为谓词函数Predicate
        Map<Boolean,List<Dish>> map2 = dishList.stream().collect(groupingBy(Dish::isVegetarian));
        Map<Boolean,List<Dish>> map3 = dishList.stream().collect(partitioningBy(Dish::isVegetarian));
        //System.out.println(map2);
        //System.out.println(map3);
        //将1-n个数分为质数和非质数
        int n = 100;
        //判断数字是否为质数
        //IntStream.range(1,n).noneMatch((int i)->n%i==0);
        //List<Integer> list = IntStream.range(2,n).boxed().collect(partitioningBy((Integer i)->isPrime(i))).get(Boolean.TRUE);
        //System.out.println(list);
        List<Integer> list = IntStream.range(0,5).boxed().collect(new MyToListCollector<Integer>());
        System.out.println(list);
    }

    public static boolean isPrime(Integer n){
        return IntStream.range(2,n.intValue()).noneMatch((int i)->n.intValue()%i==0);
    }
}
