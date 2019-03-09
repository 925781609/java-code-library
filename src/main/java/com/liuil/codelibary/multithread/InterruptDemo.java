package com.liuil.codelibary.multithread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

  private static int i;

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        i++;
      }
      System.out.println("Num:" + i);
      // 输出位true，中断标志位没有被清除
      System.out.println(Thread.currentThread().isInterrupted());
      Thread.interrupted();
      // 输出位false，中断标志位被Thread.interrupted清除
      System.out.println(Thread.currentThread().isInterrupted());
    }, "interruptDemo");
    thread.start();
    TimeUnit.SECONDS.sleep(1);
    thread.interrupt();
  }
}
