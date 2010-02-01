package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Model which contain the Boxplots and name them with an Axis.
 * 
 * @author Kevin Zuber
 * 
 */
public class BoxplotModel extends AbstractSingleAxisModel {

    /*
     * TWEAK: remove deprecated functions
     */

    private final ArrayList<Boxplot> boxplots;
    private WilcoxonTest wilcoxonTest;

    @Deprecated
    private boolean wilcoxonTestActive;
    @Deprecated
    private WilcoxonTestType wilcoxonTestType;
    @Deprecated
    private float wilcoxonSignificance;

    /**
     * Constructor for the Boxplot Model
     * 
     */
    public BoxplotModel() {
        super();
        this.boxplots = new ArrayList<Boxplot>();
        this.dataIsCalculated(false);

    }

    /**
     * Constructor for the Boxplot Model
     * 
     * @param pictureContainers
     *            the pictureContainers which are used for the boxplots (one boxplot per pictureContainer)
     * @param xAxis
     *            the xAxis of the boxplot
     */
    public BoxplotModel(final ArrayList<PictureContainer> pictureContainers, final Axis xAxis) {
        super(pictureContainers, xAxis);
        this.boxplots = new ArrayList<Boxplot>(pictureContainers.size());

        this.calculateIfNeeded();

    }

    private void calculateIfNeeded() {

        if (!this.isDataCalculated()) {
            this.clearMissingExifPictureParameter();
            this.boxplots.clear();

            for (final PictureContainer pictures : this.getPictureContainer()) {
                String boxplotName;
                if (pictures.getName() == null) {
                    boxplotName = this.xAxis.getParameter().toString();
                    System.out.println("Warning in BoxplotModel.java: pictures.getName() was null");
                } else {
                    boxplotName = pictures.getName();
                }

                this.boxplots.add(new Boxplot(pictures, this.xAxis.getParameter(), boxplotName));
            }

            this.wilcoxonTest = new WilcoxonTest(this.getPictureContainer(), this.xAxis.getParameter());

            for (final PictureContainer pictureContainer : this.getPictureContainer()) {
                for (final Picture picture : pictureContainer) {
                    if (picture.getExifParameter(this.xAxis.getParameter()) == null) {
                        System.out.println("Missing Exif Parameter: " + picture.getPath()
                                + this.xAxis.getParameter().toString());
                        this.addMissingExifPictureParameter(new PictureParameter(this.xAxis.getParameter(), picture));
                    }
                }
            }
            this.dataIsCalculated(true);
        }
    }

    /**
     * Getter for the boxplots
     * 
     * @return boxplots
     */
    public ArrayList<Boxplot> getBoxplots() {
        this.calculateIfNeeded();
        return this.boxplots;
    }

    /**
     * getter for the maximum x-value the boxplots
     * 
     * @return the count of the boxplots
     */
    @Override
    public double getMaxX() {
        this.calculateIfNeeded();

        return this.boxplots.size();
    }

    @Override
    /**
     * return the maximum Y Value, return Double.MIN_VALUE if there are no boxplots
     */
    public double getMaxY() {
        this.calculateIfNeeded();

        Double maxY = -Double.MAX_VALUE;
        for (final Boxplot boxplot : this.boxplots) {
            if (maxY < boxplot.getMaxValue()) {
                maxY = boxplot.getMaxValue();
            }
        }
        return maxY;
    }

    @Override
    /**
     * return 0 because there are no negative boxplots
     */
    public double getMinX() {
        return 0;
    }

    @Override
    /**
     * return the minimum Y Value, return  Double.MAX_VALUE if there are nox boxplots
     */
    public double getMinY() {
        this.calculateIfNeeded();
        Double minY = Double.MAX_VALUE;
        for (final Boxplot boxplot : this.boxplots) {
            if (minY > boxplot.getMinValue()) {
                minY = boxplot.getMinValue();
            }
        }
        return minY;
    }

    /**
     * deprecated
     * 
     * @deprecated Wilcoxon Test is externalized, use getWilcoxonTest()
     * @return the result of the Wilcoxon test
     */
    @Deprecated
    public float getWilcoxonPValue() {
        return 0f;
    }

    /**
     * deprecated
     * 
     * @deprecated Wilcoxon Test is externalized
     * @return deprecated
     */
    @Deprecated
    public float getWilcoxonSignificance() {
        return this.wilcoxonSignificance;
    }

    /**
     * Getter for the WilcoxonTest.
     * 
     * @return the wilcoxonTest. Returns null if wilcoxonTest is not active.
     */
    public WilcoxonTest getWilcoxonTest() {
        this.calculateIfNeeded();

        WilcoxonTest wilcoxonTest = this.wilcoxonTest;
        if (wilcoxonTest != null) {
            if (wilcoxonTest.isValid()) {
                wilcoxonTest = null;
            }
        }
        return wilcoxonTest;
    }

    /**
     * deprecated
     * 
     * @deprecated Wilcoxon Test is externalized
     * @return deprecated
     */
    @Deprecated
    public WilcoxonTestType getWilcoxonTestType() {
        return this.wilcoxonTestType;
    }

    /**
     * deprecated
     * 
     * @deprecated deprecated
     * @return if the wilcoxon test is used
     */
    @Deprecated
    public boolean isWilcoxonTestActive() {
        return this.wilcoxonTestActive;
    }

    /**
     * deprecated
     * 
     * @deprecated Wilcoxon Test is externalized
     * @param wilcoxonSignificance
     *            deprecated
     */
    @Deprecated
    public void setWilcoxonSignificance(final float wilcoxonSignificance) {
        this.wilcoxonSignificance = wilcoxonSignificance;
    }

    /**
     * deprecated
     * 
     * @deprecated
     *             deprecated
     * @param wilcoxonTestActive
     *            deprecated
     */
    @Deprecated
    public void setWilcoxonTestActive(final boolean wilcoxonTestActive) {
        this.wilcoxonTestActive = wilcoxonTestActive;
    }

    /**
     * @deprecated Wilcoxon Test is externalized
     * @param wilcoxonTestType
     *            deprecated
     */
    @Deprecated
    public void setWilcoxonTestType(final WilcoxonTestType wilcoxonTestType) {
        this.wilcoxonTestType = wilcoxonTestType;
    }

    /**
     * Sets the xAxis for the boxplot model
     * 
     * @param xAxis
     *            the xAxis for the boxplot model
     */
    @Override
    public void setxAxis(final Axis xAxis) {
        this.dataIsCalculated(false);
        this.xAxis = xAxis;
    }

}
