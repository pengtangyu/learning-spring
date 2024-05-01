package org.learning.spring.bean.definition;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示例
 */
public class Lesson1_BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1.通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "pty");
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非Bean终态，可以自定义修改


        // 2.通过 AbstractBeanDefinition 以及派生类构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过MutablePropertyValues批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id", 1);
//        propertyValues.addPropertyValue("name", "pty");
        propertyValues
                .add("id", 1)
                .add("name", "pty");
        // 通过setPropertyValues批量操作属性
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
