package me.practice.concurrency.ch_04.ex_05_thread_local.logger;

public class ThreadLocalLoggerExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new LogWorker());
        Thread thread2 = new Thread(new LogWorker());
        Thread thread3 = new Thread(new LogWorker());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
