package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Validator;

/**
 * Model which contain the Boxplots and name them with an Axis.
 * 
 * @author Kevin Zuber
 * 
 */
public class BoxplotModel extends AbstractSingleAxisModel {

    private final List<Boxplot> boxplots;

    private final WilcoxonTest wilcoxonTest = new WilcoxonTest();

    private final Logger logger = Logger.getLogger(this.getClass());

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
    public BoxplotModel(final List<PictureContainer> pictureContainers, final Axis xAxis) {
        super(pictureContainers, xAxis);

        this.boxplots = new ArrayList<Boxplot>(pictureContainers.size());

        this.calculateIfRequired();

    }

    @Override
    protected void calculate() {
        this.clearMissingExifPictureParameter();
        this.boxplots.clear();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            String boxplotName = this.xAxis.getParameter().toString();

            if (pictureContainer.getName() != null) {
                boxplotName = pictureContainer.getName();
            }
            final Boxplot boxplot = new Boxplot(pictureContainer, this.xAxis.getParameter(), boxplotName, this
                    .getExifFilterKeywords());

            if (this.maxY < boxplot.getMaxValue()) {
                this.maxY = boxplot.getMaxValue();
            }

            if (this.minY > boxplot.getMinValue()) {
                this.minY = boxplot.getMinValue();
            }
            this.boxplots.add(boxplot);
        }

        this.maxX = this.boxplots.size();
        this.minX = 0;

        this.wilcoxonTest.setPictureContainer(this.getPictureContainer());
        this.wilcoxonTest.setExifparameter(this.xAxis.getParameter());

        List<PictureInterface> validPictures = Validator.getValidPictures(this.getPictureContainer(), this.xAxis
                .getParameter(), this.getExifFilterKeywords());
        
        for (final PictureInterface picture : validPictures) {
            this.logger.info("Missing Exif Parameter: " + picture.getPath() + " : "
                    + this.xAxis.getParameter().toString());
            this.addMissingExifPictureParameter(new PictureParameter(this.xAxis.getParameter(), picture));
        }
        this.dataIsCalculated(true);
    }

    /**
     * Getter for the boxplots.
     * 
     * @return boxplots.
     */
    public List<Boxplot> getBoxplots() {
        this.calculateIfRequired();

        return this.boxplots;
    }

    /**
     * Getter for the WilcoxonTest.
     * 
     * @return the wilcoxonTest. Returns null if wilcoxonTest is not active.
     */
    public WilcoxonTest getWilcoxonTest() {
        this.calculateIfRequired();

        return this.wilcoxonTest;
    }

    /**
     * Sets the xAxis for the boxplot model
     * 
     * @param xAxis
     *            the xAxis for the boxplot model
     */
    @Override
    public void setXAxis(final Axis xAxis) {
        this.dataIsCalculated(false);

        this.xAxis = xAxis;
    }

    @Override
    public boolean isModelValid() {
        this.calculateIfRequired();

        if (this.maxX < this.minX) {
            this.logger.info("Model invalid: maxX < minX : " + this.maxX + " < " + this.minX);
            return false;
        }

        if (Validator.getValidPicturesCount(this.getPictureContainer(), this.xAxis.getParameter()) == 0) {
            this.logger.info("Model invalid: validPictureCount == 0");
            return false;
        }
        return true;
    }
}
