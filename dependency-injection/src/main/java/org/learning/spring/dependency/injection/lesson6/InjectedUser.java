package org.learning.spring.dependency.injection.lesson6;

import java.lang.annotation.*;


/**
 * 自定义依赖注解
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {

    boolean required() default true;
}
