package com.datatransfer;

import com.datatransfer.po.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(basePackages = "com.datatransfer")
public class AppBootStrap {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppBootStrap.class);
        Student student = (Student) context.getBean("student");
        System.out.println(student.getAge());
    }
}