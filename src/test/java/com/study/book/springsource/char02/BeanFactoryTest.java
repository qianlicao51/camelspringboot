package com.study.book.springsource.char02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        final MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        assertEquals("testStr", myTestBean.getTestStr());
        

    }
}
