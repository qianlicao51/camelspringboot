package com.study.wwj.api.char03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 14:54
 */
public class CyclicBarrierExample1 {
    public static void main(String[] args) throws InterruptedException {
        //首先获取商品编号的列表
        final int[] products = getProductsByCategoryId();

        //通过Stream 的map 运算 将商品编号转为 ProductPrice
        final List<CountDownLatchExample1.ProductPrice> list = Arrays.stream(products)
                .mapToObj(CountDownLatchExample1.ProductPrice::new)
                .collect(Collectors.toList());


        // ① 定义 CyclicBarrier 计算器 数量为子任务的 数量
        final CyclicBarrier barrier = new CyclicBarrier(products.length + 1);
        // ② 存放线程任务的list
        final List<Thread> threadList = new ArrayList<>();
        list.forEach(
                pp -> {
                    final Thread thread = new Thread(() -> {
                        System.out.println(pp.getProdID() + " start calculate  price ");
                        try {
                            //模拟其他的系统调用，使用睡眠代替耗时
                            TimeUnit.SECONDS.sleep(current().nextInt(10));
                            //计算价格
                            if (pp.getProdID() % 2 == 0) {
                                pp.setPrice(pp.prodID * 0.9d);
                            } else {
                                pp.setPrice(pp.prodID * 0.71d);
                            }
                            System.out.println(pp.getProdID() + " -> price calculate completed.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            //③ 在此等待其他子线程到达 barrier pointer
                            try {
                                barrier.await();
                            } catch (InterruptedException | BrokenBarrierException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    threadList.add(thread);
                    thread.start();
                }

        );

        // ④ 主线程阻塞等待所有子任务结束
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("all of price calculate finished.");
        list.forEach(System.out::println);
    }

    //根据品类ID获取商品列表
    private static int[] getProductsByCategoryId() {
        return IntStream.rangeClosed(1, 10).toArray();
    }
}
