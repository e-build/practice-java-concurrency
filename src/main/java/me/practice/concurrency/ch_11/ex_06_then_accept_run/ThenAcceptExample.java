package me.practice.concurrency.ch_11.ex_06_then_accept_run;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {

    public static void main(String[] args) {

        MyService myService = new MyService();
        CompletableFuture.supplyAsync(() -> {
                System.out.println("thread1:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 40;
            })
            .thenAccept(result -> {
                // 첫 번째 작업의 결과를 받아 추가 작업을 수행합니다.
                // 현재 스레드 이름과 결과값을 출력하고, MyService의 getData1 메서드를 호출해 결과를 출력합니다.
                System.out.println("thread2:" + Thread.currentThread().getName());
                System.out.println("결과: " + result);
                List<Integer> r = myService.getData1();
                r.forEach(System.out::println);

            }).thenAcceptAsync(result -> {
                // thenAcceptAsync는 비동기적으로 작업을 수행합니다.
                // 이 코드 블록은 실행되지 않습니다. 이유는 thenAcceptAsync는 인자로 Consumer를 받지만,
                // 이전 단계에서 이미 결과를 소비했기 때문입니다. 따라서, 실제로 이 부분은 에러를 발생시킵니다.
                System.out.println("thread3:" + Thread.currentThread().getName());
                System.out.println("결과: " + result); // 이 부분은 실제로 동작하지 않습니다.
                List<Integer> r = myService.getData2();
                r.forEach(System.out::println);

            }).join(); // 모든 작업이 완료될 때까지 메인 스레드를 대기시킵니다.
    }

    static class MyService {

        public List<Integer> getData1() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Arrays.asList(1, 2, 3);
        }

        public List<Integer> getData2() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Arrays.asList(4, 5, 6);
        }
    }
}


