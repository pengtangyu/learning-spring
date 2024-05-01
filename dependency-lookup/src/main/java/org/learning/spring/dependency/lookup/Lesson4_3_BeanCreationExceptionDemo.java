package org.learning.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}
 * @author pengty
 */
public class Lesson4_3_BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());
        // 启动应用上下文
        applicationContext.refresh();

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct
        public void init() throws Throwable {
            throw new Exception("init(): For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet(): For purposes...");
        }
    }
}
