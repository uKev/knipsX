package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.ExifParameter;

/**
 * A simple Table of the exif Data. Gives every Picture with all Data.
 * 
 * @author Kevin Zuber
 * 
 */

public class TableModel extends AbstractReportModel {
    ArrayList<Picture> pictures = new ArrayList<Picture>();

    /**
     * Creates an empty TableModel. You want to use addPictureContainer(...) to add pictures to the table.
     */
    public TableModel() {
        super();
    }

    /**
     * Creates a TableModel from an ArrayList of pictureContainer.
     * 
     * @param pictureContainer
     *            pictureContainer which should be added to the table
     */
    public TableModel(final ArrayList<PictureContainer> pictureContainer) {
        super(pictureContainer);
    }

    @Override
    protected void calculate() {

        this.pictures.clear();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            for (final Picture picture : pictureContainer) {
                this.pictures.add(picture);
                for (final ExifParameter exifParameter : ExifParameter.values()) {
                    if (picture.getExifParameter(exifParameter) == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(exifParameter, picture));
                    }

                }
            }
        }

    }

    /**
     * Returns an ArrayList of all Pictures inside the table.
     * 
     * @return an ArrayList of all Pictures inside the table.
     */
    public ArrayList<Picture> getPictures() {

        this.calculateIfNeeded();

        return this.pictures;

    }
}
