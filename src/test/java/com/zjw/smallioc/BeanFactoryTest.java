package com.zjw.smallioc;

import com.zjw.smallioc.factory.AutowireCapableBeanFactory;
import com.zjw.smallioc.factory.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test(){

        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.zjw.smallioc.HelloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
