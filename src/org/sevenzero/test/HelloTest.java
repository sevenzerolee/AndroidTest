package org.sevenzero.test;

import android.test.AndroidTestCase;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-11-12
   *
 */
public class HelloTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testHello() {
		System.out.println("hello");
	}

}
