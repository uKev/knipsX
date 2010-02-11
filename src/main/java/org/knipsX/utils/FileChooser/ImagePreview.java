package org.knipsX.utils.FileChooser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * This class is responsible for displaying the thumbnail images in the file chooser
 * dialog.
 * 
 * @author David Kaufman
 * 
 */
public class ImagePreview extends JComponent implements PropertyChangeListener {

    private static final long serialVersionUID = -933668121399921025L;
    ImageIcon thumbnail = null;
    File file = null;

    /**
     * The constructor which attaches the image preview to the specified file chooser
     * 
     * @param fc
     *            The file chooser which this object is attached to
     */
    public ImagePreview(final JFileChooser fc) {
        this.setPreferredSize(new Dimension(100, 50));
        fc.addPropertyChangeListener(this);
    }

    /* Generate the thumbnail and load it into the appropriate variable */
    private void loadImage() {
        if (this.file == null) {
            this.thumbnail = null;
            return;
        }

        /*
         * Don't use createImageIcon (which is a wrapper for getResource)
         * because the image we're trying to load is probably not one
         * of this program's own resources
         */

        final ImageIcon tmpIcon = new ImageIcon(this.file.getPath());
        if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > 90) {
                this.thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
            } else {
                /* no need to miniaturize */
                this.thumbnail = tmpIcon;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        if (this.thumbnail == null) {
            this.loadImage();
        }

        if (this.thumbnail != null) {
            int x = this.getWidth() / 2 - this.thumbnail.getIconWidth() / 2;
            int y = this.getHeight() / 2 - this.thumbnail.getIconHeight() / 2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            this.thumbnail.paintIcon(this, g, x, y);
        }
    }

    /**
     * Handles what happens if an image was selected and initiates the
     * repaint operation
     * 
     * @param e
     *            the property change event
     */
    public void propertyChange(final PropertyChangeEvent e) {
        boolean update = false;
        final String prop = e.getPropertyName();

        /* If the directory changed, don't show an image. */
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            this.file = null;
            update = true;
            /* If a file became selected, find out which one */
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            this.file = (File) e.getNewValue();
            update = true;
        }

        /* Update the preview accordingly */
        if (update) {
            this.thumbnail = null;
            if (this.isShowing()) {
                this.loadImage();
                this.repaint();
            }
        }
    }
}
