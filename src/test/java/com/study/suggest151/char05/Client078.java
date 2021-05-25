package com.study.suggest151.char05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 10:01
 */
public class Client078 {
    public static void main1(String[] args) {
        Map<String, String> map = new HashMap<>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(() -> {
            final StringBuffer sb = new StringBuffer();
            final long heapMaxSize = rt.maxMemory() >> 20;
            sb.append("最大可用内存：" + heapMaxSize + " M\n");
            final long total = rt.totalMemory() >> 20;
            sb.append("堆内存大小：" + total + " M\n");
            final long free = rt.freeMemory() >> 20;
            sb.append("空闲内存：" + free + " M\n");
            System.out.println(sb);
        }));
        //放入近40万键值对
        for (int i = 0; i < 393217; i++) {
            map.put("key" + i, "value" + i);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(() -> {
            final StringBuffer sb = new StringBuffer();
            final long heapMaxSize = rt.maxMemory() >> 20;
            sb.append("最大可用内存：" + heapMaxSize + " M\n");
            final long total = rt.totalMemory() >> 20;
            sb.append("堆内存大小：" + total + " M\n");
            final long free = rt.freeMemory() >> 20;
            sb.append("空闲内存：" + free + " M\n");
            System.out.println(sb);
        }));
        //放入近40万键值对
        for (int i = 0; i < 400000; i++) {
            list.add("key" + i);
            list.add("value" + i);
        }
    }
}
