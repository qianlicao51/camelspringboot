package com.study.book.java8sz.cahr12;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @author MI
 * @ClassName LocalDateTest.java
 * @createTime 2021年09月07日 11:33:00
 */
public class LocalDateTest {
    @Test
    public void test() {
        LocalDate date = LocalDate.now();
        // date.get(Ter)
        int year = date.getYear();
        Period tenDays = Period.between(LocalDate.of(2017, 9, 11),
                LocalDate.of(2019, 9, 21));
        System.out.println(tenDays.getDays());

        // 指定转换格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse("2019-03-01", fmt);
        LocalDate endDate = LocalDate.parse("2020-04-02", fmt);

        System.out.println("总相差的天数:" + startDate.until(endDate, ChronoUnit.DAYS));
        System.out.println("总相差的月数:" + startDate.until(endDate, ChronoUnit.MONTHS));
        System.out.println("总相差的年数:" + startDate.until(endDate, ChronoUnit.YEARS));

    }

    @Test
    public void adjuster() {
        // import static java.time.temporal.TemporalAdjusters.*;
        LocalDate date1 = LocalDate.of(2021, 9, 7);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));//2021-09-12
        LocalDate date3 = date2.with(lastDayOfMonth());//2021-09-30
        System.out.println(date2);
        System.out.println(date3);
    }

    @Test
    public void printFormat() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
