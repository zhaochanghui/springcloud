package com.zch.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.zch.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

//    @Autowired
//    DiscoveryClient discoveryClient;

    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("/hello")
    public String hello() {
//       List list = discoveryClient.getInstancesById("hello-service");
        //打印服务的服务id
//        logger.info("*********" + list.toString());

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("hello-service",false);
        return "hello,this is hello-service";
    }



    @RequestMapping("/info")
    @ResponseBody
    public User info()
    {
        return new User(1001,"张三",new Date());
    }


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User user(@PathVariable("id") Integer id)
    {
        System.out.println("调用生产者的user方法，参数ID是："+id);
        return new User(id,"张三",new Date());
    }
}