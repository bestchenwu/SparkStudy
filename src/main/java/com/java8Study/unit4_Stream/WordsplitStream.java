package com.java8Study.unit4_Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordsplitStream {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "world");
        //这里一个string[] 被映射为多个字符的stream
        List<String> result = words.stream().map((String s) -> s.split("")).flatMap((String[] strs) -> Arrays.stream(strs)).distinct().collect(Collectors.toList());
        //System.out.println(result);
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);
        list1.stream().flatMap(i->list2.stream().map(j->new int[]{i,j})).forEach((int[] ints)->System.out.println(Arrays.toString(ints)));

    }
}
