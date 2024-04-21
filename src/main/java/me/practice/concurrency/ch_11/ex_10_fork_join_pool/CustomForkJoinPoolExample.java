package me.practice.concurrency.ch_11.ex_10_fork_join_pool;

import java.util.concurrent.ForkJoinPool;

public class CustomForkJoinPoolExample {

    public static void main(String[] args) {

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        CustomRecursiveTask task = new CustomRecursiveTask(array, 0, array.length);
        long result = pool.invoke(task);

        System.out.println("result = " + result);
        System.out.println("pool = " + pool);
        System.out.println("stealing = " + pool.getStealCount());

        pool.shutdown();
    }
}
