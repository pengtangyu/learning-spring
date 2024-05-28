package org.learning.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 Resolvable Dependency
            beanFactory.registerResolvableDependency(String.class, "Hello World");
        });

        // 启动应用上下文
        applicationContext.refresh();

        /*AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        if (autowireCapableBeanFactory instanceof ConfigurableListableBeanFactory configurableListableBeanFactory) {
            configurableListableBeanFactory.registerResolvableDependency(String.class, "Hello World");
        }*/

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }
}
