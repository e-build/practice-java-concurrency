package me.practice.concurrency.ch_11.ex_01_sync_block;

import java.util.concurrent.*;

public class AsyncBlocking {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Hello Java";
        };
        // 비동기 & 논블록킹
        Future<String> future = executor.submit(task);

        // 메인 작업 수행
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum = sum + i;
        }
        System.err.println("메인 작업 결과: " + sum);

        // 블록킹, 이 구문은 맨 마지막에 위치 해도 무방함
        try {
            String result = future.get();
            System.err.println("블록킹 작업 결과: " + result);
        } catch (InterruptedException | ExecutionException e) {
        }
        executor.shutdown();
        System.err.println("프로세스 종료");
    }
}
