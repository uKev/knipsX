/**
 * 
 */
package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.utils.ExifParameter;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * @author clyde
 *
 */
public class PictureTest {

	static Picture pic1 = null;
	static Picture pic2 = null;
	static BufferedImage img1 = null;
	static Image img2 = null;
	//static ArrayList abc = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	//	pic1 = new Picture("/home/clyde/SOFT_PR/knipsX/Implementierung/testbilder/DSC00596.JPG", true);
	//	pic2 = new Picture("/home/clyde/SOFT_PR/knipsX/Implementierung/testbilder/DSC00964.JPG", false);// not active
		
	    
	        pic1 = new Picture("src/test/java/org/knipsX/model/picturemanagement/DSC00596.JPG", true);
	        pic2 = new Picture("src/test/java/org/knipsX/model/picturemanagement/DSC00964.JPG", false); // not active
	        
		/*pic1 = new Picture("DSC00596.JPG", true);
		pic2 = new Picture("DSC00964.JPG", false);// not active
*/		
	}

	/**
	 * Test method for {@link org.knipsX.model.picturemanagement.Picture#Picture(java.lang.String, boolean)}.
	 */
	@Test
	public void testPictureName() {
		assertEquals("DSC00596.JPG", pic1.getName());
		
		
	}
	
	@Test
	public void testPicturePath() {
		//System.out.println(pic1.getPath());
		//assertTrue("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder/DSC00596.JPG", pic1.getPath());
		assertTrue(!(pic1.getPath() == null));
		
		//assertNotEquals("", pic1.getItems().size());
	}
	
	@Test
	public void testPictureExif() {
		//System.out.println(pic1.getExifParameter(ExifParameter.CAMERAMODEL));
		assertEquals("W890i", pic1.getExifParameter(ExifParameter.CAMERAMODEL));
	}
	
	@Test
	public void testPictureGetItems() {
		//assertNotEquals(pic1.getItems().size());
		assertTrue( pic1.getItems().size() != 0 );
		
	}
	
	@Test
	public void testPictureInitializeThumbnails() {
		assertEquals(true, pic1.initThumbnails());
	}
	
	@Test
	public void testPictureGetBighumbnail() {
		img1 =  pic1.getBigThumbnail();
		assertEquals(200, img1.getHeight());
		
	}
	
	@Test
	public void testPictureGetSmallhumbnail() {
		img2 =  pic1.getSmallThumbnail();
		//System.out.println(img2.getWidth(observer));
		
		//assertEquals(200, img2.getHeight());
		
	}
	
	@Test
	public void testPictureHasExifParameter() {
		//assertTrue(pic1.hasExifKeyword("Blitz") || pic1.hasExifKeyword("Flash"));
		
			
	}

	@Test
	public void testPicturehasMinOneKeywordOf() {
		//ArrayList abc = new ArrayList({"Blitz", "ISO"});
		//assertTrue(pic1.hasMinOneKeywordOf(filterKeywords));
		assertTrue(true);
	}
	
	@Test
	public void testPicturehasAllKeywords() {
		//assertTrue(pic1.hasAllKeywords(new String("ISO")));
	}
	
	@Test
	public void testPictureIsActive() {
		assertFalse(pic2.isActive()); // pic2 is initialized not active
	
	}
	
	@Test
	public void testPictureCompareTo() {
		//System.out.println("paap" + pic1.compareTo(pic2));
		assertTrue(-1 == pic1.compareTo(pic2));
	}
	
	@Test
	public void testPicturegetAllExifParameter() {
		assertTrue(pic1.getAllExifParameter() != null);
	}
	
	@Test
	public void testPictureGetScaledInstance() {
		//assertTrue(pic1.getScaledInstance(img, targetWidth, targetHeight, hint, higherQuality) != null);
	}
	
	
	
}
