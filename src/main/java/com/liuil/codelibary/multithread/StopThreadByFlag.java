package com.liuil.codelibary.multithread;

import java.util.concurrent.TimeUnit;

public class StopThreadByFlag extends Thread {

  private static volatile boolean isStop = false;

  @Override
  public void run() {
    long count = 0;
    while (!isStop) {
      count++;
    }
    System.out.println("Thread is going to stop with count = " + count);
  }

  public static void main(String[] args) {
    new StopThreadByFlag().start();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    isStop = true;
  }
}
