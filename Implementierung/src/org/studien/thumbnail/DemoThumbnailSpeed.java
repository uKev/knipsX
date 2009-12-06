package org.studien.thumbnail;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DemoThumbnailSpeed {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String tp = DemoThumbnailQuality.tPath;
		
		long zstVorher;
		long zstNachher;

		
		// start time measurement
		zstVorher = System.currentTimeMillis();
		
		BufferedImage rawImage = ImageIO.read( new File(tp + "10MegTestPic.jpg") ); 
		// scale and save the thumbnail
		ImageIO.write(DemoThumbnailQuality.getThumbOf(rawImage, 200, Image.SCALE_FAST), "jpg", new File(tp + "10MegTestPic_thumb_default.jpg"));
		
		// scale and save the thumbnail in 2 steps
		//BufferedImage temp = DemoThumbnailQuality.getThumbOf(rawImage, 200, Image.SCALE_FAST);
		//ImageIO.write(temp, "jpg", new File(tp + "10MegTestPic_thumb_default.jpg"));
		

		// stop time measurement
		zstNachher = System.currentTimeMillis();
		System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)) + " milisec");
		System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)/1000) + " sec");

	}

}
