package org.learning.spring.dependency.lookup;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 类型安全 依赖查找示例
 *
 * @author pengty
 */
@Configuration
public class Lesson3_TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(Lesson3_TypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getBean 方法的安全性
        displayObjectFactoryGetBean(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);
        // 演示ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream的方法安全性
        displayObjectProviderStreamsOps(applicationContext);


        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamsOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayListableBeanFactory", () -> applicationContext.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", userObjectProvider::getIfAvailable);
    }

    private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetBean", userObjectFactory::getObject);
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("-----------------------------");
        System.err.println("Source from :" + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

}
