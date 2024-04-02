package me.practice.concurrency.ch_01.ex_03_thread_lifecycle;

public class TimedWaitingStateThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread.getState()); // TIMED_WAITING
    }

}
