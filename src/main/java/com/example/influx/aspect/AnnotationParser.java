package com.example.influx.aspect;

import com.example.influx.annotation.parentSonAnnotation.ParentAnno;
import com.example.influx.annotation.parentSonAnnotation.SonAnno;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Shen
 * @Description 注解解释器
 * @createTime 2022-08-03
 */
@Component
@Aspect
public class AnnotationParser {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(parentAnno)")
    public void parentAndSon(ProceedingJoinPoint joinPoint, ParentAnno parentAnno) throws Throwable {
        log.info("方法执行前{},",parentAnno.value());
        joinPoint.proceed();
        log.info("方法执行后{},",parentAnno.value());
    }
}
