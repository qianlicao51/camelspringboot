package com.study.book.springsource.char07;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 16:13
 */
public class AopTest {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationAop.xml");
        TestBean test = (TestBean) ctx.getBean("testBean");
        test.test();
        test = (TestBean) ctx.getBean("testBean");
        test.test();
    }
}
