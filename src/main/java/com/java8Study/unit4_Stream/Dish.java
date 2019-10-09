package com.java8Study.unit4_Stream;

/**
 * 菜肴
 *
 * @author chenwu on 2019.10.7
 */
public class Dish {

    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public static Dish of(String name, boolean vegetarian, int calories, Type type) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setVegetarian(vegetarian);
        dish.setCalories(calories);
        dish.setType(type);
        return dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}

