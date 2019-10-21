package com.zch.myfeigin;

import com.zch.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Feigin02Factory implements FallbackFactory<Feigin02> {
    @Override
    public Feigin02 create(Throwable throwable) {
       return new Feigin02() {
           @Override
           public List<User> getall() {
               List<User> res = new ArrayList<User>();
               res.add(new User(-100,new Date()));
               return res;
           }

           @Override
           public User user(int id) {
               return new User(-101,new Date());
           }
       };
    }
}
