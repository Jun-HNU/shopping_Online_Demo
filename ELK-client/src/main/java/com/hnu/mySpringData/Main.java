package com.hnu.mySpringData;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElasticsearchClientConfig.class);
        Object client = context.getBean("elasticsearchClien");
        System.out.println("hello");
    }
}


