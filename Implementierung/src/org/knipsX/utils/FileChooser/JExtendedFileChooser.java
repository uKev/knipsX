package org.knipsX.utils.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * This class is responsible for loading images and directories into the application.
 * It is also responsible for saving a buffered image into a proper format onto the
 * hard drive. It is capable of showing a thumbnail preview of the currently selected
 * image.
 * 
 * @author David Kaufman
 * 
 */
public final class JExtendedFileChooser {

    private static JFileChooser filechooser;
    private static String lastSaveDir = "";
    private static String lastOpenDir = "";

    /* the initialization routine which configures the file chooser */
    private static void initialize() {
        
        /* create a new file chooser object */
        JExtendedFileChooser.filechooser = new JFileChooser();

        /* disable default file filter */
        JExtendedFileChooser.filechooser.setAcceptAllFileFilterUsed(false);

        /* Add the preview pane */
        JExtendedFileChooser.filechooser.setAccessory(new ImagePreview(JExtendedFileChooser.filechooser));

    }

    /**
     * This method is responsible for saving a buffered image into the desired format
     * 
     * @param imageToBeSaved
     *            the image which is to be saved
     */
    public static void saveBufferedImage(final BufferedImage imageToBeSaved) {

        /* Initialize the file chooser */
        JExtendedFileChooser.initialize();

        /* Add a custom file filter */
        JExtendedFileChooser.filechooser.addChoosableFileFilter(new PNGImageFilter());        

        /* Prohibit multi selection */
        JExtendedFileChooser.filechooser.setMultiSelectionEnabled(false);

        /* Set the current directory to the last save directory */
        JExtendedFileChooser.filechooser.setCurrentDirectory(new File(JExtendedFileChooser.lastSaveDir));

        /* Show save dialog and store the result in returnVal */
        final int returnVal = JExtendedFileChooser.filechooser.showSaveDialog(null);

        /* Handle save button action. */
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            /* Get file object */
            final File file = JExtendedFileChooser.filechooser.getSelectedFile();

            /* Store the last save directory in its respective variable */
            JExtendedFileChooser.lastSaveDir = file.getAbsolutePath();

            /* Prepare file name to be stored on hard drive */
            final String fileName = file.getName().replaceAll(".png", "").replaceAll(".png", "") + ".png";
            final File outputfile = new File(file.getParent() + "/" + fileName);

            /* Uncomment for debugging purposes */
            // new ImageDisplayer(imageToBeSaved);

            try {
                /* Write image to disk */
                ImageIO.write(imageToBeSaved, "png", outputfile);
            } catch (final IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     * This method is responsible for selection multiple files and directories
     * and reporting this back to the main application.
     * 
     * @return a File array containing the selected files and directories
     */
    public static File[] selectDirectoriesAndImages() {
        File[] selectedFilesandDirectories = null;

        /* Initialize the file chooser */
        JExtendedFileChooser.initialize();

        /* Add a custom file filter and disable the default */
        JExtendedFileChooser.filechooser.addChoosableFileFilter(new JPEGImageFilter());
        JExtendedFileChooser.filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JExtendedFileChooser.filechooser.setMultiSelectionEnabled(true);

        /* Set the current directory to the last open directory */
        JExtendedFileChooser.filechooser.setCurrentDirectory(new File(JExtendedFileChooser.lastOpenDir));

        /* Show open dialog and store the result in returnVal */
        // INTERNATIONALIZE
        final int returnVal = JExtendedFileChooser.filechooser.showDialog(JExtendedFileChooser.filechooser,
                "Hinzufügen");

        /* Process the results */
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            /* Get the selected files */
            selectedFilesandDirectories = JExtendedFileChooser.filechooser.getSelectedFiles();

            /* Store the first open directory in its respective variable */
            JExtendedFileChooser.lastOpenDir = selectedFilesandDirectories[0].getAbsolutePath();
            
        }

        // Reset the file chooser for the next time it's shown.
        JExtendedFileChooser.filechooser.setSelectedFile(null);

        return selectedFilesandDirectories;

    }

    /* To satisfy checkstyle */
    private JExtendedFileChooser() {

    }

}
