package com.steve.test;

public class TestLambda {

	static Lamb l = () -> System.out.println("hello");
	
	public static void main(String[] args) throws Exception {
		l.foo();
		
		String s = "yyy";
		
		System.out.println(TestLambda.isPalindrome("Deleveled"));
	}
	
	public static boolean isPalindrome(String word) throws Exception  {
	    
	    
	    return (word.toUpperCase().equals(new StringBuffer(word).reverse().toString().toUpperCase()));
	    
	    
	}
	
}




interface Lamb {
	void foo();
}
