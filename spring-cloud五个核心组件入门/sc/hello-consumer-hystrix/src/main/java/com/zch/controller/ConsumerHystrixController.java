package com.zch.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zch.entity.User;
import com.zch.service.MyHystrixervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerHystrixController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MyHystrixervice myHystrixervice;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hy/hello")
    public String helloConsumer() {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
         User  user = restTemplate.getForObject("http://hello-service/info", User.class);
        return "hello consumer  feigin finish !!!"+user.toString();
    }

    @RequestMapping(value = "/hy/feigin_hello",method = RequestMethod.GET)
    public String feigin_hello()
    {
        return myHystrixervice.hello();
    }

    @RequestMapping(value = "/hy/user/{id}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getError")   //可以在设置错误的请求地址测试
    public String user(@PathVariable("id") int id)
    {
        return myHystrixervice.user(id);
    }


    public String getError(int id) {
        System.out.println(Thread.currentThread().getName()+":getError before ....");
        //myService.execute();
        System.out.println("断路器已触发，并作相应的业务处理...");
        System.out.println(Thread.currentThread().getName()+":getError after ....");

        return "请求超时，使用断路器返回，id=" + id;
    }

}