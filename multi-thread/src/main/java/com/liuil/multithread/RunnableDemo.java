package com.liuil.multithread;

public class RunnableDemo implements Runnable {

    private int count = 5;

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("线程" + Thread.currentThread().getName() + "卖第" + count + "张票");
        }
    }


    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread t1 = new Thread(runnableDemo, "t1");
        Thread t2 = new Thread(runnableDemo, "t2");
        Thread t3 = new Thread(runnableDemo, "t3");
        t1.start();
        t2.start();
        t3.start();
    }

}
