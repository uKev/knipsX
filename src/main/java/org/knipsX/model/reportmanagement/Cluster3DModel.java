package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 */
public class Cluster3DModel extends AbstractTrippleAxesModel {

    private final List<Frequency3DPoint> frequency3DPoints = new ArrayList<Frequency3DPoint>();

    private final Logger logger = Logger.getLogger(this.getClass());

    boolean hasMinOneKeyword = false;

    /**
     * creates an empty Cluster3DModel.
     */
    public Cluster3DModel() {
        super();

        this.dataIsCalculated(false);
    }

    /**
     * Creates a new Cluster3DModel from pictureContainers and thre axis.
     * It calculates the frequency3DPoints.
     * 
     * @param pictureContainers
     *            the picture container on which the analysis is done
     * @param xAxis
     *            the description and parameter of the x axis
     * @param zAxis
     *            the description and parameter of the z axis
     * @param yAxis
     *            the description and parameter of the y axis
     */
    public Cluster3DModel(final List<PictureContainer> pictureContainers, final Axis xAxis, final Axis zAxis,
            final Axis yAxis) {
        super(pictureContainers, xAxis, zAxis, yAxis);

        this.calculateIfRequired();
    }

    @Override
    protected void calculate() {
        Frequency3DPoint picPoint;
        boolean pointIsAdded = false;

        /* 
         * Iterate over all pictures of all pictureContainer.
         * Check each picture for missing parameter data.
         * Classify each valid picture as a point.
         */
        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            for (final PictureInterface pic : pictureContainer) {

                double x = 0d;
                double y = 0d;
                double z = 0d;

                boolean haveAllParameters = true;
                boolean haveKeyword = true;

                pointIsAdded = false;

                final Object xValue = pic.getExifParameter(this.xAxis.getParameter());
                final Object yValue = pic.getExifParameter(this.yAxis.getParameter());
                final Object zValue = pic.getExifParameter(this.zAxis.getParameter());

                if (xValue == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(this.xAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    try {
                        x = Converter.objectToDouble(xValue);
                    } catch (ClassCastException e) {
                        logger.error("Exif Parameter " + this.xAxis.getParameter());
                    }
                }
                if (yValue == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(this.yAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    y = Converter.objectToDouble(yValue);
                }

                if (zValue == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(this.zAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    z = Converter.objectToDouble(zValue);
                }

                /* filter the pictures with the Keywords */
                if (!pic.hasMinOneKeywordOf(this.getExifFilterKeywords())) {
                    haveKeyword = false;
                }

                /* only if we have all three parameters, the picture is valid and we will perfom actions on it */
                if (haveAllParameters && haveKeyword) {
                    this.minX = Math.min(this.minX, x);
                    this.minY = Math.min(this.minY, y);
                    this.minZ = Math.min(this.minZ, z);

                    this.maxX = Math.max(this.maxX, x);
                    this.maxY = Math.max(this.maxY, y);
                    this.maxZ = Math.max(this.maxZ, z);

                    picPoint = new Frequency3DPoint(x, y, z, pic);

                    for (final Frequency3DPoint freqPoint : this.frequency3DPoints) {

                        if (freqPoint.equals(picPoint)) {
                            freqPoint.addPicture(pic);
                            pointIsAdded = true;

                            /* stop searching because we found an equal point */
                            break;
                        }
                    }

                    /*
                     * if we found no point which is equal, we did not add it, so we have a new unique point and add it
                     * to the list
                     */
                    if (!pointIsAdded) {
                        this.frequency3DPoints.add(picPoint);
                    }
                } else {
                    
                    if (this.hasMinOneKeyword) {
                        this.logger.debug("Pic has not all parameters: ");
                        this.logger.debug("X: " + xValue);
                        this.logger.debug("Y: " + xValue);
                        this.logger.debug("Z: " + xValue);
                    } else {
                        this.logger.debug("Picture is filtered by keyword: " + this.getExifFilterKeywords());
                        this.logger.debug("It has only the following keywords: "
                                + new ArrayList<String>(Arrays.asList((String[]) pic
                                        .getExifParameter(ExifParameter.KEYWORDS))));
                    }
                }
            }
        }
        this.dataIsCalculated(true);
    }

    /**
     * returns the List of Frequency3DPoints
     * 
     * @return a List of Frequency3DPoints
     */
    public List<Frequency3DPoint> getFrequency3DPoints() {
        this.calculateIfRequired();

        return this.frequency3DPoints;
    }

    @Override
    public boolean isModelValid() {
        if (0 == Validator.getValidPicturesCount(this.getPictureContainer(), new ExifParameter[] {
                this.getXAxis().getParameter(), this.getYAxis().getParameter(), this.getZAxis().getParameter() })) {
            logger.info("getValidPicturesCount == 0");
            return false;
        }
        return true;
    }
}                        logger.error("Exif Parameter " + this.xAxis.getParameter());
                    }