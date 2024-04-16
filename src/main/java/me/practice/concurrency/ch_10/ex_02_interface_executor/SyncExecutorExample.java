package me.practice.concurrency.ch_10.ex_02_interface_executor;

import java.util.concurrent.Executor;

public class SyncExecutorExample {
    public static void main(String[] args) {
        Executor syncExecutor = new SyncExecutor();

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            syncExecutor.execute(() -> {
                System.out.println("동기 작업 " + finalI + " 수행 중...");
                // 작업 수행
                System.out.println("동기 작업 " + finalI + " 완료...");
            });
        }
    }

    static class SyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
