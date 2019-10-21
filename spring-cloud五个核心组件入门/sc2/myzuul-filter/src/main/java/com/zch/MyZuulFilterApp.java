package com.zch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy  //这个注解自动开启熔断，注册到服务中心，但是需要导入相关依赖
public class MyZuulFilterApp {

    public static void main(String[] args) {
        SpringApplication.run(MyZuulFilterApp.class,args);
    }
}
