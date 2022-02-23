package com.hnu;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 *
 * If you define many classes, but you must have a class named Main and a public property.
 * The Main class should be the only public class.
 * The Main class must contain a static method (function) named "main" 
 * Do not add any package, like "package main"
 *
 * The TestCase is shown below
 * Input : 1 2
 * Output : 3
 */

class 数组加1 {
	
	
	
	public static void main(String[] args) {
		
		//int [ ] d =new int []{1,2,3};

		int h=0;
		int sum=0;
		LinkedList<Integer> a=new LinkedList<>();
		a.add(9);
		a.add(9);
		a.add(9);
		sum = a.get(a.size() - 1) + 1;
		h=sum/10;
		a.set(a.size() - 1,sum%10)
		;
		for( int i=a.size()-2;i>=0;i--)
		{
			sum=a.get(i)+h;
			h=0;
			a.set(i,sum%10);
			//d[i]=sum%10;
			h=sum/10;
			sum=0;
		}
		if(h!=0)
			a.addFirst(h);

		System.out.println( Arrays.toString(a.toArray()) );
		
	}
}