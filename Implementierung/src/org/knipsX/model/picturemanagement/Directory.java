package org.knipsX.model.picturemanagement;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Directory implements PictureContainer {

    private List<Picture> pictures = new LinkedList<Picture>();

    private int currentPosition;

    private String directoryName;

    private String path;

    public Directory(String path) {
        this.path = path;
        this.directoryName = path;
    }

    private void getAllPictures(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) {
                getAllPictures(children[i]);
            }
        } else {
            this.pictures.add(new Picture(file,true));
        }
    }

    public List<Picture> getItems() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(new File(path));
        }
        
        return new LinkedList<Picture>(this.pictures);
    }

    public String getName() {
        return directoryName;
    }

    public void setName(String name) {
        this.directoryName = name;
    }

    public boolean hasNext() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(new File(path));
        }

        /* check if next item is in list */
        return (currentPosition++) <= (this.pictures.size() - 1);
    }

    public PictureContainer next() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(new File(path));
        }

        PictureContainer picture = this.pictures.get(currentPosition);

        this.currentPosition++;

        return picture;
    }

    public void remove() { 
        /* not implemented */
    }
}