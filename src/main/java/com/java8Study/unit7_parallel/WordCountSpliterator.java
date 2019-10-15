package com.java8Study.unit7_parallel;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 支持对字符串按空格拆分的拆分器
 *
 * @author chenwu on 2019.10.15
 */
public class WordCountSpliterator implements Spliterator<Character> {

    private String string;//要处理的字符串
    private long currintIndex = 0l;//字符串的下标值

    public WordCountSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt((int) (currintIndex++)));
        return currintIndex < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        long leftLength = estimateSize();
        //当低于十个字符的时候不拆分
        if (leftLength < 10) {
            return null;
        }
        long splitIndex = currintIndex + leftLength / 2;
        for (long i = splitIndex; i < string.length(); i++) {
            if (Character.isWhitespace(string.charAt((int) i))) {
                WordCountSpliterator newWordCountSpliterator = new WordCountSpliterator(string.substring((int) currintIndex, (int) i));
                currintIndex = i;
                return newWordCountSpliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currintIndex;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
