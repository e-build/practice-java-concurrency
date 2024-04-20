package me.practice.concurrency.ch_11.ex_03_completable;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionExample {

    public static void main(String[] args) {

        CompletableFuture<Object> handle = CompletableFuture
            .supplyAsync(() -> 2)
            .thenApplyAsync(result -> result + 3)
            .thenApplyAsync(result -> {
                throw new RuntimeException("error");
            })
            .handle((result, exception) -> {
                if (exception != null) {
                    System.out.println("예외 발생: " + exception.getMessage());
                    return 0; // 예외가 발생하면 0을 반환
                } else {
                    System.out.println("Async tasks 완료 : " + result);
                    return result;
                }
            });

        System.out.println("최종 결과: " + handle.join());
    }
}