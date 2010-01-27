package org.knipsX.model.picturemanagement;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Directory implements PictureContainer {

    private List<Picture> pictures = new LinkedList<Picture>();

    private int currentPosition;

    private File directoryFile;

    public Directory(String path) {
        this.directoryFile = new File(path);
    }

    public String getName() {
        return directoryFile.getName();
    }

    public String getPath() {
        return directoryFile.getAbsolutePath();
    }
    
    public PictureContainer next() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(directoryFile);
        }

        PictureContainer picture = this.pictures.get(currentPosition);

        this.currentPosition++;

        return picture;
    }

    public boolean hasNext() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(directoryFile);
        }

        /* check if next item is in list */
        return (currentPosition++) <= (this.pictures.size() - 1);
    }

    public List<Picture> getItems() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(directoryFile);
        }

        return new LinkedList<Picture>(this.pictures);
    }


    private void getAllPictures(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) {
                getAllPictures(children[i]);
            }
        } else {
            this.pictures.add(new Picture(file, true));
        }
    }

    /**
     * This method should not be implemented. This function is illegal.
     */
    public void remove() {
        /* not implemented */
    }

    public int compareTo(PictureContainer directoryToCompare) {
        if (this.getPath().hashCode() == ((Directory) directoryToCompare).getPath().hashCode()) {
            return 0;
        } else if (this.getName().compareTo(directoryToCompare.getName()) == 1) {
            return 1;
        } else {
            return -1;
        }
    }
}