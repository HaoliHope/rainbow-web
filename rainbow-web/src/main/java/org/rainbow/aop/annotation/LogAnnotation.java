package org.rainbow.aop.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author lihao3
 * @Date 2020/8/27 9:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    String action() default "";
}
