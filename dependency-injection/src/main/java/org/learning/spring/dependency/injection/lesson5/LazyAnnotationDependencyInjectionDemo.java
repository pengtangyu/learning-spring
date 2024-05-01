package org.learning.spring.dependency.injection.lesson5;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 基于{@link Qualifier}注解依赖注解
 */
@Configuration // 可以不加
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; //实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>> userSetObjectFactory; // 延迟注入

    public static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并查找bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.userObjectProvider = " + demo.userObjectProvider.getObject());
        demo.userObjectProvider.forEach(System.out::println);

        System.out.println("demo.userSetObjectFactory = " + demo.userSetObjectFactory.getObject());

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }


}
