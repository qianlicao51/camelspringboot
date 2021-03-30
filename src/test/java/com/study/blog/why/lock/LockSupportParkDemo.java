package com.study.blog.why.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/30 9:18
 */
//LockSupport.park()会释放锁资源吗|https://www.cnblogs.com/tong-yuan/p/11768904.html
@Slf4j
public class LockSupportParkDemo {
    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {
            log.info(Thread.currentThread().getName() + " start");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
            log.info(Thread.currentThread().getName() + " end");
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
        log.info("finished");
    }
}
