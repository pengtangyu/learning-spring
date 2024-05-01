package org.learning.spring.bean.definition;

import org.learning.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean垃圾回收（GC）示例
 */
public class Lesson6_BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类）
        applicationContext.register(Lesson5_BeanInitializationDemo.class);
        // 启动Spring应用上下文
        applicationContext.refresh();

        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        // 关闭Spring应用上下文
        applicationContext.close();

        Thread.sleep(5000L);
        // 强制触发GC
        System.gc();
        Thread.sleep(10000L);
    }
}
