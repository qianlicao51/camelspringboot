package com.study.book.springsource.char05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/23 9:49
 */
public class Test implements BeanFactoryAware {
    private BeanFactory beanFactory;

    //声明 bean的时候 spring 自动注册 BeanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        //从 beanFactory中获取
        final Helllo hello = (Helllo) beanFactory.getBean("hello");
        hello.say();
    }
}
