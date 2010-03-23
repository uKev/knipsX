package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/******************************************************************************
 * The Class PictureSet represents a collection of selected pictures.
 *****************************************************************************/
public class PictureSet implements PictureContainer {

    /* List with elements which are building the pictureset */
    private List<PictureContainer> children = new ArrayList<PictureContainer>();

    /* The current position in the list */
    private int currentChild = 0;

    /* The name of the pictureset */
    private final String name;

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Creates a new PictureSet from a name.
     * 
     * @param pictureSetName
     *            the name of the pictureset
     */
    public PictureSet(final String pictureSetName) {
        this.name = pictureSetName;
    }

    /**
     * Creates a new PictureSet from another one.
     * 
     * @param pictureSetToCopy
     *            the pictureset to copy
     * @param pictureSetName
     *            the name of the pictureset
     */
    public PictureSet(final PictureSet pictureSetToCopy, final String pictureSetName) {
        this.name = pictureSetName;
        this.children = pictureSetToCopy.getItems();
    }

    /**
     * Returns the name of the pictureset if (checkContent(container,this))
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds PictureContainer elements to this picture set
     * 
     * @param container
     *            the element to add
     * @return true if it can be added, false if not
     */
    public boolean add(final PictureContainer container) {

        if (container instanceof PictureSet) {
            if (this.checkContent((PictureSet) container, this)) {
                return false;
            } else {
                return this.children.add(container);
            }
        } else {
            return this.children.add(container);
        }
    }

    /**
     * Removes Element from the picture set.
     * 
     * @param container
     *            the element which should be removed
     * @return true if the element could be removed false if not.
     */
    public boolean remove(final PictureContainer container) {
        return this.children.remove(container);
    }

    /**
     * Gets all elements in the pictureset. Like picture if (base == container)
     * { return true;s, directories and picturesets
     * 
     * @return a list with all element in it
     */
    public List<PictureContainer> getItems() {
        return new ArrayList<PictureContainer>(this.children);
    }

    /**
     * @see java.lang.Iterable#iterator()
     * @return the iterator over this
     */
    public Iterator<PictureInterface> iterator() {
        /* Reset the iterator */
        this.currentChild = 0;
        return this;
    }

    /**
     * @see java.util.Iterator#hasNext()
     * @return true if there is an element left , false if not
     */
    public boolean hasNext() {
        boolean hasNext = false;

        if (this.children.isEmpty()) {
            hasNext = false;
            this.currentChild = 0;
        } else {
            if (this.children.get(this.currentChild).hasNext()) {
                hasNext = true;
            } else {
                int child = this.currentChild;

                while ((!hasNext) && (child < this.children.size() - 1)) {
                    child++;
                    if (this.children.get(child).hasNext()) {
                        hasNext = true;
                        this.currentChild = child;
                    } else {
                        hasNext = false;
                        this.currentChild = 0;
                    }
                }
            }
        }
        return hasNext;
    }

    public int returnChildpos() {
        return this.currentChild;
    }

    /**
     * @see java.util.Iterator#next()children
     * @return the next picture in the pictureset
     */
    public PictureInterface next() {
        PictureInterface picture = null;

        if (this.children.get(this.currentChild).hasNext()) {
            picture = this.children.get(this.currentChild).next();
        } else {

            while ((picture == null) && (this.currentChild < this.children.size() - 1)) {
                this.currentChild++;
                if (this.children.get(this.currentChild).hasNext()) {
                    picture = this.children.get(this.currentChild).next();
                }
            }
        }
        return picture;
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        /* not implemented because this function is illegal */
    }

    /**
     * It also allows to compare over PictureContainer but it is not done in the
     * basic version of our Program.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param picturesetToCompare
     *            other picture set to compare
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object
     */
    public int compareTo(final PictureContainer picturesetToCompare) {
        if (this.hashCode() == ((PictureSet) picturesetToCompare).hashCode()) {
            return 0;
        } else if (this.name.toLowerCase().compareTo(((PictureSet) picturesetToCompare).name.toLowerCase()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    private synchronized boolean checkContent(final PictureSet base, final PictureSet container) {
        // boolean is = false;
        // if (base == container) {
        // is=true;
        // } else {
        // for (final PictureContainer element : base.getItems()) {
        // if (element instanceof PictureSet) {
        // boolean ist = this.checkContent((PictureSet) element, container);
        // if(ist) {
        // is = ist;
        // break;
        // }
        // }
        // }
        // }
        // return is;
        return false;
    }
}
