package me.practice.concurrency.ch_05.ex_04_thread_safe;

public class Main implements Runnable {
    private ImmutablePerson person;

    public Main(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 이름: " + person.getName() + ", 나이: " + person.getAge());
    }

    public static void main(String[] args) {

        ImmutablePerson person = new ImmutablePerson("홍길동", 25);

        for (int i = 0; i < 10; i++) {
            new Thread(new Main(person)).start();
        }
    }
}

final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
