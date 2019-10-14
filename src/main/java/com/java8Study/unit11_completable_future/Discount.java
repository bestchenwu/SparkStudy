package com.java8Study.unit11_completable_future;

public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static String apply(double price, Code code) {
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return String.format("%.2f", price * (100 - code.percentage) / 100);
    }
}
