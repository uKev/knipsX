package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

/**
 * The model auf the Histogram2D which calculate the statistic categories and allocate the axes to the Data.
 * 
 * @author Kevin Zuber
 * 
 */

public class Histogram2DModel extends AbstractSingleAxisModel {

    private final Logger logger = Logger.getLogger(this.getClass());
    
    private Category[] categories;

    private double xCategorySize;

    /**
     * Creates a new empty Histogram2DModel. You need to set pictureContainer and xAxis before you can use it.
     */
    public Histogram2DModel() {
        super();
    }

    /**
     * Creates a new Hisogram2DModel with initial data.
     * 
     * @param pictureContainer
     *            An ArrayList of pictureContainer which should be compared in this report.
     * @param xAxis
     *            the configuration for the xAxis.
     */
    public Histogram2DModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis) {
        super(pictureContainer, xAxis);
    }

    /*
     * Iterate over all pictures from the different picture sets and allocate each picture in the corresponding category
     */
    private void allocatePicturesToCategories() {

        /* before we can allocate pictures to categories, we need to compute the categories first */
        this.generateCategories();

        Double xValue;
        
        Category category;
        
        Bar bar;

        /* there is no negative count of pictures, so the minum is zero */
        this.minY = 0;

        /*
         * We will memorise each picture, we have allocated, in a list.
         * 
         * So we can detect if a picture may have been allocated into more than one category.
         * 
         * If a picture is allocated into more than one category it is very likely that the generateCategories() method
         * does not work properly.
         */
        final List<PictureInterface> alreadyAllocatedPictures = new ArrayList<PictureInterface>();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {

            /* each x coordinate = each category */
            for (int i = 0; i < this.categories.length; ++i) {

                /* create a bar for each pictureContainer in each category */
                bar = new Bar(pictureContainer);
                category = this.categories[i];

                for (final PictureInterface picture : pictureContainer) {

                    boolean pictureValid = true;

                    final ExifParameter xParameter = this.getXAxis().getParameter();

                    final Object xValueObject = picture.getExifParameter(xParameter);

                    if (xValueObject == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(xParameter, picture));
                        pictureValid = false;
                    }

                    if (!picture.hasMinOneKeywordOf(this.getExifFilterKeywords())) {
                        pictureValid = false;
                    }

                    if (pictureValid) {
                        xValue = Converter.objectToDouble(xValueObject);

                        if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())) {

                            /* if x value fits between <= category */

                            /* picture fits in the category, yeah! */
                            if (alreadyAllocatedPictures.contains(picture)) {
                                this.logger.error("Picture " + picture.getName() + " already classified. x: " + xValue);
                                this.logger.error("in Category: " + "max X: " + category.getMaxValueX() + "  min x: ");
                            }
                            alreadyAllocatedPictures.add(picture);
                            bar.addPicture(picture);
                        } else if (((i + 1) == this.categories.length)) {

                            /* or if x is last */

                            /* and fits in category.maxValue == value */
                            if (xValue == category.getMaxValueX()) {
                                if (alreadyAllocatedPictures.contains(picture)) {
                                    this.logger.error("Picture " + picture.getName() + " already classified. x: "
                                            + xValue);
                                    this.logger.error("in Category: " + "max X: " + category.getMaxValueX()
                                            + "  min x: ");
                                }
                                alreadyAllocatedPictures.add(picture);
                                bar.addPicture(picture);
                            }
                        }
                    }
                }

                if (this.maxY < bar.getHeight()) {
                    this.maxY = bar.getHeight();
                }
                category.addBar(bar);
            }
        }
    }

    @Override
    protected void calculate() {
        this.allocatePicturesToCategories();
        this.dataIsCalculated(true);

        /* Check values */
        int count = 0;

        for (int i = 0; i < this.categories.length; ++i) {

            if (this.categories[i].getMaxValueX() > this.maxX) {
                this.logger.error("found bigger X value in category: " + this.categories[i].getMaxValueX() + " >= "
                        + this.maxX + " (" + i + ")");
            }

            if (this.categories[i].getMinValueX() < this.minX) {
                this.logger.error("found smaller X value in category: " + this.categories[i].getMinValueX() + " <= "
                        + this.minX + " (" + i + ")");
            }

            for (final Bar bar : this.categories[i].getBars()) {
                count += bar.getHeight();
            }
        }

        if (this.maxY != this.getMaxY()) {
            this.logger.error(" this.maxY != this.getMaxY() :" + this.maxY + " != " + this.getMaxY());
        }

        if (this.maxX != this.getMaxX()) {
            this.logger.error(" this.maxX != this.getMaxX() :" + this.maxX + " != " + this.getMaxX());
        }

        final int pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), this.getXAxis()
                .getParameter());

        if (pictureCount != count) {

            if (this.getPicturesWithMissingExifParameter().isEmpty()) {
                this.logger.error("pictureCount != count    " + pictureCount + " != " + count
                        + " , das riecht nach nem bug, da wurde was vergessen!");
            } else {
                this.logger
                        .error("pictureCount != count    "
                                + pictureCount
                                + " != "
                                + count
                                + " , aber vorsicht getPicturesWithMissingExifParameter enthält elemente, darin kann ein Bild auch mehrfach vertreten sein: "
                                + this.getPicturesWithMissingExifParameter().size());
            }
        }
    }

    private void calculateExtremeValues() {

        /* initializing maximum with the smallest possible and the minimum with the largest possible number */
        this.maxX = -Double.MAX_VALUE;
        this.maxY = -Double.MAX_VALUE;

        this.minX = Double.MAX_VALUE;
        this.minY = Double.MAX_VALUE;

        /* find the biggest and smallest value on all valid pictures. It is needed e.g. for scaling the categories. */
        final List<PictureInterface> validPictures = Validator.getValidPictures(this.getPictureContainer(), this
                .getXAxis().getParameter());

        for (final PictureInterface picture : validPictures) {
            final Object xParameter = picture.getExifParameter(this.getXAxis().getParameter());

            /* TWEAK: allow other types than double and int */
            final Double xValue = Converter.objectToDouble(xParameter);

            this.maxX = Math.max(this.maxX, xValue);
            this.minX = Math.min(this.minX, xValue);
        }
    }

    private void generateCategories() {
        this.calculateExtremeValues();

        int numberOfCategories = 5;
        final int pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), this.getXAxis()
                .getParameter());

        /* reduce the number of categories if we have only a few pictures */
        if (numberOfCategories > pictureCount) {
            if (pictureCount > 0) {
                numberOfCategories = pictureCount;
            } else {
                numberOfCategories = 1;
            }
        }
        this.categories = new Category[numberOfCategories];

        /* calculate the range of the values for finding a good category size */
        double deltaX = Math.abs(this.maxX - this.minX);

        /* FIXME what are we doing here?! */
        /* if we have only one picture, we should have at least one category to put it in */
        if (deltaX == 0) {
            deltaX = 1;
            this.maxX = this.minX + deltaX;
        }

        this.xCategorySize = (deltaX / numberOfCategories);

        this.logger.trace("MinX: " + this.minX);
        this.logger.trace("MaxX: " + this.maxX);

        /*
         * Calculate the range of each category.
         * Minimum is inclusive and maximum is exclusive
         * except the last category which must contain the last element.
         */
        double minValueX;
        double maxValueX;

        for (int i = 0; i < this.categories.length; ++i) {
            minValueX = this.minX + i * this.xCategorySize;
            maxValueX = minValueX + this.xCategorySize;

            this.logger.trace("MinValue X: " + minValueX);
            this.logger.trace("MaxValue X: " + maxValueX);

            this.categories[i] = new Category(null, minValueX, maxValueX);

            this.logger.trace("this.categories[i].getMinValueX(): " + this.categories[i].getMinValueX());
            this.logger.trace("this.categories[i].getMaxValueX(): " + this.categories[i].getMaxValueX());
        }
    }

    /**
     * Caculates the statistic categories and give them back.
     * 
     * @return the statistic categories
     */
    public Category[] getCategories() {
        this.calculateIfRequired();

        return this.categories;
    }

    @Override
    public boolean isModelValid() {
        this.calculateIfRequired();

        if (this.maxX < this.minX) {
            this.logger.info("maxX < minX");
            return false;
        }

        if (Validator.getValidPicturesCount(this.getPictureContainer(), this.getXAxis().getParameter()) == 0) {
            this.logger.info("getValidPicturesCount == 0");
            return false;
        }
        return true;
    }
}