package com.java8Study.unit11_completable_future;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import static  java.util.stream.Collectors.toList;

/**
 * {@link CompletableFuture}的使用
 * 详细可以参考:https://www.jianshu.com/p/220d05525f27
 *
 * @author chenwu on 2019.10.14
 */
public class Shop {

    private Random random = new Random(47);

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getDiscountPrice(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }

    public List<String> findDiscountPrices(List<String> products){
        List<String> result = products.stream().parallel().map((String product)->getDiscountPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount).collect(toList());
        return result;
    }

    public List<String> findDiscountPricesByCompleteFuture(List<String> products){
        Executor executor = Executors.newFixedThreadPool(Math.min(products.size(), 100),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //使用守护线程,这种方式不会组织程序的关停
                        t.setDaemon(true);
                        return t;
                    }
                });
        List<CompletableFuture<String>> list =  products.stream().map((String product)->CompletableFuture.supplyAsync(()->getDiscountPrice(product),executor))
                //同步操作,等待执行完成
                .map(future->future.thenApply(Quote::parse))
                //异步操作,组合用法
                .map(future->future.thenCompose(quote -> CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)))
                .collect(toList());
        List<String> list1 = list.stream().map(CompletableFuture::join).collect(toList());
        //进一步优化可以使用 直到获得结果才执行,相当于回调函数
        list.stream().map(future->future.thenAccept(System.out::println));
        return list1;
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> completableFuture = new CompletableFuture();
        new Thread( ()->{
            try{
                double price = calculatePrice(product);
                completableFuture.complete(price);
            }catch(Exception e){
                //将价格计算过程中发生的异常也捕获
                completableFuture.completeExceptionally(e);
            }
        }).start();
        return completableFuture;
    }

    /**
     * 上述方法的简写形式
     *
     * @param product
     * @return Future<Double>
     * @author chenwu on 2019.10.14
     */
    public Future<Double> getPriceAsync1(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    public List<String> getListPrices(List<String> products){
        List<String> list = products.stream().map((String product)->String.format("%.2f%n",getPrice(product))).collect(Collectors.toList());
        return list;
    }

    public List<String> getListPrices1(List<String> products){
        List<CompletableFuture<String>> list = products.stream().map((String product)->CompletableFuture.supplyAsync(()->String.format("%.2f%n",calculatePrice(product)))).collect(toList());
        //等待结果返回
        return list.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private double calculatePrice(String product) {
        //休息一秒再计算价格
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
