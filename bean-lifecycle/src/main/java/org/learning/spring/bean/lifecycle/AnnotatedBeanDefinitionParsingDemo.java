package org.learning.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于java注解的BeanDefinition
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCounterBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @component class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCounterAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCounterAfter - beanDefinitionCounterBefore;
        System.out.println("已加载 BeanDefinition数量：" + beanDefinitionCount);

        // 普通class作为component注册到ioc容器，通常bean名称为 annotatedBeanDefinitionParsingDemo
        /**
         * bean名称生成来自于 BeanNameGenerator {@link org.springframework.beans.factory.support.BeanNameGenerator},
         * 注解来自 {@link org.springframework.context.annotation.AnnotationBeanNameGenerator}
         */
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
