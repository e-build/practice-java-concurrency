package me.practice.concurrency.ch_10.ex_11_thread_pool_management;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecutionHandlerExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            2,
            0L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2),
            new MyRejectedExecutionHandler()
        );
        // 작업을 제출
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        // 스레드 풀 종료
        executor.shutdown();
    }
}

class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("태스크가 거부되었습니다.");
        if (!executor.isShutdown()) {
            executor.getQueue().poll();
            executor.getQueue().offer(r);
        }
    }
}
