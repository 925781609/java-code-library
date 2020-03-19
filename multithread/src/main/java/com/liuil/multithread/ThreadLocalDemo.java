package com.liuil.multithread;

public class ThreadLocalDemo {

    static ThreadLocal<String> tl1 = new ThreadLocal<>();
    static ThreadLocal<String> tl2 = new ThreadLocal<>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + tl1.get());
        System.out.println(str + " :" + tl2.get());
        //清除本地内存中的本地变量
        tl1.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                tl1.set("localVar1");
                tl2.set("localVar1");
                //调用打印方法
                print("thread1");
                //打印本地变量
                System.out.println("after remove : " + tl1.get());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                tl1.set("localVar2");
                //调用打印方法
                print("thread2");
                //打印本地变量
                System.out.println("after remove : " + tl1.get());
            }
        });

        t1.start();
        t2.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("");

        }
    }

}
