package org.knipsX.utils;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class ConverterTest {

	@Test
	public void testObjectToString() {
		
		String testString = "test";
		assertEquals("String should stay the same", testString, Converter.objectToString(testString));
		
		
		Integer five = 5;
		assertEquals("String should be 5 of type String", "5", Converter.objectToString(five));
		
		
		Object[] sequence = new Object[] {1,2,3,4,5,6};
		assertEquals("String should be 1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6", Converter.objectToString(sequence));
	}

}
