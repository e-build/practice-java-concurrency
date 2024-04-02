package me.practice.concurrency.ch_01.ex_02_thread_termination;

public class MultiThreadAppTermination {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }

        System.out.println("메인 스레드 종료");

    }
    static class MyRunnable implements Runnable{

        private final int threadId;

        public MyRunnable(int threadId) {

            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 시작");
            status(threadId);
            System.out.println(Thread.currentThread().getName() + ": 스레드 종료");
        }

        private void status(int threadId) {
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId);
        }
    }
}
