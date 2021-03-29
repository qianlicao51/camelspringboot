package com.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/24 15:25
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final ReentrantLock lock = new ReentrantLock();
    volatile int count = 0;

    @GetMapping("add")
    public void add() {
        lock.lock();
        try {
            count++;
            log.info(Thread.currentThread().getName() + "|" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
