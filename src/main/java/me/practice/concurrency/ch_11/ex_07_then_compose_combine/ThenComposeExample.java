package me.practice.concurrency.ch_11.ex_07_then_compose_combine;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyService myService = new MyService();

        CompletableFuture<Integer> asyncTask1 = myService.getData1(5); // 비동기 작업 1
        // 이전 비동기 작업의 결과를 사용 하여 다른 비동기 작업 실행
        CompletableFuture<Integer> asyncTask2 = asyncTask1.thenCompose(myService::getData2);

        int finalResult = asyncTask2.get();

        System.out.println("final result: " + finalResult);
    }

    static class MyService {

        public CompletableFuture<Integer> getData1(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500); // 2초 동안 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return input * 2;
            });
        }

        // 호출 되는 함수가 비동기로 실행 된 후 반환 값이 CompletableFuture 로 되는 경우
        public CompletableFuture<Integer> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500); // 2초 동안 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return input * 2;
            });
        }
    }
}
