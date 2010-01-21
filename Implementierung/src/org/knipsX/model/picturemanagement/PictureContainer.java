package org.knipsX.model.picturemanagement;

import java.util.Iterator;
import java.util.List;

public interface PictureContainer extends Iterator<Picture> {
	public List<?> getItems();

	public abstract String getName();
	
	public abstract void setName(String name);
}
