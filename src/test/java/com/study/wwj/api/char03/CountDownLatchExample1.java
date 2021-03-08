package com.study.wwj.api.char03;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 14:21
 */
public class CountDownLatchExample1 {
    public static void main(String[] args) throws InterruptedException {
        //首先获取商品编号的列表
        final int[] products = getProductsByCategoryId();

        //通过Stream 的map 运算 将商品编号转为 ProductPrice
        final List<ProductPrice> list = Arrays.stream(products)
                .mapToObj(ProductPrice::new)
                .collect(Collectors.toList());


        // ① 定义countdownlatch 计算器 数量为子任务的 数量

        final CountDownLatch latch = new CountDownLatch(products.length);

        list.forEach(
                pp -> {
                    //② 为每一件商品的计算都开辟对应的线程
                    new Thread(() -> {
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
                            //③ 计数器 count down ,子任务执行完成
                            latch.countDown();
                        }
                    }).start();
                }
        );

        // ④ 主线程阻塞等待所有子任务结束，如果有一个子任务没有完成则会一直等待。
        latch.await();
        System.out.println("all of price calculate finished.");
        list.forEach(System.out::println);

    }

    //根据品类ID获取商品列表
    private static int[] getProductsByCategoryId() {
        return IntStream.rangeClosed(1, 10).toArray();
    }

    static class ProductPrice {
        final int prodID;
        private double price;

        public ProductPrice(int prodID) {
            this(prodID, -1);
        }

        public ProductPrice(int prodID, double price) {
            this.prodID = prodID;
            this.price = price;
        }

        public int getProdID() {
            return prodID;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("prodID", prodID)
                    .append("price", price)
                    .toString();
        }
    }
}
