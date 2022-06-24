package com.example.influx.annotation;

import java.lang.annotation.*;

/**
 * @author Shen
 * @Description 随机数字日志注解
 * @createTime 2022-06-24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogMath {
    String value();
    int ratio() default 10;
}
