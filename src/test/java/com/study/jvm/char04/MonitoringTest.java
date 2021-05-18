package com.study.jvm.char04;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/18 15:51
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class MonitoringTest {
    /**
     * 内存占位符对象，一个OOMObject大约占66KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void filHeap(int num) throws InterruptedException {
        final ArrayList<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //延时以便监视曲线变化
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        //等待 jsonsole连接
        TimeUnit.SECONDS.sleep(2);
        filHeap(1000);
    }
}
