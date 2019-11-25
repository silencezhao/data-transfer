package com.datatransfer.po;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;


@Component
@Scope("prototype")
public class Student implements Serializable {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @PostConstruct
    public void preStart(){
        System.out.println("post construct");
    }

    @PreDestroy
    public void destory(){
        System.out.println("destory");
    }
}
