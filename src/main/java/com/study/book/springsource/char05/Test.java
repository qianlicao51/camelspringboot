package com.study.book.springsource.char05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 15:40
 */
public class Test implements BeanFactoryAware {
    private BeanFactory beanFactory;

    //声明bean的时候 spring会自动注入 BeanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        //通过hello 这个bean id 从beanFactory 获取实例
        final Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }

}
