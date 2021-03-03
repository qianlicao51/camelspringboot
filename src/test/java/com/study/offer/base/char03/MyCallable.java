package com.study.offer.base.char03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/3 20:46
 */
//step 1 通过实现Callable 接口创建MyCallable 线程
public class MyCallable implements Callable<String> {

    private String name;

    //通过构造函数，以定义线程的名称
    public MyCallable(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // step 2 创建一个固定大小为5的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //step 3  创建多个有返回值的任务列表list
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 5; i++) {
            //step 4 创建一个有返回值的线程实例
            final MyCallable c = new MyCallable(i + " ");
            //step5 提交任务，获取future 对象并将其保存到 Future list中。
            final Future<String> future = pool.submit(c);
            System.out.println("submit a callable thread " + i);
            list.add(future);
        }
        //step 6 关闭线程池，等待线程执行结束
        pool.shutdown();
        //step 7 遍历所有线程的运行结果
        for (Future future : list) {
            //从future中取出结果
            System.out.println("get the result from callable thread :" + future.get().toString());
        }
    }

    @Override
    public String call() throws Exception {
        return name;
    }
}
