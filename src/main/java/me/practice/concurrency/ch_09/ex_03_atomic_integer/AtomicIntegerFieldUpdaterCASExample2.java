package me.practice.concurrency.ch_09.ex_03_atomic_integer;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterCASExample2 {
    private static AtomicReferenceFieldUpdater<AtomicIntegerFieldUpdaterCASExample2, String> messageUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterCASExample2.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, "", "Hello World!")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            messageUpdater.set(this, "");
        } else {
            System.out.println("This thread will not enter the loop");
        }
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterCASExample2 example1 = new AtomicIntegerFieldUpdaterCASExample2();
        new Thread(example1::doSomething).start();
        new Thread(example1::doSomething).start();
    }
}