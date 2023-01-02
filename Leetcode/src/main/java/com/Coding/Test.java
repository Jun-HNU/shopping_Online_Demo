package com.Coding;

public class Test {

    /*private static final Test singleTone=new Test();

    public Test() {
    }

    public static Test getINSTANCE()
    {
        return singleTone;
    }
*/



    private static volatile  Test INSTANCE=null;

    public Test() {
    }

    public  synchronized Test getINSTANCE()
    {
        if(null==INSTANCE) INSTANCE=new Test();
        return INSTANCE;
    }
}
