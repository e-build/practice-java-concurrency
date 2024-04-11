package me.practice.concurrency.ch_06.ex_03;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    private static final Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    private static final LinkedList<Integer> buffer = new LinkedList<>();
    private static final int CAPACITY = 10;

    // 생산자 스레드
    static class Producer implements Runnable {
        public void run() {
            try {
                int value = 0;
                while (true) {
                    lock.lock();
                    try {
                        // 버퍼가 꽉 찼다면, notFull 조건에서 대기
                        while (buffer.size() == CAPACITY) {
                            notFull.await();
                        }
                        buffer.add(value++);
                        // 데이터를 추가했으니, notEmpty 조건을 통해 대기 중인 소비자를 깨움
                        notEmpty.signal();
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(1000); // 데이터 생산 시간 가정
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 소비자 스레드
    static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    lock.lock();
                    try {
                        // 버퍼가 비어있다면, notEmpty 조건에서 대기
                        while (buffer.isEmpty()) {
                            notEmpty.await();
                        }
                        System.out.println("소비된 값: " + buffer.poll());
                        // 데이터를 소비했으니, notFull 조건을 통해 대기 중인 생산자를 깨움
                        notFull.signal();
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(1000); // 데이터 소비 시간 가정
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
