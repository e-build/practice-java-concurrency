package me.practice.concurrency.ch_05.ex_01_multi_thread;

public class SingleThreadCalculate {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("합계: " + sum);
        System.out.println("싱글 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
