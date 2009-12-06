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
	static private long zstVorher;
	static private long zstNachher;
	static private long zstGesamt = 0;
	public static void main(String[] args) throws IOException {
		String tPath = "Implementierung/src/org/studien/thumbnail/";
		
		// start time measurement
		zstVorher = System.currentTimeMillis();
		measurepoint("Anfang:");
		
		BufferedImage rawImage = ImageIO.read( new File(tPath + "10MegTestPic.jpg") ); 
		measurepoint("Eingelesen");
		// scale and save the thumbnail
		//ImageIO.write(DemoThumbnailQuality.getThumbOf(rawImage, 200, Image.SCALE_FAST), "jpg", new File(tPath + "10MegTestPic_thumb_default.jpg"));
		
		// scale and save the thumbnail in 2 steps
		
		Image temp[] = new Image[60];
		
		for(int i=0; i<20;i++){
			temp[i] = getThumbOf(rawImage, 200, Image.SCALE_FAST);		
			measurepoint("Thumbnail FAST Nr."+i+" ");
		}
		for(int i=20; i<40;i++){
			temp[i] = getThumbOf(rawImage, 300, Image.SCALE_DEFAULT);
			measurepoint("Thumbnail DEFAULT Nr."+i+" ");
		}
		for(int i=40; i<60;i++){
			temp[i] = getThumbOf(rawImage, 5000, Image.SCALE_SMOOTH);
			measurepoint("Thumbnail SMOOTH Nr."+i+" ");
		}
		/*ImageIO.write(temp, "jpg", new File(tPath + "10MegTestPic_thumb_fast.jpg"));
		
		 */
		measurepoint("Gesamtzeit");
		// stop time measurement
		zstNachher = System.currentTimeMillis();
	}
	public static Image getThumbOf(BufferedImage bImage, int maxWidthOrHight, int hints){
		int width = bImage.getWidth();
		int height = bImage.getHeight();
		
		if (width>=height){
			width = maxWidthOrHight;
			height = -1; // negative Höhe bedeutet, dass diese Höhe dem Seitenverhältnis entsprechend an die neue Breite angepasst wird.
		}else{
			width = -1; // negative breite: Breite wird dem Seitenverhältnis entsprechend an die neue Höhe angepasst.
			height = maxWidthOrHight;
		}
		//measurepoint("Neue Höhe und Breite berechnet");

		Image thumbnailed = bImage.getScaledInstance(width, height, hints);
		//measurepoint("Skalierte Instanz zurückgeliefert");

		/*BufferedImage bThumb = new BufferedImage(thumbnailed.getWidth(null),thumbnailed.getHeight(null), bImage.getType());
		measurepoint("Buffered Image erzeugt");

		
		bThumb.createGraphics().drawImage(thumbnailed, 0, 0, null);
		measurepoint("Skalierte Instanz in Buffered Image geschrieben");
		*/
		return thumbnailed;
	}
	static void measurepoint(String comment){
		zstNachher = System.currentTimeMillis();
		long zstDiff = zstNachher - zstVorher - zstGesamt;
		zstGesamt += zstDiff;
		System.out.println(comment + ":");
		System.out.println("Zeit benötigt (letzter Schritt): " + ((zstDiff)) + " milisec");
		System.out.println("Zeit benötigt (letzter Schritt): " + ((zstDiff)/1000) + " sec");
		System.out.println("Zeit benötigt (gesamt): " + ((zstGesamt)) + " milisec");
		System.out.println("Zeit benötigt (gesamt): " + ((zstGesamt)/1000) + " sec");
		System.out.println();
		zstVorher = System.currentTimeMillis() - zstGesamt;
	}

}
