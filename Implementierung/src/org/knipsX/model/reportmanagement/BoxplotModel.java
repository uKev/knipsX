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

    private ArrayList<Boxplot> boxplots;
    private WilcoxonTest wilcoxonTest;
    private ArrayList<PictureParameter> missingExifParameter;
    @Deprecated
    private boolean wilcoxonTestActive;
    @Deprecated
    private WilcoxonTestType wilcoxonTestType;
    @Deprecated
    private float wilcoxonSignificance;

    /**
     * @deprecated deprecated
     * deprecated
     */
    public BoxplotModel() {
        super();
        // TODO Auto-generated constructor stub
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

        for (final PictureContainer pictures : pictureContainers) {
            String boxplotName;
            if (xAxis.getDescription() == null) {
                boxplotName = xAxis.getParameter().toString();
            } else {
                boxplotName = xAxis.getDescription();
            }
            this.boxplots.add(new Boxplot(pictures, xAxis.getParameter(), boxplotName));
        }
        this.wilcoxonTest = new WilcoxonTest(pictureContainers, xAxis.getParameter());

        this.missingExifParameter = null;

        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final Picture picture : pictureContainer) {
                if (picture.getExifParameter(xAxis.getParameter()) == null) {
                    this.missingExifParameter.add(new PictureParameter(xAxis.getParameter(), picture));
                }
            }
        }

    }

    /**
     * Getter for the boxplots
     * 
     * @return boxplots
     */
    public ArrayList<Boxplot> getBoxplots() {
        return this.boxplots;
    }

    /**
     * getter for the maximum x-value the boxplots
     * 
     * @return the count of the boxplots
     */
    @Override
    public Object getMaxX() {

        return this.boxplots.size();
    }

    @Override
    /**
     * return the maximum Y Value, return Double.MIN_VALUE if there are no boxplots
     */
    public Object getMaxY() {
        Double maxY = Double.MIN_VALUE;
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
    public Object getMinX() {
        return 0;
    }

    @Override
    /**
     * return the minimum Y Value, return  Double.MAX_VALUE if there are nox boxplots
     */
    public Object getMinY() {
        Double minY = Double.MAX_VALUE;
        for (final Boxplot boxplot : this.boxplots) {
            if (minY > boxplot.getMinValue()) {
                minY = boxplot.getMinValue();
            }
        }
        return minY;
    }

    @Override
    public ArrayList<PictureParameter> getPicturesWithMissingExifParameter() {
        return this.missingExifParameter;
    }

    /**
     * deprecated
     * 
     * @deprecated Wilcoxon Test is externalized, use getWilcoxonTest()
     * @return the result of the Wilcoxon test
     */
    @Deprecated
    public float getWilcoxonPValue() {
        // TODO: remove
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
     * @deprecated
     *             deprecated
     * @param wilcoxonTestActive deprecated
     */
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

}
