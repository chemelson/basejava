package ru.javawebinar.basejava;

public class DeadLock {

    private static final Object RESOURCE_1 = new Object();
    private static final Object RESOURCE_2 = new Object();

    public static void main(String[] args) {
        runDeadlock(RESOURCE_1, RESOURCE_2, "1");
        runDeadlock(RESOURCE_2, RESOURCE_1, "2");

    }

    private static void runDeadlock(Object resource_1, Object resource_2, String threadName) {
        new Thread(() -> {
            try {
                synchronized (resource_1) {
                    System.out.println("Thread " + threadName + " first lock:" + resource_1);
                    Thread.sleep(5000);
                    synchronized (resource_2) {
                        System.out.println("Thread " + threadName + " second lock:" + resource_2);
                        Thread.sleep(5000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
