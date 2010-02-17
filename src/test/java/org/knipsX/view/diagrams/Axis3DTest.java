package org.knipsX.view.diagrams;

import static org.junit.Assert.*;

import org.junit.Test;

public class Axis3DTest {	
	
	@Test
	public void variableMaxReportSpace() {
		// Test with buffered range disabled
		JAbstract3DView.useBufferRange = false;
		Axis3D axis = new Axis3D();
		double epsilon = 0.00000001d;		
		
		double maxReportSpace = 10;
		double minReportSpace = 0;	
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		
		assertEquals("Axis space and reportspace should be identical (10)", 10, axis.getAxisSpace(maxReportSpace), epsilon);
		
		// Alter the parameters
		minReportSpace = 0;
		maxReportSpace = 100;			
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and reportspace should be identical (100)", 100, axis.getAxisSpace(maxReportSpace), epsilon);
		
		// Try some insane large value. Note that this case might actually occur, depending on the input data
		minReportSpace = 0;
		maxReportSpace = Double.MAX_VALUE;			
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and reportspace should be identical (Double.MAX_VALUE)", Double.MAX_VALUE, axis.getAxisSpace(maxReportSpace), epsilon);
		
	
		// Set the axis space and the report space to value of zero
		maxReportSpace = 0;
		minReportSpace = 0;
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and report space should be zero", 0, axis.getAxisSpace(maxReportSpace), epsilon);		
	}
	
	
	@Test
	public void reveresMinAndMax() {
		// Test with buffered range disabled
		double epsilon = 0.00000001d;	
		Axis3D axis = new Axis3D();		
		JAbstract3DView.useBufferRange = false;		
		axis.setReportSpace(200, -200);		
		assertEquals("Max report space should be the bigger value ", 200, axis.getMaxReportSpace(), epsilon);		
	}
	
	@Test
	public void catchException() {
		Axis3D axis = new Axis3D();
		try {
			axis.getAxisSpace(10);			
		} catch (ArithmeticException e) {
			return;
		}
		
		assertTrue("Exception wasn't caught", false);		
	}

}
