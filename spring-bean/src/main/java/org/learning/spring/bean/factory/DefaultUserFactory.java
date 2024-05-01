package org.learning.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory}实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 1.基于@PostConstruct注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中...");
    }

    // 2.InitializingBean#afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet : UserFactory 初始化中...");
    }

    // 3.@Bean(initMethod = "initUserFactory")
    public void initUserFactory() {
        System.out.println("自定义初始化方法 : UserFactory 初始化中...");
    }

    // 销毁

    // 1.基于@PreDestroy
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : UserFactory销毁中...");
    }

    //2.DisposableBean#destroy()
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() : UserFactory销毁中...");
    }

    // 3.@Bean(destroyMethod = "doDestroy")
    public void doDestroy() throws Exception {
        System.out.println("自定义销毁方法 doDestroy(): UserFactory销毁中...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("当前 DefaultUserFactory对象正在被回收...");
    }
}
