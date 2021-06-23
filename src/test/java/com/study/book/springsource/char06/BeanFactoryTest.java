package com.study.book.springsource.char06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/23 21:37
 */
public class BeanFactoryTest {
    @Test
    public void testSimpleLoad() {
        final ClassPathResource resource = new ClassPathResource("beanFactoryTest.xml");
        final BeanFactory bf = new XmlBeanFactory(resource);
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanFactoryTest.xml");


    }
}
