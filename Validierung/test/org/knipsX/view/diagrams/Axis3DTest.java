package org.knipsX.view.diagrams;

import static org.junit.Assert.*;

import org.junit.Test;

public class Axis3DTest {

	@Test
	public void testGetAxisSpace() {
		// Test with buffered range disabled
		JAbstract3DView.useBufferRange = false;
		Axis3D axis = new Axis3D();
		double epsilon = 0.00000001d;		
		
		double maxReportSpace = 10;
		double minReportSpace = 0;	
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		
		assertEquals("Axis space and reportspace should be identical (10)", maxReportSpace, axis.getAxisSpace(maxReportSpace), epsilon);
		
		// Alter the parameters
		maxReportSpace = 100;			
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and reportspace should be identical (100)", maxReportSpace, axis.getAxisSpace(maxReportSpace), epsilon);
		
		// Try some insane large value. Note that this case might actually occur, depending on the input data
		maxReportSpace = Double.MAX_VALUE;			
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and reportspace should be identical (Double.MAX_VALUE)", maxReportSpace, axis.getAxisSpace(maxReportSpace), epsilon);
		
	
		// Set the axis space and the report space to value of zero
		maxReportSpace = 0;
		minReportSpace = 0;
		axis.setAxisSize(maxReportSpace);
		axis.setReportSpace(minReportSpace, maxReportSpace);
		assertEquals("Axis space and report space should be zero", maxReportSpace, axis.getAxisSpace(maxReportSpace), epsilon);
		
	}

}
