package com.hnu;

import java.util.LinkedList;
import java.util.List;




public class Main {


    private LinkedList<Integer> ls;

    public LinkedList<Integer> getLs() {
        return ls;
    }

    public void setLs(LinkedList<Integer> ls) {
        this.ls = ls;
    }

    public void set(int n, boolean f)
    {
        if(f==true)
        this.ls.set(n,1);
        else
            ls.set(n,0);

    }
    public int get(int n)
    {
        return this.ls.get(n);

    }

    public static void main(String[] args) {
        Main main = new Main();

        main.ls.add(1);
        main.ls.add(1);
        main.ls.add(1);
        main.set(1,true);
        main.set(0,false);

    }



}
