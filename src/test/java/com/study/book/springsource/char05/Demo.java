package com.study.book.springsource.char05;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 15:45
 */
public class Demo {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Test test = (Test) ctx.getBean("test");
        test.testAware();
    }
}
