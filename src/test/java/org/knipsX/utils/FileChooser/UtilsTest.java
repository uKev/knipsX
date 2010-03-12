package org.knipsX.utils.FileChooser;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void getPNGExtension() {
		
		// Test a simple file name
		assertEquals("File extension should be png", "png", Utils.getExtension("test.png")); 
		
		// Test a file name containing spaces
		assertEquals("File extension should be png", "png", Utils.getExtension("test bla.png")); 
		
		// Test a file name containing 加入营销计划
		assertEquals("File extension should be png", "png", Utils.getExtension("加入营销计划.png"));

	}
	
	@Test
	public void getEmptyExtension() {
		assertEquals("File extension should be an empty string", "", Utils.getExtension("test test"));
		
		assertEquals("File extension should be an empty string", "", Utils.getExtension("加入营销计划")); 
	}
	
	@Test
	public void multipleExtension() {
		assertEquals("File extension should be the last extension (jpg)", "jpg", Utils.getExtension("test test.png.jpg"));
		
		assertEquals("File extension should be the last extension (ps1)", "ps1", Utils.getExtension("加入营销计划.rv1.ps3.ps2.ps1")); 
	}
	
	@Test
	public void trimExtension() {
		assertEquals("File extension should be jpg", "jpg", Utils.getExtension("test test.png.jpg                  "));
		
		assertEquals("File extension should be png", "png", Utils.getExtension("test test.png.            png                  "));
		
	}
	
	

}
