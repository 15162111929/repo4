package com.itheima.filters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
//   过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(!StringUtils.equals("admin",token)){
            System.out.println("鉴权失败");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
    //调用chain.filter继续向下游执行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
