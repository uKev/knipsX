package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.List;

public class PictureSet implements PictureContainer {

    private List<PictureContainer> childs = new ArrayList<PictureContainer>();
    
    private List<Picture> inactivePictures = new ArrayList<Picture>();

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

    public boolean addToChilds(PictureContainer container) {
        if(childs.contains(container)){
            System.out.println("Element is already in the PictureSet");        
            return false;
        }else{
            if (container instanceof PictureSet){
                if (this.id == ((PictureSet)container).getID()){
                    System.out.println("Can´t add PictureSet");
                    return false;
                }else {
                    return childs.add(container);
                }
                
            }else{
                return childs.add(container); 
            }
        }      
    }

    public boolean remove(PictureContainer container) {
        return childs.remove(container);
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
    
    public int getID(){
        return id;
    }

    @Override
    public int compareTo(PictureContainer picturesetToCompare) {
        if (this.id == ((PictureSet) picturesetToCompare).getID()){
            return 0;
        } else if (this.getName().compareTo(picturesetToCompare.getName()) == 1) {
            return 1;
        } else {
            return -1; 
        }
    }
}
