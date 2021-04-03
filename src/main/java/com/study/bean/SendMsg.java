package com.study.bean;

import java.io.Serializable;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 21:20
 */
public class SendMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private String msg;

    public SendMsg() {
    }

    public SendMsg(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
