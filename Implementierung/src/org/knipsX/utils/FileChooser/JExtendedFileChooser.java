package org.knipsX.utils.FileChooser;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JExtendedFileChooser {
	
	private static JFileChooser filechooser;
	private static String lastSaveDir = "";
	private static String lastOpenDir = "";
	

    public static void selectDirectoriesAndImages() {  
    	initialize();
    	
    	filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	filechooser.setMultiSelectionEnabled(true);    	
    	filechooser.setCurrentDirectory(new File(lastOpenDir));
    	
        //Show it.
        int returnVal = filechooser.showDialog(filechooser,"Hinzufügen");
        
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile(); 
            lastOpenDir = file.getAbsolutePath();
        }
        
        //Reset the file chooser for the next time it's shown.
        filechooser.setSelectedFile(null);        

    }
    
    private static void initialize() {
    	if(filechooser == null) {
    		filechooser = new JFileChooser();
    		
    	    //Add a custom file filter and disable the default
    	    //(Accept All) file filter.
        	filechooser.addChoosableFileFilter(new ImageFilter());
        	filechooser.setAcceptAllFileFilterUsed(false);               
           
    	    //Add the preview pane.
        	filechooser.setAccessory(new ImagePreview(filechooser));
        	
//    		try {
//    			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//    		} catch (Exception exc) {
//    			System.err.println("Error loading  Look and Feel: " + exc);
//    		}
    		
    	}   	

    }
    
    public static void saveBufferedImage(BufferedImage imageToBeSaved) {

    	initialize();
    	
    	filechooser.setMultiSelectionEnabled(false);  
    	filechooser.setCurrentDirectory(new File(lastSaveDir));
    	
        //Handle save button action.
        int returnVal = filechooser.showSaveDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();     
            
            lastSaveDir = file.getAbsolutePath();
            
    		File outputfile = new File(file.getAbsolutePath().replaceAll(".png", "").replaceAll(".png", "")+".png");
    		
    		
    		// Uncomment for debugging purposes 
    		//new ImageDisplayer(imageToBeSaved);
    		
    		try {
    			ImageIO.write(imageToBeSaved, "png", outputfile);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
            
        }
    }
    

}
