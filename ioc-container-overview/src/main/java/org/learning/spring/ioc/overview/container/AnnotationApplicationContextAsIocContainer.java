package org.learning.spring.ioc.overview.container;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotationApplicationContextAsIocContainer {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIocContainer作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIocContainer.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByCollection(applicationContext);
        // 关闭
        applicationContext.close();
    }

    /**
     * 通过JAVA注解的方式定义了一个Bean
     *
     * @return
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("PTY");
        return user;
    }

    private static void lookupByCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的User集合对象：" + users);
        }
    }
}
