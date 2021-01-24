package com.study.thread.n3.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/23 19:21
 */
@Slf4j(topic = "c.ExerciseSell")
public class ExerciseSell {

    private final static Random random = new Random(System.currentTimeMillis());
    private final static ExecutorService executorService = Executors.newFixedThreadPool(8);

    /**
     * cmd for循环 |for /l %x in (1, 1, 20) do java -jar utils-0.0.1-SNAPSHOT.jar
     */
    public static void main(String[] args) throws InterruptedException {
        sell();
        // generalSell();
    }

    /**
     * 线程不安全
     */
    private static void generalSell() throws InterruptedException {
        final TicketWindow2 window = new TicketWindow2(1000);
        List<Thread> threadList = new ArrayList<>();
        List<Integer> amountLists = new Vector<>();
        for (int i = 0; i < 4000; i++) {
            final Thread thread = new Thread(() -> {
                final int sell = window.sell(random.nextInt(5));
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                amountLists.add(sell);
            });
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        log.debug("余票{}", window.getCount());
        log.debug("卖出{}", amountLists.stream().mapToInt(i -> i).sum());
    }

    private static void sell() {
        // 模拟多人买票
        final TicketWindow2 window = new TicketWindow2(1000);
        final Stream<CompletableFuture<Integer>> completableFutureStream = IntStream.rangeClosed(1, 2000).mapToObj(i -> //
                CompletableFuture.supplyAsync(//
                        () -> window.sell(random.nextInt(5) + 1), //
                        executorService));
        //这样永远不会有线程安全问题，需要并发(parallel)
        final List<Integer> collect = completableFutureStream.parallel().map(CompletableFuture::join).collect(toList());
        log.debug("卖出 {}", collect.stream().mapToInt(i -> i).sum());
        // 统计卖出票数 和剩余 票数
        log.debug("余票 {}", window.getCount());
        executorService.shutdown();
    }
}

// 售票
@Slf4j(topic = "windown")
class TicketWindow2 {
    private int count;

    public TicketWindow2(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            // log.debug("卖出 {}，还剩余{}", amount, this.count);
            return amount;
        }
        return 0;
    }
}
