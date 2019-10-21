package com.zch.myfeigin;

import com.zch.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="producer",fallbackFactory = Feigin02Factory.class)
public interface Feigin02 {

    //绑定具体的restful接口,/all 是生产者里的路由
    @RequestMapping("/all")
    List<User> getall();

    @RequestMapping("/user/{id}")
    User user(@PathVariable("id") int id);
}
