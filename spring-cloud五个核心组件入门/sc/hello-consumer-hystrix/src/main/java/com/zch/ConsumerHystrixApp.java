package com.zch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients  //创建应用主类，并通过@EnableFeignClients注解开启Spring Cloud Feign的支持功能
@EnableCircuitBreaker // 熔断 hystrix例子，启动类使用@EnableCircuitBreaker注解
public class ConsumerHystrixApp {


    //@Bean 应用在方法上，用来将方法返回值设为为bean
    @Bean
    @LoadBalanced  //@LoadBalanced实现负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApp.class, args);
    }
}
