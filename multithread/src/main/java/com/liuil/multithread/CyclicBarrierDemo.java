package com.liuil.multithread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

    private static List<String> list = new LinkedList<>();


    public static void main(String[] args) throws Exception {
        Runnable test = () -> {
            System.out.println(list);
            list.removeAll(list);
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, test);
        for (int i = 0; i < 6; i++) {
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                list.add(name);
                System.out.println(name + " is waiting");
                try {
                    cyclicBarrier.await(5, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(name + " is running");

            }, String.valueOf(i)).start();

        }

    }
}
