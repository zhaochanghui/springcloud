package com.zch.service;

import com.zch.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Component
public class MyFFact implements FallbackFactory<MyFhFactService> {
    @Override
    public MyFhFactService create(Throwable throwable) {
            return new MyFhFactService() {
                @Override
                public String hello() {
                    return "fallfactory--hello方法";
                }

                @Override
                public String user(int id) {
                    User user = new User();

                    user.setId(-999);
                    user.setName("工厂fallback--user方法");
                    user.setDate(new Date());
                    return user.toString();
                }
            };
    }
}
