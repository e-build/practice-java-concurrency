package me.practice.concurrency.ch_11.ex_04_completable_usage_sample;

import java.util.concurrent.Future;

public class FindPricesAsSync {

    public static void main(String[] args) {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        System.out.println(priceFinder.findPrices("Mac"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("완료 시간:  " + duration + " msecs");
    }
}

