package com.datatransfer;

import com.datatransfer.rpcdemo.service.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcTest {


    @Test
    public void consumer() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
        System.in.read();
    }
}