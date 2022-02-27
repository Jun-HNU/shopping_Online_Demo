package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person{
   void buy(String str);
}


class Man implements Person{


    @Override
    public void buy( String str)
    {
        System.out.println("buy some thing:"+str);
    }

}


class WoMan implements Person{


    @Override
    public void buy( String str)
    {
        System.out.println("WoMan buy some thing："+str);
    }

}

interface Car{
    void fun();
}


class bigCar implements Car {
    public void fun()
    {
        System.out.println("it is a big car");
    }
}


class ProxCompany implements InvocationHandler {

      Object target;//目标对象的引用

    public ProxCompany(Object o) {
        this.target = o;

    }
//根据特定的类生成对应代理对象,代理对象，代理对象代替目标对象执行函数。
    public Object getSpecifyTargetProx(){
        Object newProxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

        return  newProxyInstance;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("befor");

        Object invoke = method.invoke(target,args);

        System.out.println("after");
        return invoke ;
    }


    }



public class Demo {
    public static void main(String[] args) {

        Person man = new Man();
        //根据特定的类生成对应代理对象
        Person manInstance = (Person)new ProxCompany(man).getSpecifyTargetProx();
        manInstance.buy("man");

        Person woMan = new WoMan();
        Person woManInstance = (Person)new ProxCompany(woMan).getSpecifyTargetProx();
        woManInstance.buy("woman");

        Car bigCar=new bigCar();
        Car bigCarInstance = (Car)new ProxCompany(bigCar).getSpecifyTargetProx();
        bigCarInstance.fun();

    }



}
