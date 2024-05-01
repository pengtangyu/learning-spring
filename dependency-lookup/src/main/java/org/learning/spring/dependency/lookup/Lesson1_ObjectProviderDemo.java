package org.learning.spring.dependency.lookup;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 通过{@link ObjectProvider} 进行依赖查找
 *
 * @author pengty
 */
@Configuration
public class Lesson1_ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(Lesson1_ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

//        lookupByProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterator = objectProvider;
//        for (String str : stringIterator) {
//            System.out.println(str);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前User对象：" + user);
    }

    private static void lookupByProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    @Bean
    @Primary
    public String helloWorld() { // 方法名就是Bean名称 = "helloWorld"
        return "Hello,World";
    }

    @Bean
    public String message() { // 方法名就是Bean名称 = "helloWorld"
        return "Message";
    }
}
