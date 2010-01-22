package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.List;

public class PictureSet implements PictureContainer {

    private List<PictureContainer> childs = new ArrayList<PictureContainer>();

    private int id;

    private int currentPosition;

    private String name;

    public PictureSet(String pictureSetName, int freePictureSetID) {
        this.name = pictureSetName;
        this.id = freePictureSetID;
    }

    public PictureSet(PictureSet pictureSetToCopy, String pictureSetName, int freePictureSetID) {
        this.name = pictureSetName;
        this.id = freePictureSetID;
        this.childs = pictureSetToCopy.getItems();
    }

    public void add(PictureContainer container) {
        childs.add(container);
    }

    public void remove(PictureContainer container) {
        childs.remove(container);
    }

    public List<PictureContainer> getItems() {
        return new ArrayList<PictureContainer>(this.childs);
    }

    public boolean hasNext() {

        /* check if next item is in list */
        return (currentPosition++) <= (this.childs.size() - 1);
    }

    public PictureContainer next() {
        PictureContainer pictureContainer = this.childs.get(currentPosition);

        this.currentPosition++;

        return pictureContainer;
    }

    public void remove() {
        /* not implemented */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}