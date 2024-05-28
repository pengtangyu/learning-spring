package org.learning.spring.bean.lifecycle;

import org.learning.spring.ioc.overview.container.BeanFactoryAsIocContainer;
import org.learning.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean元信息配置示例
 * <p>
 * 1. 通过xml配置的方式 {@link BeanFactoryAsIocContainer}
 * 2. 本示例 基于properties方式
 */
public class BeanMetaDataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化 基于 properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String location = "META-INF/user.properties";
        // 加载 properties资源
        // 指定字符编码 utf-8
        ClassPathResource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载BeanDefinition 数量：" + beanNumbers);

        // 通过id查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

}
