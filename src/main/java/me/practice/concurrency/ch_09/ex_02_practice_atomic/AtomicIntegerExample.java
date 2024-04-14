package me.practice.concurrency.ch_09.ex_02_practice_atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static AtomicInteger counter = new AtomicInteger(0);
    private static int NUM_THREADS = 5;
    private static int NUM_INCREMENTS = 500_000;

    public static void main(String[] args) {

        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    counter.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " -> Counter: " + counter.get());
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Final value: " + counter.get());
    }
}
