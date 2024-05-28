package org.learning.spring.dependency.injection.lesson6;

import jakarta.inject.Inject;
import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 注解驱动的依赖注入处理过程
 * <p>
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String)}
 * </p>
 * <code>@Autowired</code>，<code>@Inject</code> 注解实现
 * {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}
 * <p>
 * <code>@Resource</code> 注解实现
 * {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor}
 * </p>
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

    // 扩展注解方式1
    @MyAutowired
    private Optional<User> optionalUser;

    // jsr330实现
    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

    // 扩展注解方式2
    // static 是脱离于AnnotationDependencyResolutionInjectionDemo，提早触发
    /*@Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredAnnotations =
                new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotations);
        return beanPostProcessor;
    }*/

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor2() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

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
        System.out.println("demo.injectedUser = " + demo.injectedUser);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.optionalUser = " + demo.optionalUser);

        System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }


}
