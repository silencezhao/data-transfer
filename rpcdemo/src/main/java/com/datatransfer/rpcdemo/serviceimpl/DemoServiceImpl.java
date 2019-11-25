package com.datatransfer.rpcdemo.serviceimpl;

import com.datatransfer.rpcdemo.service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "你好:"+name;
    }
}