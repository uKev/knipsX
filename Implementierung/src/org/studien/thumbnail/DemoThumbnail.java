package org.studien.thumbnail;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class DemoThumbnail extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 403168278892206655L;
	static BufferedImage image;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedImage rawImage = ImageIO.read( new File("Implementierung/src/org/studien/thumbnail/schwarzwald-Urlaub.jpg") ); 
		int percent = 50;
		BufferedImage thumbnailed = (BufferedImage) rawImage.getScaledInstance(
		  (rawImage.getWidth() * percent) / 100, 
		  (rawImage.getHeight() * percent) / 100, 
		  Image.SCALE_SMOOTH ); 
		setImage(thumbnailed);

	}
	 public static void setImage( BufferedImage imageInput ) 
	  { 
	    image = imageInput; 
	    setPreferredSize( new Dimension(image.getWidth(), image.getHeight()) ); 
	    repaint(); 
	    invalidate(); 
	  } 
	
	
	protected void paintComponent( Graphics g ) 
	  { 
	    if ( image != null ) 
	      g.drawImage( image, 0, 0, this ); 
	  } 
}
