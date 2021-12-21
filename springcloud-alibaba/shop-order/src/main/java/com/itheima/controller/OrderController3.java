package com.itheima.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import com.itheima.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Slf4j
public class OrderController3 {
    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号的下单请求，接下来调用商品微服务查询此商品信息",pid);
        Product product =productService.findByPid(pid);

        try {
            Thread.sleep(20001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
//        orderService.createOrder(order);
        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;

    }
    @RequestMapping("/order/message")
    public String message(){
         return orderServiceImpl3.message("lisi");

    }
    @RequestMapping("/order/message1")
    public String message1(){
        orderServiceImpl3.message1();
        return "message1";
    }
    @RequestMapping("/order/message2")
    public String message2(){
        orderServiceImpl3.message1();
        return "message2";
    }
    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age){
        return "message3"+name+age;
    }
}
