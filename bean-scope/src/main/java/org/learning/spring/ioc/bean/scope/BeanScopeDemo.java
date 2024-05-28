package org.learning.spring.ioc.bean.scope;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {

    @Bean
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser3;

    @Autowired
    private Map<String, User> userMap;

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(BeanScopeDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找并查找bean
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        // 结论1
        // Singleton Bean 无论依赖查找或依赖注入，均为同一个对象
        // Property Bean 无论依赖查找或依赖注入，均为新生成对象

        // 结论2
        // 如果依赖注入为集合对象，Singleton Bean和Property Bean都会存在一个

        // 结论3 （spring无法管理Property生命周期）
        // Singleton Bean 和 Property Bean都会执行初始化方法回调
        // 只有 Singleton Bean 会执行销毁

        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser1 = " + beanScopeDemo.singletonUser1);
        System.out.println("beanScopeDemo.singletonUser2 = " + beanScopeDemo.singletonUser2);
        System.out.println("beanScopeDemo.prototypeUser1 = " + beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2 = " + beanScopeDemo.prototypeUser2);
        System.out.println("beanScopeDemo.prototypeUser3 = " + beanScopeDemo.prototypeUser3);

        System.out.println("beanScopeDemo.users = " + beanScopeDemo.userMap);
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = " + singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser = " + prototypeUser);
        }
    }


    // 销毁 scope property的bean
    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean销毁中...");

        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        this.prototypeUser3.destroy();

        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }

        System.out.println("当前 BeanScopeDemo Bean销毁完成");

    }
}
