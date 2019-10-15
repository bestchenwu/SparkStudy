package com.java8Study.unit7_parallel;

public class WordCounter {

    private int count = 0;
    private boolean lastSpace = false;

    public int getCount() {
        return count;
    }

    public WordCounter(int count, boolean lastSpace){
        this.count = count;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulator(Character char0){
        if(Character.isWhitespace(char0)){
            return lastSpace?this:new WordCounter(count,true);
        }else{
            return lastSpace?new WordCounter(count+1,false):this;
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(this.count+wordCounter.count,wordCounter.lastSpace);
    }
}
