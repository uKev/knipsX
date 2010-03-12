package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PictureSetTest {

	static Picture pic1 = null;
	static Picture pic2 = null;
	static PictureSet pcs1 = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//File image1 = new File();
		
//		pic1 = new Picture("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder/DSC00596.JPG", true);
//		pic2 = new Picture("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder/DSC00964.JPG", true);
		
		pic1 = new Picture("testbilder/DSC00596.JPG", true);
		pic2 = new Picture("testbilder/DSC00964.JPG", true);
		
		pcs1 = new PictureSet("TestSet");
		pcs1.add(pic1);
		pcs1.add(pic2);
		
	}

	@Test
	public void testPictureSetGetName() {
		assertEquals("TestSet", pcs1.getName());
	}

	@Test
	public void testPictureSetPictureSetString() {
		assertNotNull(pic1);		
	}
	
	@Test
	public void testPictureSetCopy() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		assertEquals(2, pcs2.getItems().size());
		
	}
	
	@Test
	public void testPictureSetCopyName() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		assertEquals("TestSet2", pcs2.getName());
		
	}
	
	@Test
	public void testPictureSetRemove() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		pcs2.remove(pic1);
		assertEquals(1, pcs2.getItems().size());
		
	}
	
	@Test
	public void testPictureSetHasNext() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		//pcs2.add(pic2);
		assertTrue(pcs2.hasNext());
	
		
	}
	
	@Test
	public void testPictureSetNext() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		assertNotNull(pcs2.next());
		
	}
	
	@Test 
	public void testPictureCompare() {
		PictureSet pcs2 = new PictureSet(pcs1, "TestSet2");
		assertEquals(1, pcs2.compareTo(pcs1));
		
	}

}
