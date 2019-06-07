package com.zjw.smallioc.factory;

import com.zjw.smallioc.BeanDefinition;

/**
 * 可自动实现的
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition)
    {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            return bean;
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return null;
    }
}
