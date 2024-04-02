package me.practice.concurrency.ch_01.ex_03_thread_lifecycle;

public class RunnableStateThread {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                for (int i = 0; i < 1000000000; i++) {
                    if(i%1000000000 == 0){
                        System.out.println("스레드 상태: " + Thread.currentThread().getState()); // RUNNABLE
                    }
                }
            }
        });
        thread.start();
    }
}
