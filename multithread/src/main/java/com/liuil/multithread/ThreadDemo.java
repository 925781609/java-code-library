package com.liuil.multithread;

public class ThreadDemo extends Thread {

    private String name;
    private int count = 5;

    public ThreadDemo(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("线程" + Thread.currentThread().getName() + "卖第" + count + "张票");
        }
    }


    public static void main(String[] args) {
        Thread t1 = new ThreadDemo("t1");
        Thread t2 = new ThreadDemo("t2");
        Thread t3 = new ThreadDemo("t3");


        t1.start();
        t2.start();
        t3.start();

        // lambda +  Function interface
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " sell tickets");
        }, "t1").start();

    }

}
