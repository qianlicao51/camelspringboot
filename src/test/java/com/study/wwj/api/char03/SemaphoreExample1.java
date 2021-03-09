package com.study.wwj.api.char03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/9 15:29
 */
public class SemaphoreExample1 {
    public static void main(String[] args) {
        //定义许可证数量，最多允许10个用户同时在线
        final int MAX_PERMIT_LOGIN_ACCOUNT = 10;
        final LoginService login = new LoginService(MAX_PERMIT_LOGIN_ACCOUNT);
        //启动20个线程
        IntStream.range(0, 20).forEach(i -> {
            new Thread(() -> {
                final boolean logins = login.login();
                if (!logins) {
                    System.out.println(Thread.currentThread() + " is refused dule to exceed max online account.");
                    return;
                }
                try {
                    // 模拟登陆成功后的系统操作
                    simulateWork();
                } catch (Exception e) {
                } finally {
                    login.logout();
                }
            }, "User-" + i).start();
        });
    }

    //随机睡眠
    private static void simulateWork() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
        }
    }

    private static class LoginService {
        private final Semaphore semaphore;

        public LoginService(int maxPermitLonginAccount) {
            this.semaphore = new Semaphore(maxPermitLonginAccount, true);
        }

        public boolean login() {
            //获取许可证，如果获取失败返回false，tryAcquire 不是一个阻塞方法
            final boolean login = semaphore.tryAcquire();
            if (login) {
                System.out.println(Thread.currentThread() + " login success.");
            }
            return login;
        }

        //释放许可证
        public void logout() {
            semaphore.release();
            System.out.println(Thread.currentThread() + " login out.");
        }
    }
}
