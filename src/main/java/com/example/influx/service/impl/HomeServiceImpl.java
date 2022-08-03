package com.example.influx.service.impl;

import com.example.influx.annotation.LogMath;
import com.example.influx.annotation.parentSonAnnotation.ParentAnno;
import com.example.influx.annotation.parentSonAnnotation.SonAnno;
import com.example.influx.service.HomeService;
import org.springframework.stereotype.Service;

/**
 * @author Shen
 * @Description 主页实现类
 * @createTime 2022-06-24
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Override
//    @LogMath(value = "sayHi",ratio = 1000)
    @SonAnno("子注解")
    public String sayHi() {
        return "hello";
    }
}
