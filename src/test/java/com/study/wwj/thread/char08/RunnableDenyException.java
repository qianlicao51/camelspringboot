package com.study.wwj.thread.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 10:58
 */
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}
