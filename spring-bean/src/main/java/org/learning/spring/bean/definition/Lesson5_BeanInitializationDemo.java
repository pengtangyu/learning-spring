package org.learning.spring.bean.definition;

import org.learning.spring.bean.factory.DefaultUserFactory;
import org.learning.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化Demo
 */
@Configuration
public class Lesson5_BeanInitializationDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(Lesson5_BeanInitializationDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // @Lazy时延迟初始化，先打印"Spring 应用上下文已启动..."，后初始化
        // 非延迟初始化在 Spring应用上下文启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");

        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        // 显式的关闭Spring应用上下文，会触发bean的销毁
        System.out.println("Spring 应用上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
