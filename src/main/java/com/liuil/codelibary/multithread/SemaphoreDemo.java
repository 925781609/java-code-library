package com.liuil.codelibary.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(3);
    for (int i = 0; i < 10; i++) {
      new MyThread(i, semaphore).start();
    }
  }


  static class MyThread extends Thread {

    private int num;
    private Semaphore semaphore;

    public MyThread(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;

    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println("第" + num + "线程开始");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("第" + num + "线程结束");
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


}
