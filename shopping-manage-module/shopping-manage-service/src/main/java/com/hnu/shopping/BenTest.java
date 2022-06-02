package com.hnu.shopping;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class BenTest {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BenTest.class);

        context.register(BenTest.class);
        AbstractBeanDefinition UserBeandefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //UserBeandefinition.setBeanClass(User.class);
        UserBeandefinition.setBeanClass(UserFactoryBean.class);
        context.registerBeanDefinition("user",UserBeandefinition);

        //context.getBean(User.class).fun();
        UserFactoryBean myFactoryBean = (UserFactoryBean) context.getBean("&user");
        User user = null;
        try {
            user = (User) myFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.fun();

        //context.refresh();
    }

}
