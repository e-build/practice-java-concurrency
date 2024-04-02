package me.practice.concurrency.ch_01.ex_03_thread_lifecycle;

public class TerminatedStateThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 실행 중");
        });
        thread.start();
        thread.join(); // 스레드가 종료될 때까지 기다림
        System.out.println("스레드 상태: " + thread.getState()); // TERMINATED
    }

}
