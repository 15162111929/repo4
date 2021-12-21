package com.itheima.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Service
@Slf4j
public class OrderServiceImpl3 {
    @SentinelResource("message1")
    public String message1(){
        return "message1";
    }
    @SentinelResource(
            value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderServiceImpl3Fallback.class,
            fallback = "fallback"
    )
    public String message(String name){
        return "message";
    }
//    public String blockHandler(String name, BlockException e){
//        log.error("触发了BlockException，内容为{}",e);
//    return "Blockexception";
//    }
//    public String fallback(String name,Throwable e){
//        log.error("触发了Throwable，内容为{}",e);
//        return "Throwable";
//    }
}
