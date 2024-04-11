package me.practice.concurrency.ch_06.ex_01;

public class Mutex {
    private boolean lock = false;

    public synchronized void acquireLock() {
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lock = true;
    }

    public synchronized void releaseLock() {
        lock = false;
        notify();
    }
}
