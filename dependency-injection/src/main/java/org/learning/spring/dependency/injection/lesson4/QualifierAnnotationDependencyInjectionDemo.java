package org.learning.spring.dependency.injection.lesson4;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * 基于{@link Qualifier}注解依赖注解
 */
@Configuration // 可以不加
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser ->primary=true

    @Autowired
    @Qualifier("user") // 指定 bean名称或ID
    private User nameUser;

    // Spring上下文存在4个User类型的bean
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier
    @Autowired
    private Collection<User> allUsers; // 2 beans = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 2 beans = user1 + user2

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; // 2 beans = user3 + user4

    @Bean
    @Qualifier //进行逻辑分组
    public User user1(){
        return createUser(7L);
    }

    @Bean
    @Qualifier //进行逻辑分组
    public User user2(){
        return createUser(8L);
    }

    @Bean
    @UserGroup //进行逻辑分组
    public User user3(){
        return createUser(9L);
    }

    @Bean
    @UserGroup //进行逻辑分组
    public User user4(){
        return createUser(10L);
    }

    public static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并查找bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.nameUser = " + demo.nameUser);

        // 输出 user + superUser
        System.out.println("demo.allUsers = " + demo.allUsers);
        // 输出 user1 + user2 + user3 + user4
        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);
        // 输出 user3 + user4
        System.out.println("demo.groupedUsers = " + demo.groupedUsers);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }


}
