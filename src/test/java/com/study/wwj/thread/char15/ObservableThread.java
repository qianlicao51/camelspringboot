package com.study.wwj.thread.char15;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 14:38
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;
    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("The task is required.");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    public static void main(String[] args) {

        final TaskLifecycle.EmptyLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };
        final ObservableThread<String> observableThread = new ObservableThread<>(lifecycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("finished done");
            return "hello Observer";
        });
        observableThread.start();
    }

    @Override
    public void run() {
        //在执行线程单元的时候，分别触发响应的事件
        this.update(Cycle.STARTED, null, null);
        try {
            final T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
