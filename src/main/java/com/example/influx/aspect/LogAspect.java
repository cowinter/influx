package com.example.influx.aspect;

import com.example.influx.annotation.LogMath;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Shen
 * @Description 日志
 * @createTime 2022-06-24
 */
@Aspect
@Component
public class LogAspect {
    @Around("@annotation(com.example.influx.annotation.LogMath)")
    public String log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogMath logMath = method.getAnnotation(LogMath.class);

        System.out.println("-----开始插入数据------");
        System.out.println("执行的方法标识->"+logMath.value());
        System.out.println("随机生成数->"+logMath.ratio());
        String str = (String) joinPoint.proceed();
        System.out.println("-----结束插入数据------");
        return str;
    }
}
