package com.study.book.gym.char06;
/**
 * @author study
 * @version 1.0
 * @date 2021/5/19 10:00
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class CompletableFutureTimeOut {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;

        }).orTimeout(1, TimeUnit.SECONDS).exceptionally(e -> {
            System.err.println(e);
            return 0;
        }).thenAccept(System.out::println);
    }
}
