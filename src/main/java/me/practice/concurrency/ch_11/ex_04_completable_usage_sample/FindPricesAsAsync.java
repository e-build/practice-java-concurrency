package me.practice.concurrency.ch_11.ex_04_completable_usage_sample;

public class FindPricesAsAsync {

    public static void main(String[] args) {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.currentTimeMillis();
        System.out.println(priceFinder.findPricesAsParallel("Mac"));
//        System.out.println(priceFinder.findPricesAsCompletable("Mac"));
//        System.out.println(priceFinder.findPricesAsCompletableWithExecutor("Mac"));
//        System.out.println(priceFinder.findPricesWithComposeOperation("Mac"));
        long duration = (System.currentTimeMillis() - start);
        System.out.println("Duration millis : " + duration);
    }
}

