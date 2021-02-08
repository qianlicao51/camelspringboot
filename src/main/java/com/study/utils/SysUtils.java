package com.study.utils;

import org.joda.time.DateTime;

import java.io.File;
import java.net.URL;

/**
 * 工具类
 */
public class SysUtils {

    /**
     * @return YYYY-MM-dd HH:mm:ss
     */
    public static String getDate() {
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return YYYY-MM-dd-HH-mm-ss
     */
    public static String getDateYmd() {
        return new DateTime().toString("yyyy-MM-dd-HH-mm-ss");
    }

    /**
     * yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */
    public static String getDateYmD() {
        return new DateTime().toString("yyyy-MM-dd");
    }

    /**
     * 获取给定 路径的资源路劲
     *
     * @param path
     * @return
     */
    public static String getResourcePath(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("");
        File file = new File(resource.getPath(), path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    public static void main(String[] args) {
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        final String resourcePath = getResourcePath("routes/tcp.xml");
        System.out.println(resourcePath);
    }
}
