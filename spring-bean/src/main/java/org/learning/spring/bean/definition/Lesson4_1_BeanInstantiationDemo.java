package org.learning.spring.bean.definition;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 */
public class Lesson4_1_BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-creation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        System.out.println(userByStaticMethod == userByInstanceMethod);
    }
}
