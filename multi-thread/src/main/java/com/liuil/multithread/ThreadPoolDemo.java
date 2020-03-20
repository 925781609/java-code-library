package com.liuil.multithread;

import java.util.concurrent.*;

/**
 * 线程池示例代码
 */
public class ThreadPoolDemo {
    static class WorkThread implements Runnable {
        private String command;

        public WorkThread(String command) {
            this.command = command;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + Thread.currentThread().getId() + " start. Command=" + command);
            processCommand();
            System.out.println("Thread-" + Thread.currentThread().getId() + " end.");
        }

        private void processCommand() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(5, 10, 50, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5));
        for (int i = 0; i < 15; i++) {

            Runnable work = new WorkThread("" + i);
            executor.execute(work);
//            executor.submit(work);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finish all threads.");
    }

}
