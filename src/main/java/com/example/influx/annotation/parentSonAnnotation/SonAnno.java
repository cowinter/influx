package com.example.influx.annotation.parentSonAnnotation;

import java.lang.annotation.*;

/**
 * @author Shen
 * @Description 子注解
 * @createTime 2022-08-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ParentAnno
public @interface SonAnno {
    String value() default "son";
}
