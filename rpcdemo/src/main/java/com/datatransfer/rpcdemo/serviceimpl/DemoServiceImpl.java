package com.datatransfer.rpcdemo.serviceimpl;

import com.datatransfer.rpcdemo.service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        while (true){
            System.out.println("wait");
            try{
                Thread.sleep(30000);

            }catch (Exception ex){

            }
        }
    }
}