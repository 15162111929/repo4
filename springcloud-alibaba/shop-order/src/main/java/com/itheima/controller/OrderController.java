package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

//使用ribbon和用feigh替代rest template实现负载均衡
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
        Product product =productService.findByPid(pid);
if(product.getPid()== -100){
    Order order = new Order();
    order.setOid(-100L);
    order.setPname("下单失败");
    return order;
}
        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);
        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;

    }
}
//负载均衡
//@RestController
//@Slf4j
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        int index = new Random().nextInt(instances.size());
//
//        ServiceInstance instance = instances.get(index);
//
//        Product product =restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/" + pid, Product.class);
//
//        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//
//    }
//}
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private RestTemplate restTemplate;
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
//        Product product =restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
//
//        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//
//    }
//}
//@RestController
//@Slf4j
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance instance = instances.get(0);
//
//        Product product =restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/" + pid, Product.class);
//
//        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//
//    }
//}
//@RestController
//@Slf4j
//public class OrderController {
//    //    @Autowired
////    private ProductService productService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
//        Product product =restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
//
//        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//
//    }
//}