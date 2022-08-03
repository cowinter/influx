package com.example.influx.annotation.parentSonAnnotation;

import java.lang.annotation.*;

/**
 * @author Shen
 * @Description 父注解
 * @createTime 2022-08-03
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ParentAnno {
    String value() default "parent";
}
