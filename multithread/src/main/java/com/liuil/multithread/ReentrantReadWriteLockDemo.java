package com.liuil.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    static Object get(String key) {
        System.out.println("开始读数据");
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    static Object put(String key, Object value) {
        System.out.println("开始写数据");
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> ReentrantReadWriteLockDemo.get("k1")).start();
        new Thread(() -> ReentrantReadWriteLockDemo.put("k1", "v1")).start();
        new Thread(() -> ReentrantReadWriteLockDemo.get("k2")).start();
    }


}
