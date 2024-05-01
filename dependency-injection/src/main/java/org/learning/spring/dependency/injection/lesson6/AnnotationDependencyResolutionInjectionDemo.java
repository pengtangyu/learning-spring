package org.learning.spring.dependency.injection.lesson6;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 *
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String)}
 */
@Configuration // 可以不加
public class AnnotationDependencyResolutionInjectionDemo {

    @Lazy
    @Autowired
    private User lazyUser;

    @Autowired
    private User user; //  DependencyDescriptor -> 必须（required=true） 实时注入（eager=true） 通过类型（User.class） 字段名称（user） 是否首要（primary=true）

    @Autowired
    private Collection<User> users;

    @Autowired
    private Optional<User> optionalUser;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(AnnotationDependencyResolutionInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并查找bean
        AnnotationDependencyResolutionInjectionDemo demo = applicationContext.getBean(AnnotationDependencyResolutionInjectionDemo.class);

        System.out.println("demo.lazyUser = " + demo.lazyUser);
        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.optionalUser = " + demo.optionalUser);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }


}
