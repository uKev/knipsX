package org.studien.thumbnail;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class DemoThumbnail {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read( new File("Implementierung/src/org/studien/thumbnail/schwarzwald-Urlaub.jpg") ); 
		int percent = 50;
		Image scaled1 = image.getScaledInstance(
		  (image.getWidth() * percent) / 100, 
		  (image.getHeight() * percent) / 100, 
		  Image.SCALE_SMOOTH ); 
		Image scaled2 = image.getScaledInstance( 
		  Toolkit.getDefaultToolkit().getScreenSize().width, 
		  Toolkit.getDefaultToolkit().getScreenSize().height, 
		  Image.SCALE_SMOOTH );

	}

}
