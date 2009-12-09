package org.studien.kompositum;

import java.util.ArrayList;
import java.util.Iterator;

public interface PictureContainer extends Iterator {
	public abstract ArrayList<?> getItems();
}
