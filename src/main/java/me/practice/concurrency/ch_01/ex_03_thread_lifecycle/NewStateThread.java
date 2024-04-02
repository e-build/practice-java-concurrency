package me.practice.concurrency.ch_01.ex_03_thread_lifecycle;

public class NewStateThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 실행 중");
        });
        System.out.println("스레드 상태: " + thread.getState()); // NEW
    }

}
