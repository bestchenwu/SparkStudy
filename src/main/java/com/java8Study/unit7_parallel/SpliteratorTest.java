package com.java8Study.unit7_parallel;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * {@link java.util.Spliterator}的学习<br/>
 * Spliterator分为三个步骤,分别为:<br/>
 * 1、{@link Spliterator#trySplit()} 将流进行细分,返回一个新的分割器,直到不能分割返回null为止<br/>
 * 2、{@link Spliterator#tryAdvance(Consumer)}对Spliterator调用Consumer<br/>
 * 3、{@link Spliterator#estimateSize()} 估算剩余的元素的数量
 *
 * @author  chenwu on 2019.10.15
 */
public class SpliteratorTest {


    public static void main(String[] args){
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        Stream<Character> stream= IntStream.range(0,SENTENCE.length()).mapToObj(i->SENTENCE.charAt(i));
        //当使用parallel的时候,由于存在重复计数的问题,所以统计的单词数不准确
//        WordCounter wrongWordCount = stream.parallel().reduce(new WordCounter(0,false),WordCounter::accumulator,WordCounter::combine);
//        System.out.println(wrongWordCount.getCount());
        WordCountSpliterator wordCountSpliterator = new WordCountSpliterator(SENTENCE);
        Stream<Character> parallelCharacterStream = StreamSupport.stream(wordCountSpliterator,true);
        WordCounter trueWordCount = parallelCharacterStream.parallel().reduce(new WordCounter(0,false),WordCounter::accumulator,WordCounter::combine);
        System.out.println("trueWordCount:"+trueWordCount.getCount());
    }

    //顺序统计单词数
    private static int calculateWordCount(String str){
        int count = 0;
        boolean isLastSpaceFlag = false;
        for(char item:str.toCharArray()){
                //这种方法是统计字符数
//                if(!Character.isWhitespace(item)){
//                    count+=1;
//                }
            if(Character.isWhitespace(item)){
                isLastSpaceFlag = true;
            }else{
                if(isLastSpaceFlag){
                    count+=1;
                    isLastSpaceFlag = false;
                }
            }
        }
        return count;
    }
}
