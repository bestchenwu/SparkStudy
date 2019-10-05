package com.java8Study.unit2;
import java.util.List;
import java.util.ArrayList;
public class AppleUtilTest {

    public static void main(String[] args){
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(Apple.of("red",18));
        apples.add(Apple.of("blue",55));
        //List<Apple> result = AppleUtil.filterAppleByPredict(apples,new AppleColorPredict());
        //第二种写法,使用lambda表达式,将方法作为值参数传入进去
        List<Apple> result = AppleUtil.filterAppleByPredict(apples,(Apple apple)->"red".equals(apple.getColor()));
        System.out.println(result);
        Thread t = new Thread(()->System.out.println(""));
    }
}
