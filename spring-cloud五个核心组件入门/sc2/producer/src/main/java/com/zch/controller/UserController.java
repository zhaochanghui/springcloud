package com.zch.controller;

import com.netflix.discovery.EurekaClient;
import com.zch.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("/all")
    public List<User> all()
    {
        List<User> res = new ArrayList<User>();
        res.add(new User(10,new Date()));
        res.add(new User(11,new Date()));
        res.add(new User(12,new Date()));

        return res;
    }

    @RequestMapping("/info")
    public String info()
    {
        return "生产者的Info方法，url也是info";
    }


    @RequestMapping("/user/{id}")
    public User user(@PathVariable("id") int id)
    {
        return new User(id,new Date());
    }
}
