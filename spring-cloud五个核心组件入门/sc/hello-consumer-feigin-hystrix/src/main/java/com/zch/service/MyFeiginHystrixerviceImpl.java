package com.zch.service;

import com.zch.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Component
public class MyFeiginHystrixerviceImpl implements MyFeiginHystrixervice {

    //绑定具体服务的rest接口
    @Override
    public String hello(){
        return "feigin-hystrix;;;hello";
    }

    //绑定具体服务的rest接口
    @Override
    public String user(@PathVariable("id") int id){
        User user = new User();
        user.setId(-1000);
        user.setName("出错");
        user.setDate(new Date());
        return user.toString();
    }
}
