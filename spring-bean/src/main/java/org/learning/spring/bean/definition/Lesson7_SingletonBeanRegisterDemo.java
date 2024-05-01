package org.learning.spring.bean.definition;

import org.learning.spring.bean.factory.DefaultUserFactory;
import org.learning.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean初始化Demo
 */
public class Lesson7_SingletonBeanRegisterDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册一个外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry registry = applicationContext.getBeanFactory();
        registry.registerSingleton("userFactory", userFactory);
        // 启动Spring应用上下文
        applicationContext.refresh();

        // 通过依赖查找获取UserFactory
        UserFactory userFactoryLookup = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryLookup : " + (userFactory == userFactoryLookup));

        // 关闭Spring应用上下文
        applicationContext.close();
    }
}
