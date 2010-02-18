package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PictureSetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//File image1 = new File();
		Picture pic1 = new Picture("home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder/DSC00596.JPG", true);
	}

	@Test
	public void testPictureSetString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPictureSetPictureSetString() {
		Picture pic1 = null;
		try {
			pic1 = new Picture("home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder/DSC00596.JPG", true);
		} catch (PictureNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(pic1);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testResetIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePictureContainer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasNext() {
		fail("Not yet implemented");
	}

	@Test
	public void testNext() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
