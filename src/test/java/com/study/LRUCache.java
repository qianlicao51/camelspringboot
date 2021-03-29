package com.study;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/26 15:56
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    public static void main(String[] args) {
        final LRUCache<String, String> cache = new LRUCache<>(2);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);

    }

    // LUR算法
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
