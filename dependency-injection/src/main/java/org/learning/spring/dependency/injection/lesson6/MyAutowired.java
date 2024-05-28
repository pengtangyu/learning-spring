package org.learning.spring.dependency.injection.lesson6;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;


/**
 * 自定义注解（元标注）
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    boolean required() default true;
}
