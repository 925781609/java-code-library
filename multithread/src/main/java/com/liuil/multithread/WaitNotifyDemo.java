package com.liuil.multithread;

public class WaitNotifyDemo extends Thread {


    public static void main(String[] args) {
        Object lock = new Object();
        new WaitThread(lock).start();
        new NotifyThread(lock).start();
        ;
    }


    static class WaitThread extends Thread {

        private Object lock;

        public WaitThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("Start wait Thread");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finish wait Thread");

            }
        }

    }


    static class NotifyThread extends Thread {

        private Object lock;

        public NotifyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("Start notify thread");
                lock.notify();
                System.out.println("Finish notify thread");

            }
        }
    }

}
