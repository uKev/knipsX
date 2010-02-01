package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.ExifParameter;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 * 
 */
public class Cluster3DModel extends AbstractTrippleAxesModel {

    private ArrayList<Frequency3DPoint> frequency3DPoints = new ArrayList<Frequency3DPoint>();
    private ArrayList<PictureContainer> pictureContainers = new ArrayList<PictureContainer>();

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
        this.calculateIfNeeded();

    }

    private void calculateIfNeeded() {
        if (!this.isDataCalculated()) {
            Frequency3DPoint picPoint;
            boolean pointIsAdded = false;

            for (final PictureContainer pictureContainer : this.pictureContainers) {
                for (final Picture pic : pictureContainer) {

                    double x = 0, y = 0, z = 0;
                    boolean haveAllParameters = true;
                    pointIsAdded = false;

                    Object xValue = pic.getExifParameter(xAxis.getParameter());
                    Object yValue = pic.getExifParameter(yAxis.getParameter());
                    Object zValue = pic.getExifParameter(zAxis.getParameter());

                    if (xValue == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(xAxis.getParameter(), pic));
                        haveAllParameters = false;
                    } else {
                        x = (Double) xValue;
                    }
                    if (yValue == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(yAxis.getParameter(), pic));
                        haveAllParameters = false;
                    } else {
                        y = (Double) yValue;
                    }

                    if (zValue == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(zAxis.getParameter(), pic));
                        haveAllParameters = false;
                    } else {
                        z = (Double) zValue;
                    }

                    /*
                     * only if we have all three parameters,
                     * the picture is valid and we will perfom actions on it
                     */
                    if (haveAllParameters) {
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
                        System.out.println("Pic has all parameters: ");
                        System.out.println("X"+xValue);
                        System.out.println("Y"+xValue);
                        System.out.println("Z"+xValue);
                    } else {
                        System.out.println("Pic has not all parameters: ");
                        System.out.println("X" + xValue);
                        System.out.println("Y" + xValue);
                        System.out.println("Z" + xValue);
                    }

                }
            }
            this.dataIsCalculated(true);
        }
    }

    /**
     * returns the ArrayList of Frequency3DPoints
     * 
     * @return an arrayList of Frequency3DPoints
     */
    public ArrayList<Frequency3DPoint> getFrequency3DPoints() {
        this.isDataCalculated();
        return this.frequency3DPoints;
    }

}
