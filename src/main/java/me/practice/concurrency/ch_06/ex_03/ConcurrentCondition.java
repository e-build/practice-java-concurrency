package me.practice.concurrency.ch_06.ex_03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1: Waiting for the condition.");
                condition.await(); // 조건을 기다립니다.
                System.out.println("Thread 1: Condition satisfied.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 2: Satisfying the condition.");
                condition.signal(); // 조건이 충족되었음을 알립니다.
                System.out.println("Thread 2: Sent notification.");
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}
