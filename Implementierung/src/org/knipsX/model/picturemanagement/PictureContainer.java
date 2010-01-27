package org.knipsX.model.picturemanagement;

import java.util.Iterator;
import java.util.List;

public interface PictureContainer extends Iterator<PictureContainer> , Comparable<PictureContainer>{
    
    public List<? extends PictureContainer> getItems();

    public abstract String getName();
}
