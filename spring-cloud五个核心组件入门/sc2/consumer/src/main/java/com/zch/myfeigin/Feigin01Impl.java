package com.zch.myfeigin;

import com.zch.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Feigin01Impl implements Feigin01{

    //绑定具体的restful接口,/all 是生产者里的路由

    public List<User> getall(){
        List<User> list = new ArrayList<User>();
        list.add(new User(-1,new Date()));
        return list;
    }

    public User user(@PathVariable("id") int id){
        return new User(-2,new Date());
    }
}
