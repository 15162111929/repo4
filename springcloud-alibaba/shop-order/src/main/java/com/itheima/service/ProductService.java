package com.itheima.service;

import com.itheima.domain.Product;
import com.itheima.service.fallback.ProductServiceFallback;
import com.itheima.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        value = "service-product",
//        fallback = ProductServiceFallback.class
         fallbackFactory = ProductServiceFallbackFactory.class)
public interface ProductService {
    @RequestMapping("/product/{pid}")
 Product findByPid(@PathVariable("pid") Integer pid);
}
