package me.practice.concurrency.ch_06.ex_01;

public class AddWorker extends Thread {

    private final Shared shared;
    private final Mutex mutex;

    public AddWorker(Shared shared, Mutex mutex) {
        this.shared = shared;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        mutex.acquireLock();
        try {
            for (int i = 0; i < 10_000_000; i++) {
                shared.increment();
            }
        } finally {
            mutex.releaseLock();
        }
    }
}
