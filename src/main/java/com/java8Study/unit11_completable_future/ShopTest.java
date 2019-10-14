package com.java8Study.unit11_completable_future;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 测试{@link java.util.concurrent.CompletableFuture}
 *
 * @author chenwu on 2019.10.14
 */
public class ShopTest {

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();
//        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation returned after " + invocationTime
//                + " msecs");
//        // 执行更多任务，比如查询其他商店
//        //Thread.sleep(10);
//        // 在计算商品价格的同时
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n", price);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");

        List<String> products = Arrays.asList("haha", "sweet","jack","ff","aa","bb","cc","dd","ee","ff");
           long start = System.nanoTime();
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation returned after " + invocationTime
//                + " msecs");
//        shop.getListPrices1(products).stream().forEach(System.out::println);
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");
        //获取批量打折商品
        //顺序执行,没有使用并发
        List<String> result =  shop.findDiscountPrices(products);
        System.out.println("Price returned after " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        start = System.nanoTime();
        //使用并发
        List<String> result1 = shop.findDiscountPricesByCompleteFuture(products);
        //输出
        //Price returned after 248 msecs
        //Price returned after 57 msecs
        //使用CompleteFuture,性能确实比单纯的使用stream().parallel()性能好
        System.out.println("Price returned after " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        //System.out.println(result1);
    }
}
