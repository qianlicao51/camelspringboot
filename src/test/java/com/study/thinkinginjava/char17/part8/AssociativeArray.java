package com.study.thinkinginjava.char17.part8;

import java.util.Arrays;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/21 20:25
 */
public class AssociativeArray<K, V> {
    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length) {
        this.pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        pairs[index++] = new Object[]{key, value};
    }

    public V get(K key) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V) pairs[i][1];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AssociativeArray{" +
                "pairs=" + Arrays.toString(pairs) +
                ", index=" + index +
                '}';
    }
}
