package me.practice.concurrency.ch_06.ex_01;

public class Shared {

    private int resource;

    public void increment() {
        resource++;
    }

    public int getResource() {
        return resource;
    }
}
