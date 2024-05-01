package org.learning.spring.dependency.injection.lesson3;

import org.learning.spring.dependency.injection.lesson1.UserHolder;
import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于Java注解 依赖的 method 方法注入
 *
 * @Autowired, @Resource, @Inject, @Bean都可以实现方法注入
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder1;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        userHolder1 = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder) {
        userHolder2 = userHolder;
    }

    @Bean
    public UserHolder userHolder(User user) {
        // return new UserHolder(user);

        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类） -> Spring bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // 依赖查找并查找bean
        UserHolder userHolder1 = demo.userHolder1;
        System.out.println(userHolder1);

        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);

        System.out.println(userHolder1 == userHolder2);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }


}
