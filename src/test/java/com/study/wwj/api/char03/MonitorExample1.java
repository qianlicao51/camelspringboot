package com.study.wwj.api.char03;

import com.google.common.util.concurrent.Monitor;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 21:32
 */
public class MonitorExample1 {
    //定义临界值，共享数据的值不能超过MAX_VALUE
    public static final int MAX_VALUE = 10;
    //定义Monitor对象
    private static Monitor monitor = new Monitor();
    //共享数据
    private static int x = 0;
    //定义Guard并实现isSatisfied方法
    private static final Monitor.Guard INCR_WHEN_LESS_10 = new Monitor.Guard(monitor) {
        //该方法相当于我们在写对象监视器或者 Condition 时的临界值判读逻辑
        @Override
        public boolean isSatisfied() {
            return x < MAX_VALUE;
        }
    };

    // 注释①
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            //注释②
            monitor.enterWhen(INCR_WHEN_LESS_10);
            try {
                x++;
                System.out.println(Thread.currentThread().getName() + " :x value is" + x);
            } finally {
                //释放③
                monitor.leave();
            }
        }
    }
}
