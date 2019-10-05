package com.java8Study.unit2;

import java.util.ArrayList;
import java.util.List;
public class AppleUtil {

    public static List<Apple> filterGreenApple(List<Apple> inventory){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if("red".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterAppleByPredict(List<Apple> inventory,ApplePredict predict){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if(predict.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


}
