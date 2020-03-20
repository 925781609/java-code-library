package com.liuil.multithread;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {

    private static int count = 0;

    private static void inc() {
        synchronized (SynchronizedDemo.class) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> SynchronizedDemo.inc()).start();
        }

        // 需要sleep一段时间等待子线程执行结束，或者子线程调用join方法阻塞主线程，
        TimeUnit.NANOSECONDS.sleep(10);
        System.out.println("Final count is " + count);
    }

}
