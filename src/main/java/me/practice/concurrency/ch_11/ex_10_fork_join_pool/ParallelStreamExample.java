package me.practice.concurrency.ch_11.ex_10_fork_join_pool;

import java.util.stream.IntStream;

public class ParallelStreamExample{
    public static void main(String[] args) {

        int[] array = IntStream.range(0, 10).toArray();
        long sum = IntStream.of(array).parallel().sum();

        System.out.println("The sum is " + sum);
    }
}
