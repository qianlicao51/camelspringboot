package com.study.wwj.thread.char15;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 14:31
 */
public interface TaskLifecycle<T> {
    //任务启动时会触发 onStart方法
    void onStart(Thread thread);

    //任务正在运行时会 触发 onRunning 方法
    void onRunning(Thread thread);

    // 任务结束会触发 onFinish 方法，其中result 是任务结束的后果
    void onFinish(Thread thread, T result);

    //任务执行报错时会触发onError 方法
    void onError(Thread thread, Exception e);

    //生命周期接口的空实现(Adapter)
    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {
        }

        @Override
        public void onRunning(Thread thread) {
        }

        @Override
        public void onFinish(Thread thread, T result) {
        }

        @Override
        public void onError(Thread thread, Exception e) {
        }
    }
}
