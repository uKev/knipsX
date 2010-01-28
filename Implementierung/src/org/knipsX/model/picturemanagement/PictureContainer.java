/******************************************************************************
 * This package is the root of all files regarding the "picturemanagement".
 *****************************************************************************/
package org.knipsX.model.picturemanagement;

/* import classes from the java sdk */
import java.util.Iterator;
import java.util.List;

/**********************************************************************************************************************
 * The Interface PictureContainer represents a composite pattern. It combines picture directory and pictureset.
 *********************************************************************************************************************/
public interface PictureContainer extends Iterable<Picture>, Iterator<Picture>, Comparable<PictureContainer> {

    /**
     * Returns all items of an element
     * 
     * @return the list with content
     */
    List<? extends PictureContainer> getItems();

    /**
     * Returns the name of an element
     * 
     * @return the name
     */
    String getName();
}
