package com.zjw.smallioc.factory;

import com.zjw.smallioc.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {

    //private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
