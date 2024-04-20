package me.practice.concurrency.ch_11.ex_04_completable_usage_sample;

public class GetPriceAsSync {

    public static void main(String[] args) {
        Shop shop = new Shop("Jay Shop");
        long start = System.currentTimeMillis();
        double price = shop.getPrice("Jay's Mac");
//        Future<Double> price = shop.getAsyncPrice("Jay's Mac");

        doSomethingElse();
        long end = System.currentTimeMillis();
        try {
            System.out.println("Price : " + price);
            System.out.println("Duration millis : " + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSomethingElse() {
        // do something else
    }
}

