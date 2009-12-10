package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.Iterator;


public interface PictureContainer extends Iterator<Picture> {
	public abstract ArrayList<?> getItems();
}
