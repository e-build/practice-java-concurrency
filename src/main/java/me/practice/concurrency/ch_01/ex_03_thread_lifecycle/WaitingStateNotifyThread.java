package me.practice.concurrency.ch_01.ex_03_thread_lifecycle;

public class WaitingStateNotifyThread {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("스레드 깨어남: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("스레드 깨어남: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        System.out.println("스레드 상태: " + thread1.getState()); // WAITING
        System.out.println("스레드 상태: " + thread2.getState()); // WAITING

        // WAITING 상태의 스레드를 깨우기 위한 동기화 블록 - 모니터 락에 대해 대기 중인 모든 스레드를 깨움(-> Runnable)
        synchronized (lock) {
//             lock.notify(); // 하나의 스레드만 깨우기 -  JVM의 스케줄링 정책에 의해 임의로 선택
            lock.notifyAll(); // 모든 대기중인 스레드를 깨우기
        }
        /*
        notify() 또는 notifyAll() 메서드를 호출하는 스레드는 해당 객체의 모니터 락을 소유하고 있어야 함
        이는 해당 메서드들이 synchronized 블록 또는 메서드 내에서만 호출될 수 있음을 의미.
        만약 synchronized 컨텍스트 밖에서 이 메서드들을 호출하려고 하면, IllegalMonitorStateException이 발생
         */
    }
}
