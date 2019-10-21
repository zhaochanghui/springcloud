package com.zch.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; // 指定过滤器类型
    }

    @Override
    public int filterOrder() {
        return 0; // 过滤器顺序，数字越小越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; // 是否使用该过滤器。
    }

    // 过滤器具体执行的操作
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestUri = request.getRequestURI();
        System.out.println("请求的URI：{"+requestUri+"}");
        return null;
    }


}
