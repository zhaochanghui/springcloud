package com.zch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient  让eureka服务器 实现服务注册和发现
 */

@EnableDiscoveryClient
@SpringBootApplication
public class Producer {

    public static void main(String[] args) {
        SpringApplication.run(Producer.class,args);
    }
}
