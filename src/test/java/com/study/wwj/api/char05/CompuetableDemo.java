package com.study.wwj.api.char05;

import java.util.concurrent.*;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/16 12:55
 */
public class CompuetableDemo {
    public static void main1() {
        //double 类型的 CompletableFuture
        final CompletableFuture<Double> completableFuture = new CompletableFuture();
        //提交异步任务
        Executors.newCachedThreadPool().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("finished");
                //执行结束
                completableFuture.complete(1234.5D);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //非阻塞获取异步任务的计算结果，和宁县，此刻异步任务未执行结束，那么可以采用默认值的方式
        //(该方法也可以被认为是放弃异步任务的执行结果，但不会取消异步任务的执行 )
        try {
            assert completableFuture.get() == 1234.5D;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以同步的方式继续执行上一个异步任务的结果
     * supplyAsync 的 计算结果为 Java
     * thenApply 继续处理 “Java” ，返回字符串的长度
     * supplyAsync和thenApply 的任务执行是同一个线程
     */
    static void thenApply() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            return "Java";
        }, executor).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + " thenApply");
            System.out.println(Thread.currentThread().getName() + " " + e);
            return e.length();
        });
        System.out.println(future.get());
    }

    /**
     * thenApplyAsync 的计算结果为“Java”
     * thenApplyAsync 继续处理“Java”，返回字符串的长度
     */
    static void thenApplyAsync() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            return "Java";
        }, executor).thenApplyAsync(e -> {
            System.out.println(Thread.currentThread().getName() + " thenApplyAsync");
            System.out.println(Thread.currentThread().getName() + " " + e);
            return e.length();
        });
        System.out.println(future.get());
    }

    /**
     * thenAccept : 以同步的方式消费上一个异步任务的结果
     */
    static void thenAccept() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            return "Java";
        }, executor).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + " thenAccept");
            System.out.println(Thread.currentThread().getName() + " " + e);
        });
        executor.shutdown();
    }

    /**
     * thenAcceptAsync 以异步的方式消费上一个异步任务的结果
     */
    static void thenAcceptAsync() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            return "Java";
        }, executor).thenAcceptAsync(e -> {
            System.out.println(Thread.currentThread().getName() + " thenAcceptAsync");
            System.out.println(Thread.currentThread().getName() + " " + e);
        });
        executor.shutdown();
    }

    /**
     * supplyAsync的计算结果为“Java”
     * thenAcceptAsync 消费  supplyAsync 的 结果
     * thenRun 执行Runnable
     */
    static void thenRun() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            return "Java";
        }, executor).thenAcceptAsync(e -> {
            System.out.println(Thread.currentThread().getName() + " thenAcceptAsync");
            System.out.println(Thread.currentThread().getName() + " " + e);
        }).thenRunAsync(() -> {
            System.out.println("all of task completed." + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenRun();
    }

    void sypplyAsunc() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 354);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 354, Executors.newCachedThreadPool());
    }


    void runAsync() {
        CompletableFuture.runAsync(() -> {
            System.out.println("async task .");
        });
    }

    void thenCompose() {
        //通过thenCompose将两个Future合并成一个Future
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Java")
                //s 为上一个Future的计算结果
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " scala"));

        //合并后的Future通过thenApply方法组成任务链
        completableFuture.thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }

    void thenCombine() {
        final CompletableFuture<String> thenCombine = CompletableFuture.supplyAsync(() -> "Java")
                .thenCombine(CompletableFuture.supplyAsync(() -> " scala"),
                        //s1为第一个Future计算的结果，s2为第二个Future计算的结果
                        (s1, s2) -> s1 + s2);
        thenCombine.thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }

    /**
     * 多Future的并行计算
     */
    void allOf() throws ExecutionException, InterruptedException {
        //定义三个CompletableFuture
        final CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Java");
        final CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Parallel");
        final CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "Future");

        //批量执行 ，返回值是一个 void 类型的CompletableFuture
        final CompletableFuture<Void> future = CompletableFuture.allOf(f1, f2, f3).thenRun(() -> {
            try {
                System.out.println(f1.isDone() + " and result " + f1.get());
                System.out.println(f2.isDone() + " and result " + f2.get());
                System.out.println(f3.isDone() + " and result " + f3.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //阻塞等待运行结束
        future.get();
    }

}
