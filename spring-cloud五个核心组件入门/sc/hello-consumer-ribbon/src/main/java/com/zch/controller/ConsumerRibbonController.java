package com.zch.controller;

import com.zch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerRibbonController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello-consumer-ribbon")
    public String helloConsumer() {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
        User user = restTemplate.getForObject("http://hello-service/info", User.class);
        return "hello consumer-ribbob finish !!!"+user.toString();
    }
}