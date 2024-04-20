package me.practice.concurrency.ch_11.ex_04_completable_usage_sample;

import java.util.concurrent.Future;

public class GetPriceAsAsync {

    public static void main(String[] args) {
        Shop shop = new Shop("Jay Shop");
        long start = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getAsyncPrice("Jay's Mac");
//        Future<Double> futurePrice = shop.getAsyncPriceRefactoring("Jay's Mac");

        doSomethingElse();
        long end = System.currentTimeMillis();
        try {
            System.out.println("Price : " + futurePrice.get());
            System.out.println("Duration millis :" + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSomethingElse() {
        // do something else
    }
}

