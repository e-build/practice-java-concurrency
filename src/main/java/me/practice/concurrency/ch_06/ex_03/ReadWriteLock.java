package me.practice.concurrency.ch_06.ex_03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock {
    private final Lock lock = new ReentrantLock();
    private final Condition readCondition = lock.newCondition();
    private final Condition writeCondition = lock.newCondition();
    private int readCount = 0;
    private boolean isWriting = false;

    public void lockRead() {
        lock.lock();
        try {
            while (isWriting) {
                readCondition.await();
            }
            readCount++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void unlockRead() {
        lock.lock();
        try {
            readCount--;
            if (readCount == 0) {
                writeCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void lockWrite()  {
        lock.lock();
        try {
            while (isWriting || readCount > 0) {
                writeCondition.await();
            }
            isWriting = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void unlockWrite() {
        lock.lock();
        try {
            isWriting = false;
            readCondition.signalAll();
            writeCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static class Shared {
        private int resource = 0;
        private final ReadWriteLock lock;

        public Shared(ReadWriteLock lock) {
            this.resource = resource;
            this.lock = lock;
        }

        public void read(){
            lock.lockRead();
            try {
                System.out.println(Thread.currentThread().getName() + " is reading.");
                Thread.sleep(100); // 읽기 작업 시뮬레이션
                System.out.println(Thread.currentThread().getName() + " read value: " + resource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead();
            }
        }

        // 쓰기 작업
        public void write(int newValue) {
            lock.lockWrite();
            try {
                System.out.println(Thread.currentThread().getName() + " is writing.");
                Thread.sleep(100); // 쓰기 작업 시뮬레이션
                resource = newValue;
                System.out.println(Thread.currentThread().getName() + " wrote value: " + resource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockWrite();
            }
        }
    }

    // 사용 예
    public static void main(String[] args) {
        final ReadWriteLock lock = new ReadWriteLock();
        Shared sharedResource = new Shared(lock);

        // 읽기 스레드 생성 및 실행
        for (int i = 0; i < 4; i++) {
            new Thread(sharedResource::read, "Reader " + (i + 1)).start();
        }

        // 쓰기 스레드 생성 및 실행
        for (int i = 0; i < 4; i++) {
            new Thread(() -> sharedResource.write(10), "Writer" + (i+1)).start();
        }

    }
}
