package com.java8Study.unit2;

public class Apple {

    private String color;
    private Integer weight;

    public static Apple of(String color,int weight){
        Apple apple = new Apple();
        apple.setColor(color);
        apple.setWeight(weight);
        return apple;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple[color="+color+",weight="+weight+"]";
    }
}
