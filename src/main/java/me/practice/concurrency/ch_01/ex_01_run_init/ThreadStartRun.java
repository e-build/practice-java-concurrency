package me.practice.concurrency.ch_01.ex_01_run_init;

public class ThreadStartRun {
	public static void main(String[] args) {

		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " :스레드 실행중..");
			}
		});

		thread.start();
//        thread.run();
//        myRunnable.run();
	}

	static class MyRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
		}
	}
}
