package com.zch.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 getRoute()方法：指定对某个服务访问出问题时进行熔断处理。
 fallBackResponese()：自定义返回值来处理错误请求。
 */

@Component
public class ZuulFallBack implements ZuulFallbackProvider {
    @Override
    public String getRoute() {
        return "consumer";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        /**
         * 客户端向网关发送服务成功，网关向api服务请求失败，不应该把api的404 500 等问题抛给客户端
         * 网关和api服务对客户端来说都是黑盒子。
         * @return
         * @throws IOException
         */

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            /**
             * 微服务出现宕机后，客户端再次请求就会返回fallback中的预设值
             * @return
             * @throws IOException
             */

            @Override
            public InputStream getBody() throws IOException {
                //服务异常时，输出此处内容，并打印错误日志
                return new ByteArrayInputStream("the service unable use".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };

    }
}
