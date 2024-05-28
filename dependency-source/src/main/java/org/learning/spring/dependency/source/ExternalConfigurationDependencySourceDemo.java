package org.learning.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并查找bean
        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println(demo.id);
        System.out.println(demo.name);
        System.out.println(demo.resource);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }
}
