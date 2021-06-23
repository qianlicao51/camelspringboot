package com.study.book.springsource.char06;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/23 11:35
 */
public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private Set<String> obscenties;

    public ObscenityRemovingBeanFactoryPostProcessor() {
        this.obscenties = new HashSet<String>();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            final BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
            final StringValueResolver stringValueResolver = new StringValueResolver() {
                @Override
                public String resolveStringValue(String strVal) {
                    if (isObscenties(strVal)) {
                        return "******";
                    }
                    return strVal;
                }
            };
        }
    }

    public boolean isObscenties(Object value) {
        final String s = value.toString().toUpperCase();
        return this.obscenties.contains(s);
    }

    public void setObscenties(Set<String> obscenties) {
        this.obscenties.clear();
        for (String obscenty : obscenties) {
            this.obscenties.add(obscenty.toUpperCase());
        }
    }
}
