package org.learning.spring.ioc.overview.dependency.lookup;

import org.learning.spring.ioc.overview.annotation.Super;
import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * 1、通过名称的方式来查找
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动 Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
//        lookupLazy(beanFactory);
        // 按照类型查找
//        lookupByType(beanFactory);
        // 按照类型查找集合对象
//        lookupByCollection(beanFactory);
        // 通过注解查找
//        lookupByAnnotationType(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory listableBeanFactory) {
            Map users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找所有标注 @Super 的集合对象：" + users);
        }
    }

    private static void lookupByCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory listableBeanFactory) {
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的User集合对象：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找：" + user);
    }

    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }
}
