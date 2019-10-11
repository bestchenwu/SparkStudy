package com.java8Study.unit6_Collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 等价于{@link Collectors#toList()}
 *
 * @param <T>
 * @author  chenwu on 2019.10.11
 */
public class MyToListCollector<T> implements Collector<T, List<T>,List<T>> {

    /**
     * 创建一个新集合
     *
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ()->new ArrayList<T>();
    }

    /**
     * 将新的元素添加到集合中
     *
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (List<T> list,T t)->list.add(t);
    }

    /**
     * 合并两个集合
     *
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (List<T> t1,List<T> t2)->{t1.addAll(t2);return t1;};
    }

    /**
     * 是否对最终集合进行改变
     *
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        //这里也可以直接用java.util.Function.identity()
        return (List<T> t)->t;
    }

    /**
     * 表示该集合支持哪些{@link Characteristics}
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.CONCURRENT);
    }
}
