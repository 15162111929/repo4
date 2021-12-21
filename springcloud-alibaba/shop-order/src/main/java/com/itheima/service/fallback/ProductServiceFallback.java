package com.itheima.service.fallback;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-100);
        product.setPname("远程调用商品微服务出现异常了，进入了容错逻辑");
        return product;
    }
}
