package com.study.offer.base.char03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/4 21:12
 */
public class DiscardOldestPolicy implements RejectedExecutionHandler {
    //自定义拒绝策略：尝试丢弃最古老的N个线程，以便在出现异常时释放更多的资源，保障后续线程任务整体、稳定地运行。
    private int discardNumber = 5;
    private List<Runnable> discardList = new ArrayList<>();

    public DiscardOldestPolicy(int discardNumber) {
        this.discardNumber = discardNumber;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (e.getQueue().size() > discardNumber) {
            //step 1 批量移除线程队列的 discardNumber 个线程任务。
            e.getQueue().drainTo(discardList, discardNumber);
            //step 2 清空 discardList
            discardList.clear();
            if (!e.isShutdown()) {
                //step 3 尝试提交当前任务
                e.execute(r);
            }
        }
    }
}
