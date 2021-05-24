package com.study.suggest151.char01;

import java.io.Serializable;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:17
 */
public class PersonFinalDemo implements Serializable {
    private static final long serialVersionUID = 1L;

    //通过方法返回值为final变量赋值
    public final String name = initName();

    private String initName() {
        return "混世魔王";
    }
}
