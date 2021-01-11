package com.study.utils;

public interface ConfigUtils {
    String MSG_CODE = "0000";
    String MSG_NAME = "9999";

    String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss"; // 默认时间格式

    String FORMAT_TIME_MINUTE = "yyyy-MM-dd HH:mm"; // 默认时间格式

    String FORTER_DATE = "yyyyMMdd"; // 默认日期格式
    /**
     * TCP最大连接数  50
     */
    long MAX_CONNECTION = 2;
    /**
     * SESSION超时时间 100秒
     */
    long SESSION_TIMEOUT = 5 * 1000;
    //业务控制开启停止-开启
    String CMD_START = "start";
    //业务控制开启停止
    String CMD_STOP = "stop";


}
