package org.learning.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 演示
 * @author pengty
 */
public class Lesson4_2_BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean",beanDefinitionBuilder.getBeanDefinition());
        // 启动应用上下文
        applicationContext.refresh();

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }
}
