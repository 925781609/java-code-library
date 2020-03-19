package com.liuil.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    private static void inc() {
        lock.lock();
        try {
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                ReentrantLockDemo.inc();
            }).start();
        }
        // Sleep a while to let sub thread finish
        TimeUnit.MILLISECONDS.sleep(1);

        System.out.println("Final count is " + count);

    }

}
