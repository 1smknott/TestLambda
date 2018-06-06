package com.steve.test;

import java.applet.Applet;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.Vector;

public abstract class Test {

	enum x {
		a, b, c;
		static enum z {
			t, u, v;
		}
	}
	public String w1 = "";
	Test(String s1) {
	    	
	}
	
	public static void main(String[] args) { // test
		// TODO Auto-generated method stub

		int x = 2;
		
		switch(x) {
		
		}
		Object[] s3 = new Object[3];
		
		if (s3[2] == null) {
			System.out.println((128 >> 4));
		}
		
		
		int[] oldArray = { 3, 5, 7, 9}; 
		int[] newArray = { 2, 4, 6, 8, 9, 7, 5, 3}; 
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		
		System.out.println(newArray.toString());
		
		System.out.println(Math.ceil(-82.4));
		System.out.println(Math.pow(-6,2));
		for (int i : newArray) {
			
		}
	    
	}
	
	

	public void test(String s) {
		String s1 = "";
	}
	
	 class test1 {
		String s2 = "";
	}
}
