package me.practice.concurrency.ch_03.ex_01_sleep;

/**
 * class asa;ksdjf;klasjdflksdf
 */
public class LoopSleep {
    public static void main(String[] args) {

        for (int i = 0; i < 7; i++) {
            try {
                System.out.println("반복: " + (i + 1));
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
