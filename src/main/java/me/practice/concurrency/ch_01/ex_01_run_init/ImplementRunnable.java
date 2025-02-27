package me.practice.concurrency.ch_01.ex_01_run_init;

public class ImplementRunnable {
	public static void main(String[] args) {

		MyRunnable task = new MyRunnable();
		Thread thread = new Thread(task);
		thread.start();
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중");
	}
}
