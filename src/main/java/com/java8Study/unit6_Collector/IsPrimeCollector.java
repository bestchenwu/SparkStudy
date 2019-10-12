package com.java8Study.unit6_Collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 判断是否是质数的收集器
 *
 * @author chenwu on 2019.10.12
 */
public class IsPrimeCollector implements Collector<Integer, Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        Map<Boolean, List<Integer>> map = new HashMap<>();
        map.put(Boolean.TRUE,new ArrayList<>());
        map.put(Boolean.FALSE,new ArrayList<>());
        return ()->map;
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> map,Integer n)->{
            if(isPrime(n)){
                map.get(Boolean.TRUE).add(n);
            }else{
                map.get(Boolean.FALSE).add(n);
            }
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,Map<Boolean, List<Integer>> map2)->{map1.putAll(map2);return map1;};
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    private boolean isPrime(Integer n){
        return IntStream.range(2,n).boxed().noneMatch((Integer i)->n%i==0);
    }
}
