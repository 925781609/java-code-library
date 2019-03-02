package com.liuil.codelibary.multithread;

public class MyThread extends Thread {

  String name;

  public MyThread(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println("线程" + Thread.currentThread().getName() + "卖第" + i + "张票");
    }
  }


  public static void main(String[] args) {
    Thread t1 = new MyThread("t1");
    Thread t2 = new MyThread("t2");
    Thread t3 = new MyThread("t3");

    t1.start();
    t2.start();
    t3.start();
  }

}
