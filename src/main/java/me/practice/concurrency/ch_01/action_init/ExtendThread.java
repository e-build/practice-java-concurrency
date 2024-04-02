package me.practice.concurrency.ch_01.action_init;

public class ExtendThread {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();
    }
 }

 class MyThread extends Thread{
     @Override
     public void run() {
         System.out.println(Thread.currentThread().getName() + " :스레드 실행 중.. ");
     }
 }