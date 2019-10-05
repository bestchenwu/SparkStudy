package com.java8Study.unit2;

/**
 * 根据Apple的某个属性,返回一个boolean值,在java8中称之为谓词
 */
public interface ApplePredict {

    public boolean test(Apple apple);
}
class AppleWeightPredict implements ApplePredict{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>150;
    }
}

class AppleColorPredict implements ApplePredict{
    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("red");
    }
}