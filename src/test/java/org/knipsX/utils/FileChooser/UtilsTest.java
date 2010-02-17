package org.knipsX.utils.FileChooser;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testGetExtension() {
		
		// Test a simple file name
		assertEquals("File extension should be png", "png", Utils.getExtension("test.png")); 
		
		// Test a file name containing spaces
		assertEquals("File extension should be png", "png", Utils.getExtension("test bla.png")); 
		
		// Test a file name containing 加入营销计划
		assertEquals("File extension should be png", "png", Utils.getExtension("加入营销计划.png")); 
		

	}

}
