package com.java8Study.unit2;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import static java.util.Comparator.comparing;
public class AppleUtilTest {

    public static void main(String[] args){
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(Apple.of("red",18));
        apples.add(Apple.of("blue",55));
        //List<Apple> result = AppleUtil.filterAppleByPredict(apples,new AppleColorPredict());
        //第二种写法,使用lambda表达式,将方法作为值参数传入进去
        List<Apple> result = AppleUtil.filterAppleByPredict(apples,(Apple apple)->"red".equals(apple.getColor()));
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //使用匿名表达式
        apples.sort((Apple a1,Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
        //使用等值方法引用
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.sort(comparing(Apple::getWeight));
        System.out.println(result);
        Thread t = new Thread(()->System.out.println(""));
    }
}
