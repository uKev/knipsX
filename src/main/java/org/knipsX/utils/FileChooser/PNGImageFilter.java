package org.knipsX.utils.FileChooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.knipsX.Messages;

/**
 * This class is responsible for filtering what types of files you see in a
 * JFileChooser. It only displays PNG images
 * 
 * @author David Kaufman
 * 
 */
public class PNGImageFilter extends FileFilter {

    /**
     * Only accept png images and directories {@inheritDoc}
     */
    @Override
    public boolean accept(final File f) {

        /* display directories */
        if (f.isDirectory()) {
            return true;
        }

        final String extension = Utils.getExtension(f);

        if (extension.equals(Utils.PNG)) {
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return Messages.getString("PNGImageFilter.0");
    }
}
