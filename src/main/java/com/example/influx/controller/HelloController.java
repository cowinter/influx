package com.example.influx.controller;

import com.example.influx.service.impl.HomeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shen
 * @Description hello
 * @createTime 2022-06-24
 */
@RestController
@RequestMapping("/home")
@Slf4j
public class HelloController {

    @Autowired
    private HomeServiceImpl homeService;

    @GetMapping("/hi")
    String sayHi(){
        return homeService.sayHi();
    }
}
