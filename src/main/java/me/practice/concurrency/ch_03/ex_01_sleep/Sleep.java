package me.practice.concurrency.ch_03.ex_01_sleep;

public class Sleep {
    public static void main(String[] args) {
        try {
            System.out.println("2초 후에 메시지가 출력됩니다");
            Thread.sleep(2000);
            System.out.println("Hello World");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
