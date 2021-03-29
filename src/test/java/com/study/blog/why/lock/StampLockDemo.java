package com.study.blog.why.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/29 21:47
 */
//why技术博客(微信公众号)
//     https://mp.weixin.qq.com/s?__biz=MzkxNTE3NjQ3MA==&mid=2247485739&idx=1&sn=801792f4987c4c3bd3d976d030476113&scene=21#wechat_redirect
public class StampLockDemo {
    void simpleDemo() {
        final StampedLock sLock = new StampedLock();

        //乐观锁
        long stamp = sLock.tryOptimisticRead();
        //如果版本变了，就是用悲观锁
        if (!sLock.validate(stamp)) {
            stamp = sLock.readLock();
            try {
                //do sth...
            } finally {
                sLock.unlockRead(stamp);
            }
        }
    }
}
