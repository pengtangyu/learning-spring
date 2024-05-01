package org.learning.spring.dependency.injection.lesson2;

import org.learning.spring.dependency.injection.lesson1.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * byName Auto-wring依赖 constructor 方式注入示例
 */
public class AutowiringConstructorDependencyInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:META-INF/autowiring-dependency-constructor-injection.xml";
        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 依赖查找并查找bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean.getUser());
    }
}
