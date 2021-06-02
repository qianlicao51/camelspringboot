package com.study.gym.bookb.char04;

import java.lang.ref.SoftReference;

/**
 * @author MI
 * @version 1.0
 * @date 2021/6/2 22:19
 * -Xmx10m
 */
public class SoftRef {
    public static void main(String[] args) throws InterruptedException {
        User u = new User(1, "geym");
        SoftReference<User> userSoftRef = new SoftReference<User>(u);
        u = null;

        System.out.println(userSoftRef.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        byte[] b = new byte[1024 * 500 * 2];
        System.gc();
        Thread.sleep(1000);
        System.out.println(userSoftRef.get());
    }

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[id=" + id + ",name=" + name + "]";
        }
    }
}
