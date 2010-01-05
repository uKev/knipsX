package org.knipsX.utils;

import java.io.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JFileSaver extends JFileChooser {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser fileChooser;

    public JFileSaver(BufferedImage imageToBeSaved) {        
        
        //Handle save button action.
        int returnVal = this.showSaveDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();                
            
    		File outputfile = new File(file.getAbsolutePath().replaceAll(".jpg", "").replaceAll(".jpeg", "")+".jpg");
    		
    		try {
    			ImageIO.write(imageToBeSaved, "jpg", outputfile);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
            
        }
        
    }

}
