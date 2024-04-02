package me.practice.concurrency.ch_01.ex_01_run_init;

public class AnonymousRunnableClass {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중..");
            }
        });

        thread.start();

    }
}
