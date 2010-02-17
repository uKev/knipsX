/******************************************************************************
 * This package is the root of all files regarding the "picturemanagement".
 *****************************************************************************/
package org.knipsX.model.picturemanagement;

/* import classes from the java sdk */
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*******************************************************************************************************
 * The Class Directory represents a filedirectory. It is assumed that pictures are in this directory.
 ******************************************************************************************************/
public class Directory implements PictureContainer {

    /* List of all pictures you can find in this directory inclusive all subdirectories */
    private final List<Picture> pictures = new LinkedList<Picture>();

    /* The current position in the pictures list */
    private int currentPosition;

    /* The abstract representation of this directory in filesystem */
    private final File directoryFile;

    /**
     * Creates a new directory from a path.
     * 
     * @param path
     *            the pathname from the directory
     */
    public Directory(final String path) {
        this.directoryFile = new File(path);
    }

    /**
     * Returns only the name of the directory not the complete path.
     * 
     * @return name of directory
     */
    public String getName() {
        return this.directoryFile.getName();
    }

    /**
     * Returns the complete path of the directory.
     * 
     * @return path of directory
     */
    public String getPath() {
        return this.directoryFile.getAbsolutePath();
    }

    /**
     * @see java.lang.Iterable#iterator()
     * @return the iterator over this
     */
    public Iterator<PictureInterface> iterator() {
        return this;
    }

    /**
     * Refresh the directory. (That means get all Pictures from all subdirs).
     */
    public void refresh() {
        this.pictures.clear();
        this.currentPosition = 0;
        this.getAllPictures(this.directoryFile);
    }

    /**
     * @see java.util.Iterator#next()
     * @return the next picture
     */
    public Picture next() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(this.directoryFile);
        }
        this.currentPosition = this.currentPosition + 1;
        return this.pictures.get(this.currentPosition - 1);
    }

    /**
     * @see java.util.Iterator#hasNext()
     * @return true if there is an element left , false if not
     */
    public boolean hasNext() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(this.directoryFile);
        }
        if ((this.currentPosition) < (this.pictures.size())) {
            return true;
        } else {
            this.currentPosition = 0;
            return false;
        }
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        /* not implemented because this function is illegal */
    }

    /**
     * Returns the List with all pictures in this directory
     * 
     * @return the list with pictures
     */
    public List<Picture> getItems() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(this.directoryFile);
        }

        return new LinkedList<Picture>(this.pictures);
    }

    /**
     * Initializes the directory with all pictures. This includes all in this directory and all subdirectories.
     * It uses recursion. Files will be checked and if it is a picture it will be added.
     * 
     * @param file
     *            the actual file to check.
     */
    private void getAllPictures(final File file) {
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            for (final File element : children) {
                this.getAllPictures(element);
            }
        } else {
            if (file.isFile()
                    && ((file.getAbsolutePath().toLowerCase().endsWith(".jpg")) || file.getAbsolutePath().toLowerCase()
                            .endsWith(".jpeg"))) {
                this.pictures.add(new Picture(file, true));
            }
        }
    }

    /**
     * It also allows to compare over PictureContainer but it is not done in the basic version of our programm.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param directoryToCompare
     *            other directory to compare
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object
     */
    public int compareTo(final PictureContainer directoryToCompare) {
        if (this.hashCode() == ((Directory) directoryToCompare).hashCode()) {
            return 0;
        } else if (this.getName().compareTo(directoryToCompare.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}