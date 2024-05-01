package org.learning.spring.ioc.overview.dependency.injection;

import org.learning.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 * 1、通过名称的方式来查找
 * <p>
 * Spring依赖注入来源：
 * 1、自定义Bean
 * 2、内部容器所构建的Bean
 * 3、内部容器所构建的依赖
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动 Spring上下文
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        userRepository.getUsers().forEach(System.out::println);

        System.out.println(userRepository.getApplicationContext() == applicationContext);

        // 依赖来源一：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == applicationContext);

        // 依赖来源一：容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("Environment : " + environment);
    }

    public static void whoIsIocContainer(ApplicationContext applicationContext, UserRepository userRepository) {

        // 这个表达式为什么不成立？
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        // ApplicationContext is BeanFactory
        // ApplicationContext中使用组合模式组合了BeanFactory，所以两个不是同一个对象
        // AbstractRefreshableApplicationContext#getBeanFactory()
    }
}
