package com.zjw.smallioc.factory;

import com.zjw.smallioc.BeanDefinition;
import com.zjw.smallioc.BeanReference;
import com.zjw.smallioc.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动实现的
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);

        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception{
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValueList())
        {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
//            declaredField.set(bean, propertyValue.getValue());
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(bean, value);
        }
    }
}
