package me.practice.concurrency.ch_03.ex_04_interrupt;

public class Interrupted1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("스레드가 작동 중입니다.");
            }
            System.out.println("스레드가 인터럽트 되었습니다.");
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}