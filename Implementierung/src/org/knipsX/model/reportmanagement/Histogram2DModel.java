package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
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

    private Category[] categories;
    private double xCategorySize;
    Logger log = Logger.getLogger(this.getClass());

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
        /*
         * Before we can allocate pictures to categories, we need to compute the categories first.
         */
        this.generateCategories();
        
        
        Double xValue;
        Category category;
        Bar bar;

        // there is no negative count of pictures, so the minum is zero.
        this.minY = 0;

        /*
         * We will memorise each picture, we have allocated, in a list. 
         * So we can detect if a picture may have been allocated into more than one category.
         * If a picture is allocated into more than one category it is very likely that the generateCategories() method does not work properly.
         */
        final ArrayList<Picture> allreadyAllocatedPictures = new ArrayList<Picture>();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {

            /*
             * each x coordinate = each category
             */
            for (int i = 0; i < this.categories.length; i++) {
                /*
                 * create a bar for each pictureContainer in each Category
                 */
                bar = new Bar(pictureContainer);
                category = this.categories[i];

                for (final Picture picture : pictureContainer) {

                    boolean pictureValid = true;

                    final ExifParameter xParameter = this.getxAxis().getParameter();

                    final Object xValueObject = picture.getExifParameter(xParameter);

                    if (xValueObject == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(xParameter, picture));
                        pictureValid = false;
                    }

                    if (pictureValid) {
                        xValue = Converter.objectToDouble(xValueObject);

                        // if x value fits between <= category <
                        if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())) {
                            /*
                             * Picture fits in the category, yeah!
                             */
                            if (allreadyAllocatedPictures.contains(picture)) {
                                this.log.error("Picture " + picture.getName() + " already classified. x: " + xValue);
                                this.log.error("in Category: " + "max X: " + category.getMaxValueX() + "  min x: ");
                            }
                            allreadyAllocatedPictures.add(picture);
                            bar.addPicture(picture);

                            // or if x is last
                        } else if (((i + 1) == this.categories.length)) {

                            // and fits in category.maxValue == value
                            if (xValue == category.getMaxValueX()) {
                                if (allreadyAllocatedPictures.contains(picture)) {
                                    this.log
                                            .error("Picture " + picture.getName() + " already classified. x: " + xValue);
                                    this.log.error("in Category: " + "max X: " + category.getMaxValueX() + "  min x: ");
                                }
                                allreadyAllocatedPictures.add(picture);

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

        for (int i = 0; i < this.categories.length; i++) {
            if (this.categories[i].getMaxValueX() > this.maxX) {
                this.log.error("found bigger X value in category: " + this.categories[i].getMaxValueX() + " >= " + this.maxX
                        + " (" + i + ")");
            }

            if (this.categories[i].getMinValueX() < this.minX) {
                this.log.error("found smaller X value in category: " + this.categories[i].getMinValueX() + " <= " + this.minX
                        + " (" + i + ")");
            }

            for (final Bar bar : this.categories[i].getBars()) {
                count += bar.getHeight();
            }

        }

        if (this.maxY != this.getMaxY()) {
            this.log.error(" this.maxY != this.getMaxY() :" + this.maxY + " != " + this.getMaxY());
        }
        if (this.maxX != this.getMaxX()) {
            this.log.error(" this.maxX != this.getMaxX() :" + this.maxX + " != " + this.getMaxX());
        }

        final int pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), this.getxAxis().getParameter());

        if (pictureCount != count) {

            if (this.getPicturesWithMissingExifParameter().isEmpty()) {
                this.log.error("pictureCount != count    " + pictureCount + " != " + count
                        + " , das riecht nach nem bug, da wurde was vergessen!");
            } else {
                this.log
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

        /*
         * initializing maximum with the smallest possible and the minimum with the largest possible number.
         */
        this.maxX = -Double.MAX_VALUE;
        this.maxY = -Double.MAX_VALUE;

        this.minX = Double.MAX_VALUE;
        this.minY = Double.MAX_VALUE;

        /*
         * Find the biggest and smallest value on all valid pictures.
         * It is needed e.g. for scaling the categories.
         */
        for (final Picture picture : Validator.getValidPictures(this.getPictureContainer(), this.getxAxis()
                .getParameter())) {

            final Object xParameter = picture.getExifParameter(this.getxAxis().getParameter());

            // TWEAK: allow other types than double and int

            final Double xValue = Converter.objectToDouble(xParameter);

            if (this.maxX < xValue) {
                this.maxX = xValue;
            }
            if (this.minX > xValue) {
                this.minX = xValue;
            }

        }

    }

    private void generateCategories() {
        this.calculateExtremeValues();
        int numberOfCategories = 5;

        int pictureCount = 0;

        pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), this.getxAxis().getParameter());

        /*
         * Reduce the number of categories if we have only a few pictures
         */
        if (numberOfCategories > pictureCount) {
            if (pictureCount > 0) {
                numberOfCategories = pictureCount;
            } else {
                numberOfCategories = 1;
            }
        }

        this.categories = new Category[numberOfCategories];

        /*
         * calculate the range of the values for finding a good category size
         */
        double deltaX = Math.abs(this.maxX - this.minX);

        /*
         * if we have only one picture, we should have at least one category to put it in
         */
        if (deltaX == 0) {
            deltaX = 1;
            this.maxX = this.minX + deltaX;
        }

        this.xCategorySize = (deltaX / numberOfCategories);

        log.trace("MinX: " + this.minX);
        log.trace("MaxX: " + this.maxX);
        
        /*
         * Calculate the range of each category.
         * Minimum is inclusive and maximum is exclusive 
         * except the last category which must contain the last element.
         */
        double minValueX;
        double maxValueX;

        for (int i = 0; i < this.categories.length; i++) {

            minValueX = this.minX + i * this.xCategorySize;
            maxValueX = minValueX + this.xCategorySize;
            
            log.trace("MinValue X: " + minValueX);
            log.trace("MaxValue X: " + maxValueX);

            this.categories[i] = new Category(null, minValueX, maxValueX);
            
            log.trace("this.categories[i].getMinValueX(): " + this.categories[i].getMinValueX());
            log.trace("this.categories[i].getMaxValueX(): " + this.categories[i].getMaxValueX());

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
        final Logger logger = Logger.getLogger(this.getClass());

        if (this.maxX < this.minX) {
            logger.info("maxX < minX");
            return false;
        }

        if (Validator.getValidPicturesCount(this.getPictureContainer(), this.getxAxis().getParameter()) == 0) {
            logger.info("getValidPicturesCount == 0");
            return false;
        }

        return true;
    }

}
