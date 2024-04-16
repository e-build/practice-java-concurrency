package me.practice.concurrency.ch_10.ex_02_interface_executor;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {
    public static void main(String[] args) {
        Executor asyncExecutor = new AsyncExecutor();

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            asyncExecutor.execute(() -> {
                System.out.println("비동기 작업 " + finalI + " 수행 중...");
                // 작업 수행
                System.out.println("비동기 작업 " + finalI + " 완료...");
            });
        }
    }

    static class AsyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            Thread thread = new Thread(command);
            thread.start();
        }
    }
}
