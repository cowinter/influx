package com.example.influx.service.impl;

import com.example.influx.annotation.parentSonAnnotation.ParentAnno;
import com.example.influx.annotation.parentSonAnnotation.SonAnno;
import com.example.influx.service.HomeService;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-08-04
 */
public class Test {
    public static void main(String[] args) {
//        Annotation[] annotation = HomeServiceImpl.class.getAnnotations();
        ParentAnno parentAnno = AnnotatedElementUtils.getMergedAnnotation(HomeServiceImpl.class, ParentAnno.class);
        System.out.println(parentAnno);
    }
}
