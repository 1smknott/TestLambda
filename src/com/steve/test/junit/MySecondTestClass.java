package com.steve.test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class MySecondTestClass {

	@Test
	public void MySecondMethod() {
		//fail("Not yet implemented");
		String s = "";
		assertNotNull(s);
	}
	
	@Test
	public void MySecondMethod2(String s) {
		//fail("Not yet implemented");
		
		assertNotNull(s);
	}

}
