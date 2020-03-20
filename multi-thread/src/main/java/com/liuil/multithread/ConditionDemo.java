package com.liuil.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();

        new AwaitThread("thread1", lock, condition).start();
        new AwaitThread("thread2", lock, condition).start();
        new AwaitThread("thread3", lock, condition).start();
        new SignalThread("thread4", lock, condition).start();
    }


    static class AwaitThread extends Thread {

        private Lock lock;
        private Condition condition;

        public AwaitThread(String name, Lock lock, Condition condition) {
            super(name);
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " Start await");
                condition.await(5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " End await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class SignalThread extends Thread {

        private Lock lock;
        private Condition condition;

        public SignalThread(String name, Lock lock, Condition condition) {
            super(name);
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " Start signal");
                condition.signal();
                // signalAll 将会唤醒所有time_waiting状态的线程
//        condition.signalAll();
                System.out.println(Thread.currentThread().getName() + " End signal");
            } finally {
                lock.unlock();
            }
        }
    }

}
