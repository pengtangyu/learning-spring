package org.learning.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException}
 *
 * @author pengty
 */
public class Lesson4_1_NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(Lesson4_1_NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring应用上下文存在%d个 %s 类型的Bean，具体原因: %s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        // 显式的关闭Spring应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

}
