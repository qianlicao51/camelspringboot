package com.study.onjava8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/24 9:08
 */
@Slf4j(topic = "StreamTest")
public class StreamTest {
    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();
        extracted();
        watch.stop();
        log.info("Time Elapsed: " + watch.getTime() + "ms"); // 单位为毫秒

        watch.reset();
        watch.start();
        long start = System.nanoTime();
        testStream();
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        watch.stop();
        log.info("Time Elapsed: " + watch.getTime() + "ms"); // 单位为毫秒

    }

    private static void extracted() {
        //在需要将数值范围装箱成为一个一般流时， boxed 尤其有用 (intStream.boxed())
        final Stream<CompletableFuture<Void>> completableFutureStream = IntStream.rangeClosed(1, 10).mapToObj(i -> {
            return CompletableFuture.runAsync(() -> {
                final String apply = apply(2);
                log.debug("生成的随机字符串:" + apply);
            });
        });
        completableFutureStream.parallel().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static void extracted2() {
        ArrayList<CompletableFuture<Void>> asList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final CompletableFuture<Void> async = CompletableFuture.runAsync(() -> {
                final String apply = apply(2);
                log.debug("生成的随机字符串:" + apply);
            });
            asList.add(async);
        }
        asList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static void testStream() {
        final List<String> collect = IntStream.rangeClosed(1, 10).mapToObj(i -> {
            final String apply = apply(2);
            log.debug("生成的随机字符串:" + apply);
            return apply;
        }).collect(Collectors.toList());
    }

    private static String apply(int sleepTime) {
        delay(sleepTime);
        return RandomStringUtils.randomAlphanumeric(5);
    }

    private static void delay(int sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
