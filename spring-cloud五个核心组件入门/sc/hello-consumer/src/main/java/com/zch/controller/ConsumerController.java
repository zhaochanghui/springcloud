package com.zch.controller;

import com.zch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hello-consumer")
    public String helloConsumer() {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
         User  user = restTemplate.getForObject("http://hello-service/info", User.class);
        return "hello consumer finish !!!"+user.toString();
    }

    //测试ribbon负载均衡
    @RequestMapping("/test")
    @ResponseBody
    public String test()
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("hello-service");
        return serviceInstance.getServiceId()+"--"+serviceInstance.getHost()+"--"+serviceInstance.getPort();
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public String user(@PathVariable("id") int id)
    {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
        User  user = restTemplate.getForObject("http://hello-service/user/"+id, User.class);
        return "hello consumer finish use 方法 !!!"+user.toString();
    }
}