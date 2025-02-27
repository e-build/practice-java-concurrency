package me.practice.concurrency.ch_04.ex_01_exception_handling;

public class CatchException {

    public static void main(String[] args) {

        try {
            new Thread(() -> {
                throw new RuntimeException("스레드 1 예외!");
            }).start();
        } catch (Exception e) {
            sendNotificationToAdmin(e);
        }

    }

    private static void sendNotificationToAdmin(Throwable e) {
        System.out.println("관리자에게 알림: " + e.getMessage());
    }
}
