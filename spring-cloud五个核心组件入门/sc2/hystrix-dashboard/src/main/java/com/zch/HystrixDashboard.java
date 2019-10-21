package com.zch;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashboard {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard.class, args);
    }
}
