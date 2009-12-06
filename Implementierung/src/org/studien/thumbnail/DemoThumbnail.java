package org.studien.thumbnail;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class DemoThumbnail{
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String tPath = "Implementierung/src/org/studien/thumbnail/";
		BufferedImage rawImage = ImageIO.read( new File(tPath + "schwarzwald-Urlaub.jpg") ); 
		// save the thumbnail
		ImageIO.write(getThumbOf(rawImage, 200, Image.SCALE_DEFAULT), "jpg", new File(tPath + "schwarzwald-Urlaub_thumb_default.jpg"));
		ImageIO.write(getThumbOf(rawImage, 200, Image.SCALE_FAST), "jpg", new File(tPath + "schwarzwald-Urlaub_thumb_fast.jpg"));
		ImageIO.write(getThumbOf(rawImage, 200, Image.SCALE_SMOOTH), "jpg", new File(tPath + "schwarzwald-Urlaub_thumb_smooth.jpg"));
	}
	public static BufferedImage getThumbOf(BufferedImage bImage, int maxWidthOrHight, int hints){
		int width = bImage.getWidth();
		int height = bImage.getHeight();
		
		if (width>=height){
			width = maxWidthOrHight;
			height = -1; // negative Höhe bedeutet, dass diese Höhe dem Seitenverhältnis entsprechend an die neue Breite angepasst wird.
		}else{
			width = -1; // negative breite: Breite wird dem Seitenverhältnis entsprechend an die neue Höhe angepasst.
			height = maxWidthOrHight;
		}
		
		Image thumbnailed = bImage.getScaledInstance(width, height, hints);
		BufferedImage bThumb = new BufferedImage(thumbnailed.getWidth(null),thumbnailed.getHeight(null), bImage.getType());
		bThumb.createGraphics().drawImage(thumbnailed, 0, 0, null);
		return bThumb;
	}
}
