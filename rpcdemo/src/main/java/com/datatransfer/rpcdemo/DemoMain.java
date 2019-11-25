package com.datatransfer.rpcdemo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.refresh();
        context.start();
        System.in.read();
    }
}