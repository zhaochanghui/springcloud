package com.zch.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "hello-service",fallback = MyFeiginHystrixerviceImpl.class)
public interface MyFeiginHystrixervice {

    //绑定具体服务的rest接口
    @RequestMapping("/hello")
    String hello();

    //绑定具体服务的rest接口
    @RequestMapping("/us1er/{id}")
    String user(@PathVariable("id") int id);
}
