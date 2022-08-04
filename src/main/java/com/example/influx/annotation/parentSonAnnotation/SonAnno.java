package com.example.influx.annotation.parentSonAnnotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author Shen
 * @Description 子注解
 * @createTime 2022-08-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,TYPE})
@Documented
@ParentAnno
public @interface SonAnno {
    String value() default "son";
    @AliasFor(annotation = ParentAnno.class,attribute = "pValue")
    String pValue() default "parent2";
}
