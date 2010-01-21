package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents a bar which belongs to a picture category and PictureContainer.
 * It is primarily an encapsulated data container.
 * 
 * @author Kevin Zuber
 * 
 */
public class Bar {

    private final PictureContainer pictureContainer;

    /*
     * represents the absolute height of the bar.
     */
    private int height;

    /**
     * Constructor for the bar with pictureContainer parameter
     * 
     * @param pictureContainer
     *            the picture container which is represented through the bar
     */
    public Bar(final PictureContainer pictureContainer) {
        super();
        this.pictureContainer = pictureContainer;
    }

    /**
     * Constructor for the bar with pictureContainer and height parameter
     * 
     * @param pictureContainer
     *            the picture container which is represented through the bar
     * @param height
     *            the absolute height of the bar
     */
    public Bar(final PictureContainer pictureContainer, final int height) {
        super();
        this.pictureContainer = pictureContainer;
        this.height = height;
    }

    /**
     * Getter for the Height of the bar.
     * 
     * @return height the height of the bar.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter for the PictureContainer which is represented trough this bar.
     * 
     * @return pictureContainer the pictureContainer
     */
    public PictureContainer getPictureContainer() {
        return this.pictureContainer;
    }

}
