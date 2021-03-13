package com.study.wwj.api.char03;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 22:44
 */
//限速漏桶
public class RateLimiterBucket {
    //定义漏桶的上沿容量
    public static final int BUCKET_CAPACITY = 1000;
    //漏桶采用 线程安全的容器，
    public final ConcurrentLinkedQueue<Request> bucket = new ConcurrentLinkedQueue<>();
    //定义漏桶下沿水流速率，每秒均匀放行10个request
    private final RateLimiter rateLimiter = RateLimiter.create(10.0D);
    //提交请求是需要 的Monitor
    private final Monitor requestMonitor = new Monitor();
    //处理请求时需要用到的Monitor
    private final Monitor handleMonitor = new Monitor();

    public void sumbitRequest(int data) {
        this.sumbitRequest(new Request(data));
    }

    public void sumbitRequest(Request request) {
        //① 当漏桶容量未溢出时
        if (requestMonitor.enterIf(requestMonitor.newGuard(() -> bucket.size() < BUCKET_CAPACITY))) {
            try {
                //在漏桶中加入 新的  request
                final boolean result = bucket.offer(request);
                if (result) {
                    System.out.println(Thread.currentThread().getName() + " submit request " + request.getData() + " successfully!");
                } else {
                    //produce into MQ and will try again later
                }
            } finally {
                requestMonitor.leave();
            }
        } else {
            // 当漏桶溢出的时候做降权处理
            System.out.println("the request :" + request.getData() + " will be down-dimensional handle due to bucker is overflow");
            //produce into MQ and will try again later
        }
    }

    //该方法主要从漏桶中云速地处理相关请求
    public void handleRequest(Consumer<Request> consumer) {
        //若漏桶中存在请求，则处理
        if (handleMonitor.enterIf(handleMonitor.newGuard(() -> !bucket.isEmpty()))) {
            try {
                //③ 匀速处理
                rateLimiter.acquire();
                //处理数据
                consumer.accept(bucket.poll());
            } finally {
                handleMonitor.leave();
            }
        }
    }

    //一个简单的请求类
    static class Request {
        private final int data;

        public Request(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .toString();
        }
    }
}
