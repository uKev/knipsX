package org.knipsX.model.picturemanagement;

import java.util.Iterator;
import java.util.List;

public interface PictureContainer extends Iterable<Picture>, Iterator<Picture> , Comparable<PictureContainer>{
    
    public List<? extends PictureContainer> getItems();

    public abstract String getName();
    
    /* FIXME Benny! the iterator must return each picture only once (ask Kevin) */
}
