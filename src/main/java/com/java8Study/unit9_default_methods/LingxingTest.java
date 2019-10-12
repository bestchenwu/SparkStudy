package com.java8Study.unit9_default_methods;

/**
 * 菱形问题
 */
public class LingxingTest implements B, C {

    public static void main(String[] args) {
        //父类优先权,距离优先法则
        new LingxingTest().hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface B extends A {

    default void hello() {
        System.out.println("Hello from B");
    }
}

interface C extends A {

    //如果加上这一段,则会编译不通过,因为子类不知道该继承哪一个
//    default void hello() {
//        System.out.println("Hello from C");
//    }
}

