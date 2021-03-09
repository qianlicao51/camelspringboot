package com.study.offer.base.char03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 16:56
 */
public class SafeCache {
    private final Map<String, Object> cache = new HashMap<>();
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    // step 1 定义读锁
    private final Lock readLock = rw.readLock();
    // step 2 定义写锁
    private final Lock writeLock = rw.writeLock();

    //step 3 在读数据时加读锁
    public Object get(String key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    // step 4 在写数据时加写锁
    public Object put(String key, Object value) {
        writeLock.lock();
        try {
            return cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
