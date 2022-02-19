package com.hnu.设计模式.动态代理;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class 根据接口生成对象执行方法 {

    @Test
    public void test1() {

        ClassLoader loader = this.getClass().getClassLoader();
        Class[] classes = {Human.class, Car.class};
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("来了，老弟~");
                return null;
            }
        };

        Human human = (Human) Proxy.newProxyInstance(loader, classes, h);
        human.male();
        human.toString();
        human.getClass();
    }

}

interface Human {
    Object male();
}

interface Car {
    Object bmw();
}
