package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.List;

import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;

/**
 * Represents a bar which belongs to a picture category and PictureContainer.
 * It is primarily an encapsulated data container.
 * 
 * @author Kevin Zuber
 * 
 */
public class Bar {

    private final PictureContainer pictureContainer;

    private final List<PictureInterface> pictures = new ArrayList<PictureInterface>();

    /**
     * Constructor for the bar with pictureContainer argument.
     * 
     * @param pictureContainer
     *            the picture containter of the bar which is associated with the original images in this bar.
     */
    public Bar(final PictureContainer pictureContainer) {
        super();

        this.pictureContainer = pictureContainer;
    }

    /**
     * A bar represents a set of pictures. This add another picture to the bar which will increase the height of the
     * bar.
     * 
     * @param picture
     *            a picture that should be added to the bar.
     */
    public void addPicture(final PictureInterface picture) {
        this.pictures.add(picture);
    }

    /**
     * Getter for the Height of the bar. (Meaning the amount of pictures that are represented by this bar)
     * 
     * @return height the height of the bar.
     */
    public int getHeight() {
        return this.pictures.size();
    }

    /**
     * Getter for the picture containter of the bar which is associated with the original images in this bar.
     * 
     * @return pictureContainer the picture container where all pictures of this bar come from
     */
    public PictureContainer getPictureContainer() {
        return this.pictureContainer;
    }

    /**
     * Getter for the pictures which are represented by this bar.
     * 
     * @return all pictures which are represented by this bar.
     */
    public List<PictureInterface> getPictures() {
        return this.pictures;
    }
}