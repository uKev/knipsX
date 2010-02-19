package org.knipsX.utils.FileChooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

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
	public boolean accept(File f) {

		/* display directories */
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		
		if (extension.equals(Utils.PNG)) {
			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDescription() {
		// INTERNATIONALIZE
		return Messages.getString("PNGImageFilter.0"); //$NON-NLS-1$
	}
}
