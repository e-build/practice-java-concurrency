package me.practice.concurrency.ch_06.ex_01;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Shared shared = new Shared();
        Mutex mutex = new Mutex();

        AddWorker addWorker1 = new AddWorker(shared, mutex);
        AddWorker addWorker2 = new AddWorker(shared, mutex);
        AddWorker addWorker3 = new AddWorker(shared, mutex);

        addWorker1.start();
        addWorker2.start();
        addWorker3.start();

        addWorker1.join();
        addWorker2.join();
        addWorker3.join();

        System.out.println("shared : " + shared.getResource());
    }
}
