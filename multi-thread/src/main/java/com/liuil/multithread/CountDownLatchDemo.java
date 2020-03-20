package com.liuil.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                System.out.println("After count minus to zero");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> countDownLatch.countDown()).start();
        new Thread(() -> countDownLatch.countDown()).start();

        countDownLatch.await(5, TimeUnit.SECONDS);
        System.out.println("After await finish");


    }

}
