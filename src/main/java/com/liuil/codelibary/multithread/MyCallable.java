package com.liuil.codelibary.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<Integer> {


  public static void main(String[] args) {
    callableFuture();
    callableFutureTask();
  }

  private static void callableFuture() {
    MyCallable myCallable = new MyCallable();
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Future<Integer> future = executorService.submit(myCallable);
    try {
      System.out.println(future.get(2, TimeUnit.MILLISECONDS));
    } catch (Exception e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }

  private static void callableFutureTask() {
    // 1st usage
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    MyCallable myCallable = new MyCallable();
    FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
    executorService.submit(futureTask);
    System.out.println(futureTask.isDone());

    // 2nd usage
    futureTask = new FutureTask<Integer>(myCallable);
    new Thread(futureTask, "t1").start();
    try {
      System.out.println(futureTask.cancel(true));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Integer call() throws Exception {
    return 1;
  }
}
