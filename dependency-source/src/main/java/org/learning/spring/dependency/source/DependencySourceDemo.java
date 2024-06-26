package org.learning.spring.dependency.source;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 1. 依赖来源示例（只能依赖注入，不能依赖查找）
 * <p>
 * {@link org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#registerResolvableDependency(Class, Object)}
 *
 * <pre>{@code
 * protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
 * ...
 * // BeanFactory interface not registered as resolvable type in a plain factory.
 * // MessageSource registered (and found for autowiring) as a bean.
 *  beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
 *  beanFactory.registerResolvableDependency(ResourceLoader.class, this);
 *  beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
 *  beanFactory.registerResolvableDependency(ApplicationContext.class, this);
 * }
 *
 * 2. BeanDefinition注册
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 *
 * 3. 单例
 * {@link org.springframework.beans.factory.config.SingletonBeanRegistry#registerSingleton(String, Object)}
 *
 * <p>
 */
public class DependencySourceDemo {

    // 注入在 postProcessProperties方法执行，早于setter注入，也早于@PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    // 依赖注入来源比依赖查找多一项，非spring管理对象
    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在beanFactory中查找！");
        }
        return null;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(DependencySourceDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并查找bean
        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);


        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }
}
