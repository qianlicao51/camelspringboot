package com.study.utils;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/4 17:01
 * https://blog.csdn.net/wjsshhx/article/details/62422844
 */
public class DateUtils {
    private static final String FORMATE_DATE = "yyyy-MM-dd";
    private static final String FORMATE_SECONDS = "HH:mm:ss";
    private static final String FORMATE_FULL = FORMATE_DATE.concat(" ").concat(FORMATE_SECONDS);

    /**
     * 初始化方法
     * 1、参的构造方法会创建一个在当前系统所在时区的当前时间，精确到毫秒 2017-03-15T12:31:33.517+08:00
     * 2、DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour)根据传入的时间构造
     * 3、DateTime(long instant) 这个构造方法创建出来的实例，是通过一个long类型的时间戳，它表示这个时间戳距1970-01-01T00:00:00Z的毫秒数。使用默认的时区
     * 4、DateTime(Object instant) 这个构造方法可以通过一个Object对象构造一个实例。这个Object对象可以是这些类型
     */
    private static void useCaseOne() {

        DateTime dt = new DateTime();
        DateTime dt1 = new DateTime(2017, 12, 12, 12, 12, 8);
        DateTime dt2 = new DateTime(1487473917004L);
        DateTime dt3 = new DateTime(new Date());
        DateTime dt4 = new DateTime("2017-03-15T12:22:22");
        //格式化数据     输出： 2017年03月16日 13:53:42 星期四
        System.out.println(dt.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE));
        //取得一天的开始时间和结束时间
        System.out.println(dt.withTimeAtStartOfDay().toString(FORMATE_FULL));
        System.out.println(dt.millisOfDay().withMaximumValue().toString(FORMATE_FULL));

        final DateTime plusSeconds = dt.plusSeconds(2);
        Duration duration = new Duration(dt, plusSeconds);
        System.out.println("时间差是:" + duration.getStandardSeconds());
    }

    public static void main(String[] args) {
        useCaseOne();
        localDateCase();
    }

    /**
     * localDate获取一天开始和接受|来自尚硅谷电商 313 例子
     */
    private static void localDateCase() {
        System.out.println("------------");
        final LocalDate now = LocalDate.now();
        final LocalTime midnight = LocalTime.MIDNIGHT;
        final LocalTime min = LocalTime.MIN;
        final LocalTime max = LocalTime.MAX;

        System.out.println(LocalDateTime.of(now, min));
        System.out.println(LocalDateTime.of(now, midnight));
        System.out.println(LocalDateTime.of(now, max));
    }
}
