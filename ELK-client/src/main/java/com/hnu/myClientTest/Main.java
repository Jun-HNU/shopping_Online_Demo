package com.hnu.myClientTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.security.PublicKey;

//@SpringBootTest
//@ComponentScan("com.hnu.myClientTest")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        int bean = (int) context.getBean("getBean");
        System.out.println(bean);

    }

}
