package org.knipsX.model.picturemanagement;

import java.util.Iterator;
import java.util.List;

public interface PictureContainer extends Iterator<Picture> {
	public abstract List<?> getItems();
}
