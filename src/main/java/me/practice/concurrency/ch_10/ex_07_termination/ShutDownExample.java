package me.practice.concurrency.ch_10.ex_07_termination;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 실행 중...");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + ": 작업 종료");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("인터럽트 걸림");
                }
                return 42;
            });
        }

        executorService.shutdown();

        try {
            if(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
                executorService.shutdownNow();
                System.out.println("스레드 풀 강제 종료 수행");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println("스레드 풀 종료 여부: " + executorService.isShutdown());
        System.out.println("스레드 풀 완전 종료 여부: " + executorService.isTerminated());
    }
}
