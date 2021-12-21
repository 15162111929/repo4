package com.itheima.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
@Component
//自定义路由断言工厂类
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    public static final String DATETIME_KEY = "datetime";

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
return Arrays.asList("minAge","maxAge");
    }

    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
return new Predicate<ServerWebExchange>() {
    @Override
    public boolean test(ServerWebExchange serverWebExchange) {
        String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
if(StringUtils.isNotEmpty(ageStr)){
    int age= Integer.parseInt(ageStr);
    if (age< config.getMaxage() && age> config.getMinAge()){
        return true;

    }
    else{ return false;}

}
return false;

    }
    };
    }




    //配置类，用于接受参数
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private int maxage;

    }
}
