package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 * 
 */
public class Cluster3DModel extends AbstractTrippleAxesModel {

    private ArrayList<Frequency3DPoint> frequency3DPoints;

    /**
     * @deprecated deprecated
     *             deprecated
     */
    @Deprecated
    public Cluster3DModel() {
        super();
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

        Frequency3DPoint picPoint;
        boolean pointIsAdded = false;

        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final Picture pic : pictureContainer) {

                double x = 0, y = 0, z = 0;
                boolean haveAllParameters = true;
                pointIsAdded = false;

                if (pic.getExifParameter(xAxis.getParameter()) == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(xAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    x = (Double) pic.getExifParameter(xAxis.getParameter());
                }
                if (pic.getExifParameter(yAxis.getParameter()) == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(yAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    y = (Double) pic.getExifParameter(yAxis.getParameter());
                }

                if (pic.getExifParameter(zAxis.getParameter()) == null) {
                    this.addMissingExifPictureParameter(new PictureParameter(zAxis.getParameter(), pic));
                    haveAllParameters = false;
                } else {
                    z = (Double) pic.getExifParameter(yAxis.getParameter());
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
                     * if we found no point which is equal, we did not add it, so we have a new unique point and add it
                     * to the list.
                     */
                    if (!pointIsAdded) {
                        this.frequency3DPoints.add(picPoint);
                    }
                }

            }
        }

    }

    /**
     * returns the ArrayList of Frequency3DPoints
     * @return an arrayList of Frequency3DPoints
     */
    public ArrayList<Frequency3DPoint> getFrequency3DPoints() {
        return this.frequency3DPoints;
    }
    
}
