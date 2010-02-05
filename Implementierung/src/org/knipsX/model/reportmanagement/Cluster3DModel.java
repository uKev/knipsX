package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 * 
 */
public class Cluster3DModel extends AbstractTrippleAxesModel {

    private final ArrayList<Frequency3DPoint> frequency3DPoints = new ArrayList<Frequency3DPoint>();

    private final Logger log = Logger.getLogger(this.getClass());

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
    public Cluster3DModel(final ArrayList<PictureContainer> pictureContainers, final Axis xAxis, final Axis zAxis,
            final Axis yAxis) {
        super(pictureContainers, xAxis, zAxis, yAxis);
        this.calculateIfRequired();

    }

    @Override
    protected void calculate() {
        Frequency3DPoint picPoint;
        boolean pointIsAdded = false;

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            for (final Picture pic : pictureContainer) {

                double x = 0, y = 0, z = 0;
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
                    x = Converter.objectToDouble(xValue);
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

                /*
                 * Filter the pictures with the keywords
                 */
                if (!pic.hasMinOneKeywordOf(this.getExifFilterKeywords())) {
                    haveKeyword = false;
                }

                /*
                 * only if we have all three parameters,
                 * the picture is valid and we will perfom actions on it
                 */
                if (haveAllParameters && haveKeyword) {
                    if (this.minX > x) {
                        this.minX = x;
                    }
                    if (this.minY > y) {
                        this.minY = y;
                    }
                    if (this.minZ > z) {
                        this.minZ = z;
                    }

                    if (this.maxX < x) {
                        this.maxX = x;
                    }
                    if (this.maxY < y) {
                        this.maxY = y;
                    }
                    if (this.maxZ < z) {
                        this.maxZ = z;
                    }

                    picPoint = new Frequency3DPoint(x, y, z, pic);

                    for (final Frequency3DPoint freqPoint : this.frequency3DPoints) {

                        if (freqPoint.equals(picPoint)) {
                            freqPoint.addPicture(pic);
                            pointIsAdded = true;
                            // stop searching because we found an equal point.
                            break;
                        }
                    }

                    /*
                     * if we found no point which is equal, we did not add it, so we have a new unique point and add
                     * it
                     * to the list.
                     */
                    if (!pointIsAdded) {
                        this.frequency3DPoints.add(picPoint);
                    }
                } else {
                    if (this.hasMinOneKeyword) {
                        this.log.debug("Pic has not all parameters: ");
                        this.log.debug("X: " + xValue);
                        this.log.debug("Y: " + xValue);
                        this.log.debug("Z: " + xValue);
                    } else {
                        this.log.debug("Picture is filtered by keyword: " + this.getExifFilterKeywords());
                        this.log.debug("It has only the following keywords: "
                                + new ArrayList<String>(Arrays.asList((String[]) pic
                                        .getExifParameter(ExifParameter.KEYWORDS))));

                    }
                }

            }
        }
        this.dataIsCalculated(true);
    }

    /**
     * returns the ArrayList of Frequency3DPoints
     * 
     * @return an arrayList of Frequency3DPoints
     */
    public ArrayList<Frequency3DPoint> getFrequency3DPoints() {
        this.calculateIfRequired();
        return this.frequency3DPoints;
    }

    @Override
    public boolean isModelValid() {
        final Logger logger = Logger.getLogger(this.getClass());

        if (0 == Validator.getValidPicturesCount(this.getPictureContainer(), new ExifParameter[] {
                this.getxAxis().getParameter(), this.getyAxis().getParameter(), this.getzAxis().getParameter() })) {
            logger.info("getValidPicturesCount == 0");
            return false;
        }

        return true;
    }

}
