package com.study.jvm.char02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/16 23:27
 * VM Args :-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/tmp/dump_OOME.hprof
 */
public class HeapOOM {
    static class OOMObject {
    }

    @Test
    void heapOOm(){
        final ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
