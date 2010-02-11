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

    /* The initialization routine which configures the file chooser */
    private static void initialize() {

        /* Create a new file chooser object */
        JExtendedFileChooser.filechooser = new JFileChooser();

        /* Disable default file filter */
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

        JExtendedFileChooser.initialize();
        JExtendedFileChooser.filechooser.setCurrentDirectory(new File(JExtendedFileChooser.lastSaveDir));
        JExtendedFileChooser.filechooser.addChoosableFileFilter(new PNGImageFilter());
        JExtendedFileChooser.filechooser.setFileHidingEnabled(false);
        JExtendedFileChooser.filechooser.setMultiSelectionEnabled(false);

        /* handle save button action */
        if (JExtendedFileChooser.filechooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            final File file = JExtendedFileChooser.filechooser.getSelectedFile();

            JExtendedFileChooser.lastSaveDir = file.getAbsolutePath();

            /* Prepare file name to be stored on hard drive */
            final String fileName = file.getName().replaceAll(".png", "").replaceAll(".png", "") + ".png";
            final File outputfile = new File(file.getParent() + "/" + fileName);

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

        JExtendedFileChooser.initialize();
        JExtendedFileChooser.filechooser.setCurrentDirectory(new File(JExtendedFileChooser.lastOpenDir));
        JExtendedFileChooser.filechooser.addChoosableFileFilter(new JPEGImageFilter());
        JExtendedFileChooser.filechooser.setFileHidingEnabled(false);
        JExtendedFileChooser.filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JExtendedFileChooser.filechooser.setMultiSelectionEnabled(true);

        /* Process the results */
        /* INTERNATIONALIZE */
        if (JExtendedFileChooser.filechooser.showDialog(JExtendedFileChooser.filechooser, "Hinzufügen") == JFileChooser.APPROVE_OPTION) {
            selectedFilesandDirectories = JExtendedFileChooser.filechooser.getSelectedFiles();
            JExtendedFileChooser.lastOpenDir = selectedFilesandDirectories[0].getAbsolutePath();
        }

        /* Reset the file chooser for the next time it's shown. */
        JExtendedFileChooser.filechooser.setSelectedFile(null);

        return selectedFilesandDirectories;
    }

    /* To satisfy checkstyle */
    private JExtendedFileChooser() {
    }
}
