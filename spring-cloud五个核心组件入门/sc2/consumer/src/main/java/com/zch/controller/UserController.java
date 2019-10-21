package com.zch.controller;

import com.zch.entity.User;
import com.zch.myfeigin.Feigin01;
import com.zch.myfeigin.Feigin02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;  //启动类中的bean

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    Feigin01 feigin01;

    @Autowired
    Feigin02 feigin02;

    @RequestMapping("/index")
    public String index()
    {
        User user = restTemplate.getForObject("http://producer/user/1",User.class);
        return "consumer....userController》》 index 方法.user:"+user.toString();
    }

    //测试负载均衡,同一个服务启动2次，端口不一样
    @RequestMapping("/test")
    public String test()
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("producer");
        return serviceInstance.getServiceId()+"---"+serviceInstance.getHost()+"...."+ serviceInstance.getPort();
    }

    @RequestMapping("/getall")
    public List<User> getall()
    {
        return feigin01.getall();
    }

    @RequestMapping("/order/{id}")
    public User getOrder(@PathVariable("id") int id){
        return feigin02.user(id);
    }
}
