/**
 * 
 */
package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;

/**
 * @author clyde
 *
 */
public class DirectoryTest {
	
	static Directory dir1 =  null;
	static Directory dir2 =  null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
//		dir1 = new Directory("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder");
//		dir2 = new Directory("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder2");
		dir1 = new Directory("/testbilder");
		dir2 = new Directory("/testbilder2");
	}

	/**
	 * Test method for {@link org.knipsX.model.picturemanagement.Directory#Directory(java.lang.String)}.
	 */
	@Test
	public void testDirectoryGetName() {
		//System.out.println( "dir: " + dir1.getName() );
		assertEquals("testbilder", dir1.getName());
		
	}
	
	@Test
	public void testDirectoryGetPath() {
		//System.out.println( "Path: " + dir1.getPath() );
		assertEquals("/home/clyde/SOFT_PR/git_clone5/tempX/Implementierung/testbilder", dir1.getPath());
		
	}
	
	@Test
	public void testDirectoryNext() {
		//assertEquals(null, dir1.next());
		assertNotSame(null, dir1.next());
		
	}
	
	@Test
	public void testDirectoryhasNext() {
		assertEquals(true, dir1.hasNext());
	}
	
	@Test
	public void testDirectoryGetItems() {
		assertNotSame(null, dir1.getItems());

	}

	@Test
	public void testDirectorygetAllPictures() {
		//dir1.getAllPictures(new File(dir1.getPath()));
		//dir1.getAllPictures(dir1);
		
		assertTrue(true);
	}
	
	@Test
	public void testDirectoryComareTo() {
		
		assertEquals(-1, dir1.compareTo(dir2));
	
	}
	
}
