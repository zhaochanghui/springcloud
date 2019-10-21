package com.zch.controller;

import com.zch.entity.User;
import com.zch.service.MyFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerFeignController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MyFeignService myFeignService;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hello")
    public String helloConsumer() {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
         User  user = restTemplate.getForObject("http://hello-service/info", User.class);
        return "hello consumer  feigin finish !!!"+user.toString();
    }

    @RequestMapping(value = "/feigin_hello",method = RequestMethod.GET)
    public String feigin_hello()
    {
        return myFeignService.hello();
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String user(@PathVariable("id") int id)
    {
        return myFeignService.user(id);
    }

}