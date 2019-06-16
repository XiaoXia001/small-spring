package com.zjw.smallioc;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
