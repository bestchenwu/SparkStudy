package com.java8Study.unit5_Stream_operation;

import java.util.Arrays;
import java.util.List;

/**
 * 第5章第5节的练习
 *
 * @author chenwu on 2019.10.10
 */
public class Exercise_5_5 {

    public static void main(String[] args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(alan, 2011, 200),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //找出2011年发生的所有交易，并按交易额排序（从低到高）
        //transactions.stream().filter((Transaction transaction)->transaction.getYear()==2011).sorted((Transaction t1,Transaction t2)->(Integer.compare(t1.getValue(),t2.getValue()))).forEach(System.out::println);
        //交易员都在哪些不同的城市工作过？
        //transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().forEach(System.out::println);
        //查找所有来自于剑桥的交易员，并按姓名排序
        //transactions.stream().map(Transaction::getTrader).filter((Trader trader)->"Cambridge".equals(trader.getCity())).distinct().sorted((Trader trader1,Trader trader2)->trader1.getName().compareTo(trader2.getName())).forEach(System.out::println);
        //有没有交易员是在米兰工作的？ 输出true
        //System.out.println(transactions.stream().map(Transaction::getTrader).anyMatch((Trader trader)->"Milan".equals(trader.getCity())));
        //打印生活在剑桥的交易员的所有交易额
        //System.out.println(transactions.stream().filter((Transaction transaction)->"Cambridge".equals(transaction.getTrader().getCity())).map(Transaction::getValue).reduce((a,b)->(a+b)).orElse(0)) ;
        //所有交易中，最高的交易额是多少？
        //System.out.println(transactions.stream().map(Transaction::getValue).max(Integer::compareTo).orElse(0));
        //找到交易额最小的交易
        //System.out.println(transactions.stream().min((t1,t2)->Integer.compare(t1.getValue(),t2.getValue())).get());
    }
}
