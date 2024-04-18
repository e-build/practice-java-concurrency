package me.practice.concurrency.ch_11.ex_01_sync_block;

public class SyncBlocking {

    public static void main(String[] args) {

        // 동기 & 블록킹
        blocking();
        System.out.println("메인 스레드 종료");
    }

    public static void blocking() {

        try {
            Thread.sleep(3000); // 3초 대기
            System.out.println("작업 종료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
