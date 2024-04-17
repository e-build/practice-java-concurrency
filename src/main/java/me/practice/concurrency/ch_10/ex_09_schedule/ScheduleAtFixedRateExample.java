package me.practice.concurrency.ch_10.ex_09_schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        // 처음 1 초가 지난 후 실행 되고, 각 스레드가 3초 간격으로 번갈아가면서 태스크를 계속 실행한다.
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(
            () -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // 예외 처리
                }
            },
            1,
            3,
            TimeUnit.SECONDS
        );

        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(true); // 작업을 취소하면 인터럽트 되어 스케줄링이 중지된다
        scheduler.shutdown();
    }
}
