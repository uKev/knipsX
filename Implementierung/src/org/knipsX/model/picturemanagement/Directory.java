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

    private void getAllPictures(File path) {
        if (path.isDirectory()) {
            File[] children = path.listFiles();
            for (int i = 0; i < children.length; i++) {
                getAllPictures(children[i]);
            }
        } else {
            this.pictures.add(new Picture(path));
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

    @Override
    public boolean hasNext() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(new File(path));
        }

        /* check if next item is in list */
        return (currentPosition++) <= (this.pictures.size() - 1);
    }

    @Override
    public PictureContainer next() {
        if (this.pictures.size() == 0) {
            this.currentPosition = 0;
            this.getAllPictures(new File(path));
        }

        PictureContainer picture = this.pictures.get(currentPosition);

        this.currentPosition++;

        return picture;
    }

    @Override
    public void remove() { 
        /* not implemented */
    }
}